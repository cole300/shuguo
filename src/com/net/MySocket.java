package com.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

public class MySocket {

	public static MySocket instance;
	public static MySocket getInstance()
	{
		if(instance == null)
		{
			instance = new MySocket();
		}
		
		return instance;
	}
	
	public static final int port = 8080;
	public static final String url = "192.168.2.121";
	
	public void connect(String type, JSONObject jsonObj)
	{
		Socket socket = null;
		try{
			socket = new Socket(url, port);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			
			out.println(jsonObj);
			out.flush();
			
			System.out.println(in.readLine());
			socket.close();
			
		}catch(IOException e){e.printStackTrace();}
	}
	
	
}
