package dao;

import model.*;
import org.apache.commons.dbutils.*;
import utils.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import org.apache.commons.dbutils.handlers.*;

public class OrderDao {

    /**
     * 插入订单记录（使用事务连接）
     * @param con 数据库连接对象
     * @param order 包含总价、数量、状态、支付类型、收货信息和用户ID的订单对象
     * @throws SQLException 数据库操作异常
     */
    public void insertOrder(Connection con, Order order) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO `order` (total, amount, status, paytype, name, phone, address, datetime, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // 执行插入操作，使用事务连接 con
        runner.update(con, sql,
                order.getTotal(),
                order.getAmount(),
                order.getStatus(),
                order.getPaytype(),
                order.getName(),
                order.getPhone(),
                order.getAddress(),
                order.getDatetime(),
                order.getUser().getId());
    }

    /**
     * 获取上一次插入操作生成的自增主键ID
     * @param con 数据库连接对象
     * @return int 自增ID
     * @throws SQLException 数据库操作异常
     */
    public int getLastInsertId(Connection con) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "SELECT LAST_INSERT_ID()";
        // 使用 ScalarHandler 获取单值结果，并转换为 BigInteger
        BigInteger bi = runner.query(con, sql, new ScalarHandler<BigInteger>());
        return Integer.parseInt(bi.toString());
    }

    /**
     * 插入订单项记录（使用事务连接）
     * @param con 数据库连接对象
     * @param item 包含价格、数量、商品ID和订单ID的订单项对象
     * @throws SQLException 数据库操作异常
     */
    public void insertOrderItem(Connection con, OrderItem item) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO orderitem (price, amount, goods_id, order_id) VALUES (?, ?, ?, ?)";
        // 执行插入操作，绑定四个参数
        runner.update(con, sql,
                item.getPrice(),
                item.getAmount(),
                item.getGoods().getId(),
                item.getOrder().getId());
    }

    /**
     * 查询指定用户的所有订单，按下单时间降序排列
     * @param userId 用户ID
     * @return List<Order> 订单列表
     * @throws SQLException 数据库操作异常
     */
    public List<Order> selectAll(int userId) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM `order` WHERE user_id = ? ORDER BY datetime DESC";
        // 使用 BeanListHandler 自动映射为 Order 对象列表
        return runner.query(sql, new BeanListHandler<Order>(Order.class), userId);
    }

    /**
     * 查询指定订单的所有订单项，并关联商品名称
     * @param orderId 订单ID
     * @return List<OrderItem> 订单项列表
     * @throws SQLException 数据库操作异常
     */
    public List<OrderItem> selectAllItem(int orderId) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT i.id, i.price, i.amount, g.name " +
                "FROM orderitem i, goods g " +
                "WHERE i.order_id = ? AND i.goods_id = g.id";
        // 使用 BeanListHandler 自动映射为 OrderItem 对象列表
        return runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), orderId);
    }

    /**
     * 获取订单总数，可按状态过滤
     * @param status 订单状态（0 表示不限制）
     * @return int 订单数量
     * @throws SQLException 数据库操作异常
     */
    public int getOrderCount(int status) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql;
        if (status == 0) {
            // 不限制状态，统计所有订单
            sql = "SELECT COUNT(*) FROM `order`";
            return runner.query(sql, new ScalarHandler<Long>()).intValue();
        } else {
            // 按状态过滤统计
            sql = "SELECT COUNT(*) FROM `order` WHERE status = ?";
            return runner.query(sql, new ScalarHandler<Long>(), status).intValue();
        }
    }

    /**
     * 分页查询订单列表，可按状态筛选，并关联用户名称
     * @param status 订单状态（0 表示不限制）
     * @param pageNumber 页码（从1开始）
     * @param pageSize 每页数量
     * @return List<Order> 订单列表
     * @throws SQLException 数据库操作异常
     */
    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if (status == 0) {
            // 不限制状态的分页查询
            String sql = "SELECT o.id, o.total, o.amount, o.status, o.paytype, o.name, o.phone, o.address, o.datetime, u.username " +
                    "FROM `order` o, user u " +
                    "WHERE o.user_id = u.id " +
                    "ORDER BY o.datetime DESC LIMIT ?, ?";
            return runner.query(sql, new BeanListHandler<Order>(Order.class),
                    (pageNumber - 1) * pageSize, pageSize);
        } else {
            // 限制状态的分页查询
            String sql = "SELECT o.id, o.total, o.amount, o.status, o.paytype, o.name, o.phone, o.address, o.datetime, u.username " +
                    "FROM `order` o, user u " +
                    "WHERE o.user_id = u.id AND o.status = ? " +
                    "ORDER BY o.datetime DESC LIMIT ?, ?";
            return runner.query(sql, new BeanListHandler<Order>(Order.class),
                    status, (pageNumber - 1) * pageSize, pageSize);
        }
    }

    /**
     * 更新订单状态
     * @param id    订单ID
     * @param status 新状态值
     * @throws SQLException 数据库操作异常
     */
    public void updateStatus(int id, int status) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE `order` SET status = ? WHERE id = ?";
        runner.update(sql, status, id);
    }

    /**
     * 删除订单记录（使用事务连接）
     * @param con 数据库连接对象
     * @param id  订单ID
     * @throws SQLException 数据库操作异常
     */
    public void deleteOrder(Connection con, int id) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "DELETE FROM `order` WHERE id = ?";
        runner.update(con, sql, id);
    }

    /**
     * 删除指定订单的所有订单项（使用事务连接）
     * @param con 数据库连接对象
     * @param id  订单ID
     * @throws SQLException 数据库操作异常
     */
    public void deleteOrderItem(Connection con, int id) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "DELETE FROM orderitem WHERE order_id = ?";
        runner.update(con, sql, id);
    }
}
