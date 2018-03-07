package dtu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class startServer implements Runnable{
	
	static List<Node> hm=new ArrayList<Node>();
	static int num_thread=0;//当前连接数
	private ServerSocket server;
	@Override
	public void run() {
		// TODO Auto-generated method stub
			//1.创建服务器端socket,即ServerSocket,并绑定指定的端口，并在端口进行监听
			server=bind_port(8000);//绑定端口
			while(true){
				Socket socket=null;
				// 2.通过accept()方法对客户端进行监听,等待客户端的连接
				try {
					socket = server.accept();// 返回的实例用来与客户端进行通信
					WorkThread h=new WorkThread(socket);
					Thread workThread=new Thread(h);
					workThread.start();
					num_thread++;
					System.out.println("客户端数量: " + num_thread);
					InetAddress address = socket.getInetAddress();
					System.out.println("当前客户端的IP: " + address.getHostAddress());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
	}
	/***
	 * 
	 * 监听心跳
	 * @param port
	 * @return
	 */
	public ServerSocket bind_port(int port) {
		ServerSocket serverSocket=null;
		try {
			serverSocket=new ServerSocket();
			if(!serverSocket.getReuseAddress()){
				serverSocket.setReuseAddress(true); 
			}
			serverSocket.bind(new InetSocketAddress(port));
			System.out.println("开始监听心跳");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("端口已被占用");
			if(serverSocket!=null){
				if(!serverSocket.isClosed()){
					try {
						serverSocket.close();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		}
		return serverSocket;
	}
	public static void main(String[] args) {
		new Thread(new startServer()).start();
	}
}
