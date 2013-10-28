



package com.protocol.response.ack;

 
 
 

import java.util.ArrayList;
import com.net.Response;
import com.protocol.AckBean;
import com.protocol.response.WuJiangInfoList ;




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 



public class WuJiangInfoListAck extends AckBean  {

    private Response response;
  
  
  
  {
     PROT_ID = 5;
  }
  
    public void handle(){
		WuJiangInfoList.handle(this);
	}
  
  
 
 
 
 
     
            private long[]  wujiangIdArry; //id
            private String[]  wujiangNameArry; //姓名
            private short[]  wujiangLevelArry; //等级
            private int[]  wujiangQualityArry; //品质
            private int[]  wujiangHeadIdArry; //头像id
            private int[]  wujiangCurExpArry; //现有经验
            private int[]  wujiangMaxExpArry; //最大经验
            private int[]  wujiangAttackArry; //攻击
            private int[]  wujiangDefenseArry; //防御
            private int[]  wujiangHpArry; //血量
            private int[]  wujiangOwnArry; //带兵
            private int[]  wujiangCurPhysicalArry; //现有体力
            private int[]  wujiangMaxPhysicalArry; //最大体力
            private byte[]  wujiangSexArry; //性别
            private int[]  wujiangWarriorGongbingArry; //弓兵
            private int[]  wujiangWarriorBubingArry; //步兵
            private int[]  wujiangWarriorQibingArry; //骑兵
            private String[]  wujiangDesArry; //描述
     
     private short size = 0;  //数组个数



  public WuJiangInfoListAck(Response v){
        response = v;  
    }
    
    
    public void decode(){
    
     {
         size = 0;
         size = response.readShort();
        if(size > 0){
        
             wujiangIdArry = new long[size];
             wujiangNameArry = new String[size];
             wujiangLevelArry = new short[size];
             wujiangQualityArry = new int[size];
             wujiangHeadIdArry = new int[size];
             wujiangCurExpArry = new int[size];
             wujiangMaxExpArry = new int[size];
             wujiangAttackArry = new int[size];
             wujiangDefenseArry = new int[size];
             wujiangHpArry = new int[size];
             wujiangOwnArry = new int[size];
             wujiangCurPhysicalArry = new int[size];
             wujiangMaxPhysicalArry = new int[size];
             wujiangSexArry = new byte[size];
             wujiangWarriorGongbingArry = new int[size];
             wujiangWarriorBubingArry = new int[size];
             wujiangWarriorQibingArry = new int[size];
             wujiangDesArry = new String[size];
            for(int i = 0; i< size; i++)
            {
              int tmpCurrPos = response.getCurrPos();
              int tmpArrayLen = response.readShort();
            
             wujiangIdArry[i] = response.readLong() ;
             wujiangNameArry[i] = response.readUTF() ;
             wujiangLevelArry[i] = response.readShort() ;
             wujiangQualityArry[i] = response.readInt() ;
             wujiangHeadIdArry[i] = response.readInt() ;
             wujiangCurExpArry[i] = response.readInt() ;
             wujiangMaxExpArry[i] = response.readInt() ;
             wujiangAttackArry[i] = response.readInt() ;
             wujiangDefenseArry[i] = response.readInt() ;
             wujiangHpArry[i] = response.readInt() ;
             wujiangOwnArry[i] = response.readInt() ;
             wujiangCurPhysicalArry[i] = response.readInt() ;
             wujiangMaxPhysicalArry[i] = response.readInt() ;
             wujiangSexArry[i] = response.readByte() ;
             wujiangWarriorGongbingArry[i] = response.readInt() ;
             wujiangWarriorBubingArry[i] = response.readInt() ;
             wujiangWarriorQibingArry[i] = response.readInt() ;
             wujiangDesArry[i] = response.readUTF() ;
 
             if (response.getCurrPos() != (tmpCurrPos + tmpArrayLen)) {
				if (response.getCurrPos() < (tmpCurrPos + tmpArrayLen)) {
					int skip = (tmpCurrPos + tmpArrayLen) - response.getCurrPos();
					response.skipBytes(skip);
				}
			}
 
            }
        }
    }
    
     
    }






     
            public long[]  getWujiangIdArry(){
                 return wujiangIdArry;
            }
            public void setWujiangIdArry(long[] v ){
                wujiangIdArry = v;
            }
            public String[]  getWujiangNameArry(){
                 return wujiangNameArry;
            }
            public void setWujiangNameArry(String[] v ){
                wujiangNameArry = v;
            }
            public short[]  getWujiangLevelArry(){
                 return wujiangLevelArry;
            }
            public void setWujiangLevelArry(short[] v ){
                wujiangLevelArry = v;
            }
            public int[]  getWujiangQualityArry(){
                 return wujiangQualityArry;
            }
            public void setWujiangQualityArry(int[] v ){
                wujiangQualityArry = v;
            }
            public int[]  getWujiangHeadIdArry(){
                 return wujiangHeadIdArry;
            }
            public void setWujiangHeadIdArry(int[] v ){
                wujiangHeadIdArry = v;
            }
            public int[]  getWujiangCurExpArry(){
                 return wujiangCurExpArry;
            }
            public void setWujiangCurExpArry(int[] v ){
                wujiangCurExpArry = v;
            }
            public int[]  getWujiangMaxExpArry(){
                 return wujiangMaxExpArry;
            }
            public void setWujiangMaxExpArry(int[] v ){
                wujiangMaxExpArry = v;
            }
            public int[]  getWujiangAttackArry(){
                 return wujiangAttackArry;
            }
            public void setWujiangAttackArry(int[] v ){
                wujiangAttackArry = v;
            }
            public int[]  getWujiangDefenseArry(){
                 return wujiangDefenseArry;
            }
            public void setWujiangDefenseArry(int[] v ){
                wujiangDefenseArry = v;
            }
            public int[]  getWujiangHpArry(){
                 return wujiangHpArry;
            }
            public void setWujiangHpArry(int[] v ){
                wujiangHpArry = v;
            }
            public int[]  getWujiangOwnArry(){
                 return wujiangOwnArry;
            }
            public void setWujiangOwnArry(int[] v ){
                wujiangOwnArry = v;
            }
            public int[]  getWujiangCurPhysicalArry(){
                 return wujiangCurPhysicalArry;
            }
            public void setWujiangCurPhysicalArry(int[] v ){
                wujiangCurPhysicalArry = v;
            }
            public int[]  getWujiangMaxPhysicalArry(){
                 return wujiangMaxPhysicalArry;
            }
            public void setWujiangMaxPhysicalArry(int[] v ){
                wujiangMaxPhysicalArry = v;
            }
            public byte[]  getWujiangSexArry(){
                 return wujiangSexArry;
            }
            public void setWujiangSexArry(byte[] v ){
                wujiangSexArry = v;
            }
            public int[]  getWujiangWarriorGongbingArry(){
                 return wujiangWarriorGongbingArry;
            }
            public void setWujiangWarriorGongbingArry(int[] v ){
                wujiangWarriorGongbingArry = v;
            }
            public int[]  getWujiangWarriorBubingArry(){
                 return wujiangWarriorBubingArry;
            }
            public void setWujiangWarriorBubingArry(int[] v ){
                wujiangWarriorBubingArry = v;
            }
            public int[]  getWujiangWarriorQibingArry(){
                 return wujiangWarriorQibingArry;
            }
            public void setWujiangWarriorQibingArry(int[] v ){
                wujiangWarriorQibingArry = v;
            }
            public String[]  getWujiangDesArry(){
                 return wujiangDesArry;
            }
            public void setWujiangDesArry(String[] v ){
                wujiangDesArry = v;
            }
     
       public short getArraySize(){
             return size;
       }
     





 



}
