
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
 
 
 
 


public class EnterGameReq extends RequestBean {

  
    
  
 private long  uid; //账号id
 private long  sid; //sessionid
 
 





{
	 PROT_ID=4 ;
}


  public EnterGameReq(){
    }
    
    
    
    
    
    public void encode(boolean block, Http http){
       Request request = Request.newRequest(PROT_ID);
        request.writeLong(uid);
        request.writeLong(sid);
     request.send(block, http); 
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

 













}
