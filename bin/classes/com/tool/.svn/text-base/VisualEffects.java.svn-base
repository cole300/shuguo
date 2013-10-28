package com.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/*动态效果*/
public class VisualEffects {

	/*资源图*/
	private Bitmap bmp;
	/*图片横切<0> 还是竖切<1>*/
	private int picDir;
	/*图片帧数量*/
	private int frameNum;
	/*播放指针*/
	private int frameIndex;
	/*坐标*/
	private int xPos, yPos;
	/*速率控制计数器*/
	private int speedCount;
	/*速率控制*/
	private final int SPEED = 1;
	/*name*/
	private String name;
	
	public VisualEffects()
	{
		
	}
	
	public void run()
	{
		speedCount++;
		if(speedCount > SPEED)
		{
			speedCount = 0;
		}else
		{
			return;
		}
		
		if(frameIndex < this.frameNum)
			frameIndex ++;
	}
	
	public boolean isDead()
	{
		if(this.frameIndex >= this.frameNum)
			return true;
		else
			return false;
	}
	
	public void paint(Canvas canvas, Paint paint)
	{
		this.run();
		int frameW = 0;
		int frameH = 0;
		switch(this.picDir)
		{
		case 0://横切
			 frameW = this.bmp.getWidth() / this.frameNum;
			 frameH = this.bmp.getHeight();
			 Tool.getInstance().drawRegion(this.bmp, this.frameIndex * frameW, 0, frameW, frameH, this.xPos, this.yPos, canvas, paint);
			 break;
		case 1://竖切
			 frameW = this.bmp.getWidth();
			 frameH = this.bmp.getHeight() / this.frameNum;
			 Tool.getInstance().drawRegion(this.bmp, 0, this.frameIndex * frameH, frameW, frameH, this.xPos, this.yPos, canvas, paint);
			 break;
		}
	}
	
	public void destroyed()
	{
		Tool.getInstance().destroyed(this.bmp);
	}
	
	/*Setter and Getter*/
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public int getCurFrame()
	{
		return this.frameIndex;
	}
	public int getXpos()
	{
		return this.xPos;
	}
	public int getYpos()
	{
		return this.yPos;
	}
	public void setPosition(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	public Bitmap getBmp()
	{
		return this.bmp;
	}
	public void setBmp(Bitmap bmp)
	{
		this.bmp = bmp;
	}
	public int getPicDir()
	{
		return this.picDir;
	}
	public void setPicDir(int dir)
	{
		this.picDir = dir;
	}
	public int getFrameNum()
	{
		return this.frameNum;
	}
	public void setFrameNum(int num)
	{
		this.frameNum = num;
	}
}
