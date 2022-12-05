package Ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Api.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class information {
	public class Savepanel{
		int rowcount = 0;	//행 개수
		JPanel []Todaypanel;	//오늘의 날씨 패널
		JPanel []rainpanel;	//우산여부 패널
		JPanel []weatherpanel;	//날씨 특징 패널
	}
	
	Savepanel save = new Savepanel();
	
	public ResultSet TodayDB() {
		weatherApi api = new weatherApi();
		String Date = api.localDate();	//현재 날짜
		String time = "0000";
		int DateInt = Integer.parseInt(Date) + 1;	//0시 날씨정보 구하기 위해
		String DateStr = Integer.toString(DateInt);
		String sql = "SELECT * FROM 날씨 WHERE 날짜=" + "'" + Date + "'";
		String sqlplus = "OR (날짜=" + "'" + DateStr + "'" +  "AND 시간=" + "'" + time + "'" + ") ORDER BY 날짜, 시간";	//ORDER BY 컬럼명: 컬럼 값으로 컬럼 오름차순 정렬
		ResultSet rs = null;
		int i = 0;	//패널 배열 순서 카운트
		
		try {
	    	weatherDBconn dbConn = new weatherDBconn();
	        Connection connect = dbConn.dbConn();	//DB연결
			PreparedStatement Todaypstmt = connect.prepareStatement(sql + sqlplus, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); // 오늘의 날씨만 조회
			//sql문 뒤는 양방향 작업 위해 추가	
			
			rs = Todaypstmt.executeQuery();	//ResultSet에 할당
		
			
			while(rs.next()) {
				save.rowcount++;
			}
			
			rs.beforeFirst();	//후방향 작업	//커서의 위치를 가장 처음으로
			
			
			save.Todaypanel = new JPanel[save.rowcount];	//오늘의 날씨 패널
			save.rainpanel = new JPanel[save.rowcount];	//우산여부 패널
			save.weatherpanel = new JPanel[save.rowcount];	//날씨 특징 패널
			
			
			return rs;
			
		}catch(SQLException e2) {
			e2.printStackTrace();
			return rs;
		}
	}

	
	
		
	public Savepanel weatheradd() {
		ResultSet rs = TodayDB();
		int i = 0;	//패널 배열 순서 카운트
		try {
			
			while(rs.next()) {
				//오늘의 날씨 패널
				save.Todaypanel[i] = new JPanel(new GridLayout(4, 1));	
				save.Todaypanel[i].setBackground(Color.WHITE);
				save.Todaypanel[i].setBounds(12, 120, 330, 170);
				
				String date = rs.getString("날짜");
				String datestr = date.substring(0,4) + "년 " + date.substring(4,6) + "월 " + date.substring(6, 8) + "일 ";
				
				String time = rs.getString("시간");
				String timestr = time.substring(0,2) + ":" + time.substring(2, 4);
				
				String temp = "기온 -";
				if(rs.getString("기온") == null) {
					if(rs.isFirst() == false) {
						rs.previous();	//이전위치
						if(rs.getString("기온") != null) {
							temp = "기온 " + rs.getString("기온");
							rs.next();	//다음위치
						}
						else {
							rs.next();
						}
					}
				}
				else
					temp = "기온 " + rs.getString("기온");
				
				
				String Temperstr = " ";
				if(rs.getString("최고기온") != null) {
					Temperstr = "최고기온 " + rs.getString("최고기온").trim();
					
					if(rs.getString("최저기온") != null) {	//최고기온과 최저기온이 있는 경우
						Temperstr += ", 최저기온 " + rs.getString("최저기온");
					}
				}
				else {
					if(rs.getString("최저기온") != null) {	//최저기온만 있는 경우
						Temperstr = "최저기온 " + rs.getString("최저기온");
					}
				}
				
				JLabel [] Todaylabel = new JLabel[4];
				Todaylabel[0] = new JLabel(datestr + timestr);	//날짜, 시간 라벨
				Todaylabel[1] = new JLabel("하늘상태 : " + rs.getString("하늘상태"));	
				Todaylabel[2] = new JLabel(temp);	//기온라벨
				Todaylabel[3] = new JLabel(Temperstr);	//최고,최저기온라벨
				
				for(int j = 0; j < 4; j++) {
					Todaylabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
					save.Todaypanel[i].add(Todaylabel[j]);
				}	
				//오늘의 날씨 패널
				
				
				//우산여부 패널
				save.rainpanel[i] = new JPanel(new GridLayout(3, 1));
				save.rainpanel[i].setBackground(Color.WHITE);
				save.rainpanel[i].setBounds(12, 350, 330, 140);
							
				JLabel [] rainlabel = new JLabel[2];
				String rain = rs.getString("강수형태").trim();
				if(rain.equals("비"))
					rainlabel[0] = new JLabel("비가 오고 있으므로 우산을 챙기세요.");
				else if(rain.equals("눈/비"))
					rainlabel[0] = new JLabel("눈과 비가 오고 있으므로 우산을 챙기세요.");
				else if(rain.equals("눈"))
					rainlabel[0] = new JLabel("눈이 오고 있으므로 우산을 챙기세요.");
				else if(rain.equals("소나기"))
					rainlabel[0] = new JLabel("소나기가 오고 있으므로 우산을 챙기세요.");
				else
					rainlabel[0] = new JLabel("없음");
					
				String []p = rs.getString("강수확률").split(" ");
				int percent = Integer.parseInt(p[0]);	//확률 문자열을 정수형으로 	바꿈
				if(percent < 60) {	//강수확률 60% 이하일 경우
					if(rain.equals("없음")) {
						rainlabel[0] = new JLabel("우산을 챙기지 않아도 됩니다.");
						rainlabel[1] = new JLabel("강수확률이 " + percent + "% 입니다.");
					}
					else	//강수형태 있을 경우 우산여부 이미 라벨에 있음
						rainlabel[1] = new JLabel("강수확률이 " + percent + "% 입니다.");
				}
				else {	//강수확률 60% 이상일 경우
					if(rain.equals("없음")) {	
						rainlabel[0] = new JLabel("강수확률이 " + percent + "% 입니다.");
						rainlabel[1] = new JLabel("비가 올 확률이 높으므로 우산을 챙기세요.");
					}
					else
						rainlabel[1] = new JLabel("강수확률이 " + percent + "% 입니다.");
				}
				
				for(int j = 0; j < 2; j++) {
					rainlabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
					save.rainpanel[i].add(rainlabel[j]);
				}
				//우산여부 패널
				
				
				
				//날씨특징 패널
				save.weatherpanel[i] = new JPanel(new GridLayout(3, 1));
				save.weatherpanel[i].setBackground(Color.WHITE);
				save.weatherpanel[i].setBounds(12, 550, 330, 120);
				
				
				JLabel [] weatherlabel = new JLabel[2];
				String[] s = rs.getString("풍속").split(" ");	//공백 기준으로 나누기
				double speed = Double.parseDouble(s[0]);
				
				if(speed >= 0 && speed <= 0.2 ) {	//풍속
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("바람이 불지 않는 고요한 상태입니다.");
				}
				else if(speed >= 0.3 && speed <= 3.3) {
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("가벼운 바람이 불고 있는 상태입니다.");
				}
				else if(speed >= 3.4 && speed <=7.9) {
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("바람이 적당하므로 가벼운 산책을 추천합니다.");
				}
				else if(speed >=8.0 && speed <=10.7) {
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("바람이 많이 불고 있는 상태입니다.");
				}
				else if (speed >= 10.8 && speed <= 17.1) {
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("강한 바람이 불고 있으므로 유의하세요.");
				}
				else {
					weatherlabel[0] = new JLabel("풍속이 " + rs.getString("풍속").trim() + " 입니다.");
					weatherlabel[1] = new JLabel("매우 강한 바람이 불고 있으므로 외출을 삼가주세요.");
				}
						
						
				for(int j = 0; j < 2; j++) {
					weatherlabel[j].setFont(new Font("인터파크고딕 M", Font.BOLD, 15));
					save.weatherpanel[i].add(weatherlabel[j]);
				}
				//날씨특징 패널
				
				i++;
				
			}
			return save;
		
			
			
		}catch(SQLException e2) {
			e2.printStackTrace();
			return save;
		}
		
	}
	
	public static void main(String[] args)  {
		information info = new information();
		info.weatheradd();
	}
	
	
}
