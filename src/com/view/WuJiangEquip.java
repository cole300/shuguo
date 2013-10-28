package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.data.Player;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.NodeManager;
import com.tool.SourcePool;
import com.tool.Tool;

public class WuJiangEquip extends View{

	Paint paint;
	
	ShuguoActivity activity;
	
	/*ElementȺ��*/
	ElementGroup eleGroup_bag;//����ɸѡ������װ��
	ElementGroup eleGroup_equip;//װ��Ⱥ��
	
	private float SCALE_VALUE_1 = 0.5f;
	
	NodeManager nManager;
	
	Bitmap bmp_bg;
	
	Bitmap bmp_bg_gaishu;//����
	
	Bitmap bmp_bg_change;//��װ����
	
	Bitmap bmp_exp_bottom, bmp_exp;//������,��ͼ
	Bitmap bmp_bg_lev;//�ȼ�����
	
	Btn btnL, btnR;//���Ҽ�ͷ
	
	Btn btnExit;
	
	com.data.WuJiang wuj;
	
	public WuJiangEquip(com.data.WuJiang wj, Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		
		this.wuj = wj;
		
		this.loadSource(activity);
		Tool.getInstance().refreshEquipStyleEffects(this.nManager, wj, activity);//ˢ�����ϵĻ�װЧ��
	}
	/*��ʼ��elementȺ��*/
	public void initEleGroup()
	{
		/*��ѡ��ƷȺ��*/
		this.eleGroup_bag = new ElementGroup(405, 111, 2, 3, -30, -30);
		
		for(int i = 0; i < Player.getInstance().getItem().size(); i ++)
		{
			this.eleGroup_bag.add(new com.element.EleItem(Player.getInstance().getItem().get(i), this.eleGroup_bag, i, 0.7f, 0.7f, true, this.activity));
		}
		/*װ���б�Ⱥ��*/
		this.eleGroup_equip = new ElementGroup(146, 330, 4, 1, -50, 0);
		for(int i = 0; i < this.wuj.getVecEquip().size(); i ++)
		{
			if(this.wuj.getVecEquip().get(i) == null)
			{
				this.eleGroup_equip.add(null);
			}else
			{
				this.eleGroup_equip.add(new com.element.EleItem(this.wuj.getVecEquip().get(i), this.eleGroup_equip, i, SCALE_VALUE_1, SCALE_VALUE_1, false, this.activity));
			}
		}
	}
	/*��������Դ*/
	public void loadSource(Activity activity)
	{
		/*����*/
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_equip.png", activity);
		/*NodeManager*/
		this.nManager = new NodeManager(0.8f, Const.ANCHOR_NORMAL, activity);
		this.nManager.setPosition(155, 30);
		/*����*/
		this.bmp_bg_gaishu = SourcePool.getInstance().createAssetsBitmap("element/gaishu2.png", activity);
		/*��װ����*/
		this.bmp_bg_change = SourcePool.getInstance().createAssetsBitmap("element/check_bg_skill.png", activity);
		/*��ʼ�����Ҽ�ͷ*/
		this.btnL = new Btn("btn/left.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 250, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 216, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 473, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 216, null, activity);
		this.btnR.setProperties("SINGLE");
		
		this.btnExit = new Btn("element/exit.png", 608, 23, null, activity);
		this.btnExit.setProperties("SINGLE");
		/*������*/
		this.bmp_exp_bottom = SourcePool.getInstance().createAssetsBitmap("element/exp_bottom_wj.png", activity);
		this.bmp_exp = SourcePool.getInstance().createAssetsBitmap("element/exp_wj.png", activity);
		/*�ȼ�����*/
		this.bmp_bg_lev = SourcePool.getInstance().createAssetsBitmap("element/bg_lev.png", activity);
		/*����eleGroupȺ��*/
		this.initEleGroup();
	}
	
	/*��������Դ*/
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		/*NodeManager*/
		this.nManager.destroyed();
		/*����*/
		Tool.getInstance().destroyed(this.bmp_bg_gaishu);
		/*��װ����*/
		Tool.getInstance().destroyed(this.bmp_bg_change);
		/*������*/
		Tool.getInstance().destroyed(this.bmp_exp_bottom);
		Tool.getInstance().destroyed(this.bmp_exp);
		/*�ȼ�����*/
		Tool.getInstance().destroyed(this.bmp_bg_lev);
		/*����eleGroupȺ��*/
		this.eleGroup_bag.destoryed();
		this.eleGroup_equip.destoryed();
		
		this.btnL.destroyed();
		this.btnR.destroyed();
		this.btnExit.destroyed();
	}
	/*��������Դ*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/*��ǰ�������ʼ����*/
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		
		/*���Ʊ���*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*����title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("װ��", sx + 38, sy + 46, paint);
		/*�佫��*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.wuj.getName(), sx + 81, sy + 92, paint);
		/*������*/
		canvas.drawBitmap(this.bmp_exp_bottom, sx + 83, sy + 274, paint);
		canvas.drawBitmap(this.bmp_exp, sx + 83, sy + 274, paint);
		/*�ȼ�����*/
		canvas.drawBitmap(this.bmp_bg_lev, sx + 63, sy + 267, paint);
		/*�ȼ���ֵ*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(15);
		canvas.drawText("17", sx + 75, sy + 285, paint);
		/*��������*/
		canvas.drawBitmap(this.bmp_bg_gaishu, sx + 37, sy + 297, paint);
		/*����title*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("�ſ�", sx + 150, sy + 318, paint);
		/*��װ����*/
		canvas.drawBitmap(this.bmp_bg_change, sx + 274, sy + 78, paint);
		/*��װtitle*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("�������װ��", sx + 393, sy + 104, paint);
		/*ҳ��*/
		paint.setColor(Color.rgb(214, 209, 205));
		paint.setTextSize(22);
		paint.setTextAlign(Paint.Align.CENTER);
//		canvas.drawText("1/3", sx + 393, sy + 423, paint);
		canvas.drawText((this.eleGroup_bag.getIdxPage()+1) + "/"+(this.eleGroup_bag.getNumPage()+1), sx + 393, sy + 435, paint);
		/*eleGroup_bag*/
		this.eleGroup_bag.paint(canvas, paint);
		/*eleGroup_equip*/
		this.eleGroup_equip.paint(canvas, paint);
		/*���Ҽ�ͷ*/
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		/*NodeNamager*/
		this.nManager.paint(canvas, paint);
		/*Exit button*/
		this.btnExit.paint(canvas, paint);
		
		com.tool.WarnningManager.getInstance().paint(canvas, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			this.btnExit.checkPressed(x, y, true);
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			
			for(int i = 0; i < this.eleGroup_bag.getGroup().size(); i ++)
			{
				if(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.checkPressed(x, y, true)){break;}
			}
			for(int i = 0; i < this.eleGroup_equip.getGroup().size(); i ++)
			{
				if(this.eleGroup_equip.getGroup().get(i) == null)
					continue;
				
				if(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.checkPressed(x, y, true)){break;}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btnExit.collidesWith(x, y))
			{
				//���ٵ�ǰview
				this.releaseSource();
				activity.group.removeView(this);
				//ˢ����һ������WuJiangCheck��װ��Ч��
				int idxView = activity.group.getChildCount() - 1;
				com.tool.NodeManager nManager = ((com.view.WuJiangCheck)(activity.group.getChildAt(idxView))).getNodeManager();
				com.data.WuJiang wuj = ((com.view.WuJiangCheck)(activity.group.getChildAt(idxView))).getWuJiang();
				Tool.getInstance().refreshEquipStyleEffects(nManager, wuj, activity);
				//ˢ����һ������WuJiangCheck��װ��ͼ����
				((com.view.WuJiangCheck)activity.group.getChildAt(idxView)).update_equipBtns();
				
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup_bag.previousPage();
			}else if(this.btnR.collidesWith(x, y))
			{
				this.eleGroup_bag.nextPage();
			}
			
			this.btnExit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			
			int idxStart = this.eleGroup_bag.getIdxStart();
			int idxEnd = this.eleGroup_bag.getIdxEnd();
			for(int i = idxStart; i < idxEnd; i ++)
			{
				((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.setScaling(false);
				
				if(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).btn_bg.checkPressed(x, y, false))
				{
					/*����������*/
					this.nManager.changeGuGe(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getType(), ((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getStyleId(), activity);
					/*���װ���б����λ������װ������Ž�����*/
					int type_equip = ((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem().getType();
					if(this.eleGroup_equip.getGroup().get(type_equip)!=null)
					{
						this.equipOff(((com.element.EleItem)this.eleGroup_equip.getGroup().get(type_equip)).getItem(), this.wuj, type_equip);
					}
					/*�Ž�װ���б�*/
					this.equipOn(((com.element.EleItem)this.eleGroup_bag.getGroup().get(i)).getItem(), this.wuj, i);
					break;
				}
			}
			for(int i = 0; i < this.eleGroup_equip.getGroup().size(); i ++)
			{
				/*��Ʒ�б�ɾ�տ���remove����װ���б����̶�4��λ�ã�����Ҫ�ж��Ƿ�Ϊ��*/
				if(this.eleGroup_equip.getGroup().get(i) == null)
					continue;
				
				((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.setScaling(false);
				
				if(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).btn_bg.checkPressed(x, y, false))
				{
					/*�ָ�ԭʼ��������*/
					this.nManager.changeGuGe(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).getItem().getType(), -1, activity);
					/*ж��װ��->��Ʒ�б�*/
					this.equipOff(((com.element.EleItem)this.eleGroup_equip.getGroup().get(i)).getItem(), this.wuj, i);
					break;
				}
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
	/*װ������װ���б�*/
	public void equipOn(com.data.Item item, com.data.WuJiang wuj, int idFromItemGroup)
	{
		com.tool.WarnningManager.getInstance().add(new com.tool.Warnning(wuj.getName()+"װ����"+item.getName(), 0x00ff00, 35, true, 2, false));
		/*������Ʒid��ͬ���򲻽����κβ�������*/
		if(wuj.getVecEquip().get(item.getType()) != null)
		{
			if(item.getId() == wuj.getVecEquip().get(item.getType()).getId())
			{
				return;
			}
		}
		
		/*�����Ʒ��װ���б�*/
		//�����ʾ������
		this.eleGroup_equip.getGroup().set(item.getType(), new com.element.EleItem(item, this.eleGroup_bag, item.getType(), SCALE_VALUE_1, SCALE_VALUE_1, false, this.activity));
		//������ݲ�����
		wuj.getVecEquip().set(item.getType(), item);
		/*����Ʒ�б���ȥ������Ʒ*/
		if(Player.getInstance().getItem().get(idFromItemGroup).getNum() > 1)
		{
			Player.getInstance().getItem().get(idFromItemGroup).setNum(Player.getInstance().getItem().get(idFromItemGroup).getNum() - 1);
		}else
		{
			//ȥ����ʾ������
			this.eleGroup_bag.getGroup().get(idFromItemGroup).destroyed();
			this.eleGroup_bag.getGroup().remove(idFromItemGroup);
			//ȥ�����ݲ�����
			Player.getInstance().getItem().remove(item);
		}
	}
	/*жװ�����뱳��*/
	public void equipOff(com.data.Item item, com.data.WuJiang wuj, int idFromEquipGroup)
	{
		com.tool.WarnningManager.getInstance().add(new com.tool.Warnning(wuj.getName()+"ж����"+item.getName(), 0xff0000, 35, true, 2, false));
		/*���ж����Ʒ����Ʒ�б�*/
		boolean isFound = false;
		//�����Ʒ�б�������ͬ��Ʒ��������+1
		for(int i = 0; i < Player.getInstance().getItem().size(); i ++)
		{
			if(Player.getInstance().getItem().get(i).getId() == item.getId())
			{
				Player.getInstance().getItem().get(i).setNum(Player.getInstance().getItem().get(i).getNum() + 1);
				isFound = true;
				break;
			}
		}
		//�����Ʒ�б���û����ͬ��Ʒ������Ӹ���Ʒ����Ʒ�б�
		if(!isFound)
		{
			this.eleGroup_bag.getGroup().add(new com.element.EleItem(item, this.eleGroup_bag, item.getType(), 0.7f, 0.7f, false, this.activity));
			Player.getInstance().getItem().add(item);
		}
		/*���װ���б��λ��*/
		this.eleGroup_equip.getGroup().get(idFromEquipGroup).destroyed();
		this.eleGroup_equip.getGroup().set(idFromEquipGroup, null);
		wuj.getVecEquip().set(item.getType(), null);
	}
	
}
