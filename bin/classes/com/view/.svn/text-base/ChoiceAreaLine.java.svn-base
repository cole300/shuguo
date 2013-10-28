package com.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.element.EleBtn;
import com.shu.ShuguoActivity;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class ChoiceAreaLine extends View{

	ShuguoActivity activity;
	
	Paint paint;
	
	Bitmap bmp_bg;
	
	com.tool.ElementGroup eleGroup_Area;
	com.tool.ElementGroup eleGroup_Line;
	
	private int number_line;//已选择几线
	private int number_area;//已选择几区
	
	public ChoiceAreaLine(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		
		this.loadSource();
	}
	
	public void initEleGroup()
	{
		//初始化区
		this.eleGroup_Area = new ElementGroup(85, 105, 4, 2, 15, 25);
		
		for(int i = 0; i < 3; i ++)
		{
			EleBtn eleBtn = new EleBtn(this.eleGroup_Area, i, 1.0f, 1.0f, activity);
				   eleBtn.btn.setNameStyle((i+1)+"区", Color.BLACK, 24, true);
			this.eleGroup_Area.add(eleBtn);
		}
		//初始化线
		this.eleGroup_Line = new ElementGroup(85, 280, 4, 2, 15, 25);
	}
	/*刷新线*/
	public void updateLine()
	{
		this.eleGroup_Line.destoryed();
		
		for(int i = 0; i < 7; i ++)
		{
			EleBtn eleBtn = new EleBtn(this.eleGroup_Line, i, 1.0f, 1.0f, activity);
			   eleBtn.btn.setNameStyle((i+1)+"线", Color.BLACK, 24, true);
			this.eleGroup_Line.add(eleBtn);
		}
	}
	
	public void loadSource()
	{
		//加载ElementGroup
		this.initEleGroup();
		//背景图
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("login/area.png", activity);
		
	}
	public void releaseSource()
	{
		//销毁eleGroup_Area
		this.eleGroup_Area.destoryed();
		//销毁eleGroup_Line
		this.eleGroup_Line.destoryed();
		//销毁背景
		Tool.getInstance().destroyed(this.bmp_bg);
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		//绘制背景
		canvas.drawBitmap(this.bmp_bg, 0, 0, paint);
		//绘制eleGroup_Area群组
		this.eleGroup_Area.paint(canvas, paint);
		//绘制eleGroup_Line群组
		if(this.eleGroup_Line.getGroup().size() == 0)
		{
			paint.setColor(Color.WHITE);
			paint.setTextAlign(Paint.Align.CENTER);
			paint.setTextSize(30);
			paint.setFakeBoldText(true);
			canvas.drawText("请先选择游戏区", Const.SCREEN_W / 2, Const.SCREEN_H / 2 + 100, paint);
		}
		else
			this.eleGroup_Line.paint(canvas, paint);
		
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTextSize(26);
		canvas.drawText("服务器选择："+this.number_area, 85, 100, paint);
		canvas.drawText("游戏线选择："+this.number_line, 85, 275, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			//eleGroup_Area
			for(int i = 0; i < this.eleGroup_Area.getGroup().size(); i ++)
			{
				EleBtn eleBtn = (EleBtn)this.eleGroup_Area.getGroup().get(i);
				
				if(eleBtn.btn.checkPressed(x, y, true))
				{
					this.updateLine();//刷新线
					this.number_area = i + 1;
				}
			}
			//eleGroup_Line
			for(int i = 0; i < this.eleGroup_Line.getGroup().size(); i ++)
			{
				EleBtn eleBtn = (EleBtn)this.eleGroup_Line.getGroup().get(i);
				
				if(eleBtn.btn.checkPressed(x, y, true))
				{
					this.number_line = i + 1;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			//eleGroup_Area
			for(int i = 0; i < this.eleGroup_Area.getGroup().size(); i ++)
			{
				EleBtn eleBtn = (EleBtn)this.eleGroup_Area.getGroup().get(i);
			    eleBtn.btn.setScaling(false);
			}
			//eleGroup_Line
			for(int i = 0; i < this.eleGroup_Line.getGroup().size(); i ++)
			{
				EleBtn eleBtn = (EleBtn)this.eleGroup_Line.getGroup().get(i);
			    eleBtn.btn.setScaling(false);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
	
}
