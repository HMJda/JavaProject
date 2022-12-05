package Ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class JoinnClo extends JFrame {

	String consti = "";
	String cardi = "";
	String pad = "";
	String wind = "";
	String jac = "";
	String zip = "";
	String co = "";
	String ya = "";
	String fle = "";
	String shortsleeve = "";
	String blouse = "";
	String shirts = "";
	String sleeveless = "";
	String knit = "";
	String vest = "";
	String longsleeve = "";
	String hoodie = "";
	String mtm = "";
	String longpants = "";
	String slacks = "";
	String jeans = "";
	String shortpants = "";
	String miniskirt = "";
	String longskirt = "";
	String muffler = "";
	String longdress = "";
	String shortdress = "";
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField emailField;
	private JTextField idField;
	private JPasswordField pwField;

	public JoinnClo() {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 630);	//프레임 크기
		frame.setLocationRelativeTo(null);	//프레임 위치 가운데 배치
		frame.setResizable(false);	//창 크기 고정
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	 
		frame.setVisible(true);	//화면에 프레임 출력

		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(72, 41, 86, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이메일");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(72, 109, 86, 38);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("아이디");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(72, 174, 86, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("비밀번호");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(72, 240, 86, 38);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("체질");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(72, 308, 86, 38);
		contentPane.add(lblNewLabel_4);
		
		nameField = new JTextField();
		nameField.setBounds(198, 41, 235, 38);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(198, 109, 235, 38);
		contentPane.add(emailField);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(198, 174, 235, 38);
		contentPane.add(idField);
		
		JRadioButton hotRbtn = new JRadioButton("더위를 많이 탐");
		hotRbtn.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		hotRbtn.setHorizontalAlignment(SwingConstants.LEFT);
		hotRbtn.setBounds(198, 308, 235, 38);
		contentPane.add(hotRbtn);
		
		JRadioButton coldRbtn = new JRadioButton("추위를 많이 탐");
		coldRbtn.setHorizontalAlignment(SwingConstants.LEFT);
		coldRbtn.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		coldRbtn.setBounds(198, 356, 235, 38);
		contentPane.add(coldRbtn);
		
		JRadioButton bothRbtn = new JRadioButton("둘 다 많이 탐");
		bothRbtn.setHorizontalAlignment(SwingConstants.LEFT);
		bothRbtn.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		bothRbtn.setBounds(198, 408, 235, 38);
		contentPane.add(bothRbtn);
		
		JLabel lblNewLabel_5 = new JLabel("외투");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(526, 41, 86, 38);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("상의");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(526, 160, 86, 38);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("하의");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(526, 333, 86, 38);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("기타");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(526, 446, 86, 38);
		contentPane.add(lblNewLabel_8);
		
		JCheckBox cardiChk = new JCheckBox("가디건");
		cardiChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		cardiChk.setBounds(620, 42, 115, 38);
		contentPane.add(cardiChk);
		
		JCheckBox padChk = new JCheckBox("패딩");
		padChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		padChk.setBounds(755, 42, 115, 38);
		contentPane.add(padChk);
		
		JCheckBox jaChk = new JCheckBox("자켓");
		jaChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		jaChk.setBounds(1046, 39, 115, 38);
		contentPane.add(jaChk);
		
		JCheckBox windChk = new JCheckBox("바람막이");
		windChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		windChk.setBounds(897, 41, 115, 38);
		contentPane.add(windChk);
		
		JCheckBox zipChk = new JCheckBox("집업");
		zipChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		zipChk.setBounds(620, 109, 115, 38);
		contentPane.add(zipChk);
		
		JCheckBox coatChk = new JCheckBox("코트");
		coatChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		coatChk.setBounds(755, 109, 115, 38);
		contentPane.add(coatChk);
		
		JCheckBox fleeChk = new JCheckBox("플리스");
		fleeChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		fleeChk.setBounds(1046, 106, 115, 38);
		contentPane.add(fleeChk);
		
		JCheckBox yaaChk = new JCheckBox("야상");
		yaaChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		yaaChk.setBounds(897, 108, 115, 38);
		contentPane.add(yaaChk);
		
		JCheckBox srtsleeChk = new JCheckBox("반팔");
		srtsleeChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		srtsleeChk.setBounds(620, 163, 115, 38);
		contentPane.add(srtsleeChk);
		
		JCheckBox blaChk = new JCheckBox("블라우스");
		blaChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		blaChk.setBounds(755, 163, 115, 38);
		contentPane.add(blaChk);
		
		JCheckBox sleeleChk = new JCheckBox("민소매");
		sleeleChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		sleeleChk.setBounds(1046, 160, 115, 38);
		contentPane.add(sleeleChk);
		
		JCheckBox shirChk = new JCheckBox("셔츠");
		shirChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		shirChk.setBounds(897, 162, 115, 38);
		contentPane.add(shirChk);
		
		JCheckBox knitChk = new JCheckBox("니트");
		knitChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		knitChk.setBounds(620, 224, 115, 38);
		contentPane.add(knitChk);
		
		JCheckBox vestChk = new JCheckBox("조끼");
		vestChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		vestChk.setBounds(755, 224, 115, 38);
		contentPane.add(vestChk);
		
		JCheckBox hooChk = new JCheckBox("후드티");
		hooChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		hooChk.setBounds(1046, 221, 115, 38);
		contentPane.add(hooChk);
		
		JCheckBox longsleeChk = new JCheckBox("긴팔");
		longsleeChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		longsleeChk.setBounds(897, 223, 115, 38);
		contentPane.add(longsleeChk);
		
		JCheckBox mtmChk = new JCheckBox("맨투맨");
		mtmChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		mtmChk.setBounds(620, 282, 115, 38);
		contentPane.add(mtmChk);
		
		JCheckBox lngpantsChk = new JCheckBox("긴바지");
		lngpantsChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		lngpantsChk.setBounds(620, 336, 115, 38);
		contentPane.add(lngpantsChk);
		
		JCheckBox slacChk = new JCheckBox("슬랙스");
		slacChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		slacChk.setBounds(755, 336, 115, 38);
		contentPane.add(slacChk);
		
		JCheckBox jeansChk = new JCheckBox("청바지");
		jeansChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		jeansChk.setBounds(897, 335, 115, 38);
		contentPane.add(jeansChk);
		
		JCheckBox shrpantsChk = new JCheckBox("반바지");
		shrpantsChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		shrpantsChk.setBounds(1046, 333, 115, 38);
		contentPane.add(shrpantsChk);
		
		JCheckBox miniskiChk = new JCheckBox("미니 스커트");
		miniskiChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		miniskiChk.setBounds(620, 391, 115, 38);
		contentPane.add(miniskiChk);
		
		JCheckBox lonskiChk = new JCheckBox("롱 스커트");
		lonskiChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		lonskiChk.setBounds(755, 391, 115, 38);
		contentPane.add(lonskiChk);
		
		JCheckBox mufChk = new JCheckBox("목도리");
		mufChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		mufChk.setBounds(620, 447, 115, 38);
		contentPane.add(mufChk);
		
		JCheckBox lngdreChk = new JCheckBox("롱 원피스");
		lngdreChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		lngdreChk.setBounds(755, 447, 115, 38);
		contentPane.add(lngdreChk);
		
		JCheckBox minidreChk = new JCheckBox("미니 원피스");
		minidreChk.setFont(new Font("인터파크고딕 M", Font.PLAIN, 17));
		minidreChk.setBounds(897, 446, 115, 38);
		contentPane.add(minidreChk);
		
		JButton Canbtn = new JButton("취소");
		Canbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginFrame(); 
			}
		});
		Canbtn.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		Canbtn.setBounds(608, 536, 105, 38);
		contentPane.add(Canbtn);
		
		JButton Joinbtn = new JButton("가입");
		Joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDTO Idto = new LoginDTO();
				Idto.setName(nameField.getText());
				Idto.setEmail(emailField.getText());
				Idto.setId(idField.getText());
				Idto.setPw(new String(pwField.getPassword()));
				Idto.setConsti(consti);
				Idto.setCardigan(cardi);
				Idto.setPadding(pad);
				Idto.setWindbreaker(wind);
				Idto.setJacket(jac);
				Idto.setZipup(zip);
				Idto.setCoat(co);
				Idto.setYaasang(ya);
				Idto.setFleece(fle);
				Idto.setShortsleeve(shortsleeve);
				Idto.setBlouse(blouse);
				Idto.setShirts(shirts);
				Idto.setKnit(knit);
				Idto.setVest(vest);
				Idto.setLongsleeves(longsleeve);
				Idto.setHoodie(hoodie);
				Idto.setMtm(mtm);
				Idto.setLongpants(longpants);
				Idto.setSlacks(slacks);
				Idto.setJeans(jeans);
				Idto.setShortpants(shortpants);
				Idto.setMiniskirt(miniskirt);
				Idto.setLongskirt(longskirt);
				Idto.setMuffler(muffler);
				Idto.setLongdress(longdress);
				Idto.setShortdress(shortdress);
				
				LoginDAO Idao = LoginDAO.getInstance();
				int res = Idao.Join(Idto);
				
				if (res == 1) {
					JOptionPane.showMessageDialog(null, "옷이 저장되었습니다.\n가입되었습니다.", "가입 성공!", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					new LoginFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "정보를 제대로 입력하세요.");
				}
			}
		});
		Joinbtn.setFont(new Font("인터파크고딕 M", Font.PLAIN, 20));
		Joinbtn.setBounds(449, 536, 105, 38);
		contentPane.add(Joinbtn);
		
		pwField = new JPasswordField();
		pwField.setBounds(198, 243, 235, 38);
		contentPane.add(pwField);
		
		ButtonGroup g = new ButtonGroup();
		g.add(hotRbtn);
		g.add(coldRbtn);
		g.add(bothRbtn);
		
		hotRbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				consti = e.getActionCommand();
			}
		});
		
		coldRbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				consti = e.getActionCommand();
			}
		});
		
		bothRbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				consti = e.getActionCommand();
			}
		});
		
		cardiChk.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {				
				// TODO Auto-generated method stub
				cardi = e.getActionCommand();
			}
		});
				
		padChk.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pad = e.getActionCommand(); 
			}
		});
		
		windChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				wind = e.getActionCommand();
				}
		});
		
		jaChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jac = e.getActionCommand();
			}
		});
		
		zipChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				zip = e.getActionCommand();
			}
		});
		
		coatChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				co = e.getActionCommand();
			}
		});
		
		yaaChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ya = e.getActionCommand();
			}
		});
		
		fleeChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fle = e.getActionCommand();
			}
		});
		
		srtsleeChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shortsleeve = e.getActionCommand();
			}
		});
		
		blaChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				blouse = e.getActionCommand();
			}
		});
		
		shirChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shirts = e.getActionCommand();
			}
		});
		
		sleeleChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sleeveless = e.getActionCommand();
			}
		});
		
		knitChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				knit = e.getActionCommand();
			}
		});
		
		vestChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vest = e.getActionCommand();
			}
		});
		
		longsleeChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				longsleeve = e.getActionCommand();
			}
		});
		
		hooChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				hoodie = e.getActionCommand();
			}
		});
		
		mtmChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mtm = e.getActionCommand();
			}
		});
		
		lngpantsChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				longpants = e.getActionCommand();
			}
		});
		
		slacChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				slacks = e.getActionCommand();
			}
		});
		
		jeansChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jeans = e.getActionCommand();
			}
		});
		
		shrpantsChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shortpants = e.getActionCommand();
			}
		});
		
		miniskiChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				miniskirt = e.getActionCommand();
			}
		});
		
		lonskiChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				longskirt = e.getActionCommand();
			}
		});
		
		mufChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				muffler = e.getActionCommand();
			}
		});
		
		lngdreChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				longdress = e.getActionCommand();
			}
		});
		
		minidreChk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				shortdress = e.getActionCommand();
			}
		});
		
	}
}
