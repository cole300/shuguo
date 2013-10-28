



package com.protocol.response.ack;

 
 
 

import java.util.ArrayList;
import com.net.Response;
import com.protocol.AckBean;
import com.protocol.response.LogicServList ;




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 



public class LogicServListAck extends AckBean  {

    private Response response;
  
  
  
  {
     PROT_ID = 2;
  }
  
    public void handle(){
		LogicServList.handle(this);
	}
  
  
 private String  chaturl; //聊天http地址
  
 
 
 
 
     
            private String[]  servurlArry; //逻辑服务器http地址
     
     private short size = 0;  //数组个数



  public LogicServListAck(Response v){
        response = v;  
    }
    
    
    public void decode(){
        chaturl = response.readUTF() ;
    
     {
         size = 0;
         size = response.readShort();
        if(size > 0){
        
             servurlArry = new String[size];
            for(int i = 0; i< size; i++)
            {
              int tmpCurrPos = response.getCurrPos();
              int tmpArrayLen = response.readShort();
            
             servurlArry[i] = response.readUTF() ;
 
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


 public String  getChaturl(){
    return chaturl;
 }
 
  public void setChaturl(String  v ){
    chaturl = v;
 }

 




     
            public String[]  getServurlArry(){
                 return servurlArry;
            }
            public void setServurlArry(String[] v ){
                servurlArry = v;
            }
     
       public short getArraySize(){
             return size;
       }
     





 



}
