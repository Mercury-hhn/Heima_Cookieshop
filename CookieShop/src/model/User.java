package model;

/**
 * 用户实体类，封装用户的基本信息、登录凭证及权限状态
 */
public class User {
    /** 用户ID，自增主键 */
    private int id;
    /** 登录用户名（唯一） */
    private String username;
    /** 登录邮箱（唯一） */
    private String email;
    /** 登录密码（建议存储前进行哈希加密） */
    private String password;
    /** 真实姓名/昵称，用于显示 */
    private String name;
    /** 联系电话 */
    private String phone;
    /** 收货地址或联系地址 */
    private String address;
    /** 管理员标识：true = 管理员，false = 普通用户 */
    private boolean isadmin = false;
    /** 验证状态：true = 已验证（激活/审核通过），false = 未验证 */
    private boolean isvalidate = false;

    /**
     * 获取用户ID
     * @return 用户ID
     */
    public int getId() {
        return id;
    }
    /**
     * 设置用户ID
     * @param id 用户ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取用户名（登录名）
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置用户名（登录名）
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取邮箱
     * @return 邮箱地址
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置邮箱
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码（注意：请勿明文返回，安全性考虑应做脱敏处理）
     * @return 密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置密码（建议调用前进行哈希加密）
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 返回用户的字符串表示，包含所有属性信息（仅调试时使用，生产环境需注意脱敏）
     */
    @Override
    public String toString() {
        return "User [id=" + id
                + ", username=" + username
                + ", email=" + email
                + ", password=" + password
                + ", name=" + name
                + ", phone=" + phone
                + ", address=" + address
                + ", isadmin=" + isadmin
                + ", isvalidate=" + isvalidate
                + "]";
    }

    /**
     * 获取真实姓名/昵称
     * @return 用户姓名
     */
    public String getName() {
        return name;
    }
    /**
     * 设置真实姓名/昵称
     * @param name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系电话
     * @return 电话号码
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置联系电话
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系地址或收货地址
     * @return 地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置联系地址或收货地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 检查用户是否具有管理员权限
     * @return true = 管理员，false = 普通用户
     */
    public boolean isIsadmin() {
        return isadmin;
    }
    /**
     * 设置管理员标识
     * @param isadmin true = 管理员
     */
    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    /**
     * 检查用户是否已验证（激活或审核通过）
     * @return true = 已验证
     */
    public boolean isIsvalidate() {
        return isvalidate;
    }
    /**
     * 设置验证状态
     * @param isvalidate true = 已验证
     */
    public void setIsvalidate(boolean isvalidate) {
        this.isvalidate = isvalidate;
    }

    /**
     * 全参数构造，设置用户所有字段
     * @param id         用户ID
     * @param username   登录用户名
     * @param email      邮箱
     * @param password   密码
     * @param name       真实姓名/昵称
     * @param phone      联系电话
     * @param address    地址
     * @param isadmin    管理员标识
     * @param isvalidate 验证状态
     */
    public User(int id, String username, String email, String password,
                String name, String phone, String address,
                boolean isadmin, boolean isvalidate) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = isadmin;
        this.isvalidate = isvalidate;
    }

    /**
     * 部分参数构造，创建新用户时常用，默认为非管理员且未验证
     * @param username 登录用户名
     * @param email    邮箱
     * @param password 密码
     * @param name     真实姓名/昵称
     * @param phone    联系电话
     * @param address  地址
     */
    public User(String username, String email, String password,
                String name, String phone, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.isadmin = false;
        this.isvalidate = false;
    }

    /**
     * 无参构造，用于框架或 Bean 实例化
     */
    public User() {
        super();
    }
}
