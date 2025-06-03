package service;

import dao.OrderDao;
import model.Order;
import model.OrderItem;
import model.Page;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 订单业务层服务类，负责封装 OrderDao 的数据访问逻辑，
 * 并处理事务、分页及异常管理等
 */
public class OrderService {
    /** DAO 对象，用于执行底层数据库操作 */
    private OrderDao oDao = new OrderDao();

    /**
     * 添加新订单及其订单项，并在同一事务中提交。
     *
     * @param order 要插入的订单对象，其中已包含 itemMap
     */
    public void addOrder(Order order) {
        Connection con = null;
        try {
            // 获取数据库连接并手动管理事务
            con = DataSourceUtils.getConnection();
            con.setAutoCommit(false);

            // 插入订单主表
            oDao.insertOrder(con, order);
            // 获取刚插入的订单ID，并设置到 order 对象
            int id = oDao.getLastInsertId(con);
            order.setId(id);

            // 插入所有订单项
            for (OrderItem item : order.getItemMap().values()) {
                oDao.insertOrderItem(con, item);
            }

            // 全部插入成功后提交事务
            con.commit();
        } catch (SQLException e) {
            // 出现异常时回滚事务
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            // 事务结束后恢复默认自动提交，并关闭连接
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    /**
     * 查询指定用户的所有订单，并为每个订单加载其订单项列表。
     *
     * @param userId 用户 ID
     * @return List<Order> 该用户的订单列表；查询失败时返回 null
     */
    public List<Order> selectAll(int userId) {
        List<Order> list = null;
        try {
            // 查询订单主表
            list = oDao.selectAll(userId);
            // 对每个订单，填充其 OrderItem 列表
            for (Order o : list) {
                List<OrderItem> items = oDao.selectAllItem(o.getId());
                o.setItemList(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 分页查询订单列表，并为每个订单加载其订单项。
     *
     * @param status     订单状态：0 表示所有状态，否则筛选对应状态
     * @param pageNumber 当前页码，从 1 开始
     * @return Page       分页对象，包含总页数、总记录数及当前页数据
     */
    @SuppressWarnings("unchecked")
    public Page getOrderPage(int status, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);

        int pageSize = 10;  // 每页固定显示 10 条
        int totalCount = 0;
        try {
            // 查询总记录数
            totalCount = oDao.getOrderCount(status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 计算总页数并设置 pageSize/totalCount
        p.SetPageSizeAndTotalCount(pageSize, totalCount);

        List list = null;  // 使用原生 List 以兼容 Page.list 的 List<Object>
        try {
            // 查询当前页订单（包含用户信息）
            list = oDao.selectOrderList(status, pageNumber, pageSize);
            // 对每个订单填充订单项
            for (Object obj : list) {
                Order o = (Order) obj;
                List<OrderItem> items = oDao.selectAllItem(o.getId());
                o.setItemList(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    /**
     * 更新指定订单的状态。
     *
     * @param id     订单 ID
     * @param status 新状态值
     */
    public void updateStatus(int id, int status) {
        try {
            oDao.updateStatus(id, status);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定订单及其所有订单项，使用事务保证数据一致性。
     *
     * @param id 订单 ID
     */
    public void delete(int id) {
        Connection con = null;
        try {
            // 新开连接并关闭自动提交
            con = DataSourceUtils.getDataSource().getConnection();
            con.setAutoCommit(false);

            // 先删除订单项，再删除订单主表
            oDao.deleteOrderItem(con, id);
            oDao.deleteOrder(con, id);
            // 提交删除事务
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            // 恢复自动提交并关闭连接
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }
}
