package com.tool;

import java.util.LinkedList;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.shu.ShuguoActivity;

public class VisualEffectsManager {

	public static VisualEffectsManager instance;
	public static VisualEffectsManager getInstance()
	{
		if(instance == null)
		{
			instance = new VisualEffectsManager();
			instance.vecVisualEffects = new LinkedList<VisualEffects>();
		}
		return instance;
	}

	LinkedList<VisualEffects> vecVisualEffects;
	
	/**
	 * active
	 * @param path 图片路径
	 * @param xpos 横坐标
	 * @param ypos 纵坐标
	 * @param amount 帧数量
	 * @param picDir 图片横切<0> 还是竖切<1>
	 * @param activity
	 */
	public void active(String path, String name, int xpos, int ypos, int amount, int picDir, Activity activity)
	{
		VisualEffects visualEffects = new VisualEffects();
					  visualEffects.setBmp(SourcePool.getInstance().createAssetsBitmap(path, activity));
					  visualEffects.setPosition(xpos, ypos);
					  visualEffects.setFrameNum(amount);
					  visualEffects.setPicDir(picDir);
					  visualEffects.setName(name);
		this.vecVisualEffects.add(visualEffects);
	}
	
	/*remove*/
	public void remove(int idx)
	{
		this.vecVisualEffects.get(idx).destroyed();
		this.vecVisualEffects.remove(idx);
	}
	
	/*get*/
	public VisualEffects get(int idx)
	{
		return this.vecVisualEffects.get(idx);
	}
	
	/*run*/
	public void run(ShuguoActivity activity)
	{
		for(int i = 0; i < this.vecVisualEffects.size(); i ++)
		{
			if(this.vecVisualEffects.get(i).isDead())
			{
				//以该名字播放完毕的效果，结束当前索引的战报解析
				if(this.vecVisualEffects.get(i).getName().equals(Const.BATTLE_NEXT_ROUND_EFFNAME))
				{
					BattleLogic.getInstance().setLockDrive(false);
					BattleLogic.getInstance().driveNext();
					BattleLogic.getInstance().driveCheck(activity);
				}
				this.vecVisualEffects.remove(i);
			}
		}
	}
	
	/*paint*/
	public void paint(ShuguoActivity activity, Canvas canvas, Paint paint)
	{
		this.run(activity);
		
		for(int i = 0; i < this.vecVisualEffects.size(); i ++)
		{
			this.vecVisualEffects.get(i).paint(canvas, paint);
		}
	}
	
}
