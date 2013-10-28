package com.data;

/*物品&装备属性*/
public class Item {

	private int id;// id
	private int picId = -1;// 图片id
	private int styleId = -1;// 换装id
	private String name;// 名称
	private int type;// 物品类型
	private int num;// 数量

	public Item() {

	}

	/* Setter and Getter */
	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return this.num;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setPicId(int picId) {
		this.picId = picId;
	}

	public int getPicId() {
		return this.picId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getStyleId() {
		return this.styleId;
	}

}
