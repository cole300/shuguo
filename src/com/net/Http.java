package com.net;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.CRC32;

import com.net.io.Bits;
import com.net.io.ByteArrayDataInputStream;

public class Http implements Runnable {
	public static Hashtable blocks = new Hashtable(5);
	// 唯一的标识通讯连接的session id
	public static long s_sessionId = 0;
	// 统一计数器
	public static int counter = 0;
	// CRC32校验
	public static CRC32 crc32 = new CRC32();
	// 请求队列
	private Vector netRequestVector = new Vector();
	// 响应队列
	private Vector netResponseVector = new Vector();
	// 关闭
	private boolean isClosed;
	private String url = "";
	private HttpURLConnection conn;
	private DataOutputStream dos;
	private DataInputStream dis;

	public static long getCRC(byte[] b) {
		crc32.reset();
		crc32.update(b);
		return crc32.getValue();
	}

	public Http(String url) {
		this.url = url;
		Thread t = new Thread(this);
		t.start();
	}

	public void close() {
		isClosed = true;
		synchronized (netRequestVector) {
			netRequestVector.notifyAll();
		}
	}

	public void run() {
		while (!isClosed) {
			int csId = -1;
			try {
				synchronized (netRequestVector) {
					if (netRequestVector.size() == 0)
						netRequestVector.wait();
				}
				if (isClosed)
					break;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				URL Url = null;
				try {
					Url = new URL(url);
				} catch (Exception e) {
					debug("Malformed URL Exception:" + e.getMessage());
					e.printStackTrace();
				}
				if (Url != null) {
					try {
						conn = (HttpURLConnection) Url.openConnection();
						conn.setDoOutput(true);
						conn.setDoInput(true);
						conn.setRequestMethod("POST");
						conn.setUseCaches(false);
						conn.setInstanceFollowRedirects(true);
						conn.setRequestProperty("Connection", "Keep-Alive");
						conn.connect();
					} catch (Exception e) {
						debug("connect exception..." + e.getMessage());
						e.printStackTrace();
					}
				}
				dos = new DataOutputStream(baos);
				dos.writeLong(s_sessionId);
				while (true) {
					Request request = null;
					synchronized (netRequestVector) {
						if (netRequestVector.size() > 0)
							request = (Request) netRequestVector.elementAt(0);
					}

					if (request == null)
						break;
					dos.writeInt(counter); // 写计数值
					request.writeToHttp(dos, counter);
					synchronized (netRequestVector) {
						netRequestVector.removeElement(request);
					}
					counter++;
				}
				dos = new DataOutputStream(conn.getOutputStream());
				byte buff[] = baos.toByteArray();
				int blen = buff.length;
				int bvalue = (int) getCRC(buff);
				dos.writeInt(blen);
				dos.writeInt(bvalue);
				dos.write(buff, 0, buff.length);
				baos.reset();
				dos.close();
				dos = null;
				int rc = conn.getResponseCode();
				if (rc != 200) {
					debug("rc=" + rc);
					Thread.sleep(2000);
				} else {

					int contentlen = conn.getContentLength();
					byte[] data = new byte[contentlen];
					conn.getInputStream().read(data);
					if (conn.getContentEncoding() != null && conn.getContentEncoding().equals("gzip"))// 解压缩
					{
						data = new Decode().inflate(data);
					}
					dis = new DataInputStream(new ByteArrayDataInputStream(data));
					int count;
					while (true) {// 读计数值
						try {
							count = dis.readInt();
						} catch (Exception e) {
							count = -1;
						}
						if (count < 0)
							break;
						Object o = blocks.get(String.valueOf(count));
						if (o != null) {
							// 结果已经返回，去掉等待
							blocks.remove(String.valueOf(count));
						}
						int len = dis.readInt();
						if (len == 0) {
							continue;
						} else if (len > 0) {
							byte[] buf = Bits.read(dis, len);
							try {
								Response resp = Response.newResponse(buf);
								netResponseVector.addElement(resp);
								// Thread.sleep(20);
							} catch (Exception ex) {
								ex.printStackTrace();
								showErrorMsg("接收失败" + csId, 0);
							}
						}
					}
				}
			} catch (IOException ex) {
				quitGame("联网失败" + ex.getMessage(), 0);
			} catch (Exception ex) {
				quitGame("联网失败" + ex.getMessage(), 1);
			} finally {
				try {
					if (dis != null) {
						dis.close();
						dis = null;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.disconnect();
						conn = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized boolean sendMessage(Request request, boolean block) {
		synchronized (netRequestVector) {
			netRequestVector.addElement(request);
			netRequestVector.notifyAll();
		}
		return true;
	}

	public Vector getNetRequestVector() {
		return netRequestVector;
	}

	public void setNetRequestVector(Vector netRequestVector) {
		this.netRequestVector = netRequestVector;
	}

	public Vector getNetResponseVector() {
		return netResponseVector;
	}

	public void setNetResponseVector(Vector netResponseVector) {
		this.netResponseVector = netResponseVector;
	}

	public static void debug(String str) {
		System.out.println(str);
	}

	public static void showErrorMsg(String str, int type) {
		System.out.println(str);
	}

	public static void quitGame(String str, int type) {
		System.out.println(str);
	}
}
