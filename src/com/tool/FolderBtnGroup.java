package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

/*�۵���������ť�ɹ���չ����ť���ButtonGroup*/
public class FolderBtnGroup {

	private LinkedList<Btn> group;

	public static final int BTN_GAP = 40;//��ť���
	
	private Btn btnMain;
	
	public static final int STATE_GROUP_CLOSE = 0;
	public static final int STATE_GROUP_OPEN = 1;
	private int state = STATE_GROUP_OPEN;//Ⱥ��״̬<��״̬��><�ر�״̬��>
	
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
	/*Ⱥ�������Button*/
	public void addBtn(String bmpURL, String name, Activity activity)
	{
		Btn btn = new Btn(bmpURL, 0, 0, this, activity);
	    	btn.setOpenX(this.btnMain.getXpos() - this.group.size() * (btn.getWidth()+BTN_GAP) - BTN_GAP);
	    	btn.setOpenY(this.btnMain.getYpos());
	    	btn.setPosition(btn.getOpenX(), btn.getOpenY());
	    	
	    	btn.setName(name);
	    	
		this.group.add(btn);
	}
	/*Ⱥ������Button����Ƿ���*/
	public void checkPressed(float x, float y)
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).checkPressed(x, y, true);
		}
	}
	/*�ر�Ⱥ������Button�Ŵ�״̬*/
	public void closeScaling()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).setScaling(false);
		}
	}
	/*Ⱥ������Button����*/
	public void paint(Canvas canvas, Paint paint)
	{
		int for_start = this.group.size() - 1;
		for(int i = for_start; i >= 0; i --)
		{
			this.group.get(i).paint(canvas, paint);
		}
	}
	/*��ȡȺ�鿪��״̬*/
	public int getState()
	{
		return this.state;
	}
	/*����Ⱥ�鿪��״̬*/
	public void setState(int state)
	{
		this.state = state;
	}
	/*��ȡ����ť*/
	public Btn getBtnMain()
	{
		return this.btnMain;
	}
	/*����*/
	public void destroyed()
	{
		/*����������������Դ*/
		for(int i =0 ; i < this.group.size(); i ++)
		{
			this.group.get(i).destroyed();
		}
		/*�������*/
		this.group.clear();
		this.group = null;
	}
	/*Ⱥ����Buttonչ���رռ��*/
	public void checkOpenClose(float x, float y)
	{
		if(!this.btnMain.collidesWith(x, y))
			return;
		
		switch(this.state)
		{
		case STATE_GROUP_CLOSE://�򿪲���
			
			for(int i = 1; i < this.group.size(); i ++)
			{
				this.group.get(i).setDisappearShield(false);
			}
			this.setState(STATE_GROUP_OPEN);
			
			break;
		case STATE_GROUP_OPEN://�رղ���
			
			for(int i = 1; i < this.group.size(); i ++)
			{
				this.group.get(i).setDisappearShield(true);
			}
			this.setState(STATE_GROUP_CLOSE);
			
			break;
		}
		
	}
}
