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
import com.tool.BtnGroup;
import com.tool.Const;
import com.tool.SourcePool;
import com.tool.Tool;

public class PaiHang extends View{

	Paint paint;
	ShuguoActivity activity;
	
	Btn btn_exit;
	Btn btnL, btnR;//左右箭头
	
	BtnGroup btnGroup;//button群组
	Bitmap bmp_btnGroup;
	
	Bitmap bmp_bg;
	Bitmap bmp_bg_lev;//等级背景
	Bitmap bmp_bg_content;//内容背景
	Bitmap bmp_bottom_lev;
	Bitmap bmp_bottom_content;
	
	public PaiHang(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		this.loadSource(activity);
		
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	/*初始化button群组*/
	public void initBtnGroup()
	{
		this.btnGroup = new BtnGroup(0, 82, 85);
		this.btnGroup.add("element/btn1_wj.png", "总排行", activity);
		this.btnGroup.add("element/btn1_wj.png", "奖励榜", activity);
		this.btnGroup.add("element/btn1_wj.png", "荣誉榜", activity);
		this.btnGroup.add("element/btn1_wj.png", "积分榜", activity);
		this.btnGroup.getGroup().get(0).setBmp(this.btnGroup.bmp_btn);
	}
	/*加载资源*/
	public void loadSource(Activity activity)
	{
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_wj.png", activity);
		
		this.btn_exit = new Btn("element/exit.png", 730, 20, null, activity);
		this.btn_exit.setProperties("SINGLE");
		/*初始化左右箭头*/
		this.btnL = new Btn("btn/left.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 216, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 248, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 675, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 248, null, activity);
		this.btnR.setProperties("SINGLE");
		
		/*加载button群组*/
		this.initBtnGroup();
		/*加载button群组背景图*/
		this.bmp_btnGroup = SourcePool.getInstance().createAssetsBitmap("element/item_13.png", activity);
		/*加载等级背景*/
		this.bmp_bg_lev = SourcePool.getInstance().createAssetsBitmap("element/bg_lev_ph.png", activity);
		/*加载内容背景*/
		this.bmp_bg_content = SourcePool.getInstance().createAssetsBitmap("element/bg_content_ph.png", activity);
		/*加载等级底图*/
		this.bmp_bottom_lev = SourcePool.getInstance().createAssetsBitmap("element/lev_bottom_ph.png", activity);
		/*加载内容底图*/
		this.bmp_bottom_content = SourcePool.getInstance().createAssetsBitmap("element/content_bottom_ph.png", activity);
	}
	/*销毁资源*/
	public void releaseResource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		
		this.btn_exit.destroyed();
		/*左右箭头*/
		this.btnL.destroyed();
		this.btnR.destroyed();
		/*销毁群组*/
		this.btnGroup.destroyed();
		/*销毁button群组背景图*/
		Tool.getInstance().destroyed(this.bmp_btnGroup);
		/*等级背景*/
		Tool.getInstance().destroyed(this.bmp_bg_lev);
		/*内容背景*/
		Tool.getInstance().destroyed(this.bmp_bg_content);
		/*等级底图*/
		Tool.getInstance().destroyed(this.bmp_bottom_lev);
		/*内容底图*/
		Tool.getInstance().destroyed(this.bmp_bottom_content);
	}
	/*绘制资源*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		/*relative position*/
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		/*背景*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*标题title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("排行榜", sx + 38, sy + 46, paint);
		/*绘制button群组背景图*/
		canvas.drawBitmap(this.bmp_btnGroup, sx + 65, sy + 72, paint);
		/*绘制button群组*/
		this.btnGroup.paint(canvas, paint);
		/*等级背景*/
		canvas.drawBitmap(this.bmp_bg_lev, sx + 60, sy + 118, paint);
		/*等级底图*/
		canvas.drawBitmap(this.bmp_bottom_lev, sx + 66, sy + 125, paint);
		/*内容背景*/
		canvas.drawBitmap(this.bmp_bg_content, sx + 248, sy + 118, paint);
		/*内容底图*/
		canvas.drawBitmap(this.bmp_bottom_content, sx + 288, sy + 130, paint);
		/*绘制左右按钮*/
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		
		this.btn_exit.paint(canvas, paint);
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
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			this.btnGroup.checkPressed(x, y);
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.getRect().contains(x, y))
			{
				activity.group.removeView(this);
			}
			
			this.btn_exit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
