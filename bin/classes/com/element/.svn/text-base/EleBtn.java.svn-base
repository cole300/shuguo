package com.element;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Element;
import com.tool.ElementGroup;

public class EleBtn implements Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;
	int idxFromGroup;
	private float default_scaleX, default_scaleY;
	
	public Btn btn;
	
	public EleBtn(ElementGroup eleGroup, int idxFromGroup, float scaleX, float scaleY, ShuguoActivity activity)
	{
		this.eleGroup = eleGroup;
		this.idxFromGroup = idxFromGroup;
		this.activity = activity;
		this.default_scaleX = scaleX;
		this.default_scaleY = scaleY;
		
		this.loadSource(activity);
	}
	
	public void loadSource(ShuguoActivity activity) {
		// TODO Auto-generated method stub
		this.btn = new Btn("element/btn3_wj.png", 0, 0, null, activity);
		this.btn.setProperties("SINGLE");
	}

	public void destroyed() {
		// TODO Auto-generated method stub
		this.btn.destroyed();
	}

	public void paint(int x, int y, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		this.btn.setPosition(x,y);
		this.btn.paint(canvas, paint);
	}

	public void closeScaling() {
		// TODO Auto-generated method stub
		
	}

	public int getElementWidth() {
		// TODO Auto-generated method stub
		return this.btn.getWidth();
	}

	public int getElementHeight() {
		// TODO Auto-generated method stub
		return this.btn.getHeight();
	}

	
	
}
