/**
 * 
 */
package com.finder.common;

import java.net.URI;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.finder.dto.Coordinates;
import com.finder.dto.Results;
import com.finder.exception.BadRequestException;
import com.finder.exception.FinderExceptions;
import com.finder.exception.JSONParseException;
import com.finder.exception.NotFoundException;

/**
 * @author aishwaryasivaraman
 *
 */
@Component
public class Assertions {

	private final static Logger logger = LoggerFactory.getLogger(Assertions.class);
	
	/**
	 * Routine to throw if the request parameters are null
	 * @param parameter
	 * @param lng
	 * @param lat
	 * @throws BadRequestException
	 */
	public void assertRequestCoordinatesExist(String parameter, String lng, String lat) throws BadRequestException{
		if(StringUtils.isEmpty(lng) || StringUtils.isEmpty(lat)){
			String message = FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getMessage();
			message = String.format(message, parameter);
			logger.info("Throwing BadRequestException : {}", message);
			throw new BadRequestException(FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getErrorCode(), message);
		}
	}
	
	/**
	 * Routine to throw if the request parameters are invalid coordinates
	 * @param parameter
	 * @throws BadRequestException
	 * @throws JSONException 
	 */
	public void assertRequestCoordinatesNotPresent(String parameter, String resultsKey,JSONObject response) throws BadRequestException, JSONException{
		if(response.get(resultsKey)==null){
			String message = FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getMessage();
			message = String.format(message, parameter);
			logger.info("Throwing BadRequestException : {}", message);
			throw new JSONParseException(FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getErrorCode(),message);
		}
	}


	/**
	 * @param propertyName
	 * @param origin
	 * @throws BadRequestException 
	 */
	public void assertAddressIsNonNull(String propertyName, String address) throws BadRequestException {
		if(StringUtils.isEmpty(address)){
			String message = FinderExceptions.INVALID_PARAM_REQUEST.getMessage();
			message = String.format(message, propertyName, address);
			logger.info("Throwing 'BadRequestException' : {}", message);
			throw new BadRequestException(FinderExceptions.INVALID_PARAM_REQUEST.getErrorCode(), message);
		}
		
	}

	/**
	 * @param uri
	 * @param request
	 * @throws BadRequestException 
	 * @throws NotFoundException 
	 */
	public void assertRequestIsValid(URI uri, Builder request) throws BadRequestException, NotFoundException {
		Response response = request.get();
		if(!response.hasEntity() && response.getStatus()!=200){
			String message = FinderExceptions.INVALID_REQUEST.getMessage();
			message = String.format(message, uri.toString(), response.getStatus());
			logger.info("Throwing 'BadRequestException' : {}", message);
			throw new BadRequestException(FinderExceptions.INVALID_REQUEST.getErrorCode(), message);
		}
		else if(!response.hasEntity() && response.getStatus()==200){
			String message = FinderExceptions.INVALID_NO_CONTENT.getMessage();
			message = String.format(message, uri.toString(), response.getStatus());
			logger.info("Throwing 'NotFoundException' : {}", message);
			throw new NotFoundException(FinderExceptions.INVALID_NO_CONTENT.getErrorCode(), message);
		}
		
	}

	/**
	 * @param results
	 * @throws JSONParseException 
	 */
	public void assertMappingNotEmpty(String propertyName, Results results) throws JSONParseException {
		if(results==null || !results.getStatus().equalsIgnoreCase("OK")){
			String message = FinderExceptions.INVALID_MAPPING.getMessage();
			message = String.format(message, propertyName);
			logger.info("Throwing 'JsonParseException':{}", message);
			throw new JSONParseException(FinderExceptions.INVALID_MAPPING.getErrorCode(), message);
		}
		
	}
}
