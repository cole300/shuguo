
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
 
 
 
 


public class UserRegisterReq extends RequestBean {

  
    
  
 private String  username; //账号
 private String  passwd; //密码
 private String  version; //版本
 private String  email; //email
 
 





{
	 PROT_ID=3 ;
}


  public UserRegisterReq(){
    }
    
    
    
    
    
    public void encode(boolean block, Http http){
       Request request = Request.newRequest(PROT_ID);
        request.writeUTF(username);
        request.writeUTF(passwd);
        request.writeUTF(version);
        request.writeUTF(email);
     request.send(block, http); 
     System.out.println("send request");
    }


 public String  getUsername(){
    return username;
 }
 
  public void setUsername(String  v ){
    username = v;
 }

 
 public String  getPasswd(){
    return passwd;
 }
 
  public void setPasswd(String  v ){
    passwd = v;
 }

 
 public String  getVersion(){
    return version;
 }
 
  public void setVersion(String  v ){
    version = v;
 }

 
 public String  getEmail(){
    return email;
 }
 
  public void setEmail(String  v ){
    email = v;
 }

 













}
