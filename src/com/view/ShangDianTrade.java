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
import com.tool.SourcePool;
import com.tool.Tool;

public class ShangDianTrade extends View{

	ShuguoActivity activity;
	
	Paint paint;
	
	Bitmap bmp_bg;
	Bitmap bmp_bg_count;//数量背景
	
	Btn btnExit;
	Btn btn_plus, btn_lessen;//增加&减少按钮
	Btn btn_confirm;//确认按钮
	
	private int countTrade = 1;//交易数额
	
	public ShangDianTrade(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		this.loadSource(activity);
	}
	
	/*加载资源*/
	public void loadSource(Activity activity)
	{
		/*加载背景*/
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_2.png", activity);
		/*数量背景*/
		this.bmp_bg_count = SourcePool.getInstance().createAssetsBitmap("element/btn_4.png", activity);
		/*退出按钮*/
		this.btnExit = new Btn("element/exit2.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 370, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 10, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*增加&减少按钮*/
		this.btn_plus = new Btn("btn/plus.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 330, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 200, null, activity);
		this.btn_plus.setProperties("SINGLE");
		this.btn_lessen = new Btn("btn/lessen.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 50, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 200, null, activity);
		this.btn_lessen.setProperties("SINGLE");
		/*确认按钮*/
		this.btn_confirm = new Btn("element/btn_4.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 110, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 261, null, activity);
		this.btn_confirm.setProperties("SINGLE");
		this.btn_confirm.setNameStyle("确定", Color.BLACK, 22, true);
	}
	/*销毁资源*/
	public void releaseSource()
	{
		/*销毁背景*/
		Tool.getInstance().destroyed(this.bmp_bg);
		/*注销数量背景*/
		Tool.getInstance().destroyed(this.bmp_bg_count);
		/*销毁按钮*/
		this.btnExit.destroyed();
		this.btn_plus.destroyed();
		this.btn_lessen.destroyed();
		this.btn_confirm.destroyed();
	}
	/*绘制资源*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		/*绘制背景*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*标题title*/
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("购买", sx + 30, sy + 44, paint);
		/*道具说明*/
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setColor(Color.DKGRAY);
		paint.setFakeBoldText(true);
		paint.setTextSize(21);
		canvas.drawText("道具的说明信息", sx + 51, sy + 106, paint);
		/*道具数量*/
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setColor(Color.DKGRAY);
		paint.setFakeBoldText(true);
		paint.setTextSize(21);
		canvas.drawText("道具数量", sx + 51, sy + 187, paint);
		/*数量背景*/
		canvas.drawBitmap(this.bmp_bg_count, sx + 110, sy + 200, paint);
		/*绘制交易数量*/
		/*道具数量*/
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.BLUE);
		paint.setFakeBoldText(true);
		paint.setTextSize(26);
		canvas.drawText(String.valueOf(this.countTrade), sx + 216, sy + 234, paint);
		/*绘制按钮*/
		this.btnExit.paint(canvas, paint);
		this.btn_plus.paint(canvas, paint);
		this.btn_lessen.paint(canvas, paint);
		this.btn_confirm.paint(canvas, paint);
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
			this.btn_plus.checkPressed(x, y, true);
			this.btn_lessen.checkPressed(x, y, true);
			this.btn_confirm.checkPressed(x, y, true);
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				this.releaseSource();
				activity.group.removeView(this);
			}
			else if(this.btn_plus.collidesWith(x, y))
			{
				this.countTrade ++ ;
			}else if(this.btn_lessen.collidesWith(x, y))
			{
				this.countTrade = this.countTrade > 1 ? --this.countTrade : this.countTrade;
			}
			
			this.btnExit.setScaling(false);
			this.btn_plus.setScaling(false);
			this.btn_lessen.setScaling(false);
			this.btn_confirm.setScaling(false);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
}
