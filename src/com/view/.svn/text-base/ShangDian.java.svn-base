package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.element.EleItemShangDian;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.BtnGroup;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;
import com.tool.WarnningManager;

public class ShangDian extends View{

	Paint paint;
	ShuguoActivity activity;
	
	Btn btn_exit;//退出按钮
	Btn btnL, btnR;//左右按钮
	
	ElementGroup eleGroup;//view群
	BtnGroup btnGroup;//button群组
	Bitmap bmp_btnGroup;//button群组背景
	
	Bitmap bmp_bg;//背景图
	
	public ShangDian(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		this.loadSource(activity);
		
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	/*初始化element群组*/
	public void initEleGroup()
	{
		this.eleGroup = new ElementGroup(85, 125, 4, 2, 15, 5);
		
		for(int i = 0; i < 29; i ++)
		{
			this.eleGroup.add(new EleItemShangDian(this.eleGroup, i, this.activity));
		}
	}
	/*初始化button群组*/
	public void initBtnGroup()
	{
		this.btnGroup = new BtnGroup(0, 82, 85);
		this.btnGroup.add("element/btn1_wj.png", "宝物", activity);
		this.btnGroup.add("element/btn1_wj.png", "装备", activity);
		this.btnGroup.add("element/btn1_wj.png", "功勋", activity);
		this.btnGroup.add("element/btn1_wj.png", "其他", activity);
		this.btnGroup.getGroup().get(0).setBmp(this.btnGroup.bmp_btn);
	}
	/*加载资源*/
	public void loadSource(Activity activity)
	{
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_wj.png", activity);
		/*退出按钮*/
		this.btn_exit = new Btn("element/exit.png", 730, 20, null, activity);
		this.btn_exit.setProperties("SINGLE");
		/*button群组*/
		this.initBtnGroup();
		/*eleGroup群组*/
		this.initEleGroup();
		/*加载button群组背景图*/
		this.bmp_btnGroup = SourcePool.getInstance().createAssetsBitmap("element/item_13.png", activity);
		/*初始化左右箭头*/
		this.btnL = new Btn("btn/left.png",  -5, 221, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", 738, 221, null, activity);
		this.btnR.setProperties("SINGLE");
	}
	/*销毁资源*/
	public void releaseResource()
	{
		/*销毁背景图*/
		Tool.getInstance().destroyed(this.bmp_bg);
		/*销毁退出按钮*/
		this.btn_exit.destroyed();
		/*销毁button群组*/
		this.btnGroup.destroyed();
		/*销毁button群组背景*/
		Tool.getInstance().destroyed(this.bmp_btnGroup);
		/*销毁eleGroup群组*/
		this.eleGroup.destoryed();
		/*销毁左右按钮*/
		this.btnL.destroyed();
		this.btnR.destroyed();
	}
	/*绘制资源*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		/*背景*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*标题title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("商店", sx + 58, sy + 46, paint);
		/*button群组背景*/
		canvas.drawBitmap(this.bmp_btnGroup, sx + 65, sy + 72, paint);
		/*button群组*/
		this.btnGroup.paint(canvas, paint);
		/*eleGroup群组*/
		this.eleGroup.paint(canvas, paint);
		/*退出按钮*/
		this.btn_exit.paint(canvas, paint);
		/*左右按钮*/
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		/*页数*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText((this.eleGroup.getIdxPage()+1) + "/"+(this.eleGroup.getNumPage()+1), sx + 388, sy + 455, paint);
		/*绘制警告群组*/
		WarnningManager.getInstance().paint(canvas, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			this.btn_exit.checkPressed(x, y, true);
			this.btnGroup.checkPressed(x, y);
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				EleItemShangDian itemSD = (EleItemShangDian)this.eleGroup.getGroup().get(i);
				
				if(itemSD.btn_bg.checkPressed(x, y, true))
				{
					
				}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.collidesWith(x, y))
			{
				this.releaseResource();
				activity.group.removeView(this);
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup.previousPage();
			}else if(this.btnR.collidesWith(x, y))
			{
				this.eleGroup.nextPage();
			}
			
			for(int i = 0 ; i < this.eleGroup.getGroup().size(); i ++)
			{
				EleItemShangDian itemDS = (EleItemShangDian)this.eleGroup.getGroup().get(i);
				
				if(itemDS.btn_bg.collidesWith(x, y))
				{
					/*打开商店交易界面*/
					ShangDianTrade sdt = new ShangDianTrade(activity.getApplicationContext(), activity);
					activity.group.addView(sdt);
					break;
				}
			}
			
			this.btn_exit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				EleItemShangDian itemDS = (EleItemShangDian)this.eleGroup.getGroup().get(i);
				
				itemDS.btn_bg.setScaling(false);
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
