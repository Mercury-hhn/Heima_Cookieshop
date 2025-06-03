package model;

import utils.PriceUtils;

import java.util.*;

/**
 * 订单实体类，封装购物车中选购商品及订单详情，包括总价、数量、支付状态等
 */
public class Order {
    // 订单ID，数据库自增主键
    private int id;
    // 订单总价，使用 PriceUtils 进行精确计算
    private float total;
    // 订单中商品总数量
    private int amount;
    // 订单状态：1=未付款，2=已付款，3=已发货，4=已完成
    private int status;
    // 支付方式：1=微信，2=支付宝，3=货到付款
    private int paytype;
    // 收货人姓名
    private String name;
    // 收货人电话
    private String phone;
    // 收货地址
    private String address;
    // 下单时间
    private Date datetime;
    // 下单用户
    private User user;
    // 用于快速查找和更新 OrderItem 的映射：key=商品ID，value=订单项
    private Map<Integer, OrderItem> itemMap = new HashMap<>();
    // 用于前端展示的订单项列表
    private List<OrderItem> itemList = new ArrayList<>();

    /**
     * 框架或映射调用：根据用户名初始化 User 对象
     * @param username 登录用户名
     */
    public void setUsername(String username) {
        user = new User();
        user.setUsername(username);
    }

    /**
     * 向订单中添加一个商品，若已存在则数量+1，否则新建 OrderItem
     * 同时维护 total 和 amount 字段
     * @param g 要添加的商品
     */
    public void addGoods(Goods g) {
        int gid = g.getId();
        if (itemMap.containsKey(gid)) {
            // 已有该商品，则数量+1
            OrderItem item = itemMap.get(gid);
            item.setAmount(item.getAmount() + 1);
        } else {
            // 新增订单项
            OrderItem item = new OrderItem(g.getPrice(), 1, g, this);
            itemMap.put(gid, item);
        }
        // 更新订单商品总数和总价
        amount++;
        total = PriceUtils.add(total, g.getPrice());
    }

    /**
     * 获取用于展示的订单项列表
     * @return 订单项列表
     */
    public List<OrderItem> getItemList() {
        return itemList;
    }

    /**
     * 设置订单项列表（通常用于从数据库加载后填充）
     * @param itemList 订单项列表
     */
    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * 减少指定商品的数量1件，如果数量降至0则从订单中移除
     * 并更新 total 和 amount
     * @param goodsid 要减少的商品ID
     */
    public void lessen(int goodsid) {
        if (itemMap.containsKey(goodsid)) {
            OrderItem item = itemMap.get(goodsid);
            item.setAmount(item.getAmount() - 1);
            amount--;
            total = PriceUtils.subtract(total, item.getPrice());
            if (item.getAmount() <= 0) {
                // 数量为0时，移除该订单项
                itemMap.remove(goodsid);
            }
        }
    }

    /**
     * 从订单中删除指定商品的所有数量，并更新 total 和 amount
     * @param goodsid 要删除的商品ID
     */
    public void delete(int goodsid) {
        if (itemMap.containsKey(goodsid)) {
            OrderItem item = itemMap.get(goodsid);
            // 减去该商品所有数量对应的金额
            total = PriceUtils.subtract(total, item.getAmount() * item.getPrice());
            amount -= item.getAmount();
            itemMap.remove(goodsid);
        }
    }

    /**
     * 获取内部的订单项映射
     * @return key=商品ID, value=OrderItem
     */
    public Map<Integer, OrderItem> getItemMap() {
        return itemMap;
    }

    /**
     * 设置订单项映射（一般用于持久化后填充）
     * @param itemMap 订单项映射
     */
    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    // ===== 以下为标准的 getter/setter =====

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public int getPaytype() {
        return paytype;
    }
    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDatetime() {
        return datetime;
    }
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 无参构造，用于框架或手动创建空订单对象
     */
    public Order() {
        super();
    }
}
