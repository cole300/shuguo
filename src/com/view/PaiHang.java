package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.BtnGroup;
import com.tool.Const;
import com.tool.SourcePool;
import com.tool.Tool;

public class PaiHang extends View{

	Paint paint;
	ShuguoActivity activity;
	
	Btn btn_exit;
	Btn btnL, btnR;//���Ҽ�ͷ
	
	BtnGroup btnGroup;//buttonȺ��
	Bitmap bmp_btnGroup;
	
	Bitmap bmp_bg;
	Bitmap bmp_bg_lev;//�ȼ�����
	Bitmap bmp_bg_content;//���ݱ���
	Bitmap bmp_bottom_lev;
	Bitmap bmp_bottom_content;
	
	public PaiHang(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.activity = activity;
		
		this.loadSource(activity);
		
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	/*��ʼ��buttonȺ��*/
	public void initBtnGroup()
	{
		this.btnGroup = new BtnGroup(0, 82, 85);
		this.btnGroup.add("element/btn1_wj.png", "������", activity);
		this.btnGroup.add("element/btn1_wj.png", "������", activity);
		this.btnGroup.add("element/btn1_wj.png", "������", activity);
		this.btnGroup.add("element/btn1_wj.png", "���ְ�", activity);
		this.btnGroup.getGroup().get(0).setBmp(this.btnGroup.bmp_btn);
	}
	/*������Դ*/
	public void loadSource(Activity activity)
	{
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("element/bg_wj.png", activity);
		
		this.btn_exit = new Btn("element/exit.png", 730, 20, null, activity);
		this.btn_exit.setProperties("SINGLE");
		/*��ʼ�����Ҽ�ͷ*/
		this.btnL = new Btn("btn/left.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 216, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 248, null, activity);
		this.btnL.setProperties("SINGLE");
		this.btnR = new Btn("btn/right.png", Const.SCREEN_W/2 - this.bmp_bg.getWidth()/2 + 675, Const.SCREEN_H/2 - this.bmp_bg.getHeight()/2 + 248, null, activity);
		this.btnR.setProperties("SINGLE");
		
		/*����buttonȺ��*/
		this.initBtnGroup();
		/*����buttonȺ�鱳��ͼ*/
		this.bmp_btnGroup = SourcePool.getInstance().createAssetsBitmap("element/item_13.png", activity);
		/*���صȼ�����*/
		this.bmp_bg_lev = SourcePool.getInstance().createAssetsBitmap("element/bg_lev_ph.png", activity);
		/*�������ݱ���*/
		this.bmp_bg_content = SourcePool.getInstance().createAssetsBitmap("element/bg_content_ph.png", activity);
		/*���صȼ���ͼ*/
		this.bmp_bottom_lev = SourcePool.getInstance().createAssetsBitmap("element/lev_bottom_ph.png", activity);
		/*�������ݵ�ͼ*/
		this.bmp_bottom_content = SourcePool.getInstance().createAssetsBitmap("element/content_bottom_ph.png", activity);
	}
	/*������Դ*/
	public void releaseResource()
	{
		Tool.getInstance().destroyed(this.bmp_bg);
		
		this.btn_exit.destroyed();
		/*���Ҽ�ͷ*/
		this.btnL.destroyed();
		this.btnR.destroyed();
		/*����Ⱥ��*/
		this.btnGroup.destroyed();
		/*����buttonȺ�鱳��ͼ*/
		Tool.getInstance().destroyed(this.bmp_btnGroup);
		/*�ȼ�����*/
		Tool.getInstance().destroyed(this.bmp_bg_lev);
		/*���ݱ���*/
		Tool.getInstance().destroyed(this.bmp_bg_content);
		/*�ȼ���ͼ*/
		Tool.getInstance().destroyed(this.bmp_bottom_lev);
		/*���ݵ�ͼ*/
		Tool.getInstance().destroyed(this.bmp_bottom_content);
	}
	/*������Դ*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		/*relative position*/
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth()  / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		/*����*/
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		/*����title*/
		paint.setColor(Color.BLACK);
		paint.setFakeBoldText(true);
		paint.setTextSize(28);
		canvas.drawText("���а�", sx + 38, sy + 46, paint);
		/*����buttonȺ�鱳��ͼ*/
		canvas.drawBitmap(this.bmp_btnGroup, sx + 65, sy + 72, paint);
		/*����buttonȺ��*/
		this.btnGroup.paint(canvas, paint);
		/*�ȼ�����*/
		canvas.drawBitmap(this.bmp_bg_lev, sx + 60, sy + 118, paint);
		/*�ȼ���ͼ*/
		canvas.drawBitmap(this.bmp_bottom_lev, sx + 66, sy + 125, paint);
		/*���ݱ���*/
		canvas.drawBitmap(this.bmp_bg_content, sx + 248, sy + 118, paint);
		/*���ݵ�ͼ*/
		canvas.drawBitmap(this.bmp_bottom_content, sx + 288, sy + 130, paint);
		/*�������Ұ�ť*/
		this.btnL.paint(canvas, paint);
		this.btnR.paint(canvas, paint);
		
		this.btn_exit.paint(canvas, paint);
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
			this.btnGroup.checkPressed(x, y);
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.getRect().contains(x, y))
			{
				activity.group.removeView(this);
			}
			
			this.btn_exit.setScaling(false);
			this.btnL.setScaling(false);
			this.btnR.setScaling(false);
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
