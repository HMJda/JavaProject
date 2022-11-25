package Api;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Api.translateXY.*;
import Api.gpsApi.*;

public class run {
	public static void main(String[] args) throws IOException{
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
    	int size = 26;
    	if(base_time == "0500"&& base_time == "1400"&& base_time == "2000") {
    		size = 27;
    	}
    	String nx = "98"; //임시 데이터 값 부산
    	String ny = "76";//임시 데이터 값  
    	
    	nx = Integer.toString((int)xy.x); /**변경된 위도경도 x,y값을 스트링형으로 변형*/
    	ny = Integer.toString((int)xy.y);
    	
    	//System.out.println(nx+ny);//x,y 좌표 테스트
    	
    	String[][] Value = new String[4][size];
    	for(int i = 1;i<4;i++) {
    		Value[i]=wapi.bringWeaterFromApi(Integer.toString(i),base_date,base_time,nx,ny);   		
    	}
        System.out.println(gpsxy.adress); //adress 출력 
        //System.out.println(gpsxy.jibunAddress); //jibunAddress 출력
        
    	for(int j = 1;j<4;j++) {
    		for(int i = 0;i<size;i++) {
                System.out.println(" "+Value[j][i]);
            }
    		System.out.println();
    	}		
    	
    	//메모장에 저장
    	String text = "";	//저장할 텍스트
    	String fileName = "infomation.txt";	//저장 경로와 메모장 이름
    	try {
    		File file = new File(fileName);
    		FileWriter writer = new FileWriter(file, false);	//true: 파일이 이미 있을 경우 text 이어서 작성
   		 
    		writer.write(gpsxy.adress + "\n");	//주소 저장
   		 
    		for(int j = 1;j<4;j++) {
   	    		for(int i = 0;i<size;i++) {
   	    			text = " "+Value[j][i] + "\n";
   	                writer.write(text);
   	            }
   	    		writer.write("\n");
   	    	}
   		 
    		writer.flush();	//FileWriter 내부 버퍼의 내용을 파일에 writer. flush() 호출 안 하면 내용이 버퍼에만 남고 파일에는 쓰이지 않는 상황 발생할 수 있음
    		writer.close();	//파일 닫기
    	} 
    	catch(Exception e) {
    		e.printStackTrace(); 	//오류 발생 시 오류사항을 콘솔창에 표시
    	}
	}

}
