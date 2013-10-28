package com.tool;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/*Elements vector*/
public class ElementGroup {

	/*Group����*/
	private LinkedList<Element> group;
	/*��������*/
	private int xpos, ypos;
	/*ÿ����ʾ����<������ʾ����>*/
	private int visNumHorizontal;
	/*ÿ����ʾ����<������ʾ����>*/
	private int visNumVertical;
	/*Ԫ�غ�����*/
	private int gapHorizontal;
	/*Ԫ��������*/
	private int gapVertical;
	/*��ǰҳ��*/
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
	/*��ȡ��ǰҳ��*/
	public int getIdxPage()
	{
		return this.idxPage;
	}
	/*����һҳ*/
	public void nextPage()
	{
		/*�����Ƿ��ѵ�ҳĩ*/
		if(this.idxPage < this.getNumPage())
		{
			this.idxPage++;
		}else
			WarnningManager.getInstance().add(new Warnning("�������һҳ", Color.RED, 40, true, 3, true));
	}
	/*��ǰһҳ*/
	public void previousPage()
	{
		/*�����Ƿ��ѵ�ҳ��*/
		if(this.idxPage > 0)
		{
			this.idxPage --;
		}else
			WarnningManager.getInstance().add(new Warnning("������ҳ", Color.RED, 40, true, 3, true));
	}
	/*��ȡ��ǰһ���ж���ҳ*/
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
	/*��ȡԪ�غ�����*/
	public int getGapHorizontal()
	{
		return this.gapHorizontal;
	}
	/*��ȡԪ��������*/
	public int getGapVertical()
	{
		return this.gapVertical;
	}
	/*���ú�����ʾ����*/
	public void setVisNumHorizontal(int num)
	{
		this.visNumHorizontal = num;
	}
	/*��ȡ������ʾ����*/
	public int getVisNumHorizontal()
	{
		return this.visNumHorizontal;
	}
	/*����������ʾ����*/
	public void setVisNumVertical(int num)
	{
		this.visNumVertical = num;
	}
	/*��ȡ������ʾ����*/
	public int getVisNumVertical()
	{
		return this.visNumVertical;
	}
	/*��ȡ����*/
	public LinkedList<Element> getGroup()
	{
		return this.group;
	}
	/*�����������Ԫ��*/
	public void add(Element element)
	{
		this.group.add(element);
	}
	/*��ȡ����������*/
	public int getXpos()
	{
		return this.xpos;
	}
	/*��ȡ����������*/
	public int getYpos()
	{
		return this.ypos;
	}
	/*������������*/
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
	/*��ȡԪ����paint�����е�ʵ����ʼ������*/
	public int getElementPaintXpos(int elementId)
	{
		return this.getXpos() + (elementId % this.getVisNumHorizontal() * (this.group.get(elementId).getElementWidth() + this.getGapHorizontal()));
	}
	/*��ȡԪ����paint�����е�ʵ����ʼ������*/
	public int getElementPaintYpos(int elementId)
	{
		return this.getYpos() + (elementId / this.getVisNumHorizontal() * (this.group.get(elementId).getElementHeight() + this.getGapVertical()));
	}
	/*������������Ԫ��*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*��ҳ�������������*/
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
	/*����Ⱥ��Ԫ����Դ*/
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
	/*��ȡ��ǰҳ����ʼ����*/
	public int getIdxStart()
	{
		return (this.idxPage * this.visNumHorizontal * this.visNumVertical);
	}
	/*��ȡ��ǰҳ��ĩβ����*/
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
