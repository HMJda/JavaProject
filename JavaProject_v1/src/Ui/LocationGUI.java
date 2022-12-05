package Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Api.*;

public class LocationGUI {
	JTextField address;
	JLabel resAddress, resX, resY, jibunAddress;
	JLabel imageLabel;
	
	public void initGUI() {
		JFrame frm = new JFrame("location information");        // 프레임 생성
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // 프레임의 X 클릭 시 종료.
		Container c = frm.getContentPane();                     // JFrame 안쪽 영역.
		
	               
		JPanel pan = new JPanel();
		pan.setBackground( new Color(255, 204, 204) );
		JLabel addressLbl = new JLabel("주소입력");           // JFrame 안쪽 영역 상단에 들어갈 주소입력
		addressLbl.setFont(new Font("굴림",Font.BOLD, 25));
		addressLbl.setOpaque(true); 
	    addressLbl.setForeground(new Color(102,051,051));
	    addressLbl.setBackground(new Color(255, 204, 204));
		address = new JTextField(35);
		JButton btn = new JButton("입력");                 // JFrame 안쪽 영역에 들어갈 입력 버튼
		btn.setBackground( new Color(204, 102, 102) );
		btn.setForeground(new Color(102,051,051));
		btn.setFont(new Font("굴림",Font.BOLD, 25));
		pan.add(addressLbl);
		pan.add(address);
		pan.add(btn);	
		
		/** db에 값넣는 부분 */
		weatherDBconn dbConn =new weatherDBconn();
	    dbConn.Inputxy(addressLbl.getText());	 
	    
	    
		btn.addActionListener(new Project01_D(this));           // pan에 생성한 버튼(btn) 클릭 시 처리하는 이벤트 핸들러.
		
		JPanel pan1 = new JPanel();
		pan1.setBackground( new Color(255, 204, 204) );
		pan1.setLayout(new GridLayout(4, 1));                   // 지도 하단 그리드 4행 1열로 생성.
		resAddress = new JLabel("도로명");                      // 그리드 1행에 들어갈 도로명
		jibunAddress = new JLabel("지번주소");                  // 그리드 2행에 들어갈 지번주소
		resX = new JLabel("경도");                              // 그리드 3행에 들어갈 경도
		resY = new JLabel("위도");                              // 그리드 4행에 들어갈 위도
		pan1.add(resAddress);
		pan1.add(jibunAddress);
		pan1.add(resX);
		pan1.add(resY);
		
		c.add(BorderLayout.NORTH, pan);                           // 상단 pan 세팅
		                
		c.add(BorderLayout.CENTER, pan1);                        //  가운데 pan1 세팅
		
		frm.setSize(650,400);
		frm.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new LocationGUI().initGUI();	
	}
}
