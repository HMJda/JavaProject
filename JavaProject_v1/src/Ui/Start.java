package Ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.swing.border.TitledBorder;

import Api.run;
import Ui.information.*;
import Ui.*;

public class Start extends JFrame{
	private JFrame mainframe;
	int count = 0;	//패널 순서
	information Information = new information();	//information 객체 생성
	Savepanel infopanel = Information.weatheradd();	//information의 Savepanel 클래스 값을 반환받음
	
	public Start() {
		mainframe = new JFrame();
		mainframe.setTitle("옷 추천 프로그램");

		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.getContentPane().setLayout(null);
		mainframe.setLocationRelativeTo(null);
		
		mainframe.setSize(1280, 720);	//프레임 크기 
		mainframe.setLocationRelativeTo(null);	//프레임 위치 가운데 배치
		mainframe.setResizable(false);	//창 크기 고정
		mainframe.setVisible(true);	//화면에 프레임 출력
		
		JLabel recolabel = new JLabel("이 옷 어떠세요?");
		recolabel.setHorizontalAlignment(SwingConstants.CENTER);	//라벨 가운데 위치
		recolabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 27));
		recolabel.setBounds(439, 71, 183, 28);
		mainframe.getContentPane().add(recolabel);
		
		JLabel wealabel = new JLabel("날씨 특징");
		wealabel.setHorizontalAlignment(SwingConstants.CENTER);
		wealabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 30));
		wealabel.setBounds(12, 504, 125, 30);
		mainframe.getContentPane().add(wealabel);
		
		JLabel todaylabel = new JLabel("오늘의 날씨");
		todaylabel.setHorizontalAlignment(SwingConstants.CENTER);
		todaylabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 30));
		todaylabel.setBounds(12, 71, 152, 30);
		mainframe.getContentPane().add(todaylabel);
		
		JLabel umlabel = new JLabel("우산 유무");
		umlabel.setHorizontalAlignment(SwingConstants.CENTER);
		umlabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 30));
		umlabel.setBounds(12, 310, 121, 30);
		mainframe.getContentPane().add(umlabel);
		
		
		//시간별 날씨 정보 전환 버튼
		JButton prev = new JButton("이전");
		prev.setBounds(190, 85, 70, 25);
		prev.setFont(new Font("인터파크고딕 M", Font.BOLD, 13));
		mainframe.getContentPane().add(prev);
				
		JButton next = new JButton("다음");
		next.setBounds(270, 85, 70, 25);
		next.setFont(new Font("인터파크고딕 M", Font.BOLD, 13));
		mainframe.getContentPane().add(next);
		
		
		for(int i = 0; i < infopanel.rowcount; i++) {
			mainframe.getContentPane().add(infopanel.Todaypanel[i]);
			mainframe.getContentPane().add(infopanel.rainpanel[i]);
			mainframe.getContentPane().add(infopanel.weatherpanel[i]);
			if(i > 0) {	//첫 실행시 첫 화면만 띄움 
				infopanel.Todaypanel[i].setVisible(false);
				infopanel.rainpanel[i].setVisible(false);
				infopanel.weatherpanel[i].setVisible(false);
			}
		}
		
		
		
		//이벤트
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//이전 버튼 누르면 이전 시간대 날씨 정보 출력
				if(count == 0)
					JOptionPane.showMessageDialog(null, "첫 페이지입니다.");
				else {
					infopanel.Todaypanel[count].setVisible(false);
					infopanel.rainpanel[count].setVisible(false);
					infopanel.weatherpanel[count].setVisible(false);
					
					count--;
					infopanel.Todaypanel[count].setVisible(true);
					infopanel.rainpanel[count].setVisible(true);
					infopanel.weatherpanel[count].setVisible(true);
					
				}
					
			}
		});
		
		next.addActionListener(new ActionListener() {	//다음 버튼 누르면 다음 시간대 날씨 정보 출력 
			public void actionPerformed(ActionEvent e) {	
				if(count == infopanel.rowcount-1)
					JOptionPane.showMessageDialog(null, "마지막 페이지입니다.");
				else {
					infopanel.Todaypanel[count].setVisible(false);
					infopanel.rainpanel[count].setVisible(false);
					infopanel.weatherpanel[count].setVisible(false);
					
					count++;
					infopanel.Todaypanel[count].setVisible(true);
					infopanel.rainpanel[count].setVisible(true);
					infopanel.weatherpanel[count].setVisible(true);
				}
					
			}
		});
		
	}
	public static void main(String[] args) throws IOException, SQLException {
		run r = new run();
		r.runApi();
		new Start();
	}
}
