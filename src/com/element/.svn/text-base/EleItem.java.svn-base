package com.element;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Element;
import com.tool.ElementGroup;

public class EleItem implements Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;
	int idxFromGroup;
	
	public Btn btn_bg;
	
	private com.data.Item item;
	
	private float default_scaleX, default_scaleY;//默认缩放等级
	private boolean isShowNum;//是否显示数量
	
	public EleItem(com.data.Item item, ElementGroup eleGroup, int idxFromGroup, float scaleX, float scaleY,  boolean isShowNum, ShuguoActivity activity)
	{
		this.eleGroup = eleGroup;
		this.activity = activity;
		this.idxFromGroup = idxFromGroup;
		
		this.default_scaleX = scaleX;
		this.default_scaleY = scaleY;
		this.isShowNum = isShowNum;
		
		this.item = item;
		
		this.loadSource(activity);
	}
	/*加载资源*/
	public void loadSource(ShuguoActivity activity) {
		// TODO Auto-generated method stub
		/*背景按钮*/
		if(this.item.getPicId() != -1)
			this.btn_bg = new Btn("equip/"+this.item.getPicId()+".png", 0, 0, null, activity);
		else
			this.btn_bg = new Btn("element/icon_skill_wj.png", 0, 0, null, activity);
		
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
		/*绘制item*/
		this.btn_bg.setPosition(x,y);
		this.btn_bg.paintScales(this.default_scaleX, this.default_scaleY, canvas, paint);
		/*绘制item数量*/
		if(!this.isShowNum)
			return;
		paint.setColor(Color.BLUE);
		paint.setTextSize(19);
		paint.setTextAlign(Paint.Align.RIGHT);
		paint.setFakeBoldText(true);
		canvas.drawText(String.valueOf(this.item.getNum()), x + 89, y + 75, paint);
	}
	
	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}
	
	public com.data.Item getItem()
	{
		return this.item;
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
