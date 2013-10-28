
package com.protocol.request;

 
import com.net.Http;
import java.util.ArrayList;
import com.net.Request;
import com.protocol.RequestBean;




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 


public class BeatDataReq extends RequestBean {

  
    
  
 private String  msg; //心跳信息
 
 





{
	 PROT_ID=0 ;
}


  public BeatDataReq(){
    }
    
    
    
    
    
    public void encode(boolean block, Http http){
       Request request = Request.newRequest(PROT_ID);
        request.writeUTF(msg);
     request.send(block, http); 
    }


 public String  getMsg(){
    return msg;
 }
 
  public void setMsg(String  v ){
    msg = v;
 }

 













}
