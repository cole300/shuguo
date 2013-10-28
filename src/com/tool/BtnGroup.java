package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BtnGroup {

	LinkedList<Btn> group;
	
	public int gap = 0;//�Ӱ�ť���
	
	public int startX, startY;
	
	public Bitmap bmp_btn;//��ťͼ
	
	public int color_selected = Color.BLACK;//ѡ�еİ�ť��ɫ
	public int color_unselected = Color.WHITE;//δѡ�еİ�ť��ɫ
	
	public BtnGroup(int gap, int startX, int startY)
	{
		this.group = new LinkedList<Btn>();
		this.startX = startX;
		this.startY = startY;
		this.gap = gap;
	}
	/*��ȡȺ���С*/
	public int getGroupSize()
	{
		return this.group.size();
	}
	/*��ȡȺ��*/
	public LinkedList<Btn> getGroup()
	{
		return this.group;
	}
	/*��ȡ��ťͼƬ*/
	public Bitmap getBmp_Btn()
	{
		return this.bmp_btn;
	}
	/*����ѡ����ɫ*/
	public void setColor_Selected(int color)
	{
		this.color_selected = color;
	}
	/*����δѡ����ɫ*/
	public void setColor_UnSelected(int color)
	{
		this.color_unselected = color;
	}
	/*�����Ԫ��*/
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
	/*����*/
	public void paint(Canvas canvas, Paint paint)
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).paint(canvas, paint);
			
			/*�����ֺ�*/
			paint.setTextSize(20);
			/*��ȡ�ָ�*/
			float strHeight = paint.getFontMetrics().descent - paint.getFontMetrics().top;
			/*���ð�ť����������ɫ*/
			if(this.group.get(i).getBmp() != null)
			{
				paint.setColor(this.color_selected);
			}else
			{
				paint.setColor(this.color_unselected);
			}
			/*���ư�ťname*/
			canvas.drawText(this.group.get(i).getname(),
							this.group.get(i).getXpos() + this.bmp_btn.getWidth() / 2 - paint.measureText(this.group.get(i).getname()) / 2, 
							this.group.get(i).getYpos() + this.bmp_btn.getHeight() / 2 + strHeight/3, paint);
		}
	}
	/*pressed���*/
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
	/*�ָ����а�ť���ޱ���ͼ״̬*/
	public void resetAllBtn()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).setBmp(null);
		}
	}
	/*������Դ*/
	public void destroyed()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
	}
	
}
