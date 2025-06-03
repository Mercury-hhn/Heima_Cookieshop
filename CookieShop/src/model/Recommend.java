package model;

/**
 * 推荐实体类，封装商品的推荐信息，包括推荐类型及关联商品
 */
public class Recommend {
	/** 推荐ID，自增主键 */
	private int id;
	/** 推荐类型：1=轮播（Scroll），2=热销（Hot），3=新品（New） */
	private int type;
	/** 关联的商品对象 */
	private Goods goods;

	/**
	 * 获取推荐ID
	 * @return 推荐ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置推荐ID
	 * @param id 推荐ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取推荐类型
	 * @return 推荐类型（1=轮播，2=热销，3=新品）
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置推荐类型
	 * @param type 推荐类型（1=轮播，2=热销，3=新品）
	 */
	public void setType(int type) {
		this.type = type;
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
	 * 全参构造
	 *
	 * @param id     推荐ID
	 * @param type   推荐类型（1=轮播，2=热销，3=新品）
	 * @param goods  关联的商品对象
	 */
	public Recommend(int id, int type, Goods goods) {
		super();
		this.id = id;
		this.type = type;
		this.goods = goods;
	}

	/**
	 * 无参构造，用于框架或 Bean 实例化
	 */
	public Recommend() {
		super();
	}
}
