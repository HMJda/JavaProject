package Ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class information {
	public class Save{
		String adress = "";	//주소 저장
		int line = 0;	//줄 개수
		String [][] str = new String[30][30];	//[배열에 한줄][토큰 저장]
		JPanel []Todaypanel;
		JPanel []rainpanel;	//우산여부 패널
		JPanel []weatherpanel;	//날씨 특징 패널
	}
	
	Save save = new Save();
	
	public information() {	//메모장 읽기	//run파일 실행 후 실행하기!
		
		int adressline = 0;	//주소 저장하기 위해
		String Line;	//한줄 저장
				
		try {
			FileReader file = new FileReader("C:\\Users\\infomation.txt");
			BufferedReader reader = new BufferedReader(file);
					
			while((Line = reader.readLine()) != null) {	//한줄씩 읽어서 리턴
				if(adressline == 0) {	//주소 저장
					save.adress = Line;
					adressline++;
				}
				else {	//날씨정보 저장
					StringTokenizer Token = new StringTokenizer(Line, ",");	//,를 기준으로 문자열 분리
							
					int token = 0;		//토큰 구분	
					while(Token.hasMoreTokens()) {	
						save.str[save.line][token] = Token.nextToken();	//토큰 배열에 저장
						token++;
					}
					save.line++;
							
					if(save.str[save.line-1][1].equals("0000")) {	//오늘 날씨까지만 저장
						break;
					}
				}
			}
			reader.close();
			
			save.Todaypanel = new JPanel[save.line];
			save.rainpanel = new JPanel[save.line];	//우산여부 패널
			save.weatherpanel = new JPanel[save.line];	//날씨 특징 패널
					
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException e) {
			System.out.println(e);
		}
				
	}
	

	public Save Information() {
		
		for(int i = 0; i < save.line; i++) {
			save.str[i][0] = save.str[i][0].substring(1, 5) + "년 " + save.str[i][0].substring(5, 7) + "월 " + save.str[i][0].substring(7) + "일";
			save.str[i][1] = save.str[i][1].substring(0, 2) + ":" + save.str[i][1].substring(2);	
		}	
		
					
					
		//오늘의 날씨
		//ImageIcon icon = new ImageIcon("sunny1.PNG");
		//JLabel imagelabel = new JLabel(icon, SwingConstants.CENTER);
		//imagelabel.setBounds(233, 125, 150, 150);
		//mainframe.getContentPane().add(imagelabel);
		
		//오늘의 날씨	
		for(int i = 0; i < save.line; i++) {
			save.Todaypanel[i] = new JPanel(new GridLayout(4, 1));	//오늘의 날씨 패널
			save.Todaypanel[i].setBackground(Color.WHITE);
			save.Todaypanel[i].setBounds(12, 120, 330, 170);
			
			JLabel [] Todaylabel = new JLabel[4];
			Todaylabel[0] = new JLabel(save.str[i][0] + " " + save.str[i][1]);
			Todaylabel[1] = new JLabel(save.str[i][12] + " : " + save.str[i][13]);
			Todaylabel[2] = new JLabel(save.str[i][2] + " " + save.str[i][3]);
			Todaylabel[3] = new JLabel(save.str[i][22] + " " + save.str[i][23]);
			for(int j = 0; j < 4; j++) {
				Todaylabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
				save.Todaypanel[i].add(Todaylabel[j]);
			}	
		}	
					
				
		//우산 여부 
		for(int i = 0; i < save.line; i++) {
			save.rainpanel[i] = new JPanel(new GridLayout(4, 1));
			save.rainpanel[i].setBackground(Color.WHITE);
			save.rainpanel[i].setBounds(12, 350, 330, 140);
						
			JLabel [] rainlabel = new JLabel[4];
			if(save.str[i][15].equals("비"))	//강수형태
				rainlabel[0] = new JLabel("비가 오고 있으므로 우산을 챙기세요.");
			else if(save.str[i][15].equals("눈/비"))
				rainlabel[0] = new JLabel("눈과 비가 오고 있으므로 우산을 챙기세요.");
			else if(save.str[i][15].equals("눈"))
				rainlabel[0] = new JLabel("눈이 오고 있으므로 우산을 챙기세요.");
			else if(save.str[i][15].equals("소나기"))
				rainlabel[0] = new JLabel("소나기가 오고 있으므로 우산을 챙기세요.");
			else
				rainlabel[0] = new JLabel("없음");
					
			//save.str[save.count][17] = save.str[save.count][17].substring(0,2);
			//int percent = Integer.parseInt(save.str[save.count][17].trim());
			String[] p = save.str[i][17].split(" ");
			int percent = Integer.parseInt(p[0]);
			
			if(percent < 60) {	//강수확률 60% 이하일 경우
				if(save.str[i][15].equals("없음")) {
					rainlabel[0] = new JLabel("우산을 챙기지 않아도 됩니다.");
					rainlabel[1] = new JLabel(save.str[i][16] + "이 " + save.str[i][17] + " 입니다.");
				}
				else
					rainlabel[1] = new JLabel(save.str[i][16] + "이 " + save.str[i][17] + " 입니다.");
			}
			else {	//강수확률 60% 이상일 경우
				if(save.str[i][15].equals("없음")) {
					rainlabel[0] = new JLabel(save.str[i][16] + "이 " + save.str[i][17] + " 입니다.");
					rainlabel[1] = new JLabel("비가 올 확률이 높으므로 우산을 챙기세요.");
				}
				else
					rainlabel[1] = new JLabel(save.str[i][16] + "이 " + save.str[i][17] + " 입니다.");
			}
					
			rainlabel[2] = new JLabel(save.str[i][20] + " : " + save.str[i][21]);
			rainlabel[3] = new JLabel(save.str[i][24] + " : " + save.str[i][25]);
			for(int j = 0; j < 4; j++) {
				rainlabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
				save.rainpanel[i].add(rainlabel[j]);
			}
		}
		
					
				
					
		//날씨 특징
		for(int i = 0; i < save.line; i++) {
			save.weatherpanel[i] = new JPanel(new GridLayout(3, 1));
			save.weatherpanel[i].setBackground(Color.WHITE);
			save.weatherpanel[i].setBounds(12, 550, 330, 120);
						
			JLabel [] weatherlabel = new JLabel[3];
					
			//save.str[save.count][9] = save.str[save.count][9].substring(0,2);
			//double direction = Double.parseDouble(save.str[save.count][9]);
			String[] d = save.str[i][9].split(" ");
			double direction = Double.parseDouble(d[0]);
			
			if((direction >= 0 && direction <= 22.5) || (direction >= 337.5 && direction <= 360))	//풍향
				weatherlabel[0] = new JLabel("북풍이 불고 있습니다.");
			else if(direction > 22.5 && direction < 67.5)
				weatherlabel[0] = new JLabel("북동풍이 불고 있습니다.");
			else if(direction >= 67.5 && direction <= 112.5)
				weatherlabel[0] = new JLabel("동풍이 불고 있습니다.");
			else if(direction > 112.5 && direction < 157.5)
				weatherlabel[0] = new JLabel("남동풍이 불고 있습니다.");
			else if(direction >= 157.5 && direction <= 202.5)
				weatherlabel[0] = new JLabel("남풍이 불고 있습니다.");
			else if(direction > 202.5 && direction < 247.5)
				weatherlabel[0] = new JLabel("남서풍이 불고 있습니다.");
			else if(direction >= 247.5 && direction <= 292.5)
				weatherlabel[0] = new JLabel("서풍이 불고 있습니다.");
			else
				weatherlabel[0] = new JLabel("북서풍이 불고 있습니다.");
					

			//double speed = Double.parseDouble(save.str[save.count][11].substring(0, 3));
			String[] s = save.str[i][11].split(" ");	//공백 기준으로 나누기
			double speed = Double.parseDouble(s[0]);
			
			if(speed >= 0 && speed <= 0.2 ) {	//풍속
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("바람이 불지 않는 고요한 상태입니다.");
			}
			else if(speed >= 0.3 && speed <= 3.3) {
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("가벼운 바람이 불고 있는 상태입니다.");
			}
			else if(speed >= 3.4 && speed <=7.9) {
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("바람이 적당하므로 가벼운 산책을 추천합니다.");
			}
			else if(speed >=8.0 && speed <=10.7) {
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("바람이 많이 불고 있는 상태입니다.");
			}
			else if (speed >= 10.8 && speed <= 17.1) {
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("강한 바람이 불고 있으므로 유의하세요.");
			}
			else {
				weatherlabel[1] = new JLabel("풍속이 " + save.str[i][11] + " 입니다.");
				weatherlabel[2] = new JLabel("매우 강한 바람이 불고 있으므로 외출을 삼가주세요.");
			}
					
					
			for(int j = 0; j < 3; j++) {
				weatherlabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
				save.weatherpanel[i].add(weatherlabel[j]);
			}
		}
		
				
		return save;
				
	}

}
