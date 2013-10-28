package com.tool;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.shu.ShuguoActivity;


/*ElementGroup的子元素<添加方式:ElementGroup.add(this)>*/
public interface Element {
	
	/*加载资源*/
	public void loadSource(ShuguoActivity activity);
	/*释放资源*/
	public void destroyed();
	/*绘制*/
	public void paint(int x, int y, Canvas canvas, Paint paint);
	/*关闭按钮scaling*/
	public void closeScaling();
	/*获取元素的底图宽度*/
	public int getElementWidth();
	/*获取元素的底图高度*/
	public int getElementHeight();
	
}