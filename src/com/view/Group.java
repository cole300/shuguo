package com.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.shu.ShuguoActivity;
import com.tool.Const;

/*管理所有子view*/
public class Group extends ViewGroup{
	
	public static Group instance;
	
	public static Group getInstance()
	{
		return instance;
	}

	public Group(Context context, ShuguoActivity activity)
	{
		super(context);
		this.initOtherComponent(context , activity);
	}
	
	private void initOtherComponent(Context context, ShuguoActivity activity)
	{
//		Map map = new Map(context, activity);
//		//set id 1
//		map.setId(1);
//		this.addView(map);
		
//		Dating status = new Dating(activity.getApplicationContext(), activity);
		BarStatus status = new BarStatus(context, activity);
		
		//Battle status = new Battle(activity.getApplicationContext(), activity);
//		activity.group.addView(battle);
			
//		ChoiceAreaLine status = new ChoiceAreaLine(context, activity);
//		BattleBoard status = new BattleBoard(context, activity);
//		Ani status = new Ani(context, activity);
		//set id 2
		this.addView(status);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		int childCount = getChildCount();
		
		for(int i = 0; i < childCount; i++)
		{
			View child = getChildAt(i);
			
			switch(child.getId())
			{
			case 1:
				// 1 is map instance
//				System.out.println("view_map ["+"l :"+l+" t:"+t+" r:"+r+" b:"+b+"]");
				child.setVisibility(View.VISIBLE);
				child.measure(r - l, b - t);
				child.layout(0, 0, r, b);
//				System.out.println("measureWidth :"+child.getMeasuredWidth()+" measureHeight :"+child.getMeasuredHeight());
				break;
			case 2:
				// 2 is status instance
//				System.out.println("view_status ["+"l :"+l+" t:"+t+" r:"+r+" b:"+b+"]");
				child.setVisibility(View.VISIBLE);
				child.measure(r - l, b - t);
				child.layout(0, 0, r, b);
//				System.out.println("measureWidth :"+child.getMeasuredWidth()+" measureHeight :"+child.getMeasuredHeight());
				break;
			default:
				child.setVisibility(View.VISIBLE);
				child.measure(r - l, b - t);
				child.layout(0, 0, r, b);
				break;
			}
		}
	}
	
	/**根据目标view.id来返回group中的该view， 如果没有则返回null*/
	public View getViewById(int id)
	{
		for(int i = 0; i < this.getChildCount(); i ++)
		{
			if(this.getChildAt(i).getId() == id)
			{
				return this.getChildAt(i);
			}
		}
		
		return null;
	}
	
}
