package com.net;

import com.net.io.ByteArrayDataInputStream;

/**
 * ������������Ϣ
 * 
 */
public class Response {
	/**
	 * Э���
	 */

	private int protocolID;
	private String errorMessage = null;
	private final ByteArrayDataInputStream bais;

	public static Response newResponse(byte[] data) {
		return new Response(data, 0, data.length);
	}

	static Response newResponse(byte[] data, int off, int len) {
		return new Response(data, off, len);
	}

	public Response(byte[] data) {
		bais = new ByteArrayDataInputStream(data, 0, data.length);
	}

	Response(byte[] data, int off, int len) {
		this.size = len;
		bais = new ByteArrayDataInputStream(data, off, len);
		this.protocolID = bais.read();
		bais.mark(available());
		if (this.protocolID > 226) {
			this.protocolID = this.protocolID - 256;
			if (this.protocolID < 0) {
				errorMessage = readUTF();
			}
		} else {

		}
		// #if DEBUG
		sb.append("����Э�� [MSG][" + protocolID + "] Read:");
		sbLength = sb.length();
		// #endif
	}

	/**
	 * ������
	 */
	private String msg = null;
	int size;

	public final int getCurrPos() {
		return bais.getCurrPos();
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ��byteֵ
	 * 
	 * @return
	 */
	public final byte readByte() {
		byte b = bais.readByte();
		// #debug
		print("Byte", String.valueOf((byte) b));
		return b;
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ��shortֵ
	 * 
	 * @return
	 */
	public final short readShort() {
		short s = bais.readShort();
		// #debug
		print("Short", String.valueOf(s));
		return s;
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ��intֵ
	 * 
	 * @return
	 */
	public final int readInt() {
		// #if DEBUG
		int i = bais.readInt();

		print("Int", String.valueOf(i));
		return i;
		// #else
		// # return bais.readInt();
		// #endif
	}

	public final boolean readBoolean() {
		boolean is = bais.readBoolean();
		// #debug
		print("Boolean", String.valueOf(is));
		return is;

	}

	public final int read() {
		int i = bais.read();
		// #debug
		print("Int", String.valueOf(i));
		return i;
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ��longֵ
	 * 
	 * @return
	 */
	public final long readLong() {
		long l = bais.readLong();
		// #debug
		print("Long", String.valueOf(l));
		return l;
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ��UTF-8������ַ���
	 * 
	 * @return
	 */
	public final String readUTF() {
		StringBuffer buf = new StringBuffer();

		int len = bais.readShort();

		for (int i = 0; i < len; i++)
			buf.append(bais.readChar());
		// #debug
		print("UTF", buf.toString());
		return buf.toString();
	}

	public final char readChar() {
		return bais.readChar();
	}

	/**
	 * �ӷ�����������Ϣ���ȡ�ֽ����ָ���ֽ�����
	 * 
	 * @param b
	 */
	public final void readFully(byte[] b) {
		readFully(b, 0, b.length);
	}

	/**
	 * �ӷ�����������Ϣ���ȡ�ֽ����ָ���ֽ�����
	 * 
	 * @param b
	 */
	public final byte[] readFileData() {
		int count = readInt();
		if (count < 0)
			return null;
		byte filedata[] = new byte[count];
		readFully(filedata);
		return filedata;
	}

	/**
	 * �ӷ�����������Ϣ���ȡ�ֽ����ָ���ֽ������ָ������
	 * 
	 * @param b
	 *            �ֽ�����
	 * @param off
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 */
	public final void readFully(byte[] b, int off, int len) {

		bais.readFully(b, off, len);
		// #debug
		print("ByteArray", "len=" + len);
	}

	public final int read(byte[] b) {
		return bais.read(b);
	}

	/**
	 * �ӷ�����������Ϣ���ȡһ���޷���byteֵ
	 * 
	 * @return
	 */
	/*
	 * public final int readUnsignedByte() { // #if DEBUG int b =
	 * bais.readUnsignedByte();
	 * 
	 * print("UnsignedByte", String.valueOf(b)); return b; // #else // # return
	 * bais.readUnsignedByte(); // #endif }
	 *//**
	 * �ӷ�����������Ϣ���ȡһ���޷���shortֵ
	 * 
	 * @return
	 */
	/*
	 * public final int readUnsignedShort() { // #if DEBUG int s =
	 * bais.readUnsignedShort();
	 * 
	 * print("UnsignedShort", String.valueOf(s)); return s; // #else // # return
	 * bais.readUnsignedShort(); // #endif }
	 */
	/**
	 * �ӷ�����������Ϣ������ָ���ֽ���
	 * 
	 * @param n
	 * @return
	 */
	public final int skipBytes(int n) {
		// #if DEBUG

		n = bais.skipBytes(n);

		print("Skip", String.valueOf(n));
		return n;
		// #else
		// # return bais.skipBytes(n);
		// #endif
	}

	/**
	 * ���ö�ȡλ�õ���Ϣ��ʼλ��
	 */
	public final void reset() {
		// #debug
		sb.setLength(sbLength);

		bais.reset();

	}

	/**
	 * �ر�
	 */
	public final void close() {
		// #if DEBUG
		print("Close", "");
		Http.debug(sb.toString());
		// #endif
	}

	/**
	 * ������Ϣ��ʣ����ֽ���
	 * 
	 * @return
	 */
	public final int available() {
		return bais.available();

	}

	/**
	 * ���ش�����Ϣ
	 * 
	 * @return
	 */
	public final String getErrorMsg() {
		return msg;
	}

	// #if DEBUG
	StringBuffer sb = new StringBuffer();
	int sbLength;

	private void print(String str, String value) {
		sb.append("[" + str + "]" + value);
	}

	// #endif

	public int getProtocolID() {
		return protocolID;
	}

	public void setProtocolID(int protocolID) {
		this.protocolID = protocolID;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}