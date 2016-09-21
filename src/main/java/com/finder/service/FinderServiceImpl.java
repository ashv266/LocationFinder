/**
 * @author Aishwarya Sivaraman
*/

package com.finder.service;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.common.Assertions;
import com.finder.common.Utilities;
import com.finder.dto.Coordinates;
import com.finder.dto.GeocodedResult;
import com.finder.dto.GeocodedResults;
import com.finder.dto.SearchResults;
import com.finder.exception.BadRequestException;
import com.finder.exception.JSONParseException;
import com.finder.exception.NotFoundException;

@Component
@Configuration
public class FinderServiceImpl implements FinderService {
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);
	
	@Autowired
	Assertions assertions;
	
	@Value("${places.base.url}")
	private String placeBaseUrl;
	
	@Value("${geocode.base.url}")
	private String geocodeBaseUrl;
	
	@Value("${api.key}")
	private String placesKey;
	
	private Client client = ClientBuilder.newClient().register(JacksonFeature.class);
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	/* (non-Javadoc)
	 * @see com.finder.service.FinderService#getPlacesForMidpoint(java.lang.String, java.lang.Integer, java.util.List, java.lang.String)
	 */
	@Override
	public SearchResults getPlacesForMidpoint(String origin, String destination, Integer radius, List<String> types, String name) throws BadRequestException, JSONParseException, NotFoundException {
		assertGivenLocationsAreValid(origin, destination);
		
		GeocodedResults geocodedOriginResults= getGeocodedResultsFromGoogle(origin);
		GeocodedResults geocodedDestResults= getGeocodedResultsFromGoogle(destination);

		Coordinates geocodedOrigin = getNearestResultFrom(geocodedOriginResults);
		Coordinates geocodedDest = getNearestResultFrom(geocodedDestResults);
		
		Coordinates midpoint = Utilities.calculateMidpoint(geocodedOrigin,geocodedDest);
		SearchResults results = getLocation(midpoint, radius, types, name);
		return results;
	}


	/* (non-Javadoc)
	 * @see com.finder.service.FinderService#getLocation(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public SearchResults getLocation(Coordinates coords,
			Integer radius, List<String> types, String name) throws BadRequestException, JSONParseException, NotFoundException{
		logger.info("Getting locations from file...({})");
		WebTarget target = client.target(placeBaseUrl)
				.queryParam("location", coords.getLatitude()+","+coords.getLongitude())
				.queryParam("radius", radius)
				.queryParam("name",  name)
				.queryParam("key", placesKey);
		
		logger.info("target.request()...({})", target.getUri());
		Builder request = target.request("application/json");
		assertions.assertRequestIsValid(target.getUri(), request);
		SearchResults results = request.get(SearchResults.class);
		assertions.assertMappingNotEmpty("SearchResults", results);
		return results;
	}

	/**
	 * @param endpoint
	 * @throws NotFoundException 
	 */
	private GeocodedResults getGeocodedResultsFromGoogle(String endpoint) throws BadRequestException, JSONParseException, NotFoundException{
		WebTarget target = client.target(geocodeBaseUrl)
				.queryParam("address", endpoint)
				.queryParam("key", placesKey);
		logger.info("target.request()...({})", target.getUri());
		Builder request = target.request("application/json");
		assertions.assertRequestIsValid(target.getUri(), request);
		
		GeocodedResults geocodedResults = request.get(GeocodedResults.class);
		assertions.assertMappingNotEmpty("GeocodedResults", geocodedResults);
		
		return geocodedResults;
	}

	/**
	 * @param geocodedResults
	 */
	private Coordinates getNearestResultFrom(GeocodedResults geocodedResults) {
		GeocodedResult result = geocodedResults.getResults().get(0);
		Coordinates geocodedLocation = result.getGeometry().getLocation();
		return geocodedLocation;
	}
	
	/**
	 * @param origin
	 * @param destination
	 * @throws BadRequestException 
	 */
	private void assertGivenLocationsAreValid(String origin, String destination) throws BadRequestException {
		assertions.assertAddressIsNonNull("origin", origin);
		assertions.assertAddressIsNonNull("destination", destination);
	}
}