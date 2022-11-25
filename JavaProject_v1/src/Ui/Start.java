package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.border.TitledBorder;
import Ui.information.*;

public class Start extends JFrame{
	private JFrame mainframe;
	int count = 0;	//패널 순서
	information in = new information();	//information 객체 생성
	Save InFo = in.Information();
	
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
			
		
		JLabel labeladress = new JLabel("위치: " + InFo.adress);	//위치 출력
		labeladress.setBounds(12, 30, 250, 25);
		labeladress.setFont(new Font("인터파크고딕 M", Font.BOLD, 20));
		mainframe.getContentPane().add(labeladress);
		
		for(int i = 0; i < InFo.line; i++) {
			mainframe.getContentPane().add(InFo.Todaypanel[i]);
			mainframe.getContentPane().add(InFo.rainpanel[i]);
			mainframe.getContentPane().add(InFo.weatherpanel[i]);
			if(i > 0) {
				InFo.Todaypanel[i].setVisible(false);
				InFo.rainpanel[i].setVisible(false);
				InFo.weatherpanel[i].setVisible(false);
			}
		}
		
		
		//이벤트
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	//이전 버튼 누르면 이전 시간대 날씨 정보 출력
				if(count == 0)
					JOptionPane.showMessageDialog(null, "첫 페이지입니다.");
				else {
					InFo.Todaypanel[count].setVisible(false);
					InFo.rainpanel[count].setVisible(false);
					InFo.weatherpanel[count].setVisible(false);
					
					count--;
					InFo.Todaypanel[count].setVisible(true);
					InFo.rainpanel[count].setVisible(true);
					InFo.weatherpanel[count].setVisible(true);
					
				}
					
			}
		});
		
		next.addActionListener(new ActionListener() {	//다음 버튼 누르면 다음 시간대 날씨 정보 출력 
			public void actionPerformed(ActionEvent e) {	
				if(count == InFo.line-1)
					JOptionPane.showMessageDialog(null, "마지막 페이지입니다.");
				else {
					InFo.Todaypanel[count].setVisible(false);
					InFo.rainpanel[count].setVisible(false);
					InFo.weatherpanel[count].setVisible(false);
					
					count++;
					InFo.Todaypanel[count].setVisible(true);
					InFo.rainpanel[count].setVisible(true);
					InFo.weatherpanel[count].setVisible(true);
				}
					
			}
		});
		
	}
		
	
	public static void main(String[] args) {
		new Start();
	}
}
