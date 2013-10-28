package com.net;

import java.util.Vector;

import com.protocol.HandleResponse;
import com.protocol.request.AccoutVerifyReq;
import com.protocol.request.UserRegisterReq;

public class TestHttp {

	/**
	 * @param args
	 */
	public static void test(String userName, String password) {
//		final Http http = new Http("http://192.168.2.186:8080/SanGuoServer/Start");
		final Http http = new Http("http://192.168.2.191:8080/SanGuoServer/Start");
		UserRegisterReq req = new UserRegisterReq();
		req.setUsername(userName);
		req.setPasswd(password);
		req.setVersion("99.99.99.99");
		req.setEmail("aa@qq.com");
		req.encode(false, http);
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					Vector v = http.getNetResponseVector();

					while (v.size() > 0) {
						Response resp = (Response) v.remove(0);
						HandleResponse.getInstance().handle(resp);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
	}
	
	public static void login(String account, String password, String version){
		final Http http = new Http("http://192.168.2.191:8080/SanGuoServer/Start");
		AccoutVerifyReq req =  new AccoutVerifyReq();
		req.setAccount(account);
		req.setPasswd(password);
		req.setVersion(version);
		req.encode(false, http);
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					Vector v = http.getNetResponseVector();

					while (v.size() > 0) {
						Response resp = (Response) v.remove(0);
						HandleResponse.getInstance().handle(resp);
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
	}

}
