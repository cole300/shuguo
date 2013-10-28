package com.tool;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.shu.ShuguoActivity;


/*ElementGroup����Ԫ��<��ӷ�ʽ:ElementGroup.add(this)>*/
public interface Element {
	
	/*������Դ*/
	public void loadSource(ShuguoActivity activity);
	/*�ͷ���Դ*/
	public void destroyed();
	/*����*/
	public void paint(int x, int y, Canvas canvas, Paint paint);
	/*�رհ�ťscaling*/
	public void closeScaling();
	/*��ȡԪ�صĵ�ͼ���*/
	public int getElementWidth();
	/*��ȡԪ�صĵ�ͼ�߶�*/
	public int getElementHeight();
	
}