package unicasting;
//콘솔기반의 서버이용이 불편함으로 그래픽모드로 개발하자!!

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UniServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;// 접속 청취용 서버소켓!! (대화용아님)
	Thread thread;// 지연 대기, 루프등에 사용하기위함!!
					// 왜? 메인 쓰레드는 중요하니깐..운영자이니깐..

	Vector<ServerThread> vec;
	public UniServer() {
		p_north = new JPanel();
		t_port = new JTextField("7777");
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		thread = new Thread() {
			public void run() {
				// 개발자는 독립수행할 코드를 run에 넣어두면
				// JVM 알아서 실행해준다..(단 runnable 에 밀어넣어야함)
				startServer();
			}
		};
		vec = new Vector<ServerThread>();
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// startServer();//서버가동 시작!!
				thread.start();

			}
		});

		setVisible(true);
		setSize(300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void startServer() {
		int port = Integer.parseInt(t_port.getText());
		try {
			server = new ServerSocket(port);
			bt.setEnabled(false);
			area.append("서버가동 시작!!\n");
			area.append("클라이언트 접속 기다리는 중..\n");
			
			while (true) {
				Socket client = server.accept();// 클라이언트 접속 청취(대기,지연)
				// 자바는 쓰레드기반이기때문에, 우리가 지금까지 실행부라고 불렀던 그 실행주체가 바로
				// main thread였다.. 메인 쓰레드의 역할은 프로그램을 운영하는 역할
				// 특히 GUI프로그램에서는 사용자의 이벤트 감지, 그래픽 랜더링 등등..
				// 따라서 메인 쓰레드를 대기,루프, 지연 상태 빠트리면, 프로그램 자체가 불통
				// 안드로이드에서는 컴파일 에러대상일 정도다..
				String ip = client.getInetAddress().getHostAddress();
				area.append(ip + "님 접속\n");
				//접속자가 발생했으므로, 쓰레드를 생성하여 대화를 나누게 해주자
				ServerThread st=new ServerThread(client,this);
				st.start();//대화 시작!!!
				//접속자 명단에 쓰레드 담기!!
				vec.add(st);
				
				area.append("현재접속자수는"+vec.size()+"\n");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UniServer();
	}
}
