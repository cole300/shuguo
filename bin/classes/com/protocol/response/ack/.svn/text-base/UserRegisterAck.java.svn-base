



package com.protocol.response.ack;

 
 
 

import java.util.ArrayList;
import com.net.Response;
import com.protocol.AckBean;
import com.protocol.response.UserRegister ;
import com.protocol.response.ack.AccoutVerifyAck ; 




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-17 18:59
 * 
 */
 
 
 
 



public class UserRegisterAck extends AckBean  {

    private Response response;
  
  
  
  {
     PROT_ID = 3;
  }
  
    public void handle(){
		UserRegister.handle(this);
	}
  
  
 
 
 private AccoutVerifyAck  servinfo; //登陆验证回应-区服信息
 
 



  public UserRegisterAck(Response v){
        response = v;  
    }
    
    
    public void decode(){
    
      {
                byte cmd = response.readByte();
                AccoutVerifyAck tmp = new AccoutVerifyAck(response);
                tmp.decode();
                servinfo = tmp;
            }    
    
     
    }








    public AccoutVerifyAck getServinfo(){
          return servinfo;
    }
           
    public void setServinfo(AccoutVerifyAck v){
          servinfo = v;
    }     
     



 



}
