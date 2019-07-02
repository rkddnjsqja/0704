package chatting.memory;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChatB  extends JFrame{
	//필요하면 보유하라!1
	JTextArea area;
	JScrollPane scroll;
	JTextField txt;
	JPanel p;
	ChatA chatA;//필요하면 보유하자!
	public ChatB(ChatA chatA) {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		txt = new JTextField(15);
		p = new JPanel();
		this.chatA = chatA;
		
		p.add(txt);
		//개발자가 레이아웃을 지정하지 않으면 에러가 아니라 
		//디폴트레이아웃이 적용됨..
		//자바 GUI모든 컴포넌트가 레이아웃 능력이 있는게 아니라,
		//컨테이너형만 레이아웃 능력이 있다..
		//모든 GUI컴포넌트의 분류
		//	남을 포함 할 수 있는 유형 : 컨테이너(Frame,Panel)
		//									남을 포함하기 위해 배치를 고민..
		//									Frame: BorderLayout
		//									Panel : FlowLayout
		// 포함되는 유형				:비주얼 컴포넌트(Button,check...)
		add(scroll, BorderLayout.CENTER);
		add(p,BorderLayout.SOUTH);
		
		
		//키보드 이벤트 구현
		txt.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//나와 너의 창에 문자열 뿌리기!
					String msg=txt.getText();//입력한 값!1
					area.append(msg+"\n");
					chatA.area.append(msg+"\n");
					txt.setText("");//입력텍스트 초기화
				}
			}
		});
		
		setVisible(true);
		setSize(300, 400);
		
	}
	
}
