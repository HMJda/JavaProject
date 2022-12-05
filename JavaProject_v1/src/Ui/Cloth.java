package Ui;

import javax.swing.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cloth extends JFrame{
   public Cloth() {
      setTitle("날씨에 따른 옷 추천 프로그램");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c =getContentPane();
      c.setLayout(new FlowLayout());
      
      //오라클 연결 위한 4가지 정보
      String driver = "oracle.jdbc.driver.OracleDriver"; 
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String userid = "system";
      String passwd = "1";
      
      Connection con = null; //db 연결
      Statement stmt = null; //connect를 이용해 sql명령을 실행하는 객체
      
      double Temperature=0;
      
      try {
    	  Class.forName(driver);
          con = DriverManager.getConnection(url, userid, passwd);

         String viewStr1 = "SELECT * FROM 날씨";
         ResultSet result1 = stmt.executeQuery(viewStr1);
         Temperature= Double.parseDouble(result1.getString("기온"));
         System.out.println("데이터 조회 실패 이유 : " + Temperature);
         } catch(Exception e) {
            System.out.println("데이터 조회 실패 이유 : " + e.toString());
         }
      
      //날씨에 따른 옷분류
      String winter = "\n외투 : 패딩, 코트\n"
                  +"상의 : 후드티, 맨투맨, 니트\n"
                    +"하의 : 롱팬츠, 청바지, 슬랙스\n"
                    +"기타 : 목도리";
      
      String hard_winter ="\n이너 : 히트텍, 터틀넥\n"; // 추위를 많이타는경우
      
      String earlyWinter = "외투 : 야상, 플리스\n"
                         +"상의 : 후드티, 맨투맨, 니트\n"
                         +"하의 : 롱팬츠, 청바지, 슬랙스\n"
                         +"기타 : ";
      String beginWinter = "외투 : 집업, 플리스\n"
                     +"상의 : 긴팔, 니트, 후드티, 맨투맨\n"
                     +"하의 : 롱팬츠, 청바지, 슬랙스\n"
                     +"기타 : ";
      
      String fall = "외투 : 집업, 자켓\n"
                 +"상의 : 니트 , 긴팔, 맨투맨, 후드티\n"
                 +"하의 : 롱팬츠, 슬랙스, 청바지\n"
                 +"기타 : ";
      String earlyfall ="외투 : 가디건, 바람막이\n"
                  + "상의 : 셔츠, 긴팔, 블라우스\n"
                  + "하의 :롱팬츠, 청바지, 슬랙스, 롱 스커트\n"
                  + "기타 : 롱 원피스";
      String earlysummer ="외투 : \n"
                       +"상의 : 조끼, 셔츠, 반팔\n"
                       +"하의 : 청바지, 슬랙스, 숏팬츠\n"
                       + "기타 : 미니원피스";
      String beginsummer ="외투 : \n" 
                     +"상의 : 반팔,민소매\\n"
                     +"하의 : 숏팬츠, 미니스커트,청바지\n"
                     +"기타 : 미니원피스";
      String summer ="외투 : \n"
                 +"상의 : 반팔, 민소매\n"
                 +"하의 : 숏팬츠 미니스커트\n"
                 +"기타 : 미니 원피스";
      
      String hard_summer = "\n기타2 : 쿨토시, 목선풍기";//더위를 많이 타는 체질인 경우
      
      //기온에 따른 옷분류
      if(Temperature <=4) {
         System.out.println("현재 기온은 "+ Temperature +" 지금 날씨엔\n" + winter);
         System.out.println("추위를 많이 타시면" + winter + "" + hard_winter + "를 추천드립니다.");
      }
      else if(Temperature>=5 && Temperature<9) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+earlyWinter);
      }
      else if(Temperature>=9 && Temperature<12) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+beginWinter);
      }
      else if(Temperature>=12 && Temperature<17) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+fall);
      }
      else if(Temperature>=17 && Temperature<20) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+earlyfall);
      }
      else if(Temperature>=20 && Temperature<23) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+earlysummer);
      }
      else if(Temperature>=23 && Temperature<28) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+beginsummer);
      }
      else if(Temperature>=28) {
         System.out.println("현재 기온은 "+Temperature+" 지금 날씨엔\n"+summer);
         System.out.println("더위를 많이타시면" +summer + "" + hard_summer + "를 착용해보세요.");
      }
   
      
      JLabel textLabel = new JLabel("현재 날씨는"+Temperature+"입니다");
      
      String image1 = null;
      
      String path = System.getProperty("user.dir"); // path에 현재 작업 경로 입력
   
      //기온에따른 이미지
      if(Temperature <=4) {
         image1 = path+"\\옷 이미지\\winter.jpg";
      }
      else if(Temperature>=5 && Temperature<9) {
         image1 = path+"\\옷 이미지\\earlywinter";
      }
      else if(Temperature>=9 && Temperature<12) {
         image1 = path+"\\옷 이미지\\beginwinter.jpg";
      }
      else if(Temperature>=12 && Temperature<17) {
         image1 = path+"\\옷 이미지\\fall.jpg";
      }
      else if(Temperature>=17 && Temperature<20) {
         image1 = path+"\\옷 이미지\\earlyfall.jpg";
      }
      else if(Temperature>=20 && Temperature<23) {
         image1 = path+"\\옷 이미지\\earlysummer.jpg";
      }
      else if(Temperature>=23 && Temperature<28) {
         image1 = path+"\\옷 이미지\\earlysummer.jpg";
      }
      else if(Temperature>=28) {
         image1 = path+"\\옷 이미지\\summer.jpg";
         }
      
      ImageIcon img = new ImageIcon(image1);
      
      JLabel imageLabel = new JLabel(img);
      
      c.add(textLabel);
      c.add(imageLabel);
      
      setSize(300,500);
      setVisible(true);
   }
   
   public static void main(String[]args) {
      new Cloth();
   }
}