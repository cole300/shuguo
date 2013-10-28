package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

/*折叠的有主按钮可供伸展开按钮组的ButtonGroup*/
public class FolderBtnGroup {

	private LinkedList<Btn> group;

	public static final int BTN_GAP = 40;//按钮间距
	
	private Btn btnMain;
	
	public static final int STATE_GROUP_CLOSE = 0;
	public static final int STATE_GROUP_OPEN = 1;
	private int state = STATE_GROUP_OPEN;//群组状态<打开状态中><关闭状态中>
	
	public FolderBtnGroup(String bmpURL, int xpos, int ypos, Activity activity)
	{
		btnMain = new Btn(bmpURL, xpos, ypos, this, activity);
		btnMain.setName("group");
		
		this.group = new LinkedList<Btn>();
		this.group.add(btnMain);
	}
	public int getGroupSize()
	{
		return this.group.size();
	}
	public LinkedList<Btn> getGroup()
	{
		return this.group;
	}
	/*群组添加子Button*/
	public void addBtn(String bmpURL, String name, Activity activity)
	{
		Btn btn = new Btn(bmpURL, 0, 0, this, activity);
	    	btn.setOpenX(this.btnMain.getXpos() - this.group.size() * (btn.getWidth()+BTN_GAP) - BTN_GAP);
	    	btn.setOpenY(this.btnMain.getYpos());
	    	btn.setPosition(btn.getOpenX(), btn.getOpenY());
	    	
	    	btn.setName(name);
	    	
		this.group.add(btn);
	}
	/*群组所有Button检测是否按下*/
	public void checkPressed(float x, float y)
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).checkPressed(x, y, true);
		}
	}
	/*关闭群组所有Button放大状态*/
	public void closeScaling()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).setScaling(false);
		}
	}
	/*群组所有Button绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		int for_start = this.group.size() - 1;
		for(int i = for_start; i >= 0; i --)
		{
			this.group.get(i).paint(canvas, paint);
		}
	}
	/*获取群组开关状态*/
	public int getState()
	{
		return this.state;
	}
	/*设置群组开关状态*/
	public void setState(int state)
	{
		this.state = state;
	}
	/*获取主按钮*/
	public Btn getBtnMain()
	{
		return this.btnMain;
	}
	/*销毁*/
	public void destroyed()
	{
		/*销毁容器内所有资源*/
		for(int i =0 ; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
		/*清空容器*/
		this.group.clear();
		this.group = null;
	}
	/*群组主Button展开关闭检测*/
	public void checkOpenClose(float x, float y)
	{
		if(!this.btnMain.collidesWith(x, y))
			return;
		
		switch(this.state)
		{
		case STATE_GROUP_CLOSE://打开操作
			
			for(int i = 1; i < this.group.size(); i ++)
			{
				this.group.get(i).setDisappearShield(false);
			}
			this.setState(STATE_GROUP_OPEN);
			
			break;
		case STATE_GROUP_OPEN://关闭操作
			
			for(int i = 1; i < this.group.size(); i ++)
			{
				this.group.get(i).setDisappearShield(true);
			}
			this.setState(STATE_GROUP_CLOSE);
			
			break;
		}
		
	}
}
