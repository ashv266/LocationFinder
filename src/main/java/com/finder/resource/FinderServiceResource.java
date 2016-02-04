package com.finder.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.finder.service.FinderService;

@Component
@Path("/")
public class FinderServiceResource {
	Logger logger = LoggerFactory.getLogger(FinderServiceResource.class);
	
	@Autowired
	FinderService finderService;
	
	@GET
	@Path("lattitude/{lattitude}/longitude/{longitude}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getLocation(@PathParam("lattitude") String lattitude, @PathParam("longitude") String longitude){
		String state = new String();
		try{
			state = finderService.getLocationFromFile(lattitude, longitude);
		}catch(Exception e){
			logger.error("FinderServiceResource.getLocations(lattitude:{}, longitude:{})", lattitude, longitude);
			return Response.serverError().entity(state).build();
		}
		
		return Response.ok()
				.entity(state)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET<POST<DELETE<PUT")
				.allow("OPTIONS").build();
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getStaticLocation(@FormParam("latitude") String latitude,@FormParam("longitude") String longitude ){
		String state = null;
		try{
			state = finderService.getLocationFromFile(latitude, longitude);
		}catch(Exception e){
			logger.error("FinderServiceResource.getLocations(lattitude:{}, longitude:{})",e);
			return Response.serverError().entity(state).build();
		}
		
		logger.debug("This is state from curl:state={},latitude={}, longitude={}", state, latitude, longitude);
		return Response.ok()
				.entity(state)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET<POST<DELETE<PUT")
				.allow("OPTIONS").build();
	}
	
}
