package com.data;

import java.util.LinkedList;

import com.tool.Building;
import com.tool.Const;

/*玩家数据*/
public class Player {

	public static Player instance;

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}

		return instance;
	}

	public static final int EQUIP_ID_YIFU = 0;
	public static final int EQUIP_ID_WUQI = 1;
	public static final int EQUIP_ID_LV = 2;
	public static final int EQUIP_ID_TOUKUI = 3;

	private int id;// 玩家ID
	private int nation;// 国籍
	private String nickName;// 玩家昵称
	private int money;// 游戏货币
	private int gold;// 元宝
	private int yinyuan;//银元
	private int lev;// 等级
	
	private int curExp;// 现有经验
	private int maxExp;// 最大经验
	
	private WuJiang wj_renMing;//任命武将

	private LinkedList<WuJiang> wuj = new LinkedList<WuJiang>();
	private LinkedList<Item> item = new LinkedList<Item>();
	private LinkedList<Building> build = new LinkedList<Building>();

	public Player() {
		this.addWuJiang();
		this.addItem();
		this.addBuilding();
	}
	
	//设置任命的武将
	public void setWj_RenMing(WuJiang wj)
	{
		this.wj_renMing = wj;
	}
	public WuJiang getWj_RenMing()
	{
		return this.wj_renMing;
	}

	public LinkedList<WuJiang> getWuJiang() {
		return this.wuj;
	}

	public LinkedList<Item> getItem() {
		return this.item;
	}
	
	public LinkedList<Building> getBuilding()
	{
		return this.build;
	}

	public void addBuilding()
	{
		/*大厅*/
		Building tempBuild = new Building();
				 tempBuild.setName("大厅");
				 tempBuild.setId_pic(0);
				 tempBuild.setHorId_map(1);
				 tempBuild.setVerId_map(4);
				 tempBuild.setType(Const.Building_Dating);
		this.build.add(tempBuild);
		/*兵营*/
		Building tempBuild2 = new Building();
				 tempBuild2.setName("兵营");
				 tempBuild2.setId_pic(1);
				 tempBuild2.setHorId_map(7);
				 tempBuild2.setVerId_map(8);
				 tempBuild2.setType(Const.Building_Bingying);
		this.build.add(tempBuild2);
		/*铁匠铺*/
		Building tempBuild3 = new Building();
				 tempBuild3.setName("铁匠铺");
				 tempBuild3.setId_pic(2);
				 tempBuild3.setHorId_map(7);
				 tempBuild3.setVerId_map(4);
				 tempBuild3.setType(Const.Building_Tiejiangpu);
		this.build.add(tempBuild3);
		/*酒馆*/
		Building tempBuild4 = new Building();
				 tempBuild4.setName("酒馆");
				 tempBuild4.setId_pic(3);
				 tempBuild4.setHorId_map(4);
				 tempBuild4.setVerId_map(9);
				 tempBuild4.setType(Const.Building_Jiuguan);
		this.build.add(tempBuild4);
	}
	
	/* 添加Item */
	public void addItem() {
		/* 铁甲1 */
		Item item = new Item();
		item.setId(0);
		item.setPicId(0);
		item.setStyleId(0);
		item.setName("铁甲");
		item.setType(0);
		item.setNum(2);
		this.item.add(item);
		/* 铁矛1 */
		Item item1 = new Item();
		item1.setId(1);
		item1.setPicId(1);
		item1.setStyleId(0);
		item1.setName("铁矛");
		item1.setType(1);
		item1.setNum(3);
		this.item.add(item1);
		/* 铁驴1 */
		Item item2 = new Item();
		item2.setId(2);
		item2.setPicId(2);
		item2.setStyleId(0);
		item2.setName("铁驴");
		item2.setType(2);
		item2.setNum(1);
		this.item.add(item2);
		/* 铁盔1 */
		Item item3 = new Item();
		item3.setId(3);
		item3.setPicId(3);
		item3.setStyleId(0);
		item3.setName("铁盔");
		item3.setType(3);
		item3.setNum(1);
		this.item.add(item3);
		
		/* 铁甲2 */
		Item item4 = new Item();
		item4.setId(4);
		item4.setPicId(4);
		item4.setStyleId(1);
		item4.setName("铁甲");
		item4.setType(0);
		item4.setNum(1);
		this.item.add(item4);
		/* 铁矛2 */
		Item item5 = new Item();
		item5.setId(5);
		item5.setPicId(5);
		item5.setStyleId(1);
		item5.setName("铁矛");
		item5.setType(1);
		item5.setNum(1);
		this.item.add(item5);
		/* 铁驴2 */
		Item item6 = new Item();
		item6.setId(6);
		item6.setPicId(6);
		item6.setStyleId(1);
		item6.setName("铁驴");
		item6.setType(2);
		item6.setNum(1);
		this.item.add(item6);
		/* 铁盔1 */
		Item item7 = new Item();
		item7.setId(7);
		item7.setPicId(7);
		item7.setStyleId(1);
		item7.setName("铁盔");
		item7.setType(3);
		item7.setNum(1);
		this.item.add(item7);
	}

	/* 添加武将 */
	public void addWuJiang() {
		/* 关羽 */
		WuJiang wj = new WuJiang();
		wj.setId(0);
		wj.setName("关羽");
		wj.setLev(1);
		wj.setQuality(2);
		wj.setHeadId(0);
		wj.setAttack(98);
		wj.setDefense(91);
		wj.setHp(100);
		wj.setCurPhysical(10);
		wj.setMaxPhysical(10);
		wj.setSex(0);
		wj.setDes("蜀汉五虎上将之首，封汉寿亭侯，后人称之武圣。");
		this.wuj.add(wj);
		/* 马良 */
		WuJiang wj1 = new WuJiang();
		wj1.setId(1);
		wj1.setName("马良");
		wj1.setLev(1);
		wj1.setQuality(0);
		wj1.setHeadId(1);
		wj1.setAttack(45);
		wj1.setDefense(37);
		wj1.setHp(60);
		wj1.setCurPhysical(10);
		wj1.setMaxPhysical(10);
		wj1.setSex(0);
		wj1.setDes("蜀汉参军，跟随关羽征战多年，白眉。");
		this.wuj.add(wj1);
		/* 诸葛亮 */
		WuJiang wj2 = new WuJiang();
		wj2.setId(2);
		wj2.setName("诸葛亮");
		wj2.setLev(1);
		wj2.setQuality(2);
		wj2.setHeadId(2);
		wj2.setAttack(31);
		wj2.setDefense(29);
		wj2.setHp(54);
		wj2.setCurPhysical(10);
		wj2.setMaxPhysical(10);
		wj2.setSex(0);
		wj2.setDes("字卧龙。蜀汉首任丞相，精于计算，心思缜密，用兵如神。");
		this.wuj.add(wj2);
		/* 黄月英 */
		WuJiang wj3 = new WuJiang();
		wj3.setId(3);
		wj3.setName("黄月英");
		wj3.setLev(1);
		wj3.setQuality(1);
		wj3.setHeadId(3);
		wj3.setAttack(81);
		wj3.setDefense(69);
		wj3.setHp(77);
		wj3.setCurPhysical(10);
		wj3.setMaxPhysical(10);
		wj3.setSex(1);
		wj3.setDes("诸葛亮老婆。");
		this.wuj.add(wj3);
		/* 吕蒙 */
		WuJiang wj4 = new WuJiang();
		wj4.setId(4);
		wj4.setName("吕蒙");
		wj4.setLev(1);
		wj4.setQuality(1);
		wj4.setHeadId(3);
		wj4.setAttack(81);
		wj4.setDefense(69);
		wj4.setHp(77);
		wj4.setCurPhysical(10);
		wj4.setMaxPhysical(10);
		wj4.setSex(1);
		wj4.setDes("东吴水师副都督");
		this.wuj.add(wj4);
	}

	/* Getter and Setter */
	public void setNation(int nation) {
		this.nation = nation;
	}

	public int getNation() {
		return this.nation;
	}

	public void setCurExp(int exp) {
		this.curExp = exp;
	}

	public int getCurExp() {
		return this.curExp;
	}

	public void setMaxExp(int exp) {
		this.maxExp = exp;
	}

	public int getMaxExp() {
		return this.maxExp;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public int getLev() {
		return this.lev;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return this.money;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getGold() {
		return this.gold;
	}

}
