package com.element;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Element;
import com.tool.ElementGroup;

/*商店物品元素*/
public class EleItemShangDian implements Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;
	int idxFromGroup;
	
	public Btn btn_bg;
	
	public EleItemShangDian(ElementGroup eleGroup, int idxFromGroup, ShuguoActivity activity)
	{
		this.eleGroup = eleGroup;
		this.activity = activity;
		this.idxFromGroup = idxFromGroup;
		
		this.loadSource(activity);
	}
	/*加载资源*/
	public void loadSource(ShuguoActivity activity) {
		// TODO Auto-generated method stub
		/*背景按钮*/
		this.btn_bg = new Btn("element/bg_dj.png", 0, 0, null, activity);
		this.btn_bg.setProperties("SINGLE");
	}
	/*销毁资源*/
	public void destroyed() {
		// TODO Auto-generated method stub
		/*销毁背景按钮*/
		this.btn_bg.destroyed();
	}
	/*绘制资源*/
	public void paint(int x, int y, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		/*绘制界面背景*/
		this.btn_bg.setPosition(x,y);
		this.btn_bg.paint(canvas, paint);
		/*绘制物品名称*/
		paint.setFakeBoldText(true);
		paint.setTextSize(22);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("商品id:" + this.idxFromGroup, x+this.btn_bg.getWidth() / 2, y + 33, paint);
	}
	
	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}
	
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return this.btn_bg.getWidth();
	}
	public int getElementHeight() {
		// TODO Auto-generated method stub
		return this.btn_bg.getHeight();
	}
	
}
