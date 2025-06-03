package service;

import dao.GoodsDao;
import model.Goods;
import model.Page;


import javax.management.monitor.StringMonitorMBean;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 商品业务层服务类，负责包装 GoodsDao 的数据访问方法，并处理分页、异常等逻辑
 */
public class GoodsService {
    // DAO 对象，用于执行底层数据库操作
    private GoodsDao gDao = new GoodsDao();

    /**
     * 根据推荐类型获取原始商品数据列表（Map 格式）
     *
     * @param recommendType 推荐类型：1=轮播，2=热销，3=新品
     * @return List<Map<String,Object>> 商品数据列表，若查询异常则返回 null
     */
    public List<Map<String, Object>> getGoodsList(int recommendType) {
        List<Map<String, Object>> list = null;
        try {
            list = gDao.getGoodsList(recommendType);
        } catch (SQLException e) {
            // 打印异常，后续可以替换为统一日志记录或业务异常包装
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取首页轮播推荐的商品列表
     *
     * @return List<Map<String,Object>> 轮播商品列表，若查询异常则返回 null
     */
    public List<Map<String, Object>> getScrollGood() {
        List<Map<String, Object>> list = null;
        try {
            list = gDao.getScrollGood();
        } catch (SQLException e) {
            // 异常处理
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据分类 ID 分页查询商品实体列表
     *
     * @param typeID     分类 ID，0 表示不按照分类筛选
     * @param pageNumber 当前页码（从 1 开始）
     * @param pageSize   每页显示条数
     * @return List<Goods> 商品实体列表，若查询异常则返回 null
     */
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) {
        List<Goods> list = null;
        try {
            list = gDao.selectGoodsByTypeID(typeID, pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据分类 ID 获取分页对象，包含总页数、总记录数及当前页数据
     *
     * @param typeID     分类 ID，0 表示所有分类
     * @param pageNumber 当前页码（从 1 开始）
     * @return Page 分页信息对象，list 字段包含当前页商品列表
     */
    public Page selectPageByTypeID(int typeID, int pageNumber) {
        // 初始化分页对象并设置当前页
        Page p = new Page();
        p.setPageNumber(pageNumber);

        // 查询总记录数
        int totalCount = 0;
        try {
            totalCount = gDao.getCountOfGoodsByTypeID(typeID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 每页固定8条，计算总页数
        p.SetPageSizeAndTotalCount(8, totalCount);

        // 查询当前页具体数据
        List list = null;
        try {
            list = gDao.selectGoodsByTypeID(typeID, pageNumber, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    /**
     * 根据推荐类型获取分页后的商品实体列表，并在每个 Goods 对象上设置 scroll/hot/new 标识
     *
     * @param type       推荐类型：1=轮播，2=热销，3=新品，0 表示全部
     * @param pageNumber 当前页码（从 1 开始）
     * @return Page 分页信息对象，list 字段包含当前页商品实体列表
     */
    @SuppressWarnings("unchecked")
    public Page getGoodsRecommendPage(int type, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);

        // 查询推荐商品总数
        int totalCount = 0;
        try {
            totalCount = gDao.getRecommendCountOfGoodsByTypeID(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);

        // 查询当前页推荐商品列表
        List list = null;
        try {
            list = gDao.selectGoodsbyRecommend(type, pageNumber, 8);
            // 遍历列表，为每个商品设置推荐标识
            for (Goods g : (List<Goods>)list) {
                g.setScroll(gDao.isScroll(g));
                g.setHot(gDao.isHot(g));
                g.setNew(gDao.isNew(g));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        p.setList(list);
        return p;
    }

    /**
     * 根据商品 ID 查询单个商品实体
     *
     * @param id 商品 ID
     * @return Goods 商品对象，若查询异常或不存在则返回 null
     */
    public Goods getGoodsById(int id) {
        Goods g = null;
        try {
            g = gDao.getGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return g;
    }

    /**
     * 根据关键字分页查询商品，并返回分页结果
     *
     * @param keyword    搜索关键字
     * @param pageNumber 当前页码（从 1 开始）
     * @return Page 分页信息对象，list 字段包含搜索结果的当前页数据
     */
    public Page getSearchGoodsPage(String keyword, int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);

        // 查询搜索结果总数
        int totalCount = 0;
        try {
            totalCount = gDao.getSearchCount(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.SetPageSizeAndTotalCount(8, totalCount);

        // 查询当前页搜索结果数据
        List list = null;
        try {
            list = gDao.selectSearchGoods(keyword, pageNumber, 8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        p.setList(list);
        return p;
    }

    /**
     * 为指定商品添加推荐记录
     *
     * @param id   商品 ID
     * @param type 推荐类型：1=轮播，2=热销，3=新品
     */
    public void addRecommend(int id, int type) {
        try {
            gDao.addRecommend(id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除指定商品的推荐记录
     *
     * @param id   商品 ID
     * @param type 推荐类型
     */
    public void removeRecommend(int id, int type) {
        try {
            gDao.removeRecommend(id, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增商品到数据库
     *
     * @param goods 待新增的商品对象
     */
    public void insert(Goods goods) {
        try {
            gDao.insert(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新数据库中已有的商品信息
     *
     * @param goods 包含更新字段的商品对象
     */
    public void update(Goods goods) {
        try {
            gDao.update(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据商品 ID 从数据库中删除商品记录
     *
     * @param id 商品 ID
     */
    public void delete(int id) {
        try {
            gDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
