package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BtnGroup {

	LinkedList<Btn> group;
	
	public int gap = 0;//子按钮间距
	
	public int startX, startY;
	
	public Bitmap bmp_btn;//按钮图
	
	public int color_selected = Color.BLACK;//选中的按钮字色
	public int color_unselected = Color.WHITE;//未选中的按钮字色
	
	public BtnGroup(int gap, int startX, int startY)
	{
		this.group = new LinkedList<Btn>();
		this.startX = startX;
		this.startY = startY;
		this.gap = gap;
	}
	/*获取群组大小*/
	public int getGroupSize()
	{
		return this.group.size();
	}
	/*获取群组*/
	public LinkedList<Btn> getGroup()
	{
		return this.group;
	}
	/*获取按钮图片*/
	public Bitmap getBmp_Btn()
	{
		return this.bmp_btn;
	}
	/*设置选中字色*/
	public void setColor_Selected(int color)
	{
		this.color_selected = color;
	}
	/*设置未选中字色*/
	public void setColor_UnSelected(int color)
	{
		this.color_unselected = color;
	}
	/*添加子元素*/
	public void add(String bmpURL, String name, Activity activity)
	{
		this.bmp_btn = SourcePool.getInstance().createAssetsBitmap(bmpURL, activity);
		Btn btn = new Btn(null, 0, 0, null, activity);
			btn.setWidth(this.bmp_btn.getWidth());
	    	btn.setHeight(this.bmp_btn.getHeight());
	    	btn.setPosition(this.startX + this.getGroupSize() * (bmp_btn.getWidth() + this.gap), this.startY);
	    	btn.setName(name);
	    	btn.setProperties("SINGLE");
		this.group.add(btn);
	}
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).paint(canvas, paint);
			
			/*设置字号*/
			paint.setTextSize(20);
			/*获取字高*/
			float strHeight = paint.getFontMetrics().descent - paint.getFontMetrics().top;
			/*设置按钮文字内容颜色*/
			if(this.group.get(i).getBmp() != null)
			{
				paint.setColor(this.color_selected);
			}else
			{
				paint.setColor(this.color_unselected);
			}
			/*绘制按钮name*/
			canvas.drawText(this.group.get(i).getname(),
							this.group.get(i).getXpos() + this.bmp_btn.getWidth() / 2 - paint.measureText(this.group.get(i).getname()) / 2, 
							this.group.get(i).getYpos() + this.bmp_btn.getHeight() / 2 + strHeight/3, paint);
		}
	}
	/*pressed检测*/
	public void checkPressed(float x, float y)
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			if(this.group.get(i).checkPressed(x, y, false))
			{
				this.resetAllBtn();
				this.group.get(i).setBmp(this.bmp_btn);
				break;
			}
		}
	}
	/*恢复所有按钮到无背景图状态*/
	public void resetAllBtn()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).setBmp(null);
		}
	}
	/*销毁资源*/
	public void destroyed()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
	}
	
}
