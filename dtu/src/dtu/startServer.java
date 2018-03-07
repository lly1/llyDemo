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
	static int num_thread=0;//��ǰ������
	private ServerSocket server;
	@Override
	public void run() {
		// TODO Auto-generated method stub
			//1.������������socket,��ServerSocket,����ָ���Ķ˿ڣ����ڶ˿ڽ��м���
			server=bind_port(8000);//�󶨶˿�
			while(true){
				Socket socket=null;
				// 2.ͨ��accept()�����Կͻ��˽��м���,�ȴ��ͻ��˵�����
				try {
					socket = server.accept();// ���ص�ʵ��������ͻ��˽���ͨ��
					WorkThread h=new WorkThread(socket);
					Thread workThread=new Thread(h);
					workThread.start();
					num_thread++;
					System.out.println("�ͻ�������: " + num_thread);
					InetAddress address = socket.getInetAddress();
					System.out.println("��ǰ�ͻ��˵�IP: " + address.getHostAddress());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
	}
	/***
	 * 
	 * ��������
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
			System.out.println("��ʼ��������");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�˿��ѱ�ռ��");
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
