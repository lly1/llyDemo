package dtu;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import dtu.Instructions;

public class WorkThread implements Runnable{
	String data;
	static int pd = 1;
	boolean flag=true;
	DataInputStream in;
	Node node=new Node();
	private Socket socket;
	static Object hashlock=new Object();//对象锁，加static类似类锁
	public WorkThread(Socket socket){
		this.socket=socket;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		OutputStream os = null;
		Checksum checksum=new Checksum();
		byte[] sc = new byte[50];
		byte[] b = new byte[1024];
		try {
			// 获取输入流,并读取客户端的信息
			in=new DataInputStream(socket.getInputStream());
			os=socket.getOutputStream();
			System.out.println("用户已连接");
			while(true){
				in.read(b);
				data=new String(b);
				if(data.substring(0, 3).equals("log")){
					System.out.println("收到心跳");
					node.setTime(new Date().getTime());
					node.setSocket(socket);
					synchronized(hashlock){
						startServer.hm.add(node);
						lookUp lk=new lookUp();
						lk.start();
					}
				}
				else if(b[0] == 58 && b[1] == 58) {//3a3a
					if (data.substring(8, 9).equals("S")) {
						String scsn = checksum.bytesToHexFun2(b).substring(18, 50);
						String zc = checksum.makeChecksum((Instructions.ZC + scsn + "11").replace(" ", ""));
						System.out.println(zc);
						sc = checksum.decode(zc);
						os.write(sc);
						System.out.println("注册");
					} else {
						pd=1;
						System.out.println("读取数据！！");
						Convert convert=new Convert();
						InvertMsg invertMsg=new InvertMsg();
						invertMsg=convert.ConvertIM(checksum.bytesToHexFun2(b));
						System.out.println(checksum.bytesToHexFun2(b));
						
						System.out.println("读取完毕！！");

					}
			}
		}
	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("用户已断开连接");
			System.out.println(e);
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} 
	}
}
