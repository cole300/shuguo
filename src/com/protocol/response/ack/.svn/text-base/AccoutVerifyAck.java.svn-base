



package com.protocol.response.ack;

 
 
 

import java.util.ArrayList;
import com.net.Response;
import com.protocol.AckBean;
import com.protocol.response.AccoutVerify ;
import com.protocol.response.ack.LogicServListAck ; 




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 



public class AccoutVerifyAck extends AckBean  {

    private Response response;
  
  
  
  {
     PROT_ID = 1;
  }
  
    public void handle(){
		AccoutVerify.handle(this);
	}
  
  
  
 private long  uid; //账号id
  
 private long  sid; //sessionid
  
 
 
 
 
     
            private String[]  areanameArry; //区服务器名
            private String[]  areaurlArry; //区服务http地址
           private LogicServListAck[]  servlistArry; //线服务器列表
     
     private short size = 0;  //数组个数



  public AccoutVerifyAck(Response v){
        response = v;  
    }
    
    
    public void decode(){
        uid = response.readLong() ;
        sid = response.readLong() ;
    
     {
         size = 0;
         size = response.readShort();
        if(size > 0){
        
             areanameArry = new String[size];
             areaurlArry = new String[size];
            servlistArry = new LogicServListAck[size];
            for(int i = 0; i< size; i++)
            {
              int tmpCurrPos = response.getCurrPos();
              int tmpArrayLen = response.readShort();
            
             areanameArry[i] = response.readUTF() ;
             areaurlArry[i] = response.readUTF() ;
           {
            byte cmd = response.readByte();
           LogicServListAck tmp = new LogicServListAck(response);
           tmp.decode();
           
           servlistArry[i]= tmp;
           }
 
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


 public long  getUid(){
    return uid;
 }
 
  public void setUid(long  v ){
    uid = v;
 }

 
 public long  getSid(){
    return sid;
 }
 
  public void setSid(long  v ){
    sid = v;
 }

 




     
            public String[]  getAreanameArry(){
                 return areanameArry;
            }
            public void setAreanameArry(String[] v ){
                areanameArry = v;
            }
            public String[]  getAreaurlArry(){
                 return areaurlArry;
            }
            public void setAreaurlArry(String[] v ){
                areaurlArry = v;
            }
           
           public LogicServListAck[] getServlistArry(){
               return servlistArry;
           }
           
           public void setServlistArry(LogicServListAck[] v){
                servlistArry = v;
           }
     
       public short getArraySize(){
             return size;
       }
     





 



}
