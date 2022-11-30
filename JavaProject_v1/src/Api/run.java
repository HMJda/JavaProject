package Api;

import java.io.IOException;


import java.sql.Connection;//SQL 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Api.translateXY.*;
import Api.gpsApi.*;

public class run {
	public static void main(String[] args) throws IOException{
		 Connection connect = null;
	        try {
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	            String user = "system"; 
	            String pw = "1";
	            String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
	            
	            Class.forName("oracle.jdbc.driver.OracleDriver");        
	            connect = DriverManager.getConnection(DBURL, user, pw);
	        } catch (ClassNotFoundException cnfe) {
	            System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
	        } catch (SQLException sqle) {
	            System.out.println("DB 접속실패 : "+sqle.toString());
	        } catch (Exception e) {
	            System.out.println("Unkonwn error");
	            e.printStackTrace();
	        }
	        try {
			PreparedStatement reset = connect.prepareStatement("DELETE FROM 날씨");
			reset.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	
		
		translateXY XY = new translateXY(); //translateXY 객체 생성
		weatherApi wapi = new weatherApi();//weatherApi 객체 생성
		gpsApi gapi = new gpsApi(); //gpsApi 객체 생성
		Adress gpsxy;
		
		gpsxy = gapi.bringGpsfromApi();
		
		LatXLngY xy;
		/**위도 경도 입력 받아 xy좌표로 변경하는 부분*/
		xy = XY.convertGRID_GPS(gpsxy.Y ,gpsxy.X); 
    	//System.out.println(gpsxy.X+" "+gpsxy.Y); //GPS 테스트

		
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
    	
    	String nx = "98"; //임시 데이터 값 부산
    	String ny = "76";//임시 데이터 값  
    	
    	nx = Integer.toString((int)xy.x); /**변경된 위도경도 x,y값을 스트링형으로 변형*/
    	ny = Integer.toString((int)xy.y);
    	
    	//System.out.println(nx+ny);//x,y 좌표 테스트
    	
    	for(int i = 1;i<4;i++) {
    		wapi.bringWeaterFromApi(Integer.toString(i),base_date,base_time,nx,ny);   		
    	}
        System.out.println(gpsxy.adress); //adress 출력 
        //System.out.println(gpsxy.jibunAddress); //jibunAddress 출력
        
    
    	
	}

}
