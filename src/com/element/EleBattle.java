package com.element;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Const;
import com.tool.Data;
import com.tool.Element;
import com.tool.NodeManager;
import com.tool.Tool;
import com.view.Battle;

/*ս��Ԫ��*/
public class EleBattle implements Element{

	private com.data.WuJiang wuj;
	
	private NodeManager ani;//����
	
	private byte stage;
	
	public EleBattle(com.data.WuJiang wuj, int init_xPos, int init_yPos, int anchor, float scaleLev, ShuguoActivity activity)
	{
		this.wuj = wuj;
		/*����Ԫ����Դ*/
		this.loadSource(activity);
		//��ʼ��ΪMove״̬
		this.stage = Const.EleBattle_Move;
		//���ض���animation
		this.ani = new NodeManager(scaleLev, anchor, activity);
		this.ani.setPosition(init_xPos, init_yPos);
		//ˢ��װ��
		Tool.getInstance().refreshEquipStyleEffects(ani, wuj, activity);
	}
	/*������Դ*/
	public void loadSource(ShuguoActivity activity)
	{
	
	}
	/*������Դ*/
	public void destroyed()
	{
		/*���ٶ���animation*/
		this.ani.destroyed();
	}
	/*��ö���animation*/
	public NodeManager getAni()
	{
		return this.ani;
	}
	/*��ȡ��ǰ�׶�*/
	public byte getStage()
	{
		return this.stage;
	}
	/*��⶯���仯*/
	public void checkAction()
	{
		switch(this.stage)
		{
		case Const.EleBattle_Move:
			 
				if(this.ani.getAnchor() == Const.ANCHOR_NORMAL)
				{
					if(this.ani.getXpos() < Battle.LINE_RUSH)
					{
						//�������
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_MOVE)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_MOVE);
						 }
						 //�ƶ�
						 this.getAni().move(Battle.SPEED_MOVE, 0);
					}else
					{
						this.stage = Const.EleBattle_Rush;
					}
				}else if(this.ani.getAnchor() == Const.ANCHOR_MIRROR)
				{
					if(this.getAni().getXpos() > Const.SCREEN_W - Battle.LINE_RUSH - 150)
					{
						 //�������
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_MOVE)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_MOVE);
						 }
						 //�ƶ�
						 this.getAni().move(-Battle.SPEED_MOVE, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Rush;
						 
					 }
				}
			
			 break;
		case Const.EleBattle_Rush:
			
				if(this.ani.getAnchor() == Const.ANCHOR_NORMAL)
				{
					if(this.getAni().getXpos() < Const.SCREEN_W / 2 - 150)
					{
						 //�������
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_RUSH)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_RUSH);
							 this.getAni().setDelay(3);
						 }
						 //�ƶ�
						 this.getAni().move(Battle.SPEED_RUSH, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Battle;
						 this.getAni().setCurActionData(Data.FRAME_DATA_STAND);
					 }
				}else if(this.ani.getAnchor() == Const.ANCHOR_MIRROR)
				{
					if(this.getAni().getXpos() > Const.SCREEN_W / 2 - 50)
					{
						 //�������
						 if(this.getAni().getCurActionData() != Data.FRAME_DATA_RUSH)
						 {
							 this.getAni().setCurActionData(Data.FRAME_DATA_RUSH);
							 this.getAni().setDelay(2);
						 }
						 //�ƶ�
						 this.getAni().move(-Battle.SPEED_RUSH, 0);
					 }else
					 {
						 this.stage = Const.EleBattle_Battle;
						 this.getAni().setCurActionData(Data.FRAME_DATA_STAND);
					 }
				}
			
			 break;
		case Const.EleBattle_Battle:
			
			 break;
		}
	}
	
	public void paint(int x, int y, Canvas canvas, Paint paint)
	{
		
	}
	/*������Դ*/
	public void paint(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
		/*���Ƶ�ͼ*/
		canvas.save();
		this.ani.paint(canvas, paint);
		canvas.restore();
		/*�佫����*/
		paint.setColor(Color.DKGRAY);
		paint.setFakeBoldText(true);
		paint.setTextSize(16);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.wuj.getName(), this.ani.getXpos() + 50, this.ani.getYpos() +42, paint);
	}
	/*�����佫����*/
	public com.data.WuJiang getWuJiang()
	{
		return this.wuj;
	}
	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return 100;
	}
	public int getElementHeight() {
		// TODO Auto-generated method stub
		return 100;
	}

}
