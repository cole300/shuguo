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
import com.element.EleWuJiang;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.BtnGroup;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;
import com.tool.WarnningManager;

public class WuJiang extends View{

	/*element群组*/
	ElementGroup eleGroup;
	/*button群组*/
	BtnGroup btnGroup;
	/*button群组背景*/
	Bitmap bmp_btnGroup;
	
	Paint paint;
	
	Bitmap bmp_bg;
	
	Btn btnExit;
	
	Btn btnL, btnR;//左右箭头
	
	ShuguoActivity activity;
	
	public WuJiang(Context context, ShuguoActivity activity)
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
		this.eleGroup = new ElementGroup(85, 132, 4, 1, 10, 0);
		
		for(int i = 0; i < Player.getInstance().getWuJiang().size(); i ++)
		{
			this.eleGroup.add(new EleWuJiang(Player.getInstance().getWuJiang().get(i), this.eleGroup, i, this.activity));
		}
	}
	/*初始化button群组*/
	public void initBtnGroup()
	{
		this.btnGroup = new BtnGroup(0, 22, 90);
		this.btnGroup.add("element/btn1_wj.png", "待命将领", activity);
		this.btnGroup.add("element/btn1_wj.png", "驻防将领", activity);
		this.btnGroup.add("element/btn1_wj.png", "训练将领", activity);
		this.btnGroup.add("element/btn1_wj.png", "特殊将领", activity);
		this.btnGroup.getGroup().get(0).setBmp(this.btnGroup.bmp_btn);
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		
		canvas.drawBitmap(this.bmp_bg, Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2, Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2, paint);
		
		/*标题title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("武将", sx + 38, sy + 46, paint);
		
		/*页数*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText((this.eleGroup.getIdxPage()+1) + "/"+(this.eleGroup.getNumPage()+1), 622, 124, paint);
		
		this.btnExit.paint(canvas, paint);
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		
		canvas.drawBitmap(this.bmp_btnGroup, 16,85, paint);
		
		this.btnGroup.paint(canvas, paint);
		this.eleGroup.paint(canvas, paint);
		
		/*绘制警告群组*/
		WarnningManager.getInstance().paint(canvas, paint);
	}
	
	public void loadSource(Activity activity)
	{
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_wj.png", activity);
		/*加载退出按钮*/
		this.btnExit = new Btn("element/exit.png", 730, 20, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*初始化左右箭头*/
		this.btnL = new Btn("btn/left.png", 0, 240, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", 740, 240, null, activity);
		this.btnR.setProperties("SINGLE");
		/*加载element群组*/
		this.initEleGroup();
		/*加载button群组*/
		this.initBtnGroup();
		
		this.bmp_btnGroup = SourcePool.getInstance().createAssetsBitmap("element/item_13.png", activity);
	}
	
	public void releaseResource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		this.btnExit.destroyed();
		this.btnL.destroyed();
		this.btnR.destroyed();
		Tool.getInstance().destroyed(this.bmp_btnGroup);
		/*销毁群组元素资源*/
		this.eleGroup.destoryed();
		this.btnGroup.destroyed();
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
			this.btnGroup.checkPressed(x, y);
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((EleWuJiang)this.eleGroup.getGroup().get(i)).btn_plus.checkPressed(x, y, true)){}
				else if(((EleWuJiang)this.eleGroup.getGroup().get(i)).btn_check.checkPressed(x, y, true)){}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				this.releaseResource();
				activity.group.removeView(this);
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup.previousPage();
			}
			 else if(this.btnR.collidesWith(x, y))
			 {
				 this.eleGroup.nextPage();
			 }
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((EleWuJiang)this.eleGroup.getGroup().get(i)).btn_check.collidesWith(x, y))
				{
					WuJiangCheck wjCheck = new WuJiangCheck(((EleWuJiang)this.eleGroup.getGroup().get(i)).getWuJiang(),
															activity.getApplicationContext(),
															activity);
					activity.group.addView(wjCheck);
					break;
				}else if(((EleWuJiang)this.eleGroup.getGroup().get(i)).btn_plus.collidesWith(x, y))
				{
					WuJiangPlus wjPlus = new WuJiangPlus(activity.getApplicationContext(), activity);
					activity.group.addView(wjPlus);
					break;
				}
			}
			
			this.btnExit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			this.eleGroup.closeScaling();
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
}
