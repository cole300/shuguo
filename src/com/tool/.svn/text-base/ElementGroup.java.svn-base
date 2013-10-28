package com.tool;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/*Elements vector*/
public class ElementGroup {

	/*Group容器*/
	private LinkedList<Element> group;
	/*容器坐标*/
	private int xpos, ypos;
	/*每行显示数量<横向显示数量>*/
	private int visNumHorizontal;
	/*每列显示数量<纵向显示数量>*/
	private int visNumVertical;
	/*元素横向间隔*/
	private int gapHorizontal;
	/*元素纵向间隔*/
	private int gapVertical;
	/*当前页数*/
	private int idxPage;
	public ElementGroup(int xpos, int ypos, int visNumHorizontal, int visNumVertical, int gapHorizontal, int gapVertical)
	{
		group = new LinkedList<Element>();
		
		this.setPosition(xpos, ypos);
		
		this.visNumHorizontal = visNumHorizontal;
		this.visNumVertical =     visNumVertical;
		this.gapHorizontal = gapHorizontal;
		this.gapVertical =     gapVertical;
	}
	/*获取当前页数*/
	public int getIdxPage()
	{
		return this.idxPage;
	}
	/*翻下一页*/
	public void nextPage()
	{
		/*计算是否已到页末*/
		if(this.idxPage < this.getNumPage())
		{
			this.idxPage++;
		}else
			WarnningManager.getInstance().add(new Warnning("已是最后一页", Color.RED, 40, true, 3, true));
	}
	/*翻前一页*/
	public void previousPage()
	{
		/*计算是否已到页首*/
		if(this.idxPage > 0)
		{
			this.idxPage --;
		}else
			WarnningManager.getInstance().add(new Warnning("已是首页", Color.RED, 40, true, 3, true));
	}
	/*获取当前一共有多少页*/
	public int getNumPage()
	{
		if(this.group.size() % (this.visNumHorizontal * this.visNumVertical) == 0)
		{
			return this.group.size() / (this.visNumHorizontal * this.visNumVertical) - 1;
		}else
		{
			return this.group.size() / (this.visNumHorizontal * this.visNumVertical);
		}
	}
	/*获取元素横向间隔*/
	public int getGapHorizontal()
	{
		return this.gapHorizontal;
	}
	/*获取元素纵向间隔*/
	public int getGapVertical()
	{
		return this.gapVertical;
	}
	/*设置横向显示数量*/
	public void setVisNumHorizontal(int num)
	{
		this.visNumHorizontal = num;
	}
	/*获取横向显示数量*/
	public int getVisNumHorizontal()
	{
		return this.visNumHorizontal;
	}
	/*设置纵向显示数量*/
	public void setVisNumVertical(int num)
	{
		this.visNumVertical = num;
	}
	/*获取纵向显示数量*/
	public int getVisNumVertical()
	{
		return this.visNumVertical;
	}
	/*获取容器*/
	public LinkedList<Element> getGroup()
	{
		return this.group;
	}
	/*往容器中添加元素*/
	public void add(Element element)
	{
		this.group.add(element);
	}
	/*获取容器横坐标*/
	public int getXpos()
	{
		return this.xpos;
	}
	/*获取容器纵坐标*/
	public int getYpos()
	{
		return this.ypos;
	}
	/*设置容器坐标*/
	public void setPosition(int x, int y)
	{
		this.xpos = x;
		this.ypos = y;
	}
	
	/*closeScaling*/
	public void closeScaling()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			this.group.get(i).closeScaling();
		}
	}
	/*获取元素在paint矩阵中的实际起始横坐标*/
	public int getElementPaintXpos(int elementId)
	{
		return this.getXpos() + (elementId % this.getVisNumHorizontal() * (this.group.get(elementId).getElementWidth() + this.getGapHorizontal()));
	}
	/*获取元素在paint矩阵中的实际起始纵坐标*/
	public int getElementPaintYpos(int elementId)
	{
		return this.getYpos() + (elementId / this.getVisNumHorizontal() * (this.group.get(elementId).getElementHeight() + this.getGapVertical()));
	}
	/*绘制容器所有元素*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*翻页引起的跳过数量*/
		int numPageSkip = this.idxPage * (this.visNumHorizontal * this.visNumVertical);
		
		for(int i = 0; i < this.visNumVertical; i ++)
		{
			for(int j = 0; j < this.visNumHorizontal; j ++)
			{
				int paintId = i * this.visNumHorizontal + j + numPageSkip;
				
				if(paintId >= this.group.size())
					break;
				
					if(this.group.get(paintId)==null)
						continue;
					
					this.group.get(paintId).paint(this.getXpos() + j * (this.group.get(paintId).getElementWidth()  + this.gapHorizontal),
											  	  this.getYpos() + i * (this.group.get(paintId).getElementHeight() +   this.gapVertical),
											      canvas, paint);
			}
		}
	}
	/*销毁群组元素资源*/
	public void destoryed()
	{
		for(int i = 0; i < this.group.size(); i ++)
		{
			if(this.group.get(i) == null)
				continue;
			
			this.group.get(i).destroyed();
		}
		this.group.clear();
	}
	/*获取当前页的起始索引*/
	public int getIdxStart()
	{
		return (this.idxPage * this.visNumHorizontal * this.visNumVertical);
	}
	/*获取当前页的末尾索引*/
	public int getIdxEnd()
	{
		if(this.group.size() - this.getIdxStart() > this.visNumHorizontal * this.visNumVertical)
		{
			return this.getIdxStart() + (this.visNumHorizontal * this.visNumVertical);
		}else
		{
			return this.group.size();
		}
	}
}
