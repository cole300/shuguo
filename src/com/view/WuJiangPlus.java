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

public class WuJiangPlus extends View{

	//wujiang
	Paint paint;
	
	ShuguoActivity activity;
	
	Bitmap bmp_bg;
	
	Btn btnExit;
	Btn btnConfirm;//确定
	
	public WuJiangPlus(Context context, ShuguoActivity activity)
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
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_1.png", activity);
		/*退出按钮*/
		this.btnExit = new Btn("element/exit2.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 236, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 10, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*确定按钮*/
		this.btnConfirm = new Btn("element/btn_4.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 44, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 145, null, activity);
		this.btnConfirm.setProperties("SINGLE");
		this.btnConfirm.setNameStyle("确定", Color.BLACK, 20, true);
	}
	/*释放资源*/
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		
		this.btnExit.destroyed();
		this.btnConfirm.destroyed();
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
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("行动力", sx + 30, sy + 44, paint);
		/*标题title*/
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(18);
		canvas.drawText("是否恢复100点行动力", sx + 150, sy + 98, paint);
		
		this.btnExit.paint(canvas, paint);
		this.btnConfirm.paint(canvas, paint);
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
			this.btnConfirm.checkPressed(x, y, true);
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				this.releaseSource();
				activity.group.removeView(this);
			}
			
			this.btnExit.setScaling(false);
			this.btnConfirm.setScaling(false);
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
