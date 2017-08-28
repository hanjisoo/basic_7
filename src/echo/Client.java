
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
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();

		System.out.println("**************");
		System.out.println("<클라이언트 시작>");
		System.out.println("**************");
		socket.connect(new InetSocketAddress("192.168.1.12", 10001));// socket이 주소에 연결(똑똑)
		System.out.println("[서버에 연결되었습니다]");

		// 메세지 보내기 스트림준비 outputStream,writer,bufferedWriter

		OutputStream os = socket.getOutputStream();// 이미 socket 꽂혀있으니깐 new안함
		Writer osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지받기 스트림준비

		InputStream is = socket.getInputStream();
		Reader isr = new InputStreamReader(is, "UTF-8");	//모스부호처럼 들어오니깐 UTF-8파일로 변환
		BufferedReader br = new BufferedReader(isr);		//모아주고 쓩~ 정해진 거야

		// 키보드쳐준거 보내줄꺼야/스캐너연결
		Scanner sc = new Scanner(System.in);

		while (true) {	//계속 써서 보내고 받고 싶어서 반복문 사용
			// 메세지 보내기
			String msg = sc.nextLine();
			/* String msg="안녕 서버얌~"; */
			if (msg.equals("/q")) {
				break;
			}

			bw.write(msg);
			bw.newLine();// 줄바꿈
			bw.flush();// 가랏
			System.out.println(msg + "메세지전송 성공");

			// 메세지받기
			msg = br.readLine();// br이 받아
			System.out.println("받은메세지" + msg);
		}
		System.out.println("클라이언트 종료");

		socket.close();
	}
}
