package Api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class gpsApi {
	public class Adress{
		String adress;
		String jibunAddress;
		double X;
		double Y;
	}
	public Adress bringGpsfromApi() {
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));	
		String clientId = "아이디";
		String clientSecret = "시크릿";
		Adress ad = new Adress();
		try {
			System.out.println("주소를 입력해주세요 : ");
			
			String address = io.readLine();
			String addr = URLEncoder.encode(address, "UTF-8");
			
			// Geocoding 개요에 나와있는 API URL 입력.
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;	// JSON
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			// Geocoding 개요에 나와있는 요청 헤더 입력.
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			
			// 요청 결과 확인. 정상 호출인 경우 200
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			
			StringBuffer response = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);			
			JSONArray arr = object.getJSONArray("addresses");
			
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				ad.adress = temp.get("roadAddress").toString();
				//System.out.println("address : " + temp.get("roadAddress"));
				ad.jibunAddress = temp.get("jibunAddress").toString();
				//System.out.println("jibunAddress : " + temp.get("jibunAddress"));
				//System.out.println("위도 : " + temp.get("y"));
				ad.Y = Double.parseDouble(temp.get("y").toString());
				//System.out.println("경도 : " + temp.get("x"));
				ad.X = Double.parseDouble(temp.get("x").toString());
			}
			
			// JSON.simple 사용한 경우 아래와 같이 진행.
			/*JSONParser jpr = new JSONParser();
			JSONObject jarr = (JSONObject) jpr.parse(response.toString());
			JSONArray arr2 = (JSONArray) jarr.get("addresses");
				
			for (int i = 0; i < arr2.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				System.out.println("address : " + temp.get("roadAddress"));
				System.out.println("jibunAddress : " + temp.get("jibunAddress"));
				System.out.println("위도 : " + temp.get("y"));
				System.out.println("경도 : " + temp.get("x"));
			}*/
			return ad;
		} catch (Exception  e) {
			System.out.println(e);
			return null;
		}
	}
}