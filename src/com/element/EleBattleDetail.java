package com.element;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Const;
import com.tool.Element;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class EleBattleDetail implements Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;
	int idxFromGroup;
	int dir;
	Bitmap bmp_avatar;
	Bitmap bmp_iconArmy;
	
	private com.data.WuJiang wj;
	
	public EleBattleDetail(com.data.WuJiang wj, ElementGroup eleGroup, int idxFromGroup, int dir, ShuguoActivity activity)
	{
		this.eleGroup = eleGroup;
		this.activity = activity;
		this.idxFromGroup = idxFromGroup;
		this.dir = dir;
		this.wj = wj;
		
		this.bmp_avatar = SourcePool.getInstance().createAssetsBitmap("battle_avatar/"+wj.getId()+".png", activity);
		
		if(dir == Const.ANCHOR_NORMAL)
		    this.bmp_iconArmy = SourcePool.getInstance().createAssetsBitmap("battle/icon_1.png", activity);
		else
			this.bmp_iconArmy = SourcePool.getInstance().createAssetsBitmap("battle/icon_1s.png", activity);
		
		this.loadSource(activity);
	}
	/*加载资源*/
	public void loadSource(ShuguoActivity activity) {
		// TODO Auto-generated method stub
	}
	/*销毁资源*/
	public void destroyed() {
		// TODO Auto-generated method stub
		Tool.getInstance().destroyed(this.bmp_avatar);
		Tool.getInstance().destroyed(this.bmp_iconArmy);
	}
	/*绘制资源*/
	public void paint(int x, int y, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		//detail
		paint.setAntiAlias(true);
		paint.setTypeface(this.activity.typeFace);
		paint.setColor(Color.rgb(245, 240, 230));
		paint.setTextAlign(Paint.Align.LEFT);
		paint.setTextSize(18);
		
		switch(this.dir)
		{
		case Const.ANCHOR_NORMAL:
			 canvas.drawBitmap(this.bmp_avatar, x, y, paint);
			 canvas.drawBitmap(this.bmp_iconArmy, x + this.bmp_avatar.getWidth() - this.bmp_iconArmy.getWidth()/2,  y - this.bmp_iconArmy.getHeight()/2, paint);
			 canvas.drawText("90412/100000", x + 80, y + 20, paint);
			 paint.setColor(Color.WHITE);
			 paint.setTextAlign(Paint.Align.RIGHT);
			 paint.setTextSize(17);
			 canvas.drawText("Lev"+this.wj.getLev(), x + this.bmp_avatar.getWidth()/2, y + this.bmp_avatar.getHeight(), paint);
			 break;
		case Const.ANCHOR_MIRROR:
			 canvas.drawBitmap(this.bmp_avatar, x + 240, y, paint);
			 canvas.drawBitmap(this.bmp_iconArmy, x + 240 - this.bmp_iconArmy.getWidth()/2,  y - this.bmp_iconArmy.getHeight()/2, paint);
			 paint.setTextAlign(Paint.Align.RIGHT);
			 canvas.drawText("90412/100000", x + 210, y + 20, paint);
			 paint.setColor(Color.WHITE);
			 paint.setTextAlign(Paint.Align.LEFT);
			 paint.setTextSize(17);
			 canvas.drawText("Lev"+this.wj.getLev(), x + 240 + this.bmp_avatar.getWidth()/2, y + this.bmp_avatar.getHeight(), paint);
			 break;
		}
		
		paint.reset();
		paint.setAntiAlias(true);
	}
	
	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}
	
	public com.data.WuJiang getWuJiang()
	{
		return this.wj;
	}
	
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return 350;
	}
	public int getElementHeight() {
		// TODO Auto-generated method stub
		return this.bmp_avatar.getHeight();
	}
	
}
