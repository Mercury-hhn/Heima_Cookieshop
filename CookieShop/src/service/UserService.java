package service;

import dao.UserDao;
import model.Page;
import model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户业务逻辑类，封装对 UserDao 的调用，
 * 提供注册、登录、分页查询、信息修改、删除等服务
 */
public class UserService {
    // DAO 层实例
    private UserDao uDao = new UserDao();

    /**
     * 用户注册方法，会检查用户名和邮箱是否已存在
     *
     * @param user 用户对象
     * @return 注册成功返回 true，已存在或异常返回 false
     */
    public boolean register(User user) {
        try {
            // 检查用户名是否重复
            if (uDao.isUsernameExist(user.getUsername())) {
                return false;
            }
            // 检查邮箱是否重复
            if (uDao.isEmailExist(user.getEmail())) {
                return false;
            }
            // 添加用户记录
            uDao.addUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户登录方法，支持用用户名或邮箱登录
     *
     * @param ue       用户名或邮箱
     * @param password 密码
     * @return 登录成功返回对应用户对象，否则返回 null
     */
    public User login(String ue, String password) {
        User user = null;
        try {
            // 尝试通过用户名登录
            user = uDao.selectByUsernamePassword(ue, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        }
        try {
            // 尝试通过邮箱登录
            user = uDao.selectByEmailPassword(ue, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户 ID 查询用户信息
     *
     * @param id 用户 ID
     * @return 用户对象；如查询失败返回 null
     */
    public User selectById(int id) {
        User u = null;
        try {
            u = uDao.selectById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    /**
     * 修改用户的收货信息（姓名、电话、地址）
     *
     * @param user 用户对象（必须包含 id）
     */
    public void updateUserAddress(User user) {
        try {
            uDao.updateUserAddress(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户密码
     *
     * @param user 用户对象（包含 id 与新密码）
     */
    public void updatePwd(User user) {
        try {
            uDao.updatePwd(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户分页列表（管理员后台使用）
     *
     * @param pageNumber 当前页码（从 1 开始）
     * @return Page 对象，包含用户列表、总页数、总记录数等信息
     */
    @SuppressWarnings("unchecked")
    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;  // 每页显示 7 个用户
        int totalCount = 0;

        try {
            totalCount = uDao.selectUserCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        p.SetPageSizeAndTotalCount(pageSize, totalCount);

        List<User> list = null;
        try {
            list = uDao.selectUserList(pageNumber, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        p.setList((List<Object>) (List<?>) list); // 类型转换以兼容 Page 泛型
        return p;
    }

    /**
     * 删除用户
     *
     * @param id 用户 ID
     * @return 删除成功返回 true，失败或异常返回 false
     */
    public boolean delete(int id) {
        try {
            uDao.delete(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
