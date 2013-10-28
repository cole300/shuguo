package com.tool;

import java.util.LinkedList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.element.GGNode;

public class NodeManager {

	public LinkedList<GGNode> vecNodes;
	
	public static int degree = 0;//旋转角度
	
	private int xPos = 0, yPos = 0;
	
	private int idxFrame = 0;//当前帧
	private int delayFrame = 0;
	private int DELAY = 8;
	private float scaleLev;
	private int anchor;//绘制方向<ANCHOR_NORMAL><ANCHOR_MIRROR>
	
	Activity activity;
		
	private int[][][] curActionData = Data.FRAME_DATA_STAND;
	
	/**
	 * @param scaleLev 动画缩放等级
	 * @param activity 
	 */
	public NodeManager(float scaleLev, int anchor, Activity activity)
	{
		this.activity = activity;
		this.vecNodes = new LinkedList<GGNode>();
		//设置缩放等级
		this.setScaleLev(scaleLev);
		//设置绘制方向
		this.setAnchor(anchor);
		//初始化节点信息
		for(int i = 0 ; i < 20; i ++)
		{
			this.vecNodes.add(new GGNode(this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X],
										 this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y],
										 this.curActionData[this.idxFrame][i][Const.FRAMEDATA_DEGREE],
										 this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X_ORIGIN] + this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X] + this.xPos,
										 this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y_ORIGIN] + this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y] + this.yPos,
										 this.curActionData[this.idxFrame][i][Const.FRAMEDATA_BMP_ID],
										 this.vecNodes,
										 activity
										 ));
		}
	}
	
	public void setPosition(int x, int y)
	{
		this.xPos = x;
		this.yPos = y;
	}
	public void move(int x_moves, int y_moves)
	{
		this.xPos += x_moves;
		this.yPos += y_moves;
	}
	public int getXpos()
	{
		return this.xPos;
	}
	public void setXpos(int x)
	{
		this.xPos = x;
	}
	public int getYpos()
	{
		return this.yPos;
	}
	public void setYpos(int y)
	{
		this.yPos = y;
	}
	public int getAnchor()
	{
		return this.anchor;
	}
	public void setAnchor(int anchor)
	{
		this.anchor = anchor;
	}
	public float getScaleLev()
	{
		return this.scaleLev;
	}
	public void setScaleLev(float scaleLev)
	{
		this.scaleLev = scaleLev;
	}
	/*添加子节点*/
	public void add(GGNode node)
	{
		this.vecNodes.add(node);
	}
	/*返回子节点*/
	public GGNode getByIndex(int index)
	{
		return this.vecNodes.get(index);
	}
	/*设置当前动作帧序列*/
	public void setCurActionData(int[][][] data)
	{
		this.idxFrame = 0;
		this.curActionData = data;
	}
	/*获取当前动作帧序列*/
	public int[][][] getCurActionData()
	{
		return this.curActionData;
	}
	/*获取当前帧*/
	public int getIdxFrame()
	{
		return this.idxFrame;
	}
	public void setDelay(int value)
	{
		this.DELAY = value;
	}
	public int getDelay()
	{
		return this.DELAY;
	}
	/*注销*/
	public void destroyed()
	{
		for(int i = 0; i < this.vecNodes.size(); i ++)
		{
			this.vecNodes.get(i).destroyed();
		}
		this.vecNodes.clear();
	}
	/*更新所有节点信息*/
	public void update()
	{
		for(int i = 0; i < this.vecNodes.size(); i ++)
		{
			this.vecNodes.get(i).update(this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X],
										this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y],
										this.curActionData[this.idxFrame][i][Const.FRAMEDATA_DEGREE],
										this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X_ORIGIN] + this.curActionData[this.idxFrame][i][Const.FRAMEDATA_X] + this.getXpos(),
										this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y_ORIGIN] + this.curActionData[this.idxFrame][i][Const.FRAMEDATA_Y] + this.getYpos());
		}
	}
	/**换骨骼形象
	 * @type 装备类型<衣服><武器><战马><头盔>
	 * @styleId 风格类型<风格文件夹内的>
	 * */
	public void changeGuGe(int type, int styleId, Activity activity)
	{
		switch(type)
		{
		case 0://换衣服(0,1,3,8,9,10)
			this.changeNodeImg( 1, styleId);
			this.changeNodeImg( 8, styleId);
			this.changeNodeImg( 9, styleId);
			break;
		case 1://换武器(2)
			this.changeNodeImg( 3, styleId);
			break;
		case 2://换驴(7，6,11,12,13,14,15)
			this.changeNodeImg( 4, styleId);
			this.changeNodeImg( 7, styleId);
			this.changeNodeImg(11, styleId);
			this.changeNodeImg(12, styleId);
			this.changeNodeImg(13, styleId);
			this.changeNodeImg(14, styleId);
			this.changeNodeImg(15, styleId);
			this.changeNodeImg(16, styleId);
			this.changeNodeImg(17, styleId);
			this.changeNodeImg(18, styleId);
			this.changeNodeImg(19, styleId);
			this.changeNodeImg(20, styleId);
			break;
		case 3://换头盔(4)
			this.changeNodeImg(5, styleId);
			break;
		}
	}
	
	public void changeNodeImg(int picId, int styleId)
	{
		for(int i = 0; i < this.vecNodes.size(); i ++)
		{
			if(this.vecNodes.get(i).getId() == picId)
			{
				this.vecNodes.get(i).destroyed();
				this.vecNodes.get(i).setBmp(SourcePool.getInstance().createAssetsBitmap("ani/"+styleId+"/"+picId+".png", activity));
				
				if(this.vecNodes.get(i).getBmp() == null)
				{
					this.vecNodes.get(i).setBmp(SourcePool.getInstance().createAssetsBitmap("ani/-1/"+picId+".png", activity));
				}
			}
		}
	}
	
	/*绘制manager容器资源*/
	public void paint(Canvas canvas, Paint paint)
	{
		canvas.save();
		canvas.scale(this.scaleLev, this.scaleLev, this.xPos + 100,  this.yPos + 100);
		
		for(int i = 0; i < this.vecNodes.size(); i ++)
		{
			  this.vecNodes.get(i).paintMid(this.getXpos(), this.vecNodes.get(i).getXpaint(),
			 		 					    this.getYpos(), this.vecNodes.get(i).getYpaint(),
						 				    this.getAnchor(), canvas, paint);
		}
		canvas.restore();
		
		if(this.delayFrame < DELAY)
		{
			this.delayFrame ++;
		}else
		{
			this.delayFrame = 0;
			this.idxFrame ++;
			if(this.idxFrame >= this.getCurActionData().length)
			{
				if(this.getCurActionData() == Data.FRAME_DATA_ATTACK)
				{
					this.setCurActionData(Data.FRAME_DATA_STAND);
				}else
				{
					this.setCurActionData(this.getCurActionData());
				}
			}
			this.update();
		}
	}
}
