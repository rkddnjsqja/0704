package multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

//청취는 사용자가 어떠한 액션을 취하지 않더라도 , 무한루프로 실행해야
//하므로 현재 메인 쓰레드로 청취업무를 시켰다가는 프로그램은  멈춘다
//별도의 쓰레드가 필요하다
public class ClientThread extends Thread{
	ChatClient chatClient;
	BufferedReader buffr;
	BufferedWriter buffw;
	Socket client;
	JTextArea area;
	boolean flag=true;
	public ClientThread(ChatClient chatClient) {
		this.chatClient=chatClient;
		client = chatClient.client;
		area=chatClient.area;
		try {
			buffr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//내가 말을 걸을 않아도 서버로 부터 계속 청취가 가능하려면
	//while문으로 처리해야함
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void send(String msg) {
		//서버측에 메세지 전송!1
		
		try {
			buffw.write(msg+"\n");//문자열의 끝에 줄바꿈표시가 있어야 한줄의 끝을 이해한다!
			//따라서 서버측에서는 문자열의 끝이 없기 때문에 계속
			//대기하고 있음..
			buffw.flush();//버퍼처리된 출력스트림 계열에서 이 메서드를 호출하면
			//스트림에 현재 쌓여있는 데이터를 모두방출!!
			//입력한 데이터 초기화
			//listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//독립수행할 코드를 작성한다!!
	public void run() {
		while(flag) {
			listen();
		}
	
	}
}
