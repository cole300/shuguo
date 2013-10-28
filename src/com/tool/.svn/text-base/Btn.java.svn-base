package com.tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/*Button*/
public class Btn {

	private FolderBtnGroup btnGroup;//附属于哪个按钮群组
	private String properties = "default";//SINGLE?GROUP
	private String name="default";
	private Bitmap bmp;
	private RectF rect;
	private int xpos, ypos;
	private int btnW, btnH;
	private int openX, openY;//按钮组伸展开的位置<群组功能，只有该按钮放在BtnGroup中该坐标才有效>
	private boolean scaling;//缩放
	private String visibleName;//可视名称<显示在按钮上>
	private int nameColor = -1;
	private int nameFontSize = -1;
	private boolean nameBold = false;//名字是否粗体显示<跟paint.setFakeBoldText(argument)关联>
	private boolean responseValid = false;//按钮的按下&松开是否有效<检测是否在同一按钮上按下和松开>
	
	public static final int SPEED_DISAPPEAR = 30;
	public static final int SPEED_OPEN_CLOSE = 30;
	/*固定缩放等级*/
	public static final float SCALE_LEV = 1.2f;
	private boolean disappearShield;//消失并屏蔽当前按钮
	
	private int alpha = 255;
	
	public Btn(String bmpURL, int init_xPos, int init_yPos, FolderBtnGroup btnGroup, Activity activity)
	{
		/*初始化资源*/
		this.rect = new RectF();
		if(bmpURL != null)
		{
			this.bmp = SourcePool.getInstance().createAssetsBitmap(bmpURL, activity);
			/*初始化碰撞区域*/
			this.rect.set(init_xPos, init_yPos, init_xPos + this.bmp.getWidth(), init_yPos + this.bmp.getHeight());
		}
		this.xpos = init_xPos;
		this.ypos = init_yPos;
		this.btnGroup = btnGroup;
	}
	public void setProperties(String str)
	{
		this.properties = str;
	}
	public String getProperties()
	{
		return this.properties;
	}
	/*设置按钮响应是否有效*/
	public void setResponseValid(boolean isValid)
	{
		this.responseValid = isValid;
	}
	/*获取按钮响应是否有效*/
	public boolean getResponseValid()
	{
		return this.responseValid;
	}
	/*设置按钮文字风格*/
	public void setNameStyle(String visName, int color, int fontSize, boolean isBold)
	{
		this.visibleName = visName;
		this.nameColor = color;
		this.nameFontSize = fontSize;
		this.nameBold = isBold;
	}
	public String getVisibleName()
	{
		return this.visibleName;
	}
	/*设置名称*/
	public void setName(String name)
	{
		this.name = name;
	}
	public String getname()
	{
		return this.name;
	}
	/*设置坐标*/
	public void setPosition(int x, int y)
	{
		this.xpos = x;
		this.ypos = y;
		
		if(this.bmp != null)
		{
			this.setRect(xpos, ypos, this.bmp.getWidth(), this.bmp.getHeight());
		}else
		{
			this.setRect(xpos, ypos, this.btnW, this.btnH);
		}
	}
	/*获取BMP*/
	public Bitmap getBmp()
	{
		return this.bmp;
	}
	/*设置BMP*/
	public void setBmp(Bitmap bmp)
	{
		this.bmp = bmp;
		
		if(this.bmp != null)
		{
			this.setRect(xpos, ypos, this.bmp.getWidth(), this.bmp.getHeight());
		}
	}
	/*设置按钮宽度<该按钮没有BMP时使用>*/
	public void setWidth(int width)
	{
		this.btnW = width;
	}
	/*设置按钮高度<该按钮没有BMP时使用>*/
	public void setHeight(int height)
	{
		this.btnH = height;
	}
	/*返回按钮宽度*/
	public int getWidth()
	{
		if(this.bmp != null)
		{
			return this.bmp.getWidth();
		}else
		{
			return this.btnW;
		}
	}
	/*返回按钮高度*/
	public int getHeight()
	{
		if(this.bmp != null)
		{
			return this.bmp.getHeight();
		}else
		{
			return this.btnH;
		}
	}
	/*设置消失&屏蔽*/
	public void setDisappearShield(boolean disappear)
	{
		this.disappearShield = disappear;
	}
	public boolean getDisappearShield()
	{
		return this.disappearShield;
	}
	/*是否处于打开关闭过程*/
	public boolean isOpenClosing()
	{
		if(this.alpha != 255)
			return true;
		else
			return false;
	}
	/*注销按钮图片资源*/
	public void destroyedBmp()
	{
		Tool.getInstance().destroyed(this.bmp);
	}
	
	public void paintScales(float scaleX, float scaleY, Canvas canvas, Paint paint)
	{
		if(this.scaling)
		{
			canvas.save();
			canvas.scale(SCALE_LEV + (scaleX - 1.0f), SCALE_LEV + (scaleY - 1.0f), this.xpos+this.bmp.getWidth()/2, this.ypos+this.bmp.getHeight()/2);
			canvas.drawBitmap(this.bmp, this.xpos, this.ypos, paint);
			canvas.restore();
		}else
		{
			if(this.bmp != null)
			{
				canvas.save();
				canvas.scale(scaleX, scaleY, this.xpos+this.bmp.getWidth()/2, this.ypos+this.bmp.getHeight()/2);
				canvas.drawBitmap(this.bmp, this.xpos, this.ypos, paint);
				canvas.restore();
			}
		}
	}
	/*绘制*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*根据消失&屏蔽标记控制alpha值*/
		if(this.disappearShield)
		{
			/*改变alpha值*/
			if(this.alpha > 0)
				this.alpha = this.alpha - SPEED_DISAPPEAR < 0 ? 0 : this.alpha - SPEED_DISAPPEAR;
			/*改变与群组主按钮的间距*/
			if(this.getXpos() < this.btnGroup.getBtnMain().getXpos() && !this.properties.equals("SINGLE"))
			{
				this.setPosition(this.getXpos() + SPEED_OPEN_CLOSE, this.getYpos());
			}
		}else
		{
			/*改变alpha值*/
			if(this.alpha < 255)
				this.alpha = this.alpha + SPEED_DISAPPEAR > 255 ? 255 : this.alpha + SPEED_DISAPPEAR;
			/*改变与群组主按钮的间距*/
			if(!this.getname().equals("group") && this.getXpos() > this.getOpenX() && !this.properties.equals("SINGLE"))
			{
				this.setPosition(this.getXpos() - SPEED_OPEN_CLOSE, this.getYpos());
			}
		}
		paint.setAlpha(this.alpha);
			
		/*根据可视name的风格来设置paint属性*/
		this.setVisNamePaintStyle(paint);
		
		if(this.scaling)
		{
			canvas.save();
			canvas.scale(SCALE_LEV, SCALE_LEV, this.xpos+this.bmp.getWidth()/2, this.ypos+this.bmp.getHeight()/2);
			canvas.drawBitmap(this.bmp, this.xpos, this.ypos, paint);
			
			if(this.visibleName != null)
			{
				canvas.drawText(this.visibleName,
								this.xpos + this.bmp.getWidth()  / 2 - paint.measureText(this.visibleName) / 2, 
								this.ypos + this.bmp.getHeight() / 2 + Tool.getInstance().getStrHeight(paint)/3, paint);
			}
			canvas.restore();
		}else
		{
			if(this.bmp != null)
			{
				canvas.drawBitmap(this.bmp, this.xpos, this.ypos, paint);
				
				if(this.visibleName != null)
				{
					canvas.drawText(this.visibleName,
									this.xpos + this.bmp.getWidth() / 2 - paint.measureText(this.visibleName) / 2, 
									this.ypos + this.bmp.getHeight() / 2 + Tool.getInstance().getStrHeight(paint)/3, paint);
				}
			}
		}
		paint.reset();
		paint.setAntiAlias(true);
		
	}
	
	public void setVisNamePaintStyle(Paint paint)
	{
		if(this.nameColor != -1)
		{
			paint.setColor(this.nameColor);
		}
		if(this.nameFontSize != -1)
		{
			paint.setTextSize(this.nameFontSize);
		}
		paint.setFakeBoldText(this.nameBold);
	}
	
	/*按钮按下*/
	public boolean checkPressed(float x, float y, boolean isScaling)
	{
		if(this.rect.contains(x, y))
		{
			this.scaling = isScaling;
			/*按下时设置记录此次按下有效*/
			this.setResponseValid(true);
			return true;
		}
		return false;
	}
	/*碰撞检测*/
	public boolean collidesWith(float x, float y)
	{
		if(!this.getResponseValid())
		{
			return false;
		}
		if(this.getRect().contains(x, y))
		{
			return true;
		}
		
		return false;
	}
	/*销毁资源*/
	public void destroyed()
	{
		Tool.getInstance().destroyed(this.bmp);
	}
	
	/**Setter and Getter*/
	public int getXpos()
	{
		return this.xpos;
	}
	public void setXpos(int xpos)
	{
		this.xpos = xpos;
	}
	public int getYpos()
	{
		return this.ypos;
	}
	public void setYpos(int ypos)
	{
		this.ypos = ypos;
	}
	public int getOpenX()
	{
		return this.openX;
	}
	public void setOpenX(int x)
	{
		this.openX = x;
	}
	public int getOpenY()
	{
		return this.openY;
	}
	public void setOpenY(int y)
	{
		this.openY = y;
	}
	
	public RectF getRect()
	{
		return this.rect;
	}
	public void setRect(int x, int y, int w, int h)
	{
		this.rect.set(x, y, x +w, y + h);
	}
	public boolean getScaling()
	{
		return this.scaling;
	}
	public void setScaling(boolean scaling)
	{
		this.scaling = scaling;
		/*初始化按下为无效*/
		this.setResponseValid(false);
	}
	
}
