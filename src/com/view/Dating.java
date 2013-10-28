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
import com.tool.SourcePool;
import com.tool.Tool;

public class Dating extends View{

	com.shu.ShuguoActivity activity;
	Paint paint;
	
	//背景图
	Bitmap bmp_bg;
	//退出按钮
	com.tool.Btn btn_exit;
	//任命按钮
	com.tool.Btn btn_renMing;
	//人口
	com.tool.Btn btn_renKou;
	com.tool.Btn btn_junXiang;
	com.tool.Btn btn_junLiang;
	com.tool.Btn btn_shangYe;
	com.tool.Btn btn_nongYe;
	com.tool.Btn btn_zhiAn;
	//任命头像
	Bitmap bmp_rmHead;
	
	public Dating(Context context, com.shu.ShuguoActivity activity)
	{
		super(context);
		
		this.setId(Const.ID_MAP_DaTing);
		
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
		
		this.activity = activity;
		
		this.loadSource(activity);
	}
	
	public void loadSource(Activity activity)
	{
		//创建背景
		this.bmp_bg = SourcePool.getInstance().createAssetsBitmap("ui/bg_dt.png", activity);
		//创建退出按钮
		this.btn_exit = new Btn("btn/exit2.png", 695, 55, null, activity);
		this.btn_exit.setProperties("SINGLE");
		//创建任命按钮
		this.btn_renMing = new Btn("btn/renming.png", 255, 120, null, activity);
		this.btn_renMing.setNameStyle("任命", Color.rgb(204, 178, 121), 27, true);
		this.btn_renMing.setProperties("SINGLE");
		//人口
		this.btn_renKou = new Btn("btn/data_bar.png", 90, 230, null, activity);
		this.btn_renKou.setNameStyle("人口：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_renKou.setProperties("SINGLE");
		//军饷
		this.btn_junXiang = new Btn("btn/data_bar.png", 90, 295, null, activity);
		this.btn_junXiang.setNameStyle("军饷：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_junXiang.setProperties("SINGLE");
		//军粮
		this.btn_junLiang = new Btn("btn/data_bar.png", 90, 360, null, activity);
		this.btn_junLiang.setNameStyle("军粮：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_junLiang.setProperties("SINGLE");
		//商业
		this.btn_shangYe = new Btn("btn/data_bar.png", 410, 230, null, activity);
		this.btn_shangYe.setNameStyle("商业：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_shangYe.setProperties("SINGLE");
		//农业
		this.btn_nongYe = new Btn("btn/data_bar.png", 410, 295, null, activity);
		this.btn_nongYe.setNameStyle("农业：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_nongYe.setProperties("SINGLE");
		//治安
		this.btn_zhiAn = new Btn("btn/data_bar.png", 410, 360, null, activity);
		this.btn_zhiAn.setNameStyle("治安：307092", Color.rgb(204, 178, 121), 27, true);
		this.btn_zhiAn.setProperties("SINGLE");
		//任命武将图片
		if(Player.getInstance().getWj_RenMing() != null)
		this.bmp_rmHead = SourcePool.getInstance().createAssetsBitmap("head/"+Player.getInstance().getWj_RenMing().getHeadId()+".png", activity);
	}
	
	public void releaseSource()
	{
		//释放背景
		Tool.getInstance().destroyed(this.bmp_bg);
		//释放按钮
		this.btn_exit.destroyed();
		this.btn_renMing.destroyed();
		this.btn_renKou.destroyed();
		this.btn_junXiang.destroyed();
		this.btn_junLiang.destroyed();
		this.btn_shangYe.destroyed();
		this.btn_nongYe.destroyed();
		this.btn_zhiAn.destroyed();
		//释放任命头像
		if(this.bmp_rmHead != null)
		{
			if(!this.bmp_rmHead.isRecycled())
			{
				this.bmp_rmHead.recycle();
				this.bmp_rmHead =  null;
			}
		}
	}
	
	public void update()
	{
		//刷新任命头像
		Tool.getInstance().destroyed(this.bmp_rmHead);
		this.bmp_rmHead = SourcePool.getInstance().createAssetsBitmap("head/"+Player.getInstance().getWj_RenMing().getHeadId()+".png", activity);
	}
	
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		int sx = Const.SCREEN_W / 2 - this.bmp_bg.getWidth() / 2;
		int sy = Const.SCREEN_H / 2 - this.bmp_bg.getHeight() / 2;
		//绘制背景
		canvas.drawBitmap(this.bmp_bg, sx, sy, paint);
		//设置字体
		paint.setTypeface(this.activity.typeFace);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setTextSize(24);
		//标题
		canvas.drawText("长安城内政详情", Const.SCREEN_W / 2, sy + 70, paint);
		canvas.drawRect(Const.SCREEN_W / 2 - 200, sy + 77, Const.SCREEN_W / 2 + 200, sy + 83, paint);
		//退出按钮
		this.btn_exit.paint(canvas, paint);
		this.btn_renMing.paint(canvas, paint);
		this.btn_renKou.paint(canvas, paint);
		this.btn_junXiang.paint(canvas, paint);
		this.btn_junLiang.paint(canvas, paint);
		this.btn_shangYe.paint(canvas, paint);
		this.btn_nongYe.paint(canvas, paint);
		this.btn_zhiAn.paint(canvas, paint);
		//任命武将头像
		if(this.bmp_rmHead != null)
		{
			canvas.save();
			canvas.scale(0.45f, 0.42f, 265, 130);
			canvas.drawBitmap(this.bmp_rmHead, 265, 130 , paint);
			canvas.restore();
		}
		//太守
		paint.setColor(Color.rgb(11, 17, 28));
		paint.setTextSize(24);
		paint.setFakeBoldText(true);
		paint.setTypeface(this.activity.typeFace);
		canvas.drawText("太守", 200, 180, paint);
		canvas.drawText("内政额外加成  0%", 390, 160, paint);
		canvas.drawText("治安额外加成  0%", 390, 195, paint);
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
			this.btn_renMing.checkPressed(x, y, false);
			
			break;
		case MotionEvent.ACTION_UP:
			
			if(this.btn_exit.collidesWith(x, y))
			{
				activity.group.removeView(this);
			}else if(this.btn_renMing.collidesWith(x, y))
			{
				RenMing view = new RenMing(this.activity.getApplicationContext(), this.activity);
				activity.group.addView(view);
			}
			
			this.btn_exit.setScaling(false);
			this.btn_renMing.setScaling(false);
			
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}
		
		return true;
	}
	
}
