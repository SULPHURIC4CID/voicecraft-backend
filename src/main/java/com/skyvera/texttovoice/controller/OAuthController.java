package com.skyvera.texttovoice.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/login")
public class OAuthController {

	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	private static final String REDIRECT_URI = "http://localhost:3000";
	private static final String GRANT_TYPE ="authorization_code";
	
	private static String accessToken;
	
	//Setter for Access Token
	public void setAccessToken(String token)
	{
		accessToken = token;
	}
	
	//Getter for AccessToken
	
	public String getAccessToken()
	{
		return accessToken;
	}
	
	
	@PostMapping("/revoke/token")
	public String revokeToken()
	{
		//Make a HttpPost call to fetch the token using the code
		try {
	      URL url = new URL("https://oauth2.googleapis.com/revoke");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("POST");
	      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	      conn.setDoOutput(true);
	      
	      String data = "token="+accessToken;
	      try (OutputStream os = conn.getOutputStream()) {
	        byte[] input = data.getBytes("utf-8");
	        os.write(input, 0, input.length);
	      }
	      //Data sent to Google OAuth server for revoking access	      
	      System.out.println("Response Code : "+conn.getResponseCode());	      
	     
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
				
		return "SUCCESS";
	}
	
	@PostMapping("/get/token")
	public String getToken(@RequestBody String authorizationCode)
	{
		System.out.println("Payload : "+authorizationCode);
		//Extract AuthCode from request payload
		String authCode = authorizationCode.split(":")[1];
		StringBuilder code = new StringBuilder();
		for(int i = 0;i<authCode.length();i++)
		{			
			if(i!=0 && i!=authCode.length()-1 && i!=authCode.length()-2)
			{
				code.append(authCode.charAt(i));
				System.out.println("Character : "+authCode.charAt(i));
			}
		}
		
		
		System.out.println("AuthorizationCode : "+code.toString());
		String result = "";
		//Make a HttpPost call to fetch the token using the code
		try {
		      URL url = new URL("https://oauth2.googleapis.com/token");
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setRequestMethod("POST");
		      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		      conn.setDoOutput(true);

		      String data = "client_id="+CLIENT_ID+"&client_secret="+CLIENT_SECRET+"&code="+code.toString()+"&redirect_uri="+REDIRECT_URI+"&grant_type="+GRANT_TYPE;
		      

		      try (OutputStream os = conn.getOutputStream()) {
		        byte[] input = data.getBytes("utf-8");
		        os.write(input, 0, input.length);
		      }
		      //Data sent to Google OAuth server for token
		      
		      System.out.println("Response Code : "+conn.getResponseCode());
		      try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
		        StringBuilder response = new StringBuilder();
		        String responseLine = null;
		        while ((responseLine = br.readLine()) != null) {
		          response.append(responseLine.trim());
		        }
		        System.out.println("Token JSON : "+response.toString());
		        JSONParser parser = new JSONParser();
		        JSONObject obj = (JSONObject) parser.parse(response.toString());
		        accessToken = (String) obj.get("access_token");
		        result = response.toString();
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
		return result;
	}
}
