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

import com.shu.ShuguoActivity;
import com.tool.BattleLogic;
import com.tool.Const;
import com.tool.SourcePool;
import com.tool.Tool;
import com.tool.WarnningManager;

/*战斗结果统计面板*/
public class BattleBoard extends View{

	Paint paint;
	
	/*底图*/
	Bitmap bmp_bg_title;
	Bitmap bmp_bg_board;
	ShuguoActivity activity;
	
	public BattleBoard(Context context, ShuguoActivity activity)
	{
		super(context);
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		
		this.activity = activity;
		
		/*加载资源*/
		this.loadSource(activity);
	}
	
	/*加载资源*/
	public void loadSource(Activity activity)
	{
		/*底图*/
		this.bmp_bg_title = SourcePool.getInstance().createAssetsBitmap("battle/result.png", activity);
		this.bmp_bg_board = SourcePool.getInstance().createAssetsBitmap("battle/board.png", activity);
	}
	/*销毁资源*/
	public void releaseSource()
	{
		/*销毁底图*/
		Tool.getInstance().destroyed(this.bmp_bg_title);
		Tool.getInstance().destroyed(this.bmp_bg_board);
	}
	/*绘制资源*/
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/*绘制底图*/
		canvas.drawBitmap(this.bmp_bg_board, Const.SCREEN_W / 2 - this.bmp_bg_board.getWidth() / 2, 85, paint);
		canvas.drawBitmap(this.bmp_bg_title, Const.SCREEN_W / 2 - this.bmp_bg_title.getWidth() / 2, 10, paint);
		//"战斗结果"
		paint.setAntiAlias(true);
		paint.setTypeface(this.activity.typeFace);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(44);
		paint.setColor(Color.rgb(245, 240, 230));
		canvas.drawText("战斗结果", Const.SCREEN_W / 2, 75, paint);
		paint.setTextSize(50);
		canvas.drawText("点击屏幕确认", Const.SCREEN_W / 2, 420, paint);
		paint.setTextSize(34);
		canvas.drawText("攻击方", 120, 140, paint);
		canvas.drawText("守备方", 580, 140, paint);
		paint.setColor(Color.rgb(204, 178, 121));
		paint.setTextSize(30);
		canvas.drawText("战利品", Const.SCREEN_W / 2, 190, paint);
		canvas.drawText("经验", Const.SCREEN_W / 2, 244, paint);
		canvas.drawText("功勋", Const.SCREEN_W / 2, 298, paint);
		canvas.drawText("士兵伤亡", Const.SCREEN_W / 2, 352, paint);
		
		canvas.drawText("312580", 180, 242, paint);
		canvas.drawText("312580", 180, 296, paint);
		canvas.drawText("312580", 180, 350, paint);
		
		canvas.drawText("312580", 592, 242, paint);
		canvas.drawText("312580", 592, 296, paint);
		canvas.drawText("312580", 592, 350, paint);	
		
		paint.setTextSize(36);
		paint.setColor(Color.RED);
		canvas.drawText("胜利", 220, 140, paint);
		paint.setColor(Color.YELLOW);
		canvas.drawText("败北", 694, 140, paint);
		
		paint.reset();
		
		/*绘制标题*/
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setColor(Color.BLACK);
		paint.setTextSize(24);
//		canvas.drawText("战报", sx + this.bmp_bg.getWidth() / 2, sy + Tool.getInstance().getStrHeight(paint) - 5, paint);
		/*战斗结果*/
//		this.paintBattleResult(sx + this.bmp_bg.getWidth()/2, sy + 45, canvas, paint);
	}
	
	/*绘制战斗结果*/
	public void paintBattleResult(int x, int y, Canvas canvas, Paint paint)
	{
		paint.setTextSize(19);
		paint.setTextAlign(Paint.Align.CENTER);
		
		LinkedList<LinkedList<String>> content = BattleLogic.getInstance().getContent();
		for(int i = 0; i < content.size(); i ++)
		{
			if(i > BattleLogic.getInstance().getIdxDrive() &&  i != content.size() -1)
				break;
			canvas.drawText(content.get(i).get(BattleLogic.AniDrive_Info), x, y+Tool.getInstance().getStrHeight(paint) * i, paint);
		}
	}
	
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
			this.activity.group.removeView(this.activity.group.getChildAt(this.activity.group.getChildCount()-2));
			this.activity.group.removeView(this);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
