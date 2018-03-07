package dtu;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import dtu.Checksum;
import dtu.Instructions;

public class lookUp extends Thread {
	long now_date,last_date;
	Socket socket=null;
	OutputStream os = null;
	Checksum checksum = new Checksum(); 
	byte[] sc = new byte[100];
	public void run(){
		while(true){
			/*
			 * 每10秒轮询一次
			 */
			try {
				sleep(20000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("----轮询中----");
			now_date=new Date().getTime();
			synchronized(WorkThread.hashlock){
				chaxun();
			}
		}
	}
	public void chaxun(){
		for(Node node:startServer.hm){
			last_date=node.getTime();
			this.socket=node.getSocket();
		}
		try {
			os = socket.getOutputStream();
			String s = checksum.makeChecksum(Instructions.READ.replace(" ", ""));
			sc = checksum.decode(s);
			os.write(sc);
			os.flush();
			String xw = checksum.makeChecksum(Instructions.XW.replace(" ", ""));
			sc = checksum.decode(xw);
			// 判断当前是否处于连接
			os.write(sc);
			os.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(now_date-last_date>30000){
			if(socket!=null){
				try {
					socket.close();
					for(Node node:startServer.hm){
						startServer.hm.remove(node.getTime());
						startServer.hm.remove(node.getSocket());
						node.setTime(0);
						node.setSocket(null);
					}
					System.out.println("已关闭超时连接");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else{
				System.out.println("无在线客户端");
			}
		}
	}
}
