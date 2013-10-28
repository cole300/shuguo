package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * 
 * @author gwd
 *Numerical管理类
 */
public class NumericalFactory {

	public static NumericalFactory instance;
	
	public static NumericalFactory getInstance()
	{
		if(instance == null)
		{
			instance = new NumericalFactory();
			instance.vecNumer = new LinkedList<Numerical>();
		}
		
		return instance;
	}
	
	LinkedList<Numerical> vecNumer;
	
	Bitmap bmp;
	/*设置Numerical用图*/
	public void setBitmap(String url, Activity activity)
	{
		this.bmp = SourcePool.getInstance().createAssetsBitmap(url, activity);
	}
	
	/*增加*/
	public void active(int xPos, int yPos, int value, int style)
	{
		this.vecNumer.add(new Numerical(xPos, yPos, value, 30, style, this.bmp));
	}
	/*删除*/
	public void remove(int idx)
	{
		this.vecNumer.remove(idx);
	}
	/*查看*/
	public Numerical get(int idx)
	{
	    return this.vecNumer.get(idx); 
	}
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		this.run();
		
		for(int i = 0; i < this.vecNumer.size(); i ++)
		{
			this.vecNumer.get(i).paint(canvas, paint);
		}
	}
	/*run*/
	public void run()
	{
		for(int i = 0; i < this.vecNumer.size(); i ++)
		{
			if(this.vecNumer.get(i).isDead())
			{
				this.vecNumer.remove(i);
			}
		}
	}
	/*销毁*/
	public void destroyed()
	{
		Tool.getInstance().destroyed(this.bmp);
	}
	
}
