/**
 * 
 */
package com.finder.common;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.finder.dto.Coordinates;
import com.finder.exception.BadRequestException;
import com.finder.exception.FinderExceptions;
import com.finder.exception.JSONParseException;

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
	public void assertRequestCoordinatesPresent(String parameter, String lng, String lat) throws BadRequestException{
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
	 * @param lng
	 * @param lat
	 * @throws BadRequestException
	 * @throws JSONException 
	 */
	public void assertRequestCoordinatesNotPresent(String parameter, String resultsKey, Coordinates coords, JSONObject response) throws BadRequestException, JSONException{
		if(response.get(resultsKey)==null){
			String message = FinderExceptions.INVALID_PARAM_REQUEST.getMessage();
			message = String.format(message, parameter);
			logger.info("Throwing BadRequestException : {}", message);
			throw new JSONParseException(message, FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getErrorCode());
		}
	}
	/**
	 * Routine to throw if the request parameters are invalid coordinates
	 * @param parameter
	 * @param lng
	 * @param lat
	 * @throws BadRequestException
	 * @throws JSONException 
	 */
	public void assertFT(String parameter, String resultsKey, Coordinates coords, JSONObject response) throws BadRequestException, JSONException{
		if(response.get(resultsKey)==null){
			String message = FinderExceptions.INVALID_PARAM_REQUEST.getMessage();
			message = String.format(message, parameter);
			logger.info("Throwing BadRequestException : {}", message);
			throw new JSONParseException(message, FinderExceptions.REQUIRED_PARAM_NOT_PRESENT.getErrorCode());
		}
	}
}
