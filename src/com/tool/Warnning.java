package com.tool;

import android.graphics.Canvas;
import android.graphics.Paint;

/*������Ϣ��ʾ*/
public class Warnning {

	private String content;//��ʾ����
	private int color;//��ʾ��ɫ
	private int font;//��ʾ�ֺ�
	private int speedAlpha;//��ʾalpha˥���ٶ�
	private int alpha = 255;//alphaֵ
	private boolean isBold;//�Ƿ�Ӵ�
	private float xPos, yPos;//����
	private float ySpeed = 3;//�����ƶ��ٶ�
	private boolean isScroll;//��ֹ��ʾ/������ʾ
	
	/**
	 *isScroll : <������ʾ><��ֹ��ʾ>
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
	/*��ȡ��ǰalphaֵ*/
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
	/*����*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*���к����*/
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
		/*�Ƿ������ʾ*/
		if(this.isScroll)
		{
			this.yPos -= ySpeed;
		}
		
		this.alpha = this.alpha - this.speedAlpha >= 0 ? this.alpha - this.speedAlpha : 0;
	}
	/*ע����Դ*/
	public void destroyed()
	{
		this.content = null;
	}
	
}
