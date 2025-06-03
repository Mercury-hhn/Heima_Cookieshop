package model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 商品分类实体类，封装分类 ID、名称及其 URL 编码形式
 */
public class Type {
	/** 分类 ID，自增主键 */
	private int id;
	/** 分类名称 */
	private String name;
	/** URL 编码后的分类名称，用于在链接或请求参数中安全传输 */
	private String encodeName;

	/**
	 * 获取 URL 编码后的分类名称
	 * @return encodeName URL 编码后的分类名称
	 */
	public String getEncodeName() {
		return encodeName;
	}

	/**
	 * 设置 URL 编码后的分类名称（通常不手动调用）
	 * @param encodeName URL 编码后的分类名称
	 */
	public void setEncodeName(String encodeName) {
		this.encodeName = encodeName;
	}

	/**
	 * 获取分类 ID
	 * @return 分类 ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置分类 ID
	 * @param id 分类 ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取分类名称
	 * @return 分类名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置分类名称，并自动生成 URL 编码后的名称
	 * @param name 分类名称
	 */
	public void setName(String name) {
		this.name = name;
		try {
			// 将名称按 UTF-8 编码，保存至 encodeName 字段
			this.encodeName = URLEncoder.encode(name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// 通常不会发生，UTF-8 为标准编码；在异常情况下打印堆栈以便排查
			e.printStackTrace();
		}
	}

	/**
	 * 全参构造
	 * @param id   分类 ID
	 * @param name 分类名称
	 */
	public Type(int id, String name) {
		super();
		this.id = id;
		// 使用 setName 确保 encodeName 也被正确设置
		setName(name);
	}

	/**
	 * 无参构造，用于框架或 Bean 实例化
	 */
	public Type() {
		super();
	}

	/**
	 * 仅指定名称的构造，可使 encodeName 在 setName 中自动生成
	 * @param name 分类名称
	 */
	public Type(String name) {
		super();
		setName(name);
	}
}
