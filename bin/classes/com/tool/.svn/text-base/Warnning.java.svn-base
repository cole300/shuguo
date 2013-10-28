package com.tool;

import android.graphics.Canvas;
import android.graphics.Paint;

/*警告信息提示*/
public class Warnning {

	private String content;//提示内容
	private int color;//提示颜色
	private int font;//提示字号
	private int speedAlpha;//提示alpha衰减速度
	private int alpha = 255;//alpha值
	private boolean isBold;//是否加粗
	private float xPos, yPos;//坐标
	private float ySpeed = 3;//纵向移动速度
	private boolean isScroll;//静止显示/滚动显示
	
	/**
	 *isScroll : <滚动显示><静止显示>
	 * */
	public Warnning(String strWarnning, int colorWarnning, int fontWarnning, boolean isBold, int speedAlpha, boolean isScroll)
	{
		this.content = strWarnning;
		this.color = colorWarnning;
		this.font = fontWarnning;
		this.isBold = isBold;
		this.speedAlpha = speedAlpha;
		this.isScroll = isScroll;
	}
	/*获取当前alpha值*/
	public int getAlpha()
	{
		return this.alpha;
	}
	public void setYspeed(float y)
	{
		this.ySpeed = y;
	}
	public float getYspeed()
	{
		return this.yPos;
	}
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*运行后绘制*/
		this.run();
		
		paint.setColor(this.color);
		paint.setTextSize(this.font);
		paint.setFakeBoldText(this.isBold);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAlpha(this.alpha);
		
		canvas.drawText(this.content, Const.SCREEN_W /2, this.yPos + Const.SCREEN_H *3/5, paint);
		paint.reset();
	}
	/*run*/
	public void run()
	{
		/*是否滚动显示*/
		if(this.isScroll)
		{
			this.yPos -= ySpeed;
		}
		
		this.alpha = this.alpha - this.speedAlpha >= 0 ? this.alpha - this.speedAlpha : 0;
	}
	/*注销资源*/
	public void destroyed()
	{
		this.content = null;
	}
	
}
