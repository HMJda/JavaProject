package Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;//SQL 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Api.translateXY.*;
import Api.gpsApi.*;

public class run {
	public static void main(String[] args) throws IOException, SQLException{
		/** db 연결 부분 */
        weatherDBconn dbConn = new weatherDBconn();
        Connection connect = dbConn.dbConn();
	    try {
			PreparedStatement reset = connect.prepareStatement("DELETE FROM 날씨"); // db 초기화 부분
			reset.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	

		
		weatherApi wapi = new weatherApi();//weatherApi 객체 생성
		/*
		translateXY XY = new translateXY(); //translateXY 객체 생성
		gpsApi gapi = new gpsApi(); //gpsApi 객체 생성
		Adress gpsxy;
		*/
		//gpsxy = gapi.bringGpsfromApi();
		
		//LatXLngY xy;
		/**위도 경도 입력 받아 xy좌표로 변경하는 부분*/
		//xy = XY.convertGRID_GPS(gpsxy.Y ,gpsxy.X); 
    	//System.out.println(gpsxy.X+" "+gpsxy.Y); //GPS 테스트
		
		/**위치 정보 삭제 
		try {
			PreparedStatement reset = connect.prepareStatement("DELETE FROM 위치정보"); // db 초기화 부분
			reset.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		*/
		/**DB상에 위치정보 넣는 곳*/
		//String nx = dbConn.BringX();
		if(dbConn.BringX() == null) { //db상에 위치정보 없으면 위치 입력 받게 함
			System.out.println("주소를 입력해주세요 : ");
			BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
			String address = io.readLine();
		    dbConn.Inputxy(address);
		}
		
		
		String base_time = "2300"; //임시 데이터 값 
		//String PageNo = "1";
		String base_date = "20221023"; //임시 데이터 값    
		base_date = wapi.localDate();	
		base_time = wapi.timeChange(wapi.localTime().substring(0, 2));
		if(base_time=="2300") { //23시정보는 어제의 날짜를 입력해 줘야 하므로 시간에 따른 날짜 변경 (**윤년 부분 미 구현)
			int date = 0;
			if(base_date.substring(4, 6) == "01") {
				date = Integer.parseInt(base_date)-Integer.parseInt(base_date.substring(4, 6))-10000+1231; //1년빼기 현재 월일 빼기 12월31일 더하기 
			}
			else if(base_date.substring(6, 8) == "01") {
				date = Integer.parseInt(base_date)-Integer.parseInt(base_date.substring(6, 8))-100+31; //1월빼기 현재 일 빼기 31일 더하기 
			}else {
				date = Integer.parseInt(base_date)-1;
			}
			base_date = Integer.toString(date);
		}
		
    	System.out.println("현재시각"+base_date+wapi.localTime());
    	
    	//String nx = "98"; //임시 데이터 값 부산
    	//String ny = "76";//임시 데이터 값  
    	
    	//nx = Integer.toString((int)xy.x); /**변경된 위도경도 x,y값을 스트링형으로 변형*/
    	//ny = Integer.toString((int)xy.y);
    	
    	//System.out.println(nx+ny);//x,y 좌표 테스트
    	
    	for(int i = 1;i<4;i++) {
    		wapi.bringWeaterFromApi(Integer.toString(i),base_date,base_time,dbConn.BringX().trim(),dbConn.BringY().trim());   		
    	}
        //System.out.println(gpsxy.adress); //adress 출력 
        //System.out.println(gpsxy.jibunAddress); //jibunAddress 출력
        
        //최저 최고 기온 테스트
    	double TMX = dbConn.BringTMX();   	
    	double TMN = dbConn.BringTMN();
    	System.out.println("최고 기온 " +TMX);
    	System.out.println("최저 기온 " +TMN);
    	
	}

}
