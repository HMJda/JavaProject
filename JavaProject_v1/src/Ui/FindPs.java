package Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.print.attribute.standard.RequestingUserName;
import javax.swing.JButton;

public class FindPs {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public FindPs() {
		
		String name = "홍길동";
		String email = "1234@naver.com";
		String id = "id1234";
		String pw = "1111";

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setSize(601, 341);	//프레임 크기 
		frame.setLocationRelativeTo(null);	//프레임 위치 가운데 배치
		frame.setResizable(false);	//창 크기 고정
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PW 찾기");
		lblNewLabel.setFont(new Font("인터파크고딕 M", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 109, 47);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(143, 75, 240, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 140, 240, 40);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 205, 240, 40);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		btnNewButton.setBounds(413, 118, 121, 79);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(59, 73, 50, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("아이디");
		lblNewLabel_2.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(48, 138, 71, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("이메일");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(48, 203, 77, 39);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton calcel = new JButton("돌아가기");
		calcel.setFont(new Font("인터파크고딕 M", Font.PLAIN, 16));
		calcel.setBounds(413, 238, 121, 23);
		frame.getContentPane().add(calcel);
		frame.setVisible(true);	//화면에 프레임 출력
		
		calcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginFrame();
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				
				String name = textField.getText();
				String id = textField_1.getText();
				String email = textField_2.getText();
				
				try {
					Connection con = DBConn.getConnection();
					PreparedStatement pstm = con.prepareStatement("select user_pw from join_user "
							+ "where user_name = ? and user_id = ? and user_email = ?");
					
					pstm.setString(1, name);
					pstm.setString(2, id);
					pstm.setString(3, email);
					
					ResultSet rs = pstm.executeQuery();
					
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "비밀번호는 " + rs.getString(1) + "입니다.");
					}
					else {
						JOptionPane.showMessageDialog(null, "정보와 일치하는 비밀번호가 없습니다.");
					}
				} catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
	}
	
	//public static void main(String[] args) {
	//	new FindPs();
	//}

}
