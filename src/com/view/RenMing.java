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
import com.tool.Btn;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class RenMing extends View{

	com.shu.ShuguoActivity activity;
	
	Paint paint;
	
	//背景图
	Bitmap bmp_bg;
	//element群组
	ElementGroup eleGroup;

	Btn btn_exit;//退出按钮
	Btn btnL, btnR;//左右箭头
	
	public RenMing(Context context, com.shu.ShuguoActivity activity)
	{
		super(context);
		
		this.setId(Const.ID_MAP_RenMing);
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		
		this.activity = activity;
		
		this.loadSource(activity);
	}
	/*初始化element群组*/
	public void initEleGroup()
	{
		this.eleGroup = new ElementGroup(80, 160, 5, 1, 5, 0);
		
		for(int i = 0; i < Player.getInstance().getWuJiang().size(); i ++)
		{
			this.eleGroup.add(new com.element.EleWuJiang2(Player.getInstance().getWuJiang().get(i), this.eleGroup, i, this.activity));
		}
	}
	public void loadSource(Activity activity)
	{
		//施放背景
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("ui/bg_dt.png", activity);
		//加载eleGroup群组
		this.initEleGroup();
		//加载左右箭头
		this.btnL = new Btn("btn/left.png", 0, 240, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", 740, 240, null, activity);
		this.btnR.setProperties("SINGLE");
		//加载退出按钮
		this.btn_exit = new Btn("btn/exit2.png", 730, 20, null, activity);
		this.btn_exit.setProperties("SINGLE");
	}
	
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		//销毁eleGroup群组
		this.eleGroup.destoryed();
		//销毁退出按钮
		this.btn_exit.destroyed();
		//销毁左右翻页按钮
		this.btnL.destroyed();
		this.btnR.destroyed();
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		//绘制背景
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		//设置字体
		paint.setTypeface(this.activity.typeFace);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(24);
		//标题
		canvas.drawText("太守任命", Const.SCREEN_W / 2, sy + 70, paint);
		canvas.drawRect(Const.SCREEN_W / 2 - 200, sy + 77, Const.SCREEN_W / 2 + 200, sy + 83, paint);
		//太守
		paint.setColor(Color.rgb(11, 17, 28));
		paint.setTextSize(19);
		paint.setFakeBoldText(true);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTypeface(this.activity.typeFace);
		canvas.drawText("内政当前额外加成 5%", 195, 135, paint);
		canvas.drawText("内政预期额外加成 5%", 195, 155, paint);
		canvas.drawText("治安当前额外加成 5%", 420, 135, paint);
		canvas.drawText("治安预期额外加成 5%", 420, 155, paint);
		//eleGroup群组
		this.eleGroup.paint(canvas, paint);
		//左右翻页按钮
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		//绘制退出按钮
		this.btn_exit.paint(canvas, paint);
		//绘制警告
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

			this.btn_exit.checkPressed(x, y, true);
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				//如果按下的该元素为未选中状态，则跳过
				if(!((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getIsInView())
					continue;
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_renMing.checkPressed(x, y, true))
				{
					
				}
			}
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_bg.checkPressed(x, y, false)){}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.collidesWith(x, y))
			{
				this.releaseSource();
				activity.group.removeView(this);
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup.previousPage();
			}else if(this.btnR.collidesWith(x, y))
			{
				this.eleGroup.nextPage();
			}
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_renMing.collidesWith(x, y))
				{
					//如果按下的该元素为未选中状态，则跳过
					if(!((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getIsInView())
						break;
					//设置任命武将
					Player.getInstance().setWj_RenMing(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getWuJiang());
					//设置Dating界面按钮头像
					((Dating)activity.group.getViewById(Const.ID_MAP_DaTing)).update();
					//关闭当前界面
					this.activity.group.removeView(this);
					break;
				}else if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_bg.collidesWith(x, y))
				{
					//先初始化所有为未选中状态
					for(int j = 0; j < this.eleGroup.getGroup().size(); j ++)
					{
						((com.element.EleWuJiang2)this.eleGroup.getGroup().get(j)).setIsInView(false);
					}
					//设置按下的为选中状态
					((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).setIsInView(true);
					break;
				}
			}
			
			this.btn_exit.setScaling(false);
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
