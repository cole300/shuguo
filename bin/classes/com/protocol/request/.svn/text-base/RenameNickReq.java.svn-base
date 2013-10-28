
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
 
 
 
 


public class RenameNickReq extends RequestBean {

  
    
  
 private String  newNick; //新的昵称
 
 





{
	 PROT_ID=6 ;
}


  public RenameNickReq(){
    }
    
    
    
    
    
    public void encode(boolean block, Http http){
       Request request = Request.newRequest(PROT_ID);
        request.writeUTF(newNick);
     request.send(block, http); 
    }


 public String  getNewNick(){
    return newNick;
 }
 
  public void setNewNick(String  v ){
    newNick = v;
 }

 













}
