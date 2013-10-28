
package com.protocol.response;

 
 
 

 


import com.protocol.response.ack.AccoutVerifyAck;
import com.protocol.response.ack.LogicServListAck;




/**
 * @author 霍心迪  QQ:175838261
 * @version 创建时间：2012-12-14 17:37
 * 
 */
 
 
 

public class AccoutVerify  {
    public static void handle(AccoutVerifyAck  sp ){
    	sp.decode();
    	String[] strName = sp.getAreanameArry();
    	String[] strUrl = sp.getAreaurlArry();
    	LogicServListAck[] array = sp.getServlistArry();
    	for(int i = 0; i < strName.length; i ++)
    	{
    		System.out.println("strName :"+strName[i]);
    	}
    	for(int i = 0; i < strUrl.length; i ++)
    	{
    		System.out.println("strUrl :"+strUrl[i]);
    	}
    	for(int i = 0; i < array.length; i ++)
    	{
    		for(int j = 0; j < array[i].getServurlArry().length; j ++)
    		{
    			System.out.println("array :"+array[i].getServurlArry()[j]);
    		}
    	}
    	
    }
}
