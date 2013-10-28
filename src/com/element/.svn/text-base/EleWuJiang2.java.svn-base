package com.element;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.data.WuJiang;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class EleWuJiang2 implements com.tool.Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;//附属于这个
//	Bitmap bmp_bg;
	
	String name="刘备";//武将名称
	Bitmap bmp_avatar;//头像
	
	public Btn btn_bg;//背景按钮
	public Btn btn_renMing;//查看详细信息按钮
	
	private boolean isInView = false;//查看状态
	
	int idxFromGroup;
	
	WuJiang wuj;
	
	public EleWuJiang2(WuJiang wujiang, ElementGroup eleGroup, int idxFromGroup, ShuguoActivity activity)
	{
		this.eleGroup = eleGroup;
		this.idxFromGroup = idxFromGroup;
		this.activity = activity;
		
		this.wuj = wujiang;
		this.loadSource(activity);
		/*武将名*/
		this.name = wujiang.getName();
	}
	
	public void loadSource(ShuguoActivity activity) {
		// TODO Auto-generated method stub
		//头像
		this.bmp_avatar = SourcePool.getInstance().createAssetsBitmap("head/"+this.wuj.getHeadId()+".png", activity);
		//任命按钮
		this.btn_renMing = new Btn("btn/btn_1.png", 0, 0, null, activity);
		this.btn_renMing.setProperties("SINGLE");
		this.btn_renMing.setNameStyle("任命", Color.WHITE, 22, true);
		//背景按钮
		this.btn_bg = new Btn("ui/btm"+this.wuj.getQuality()+".png", 0, 0, null, activity);
		this.btn_bg.setProperties("SINGLE");
	}

	public void destroyed() {
		// TODO Auto-generated method stub
		//销毁头像
		Tool.getInstance().destroyed(this.bmp_avatar);
		//销毁按钮
		this.btn_bg.destroyed();
		this.btn_renMing.destroyed();
	}
	
	public void closeScaling() {
		// TODO Auto-generated method stub
		this.btn_renMing.setScaling(false);
	}
	
	public void paint(int x, int y, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
		/*设置色彩矩阵*/
		this.setColorMatrix(paint);
		/*绘制背景*/
		this.btn_bg.setPosition(x, y);
		this.btn_bg.paint(canvas, paint);
		/*姓名和底框*/
		paint.setTypeface(this.activity.typeFace);
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
		paint.setTextSize(19);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.name, x + 60, y + 120, paint);
		/*三项数值<统率><谋略><政治>*/
		canvas.drawText("统率 97", x + 60, y + 140, paint);
		canvas.drawText("谋略 97", x + 60, y + 160, paint);
		canvas.drawText("政治 97", x + 60, y + 180, paint);
		/*头像*/
		canvas.save();
		canvas.scale(0.5f, 0.45f, x + 15, y + 15);
		canvas.drawBitmap(this.bmp_avatar, x + 15, y + 15 , paint);
		canvas.restore();
		/*兵，攻，守，体内容*/
//		canvas.drawText(String.valueOf(     this.wuj.getAttack()), x + 32, y + 198, paint);
		/*查看按钮*/
		paint.setTextAlign(Paint.Align.LEFT);
		this.btn_renMing.setPosition(x + 25, y + 200);
		this.btn_renMing.paint(canvas, paint);
		paint.setColorFilter(null);
	}
	/**设置彩色矩阵*/
	public void setColorMatrix(Paint paint)
	{
		if(this.isInView)
			return;
		
		android.graphics.ColorMatrix cMatrix = new android.graphics.ColorMatrix();
		cMatrix.setSaturation(0);
		android.graphics.ColorMatrixColorFilter cMatrixFilter = new android.graphics.ColorMatrixColorFilter(cMatrix);
		paint.setColorFilter(cMatrixFilter);
	}

	public WuJiang getWuJiang()
	{
		return this.wuj;
	}
	
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return this.btn_bg.getBmp().getWidth();
	}

	public int getElementHeight() {
		// TODO Auto-generated method stub
		return this.btn_bg.getBmp().getHeight();
	}
	
	
	/*Setter and Getter*/
	public void setIsInView(boolean is)
	{
		this.isInView = is;
	}
	
	public boolean getIsInView()
	{
		return this.isInView;
	}
	
}
