package com.tool;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Paint;

/*警告信息管理类*/
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
	
	LinkedList<Warnning> group;//warn元素群组
	
	public WarnningManager()
	{
		this.group = new LinkedList<Warnning>();
	}
	/*添加*/
	public void add(Warnning warnning)
	{
		this.group.add(warnning);
	}
	/*移除*/
	public void removeById(int warnId)
	{
		this.group.get(warnId).destroyed();
		this.group.remove(warnId);
	}
	/*移除所有*/
	public void removeAll()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
		this.group.clear();
	}
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*检测删除失效的warning*/
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
