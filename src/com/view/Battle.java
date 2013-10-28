package com.view;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.data.Player;
import com.element.EleBattle;
import com.element.EleBattleDetail;

import com.shu.ShuguoActivity;
import com.tool.BattleLogic;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.NumericalFactory;
import com.tool.SourcePool;
import com.tool.Tool;
import com.tool.VisualEffectsManager;
import com.tool.Warnning;
import com.tool.WarnningManager;

/*ս��*/
public class Battle extends View{

	Paint paint;
	ShuguoActivity activity;
	
	Bitmap bmp_bg;
	Bitmap bmp_state;
	
	Bitmap bmp_zhanyi;
	Bitmap bmp_blood_home;
	
	LinkedList<EleBattle> vecHome;//����ж�
	LinkedList<EleBattle> vecGuest;//�ұ��ж�
	
	public static boolean visInfoBar = true;//��ʾ��Ϣbar����
	
	/*<MOVE><RUSH><ATTACK>*/
	public static int LINE_RUSH = 50;
	/*SPEED*/
	public static int SPEED_MOVE = 1;
	public static int SPEED_RUSH = 2;
	/*group*/
	public ElementGroup group_HomeDetail;
	public ElementGroup group_GuestDetail;
	
	public Battle(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		/*��������Դ*/
		this.loadResource(activity);
		
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	/*��ʼ��elementȺ��*/
	public void initEleGroup()
	{
		//����ж�
		this.vecHome = new LinkedList<EleBattle>();
		this.vecHome.add(new com.element.EleBattle(Player.getInstance().getWuJiang().get(1), -130, Const.SCREEN_H/2-50, Const.ANCHOR_NORMAL, 0.50f, this.activity));
		this.vecHome.add(new com.element.EleBattle(Player.getInstance().getWuJiang().get(0), -100, Const.SCREEN_H/2, Const.ANCHOR_NORMAL, 0.50f, this.activity));
		//�ұ��ж�
		this.vecGuest = new LinkedList<EleBattle>();
		this.vecGuest.add(new com.element.EleBattle(Player.getInstance().getWuJiang().get(3), Const.SCREEN_W-90, Const.SCREEN_H/2-30, Const.ANCHOR_MIRROR, 0.50f, this.activity));
		//detail-home
		this.group_HomeDetail = new ElementGroup(60,45,1,4,15,5);
		this.group_HomeDetail.add(new EleBattleDetail(Player.getInstance().getWuJiang().get(1), this.group_HomeDetail, 0, Const.ANCHOR_NORMAL, this.activity));
		this.group_HomeDetail.add(new EleBattleDetail(Player.getInstance().getWuJiang().get(0), this.group_HomeDetail, 1, Const.ANCHOR_NORMAL, this.activity));
		//detail-guest
		this.group_GuestDetail = new ElementGroup(442,45,1,4,15,5);
		this.group_GuestDetail.add(new EleBattleDetail(Player.getInstance().getWuJiang().get(3), this.group_GuestDetail, 0, Const.ANCHOR_MIRROR, this.activity));
	}
	/*������Դ*/
	public void loadResource(Activity activity)
	{
		//���ر���ͼ
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("battle/sence.png", activity);
		//��ʼ��Ⱥ��
		this.initEleGroup();
		//���ز�����ս�����̣�������
		BattleLogic.getInstance().serializeData(this.vecHome, this.vecGuest);
		BattleLogic.getInstance().simulateResult();
		//������ֵ
		NumericalFactory.getInstance().setBitmap("battle/num_n.png", activity);
		//
		this.bmp_state = SourcePool.getInstance().createAssetsBitmap("battle/state.png", activity);
		//zhanyi
		this.bmp_zhanyi = SourcePool.getInstance().createAssetsBitmap("battle/zhanyi.png", activity);
		//
		this.bmp_blood_home = SourcePool.getInstance().createAssetsBitmap("battle/blood_red.png", activity);
	}
	/*�ͷ���Դ*/
	public void releaseResource()
	{
		//�ͷű���ͼ
		Tool.getInstance().destroyed(this.bmp_bg);
		//�ͷ�������Դ
		for(int i = 0; i < this.vecHome.size(); i ++)
		{
			this.vecHome.get(i).destroyed();
		}
		this.vecHome.clear();
		//�ͷſͶ���Դ
		for(int i = 0; i <  this.vecGuest.size(); i ++)
		{	
			this.vecGuest.get(i).destroyed();
		}
		this.vecGuest.clear();
		
		Tool.getInstance().destroyed(this.bmp_state);
		//details-home
		this.group_HomeDetail.destoryed();
		//details-guest
		this.group_GuestDetail.destoryed();
		//zhanyi
		Tool.getInstance().destroyed(this.bmp_zhanyi);
		//
		Tool.getInstance().destroyed(this.bmp_blood_home);
	}
	/*������Դ*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/*���Ʊ���*/
		canvas.drawBitmap(this.bmp_bg, 0,  0, paint);
		/*�����佫*/
		this.paintWuJiang(canvas, paint);
		/*ս������*/
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.BLACK);
		/*��������*/
		this.driveControl();
		//��������
		NumericalFactory.getInstance().paint(canvas, paint);
		/*��Ч����*/
		VisualEffectsManager.getInstance().paint(this.activity, canvas, paint);
		/*����WarnningManager*/
		WarnningManager.getInstance().paint(canvas, paint);
		
		this.paintInfoBar(canvas, paint);
	}
	
	public void paintWuJiang(Canvas canvas, Paint paint)
	{
		/*ֻ�п���ʱ��ʾ*/
		if(!Battle.visInfoBar)
			return;
		
		/*����vecHome*/
		for(int i = 0; i < this.vecHome.size(); i ++)
		{
			this.vecHome.get(i).paint(canvas, paint);
		}
		/*����vecGuest*/
		for(int i = 0; i < this.vecGuest.size(); i ++)
		{
			this.vecGuest.get(i).paint(canvas, paint);
		}
		for(int i = 0; i < this.vecHome.size(); i ++)
		{
			this.vecHome.get(i).checkAction();
		}
		for(int i = 0; i < this.vecGuest.size(); i ++)
		{
			this.vecGuest.get(i).checkAction();
		}
	}
	
	public void paintInfoBar(Canvas canvas, Paint paint)
	{
		/*ֻ�п���ʱ��ʾ*/
		if(!Battle.visInfoBar)
			return;
		
		//5����ɫ�׿�
		paint.setAlpha(85);
		for(int i = 0; i < 2; i ++)
		{
			canvas.drawRect(25, 48 + i * 30, Const.SCREEN_W - 22, 45 + i * 30 + 24, paint);
		}
		paint.reset();
		//
		canvas.drawBitmap(this.bmp_state, 22, 0, paint);
		//zhanyi
		canvas.drawBitmap(this.bmp_zhanyi, Const.SCREEN_W /2 - this.bmp_zhanyi.getWidth() / 2, 0, paint);
		//blood_home
		canvas.save();
		canvas.scale(208.0f, 1.0f, 140, 20);
		canvas.drawBitmap(this.bmp_blood_home, 140, 20, paint);
		canvas.restore();
	    canvas.save();
		canvas.scale(208.0f, 1.0f, 452, 20);
		canvas.drawBitmap(this.bmp_blood_home, 452, 20, paint);
		canvas.restore();
		//����detail
		this.group_HomeDetail.paint(canvas, paint);
		this.group_GuestDetail.paint(canvas, paint);
		//vs
		paint.setTypeface(activity.typeFace);
		paint.setTextSize(54);
		paint.setColor(Color.rgb(204, 178, 121));
		canvas.drawText("��", 370, 120, paint);
		paint.setTextSize(35);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("ս��", 400, 43, paint);
		paint.reset();
		paint.setAntiAlias(true);
	}
	
	/*ս���Ƿ��Ѿ�������˫���佫���͸�λ��*/
	public boolean isBattleReady()
	{
		for(int i = 0; i < this.vecHome.size(); i ++)
		{
			if(this.vecHome.get(i).getStage() != Const.EleBattle_Battle)
				return false;
		}
		for(int i = 0; i < this.vecGuest.size(); i ++)
		{
			if(this.vecGuest.get(i).getStage() != Const.EleBattle_Battle)
				return false;
		}
		return true;
	}
	public void driveControl()
	{
		//˫���佫׼����λ�󣬲��ܿ�ʼ������������
		if(!this.isBattleReady())
			return;
		//������һ����������Ҫ�����򿪺���ܼ�������
		if(BattleLogic.getInstance().getLockDrive())
			return;
		this.aniDrive();
	}
	/*���������¼�*/
	public void aniDrive()
	{
		LinkedList<LinkedList<String>> content = BattleLogic.getInstance().getContent();
		int idxDrive = BattleLogic.getInstance().getIdxDrive();
		int attackTeam =   Integer.parseInt(content.get(idxDrive).get(BattleLogic.AniDrive_AttackTeam));
		int targetTeam =   Integer.parseInt(content.get(idxDrive).get(BattleLogic.AniDrive_TargetTeam));
		int attackerId =   Integer.parseInt(content.get(idxDrive).get(BattleLogic.AniDrive_AttackerId));
		int targeterId =     Integer.parseInt(content.get(idxDrive).get(BattleLogic.AniDrive_TargetId));
		int attackValue = Integer.parseInt(content.get(idxDrive).get(BattleLogic.AniDrive_AttackValue));
		String info = content.get(idxDrive).get(BattleLogic.AniDrive_Info);

		//��ȡ��attackTeam & targetTeam
	    LinkedList<com.element.EleBattle> attTeam = null;
	    LinkedList<com.element.EleBattle> tagTeam = null;
		
		if(attackTeam == BattleLogic.HomeTeam)
			attTeam = this.vecHome;
		else
			attTeam = this.vecGuest;
		
		if(targetTeam == BattleLogic.HomeTeam)
			tagTeam = this.vecHome;
		else
			tagTeam = this.vecGuest;
		
		com.element.EleBattle eleTag = tagTeam.get(targeterId);
		com.element.EleBattle eleAtt = attTeam.get(attackerId);
		
		eleAtt.getAni().setCurActionData(com.tool.Data.FRAME_DATA_ATTACK);
		
		if(targetTeam == BattleLogic.GuestTeam)
		{
			VisualEffectsManager.getInstance().active("battle/light.png", "eff_light", eleTag.getAni().getXpos() +  40, eleTag.getAni().getYpos() + 150, 5, 1, this.activity);
			VisualEffectsManager.getInstance().active("battle/blood.png", "eff_blood", eleTag.getAni().getXpos() + 120, eleTag.getAni().getYpos() + 130, 9, 1, this.activity);
			NumericalFactory.getInstance().active(eleTag.getAni().getXpos() + 120, eleTag.getAni().getYpos() + 100, attackValue, 1);
			
			Warnning warnning = new Warnning(info, 0x1743c1, 28, true, 1, true);
					 warnning.setYspeed(1.5f);//���������ƶ��ٶ�
			WarnningManager.getInstance().add(warnning);
		}else
		{
			VisualEffectsManager.getInstance().active("battle/light.png", "eff_light", eleTag.getAni().getXpos() +  40, eleTag.getAni().getYpos() + 150, 5, 1, this.activity);
			VisualEffectsManager.getInstance().active("battle/blood.png", "eff_blood", eleTag.getAni().getXpos() + 120, eleTag.getAni().getYpos() + 130, 9, 1, this.activity);
			NumericalFactory.getInstance().active(eleTag.getAni().getXpos() + 120, eleTag.getAni().getYpos() + 100, attackValue, 2);
			
			Warnning warnning = new Warnning(info, 0xac9901, 28, true, 1, true);
					 warnning.setYspeed(1.5f);//���������ƶ��ٶ�
			WarnningManager.getInstance().add(warnning);
		}
		
		//����������
		BattleLogic.getInstance().setLockDrive(true);
	}
	/*�����¼�����*/
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}