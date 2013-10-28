package com.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author gwd
 * 数值显示对象
 * 管理类<NumericalFactory>
 */
public class Numerical {

	/*数值*/
	private int value;
	/*资源图*/
	Bitmap bmp;
	/*横坐标*/
	private int xPos;
	/*纵坐标*/
	private int yPos;
	/*显示延迟*/
	private int c_delay;
	/*延迟数值*/
	private int delay;
	/*类型*/
	private int style;
	
	public Numerical(int xPos, int yPos, int value, int delay, int style, Bitmap bmp)
	{
		this.bmp = bmp;
		this.value = value;
		this.delay = delay;
		this.style = style;
		this.setPosition(xPos, yPos);
	}
	
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		//计算位数
		int numberDigit = String.valueOf(this.value).length();
		//单帧宽度
		int frameW = this.bmp.getWidth() / 12;
		//单帧高度
		int frameH = this.bmp.getHeight();
		//alpha
		int alpha = 255 - this.c_delay*6;
		
		alpha = alpha < 0 ? 0 : alpha;
		
		paint.setAlpha(alpha);
		
		for(int i = 0; i < numberDigit; i ++)
		{
			//当前帧数值
			int curFrameNumber = Tool.getInstance().getDigit(i, value);
			
			switch(this.style)
			{
			case 1:
				 Tool.getInstance().drawRegion(this.bmp, frameW * curFrameNumber, 0, frameW, frameH, this.xPos + i * (frameW - 10)+ this.c_delay, this.yPos - this.c_delay, canvas, paint);
				 break;
			case 2:
				 Tool.getInstance().drawRegion(this.bmp, frameW * curFrameNumber, 0, frameW, frameH, this.xPos + i * (frameW - 10)- this.c_delay, this.yPos - this.c_delay, canvas, paint);
				 break;
			}
			
		}
		
		paint.setAlpha(255);
		
		/*显示控制*/
		this.displayControl();
		
	}
	
	public void displayControl()
	{
		if(this.c_delay >= this.delay)
			return;
		
		this.c_delay ++;
	}
	
	public boolean isDead()
	{
		if(this.c_delay >= this.delay)
		{
			return true;
		}
		return false;
	}

	/*Setter and Getter*/
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public void setPosition(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	public void move(int x_move, int y_move)
	{
		this.xPos += x_move;
		this.yPos += y_move;
	}
}
