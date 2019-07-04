package unicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JTextArea;

/* 
 * 접속자 마다, 각각 독립적으로 대화를 유지해야 하므로,
 * 하나의 서버내에 독립적으로 실행 될 세부실행단위인
 * 쓰레드로 구현하자!!
 * 접속자가 10명이면 10개의 쓰레드가 필요하므로, java
 * 로 정의한다!1
 * */
public class ServerThread extends Thread {

	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	JTextArea area;
	Vector vec;
	String ip;
	boolean flag = true;// 쓰레드 살아있게
	// 소켓은 새롭게 새성하면 안되고, 서버가 접속청취시 발생한
	// 바로 그 소켓을 넘겨받자!!

	public ServerThread(Socket client, UniServer uniServer) {
		this.client = client;
		this.area = uniServer.area;// 새로운 area가 아니라 서버가 가진 그 area
		this.vec = uniServer.vec;
		ip = client.getInetAddress().getHostAddress();
		ip = ip.substring(ip.lastIndexOf("."), ip.length());

		System.out.println(ip.split(".")[3]);

		// 접속자가 감지되면, 스트림을 뽑아서 대화를 진행 하자!!
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));// 입력 귀
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));// 출력 말
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 대화 처리코드(듣고,말하는것) ,입력 buffr
	public String listen() {
		String msg = null;
		try {
			msg = buffr.readLine();// 듣기
			area.append(ip + "님의 말:" + msg + "\n");
		} catch (IOException e) {
			// e.printStackTrace();
			flag = false;
			vec.remove(this);// 내가나감
			area.append(ip + "님이 나갔습니다, 현재 남은수는" + vec.size() + "\n");
		}
		return msg;
	}

	// 보낼 메세지를 매개변수로 받자!1
	public void send(String msg) {
		try {// 말하기
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 개발자는 독립 수행할 코드를 run에 재정의 해야하고, run의 닫는 브레이스를
	// 만나면 쓰레드는 생명주기 중 dead상태가 된다. 따라서 죽지않기 위해
	// 무한 루프로 처리하자!1
	public void run() {

		while (flag) {
			String msg = listen();
			if (flag) {
				send(msg);
			}
		}

	}
}
