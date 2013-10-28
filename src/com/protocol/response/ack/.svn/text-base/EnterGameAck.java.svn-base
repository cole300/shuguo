



package com.protocol.response.ack;

 
 
 

import java.util.ArrayList;
import com.net.Response;
import com.protocol.AckBean;
import com.protocol.response.EnterGame ;
import com.protocol.response.ack.WuJiangInfoListAck ; 




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 



public class EnterGameAck extends AckBean  {

    private Response response;
  
  
  
  {
     PROT_ID = 4;
  }
  
    public void handle(){
		EnterGame.handle(this);
	}
  
  
 private short  nation; //国籍
  
 private String  nickName; //玩家昵称
  
 private byte  nickNameNeedSet; //玩家昵称需要设置（0不需要，1需要）
  
 private int  money; //游戏货币
  
 private int  yinyuan; //银元
  
 private int  gold; //元宝
  
 private int  level; //等级
  
 private int  curExp; //现有经验
  
 private int  maxExp; //最大经验
  
  
 
 
 private WuJiangInfoListAck  wujianglist; //武将列表
 
 
     
            private long[]  itemuidArry; //物品UID
            private int[]  picidArry; //图片id
            private int[]  styleidArry; //换装id
            private String[]  itemnameArry; //物品名称
            private int[]  typeArry; //物品类型
            private int[]  numArry; //物品数量
     
     private short size = 0;  //数组个数



  public EnterGameAck(Response v){
        response = v;  
    }
    
    
    public void decode(){
        nation = response.readShort() ;
        nickName = response.readUTF() ;
        nickNameNeedSet = response.readByte() ;
        money = response.readInt() ;
        yinyuan = response.readInt() ;
        gold = response.readInt() ;
        level = response.readInt() ;
        curExp = response.readInt() ;
        maxExp = response.readInt() ;
    
     {
         size = 0;
         size = response.readShort();
        if(size > 0){
        
             itemuidArry = new long[size];
             picidArry = new int[size];
             styleidArry = new int[size];
             itemnameArry = new String[size];
             typeArry = new int[size];
             numArry = new int[size];
            for(int i = 0; i< size; i++)
            {
              int tmpCurrPos = response.getCurrPos();
              int tmpArrayLen = response.readShort();
            
             itemuidArry[i] = response.readLong() ;
             picidArry[i] = response.readInt() ;
             styleidArry[i] = response.readInt() ;
             itemnameArry[i] = response.readUTF() ;
             typeArry[i] = response.readInt() ;
             numArry[i] = response.readInt() ;
 
             if (response.getCurrPos() != (tmpCurrPos + tmpArrayLen)) {
				if (response.getCurrPos() < (tmpCurrPos + tmpArrayLen)) {
					int skip = (tmpCurrPos + tmpArrayLen) - response.getCurrPos();
					response.skipBytes(skip);
				}
			}
 
            }
        }
    }
    
    
      {
                byte cmd = response.readByte();
                WuJiangInfoListAck tmp = new WuJiangInfoListAck(response);
                tmp.decode();
                wujianglist = tmp;
            }    
    
     
    }


 public short  getNation(){
    return nation;
 }
 
  public void setNation(short  v ){
    nation = v;
 }

 
 public String  getNickName(){
    return nickName;
 }
 
  public void setNickName(String  v ){
    nickName = v;
 }

 
 public byte  getNickNameNeedSet(){
    return nickNameNeedSet;
 }
 
  public void setNickNameNeedSet(byte  v ){
    nickNameNeedSet = v;
 }

 
 public int  getMoney(){
    return money;
 }
 
  public void setMoney(int  v ){
    money = v;
 }

 
 public int  getYinyuan(){
    return yinyuan;
 }
 
  public void setYinyuan(int  v ){
    yinyuan = v;
 }

 
 public int  getGold(){
    return gold;
 }
 
  public void setGold(int  v ){
    gold = v;
 }

 
 public int  getLevel(){
    return level;
 }
 
  public void setLevel(int  v ){
    level = v;
 }

 
 public int  getCurExp(){
    return curExp;
 }
 
  public void setCurExp(int  v ){
    curExp = v;
 }

 
 public int  getMaxExp(){
    return maxExp;
 }
 
  public void setMaxExp(int  v ){
    maxExp = v;
 }

 




     
            public long[]  getItemuidArry(){
                 return itemuidArry;
            }
            public void setItemuidArry(long[] v ){
                itemuidArry = v;
            }
            public int[]  getPicidArry(){
                 return picidArry;
            }
            public void setPicidArry(int[] v ){
                picidArry = v;
            }
            public int[]  getStyleidArry(){
                 return styleidArry;
            }
            public void setStyleidArry(int[] v ){
                styleidArry = v;
            }
            public String[]  getItemnameArry(){
                 return itemnameArry;
            }
            public void setItemnameArry(String[] v ){
                itemnameArry = v;
            }
            public int[]  getTypeArry(){
                 return typeArry;
            }
            public void setTypeArry(int[] v ){
                typeArry = v;
            }
            public int[]  getNumArry(){
                 return numArry;
            }
            public void setNumArry(int[] v ){
                numArry = v;
            }
     
       public short getArraySize(){
             return size;
       }
     


    public WuJiangInfoListAck getWujianglist(){
          return wujianglist;
    }
           
    public void setWujianglist(WuJiangInfoListAck v){
          wujianglist = v;
    }     
     



 



}
