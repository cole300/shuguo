package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.data.Player;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.NodeManager;
import com.tool.SourcePool;
import com.tool.Tool;

public class WuJiangEquip extends View{

	Paint paint;
	
	ShuguoActivity activity;
	
	/*Element群组*/
	ElementGroup eleGroup_bag;//背包筛选出来的装备
	ElementGroup eleGroup_equip;//装备群组
	
	private float SCALE_VALUE_1 = 0.5f;
	
	NodeManager nManager;
	
	Bitmap bmp_bg;
	
	Bitmap bmp_bg_gaishu;//概述
	
	Bitmap bmp_bg_change;//换装背景
	
	Bitmap bmp_exp_bottom, bmp_exp;//经验条,底图
	Bitmap bmp_bg_lev;//等级背景
	
	Btn btnL, btnR;//左右箭头
	
	Btn btnExit;
	
	com.data.WuJiang wuj;
	
	public WuJiangEquip(com.data.WuJiang wj, Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		this.wuj = wj;
		
		this.loadSource(activity);
		Tool.getInstance().refreshEquipStyleEffects(this.nManager, wj, activity);//刷新身上的换装效果
	}
	/*初始化element群组*/
	public void initEleGroup()
	{
		/*待选物品群组*/
		this.eleGroup_bag = new ElementGroup(405, 111, 2, 3, -30, -30);
		
		for(int i = 0; i < Player.getInstance().getItem().size(); i ++)
		{
			this.eleGroup_bag.add(new com.element.EleItem(Player.getInstance().getItem().get(i), this.eleGroup_bag, i, 0.7f, 0.7f, true, this.activity));
		}
		/*装备列表群组*/
		this.eleGroup_equip = new ElementGroup(146, 330, 4, 1, -50, 0);
		for(int i = 0; i < this.wuj.getVecEquip().size(); i ++)
		{
			if(this.wuj.getVecEquip().get(i) == null)
			{
				this.eleGroup_equip.add(null);
			}else
			{
				this.eleGroup_equip.add(new com.element.EleItem(this.wuj.getVecEquip().get(i), this.eleGroup_equip, i, SCALE_VALUE_1, SCALE_VALUE_1, false, this.activity));
			}
		}
	}
	/*加载类资源*/
	public void loadSource(Activity activity)
	{
		/*背景*/
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_equip.png", activity);
		/*NodeManager*/
		this.nManager = new NodeManager(0.8f, Const.ANCHOR_NORMAL, activity);
		this.nManager.setPosition(155, 30);
		/*概述*/
		this.bmp_bg_gaishu = SourcePool.getInstance().createAssetsBitmap("element/gaishu2.png", activity);
		/*换装背景*/
		this.bmp_bg_change = SourcePool.getInstance().createAssetsBitmap("element/check_bg_skill.png", activity);
		/*初始化左右箭头*/
		this.btnL = new Btn("btn/left.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 250, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 216, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 473, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 216, null, activity);
		this.btnR.setProperties("SINGLE");
		
		this.btnExit = new Btn("element/exit.png", 608, 23, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*经验条*/
		this.bmp_exp_bottom = SourcePool.getInstance().createAssetsBitmap("element/exp_bottom_wj.png", activity);
		this.bmp_exp = SourcePool.getInstance().createAssetsBitmap("element/exp_wj.png", activity);
		/*等级背景*/
		this.bmp_bg_lev = SourcePool.getInstance().createAssetsBitmap("element/bg_lev.png", activity);
		/*加载eleGroup群组*/
		this.initEleGroup();
	}
	
	/*销毁类资源*/
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		/*NodeManager*/
		this.nManager.destroyed();
		/*概述*/
		Tool.getInstance().destroyed(this.bmp_bg_gaishu);
		/*换装背景*/
		Tool.getInstance().destroyed(this.bmp_bg_change);
		/*经验条*/
		Tool.getInstance().destroyed(this.bmp_exp_bottom);
		Tool.getInstance().destroyed(this.bmp_exp);
		/*等级背景*/
		Tool.getInstance().destroyed(this.bmp_bg_lev);
		/*销毁eleGroup群组*/
		this.eleGroup_bag.destoryed();
		this.eleGroup_equip.destoryed();
		
		this.btnL.destroyed();
		this.btnR.destroyed();
		this.btnExit.destroyed();
	}
	/*绘制类资源*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/*当前界面的起始坐标*/
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		
		/*绘制背景*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*标题title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("装备", sx + 38, sy + 46, paint);
		/*武将名*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.wuj.getName(), sx + 81, sy + 92, paint);
		/*经验条*/
		canvas.drawBitmap(this.bmp_exp_bottom, sx + 83, sy + 274, paint);
		canvas.drawBitmap(this.bmp_exp, sx + 83, sy + 274, paint);
		/*等级背景*/
		canvas.drawBitmap(this.bmp_bg_lev, sx + 63, sy + 267, paint);
		/*等级数值*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(15);
		canvas.drawText("17", sx + 75, sy + 285, paint);
		/*概述背景*/
		canvas.drawBitmap(this.bmp_bg_gaishu, sx + 37, sy + 297, paint);
		/*概述title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("概况", sx + 150, sy + 318, paint);
		/*换装背景*/
		canvas.drawBitmap(this.bmp_bg_change, sx + 274, sy + 78, paint);
		/*换装title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("点击更换装备", sx + 393, sy + 104, paint);
		/*页数*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
//		canvas.drawText("1/3", sx + 393, sy + 423, paint);
		canvas.drawText((this.eleGroup_bag.getIdxPage()+1) + "/"+(this.eleGroup_bag.getNumPage()+1), sx + 393, sy + 435, paint);
		/*eleGroup_bag*/
		this.eleGroup_bag.paint(canvas, paint);
		/*eleGroup_equip*/
		this.eleGroup_equip.paint(canvas, paint);
		/*左右箭头*/
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		/*NodeNamager*/
		this.nManager.paint(canvas, paint);
		/*Exit button*/
		this.btnExit.paint(canvas, paint);
		
		com.tool.WarnningManager.getInstance().paint(canvas, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			this.btnExit.checkPressed(x, y, true);
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			
			for(int i = 0; i < this.eleGroup_bag.getGroup().size(); i ++)
			{
				if(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.checkPressed(x, y, true)){break;}
			}
			for(int i = 0; i < this.eleGroup_equip.getGroup().size(); i ++)
			{
				if(this.eleGroup_equip.getGroup().get(i) == null)
					continue;
				
				if(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.checkPressed(x, y, true)){break;}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				//销毁当前view
				this.releaseSource();
				activity.group.removeView(this);
				//刷新上一级界面WuJiangCheck的装备效果
				int idxView = activity.group.getChildCount() - 1;
				com.tool.NodeManager nManager = ((com.view.WuJiangCheck)(activity.group.getChildAt(idxView))).getNodeManager();
				com.data.WuJiang wuj = ((com.view.WuJiangCheck)(activity.group.getChildAt(idxView))).getWuJiang();
				Tool.getInstance().refreshEquipStyleEffects(nManager, wuj, activity);
				//刷新上一级界面WuJiangCheck的装备图标栏
				((com.view.WuJiangCheck)activity.group.getChildAt(idxView)).update_equipBtns();
				
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup_bag.previousPage();
			}else if(this.btnR.collidesWith(x, y))
			{
				this.eleGroup_bag.nextPage();
			}
			
			this.btnExit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			
			int idxStart = this.eleGroup_bag.getIdxStart();
			int idxEnd = this.eleGroup_bag.getIdxEnd();
			for(int i = idxStart; i < idxEnd; i ++)
			{
				((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.setScaling(false);
				
				if(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.checkPressed(x, y, false))
				{
					/*换骨骼形象*/
					this.nManager.changeGuGe(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getType(), ((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getStyleId(), activity);
					/*如果装备列表里该位置已有装备，则放进背包*/
					int type_equip = ((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getType();
					if(this.eleGroup_equip.getGroup().get(type_equip)!=null)
					{
						this.equipOff(((com.element.EleItem)this.eleGroup_equip.getGroup().get(type_equip)).getItem(), this.wuj, type_equip);
					}
					/*放进装备列表*/
					this.equipOn(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem(), this.wuj, i);
					break;
				}
			}
			for(int i = 0; i < this.eleGroup_equip.getGroup().size(); i ++)
			{
				/*物品列表删空可以remove，但装备列表必须固定4个位置，所以要判断是否为空*/
				if(this.eleGroup_equip.getGroup().get(i) == null)
					continue;
				
				((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.setScaling(false);
				
				if(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.checkPressed(x, y, false))
				{
					/*恢复原始骨骼形象*/
					this.nManager.changeGuGe(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).getItem().getType(), -1, activity);
					/*卸下装备->物品列表*/
					this.equipOff(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).getItem(), this.wuj, i);
					break;
				}
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
	/*装备放入装备列表*/
	public void equipOn(com.data.Item item, com.data.WuJiang wuj, int idFromItemGroup)
	{
		com.tool.WarnningManager.getInstance().add(new com.tool.Warnning(wuj.getName()+"装备上"+item.getName(), 0x00ff00, 35, true, 2, false));
		/*若俩物品id相同，则不进行任何操作返回*/
		if(wuj.getVecEquip().get(item.getType()) != null)
		{
			if(item.getId() == wuj.getVecEquip().get(item.getType()).getId())
			{
				return;
			}
		}
		
		/*添加物品到装备列表*/
		//添加显示层内容
		this.eleGroup_equip.getGroup().set(item.getType(), new com.element.EleItem(item, this.eleGroup_bag, item.getType(), SCALE_VALUE_1, SCALE_VALUE_1, false, this.activity));
		//添加数据层内容
		wuj.getVecEquip().set(item.getType(), item);
		/*从物品列表里去掉该物品*/
		if(Player.getInstance().getItem().get(idFromItemGroup).getNum() > 1)
		{
			Player.getInstance().getItem().get(idFromItemGroup).setNum(Player.getInstance().getItem().get(idFromItemGroup).getNum() - 1);
		}else
		{
			//去掉显示层内容
			this.eleGroup_bag.getGroup().get(idFromItemGroup).destroyed();
			this.eleGroup_bag.getGroup().remove(idFromItemGroup);
			//去掉数据层内容
			Player.getInstance().getItem().remove(item);
		}
	}
	/*卸装备放入背包*/
	public void equipOff(com.data.Item item, com.data.WuJiang wuj, int idFromEquipGroup)
	{
		com.tool.WarnningManager.getInstance().add(new com.tool.Warnning(wuj.getName()+"卸下了"+item.getName(), 0xff0000, 35, true, 2, false));
		/*添加卸下物品到物品列表*/
		boolean isFound = false;
		//如果物品列表中有相同物品，则数量+1
		for(int i = 0; i < Player.getInstance().getItem().size(); i ++)
		{
			if(Player.getInstance().getItem().get(i).getId() == item.getId())
			{
				Player.getInstance().getItem().get(i).setNum(Player.getInstance().getItem().get(i).getNum() + 1);
				isFound = true;
				break;
			}
		}
		//如果物品列表中没有相同物品，则添加该物品到物品列表
		if(!isFound)
		{
			this.eleGroup_bag.getGroup().add(new com.element.EleItem(item, this.eleGroup_bag, item.getType(), 0.7f, 0.7f, false, this.activity));
			Player.getInstance().getItem().add(item);
		}
		/*清空装备列表该位置*/
		this.eleGroup_equip.getGroup().get(idFromEquipGroup).destroyed();
		this.eleGroup_equip.getGroup().set(idFromEquipGroup, null);
		wuj.getVecEquip().set(item.getType(), null);
	}
	
}
