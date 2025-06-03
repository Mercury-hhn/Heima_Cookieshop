package dao;

import model.Goods;
import model.Recommend;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.*;

public class GoodsDao {
    /**
     * 根据推荐类型获取商品列表
     * @param recommendType 推荐类型（1-轮播，2-热卖，3-新品等）
     * @return List<Map<String,Object>> 商品信息集合，每条记录包含 id, name, cover, price, typename
     * @throws SQLException 数据库操作异常
     */
    public List<Map<String,Object>> getGoodsList(int recommendType) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 查询语句：通过 recommend 表关联 goods 和 type 表
        String sql = "SELECT g.id, g.name, g.cover, g.price, t.name AS typename " +
                "FROM recommend r, goods g, type t " +
                "WHERE r.type = ? AND r.goods_id = g.id AND g.type_id = t.id";
        // 执行查询，返回 List<Map>，每个 Map 对应一行数据
        return runner.query(sql, new MapListHandler(), recommendType);
    }

    /**
     * 获取所有轮播商品（不区分推荐类型）
     * @return List<Map<String,Object>> 商品信息集合，包括 id, name, cover, price
     * @throws SQLException 数据库操作异常
     */
    public List<Map<String,Object>> getScrollGood() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 查询语句：关联 recommend 和 goods 表
        String sql = "SELECT g.id, g.name, g.cover, g.price " +
                "FROM recommend r, goods g " +
                "WHERE r.goods_id = g.id";
        // 返回多条记录的 Map 列表
        return runner.query(sql, new MapListHandler());
    }

    /**
     * 分页按类型查询商品
     * @param typeID 商品类型 ID，0 表示所有类型
     * @param pageNumber 第几页（从 1 开始）
     * @param pageSize 每页记录数
     * @return List<Goods> 对应页的商品对象列表
     * @throws SQLException 数据库操作异常
     */
    public List<Goods> selectGoodsByTypeID(int typeID, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if (typeID == 0) {
            // 查询所有商品并分页
            String sql = "SELECT * FROM goods LIMIT ?, ?";
            return runner.query(sql, new BeanListHandler<>(Goods.class),
                    (pageNumber - 1) * pageSize, pageSize);
        } else {
            // 按类型过滤并分页
            String sql = "SELECT * FROM goods WHERE type_id = ? LIMIT ?, ?";
            return runner.query(sql, new BeanListHandler<>(Goods.class),
                    typeID, (pageNumber - 1) * pageSize, pageSize);
        }
    }

    /**
     * 获取指定类型的商品总数
     * @param typeID 商品类型 ID，0 表示所有类型
     * @return int 商品总数
     * @throws SQLException 数据库操作异常
     */
    public int getCountOfGoodsByTypeID(int typeID) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql;
        if (typeID == 0) {
            // 查询全部商品数量
            sql = "SELECT COUNT(*) FROM goods";
            return runner.query(sql, new ScalarHandler<Long>()).intValue();
        } else {
            // 查询指定类型商品数量
            sql = "SELECT COUNT(*) FROM goods WHERE type_id = ?";
            return runner.query(sql, new ScalarHandler<Long>(), typeID).intValue();
        }
    }

    /**
     * 根据推荐类型分页查询商品
     * @param type 推荐类型（0 表示不限制）
     * @param pageNumber 第几页（从 1 开始）
     * @param pageSize 每页记录数
     * @return List<Goods> 推荐商品对象列表
     * @throws SQLException 数据库操作异常
     */
    public List<Goods> selectGoodsbyRecommend(int type, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        if (type == 0) {
            // 不限制推荐类型，直接按商品 ID 分页
            String sql = "SELECT g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name AS typename " +
                    "FROM goods g, type t " +
                    "WHERE g.type_id = t.id " +
                    "ORDER BY g.id LIMIT ?, ?";
            return runner.query(sql, new BeanListHandler<>(Goods.class),
                    (pageNumber - 1) * pageSize, pageSize);
        }
        // 限制推荐类型，关联 recommend 表
        String sql = "SELECT g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name AS typename " +
                "FROM goods g, recommend r, type t " +
                "WHERE g.id = r.goods_id AND g.type_id = t.id AND r.type = ? " +
                "ORDER BY g.id LIMIT ?, ?";
        return runner.query(sql, new BeanListHandler<>(Goods.class),
                type, (pageNumber - 1) * pageSize, pageSize);
    }

    /**
     * 获取推荐商品总数
     * @param type 推荐类型，0 表示所有商品
     * @return int 推荐商品总数
     * @throws SQLException 数据库操作异常
     */
    public int getRecommendCountOfGoodsByTypeID(int type) throws SQLException {
        if (type == 0) {
            // 若不限制推荐类型，则调用通用统计方法
            return getCountOfGoodsByTypeID(0);
        }
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM recommend WHERE type = ?";
        return runner.query(sql, new ScalarHandler<Long>(), type).intValue();
    }

    /**
     * 根据商品 ID 获取单个商品详情
     * @param id 商品 ID
     * @return Goods 商品对象，包含类型 ID 和类型名称
     * @throws SQLException 数据库操作异常
     */
    public Goods getGoodsById(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT g.id, g.name, g.cover, g.image1, g.image2, g.price, g.intro, g.stock, " +
                "t.id AS typeid, t.name AS typename " +
                "FROM goods g, type t " +
                "WHERE g.id = ? AND g.type_id = t.id";
        return runner.query(sql, new BeanHandler<>(Goods.class), id);
    }

    /**
     * 获取搜索匹配的商品数量
     * @param keyword 搜索关键词
     * @return int 匹配商品总数
     * @throws SQLException 数据库操作异常
     */
    public int getSearchCount(String keyword) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM goods WHERE name LIKE ?";
        return runner.query(sql, new ScalarHandler<Long>(), "%" + keyword + "%").intValue();
    }

    /**
     * 分页查询搜索结果商品
     * @param keyword 搜索关键词
     * @param pageNumber 第几页（从 1 开始）
     * @param pageSize 每页记录数
     * @return List<Goods> 搜索结果商品列表
     * @throws SQLException 数据库操作异常
     */
    public List<Goods> selectSearchGoods(String keyword, int pageNumber, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM goods WHERE name LIKE ? LIMIT ?, ?";
        return runner.query(sql, new BeanListHandler<>(Goods.class),
                "%" + keyword + "%", (pageNumber - 1) * pageSize, pageSize);
    }

    /**
     * 判断商品是否在轮播列表中
     */
    public boolean isScroll(Goods g) throws SQLException {
        return isRecommend(g, 1);
    }

    /**
     * 判断商品是否热卖
     */
    public boolean isHot(Goods g) throws SQLException {
        return isRecommend(g, 2);
    }

    /**
     * 判断商品是否为新品
     */
    public boolean isNew(Goods g) throws SQLException {
        return isRecommend(g, 3);
    }

    /**
     * 私有方法：根据类型判断推荐表中是否存在记录
     * @param g 商品对象
     * @param type 推荐类型
     * @return boolean 存在返回 true，否则 false
     * @throws SQLException 数据库操作异常
     */
    private boolean isRecommend(Goods g, int type)	throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM recommend WHERE type = ? AND goods_id = ?";
        Recommend rec = runner.query(sql, new BeanHandler<>(Recommend.class), type, g.getId());
        return rec != null;
    }

    /**
     * 向推荐表中新增一条推荐记录
     * @param id 商品 ID
     * @param type 推荐类型
     * @throws SQLException 数据库操作异常
     */
    public void addRecommend(int id, int type) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO recommend(type, goods_id) VALUES(?, ?)";
        runner.update(sql, type, id);
    }

    /**
     * 从推荐表中移除一条推荐记录
     * @param id 商品 ID
     * @param type 推荐类型
     * @throws SQLException 数据库操作异常
     */
    public void removeRecommend(int id, int type) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "DELETE FROM recommend WHERE type = ? AND goods_id = ?";
        runner.update(sql, type, id);
    }

    /**
     * 插入新商品记录
     * @param g 商品对象
     * @throws SQLException 数据库操作异常
     */
    public void insert(Goods g) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO goods(name, cover, image1, image2, price, intro, stock, type_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        runner.update(sql, g.getName(), g.getCover(), g.getImage1(), g.getImage2(),
                g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId());
    }

    /**
     * 更新商品信息
     * @param g 商品对象（包含 ID）
     * @throws SQLException 数据库操作异常
     */
    public void update(Goods g) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE goods SET name = ?, cover = ?, image1 = ?, image2 = ?, price = ?, intro = ?, stock = ?, type_id = ? WHERE id = ?";
        runner.update(sql, g.getName(), g.getCover(), g.getImage1(), g.getImage2(),
                g.getPrice(), g.getIntro(), g.getStock(), g.getType().getId(), g.getId());
    }

    /**
     * 删除商品记录
     * @param id 商品 ID
     * @throws SQLException 数据库操作异常
     */
    public void delete(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "DELETE FROM goods WHERE id = ?";
        runner.update(sql, id);
    }
}
