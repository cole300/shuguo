package com.tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/*Button*/
public class Btn {

	private FolderBtnGroup btnGroup;//�������ĸ���ťȺ��
	private String properties = "default";//SINGLE?GROUP
	private String name="default";
	private Bitmap bmp;
	private RectF rect;
	private int xpos, ypos;
	private int btnW, btnH;
	private int openX, openY;//��ť����չ����λ��<Ⱥ�鹦�ܣ�ֻ�иð�ť����BtnGroup�и��������Ч>
	private boolean scaling;//����
	private String visibleName;//��������<��ʾ�ڰ�ť��>
	private int nameColor = -1;
	private int nameFontSize = -1;
	private boolean nameBold = false;//�����Ƿ������ʾ<��paint.setFakeBoldText(argument)����>
	private boolean responseValid = false;//��ť�İ���&�ɿ��Ƿ���Ч<����Ƿ���ͬһ��ť�ϰ��º��ɿ�>
	
	public static final int SPEED_DISAPPEAR = 30;
	public static final int SPEED_OPEN_CLOSE = 30;
	/*�̶����ŵȼ�*/
	public static final float SCALE_LEV = 1.2f;
	private boolean disappearShield;//��ʧ�����ε�ǰ��ť
	
	private int alpha = 255;
	
	public Btn(String bmpURL, int init_xPos, int init_yPos, FolderBtnGroup btnGroup, Activity activity)
	{
		/*��ʼ����Դ*/
		this.rect = new RectF();
		if(bmpURL != null)
		{
			this.bmp = SourcePool.getInstance().createAssetsBitmap(bmpURL, activity);
			/*��ʼ����ײ����*/
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
	/*���ð�ť��Ӧ�Ƿ���Ч*/
	public void setResponseValid(boolean isValid)
	{
		this.responseValid = isValid;
	}
	/*��ȡ��ť��Ӧ�Ƿ���Ч*/
	public boolean getResponseValid()
	{
		return this.responseValid;
	}
	/*���ð�ť���ַ��*/
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
	/*��������*/
	public void setName(String name)
	{
		this.name = name;
	}
	public String getname()
	{
		return this.name;
	}
	/*��������*/
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
	/*��ȡBMP*/
	public Bitmap getBmp()
	{
		return this.bmp;
	}
	/*����BMP*/
	public void setBmp(Bitmap bmp)
	{
		this.bmp = bmp;
		
		if(this.bmp != null)
		{
			this.setRect(xpos, ypos, this.bmp.getWidth(), this.bmp.getHeight());
		}
	}
	/*���ð�ť���<�ð�ťû��BMPʱʹ��>*/
	public void setWidth(int width)
	{
		this.btnW = width;
	}
	/*���ð�ť�߶�<�ð�ťû��BMPʱʹ��>*/
	public void setHeight(int height)
	{
		this.btnH = height;
	}
	/*���ذ�ť���*/
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
	/*���ذ�ť�߶�*/
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
	/*������ʧ&����*/
	public void setDisappearShield(boolean disappear)
	{
		this.disappearShield = disappear;
	}
	public boolean getDisappearShield()
	{
		return this.disappearShield;
	}
	/*�Ƿ��ڴ򿪹رչ���*/
	public boolean isOpenClosing()
	{
		if(this.alpha != 255)
			return true;
		else
			return false;
	}
	/*ע����ťͼƬ��Դ*/
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
	/*����*/
	public void paint(Canvas canvas, Paint paint)
	{
		/*������ʧ&���α�ǿ���alphaֵ*/
		if(this.disappearShield)
		{
			/*�ı�alphaֵ*/
			if(this.alpha > 0)
				this.alpha = this.alpha - SPEED_DISAPPEAR < 0 ? 0 : this.alpha - SPEED_DISAPPEAR;
			/*�ı���Ⱥ������ť�ļ��*/
			if(this.getXpos() < this.btnGroup.getBtnMain().getXpos() && !this.properties.equals("SINGLE"))
			{
				this.setPosition(this.getXpos() + SPEED_OPEN_CLOSE, this.getYpos());
			}
		}else
		{
			/*�ı�alphaֵ*/
			if(this.alpha < 255)
				this.alpha = this.alpha + SPEED_DISAPPEAR > 255 ? 255 : this.alpha + SPEED_DISAPPEAR;
			/*�ı���Ⱥ������ť�ļ��*/
			if(!this.getname().equals("group") && this.getXpos() > this.getOpenX() && !this.properties.equals("SINGLE"))
			{
				this.setPosition(this.getXpos() - SPEED_OPEN_CLOSE, this.getYpos());
			}
		}
		paint.setAlpha(this.alpha);
			
		/*���ݿ���name�ķ��������paint����*/
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
	
	/*��ť����*/
	public boolean checkPressed(float x, float y, boolean isScaling)
	{
		if(this.rect.contains(x, y))
		{
			this.scaling = isScaling;
			/*����ʱ���ü�¼�˴ΰ�����Ч*/
			this.setResponseValid(true);
			return true;
		}
		return false;
	}
	/*��ײ���*/
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
	/*������Դ*/
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
		/*��ʼ������Ϊ��Ч*/
		this.setResponseValid(false);
	}
	
}
