package com.net;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 请求消息
 */
public class Request {
	ByteArrayOutputStream baos11 = new ByteArrayOutputStream();
	DataOutputStream out = new DataOutputStream(baos11);
	/**
	 * 协议号
	 */
	public final int protocolID;
	private final long timeOut = 30000; // 超时时间
	private boolean checked;

	/**
	 * 新建请求消息
	 * 
	 * @param protocolID
	 *            协议号
	 * @return
	 */
	public static Request newRequest(int protocolID) {
		return new Request(protocolID);
	}

	private Request(int protocolID) {
		this.protocolID = protocolID;
		try {
			out.writeByte(protocolID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// #debug
		sb.append("发送协议[MSG][" + protocolID + "]Write:");
	}

	/*
	 * 写入数据输出流(socket通讯方式)
	 */
	public void writeTo(DataOutputStream dos, int count) throws IOException {
		if (block) {
			// TODO 对需要等待返回结果的请求加入队列，需重写以下代码
			Object o = new Object();
			Http.blocks.put(String.valueOf(count), o);
		}
		dos.writeByte(count);
		dos.writeShort(baos11.size());
		dos.write(baos11.toByteArray(), 0, baos11.size());
		dos.flush();
		baos11.reset();
	}

	// public void writeToHttp(DataOutputStream dos, int count) throws
	// IOException {
	// if (block) {
	// Window w = CGame.newRequestBlock(protocolID);
	// w.setName(protocolID + "widnow" + count);
	// HR.debug("发送时候=" + count + "," + w.getName());
	// Net.blockDialog.put(String.valueOf(count), w);
	// }
	// dos.writeByte(count);
	// dos.writeShort(baos11.size());
	// dos.write(baos11.toByteArray(), 0, baos11.size());
	// dos.flush();
	// baos11.reset();
	// }

	/*
	 * 返回http协议长度
	 */
	int getHttpSize() {
		return baos11.size() - 4;
	}

	/*
	 * 写入http请求输出流
	 */
	public void writeToHttp(DataOutputStream dos, int count) throws IOException {
		if (block) {
			// TODO 对需要等待返回结果的请求加入队列，需重写以下代码
			Object o = new Object();
			Http.blocks.put(String.valueOf(count), o);
		}
		byte[] data = baos11.toByteArray();
		// 前面4位是2位shor协议号，2位长度
		dos.write(data, 0, data.length - 0);
	}

	void initTimeOut() {
		// timeOut = System.currentTimeMillis() + Network.getProtocolTimeOut();
		checked = false;
	}

	private boolean block;

	public final void send(boolean block, Http http) {
		this.block = block;
		// net.addElement(this);\
		if (http.sendMessage(this, block))
			initTimeOut();
		// #debug
		print("Close", "");
		// #debug
		Http.debug(sb.toString() + this.block);
	}

	/**
	 * 发送消息给其他网络
	 * 
	 * @param block
	 *            是否以阻塞方式发送
	 */
	public final void sendOtherNet(boolean block) {
		this.block = block;
		initTimeOut();
		// #debug
		print("Close", "");
		// #debug
		Http.debug(sb.toString() + this.block);

	}

	/* *//**
	 * 根据返回的协议号取消
	 */
	/*
	 * public final void send(boolean block, int protocolID) {
	 * 
	 * }
	 */
	/**
	 * 往请求信息里写入一个boolean值
	 * 
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public final Request writeBoolean(boolean value) {
		// #debug
		print("Boolean", String.valueOf(value));
		try {
			out.writeBoolean(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个byte值
	 * 
	 * @param value
	 * @return
	 */
	public final Request writeByte(int value) {
		// #debug
		print("Byte", value < Byte.MIN_VALUE || value > Byte.MAX_VALUE ? "[OverFlow]" + value : String.valueOf(value));
		try {
			out.writeByte(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个short值
	 * 
	 * @param value
	 * @return
	 */
	public final Request writeShort(int value) {
		// #debug
		print("Short", value < Short.MIN_VALUE || value > Short.MAX_VALUE ? "[OverFlow]" + value : String.valueOf(value));
		try {
			out.writeShort(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个int值
	 * 
	 * @param value
	 * @return
	 */
	public final Request writeInt(int value) {
		// #debug
		print("Int", String.valueOf(value));
		try {
			out.writeInt(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个long值
	 * 
	 * @param value
	 * @return
	 */
	public final Request writeLong(long value) {
		// #debug
		print("Long", String.valueOf(value));
		try {
			out.writeLong(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个UTF-8格式的字符串
	 * 
	 * @param str
	 * @return
	 */
	public final Request writeUTF(String str) {
		// #debug
		print("UTF", str);
		try {
			out.writeShort(str.length());
			out.writeChars(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * 往请求信息里写入一个byte[]
	 * 
	 * @param b
	 * @return
	 */
	public final Request write(byte[] b) {
		// #debug
		print("ByteArray", "len=" + b.length);
		try {
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 往请求信息里写入一个byte[]的一部分
	 * 
	 * @param b
	 *            字节数组
	 * @param off
	 *            开始位置
	 * @param len
	 *            长度
	 * @return
	 */
	public final Request write(byte[] b, int off, int len) {
		// #debug
		print("ByteArray", "len=" + len);
		try {
			out.write(b, off, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	// #if DEBUG
	StringBuffer sb = new StringBuffer();

	private void print(String str, String value) {
		sb.append("[" + str + "]" + value);
	}

	// #endif

	/**
	 * 判定协议是否发送超时
	 */
	public boolean checkTimeOut() {
		if (checked)
			return false;
		boolean timeOut = this.timeOut < System.currentTimeMillis();
		if (timeOut)
			this.checked = true;
		return this.checked;
	}

	public int size() {
		// TODO
		return 0;
	}
}