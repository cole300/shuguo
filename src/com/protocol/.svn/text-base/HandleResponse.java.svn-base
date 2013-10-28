package com.protocol;

import com.net.Response;
import com.protocol.response.UserRegister;
import com.protocol.response.ack.AccoutVerifyAck;
import com.protocol.response.ack.UserRegisterAck;

/**
 * @author 霍心迪 QQ:175838261
 * @version 创建时间：2012-12-4 下午5:51:14
 * 
 */

public class HandleResponse {

	private static HandleResponse handle = null;

	public static HandleResponse getInstance() {
		if (handle == null)
			handle = new HandleResponse();
		return handle;
	}

	public void handle(Response resp) {
		// 错误提示处理
		if (resp.getProtocolID() < 0) {
			String error = resp.readUTF();
			switch (resp.getProtocolID()) {
			case -1:
				System.out.println(error);
				break;
			}
		} else {
			switch (resp.getProtocolID()) {

			case ProtocolDefine.P3_UserRegister:
				UserRegister.handle(new UserRegisterAck(resp));
				break;
			case ProtocolDefine.P1_AccoutVerify:
				com.protocol.response.AccoutVerify.handle(new AccoutVerifyAck(resp));
				break;
			}
		}
	}
}
