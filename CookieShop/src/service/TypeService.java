package service;

import dao.TypeDao;
import model.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品类别业务服务类，封装 TypeDao 的数据访问方法，并处理异常
 */
public class TypeService {
    // DAO 对象，用于执行底层数据库操作
     TypeDao tDao = new TypeDao();

    /**
     * 查询所有商品类别
     *
     * @return List<Type> 所有类别列表，查询异常时返回 null
     */
    public List<Type> GetAllType() {
        List<Type> list = null;
        try {
            // 调用 DAO 方法，获取所有类别
            list = tDao.getAllType();
        } catch (SQLException e) {
            // 打印异常信息，实际项目中可改为日志记录
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据类别 ID 查询类别信息
     *
     * @param typeid 类别 ID
     * @return Type 对象，若查询异常或不存在则返回 null
     */
    public Type selectTypeNameByID(int typeid) {
        Type type = null;
        try {
            type = tDao.selectTypeNameByID(typeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    /**
     * 根据类别 ID 查询类别对象
     *
     * @param id 类别 ID
     * @return Type 对象，若查询异常或不存在则返回 null
     */
    public Type select(int id) {
        Type t = null;
        try {
            t = tDao.select(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 新增一个商品类别
     *
     * @param t 待插入的类别对象
     */
    public void insert(Type t) {
        try {
            tDao.insert(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新已有的商品类别信息
     *
     * @param t 包含更新字段的类别对象
     */
    public void update(Type t) {
        try {
            tDao.update(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 ID 删除商品类别
     *
     * @param id 要删除的类别 ID
     * @return boolean 删除是否成功，异常时返回 false
     */
    public boolean delete(int id) {
        try {
            tDao.delete(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
