package model;

/**
 * 订单项实体类，封装单个商品在订单中的价格、数量及关联信息
 */
public class OrderItem {
    // 订单项ID，数据库自增主键
    private int id;
    // 单价（下单时商品价格）
    private float price;
    // 购买数量
    private int amount;
    // 商品名称（方便在订单列表中展示）
    private String goodsName;
    // 关联的商品对象
    private Goods goods;
    // 所属订单对象，用于反向关联（对应 order_id 外键）
    private Order order;

    /**
     * 框架映射专用：设置商品名称字段
     * @param name 商品名称
     */
    public void setName(String name) {
        this.goodsName = name;
    }

    /**
     * 获取在订单中展示的商品名称
     * @return 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取订单项ID
     * @return 订单项ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置订单项ID
     * @param id 订单项ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取单价（下单时的商品价格）
     * @return 单价
     */
    public float getPrice() {
        return price;
    }

    /**
     * 设置单价（下单时的商品价格）
     * @param price 单价
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * 获取购买数量
     * @return 购买数量
     */
    public int getAmount() {
        return amount;
    }

    /**
     * 设置购买数量
     * @param amount 购买数量
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * 获取关联的商品对象
     * @return 商品对象
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * 设置关联的商品对象
     * @param goods 商品对象
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    /**
     * 获取所属的订单对象
     * @return 订单对象
     */
    public Order getOrder() {
        return order;
    }

    /**
     * 设置所属订单对象（外键 order_id）
     * @param order 订单对象
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * 无参构造，用于框架或 Bean 实例化
     */
    public OrderItem() {
        super();
    }

    /**
     * 全参构造，用于手动创建订单项
     * @param price 单价
     * @param amount 数量
     * @param goods 关联的商品对象
     * @param order 所属订单对象
     */
    public OrderItem(float price, int amount, Goods goods, Order order) {
        super();
        this.price = price;
        this.amount = amount;
        this.goods = goods;
        this.order = order;
    }
}
