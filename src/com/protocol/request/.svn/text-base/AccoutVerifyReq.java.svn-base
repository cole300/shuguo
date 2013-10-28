
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
 
 
 
 


public class AccoutVerifyReq extends RequestBean {

	private String  account; //账号
	private String  passwd; //密码
	private String  version; //版本 格式：格式：版本前缀.当前版本号.机型(ios通用版：1,iphone:2,ipad:3).渠道(appstore:1、91：2) .mac.具体机型 例子：0.60.1.2.0800270094C1.iphone31
 
{
	 PROT_ID=1 ;
}

  	public AccoutVerifyReq()
  	{
  	}
    
    public void encode(boolean block, Http http)
    {
       Request request = Request.newRequest(PROT_ID);
        	   request.writeUTF(account);
               request.writeUTF(passwd);
               request.writeUTF(version);
               request.send(block, http); 
    }

    public String  getAccount(){
    	return account;
    }
 
    public void setAccount(String  v ){
	    account = v;
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

}
