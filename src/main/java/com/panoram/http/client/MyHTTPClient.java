package com.panoram.http.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@Singleton
@LocalBean
public class MyHTTPClient {
	
	public String post(String url,String params){
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(
			url);
 
		StringEntity input;
		try {
			input = new StringEntity(params);
			input.setContentType("application/json");
			postRequest.setEntity(input);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result="";
	    result = processRequest(result, httpClient, postRequest);
		
		return"";
	}
	
	
	
	
	

	public String request(String url){
		String result="error";
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
			url);
		getRequest.addHeader("accept", "application/json");
 
		result = processRequest(result, httpClient, getRequest);
		return result;
	}






	private String processRequest(String result, DefaultHttpClient httpClient,
			HttpUriRequest getRequest) {
		HttpResponse response;
		try {
			response = httpClient.execute(getRequest);
	
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));
 
		String output;
		result="";
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			result+=output;
			 
		}
 
		httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return result;
	}
}
