package Ui;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginFrame extends JFrame {
	
	public LoginFrame() {
		//테스트를 위한 임시 데이터
		
		setTitle("로그인");	//타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//윈도우 닫으면 프로그램 종료
		Container c = getContentPane();	//컨텐트팬을 알아냄
		c.setLayout(null);	//배치관리자 제거
		
		JLabel JL1 = new JLabel("I D  ");
		JL1.setBounds(30, 45, 70, 15);
		c.add(JL1);
		
		JTextField TField = new JTextField(25);	//창의 열 개수 25
		TField.setBounds(70, 40, 250, 30);	//(x, y, 폭, 높이)
		c.add(TField);	
		
		JLabel JL2 = new JLabel("P W  ");
		JL2.setBounds(30, 80, 47, 15);
		c.add(JL2);
		
		JPasswordField PField = new JPasswordField(25);
		PField.setBounds(70, 75, 250, 30);
		c.add(PField);
		
		JButton button1 = new JButton("로그인");
		button1.setBounds(330, 40, 100, 65);
		c.add(button1);
		
		JButton Findid = new JButton("ID 찾기");
		Findid.setBounds(70, 120, 100, 30);
		c.add(Findid);
		
		JButton Findpw = new JButton("PW 찾기");
		Findpw.setBounds(175, 120, 100, 30);
		c.add(Findpw);
		
		JButton Newuse = new JButton("회원가입");
		Newuse.setBounds(280, 120, 100, 30);
		c.add(Newuse);
		
		
		//이벤트
		
		Findid.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {	//ID 찾기 버튼 클릭시 호출되는 메소드
				dispose();	//현재 프레임 종료
				new Findid(); //+ID 찾기 창 열기
			}
		});
		
		Findpw.addActionListener(new ActionListener() {	//PW 찾기 버튼 클릭시 호출
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FindPs(); //pw 찾기 창 열기
			}
		});
		
		button1.addActionListener(new ActionListener() {	//로그인 버튼을 누를 시 이벤트 발생
			public void actionPerformed(ActionEvent e) {
				
				String id = TField.getText();
				String pw = new String(PField.getPassword());
				LoginDAO dao = LoginDAO.getInstance();
				int res = dao.findByUserIdAndPw(id, pw);
				
				if(res == 1) {
					JOptionPane.showMessageDialog(null, "로그인되었습니다");
					dispose();
					new LocationGUI();
				}
				else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}				
				
			}
		});
		
		Newuse.addActionListener(new ActionListener() {	//회원가입 버튼을 누를 시 이벤트 발생
			public void actionPerformed(ActionEvent e) {
				dispose();
				new JoinnClo();	//회원가입창 띄우기
			}
		});
	
		setSize(470, 220);	//프레임 크기 
		setLocationRelativeTo(null);	//프레임 위치 가운데 배치
		setResizable(false);	//창 크기 고정
		setVisible(true);	//화면에 프레임 출력
	}

	public static void main(String[] args) {
		new LoginFrame();

	}

}