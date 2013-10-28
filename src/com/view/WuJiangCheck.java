package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Const;
import com.tool.NodeManager;
import com.tool.SourcePool;
import com.tool.Tool;

public class WuJiangCheck extends View{

	Paint paint;
	
	ShuguoActivity activity;
	
	Bitmap bmp_bg;
	
	Bitmap bmp_bg_skill;//左边技能底框
	Bitmap bmp_bg_equip;//右上装备底框
	Bitmap bmp_bg_celue;//右下策略底框
	
	Bitmap bmp_icon_skill;//技能图标
	Bitmap bmp_icon_equip;//装备图标
	
	Bitmap bmp_exp_bottom;
	Bitmap bmp_exp;
	Bitmap bmp_bg_lev;//等级底图
	
	Bitmap bmp_skill;//技能图标
	
	Bitmap bmp_bg_gaishu;//概述背景
	
	Btn btnExit;
	
	Btn btn_learn;//学习按钮
	Btn btn_reset;//重置按钮
	Btn btn_baoguo;//包裹按钮
	Btn btn_onebest;//一键最强按钮
	
	Btn[] btns_equip;//装备图标按钮
	
	private NodeManager nManager;
	
	Btn btn_celue_1, btn_celue_2, btn_celue_3, btn_celue_4;
	
	com.data.WuJiang wuj;
	
	public WuJiangCheck(com.data.WuJiang wj, Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		this.wuj = wj;
		
		this.loadSource(activity);
		/*刷新身上装备效果*/
		Tool.getInstance().refreshEquipStyleEffects(this.nManager, wj, activity);
		/*刷新装备图标栏*/
		this.update_equipBtns();
	}
	
	public NodeManager getNodeManager()
	{
		return this.nManager;
	}
	public com.data.WuJiang getWuJiang()
	{
		return this.wuj;
	}
	
	public void loadSource(Activity activity)
	{
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_wj.png", activity);
		/*NodeManager*/
		this.nManager = new NodeManager(0.8f, Const.ANCHOR_NORMAL, activity);
		this.nManager.setPosition(300, 80);
		/*技能图标*/
		this.bmp_skill = SourcePool.getInstance().createAssetsBitmap("skill/1.jpg", activity);
		/*概述*/
		this.bmp_bg_gaishu = SourcePool.getInstance().createAssetsBitmap("element/gaishu.png", activity);
		/*经验条，经验条底图*/
		this.bmp_exp_bottom = SourcePool.getInstance().createAssetsBitmap("element/exp_bottom_wj.png", activity);
		this.bmp_exp = SourcePool.getInstance().createAssetsBitmap("element/exp_wj.png", activity);
		/*等级底图*/
		this.bmp_bg_lev = SourcePool.getInstance().createAssetsBitmap("element/bg_lev.png", activity);
		/*技能/装备/策略底框*/
		this.bmp_bg_skill = SourcePool.getInstance().createAssetsBitmap("element/check_bg_skill.png", activity);
		this.bmp_bg_equip = SourcePool.getInstance().createAssetsBitmap("element/check_bg_equip.png", activity);
		this.bmp_bg_celue = SourcePool.getInstance().createAssetsBitmap("element/check_bg_celue.png", activity);
		/*技能，装备图标*/
		this.bmp_icon_equip = SourcePool.getInstance().createAssetsBitmap("element/icon_skill_wj.png", activity);
		this.bmp_icon_skill = SourcePool.getInstance().createAssetsBitmap("element/icon_skill_wj.png", activity);
		/*退出按钮*/
		this.btnExit = new Btn("element/exit.png", 730, 20, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*学习按钮*/
		this.btn_learn = new Btn("element/btn2_wj.png", 67, 365, null, activity);
		this.btn_learn.setProperties("SINGLE");
		this.btn_learn.setNameStyle("学习", Color.BLACK, 20, true);
		/*重置按钮*/
		this.btn_reset = new Btn("element/btn2_wj.png", 67, 407, null, activity);
		this.btn_reset.setProperties("SINGLE");
		this.btn_reset.setNameStyle("重置", Color.BLACK, 20, true);
		/*包裹按钮*/
		this.btn_baoguo = new Btn("element/btn3_wj.png", 525, 290, null, activity);
		this.btn_baoguo.setProperties("SINGLE");
		this.btn_baoguo.setNameStyle("包裹", Color.BLACK, 20, true);
		/*一键最强按钮*/
		this.btn_onebest = new Btn("element/btn3_wj.png", 646, 290, null, activity);
		this.btn_onebest.setProperties("SINGLE");
		this.btn_onebest.setNameStyle("一键最强", Color.BLACK, 20, true);
		/*策略1,2,3,4*/
		this.btn_celue_1 = new Btn("element/btn1_wj.png", 535, 371, null, activity);
		this.btn_celue_1.setProperties("SINGLE");
		this.btn_celue_1.setNameStyle("策略一", Color.BLACK, 20, true);
		
		this.btn_celue_2 = new Btn("element/btn1_wj.png", 645, 371, null, activity);
		this.btn_celue_2.setProperties("SINGLE");
		this.btn_celue_2.setNameStyle("策略二", Color.BLACK, 20, true);
		
		this.btn_celue_3 = new Btn("element/btn1_wj.png", 535, 408, null, activity);
		this.btn_celue_3.setProperties("SINGLE");
		this.btn_celue_3.setNameStyle("策略三", Color.BLACK, 20, true);
		
		this.btn_celue_4 = new Btn("element/btn1_wj.png", 645, 408, null, activity);
		this.btn_celue_4.setProperties("SINGLE");
		this.btn_celue_4.setNameStyle("策略四", Color.BLACK, 20, true);
		
		/*装备图标按钮*/
		this.btns_equip = new Btn[4];//
		this.btns_equip[0] = new Btn("equip/-1.png", 525, 110, null, activity);
		this.btns_equip[1] = new Btn("equip/-1.png", 618, 110, null, activity);
		this.btns_equip[2] = new Btn("equip/-1.png", 525, 190, null, activity);
		this.btns_equip[3] = new Btn("equip/-1.png", 618, 190, null, activity);
	}
	
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		/*技能图标*/
		Tool.getInstance().destroyed(this.bmp_skill);
		/*NodeManager*/
		this.nManager.destroyed();
		/*概述*/
		Tool.getInstance().destroyed(this.bmp_bg_gaishu);
		/*经验条，底图*/
		Tool.getInstance().destroyed(this.bmp_exp_bottom);
		Tool.getInstance().destroyed(this.bmp_exp);
		/*等级底图*/
		Tool.getInstance().destroyed(this.bmp_bg_lev);
		/*技能/装备/策略底框*/
		Tool.getInstance().destroyed(this.bmp_bg_skill);
		Tool.getInstance().destroyed(this.bmp_bg_equip);
		Tool.getInstance().destroyed(this.bmp_bg_celue);
		/*技能，装备图标*/
		Tool.getInstance().destroyed(this.bmp_icon_equip);
		Tool.getInstance().destroyed(this.bmp_icon_skill);
		
		this.btn_learn.destroyed();
		this.btn_reset.destroyed();
		this.btn_baoguo.destroyed();
		this.btn_onebest.destroyed();
		this.btn_celue_1.destroyed();
		this.btn_celue_2.destroyed();
		this.btn_celue_3.destroyed();
		this.btn_celue_4.destroyed();
		/*注销装备图标按钮*/
		for(int i = 0; i < btns_equip.length; i ++)
		{
			this.btns_equip[i].destroyed();
		}
		this.btns_equip = null;
		
		/*退出按钮*/
		this.btnExit.destroyed();
	}
	
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(this.bmp_bg, Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2, Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2, paint);
		
		/*绘制标题title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("查看", 40, 52, paint);
		/*绘制技能/装备/策略底框*/
		canvas.drawBitmap(this.bmp_bg_skill, 41, 100, paint);
		canvas.drawBitmap(this.bmp_bg_equip, 758-236, 100, paint);
		canvas.drawBitmap(this.bmp_bg_celue, 758-236, 338, paint);
		/*绘制技能title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		canvas.drawText("技能", 60, 128, paint);
		/*绘制技能图标*/
		for(int i = 0; i < 3; i ++)
		{
			canvas.drawBitmap(this.bmp_icon_skill, 80, 140 + i * 75, paint);
			canvas.drawBitmap(this.bmp_icon_skill, 160, 140 + i * 75, paint);
			
			canvas.drawBitmap(this.bmp_skill, 80+2, 140+4 + i * 75, paint);
			canvas.drawBitmap(this.bmp_skill, 160+2, 140+4 + i * 75, paint);
		}
		/*绘制装备title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		canvas.drawText("装备", 540, 128, paint);
		/*绘制装备图标*/
		for(int i = 0; i < this.btns_equip.length; i ++)
		{
			this.btns_equip[i].paintScales(0.6f, 0.6f, canvas, paint);
		}
		this.btn_learn.paint(canvas, paint);
		this.btn_reset.paint(canvas, paint);
		this.btn_baoguo.paint(canvas, paint);
		this.btn_onebest.paint(canvas, paint);
		
		/*绘制技能title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		canvas.drawText("策略", 540, 363, paint);
		/*策略1,2,3,4*/
		this.btn_celue_1.paint(canvas, paint);
		this.btn_celue_2.paint(canvas, paint);
		this.btn_celue_3.paint(canvas, paint);
		this.btn_celue_4.paint(canvas, paint);
		
		/*武将名*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.wuj.getName(), 320, 120, paint);
		/*概述背景*/
		canvas.drawBitmap(this.bmp_bg_gaishu, 286, 320, paint);
		/*概述title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(21);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("概况", 404, 342, paint);
		/*经验条，底图*/
		canvas.drawBitmap(this.bmp_exp_bottom, 332, 301, paint);
		canvas.drawBitmap(this.bmp_exp, 332, 301, paint);
		/*等级底图*/
		canvas.drawBitmap(this.bmp_bg_lev, 310, 294, paint);
		/*等级数值*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(15);
		canvas.drawText("17", 322, 312, paint);
		/*NodeManager*/
		this.nManager.paint(canvas, paint);
		/*Exit button*/
		this.btnExit.paint(canvas, paint);
	}
	
	/*刷新装备图标按钮*/
	public void update_equipBtns()
	{
		for(int i = 0; i < this.wuj.getVecEquip().size(); i ++)
		{
			//销毁旧资源
			this.btns_equip[i].destroyedBmp();
			//加载新资源
			if(this.wuj.getVecEquip().get(i) == null)
			{
				Bitmap bmp = SourcePool.getInstance().createAssetsBitmap("equip/-1.png", activity);
				this.btns_equip[i].setBmp(bmp);
			}else
			{
				Bitmap bmp = SourcePool.getInstance().createAssetsBitmap("equip/"+this.wuj.getVecEquip().get(i).getPicId()+".png", activity);
				this.btns_equip[i].setBmp(bmp);
			}	
		}
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
			this.btn_learn.checkPressed(x, y, true);
			this.btn_reset.checkPressed(x, y, true);
			this.btn_baoguo.checkPressed(x, y, true);
			this.btn_onebest.checkPressed(x, y, true);
			this.btn_celue_1.checkPressed(x, y, true);
			this.btn_celue_2.checkPressed(x, y, true);
			this.btn_celue_3.checkPressed(x, y, true);
			this.btn_celue_4.checkPressed(x, y, true);
			/*装备图标按钮*/
			for(int i = 0; i < this.btns_equip.length; i ++)
			{
				this.btns_equip[i].checkPressed(x, y, true);
			}
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				this.releaseSource();
				activity.group.removeView(this);
				break;
			}else if(this.btn_baoguo.collidesWith(x, y))
			{
				WuJiangEquip wjEquip = new WuJiangEquip(this.wuj, activity.getApplicationContext(), activity);
				activity.group.addView(wjEquip);
				break;
			}
			
			/*装备按钮响应<按任意按钮进入WuJiangEquip界面>*/
			for(int i = 0; i < this.btns_equip.length; i ++)
			{
				if(this.btns_equip[i].collidesWith(x, y))
				{
					WuJiangEquip wjEquip = new WuJiangEquip(this.wuj, activity.getApplicationContext(), activity);
					activity.group.addView(wjEquip);
					break;
				}
			}
			
			this.btnExit.setScaling(false);
			this.btn_learn.setScaling(false);
			this.btn_reset.setScaling(false);
			this.btn_baoguo.setScaling(false);
			this.btn_onebest.setScaling(false);
			this.btn_celue_1.setScaling(false);
			this.btn_celue_2.setScaling(false);
			this.btn_celue_3.setScaling(false);
			this.btn_celue_4.setScaling(false);
			/*装备图标按钮*/
			for(int i = 0; i < this.btns_equip.length; i ++)
			{
				this.btns_equip[i].setScaling(false);
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
}
