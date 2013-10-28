package com.data;

import java.util.LinkedList;

/*武将属性*/
public class WuJiang {

	private int id;// id
	private String name;// 姓名
	private int lev;// 等级
	private int quality;// 品质
	private int headId;// 头像id
	private int curExp;// 现有经验
	private int maxExp;// 最大经验
	private int attack;// 攻击
	private int defense;// 防御
	private int hp;// 血量
	private int own;// 带兵
	private int curPhysical;// 现有体力
	private int maxPhysical;// 最大体力
	private int sex;// 性别
	private int warrior_gongbing;// 弓兵
	private int warrior_bubing;// 步兵
	private int warrior_qibing;// 骑兵
	private String des;

	private LinkedList<Item> vecEquip;

	public WuJiang() {
		vecEquip = new LinkedList<Item>();
		vecEquip.add(null);// ARMOR 位置
		vecEquip.add(null);// WEAPON位置
		vecEquip.add(null);// HORSE 位置
		vecEquip.add(null);// HELMET位置
	}

	/* Setter and Getter */
	public LinkedList<Item> getVecEquip() {
		return this.vecEquip;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getSex() {
		return this.sex;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDes() {
		return this.des;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public int getCurExp() {
		return curExp;
	}

	public void setCurExp(int curExp) {
		this.curExp = curExp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getOwn() {
		return own;
	}

	public void setOwn(int own) {
		this.own = own;
	}

	public int getCurPhysical() {
		return curPhysical;
	}

	public void setCurPhysical(int curPhysical) {
		this.curPhysical = curPhysical;
	}

	public int getMaxPhysical() {
		return maxPhysical;
	}

	public void setMaxPhysical(int maxPhysical) {
		this.maxPhysical = maxPhysical;
	}

	public void setWarrior_gongbing(int amount) {
		this.warrior_gongbing = amount;
	}

	public int getWarrior_gongbing() {
		return this.warrior_gongbing;
	}

	public void setWarrior_bubing(int amount) {
		this.warrior_bubing = amount;
	}

	public int getWarrior_bubing() {
		return this.warrior_bubing;
	}

	public void setWarrior_qibing(int amount) {
		this.warrior_qibing = amount;
	}

	public int getWarrior_qibing() {
		return this.warrior_qibing;
	}

	/* 返回总兵力 */
	public int getWarrior_sum() {
		return (this.getWarrior_bubing() + this.getWarrior_gongbing() + this.getWarrior_qibing());
	}

}
