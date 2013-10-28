package com.element;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Const;
import com.tool.Data;
import com.tool.Element;
import com.tool.NodeManager;
import com.tool.Tool;
import com.view.Battle;

/*战斗元素*/
public class EleBattle implements Element{

	private com.data.WuJiang wuj;
	
	private NodeManager ani;//动画
	
	private byte stage;
	
	public EleBattle(com.data.WuJiang wuj, int init_xPos, int init_yPos, int anchor, float scaleLev, ShuguoActivity activity)
	{
		this.wuj = wuj;
		/*加载元素资源*/
		this.loadSource(activity);
		//初始化为Move状态
		this.stage = Const.EleBattle_Move;
		//加载动画animation
		this.ani = new NodeManager(scaleLev, anchor, activity);
		this.ani.setPosition(init_xPos, init_yPos);
		//刷新装备
		Tool.getInstance().refreshEquipStyleEffects(ani, wuj, activity);
	}
	/*加载资源*/
	public void loadSource(ShuguoActivity activity)
	{
	
	}
	/*销毁资源*/
	public void destroyed()
	{
		/*销毁动画animation*/
		this.ani.destroyed();
	}
	/*获得动画animation*/
	public NodeManager getAni()
	{
		return this.ani;
	}
	/*获取当前阶段*/
	public byte getStage()
	{
		return this.stage;
	}
	/*监测动作变化*/
	public void checkAction()
	{
		switch(this.stage)
		{
		case Const.EleBattle_Move:
			 
				if(this.ani.getAnchor() == Const.ANCHOR_NORMAL)
				{
					if(this.ani.getXpos() < Battle.LINE_RUSH)
					{
						//监测序列
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_MOVE)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_MOVE);
						 }
						 //移动
						 this.getAni().move(Battle.SPEED_MOVE, 0);
					}else
					{
						this.stage = Const.EleBattle_Rush;
					}
				}else if(this.ani.getAnchor() == Const.ANCHOR_MIRROR)
				{
					if(this.getAni().getXpos() > Const.SCREEN_W - Battle.LINE_RUSH - 150)
					{
						 //监测序列
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_MOVE)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_MOVE);
						 }
						 //移动
						 this.getAni().move(-Battle.SPEED_MOVE, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Rush;
						 
					 }
				}
			
			 break;
		case Const.EleBattle_Rush:
			
				if(this.ani.getAnchor() == Const.ANCHOR_NORMAL)
				{
					if(this.getAni().getXpos() < Const.SCREEN_W / 2 - 150)
					{
						 //监测序列
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_RUSH)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_RUSH);
							 this.getAni().setDelay(3);
						 }
						 //移动
						 this.getAni().move(Battle.SPEED_RUSH, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Battle;
						 this.getAni().setCurActionData(Data.FRAME_DATA_STAND);
					 }
				}else if(this.ani.getAnchor() == Const.ANCHOR_MIRROR)
				{
					if(this.getAni().getXpos() > Const.SCREEN_W / 2 - 50)
					{
						 //监测序列
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_RUSH)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_RUSH);
							 this.getAni().setDelay(2);
						 }
						 //移动
						 this.getAni().move(-Battle.SPEED_RUSH, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Battle;
						 this.getAni().setCurActionData(Data.FRAME_DATA_STAND);
					 }
				}
			
			 break;
		case Const.EleBattle_Battle:
			
			 break;
		}
	}
	
	public void paint(int x, int y, Canvas canvas, Paint paint)
	{
		
	}
	/*绘制资源*/
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
		/*绘制底图*/
		canvas.save();
		this.ani.paint(canvas, paint);
		canvas.restore();
		/*武将姓名*/
		paint.setColor(Color.DKGRAY);
		paint.setFakeBoldText(true);
		paint.setTextSize(16);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.wuj.getName(), this.ani.getXpos() + 50, this.ani.getYpos() +42, paint);
	}
	/*返回武将数据*/
	public com.data.WuJiang getWuJiang()
	{
		return this.wuj;
	}
	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return 100;
	}
	public int getElementHeight() {
		// TODO Auto-generated method stub
		return 100;
	}

}
