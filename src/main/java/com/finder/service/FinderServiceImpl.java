/**
 * @author Aishwarya Sivaraman
*/

package com.finder.service;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.dto.SearchResults;


@Component
@Configuration
public class FinderServiceImpl implements FinderService {
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);
	
	@Value("${places.base.url}")
	private String placeBaseUrl;
	
	@Value("${places.key}")
	private String placesKey;
	
	private Client client = ClientBuilder.newClient().register(JacksonFeature.class);
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	/* (non-Javadoc)
	 * @see com.finder.service.FinderService#getLocation(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public SearchResults getLocation(String longitude, String latitude,
			Integer radius, List<String> types, String name) throws JsonParseException, JsonMappingException, IOException, JSONException {
		logger.info("Getting locations from file...({})");
		WebTarget target = client.target(placeBaseUrl)
				.queryParam("location", longitude+","+latitude)
				.queryParam("radius", radius)
				.queryParam("name",  name)
				.queryParam("key", placesKey);
		
		logger.info("target.request()...({})", target.getUri());
		String response = target.request("application/json").get(String.class);
		logger.info("ResponseString:{}", response);
		//JSONObject responseBody = new JSONObject(response);
		SearchResults results = mapper.readValue(response, SearchResults.class);
		logger.info("SearchResults from Google:{}", results);
		return results;
				
	}
	
}