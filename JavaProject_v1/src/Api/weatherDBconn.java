package Api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			 if(rs.getString(1).trim().equals(w.localDate())   ) {//getString(i)는 i번째 컬럼을 가져옴(string) trim 사용해서 공백제거
				 if(TMX==0.0) {
					 TMX = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
				 }
				 else if(TMX < Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""))) {
					 TMX = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
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
				 
				 if(TMN==0.0) {
					 TMN = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
				 }
				 else if(TMN > Double.parseDouble(rs.getString(3).replaceAll(" ℃", "")) ) {					
					 TMN = Double.parseDouble(rs.getString(3).replaceAll(" ℃", ""));
				 }
			 }
		 }
		 connect.close();
		 return TMN;
	 }
}
