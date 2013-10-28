package com.protocol;

import com.net.Response;
import com.protocol.response.UserRegister;
import com.protocol.response.ack.AccoutVerifyAck;
import com.protocol.response.ack.UserRegisterAck;

/**
 * @author ���ĵ� QQ:175838261
 * @version ����ʱ�䣺2012-12-4 ����5:51:14
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
		// ������ʾ����
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
