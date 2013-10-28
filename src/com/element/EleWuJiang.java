package com.element;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.data.WuJiang;
import com.shu.ShuguoActivity;
import com.tool.Btn;
import com.tool.Element;
import com.tool.ElementGroup;
import com.tool.SourcePool;
import com.tool.Tool;

public class EleWuJiang implements Element{

	ShuguoActivity activity;
	ElementGroup eleGroup;//附属于这个
	Bitmap bmp_bg;
	
	String name="刘备";//武将名称
	Bitmap bmp_avatar;//头像
	Bitmap bmp_expBottom;//经验底条
	Bitmap bmp_exp;//经验条
	Bitmap bmp_icon_skill;//技能图标
	Bitmap bmp_bar_bing, bmp_bar_gong, bmp_bar_shou, bmp_bar_ti;
	
	public Btn btn_plus;//加行动力按钮
	public Btn btn_check;//查看详细信息按钮
	
	int idxFromGroup;
	
	WuJiang wuj;
	
	public EleWuJiang(WuJiang wujiang, ElementGroup eleGroup, int idxFromGroup, ShuguoActivity activity)
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
		this.bmp_bg = 		        SourcePool.getInstance().createAssetsBitmap("element/card_wj"+this.wuj.getQuality()+".png", activity);
		this.bmp_avatar =            SourcePool.getInstance().createAssetsBitmap("head/"+this.wuj.getHeadId()+".png", activity);
		this.bmp_expBottom =  SourcePool.getInstance().createAssetsBitmap("element/exp_bottom_wj.png", activity);
		this.bmp_exp =               SourcePool.getInstance().createAssetsBitmap("element/exp_wj.png", activity);
		this.bmp_icon_skill = SourcePool.getInstance().createAssetsBitmap("element/icon_skill_wj.png", activity);
		this.bmp_bar_bing =        SourcePool.getInstance().createAssetsBitmap("element/bar_bing.png", activity);
		this.bmp_bar_gong = 	   SourcePool.getInstance().createAssetsBitmap("element/bar_gong.png", activity);
		this.bmp_bar_shou = 	   SourcePool.getInstance().createAssetsBitmap("element/bar_shou.png", activity);
		this.bmp_bar_ti = 			 SourcePool.getInstance().createAssetsBitmap("element/bar_ti.png", activity);
		
		this.btn_check = new Btn("element/btn_check.png", 0, 0, null, activity);
		this.btn_check.setProperties("SINGLE");
		this.btn_check.setNameStyle("查看", Color.BLACK, 22, true);
		/*加行动力按钮*/
		this.btn_plus = new Btn("element/plus.png", 0, 0, null, activity);
		this.btn_plus.setProperties("SINGLE");
	}

	public void destroyed() {
		// TODO Auto-generated method stub
		Tool.getInstance().destroyed(this.bmp_bg);
		Tool.getInstance().destroyed(this.bmp_avatar);
		Tool.getInstance().destroyed(this.bmp_expBottom);
		Tool.getInstance().destroyed(this.bmp_exp);
		Tool.getInstance().destroyed(this.bmp_icon_skill);
		Tool.getInstance().destroyed(this.bmp_bar_bing);
		Tool.getInstance().destroyed(this.bmp_bar_gong);
		Tool.getInstance().destroyed(this.bmp_bar_shou);
		Tool.getInstance().destroyed(this.bmp_bar_ti);
		
		this.btn_check.destroyed();
		this.btn_plus.destroyed();
	}
	
	public void closeScaling() {
		// TODO Auto-generated method stub
		this.btn_check.setScaling(false);
		this.btn_plus.setScaling(false);
	}
	
	public void paint(int x, int y, Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		
		canvas.drawBitmap(this.bmp_bg, x, y, paint);
		/*姓名和底框*/
		paint.setColor(Color.WHITE);
		paint.setFakeBoldText(true);
		paint.setTextSize(19);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(this.name, x + 72, y + 30, paint);
		/*头像*/
		canvas.save();
		canvas.scale(0.5f, 0.5f, x - 20 + this.bmp_avatar.getWidth()/2, y  +this.bmp_avatar.getHeight()/2);
		canvas.drawBitmap(this.bmp_avatar, x - 20, y , paint);
		canvas.restore();
		/*经验条*/
		canvas.drawBitmap(this.bmp_expBottom, x - 3, y + 145, paint);
		/*技能图标*/
		for(int i = 0; i < 5; i ++)
		{
			Matrix matrix = new Matrix();
				   matrix.postScale(0.3f, 0.3f);
			Bitmap tempBMP = Bitmap.createBitmap(this.bmp_icon_skill, 0, 0, this.bmp_icon_skill.getWidth(), this.bmp_icon_skill.getHeight(), matrix, true);
			canvas.drawBitmap(tempBMP, x + 12 + (i*24), y + 160, null);
		}
		/*兵，攻，守，体栏*/
		canvas.drawBitmap(this.bmp_bar_gong, x + 12, y + 185, paint);
		canvas.drawBitmap(this.bmp_bar_ti,   x + 12, y + 202, paint);
		canvas.drawBitmap(this.bmp_bar_shou, x + 72, y + 185, paint);
		canvas.drawBitmap(this.bmp_bar_bing, x + 72, y + 202, paint);
		/*兵，攻，守，体内容*/
		paint.setColor(Color.RED);
		paint.setFakeBoldText(true);
		paint.setTextSize(16);
		paint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText(String.valueOf(     this.wuj.getAttack()), x + 32, y + 198, paint);
		canvas.drawText(String.valueOf(    this.wuj.getDefense()), x + 92, y + 198, paint);
		canvas.drawText(String.valueOf(  		this.wuj.getHp()), x + 32, y + 215, paint);
		canvas.drawText(String.valueOf(this.wuj.getWarrior_sum()), x + 92, y + 215, paint);
		/*行动力*/
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		paint.setTextAlign(Paint.Align.LEFT);
		canvas.drawText("行动 "+this.wuj.getCurPhysical()+"/"+this.wuj.getMaxPhysical(), x + 12, y + 235, paint);
		/*策略*/
		canvas.drawText("策略 全体出击", x + 12, y + 255, paint);
		/*状态*/
		canvas.drawText("状态 出击", x + 12, y + 275, paint);
		/*查看按钮*/
		this.btn_check.setPosition(x + 25, y + 280);
		this.btn_check.paint(canvas, paint);
		this.btn_plus.setPosition(x + 85, y + 218);
		this.btn_plus.paint(canvas, paint);
	}

	public WuJiang getWuJiang()
	{
		return this.wuj;
	}
	
	public int getElementWidth() {
		// TODO Auto-generated method stub
		return this.bmp_bg.getWidth();
	}

	public int getElementHeight() {
		// TODO Auto-generated method stub
		return this.bmp_bg.getHeight();
	}

}
