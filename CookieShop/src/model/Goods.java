package model;

/**
 * 商品实体类，封装商品的属性信息，包括图片、价格、库存及分类等
 */
public class Goods {
	// 商品ID，唯一标识
	private int id;
	// 商品名称
	private String name;
	// 商品主图路径
	private String cover;
	// 商品详情图1路径
	private String image1;
	// 商品详情图2路径
	private String image2;
	// 商品价格
	private float price;
	// 商品简介
	private String intro;
	// 商品库存数量
	private int stock;
	// 商品所属分类对象
	private Type type;

	// 以下三个为页面推荐标识
	// 是否在首页轮播图中展示
	private boolean isScroll;
	// 是否被标记为“热门”推荐
	private boolean isHot;
	// 是否被标记为“新品”推荐
	private boolean isNew;

	/**
	 * 获取轮播推荐状态
	 * @return true 表示在轮播推荐中展示，否则 false
	 */
	public boolean getIsScroll() {
		return isScroll;
	}
	/**
	 * 设置轮播推荐状态
	 * @param isScroll true 表示在轮播推荐中展示，否则 false
	 */
	public void setScroll(boolean isScroll) {
		this.isScroll = isScroll;
	}

	/**
	 * 获取热门推荐状态
	 * @return true 表示热门推荐，否则 false
	 */
	public boolean getIsHot() {
		return isHot;
	}
	/**
	 * 设置热门推荐状态
	 * @param isHot true 表示热门推荐，否则 false
	 */
	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 * 获取新品推荐状态
	 * @return true 表示新品推荐，否则 false
	 */
	public boolean getIsNew() {
		return isNew;
	}
	/**
	 * 设置新品推荐状态
	 * @param isNew true 表示新品推荐，否则 false
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * 框架映射专用：根据数据库中的 type_id 自动构造 Type 对象并设置 ID
	 * @param typeid 分类 ID
	 */
	public void setTypeid(int typeid) {
		if (type == null) {
			type = new Type();
		}
		type.setId(typeid);
	}

	/**
	 * 框架映射专用：根据数据库中的 typename 字段自动构造 Type 对象并设置名称
	 * @param typename 分类名称
	 */
	public void setTypename(String typename) {
		if (type == null) {
			type = new Type();
		}
		type.setName(typename);
	}

	/**
	 * 输出 Goods 对象的字符串表示，包含主要字段值
	 */
	@Override
	public String toString() {
		return "Goods [id=" + id
				+ ", name=" + name
				+ ", cover=" + cover
				+ ", image1=" + image1
				+ ", image2=" + image2
				+ ", price=" + price
				+ ", intro=" + intro
				+ ", stock=" + stock
				+ ", type=" + type
				+ "]";
	}

	// ====== 以下为标准的 Getter/Setter ======

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	// ====== 构造函数 ======

	/**
	 * 无参构造，用于框架或 Bean 实例化
	 */
	public Goods() {
		super();
	}

	/**
	 * 全参构造，用于手动创建完整的 Goods 对象
	 * @param id 商品ID
	 * @param name 商品名称
	 * @param cover 主图路径
	 * @param image1 详情图1路径
	 * @param image2 详情图2路径
	 * @param price 价格
	 * @param intro 简介
	 * @param stock 库存
	 * @param type 分类对象
	 */
	public Goods(int id, String name, String cover,
				 String image1, String image2,
				 float price, String intro,
				 int stock, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.cover = cover;
		this.image1 = image1;
		this.image2 = image2;
		this.price = price;
		this.intro = intro;
		this.stock = stock;
		this.type = type;
	}
}
