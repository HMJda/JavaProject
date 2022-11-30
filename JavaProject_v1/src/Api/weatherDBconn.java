package Api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class weatherDBconn {
	/** db 연결 */
	public Connection dbConn() {
		Connection connect = null;
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            String user = "system"; 
            String pw = "1";
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
	/** 테이블 선택해서 가져오는 부분 */
	 public PreparedStatement select(String Table) { //테이블 선택해서 가져오기
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
	     String selectQuery = 
	    			"select * from "+ Table; //테이블 선택 
	     try {
			PreparedStatement select = connect.prepareStatement(selectQuery);
			return select;
		 } catch (SQLException e) {
			e.printStackTrace();
			return null;
		 }
	 }	
}
