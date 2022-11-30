package Api;

import java.time.LocalDate; //날짜
import java.time.format.DateTimeFormatter; //시간 포맷
import java.time.LocalTime; //시간

/** https://code.google.com/archive/p/json-simple/downloads 다운로드후 추가 https://bp666.tistory.com/6 */
import org.json.simple.JSONArray; //json 관련 라이브러리   
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader; //url 관련 라이브러리
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.*; // SQL 
import Api.weatherDBconn;

public class weatherApi{
	/** 현재 날짜 가져오기*/ //https://hianna.tistory.com/607 참고함
	public String localDate() 
    {
    
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    
		String data = now.format(formatter);
    
		return data;    
    }
	/** 현재 시간 가져오기*/
	public String localTime() 
    {
    
		LocalTime now = LocalTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm"); //포맷을 시간만 표시하게함
    
		String data = now.format(formatter);
    
		return data;    
    }
	  /**
     * 현재시간을 가져와서 ex) 1000 형태로 만들어줌
     * 3시간 마다 업데이트 되기 때문에 각 시간에 따라 업데이트 시간으로 설정*/
	public String timeChange(String LocalTime) 
    {
            String baseTime = "";
            
            // 현재 시간에 따라 데이터 시간 설정(3시간 마다 업데이트) //
            switch(LocalTime+"00") {
            
            case "0200":
            case "0300":
            case "0400":
                baseTime = "0200";
                break;
            case "0500":
            case "0600":
            case "0700":
                baseTime = "0500";
                break;
            case "0800":
            case "0900":
            case "1000":
                baseTime = "0800";
                break;
            case "1100":
            case "1200":
            case "1300":
                baseTime = "1100";
                break;
            case "1400":
            case "1500":
            case "1600":
                baseTime = "1400";
                break;
            case "1700":
            case "1800":
            case "1900":
                baseTime = "1700";
                break;
            case "2000":
            case "2100":
            case "2200":
                baseTime = "2000";
                break;
            default: 
                baseTime = "2300";
                
            }
            return baseTime;
    }
	
	/**PageNO, base_date, base_time, nx, ny 
	 * pageNO는 base time 23시간 기준 1일이면 내일 2면 2일뒤 3이면 3일뒤이나 한계값은 3 
	 * base_date와 base_time은 기상청예보가 나왔을떄 받는 기준 시간 내일 시간을 입력하면 안됨 기상청에서 정보가 안나왔기떄문 1~3일뒤의 정보를 알려면 pageNo 이용
	 * nx와 ny는 경도 위도 값*/
	public void bringWeaterFromApi(String pageNo,String base_date, String base_time, String nx,String ny)throws IOException {
		Api.weatherDBconn dbConn = new Api.weatherDBconn();
    	//http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst 단기 예보 
		//http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst 초단기 예보
    	String URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    	String serviceKey = "Pgi1iRwF2BddrARqlNbIA27xW19ct1RXz9ZPDZSRqEMP2WjQC93JFHc1rOw1cNCqWMDRonq9F8rDCR7hA5SQcw%3D%3D";
    	// pageNo 아마 보여줄 1이면 ex1015 기준 1이면 16일 2이면 17일 ....
    	String numOfRows = "290";//290이 00~23시까지 출력 2+ 12 * 시간
    	String dataType = "JSON";                     
        StringBuilder urlBuilder = new StringBuilder(URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();        //api 호출 끝
        
        //System.out.println(sb.toString()); //혹시모를 api 출력문 println
       
        
        /** db 연결 부분 */
        Connection connect = dbConn.dbConn();
        /* CREATE TABLE  날씨(
    		날짜 CHAR(20) NOT NULL,
    		시간 CHAR(20) NOT NULL,
    		기온 CHAR(20),
    		풍속 CHAR(20),
    		하늘상태 CHAR(20),
    		강수형태 CHAR(20),
    		강수확률 CHAR(20),
    		최고기온 CHAR(20),
    		최저기온 CHAR(20)
			);
         */	  
        /** db 연결 부분 */   
        
        
        String result = sb.toString();
        //값을 스트링배열에 저장 하는 부분
        JSONObject WeatherData;
        String date = "";
        String time = "";
        String DataValue = "";
        String info = "";

        /**저장용 문자열*/
        String temp = null;//기온
        String wind = null;//풍속
        String sky= null; //하늘상태
        String pp = null; //강수형태
        String POP = null; //강수확률
        String TMX = null; //최고기온
        String TMN = null; //최저기온

        String checkTime = ""; //현재 시간 확인
        /*try {
			PreparedStatement reset = connect.prepareStatement("DELETE FROM 날씨");
			reset.executeUpdate();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}*/
        try {
        	JSONParser parsar = new JSONParser();
        	JSONObject obj = (JSONObject) parsar.parse(result);
        	JSONObject response = (JSONObject) obj.get("response");
        	JSONObject body = (JSONObject)response.get("body");
        	JSONObject items = (JSONObject)body.get("items");
        	JSONArray item = (JSONArray)items.get("item");
        
            for(int i = 0; i < item.size(); i++) 
            {
                WeatherData = (JSONObject) item.get(i);             
                date = WeatherData.get("fcstDate").toString();
                time = WeatherData.get("fcstTime").toString();
                DataValue = WeatherData.get("fcstValue").toString();
                info = WeatherData.get("category").toString();
                checkTime = time; //fcstTime 넘겨줌
                
                if(info.equals("POP")) { //강수확률               
                	info = "강수확률";
                	POP  = DataValue+" %";
                }
                if(info.equals("PTY")){ //강수
                	info = "강수형태"; 
                	if(DataValue.equals("0")) {
                		pp = "없음";
                	}else if(DataValue.equals("1")) {
                		pp = "비";
                	}else if(DataValue.equals("2")) {
                		pp = "눈/비";
                	}else if(DataValue.equals("3")) {
                		pp = "눈";
                	}else if(DataValue.equals("4")) {
                		pp = "소나기";
                	}
                }
                if(info.equals("SKY")) { //하늘상태
                	info = "하늘상태";
                	if(DataValue.equals("1")) {
                		sky = "맑음";
                	}else if(DataValue.equals("2")) {
                		sky = "비";
                	}else if(DataValue.equals("3")) {
                		sky = "구름많음";
                	}else if(DataValue.equals("4")) {
                		sky = "흐림";
                	}
                }
                if(info.equals("TMP")) { //기온
                	info = "기온";
                	temp = DataValue+" ℃";
                }
                if(info.equals("WSD")) { //풍속 
                	info = "풍속";
                	wind = DataValue + " m/s";
                }
                if(info.equals("TMX")) { //최고 기온
                	info = "기온";
                	TMX = DataValue + " ℃";
                }
                if(info.equals("TMN")) { //최저 기온
                	info = "기온";
                	TMN = DataValue + " ℃";
                }
                /** db 저장 부분 */  
                String insertQuery = 
    	    			"insert into 날씨 (날짜,시간,기온,풍속,하늘상태,강수형태,강수확률,최고기온,최저기온) values (?, ?,?,?,?,?,?,?,?)";
                if(info.equals("SNO")||info.equals("TMX")||info.equals("TMN")) {
                	try {
                    	PreparedStatement preState = connect.prepareStatement(insertQuery); // db에 정보 입력 부분
                    	preState.setString(1,date );
                    	preState.setString(2, checkTime);
                    	preState.setString(3, temp);
                    	preState.setString(4, wind);
                    	preState.setString(5, sky);
                    	preState.setString(6, pp);
                    	preState.setString(7, POP);
                    	preState.setString(8, TMX);
                    	preState.setString(9, TMN);
                    	preState.executeUpdate();
                    } catch (SQLException e1) {
                    	e1.printStackTrace();
                    }
                }
                /** db 저장 부분 */  
            } 

        }
        catch(Exception e) 
        {
        	System.out.println(e);
        }             
    }
}
