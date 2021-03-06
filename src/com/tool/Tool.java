package com.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Tool {

	public static Tool instance;
	public static Tool getInstance()
	{
		if(instance == null)
		{
			instance = new Tool();
		}
		return instance;
	}
	
	/*获取对方队伍id*/
	public int getReverseTeam(int team)
	{
		switch(team)
		{
		case 0:
			return 1;
		case 1:
			return 0;
		}
		return -1;
	}
	/*teamName*/
	public String getTeamName(int team)
	{
		switch(team)
		{
		case BattleLogic.HomeTeam :
			return "[主队]";
		case BattleLogic.GuestTeam:
			return "[客队]";
		}
		return null;
	}
	/*rand*/
	public int rand(int rangeValue)
	{
		Random rand = new Random();
		return Math.abs(rand.nextInt() % rangeValue);
	}
	/*abs*/
	public float abs(float a, float b)
	{
		float result = a - b;
		
		if(result < 0)
			return -result;
		else
			return result;
	}
	/*release resource*/
	public void destroyed(Bitmap bmp)
	{
		if(bmp != null)
		{
			bmp.recycle();
			bmp = null;
		}
	}
	/*read json file <"data/map.json">*/
	public JSONArray jsonReader(String url_file , Activity activity)
	{
		String content = "";
		InputStream is = null;
		
		try{
			is = activity.getAssets().open(url_file);
			byte[] data = new byte[is.available()];
			is.read(data);
			content = new String(data);
//			System.out.println("json string :"+content);
			
			JSONObject jsonContent = new JSONObject(content);
			
			String layerContent = jsonContent.getString("layers");
			
			JSONArray mapData = new JSONArray(layerContent);
	
			/*取出map数组存入JSONArray*/
			for(int i =0; i< mapData.length(); i++)
			{
				if(mapData.getJSONObject(i).getString("data") != null)
				{
				    mapData = mapData.getJSONObject(i).getJSONArray("data");
				    break;
				}else
				{
					System.err.println("data is not exist");
				}
			}
			/*map相关信息存入JSONArray*/
			JSONArray jsonArray = new JSONArray();
				jsonArray.put(0, mapData);//存入map数组
				jsonArray.put(1, jsonContent.getString("width"));  //存入地图宽block数量
				jsonArray.put(2, jsonContent.getString("height")); //存入地图高block数量
				jsonArray.put(3, jsonContent.getString("tilewidth")); //存入每个tile宽度
				jsonArray.put(4, jsonContent.getString("tileheight"));//存入每个tile高度
			return jsonArray;
		}catch(IOException e){e.printStackTrace();}
		 catch(JSONException e){e.printStackTrace();}
		
		return null;
	}
	/*draw region*/
	public void drawRegion(Bitmap bmp, int x, int y, int w, int h, int des_x, int des_y, Canvas canvas, Paint paint)
	{
		canvas.save();
		
		int w_clip = des_x + w;
		int h_clip = des_y + h;
		canvas.clipRect(des_x, des_y, w_clip, h_clip);
		
		int x_paint = des_x - x;
		int y_paint = des_y - y;
		canvas.drawBitmap(bmp, x_paint, y_paint, paint);
		
		canvas.restore();
	}
	
	/**
	 * 获取数值某一位上的数字
	 * @param numberDigit
	 * @param value
	 */
	public int getDigit(int idx, int value)
	{
		return Integer.parseInt(String.valueOf(String.valueOf(value).charAt(idx)));
	}
	/*获取字符高度*/
	public float getStrHeight(Paint paint)
	{
		/*获取字高*/
		return paint.getFontMetrics().descent - paint.getFontMetrics().top;
	}
	
	/*MirrorY*/
	public float[] mirrorY = {
			-1, 0, 0,
			 0, 1, 0,
			 0, 0, 1
	};
	
	public void refreshEquipStyleEffects(NodeManager nManager, com.data.WuJiang wj, Activity activity)
	{
		for(int i = 0; i < wj.getVecEquip().size(); i ++)
		{
			if(wj.getVecEquip().get(i) == null)
			{
				nManager.changeGuGe(i, -1, activity);
			}else
			{
				nManager.changeGuGe(wj.getVecEquip().get(i).getType(), wj.getVecEquip().get(i).getStyleId(), activity);
			}
		}
	}

	/**
	 * 
	 * @param x 图片x坐标
	 * @param y 图片y坐标
	 * @param degree
	 * @param rotateOriginX 旋转中心点x
	 * @param rotateOriginY 旋转中心点y
	 * @param canvas
	 * @param paint
	 */
	public void drawBitmapCenter(Bitmap bmp, int x, int xPaint, int y, int yPaint, float degree, int rotateOriginX, int rotateOriginY, int anchor, Canvas canvas, Paint paint)
	{
		Matrix matrix = new Matrix();
		int x_translate = 0;
		int y_translate = 0;
		switch(anchor)
		{
		case Const.ANCHOR_NORMAL:
		     matrix.postRotate(degree, rotateOriginX - (x + xPaint), rotateOriginY - (y + yPaint));
		    
		     x_translate = (x + xPaint) - bmp.getWidth()  / 2;
		     y_translate = (y + yPaint) - bmp.getHeight() / 2;
		    
		     matrix.postTranslate(x_translate, y_translate);
		     canvas.drawBitmap(bmp, matrix, paint);
			 break;
		case Const.ANCHOR_MIRROR:
			
		     int mid = x + 120;
			
		     matrix.postRotate(degree, rotateOriginX - (x + xPaint), rotateOriginY - (y + yPaint));
		    
		     x_translate = (x + xPaint) - bmp.getWidth()  / 2;
		     y_translate = (y + yPaint) - bmp.getHeight() / 2;
		     
		     int m_translate = mid + (mid-(x_translate));
		    
		     matrix.postScale(-1, 1);
		     matrix.postTranslate(m_translate, y_translate);
		     canvas.drawBitmap(bmp, matrix, paint);
			 break;
		}
	}
}
