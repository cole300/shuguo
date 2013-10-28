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
import com.tool.Btn;
import com.tool.Const;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class RenMing extends View{

	com.shu.ShuguoActivity activity;
	
	Paint paint;
	
	//����ͼ
	Bitmap bmp_bg;
	//elementȺ��
	ElementGroup eleGroup;

	Btn btn_exit;//�˳���ť
	Btn btnL, btnR;//���Ҽ�ͷ
	
	public RenMing(Context context, com.shu.ShuguoActivity activity)
	{
		super(context);
		
		this.setId(Const.ID_MAP_RenMing);
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		
		this.activity = activity;
		
		this.loadSource(activity);
	}
	/*��ʼ��elementȺ��*/
	public void initEleGroup()
	{
		this.eleGroup = new ElementGroup(80, 160, 5, 1, 5, 0);
		
		for(int i = 0; i < Player.getInstance().getWuJiang().size(); i ++)
		{
			this.eleGroup.add(new com.element.EleWuJiang2(Player.getInstance().getWuJiang().get(i), this.eleGroup, i, this.activity));
		}
	}
	public void loadSource(Activity activity)
	{
		//ʩ�ű���
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("ui/bg_dt.png", activity);
		//����eleGroupȺ��
		this.initEleGroup();
		//�������Ҽ�ͷ
		this.btnL = new Btn("btn/left.png", 0, 240, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", 740, 240, null, activity);
		this.btnR.setProperties("SINGLE");
		//�����˳���ť
		this.btn_exit = new Btn("btn/exit2.png", 730, 20, null, activity);
		this.btn_exit.setProperties("SINGLE");
	}
	
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		//����eleGroupȺ��
		this.eleGroup.destoryed();
		//�����˳���ť
		this.btn_exit.destroyed();
		//�������ҷ�ҳ��ť
		this.btnL.destroyed();
		this.btnR.destroyed();
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		//���Ʊ���
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		//��������
		paint.setTypeface(this.activity.typeFace);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(24);
		//����
		canvas.drawText("̫������", Const.SCREEN_W / 2, sy + 70, paint);
		canvas.drawRect(Const.SCREEN_W / 2 - 200, sy + 77, Const.SCREEN_W / 2 + 200, sy + 83, paint);
		//̫��
		paint.setColor(Color.rgb(11, 17, 28));
		paint.setTextSize(19);
		paint.setFakeBoldText(true);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTypeface(this.activity.typeFace);
		canvas.drawText("������ǰ����ӳ� 5%", 195, 135, paint);
		canvas.drawText("����Ԥ�ڶ���ӳ� 5%", 195, 155, paint);
		canvas.drawText("�ΰ���ǰ����ӳ� 5%", 420, 135, paint);
		canvas.drawText("�ΰ�Ԥ�ڶ���ӳ� 5%", 420, 155, paint);
		//eleGroupȺ��
		this.eleGroup.paint(canvas, paint);
		//���ҷ�ҳ��ť
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		//�����˳���ť
		this.btn_exit.paint(canvas, paint);
		//���ƾ���
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

			this.btn_exit.checkPressed(x, y, true);
			this.btnL.checkPressed(x, y, true);
			this.btnR.checkPressed(x, y, true);
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				//������µĸ�Ԫ��Ϊδѡ��״̬��������
				if(!((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getIsInView())
					continue;
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_renMing.checkPressed(x, y, true))
				{
					
				}
			}
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_bg.checkPressed(x, y, false)){}
			}
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.collidesWith(x, y))
			{
				this.releaseSource();
				activity.group.removeView(this);
			}else if(this.btnL.collidesWith(x, y))
			{
				this.eleGroup.previousPage();
			}else if(this.btnR.collidesWith(x, y))
			{
				this.eleGroup.nextPage();
			}
			
			for(int i = 0; i < this.eleGroup.getGroup().size(); i ++)
			{
				if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_renMing.collidesWith(x, y))
				{
					//������µĸ�Ԫ��Ϊδѡ��״̬��������
					if(!((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getIsInView())
						break;
					//���������佫
					Player.getInstance().setWj_RenMing(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).getWuJiang());
					//����Dating���水ťͷ��
					((Dating)activity.group.getViewById(Const.ID_MAP_DaTing)).update();
					//�رյ�ǰ����
					this.activity.group.removeView(this);
					break;
				}else if(((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).btn_bg.collidesWith(x, y))
				{
					//�ȳ�ʼ������Ϊδѡ��״̬
					for(int j = 0; j < this.eleGroup.getGroup().size(); j ++)
					{
						((com.element.EleWuJiang2)this.eleGroup.getGroup().get(j)).setIsInView(false);
					}
					//���ð��µ�Ϊѡ��״̬
					((com.element.EleWuJiang2)this.eleGroup.getGroup().get(i)).setIsInView(true);
					break;
				}
			}
			
			this.btn_exit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			this.eleGroup.closeScaling();
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}