package Ui;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
	
	private LoginDAO() {}
	private static LoginDAO instance = new LoginDAO();
	public static LoginDAO getInstance() {
		return instance;
	}
	
	Connection conn = null; // DB와 연결된 객체
	PreparedStatement pstm = null; // SQL문을 담는 객체
	ResultSet rs; // SQL문 결과를 담는 객체
	
	public int findByUserIdAndPw(String id, String pw) {
		conn = DBConn.getConnection();
		try {
			pstm = conn.prepareStatement("select * from join_user where user_id = ? and user_pw = ?");
			pstm.setString(1, id);
			pstm.setString(2, pw);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}
	
	public int Join(LoginDTO user) { 
		
	      String query = "insert into join_user(user_name,user_email,user_id,user_pw,consti,cardigan,"
	      		+ "Padding,Windbreaker,Jacket,zipup,Coat,Yaasang,Fleece,Shortsleeve,Blouse,shirts,"
	      		+ "Sleeveless,Knit,Vest,Longsleeves,Hoodie,MTM,longpants,slacks,Jeans,Shortspants,"
	      		+ "miniskirt,longskirt,Muffler,longdress,Shortsdress) "
	      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	      try {
	         conn = DBConn.getConnection();
	         pstm = conn.prepareStatement(query);
	         
	         pstm.setString(1, user.getName());
	         pstm.setString(2, user.getEmail());
	         pstm.setString(3, user.getId());
	         pstm.setString(4, user.getPw());
	         pstm.setString(5, user.getConsti());
	         pstm.setString(6, user.getCardiigan());
	         pstm.setString(7, user.getPadding());
	         pstm.setString(8, user.getWindbreaker());
	         pstm.setString(9, user.getJacket());
	         pstm.setString(10, user.getZipup());
	         pstm.setString(11, user.getCoat());
	         pstm.setString(12, user.getYaasang());
	         pstm.setString(13, user.getFleece());
	         pstm.setString(14, user.getShortsleeve());
	         pstm.setString(15, user.getBlouse());
	         pstm.setString(16, user.getShirts());
	         pstm.setString(17, user.getSleeveless());
	         pstm.setString(18, user.getKnit());
	         pstm.setString(19, user.getVest());
	         pstm.setString(20, user.getLongsleeves());
	         pstm.setString(21, user.getHoodie());
	         pstm.setString(22, user.getMtm());
	         pstm.setString(23, user.getLongpants());
	         pstm.setString(24, user.getSlacks());
	         pstm.setString(25, user.getJeans());
	         pstm.setString(26, user.getShortpants());
	         pstm.setString(27, user.getMiniskirt());
	         pstm.setString(28, user.getLongskirt());
	         pstm.setString(29, user.getMuffler());
	         pstm.setString(30, user.getLongdress());
	         pstm.setString(31, user.getShortdress());
	         
	         
	         pstm.executeUpdate();
	         return 1;
	         
	      } catch (SQLException e) {
	    	  e.printStackTrace();
	      }
	      return -1;
	   }
	   
}
