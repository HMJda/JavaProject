package Api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Api.gpsApi.Adress;
import Api.translateXY.LatXLngY;

public class weatherDBconn {
	/** db 연결 */
	public Connection dbConn() {
		Connection connect = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            String user = "system";  //오라클 db 유저
            String pw = "1"; //오라클 db 비밀번호 
            String DBURL = "jdbc:oracle:thin:@localhost:1521:xe";
            
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            connect = DriverManager.getConnection(DBURL, user, pw); 
            return connect;
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
            return null;
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : "+sqle.toString());
            return null;
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
            return null;
        }
	}
	/** 테이블 선택해서 가져오는 부분 
	 * @throws SQLException */
	 public ResultSet select(String Table) throws SQLException { //테이블 선택해서 가져오기
		 Connection connect = dbConn(); 
		 Statement stmt;
		 stmt = connect.createStatement();
	     String selectQuery = 
	    			"select * from "+ Table; //테이블 선택 
	     try {
	    	 ResultSet select = stmt.executeQuery(selectQuery);
			return select;
		 } catch (SQLException e) {
			e.printStackTrace();	
			return null;
		 }
	 }
	 /** 오늘 최고 기온을 가져옴*/
	 public double BringTMX() throws SQLException {		 
		 Api.weatherApi w = new Api.weatherApi();
		 double TMX = 0.0;		 
		 Connection connect = dbConn(); 
		 ResultSet rs = select("날씨"); //ResultSet에 선택한 테이블 넣기
		 
		 while(rs.next()) {	//rs.next를 통해 다음 row로 이동
			 if(rs.getString(1).trim().equals(w.localDate())   ) {//getString(i)는 i번째 컬럼을 가져옴(string) trim 사용해서 공백제거 (현재 날짜와, DB상의 날짜 비교)
				if(rs.getString(8) != null) {
					TMX = Double.parseDouble(rs.getString(8).replaceAll(" ℃", ""));
					break;
				}
				else {
					 if(TMX==0.0) {
						 TMX = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
					 }
					 else if(TMX < Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""))) {
						 TMX = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
					 }
				}
			 }
		 }		 
		 connect.close();
		 return TMX;
	 }
	 /** 오늘 최저 기온을 가져옴*/
	 public double BringTMN() throws SQLException {
		 Api.weatherApi w = new Api.weatherApi();
		 double TMN = 0.0;		 
		 Connection connect = dbConn(); 
		 ResultSet rs = select("날씨");
		 while(rs.next()) {
			 if(rs.getString(1).trim().equals(w.localDate())  ) {
				 if(rs.getString(9) != null) {
						TMN = Double.parseDouble(rs.getString(9).replaceAll(" ℃", ""));
						break;
				}
				 else {
					 if(TMN==0.0) {
						 TMN = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
					 }
					 else if(TMN > Double.parseDouble(rs.getString(3).replaceAll(" ℃", "")) ) {					
						 TMN = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
					 }
				 }
				
			 }
		 }
		 connect.close();
		 return TMN;
	 }
	 /** DB에 위치정보 저장*/
	 public void Inputxy(String gps) {
		 gpsApi g = new gpsApi();
		 translateXY XY = new translateXY();
		 Adress gpsxy;
		 LatXLngY xy;
		 gpsxy = g.bringGpsfromApi(gps);
		 /**위도 경도 입력 받아 xy좌표로 변경하는 부분*/
		 xy = XY.convertGRID_GPS(gpsxy.Y ,gpsxy.X);
		 String nx = "98"; //임시 데이터 값 부산
	     String ny = "76";//임시 데이터 값  
	    	
	     nx = Integer.toString((int)xy.x); /**변경된 위도경도 x,y값을 스트링형으로 변형*/
	     ny = Integer.toString((int)xy.y);
		 Connection connect = dbConn(); //db연결
		 /*
		    CREATE TABLE  위치정보(
    			nx CHAR(20) NOT NULL,
    			ny CHAR(20) NOT NULL    
			);
		  */
		 String insertQuery = 
	    			"insert into 위치정보 (nx,ny) values (?,?)";
		 try {
         	PreparedStatement preState = connect.prepareStatement(insertQuery); // db에 정보 입력 부분
         	preState.setString(1,nx);
         	preState.setString(2,ny);
         	preState.executeUpdate();
         } catch (SQLException e1) {
         	e1.printStackTrace();
         }
	 }
	 /** DB에서 위치정보 nx 가져오기*/
	 public String BringX() throws SQLException {
		 String nx = "";	
		 Connection connect = dbConn(); 
		 ResultSet rs = select("위치정보");		
		 while(rs.next()) {//열이 있는 경우에만 nx 값 저장 
			 nx = rs.getString(1);		
			 connect.close();
			 return nx;
		 }		  
		 return null;
	 }
	 /** DB에서 위치정보 ny 가져오기*/
	 public String BringY() throws SQLException {
		 String ny = "";	
		 Connection connect = dbConn(); 
		 ResultSet rs = select("위치정보");		
		 while(rs.next()) { //열이 있는 경우에만 ny 값 저장
			 ny = rs.getString(2);		
			 connect.close();
			 return ny;
		 }		 
		 connect.close();
		 return null;
	 }
}
