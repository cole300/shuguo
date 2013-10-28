package com.tool;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Paint;

/*������Ϣ������*/
public class WarnningManager {

	public static WarnningManager instance;
	public static WarnningManager getInstance()
	{
		if(instance == null)
		{
			instance = new WarnningManager();
		}
		
		return instance;
	}
	
	LinkedList<Warnning> group;//warnԪ��Ⱥ��
	
	public WarnningManager()
	{
		this.group = new LinkedList<Warnning>();
	}
	/*���*/
	public void add(Warnning warnning)
	{
		this.group.add(warnning);
	}
	/*�Ƴ�*/
	public void removeById(int warnId)
	{
		this.group.get(warnId).destroyed();
		this.group.remove(warnId);
	}
	/*�Ƴ�����*/
	public void removeAll()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
		this.group.clear();
	}
	/*����*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*���ɾ��ʧЧ��warning*/
		this.checkDestroyed();
		
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).paint(canvas, paint);
		}
	}
	/*run*/
	public void checkDestroyed()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			if(this.group.get(i).getAlpha() == 0)
			{
				this.removeById(i);
			}
		}
	}
}
