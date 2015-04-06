package com.panorama.service;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.panoram.http.client.MyHTTPClient;

@Path("/myService")
public class MyService {
	@EJB 
	MyHTTPClient httpClient;
    @GET
    @Path("/sayHello")
    @Produces(MediaType.APPLICATION_JSON )
	public String sayHello(){
    	
    	//String data="{title: 'foo',body: 'bar',userId: 1}";
    	return httpClient.request("http://quizwebapi.azurewebsites.net/Api/EndUsers");
    	//return httpClient.post("http://quizwebapi.azurewebsites.net/Api/EndUsers",data);
	}
}
