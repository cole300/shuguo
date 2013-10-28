package com.element;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.tool.SourcePool;
import com.tool.Tool;

/*骨骼节点*/
public class GGNode {

	private String str_Name;
	
	private Bitmap bmp_Node;
	
	private int id;//节点id
	
	private int xPaint, yPaint;//记录该节点挂靠父节点后的实际绘制坐标
	
	private int degree;//该节点转动角度
	private int xOrigin;//该节点转动原点x坐标
	private int yOrigin;//该节点转动原点y坐标
	
	private LinkedList<GGNode> vecNodes;
	
	/*构造独立node*/
	public GGNode(int x, int y, int degree, int rotateOriginX, int rotateOriginY, int url, LinkedList<GGNode> vecNodes, Activity activity)
	{
		this.id = url;
		this.xPaint = x;
		this.yPaint = y;
		this.bmp_Node = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+url+".png", activity);
		this.vecNodes = vecNodes;
		this.degree = degree;
		this.xOrigin = rotateOriginX;
		this.yOrigin = rotateOriginY;
	}
	/*更新节点信息*/
	public void update(int x, int y, int degree, int rotateOriginX, int rotateOriginY)
	{
		this.xPaint = x;
		this.yPaint = y;
		this.degree = degree;
		this.xOrigin = rotateOriginX;
		this.yOrigin = rotateOriginY;
	}
	/*Setter and Getter*/
	public void setXpaint(int x)
	{
		this.xPaint = x;
	}
	public int getXpaint()
	{
		return this.xPaint;
	}
	public void setYpaint(int y)
	{
		this.yPaint = y;
	}
	public int getYpaint()
	{
		return this.yPaint;
	}
	public String getName()
	{
		return this.str_Name;
	}
	public int getId()
	{
		return id;
	}
	public Bitmap getBmp()
	{
		return this.bmp_Node;
	}
	public void setBmp(Bitmap bmp)
	{
		this.bmp_Node = bmp;
	}
	/*注销node资源*/
	public void destroyed()
	{
		Tool.getInstance().destroyed(this.bmp_Node);
	}
	/*绘制node资源*/
	/**
	 * 
	 * @param x 绘制x坐标
	 * @param y 绘制y坐标
	 * @param canvas 
	 * @param paint
	 */
	public void paintMid(int x, int xPaint, int y, int yPaint, int anchor, Canvas canvas, Paint paint)
	{
		//xOrigin :旋转中心x坐标
		//yOrigin :旋转中心y坐标
		
	    Tool.getInstance().drawBitmapCenter(this.bmp_Node, x, xPaint, y, yPaint, this.degree, this.xOrigin, this.yOrigin, anchor, canvas, paint);
	}
}
