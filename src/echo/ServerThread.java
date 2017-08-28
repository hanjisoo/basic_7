package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket socket;

	public ServerThread(Socket socket) {
	this.socket=socket;
	}
	@Override
	public void run() {
		
		try {
			System.out.println("[연결되었습니다]");
		
			//메세지받기 스트림준비
			InputStream is=socket.getInputStream();
			Reader isr=new InputStreamReader(is,"UTF-8");
			BufferedReader br=new BufferedReader(isr);
				
			//메세지보내기 스트림준비
			OutputStream os=socket.getOutputStream();
			Writer osw=new OutputStreamWriter(os,"UTF-8");
			BufferedWriter bw=new BufferedWriter(osw);
			
			while(true) {
				//메세지 받기
			String msg=br.readLine();
			if(msg==null) {
				break;
			}
			System.out.println("server "+msg);
		
		//메세지 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush();
			System.out.println("보낸메세지 "+msg);
			}
					socket.close();
		}
		catch(IOException e) {
		System.out.println(e);
		}
	
	}
}
