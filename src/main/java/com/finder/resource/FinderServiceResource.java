package com.finder.resource;

import java.util.ArrayList;
import java.util.List;

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
		List<String> statesFound = new ArrayList<String>();
		try{
			statesFound = finderService.getPointState(lattitude, longitude);
		}catch(Exception e){
			logger.error("FinderServiceResource.getLocations(lattitude:{}, longitude:{})", lattitude, longitude);
			return Response.serverError().entity(statesFound).build();
		}
		
		return Response.ok()
				.entity(statesFound)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET<POST<DELETE<PUT")
				.allow("OPTIONS").build();
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getStateFromCoordinates(@FormParam("latitude") String latitude,@FormParam("longitude") String longitude ){
		List<String> statesFound = null;
		try{
			statesFound = finderService.getPointState(latitude, longitude);
		}catch(Exception e){
			logger.error("FinderServiceResource.getLocations(lattitude:{}, longitude:{})",e);
			return Response.serverError().entity(statesFound).build();
		}
		
		logger.debug("This is state from curl:state={},latitude={}, longitude={}", statesFound, latitude, longitude);
		return Response.ok()
				.entity(statesFound)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET<POST<DELETE<PUT")
				.allow("OPTIONS").build();
	}
	
}
