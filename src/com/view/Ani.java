package com.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.tool.Const;
import com.tool.SourcePool;
import com.tool.Tool;

public class Ani{

	/*图块数量*/
	private  final int SIZE_BLOCK = 21;
	/*图块图片数组*/
	Bitmap[] bmp_ani = new Bitmap[SIZE_BLOCK];
	
	int move_y1, move_y2, move_y3, move_y4;
	final int max_y = 2;
	boolean dir_1,dir_2,dir_3,dir_4;
	int count;
	int frameSpeed;
	private int xpos, ypos;//位置信息
	
	/*加载资源*/
	public void loadSource(Activity activity)
	{
		for(int i = 0; i < bmp_ani.length; i ++)
		{
			//头部和头盔合并为一个图块
			if(i == 5)
				continue;
			
			this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+(i+1)+".png", activity);
		}
	}
	/*释放资源*/
	public void releaseSource()
	{
		for(int i = 0; i < bmp_ani.length; i++)
		{
			Tool.getInstance().destroyed(this.bmp_ani[i]);
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
			for(int i = 0; i < bmp_ani.length; i ++)
			{
				if(i == 0 || i == 7 || i == 8)
				{
					Tool.getInstance().destroyed(this.bmp_ani[i]);
					this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/"+styleId+"/"+(i+1)+".png", activity);
					
					if(this.bmp_ani[i]==null)
					{
						this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+(i+1)+".png", activity);
					}
				}
			}
			break;
		case 1://换武器(2)
			for(int i = 0; i < bmp_ani.length; i ++)
			{
				if(i == 2)
				{
					Tool.getInstance().destroyed(this.bmp_ani[i]);
					this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/"+styleId+"/"+(i+1)+".png", activity);
					
					if(this.bmp_ani[i]==null)
					{
						this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+(i+1)+".png", activity);
					}
				}
			}
			break;
		case 2://换驴(7，6,11,12,13,14,15)
			for(int i = 0; i < bmp_ani.length; i ++)
			{//4,7,11,12
				if(i == 3 || i == 6 || i == 10 || i == 11 || i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i ==17 ||
				   i == 18 || i == 19)
				{
					Tool.getInstance().destroyed(this.bmp_ani[i]);
					this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/"+styleId+"/"+(i+1)+".png", activity);
					
					if(this.bmp_ani[i]==null)
					{
						this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+(i+1)+".png", activity);
					}
				}
			}
			break;
		case 3://换头盔(4)
			for(int i = 0; i < bmp_ani.length; i ++)
			{
				if(i == 4)
				{
					Tool.getInstance().destroyed(this.bmp_ani[i]);
					this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/"+styleId+"/"+(i+1)+".png", activity);
					
					if(this.bmp_ani[i]==null)
					{
						this.bmp_ani[i] = SourcePool.getInstance().createAssetsBitmap("ani/-1/"+(i+1)+".png", activity);
					}
				}
			}
			break;
		}
	}
	
	
	/*绘制资源*/
	public void paint(int sx, int sy, int anchor, Canvas canvas, Paint paint)
	{
		/*draw ani*/
		this.drawBitmapCenter(this.bmp_ani[20],  sx,   46, sy +  92 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[19],  sx,   72, sy + 250, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[18],  sx,   73, sy + 212 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[17],  sx,  150, sy + 252, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[16],  sx,  142, sy + 217 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[15],  sx,   97, sy + 207 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[14],  sx,   65, sy + 259, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[13],  sx,   64, sy + 221 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[12],  sx,  140, sy + 262, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[11],  sx,  132, sy + 227 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[6],   sx,  149, sy + 170 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[10],  sx,  106, sy + 204 - move_y3, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[9],   sx,   95, sy + 200 - move_y4, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[8],   sx,   95, sy + 158 - move_y4, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[7],   sx,  102, sy + 191 - move_y3, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[4],   sx,  107, sy + 111 - move_y4, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[3],   sx,  164, sy + 167 - move_y2, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[2],   sx,  120, sy + 190 - move_y4, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[1],   sx,   90, sy + 175 - move_y4, anchor, canvas, paint);
		this.drawBitmapCenter(this.bmp_ani[0],   sx,   87, sy + 154 - move_y3, anchor, canvas, paint);
		this.logic();
	}
	
	public void logic()
	{
		if(frameSpeed<4)
		{
			frameSpeed++;
			return;
		}else
		{
			frameSpeed = 0;
		}
		
		if(move_y1 < max_y && !dir_1)
		{
			move_y1 ++;
		}else if(!dir_1)
		{
			dir_1 = true;
		}
		if(move_y1 > 0 && dir_1)
		{
			move_y1 --;
		}else if(dir_1)
		{
			dir_1 = false;
		}
		
		if(count<10)
			count++;
		
		if(count>2)
		{
			if(move_y2 < max_y && !dir_2)
			{
				move_y2 ++;
			}else if(!dir_2)
			{
				dir_2 = true;
			}
			if(move_y2 > 0 && dir_2)
			{
				move_y2 --;
			}else if(dir_2)
			{
				dir_2 = false;
			}
		}
		if(count>3)
		{
			if(move_y3 < max_y && !dir_3)
			{
				move_y3 ++;
			}else if(!dir_3)
			{
				dir_3 = true;
			}
			if(move_y3 > 0 && dir_3)
			{
				move_y3 --;
			}else if(dir_3)
			{
				dir_3 = false;
			}
		}
		if(count>4)
		{
			if(move_y4 < max_y && !dir_4)
			{
				move_y4 ++;
			}else if(!dir_4)
			{
				dir_4 = true;
			}
			if(move_y4 > 0 && dir_4)
			{
				move_y4 --;
			}else if(dir_4)
			{
				dir_4 = false;
			}
		}
		
	}
	
	public void drawBitmapCenter(Bitmap bmp, int sx, int x, int y, int anchor, Canvas canvas, Paint paint)
	{
		switch(anchor)
		{
		case Const.ANCHOR_NORMAL:
			 canvas.drawBitmap(bmp, sx + x - bmp.getWidth()/2, y - bmp.getHeight()/2, paint);
			 break;
		case Const.ANCHOR_MIRROR:
			 canvas.drawBitmap(bmp, sx + x - bmp.getWidth()/2, y - bmp.getHeight()/2, paint);
			 break;
		}
	}
	
	/*getter and setter*/
	public void setPosition(int x, int y)
	{
		this.xpos = x;
		this.ypos = y;
	}
	public int getXpos()
	{
		return this.xpos;
	}
	public int getYpos()
	{
		return this.ypos;
	}
}
