package dtu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SignThread extends Thread{
	Socket socket=null;
	public SignThread(Socket socket){
		this.socket=socket;
	}
	public void run(){
		InputStream is=null;
		OutputStream os=null;
		String data=null;
		Checksum checksum=new Checksum();
		byte[] b =new byte[1024];
		byte[] c =new byte[1024];
		try {
			is=socket.getInputStream();
			is.read(b);
			data=new String(b);
			//²éÑ¯ÊÇ·ñ×¢²á
			os=socket.getOutputStream();
			String s = "3A 3A 01 00 00 00 10 00 00 ".replace(" ", "");
			System.out.println(checksum.makeChecksum(s));
			c=checksum.decode(checksum.makeChecksum(s));
			os.write(c);
			System.out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
