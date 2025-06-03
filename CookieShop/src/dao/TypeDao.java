package dao;

import model.Type;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class TypeDao {

    /**
     * 获取所有商品类型列表
     * @return List<Type> 商品类型对象集合
     * @throws SQLException 数据库操作异常
     */
    public List<Type> getAllType() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 查询所有类型记录
        String sql = "SELECT * FROM type";
        // 使用 BeanListHandler 将结果映射为 Type 对象列表
        return runner.query(sql, new BeanListHandler<Type>(Type.class));
    }

    /**
     * 根据类型ID查询类型对象（与 getById 功能重复，可按需保留）
     * @param typeId 类型ID
     * @return Type 对应ID的类型对象
     * @throws SQLException 数据库操作异常
     */
    public Type selectTypeNameByID(int typeId) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 根据ID查询单条记录
        String sql = "SELECT * FROM type WHERE id = ?";
        return runner.query(sql, new BeanHandler<Type>(Type.class), typeId);
    }

    /**
     * 根据类型ID查询类型对象
     * @param id 类型ID
     * @return Type 类型对象
     * @throws SQLException 数据库操作异常
     */
    public Type select(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM type WHERE id = ?";
        // BeanHandler 映射单个 Type 对象
        return runner.query(sql, new BeanHandler<Type>(Type.class), id);
    }

    /**
     * 插入新的类型记录
     * @param t 包含名称的类型对象
     * @throws SQLException 数据库操作异常
     */
    public void insert(Type t) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 插入操作，只需提供名称字段
        String sql = "INSERT INTO type(name) VALUES(?)";
        runner.update(sql, t.getName());
    }

    /**
     * 更新已有类型名称
     * @param t 包含ID和新名称的类型对象
     * @throws SQLException 数据库操作异常
     */
    public void update(Type t) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 根据ID更新名称字段
        String sql = "UPDATE type SET name = ? WHERE id = ?";
        runner.update(sql, t.getName(), t.getId());
    }

    /**
     * 删除指定类型记录
     * @param id 要删除的类型ID
     * @throws SQLException 数据库操作异常
     */
    public void delete(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        // 根据ID删除记录
        String sql = "DELETE FROM type WHERE id = ?";
        runner.update(sql, id);
    }
}
