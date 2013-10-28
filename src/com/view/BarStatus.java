package com.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.shu.ShuguoActivity;
import com.tool.Const;
import com.tool.FolderBtnGroup;
import com.tool.SourcePool;
import com.tool.Tool;
import com.tool.WarnningManager;

/*����״̬��*/
public class BarStatus extends View{

	Map map;
	
	Paint paint;
	
	Bitmap bmp_head_bar;//ͷ�񱳾���
	FolderBtnGroup btnGroup;//��ť����ͼ��

	ShuguoActivity activity;
	
	public BarStatus(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.setFocusable(true);
		
		this.setId(Const.ID_MAP_Map);
		
		this.activity = activity;
		
		paint = new Paint();
		paint.setAntiAlias(true);
		/*������Դ*/
		this.loadSource(activity);
		
		map = new Map(activity);
	}
	
	/*������Դ*/
	public void loadSource(Activity activity)
	{
		/*����ͷ��*/
		this.bmp_head_bar = SourcePool.getInstance().createAssetsBitmap("status/headbar.png", activity);
		/*���ͼ���ܰ�ť��*/
		this.btnGroup = new FolderBtnGroup("status/open.png", Const.SCREEN_W - 100, Const.SCREEN_H - 80, activity);
		this.btnGroup.addBtn(   "status/beibao.png",    "beibao", activity);
		this.btnGroup.addBtn(  "status/jianshe.png",   "jianshe", activity);
		this.btnGroup.addBtn(  "status/paihang.png",   "paihang", activity);
		this.btnGroup.addBtn("status/shangdian.png", "shangdian", activity);
		this.btnGroup.addBtn(  "status/wujiang.png",   "wujiang", activity);
	}
	/*�ͷ���Դ*/
	public void releaseSource()
	{
		Tool.getInstance().destroyed(this.bmp_head_bar);
		
		this.btnGroup.destroyed();
	}
	/*������Դ*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	
		this.map.paint(canvas, paint);
		canvas.drawBitmap(bmp_head_bar, 100, 10, paint);
		/*���ƴ��ͼ�Ϲ��ܰ�ť��*/
		this.btnGroup.paint(canvas, paint);
		/*���ƾ���Ⱥ��*/
		WarnningManager.getInstance().paint(canvas, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float xpos = event.getX();
		float ypos = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			this.btnGroup.checkPressed(xpos, ypos);
			break;
		case MotionEvent.ACTION_UP:
			
			for(int i = 1; i < this.btnGroup.getGroupSize(); i ++)
			{
				if(this.btnGroup.getGroup().get(i).isOpenClosing())
					continue;
				if(this.btnGroup.getGroup().get(i).collidesWith(xpos, ypos))
				{
					if(this.btnGroup.getGroup().get(i).getname().equals("wujiang"))
					{
						WuJiang wj = new WuJiang(activity.getApplicationContext(), activity);
						activity.group.addView(wj);
					}else if(this.btnGroup.getGroup().get(i).getname().equals("beibao"))
					{
						Battle bb = new Battle(activity.getApplicationContext(), activity);
						//BeiBao bb = new BeiBao(activity.getApplicationContext(), activity);
						activity.group.addView(bb);
					}else if(this.btnGroup.getGroup().get(i).getname().equals("shangdian"))
					{
						ShangDian sd = new ShangDian(activity.getApplicationContext(), activity);
						activity.group.addView(sd);
					}else if(this.btnGroup.getGroup().get(i).getname().equals("paihang"))
					{
						PaiHang ph = new PaiHang(activity.getApplicationContext(), activity);
						activity.group.addView(ph);
					}else if(this.btnGroup.getGroup().get(i).getname().equals("jianshe"))
					{
//						WarnningManager.getInstance().add(new Warnning("�ݲ����Ŵ˹���", Color.GREEN, 30, true, 2, true));
						
//						Battle battle = new Battle(activity.getApplicationContext(), activity);
//						activity.group.addView(battle);
						
						Dating status = new Dating(activity.getApplicationContext(), activity);
						activity.group.addView(status);
						
//						BattleBoard board = new BattleBoard(activity.getApplicationContext(), activity);
//						activity.group.addView(board);
					}
				}
			}
			
			this.btnGroup.checkOpenClose(xpos, ypos);
			this.btnGroup.closeScaling();
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
//		return true;
		return map.scaleGesture.onTouchEvent(event);
	}
}