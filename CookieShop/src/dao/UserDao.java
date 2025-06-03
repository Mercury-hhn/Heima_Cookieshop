package dao;

import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class UserDao {

    /**
     * 添加新用户
     * @param user 用户对象，包含用户名、邮箱、密码、姓名、电话、地址及权限和验证状态
     * @throws SQLException 数据库操作异常
     */
    public void addUser(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "INSERT INTO user(username, email, password, name, phone, address, isadmin, isvalidate) VALUES(?,?,?,?,?,?,?,?)";
        // 执行插入操作，按顺序绑定 user 对象属性
        runner.update(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.isIsadmin(),
                user.isIsvalidate());
    }

    /**
     * 检查用户名是否已存在
     * @param username 待检测的用户名
     * @return boolean 存在返回 true，否则 false
     * @throws SQLException 数据库操作异常
     */
    public boolean isUsernameExist(String username) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE username = ?";
        // 查询匹配用户名的单条记录
        User u = runner.query(sql, new BeanHandler<User>(User.class), username);
        return u != null;
    }

    /**
     * 检查邮箱是否已存在
     * @param email 待检测的邮箱
     * @return boolean 存在返回 true，否则 false
     * @throws SQLException 数据库操作异常
     */
    public boolean isEmailExist(String email) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE email = ?";
        // 查询匹配邮箱的单条记录
        User u = runner.query(sql, new BeanHandler<User>(User.class), email);
        return u != null;
    }

    /**
     * 根据用户名和密码查询用户（用于登录验证）
     * @param username 用户名
     * @param password 密码
     * @return User 匹配的用户对象，若无则返回 null
     * @throws SQLException 数据库操作异常
     */
    public User selectByUsernamePassword(String username, String password) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        return runner.query(sql, new BeanHandler<User>(User.class), username, password);
    }

    /**
     * 根据邮箱和密码查询用户（用于邮箱登录验证）
     * @param email 邮箱地址
     * @param password 密码
     * @return User 匹配的用户对象，若无则返回 null
     * @throws SQLException 数据库操作异常
     */
    public User selectByEmailPassword(String email, String password) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        return runner.query(sql, new BeanHandler<User>(User.class), email, password);
    }

    /**
     * 根据用户ID查询用户信息
     * @param id 用户ID
     * @return User 用户对象，若无则返回 null
     * @throws SQLException 数据库操作异常
     */
    public User selectById(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user WHERE id = ?";
        return runner.query(sql, new BeanHandler<User>(User.class), id);
    }

    /**
     * 更新用户的姓名、电话和地址
     * @param user 包含更新信息的用户对象（需包含ID）
     * @throws SQLException 数据库操作异常
     */
    public void updateUserAddress(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE user SET name = ?, phone = ?, address = ? WHERE id = ?";
        runner.update(sql,
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getId());
    }

    /**
     * 更新用户密码
     * @param user 包含新密码及用户ID的用户对象
     * @throws SQLException 数据库操作异常
     */
    public void updatePwd(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        runner.update(sql, user.getPassword(), user.getId());
    }

    /**
     * 查询用户总数（用于后台管理分页）
     * @return int 用户总记录数
     * @throws SQLException 数据库操作异常
     */
    public int selectUserCount() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT COUNT(*) FROM user";
        // 使用 ScalarHandler 获取单行单列的计数
        return runner.query(sql, new ScalarHandler<Long>()).intValue();
    }

    /**
     * 分页查询用户列表
     * @param pageNo   页码（从1开始）
     * @param pageSize 每页记录数
     * @return List<User> 用户对象列表
     * @throws SQLException 数据库操作异常
     */
    public List<User> selectUserList(int pageNo, int pageSize) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "SELECT * FROM user LIMIT ?, ?";
        return runner.query(sql,
                new BeanListHandler<User>(User.class),
                (pageNo - 1) * pageSize,
                pageSize);
    }

    /**
     * 删除用户记录
     * @param id 用户ID
     * @throws SQLException 数据库操作异常
     */
    public void delete(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "DELETE FROM user WHERE id = ?";
        runner.update(sql, id);
    }
}
