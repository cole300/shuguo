package com.tool;

import java.io.BufferedInputStream;

import org.json.JSONArray;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SourcePool {
	
	public static SourcePool instance;
	
	public static SourcePool getInstance()
	{
		if(instance == null)
		{
			instance = new SourcePool();
		}
		return instance;
	}

	public static final int        MAPDATA_DATA = 0;//地图数组
	public static final int   MAPDATA_COL_WIDTH = 1;//横向块数
	public static final int  MAPDATA_VER_HEIGHT = 2;//纵向块数
	public static final int  MAPDATA_TILE_WIDTH = 3;//tile宽度
	public static final int MAPDATA_TILE_HEIGHT = 4;//tile高度
	public static JSONArray mapData;//存放map数组
	
	public Bitmap bmp_map;
	
	public void createTileResource(Activity activity)
	{
		bmp_map= this.createAssetsBitmap("tile/map.png", activity);
	}
	
	public Bitmap createAssetsBitmap(String url, Activity activity)
    {
    	Bitmap bitmap= null;
    	try
    	{
    		BufferedInputStream bis= new BufferedInputStream(activity.getAssets().open(url));
    	    bitmap= BitmapFactory.decodeStream(bis);
    	}catch(Exception e){e.printStackTrace();}
    	
    	return bitmap;
    }

}
