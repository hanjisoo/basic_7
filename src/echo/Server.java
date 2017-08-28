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
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(); // 이름정해주고

		serverSocket.bind(new InetSocketAddress("192.168.1.12", 10001)); // 암기//bide가 지키고있어
																			// ip랑 port번호//cmd에 ipconfig치면 ip번호나와
		System.out.println("#################");
		System.out.println("<서버시작>");
		System.out.println("#################");
		System.out.println("[연결을 기다리고 있습니다.]"); // 클라이언트 접속대기중

		while (true) {
			Socket socket = serverSocket.accept();// 연결기다리고 똑똑하면 socket이랑 연결

			Thread thread = new ServerThread(socket);//나가
			thread.start();
		}

	}
}
