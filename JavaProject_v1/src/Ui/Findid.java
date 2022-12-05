package Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.AbstractMultiResolutionImage;
import java.beans.Statement;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Findid {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	public Findid() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		frame.setSize(560, 272);	//프레임 크기 
		frame.setLocationRelativeTo(null);	//프레임 위치 가운데 배치
		frame.setResizable(false);	//창 크기 고정
		frame.setVisible(true);	//화면에 프레임 출력
		
		JLabel lblNewLabel = new JLabel("ID 찾기");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 22));
		lblNewLabel.setBounds(0, 10, 109, 47);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(142, 66, 240, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(142, 121, 240, 40);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(50, 67, 50, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이메일");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(38, 128, 83, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("입력");
		btnNewButton.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		btnNewButton.setBounds(410, 80, 91, 66);
		frame.getContentPane().add(btnNewButton);
		
		JButton cancel = new JButton("돌아가기");
		cancel.setFont(new Font("인터파크고딕 M", Font.PLAIN, 14));
		cancel.setBounds(410, 187, 91, 23);
		frame.getContentPane().add(cancel);
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				frame.dispose();
				new LoginFrame();
			}
		});
		
		
		btnNewButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				
				String name = textField.getText();
				String email = textField_1.getText();
				
				Connection conn;
				conn = DBConn.getConnection();
				PreparedStatement pstm1 = null;
				ResultSet rs1 = null;
				
				try {
					
					pstm1 = conn.prepareStatement("select user_id from join_user where user_name =? and user_email =?");
					
					pstm1.setString(1, name);
					pstm1.setString(2, email);
					
					rs1 = pstm1.executeQuery();
					
					if (rs1.next()) {
						JOptionPane.showMessageDialog(null, "찾으신 아이디는 " + rs1.getString("user_id") + "입니다.");
					}
					else {
						JOptionPane.showMessageDialog(null,  "정보와 일치하는 아이디가 없습니다.");
					}
					
					rs1.close();
					pstm1.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				} 
		     }
		});
		
	}
						
	//public static void main(String[] args) {
	//	new Findid();
	//}
}
