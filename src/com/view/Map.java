package com.view;

import java.util.LinkedList;

import org.json.JSONException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

import com.data.Player;
import com.tool.Const;
import com.tool.SourcePool;
import com.tool.Tool;

public class Map{

	public ScaleGestureDetector scaleGesture = null;
	float scale_base = 1.0f;//默认缩放等级
	float scale = 0.0f;//缩放浮动值
	
	LinkedList<Bitmap> vec_bmpBuild;
	
	Bitmap bmp;
	
	public Map(Activity activity)
	{
		SourcePool.getInstance().createTileResource(activity);
		
		scaleGesture = new ScaleGestureDetector(activity, listener);

		/*从文件中读取地图数组*/
		SourcePool.mapData = Tool.getInstance().jsonReader("data/map.json", activity);
		/*加载建筑资源*/
		this.load_building_source(activity);
	}
	
	public void load_building_source(Activity activity)
	{
		this.bmp =  SourcePool.getInstance().createAssetsBitmap("tile/HUD.png", activity);
		this.vec_bmpBuild = new LinkedList<Bitmap>();
		for(int i = 0; i < Player.getInstance().getBuilding().size(); i ++)
		{
			this.vec_bmpBuild.add(SourcePool.getInstance().createAssetsBitmap("build/"+Player.getInstance().getBuilding().get(i).getId_pic()+".png", activity));
		}
	}
	
	OnScaleGestureListener listener = new OnScaleGestureListener()
	{
		public void onScaleEnd(ScaleGestureDetector detector)
		{
			Log.i("OnScaleGestureListener", "onScaleEnd");
			scale_base += scale;
			scale = 0;
		}
		
		public boolean onScaleBegin(ScaleGestureDetector detector)
		{
			Log.i("OnScaleGestureListener", "onScaleBegin detector.getCurrentSpan()="+detector.getCurrentSpan());
			detector.getCurrentSpan();
			return true;
		}
		
		public boolean onScale(ScaleGestureDetector detector)
		{
			float cur = detector.getCurrentSpan();
			float pre = detector.getPreviousSpan();
			
			if(pre - cur == 0)
				return false;
			
//			System.out.println("CurrentSpan()="+cur
//					+ "PreviousSpan()= " + pre
//					+ " cur - pre="+(cur-pre));
			
			/*每次手势移动放大值*/
			float scale_average = (cur - pre) / 200;
			
			/*整体目前放大值*/
			float scale_world = scale_base + scale_average;
			
			/*整体缩放限制在1~3倍以内*/
			if(scale_world < 0.5f || scale_world > 3.0f)
			{
				return false;
			}
			
			scale = scale_average;
			
			return false;
		}
	};
	
	public void paint(Canvas canvas, Paint paint)
	{
		/*绘制2.5D地图层*/
//		canvas.save();
//		canvas.scale(scale_base + scale, scale_base + scale, Const.SCREEN_W / 2, Const.SCREEN_H / 2);
//		this.drawMap(canvas, paint);
		canvas.drawBitmap(this.bmp, 0,0, paint);
//		try{
//			this.drawBuilding(canvas, paint);
//		}catch(JSONException e){e.printStackTrace();}
		
		canvas.restore();
		
		/*绘制单张假地图*/
//		canvas.drawBitmap(SourcePool.getInstance().bmp_temp, 0, 0, paint);
	}
	
	/*绘制建筑*/
	public void drawBuilding(Canvas canvas, Paint paint) throws JSONException
	{
		
		for(int i = 0; i < Player.getInstance().getBuilding().size(); i ++)
		{
			int horId = Player.getInstance().getBuilding().get(i).getHorId_map();
			int verId = Player.getInstance().getBuilding().get(i).getVerId_map();
			
			int xpos = (horId - verId) * Const.TILE_W;
			int ypos = (verId + horId) * Const.TILE_H;
			
			canvas.drawBitmap(this.vec_bmpBuild.get(i), Const.SCREEN_W/2 + xpos, ypos, paint);
			
			paint.setColor(Color.BLACK);
			paint.setAlpha(80);
			
			RectF rectF = new RectF(Const.SCREEN_W / 2 + xpos + this.vec_bmpBuild.get(i).getWidth() / 2 - 50,
									ypos - 20,
									Const.SCREEN_W / 2 + xpos + this.vec_bmpBuild.get(i).getWidth() / 2 + 50,
									ypos);
			canvas.drawRoundRect(rectF, 10, 10, paint);
			paint.reset();
			paint.setColor(Color.WHITE);
			paint.setTextSize(15);
			paint.setFakeBoldText(true);
			paint.setTextAlign(Paint.Align.CENTER);
			canvas.drawText(Player.getInstance().getBuilding().get(i).getName(), Const.SCREEN_W / 2 + xpos + this.vec_bmpBuild.get(i).getWidth() / 2, ypos-4, paint);
			paint.reset();
		}
	}
	/*当paintY为CHIP_H / 2的奇数倍时，paintX需要偏移CHIP_W / 2*/
	public void drawMap(Canvas canvas, Paint paint)
	{
		int width = 0;
		int height = 0;
		int ver_height = 0;
		int tile_per_width = 0;
		int tile_per_height = 0;
		try{
			ver_height =       SourcePool.mapData.getInt(SourcePool.MAPDATA_VER_HEIGHT);
			tile_per_width =   SourcePool.mapData.getInt(SourcePool.MAPDATA_TILE_WIDTH);
			tile_per_height = SourcePool.mapData.getInt(SourcePool.MAPDATA_TILE_HEIGHT);
			width =             SourcePool.mapData.getInt(SourcePool.MAPDATA_COL_WIDTH);
			height = 		   SourcePool.mapData.getInt(SourcePool.MAPDATA_VER_HEIGHT);
			
		}catch(JSONException e){e.printStackTrace();}
		for(int j = 0 ; j < height; j ++)
		{
			for(int i = 0; i < width; i++)
			{
				/*图片x坐标*/
				int x_pic = (i - j) * Const.TILE_W;
				int y_pic = (j + i) * Const.TILE_H;
				
				int data = 0;	
				try
				{
				   data = SourcePool.mapData.getJSONArray(SourcePool.MAPDATA_DATA).getInt(ver_height*j+i);
				}catch(JSONException e){e.printStackTrace();}
				
				int start_x = (data-1)*tile_per_width;
				int start_y = 0;
				int start_w = tile_per_width;
				int start_h = tile_per_height;
				int xpos = Const.SCREEN_W/2 + x_pic;
				int ypos = y_pic;
				Tool.getInstance().drawRegion(SourcePool.getInstance().bmp_map, 
											                           start_x,
											                           start_y, 
											                           start_w,
											                           start_h, 
											                              xpos, 
											          					  ypos, 
											          		    canvas, paint);
			}
		}
	}
	
}
