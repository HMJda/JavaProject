package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Api.run;


public class Project01_D implements ActionListener {
	run r = new run();
	
	LocationGUI locationgui;
	public Project01_D(LocationGUI locationgui) {
		this.locationgui = locationgui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String clientId = "cqf5kmcxyb";
		String clientSecret = "7tmlHkZNCCZ5RDIES0OfsVsZ4sP4fa9OE1bA8Pxi";
		try {
			r.runApi();
		} catch (IOException | SQLException e1) {
			e1.printStackTrace();
		}		
		Address vo = null;
		
		try {
			String address = locationgui.address.getText();
			String addr = URLEncoder.encode(address, "UTF-8");
			String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);
			System.out.println(object);
			
			JSONArray arr = object.getJSONArray("addresses");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				vo = new Address();
				vo.setRoadAddress((String) temp.get("roadAddress"));
				vo.setJibunAddress((String)temp.get("jibunAddress"));
				vo.setX((String)temp.get("x"));
				vo.setY((String)temp.get("y"));
				System.out.println(vo);
			}
			
			
		project(vo);	
      
      } catch (Exception err) {
         System.out.println(err);
      }
   }
	
	public void project(Address vo) {
		locationgui.resAddress.setText(vo.getRoadAddress());
		locationgui.jibunAddress.setText(vo.getJibunAddress());
		locationgui.resX.setText(vo.getX());
		locationgui.resY.setText(vo.getY());
	}

 }
