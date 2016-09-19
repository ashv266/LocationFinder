/**
 * @author Aishwarya Sivaraman
*/

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.finder.dto.SearchResults;
import com.finder.service.FinderService;

@Component
@Path("/")
public class FinderServiceResource {
	Logger logger = LoggerFactory.getLogger(FinderServiceResource.class);
	
	@Autowired
	FinderService finderService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getLocation(@QueryParam("latitude") String latitude, @QueryParam("longitude") String longitude,
			@DefaultValue("1000") @QueryParam("radius") Integer radius,
			@DefaultValue("") @QueryParam("type") List<String> types,
			@DefaultValue("") @QueryParam("name") String name){
		SearchResults response=null;
		try{
			response = finderService.getLocation(longitude, latitude, radius, types, name);
		}catch(Exception e){
			logger.error("FinderServiceResource.getLocations(lattitude:{}, longitude:{}): Error fetching state for coordinates provided", latitude, longitude, e);
			return Response.serverError().entity(response).build();
		}
		
		return Response.ok()
				.entity(response)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET<POST<DELETE<PUT")
				.allow("OPTIONS").build();
	}
	
}
