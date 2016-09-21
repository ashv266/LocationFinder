package com.finder.exception;

import org.apache.http.HttpResponse;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aishwaryasivaraman
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseBody
	public FinderError handleBadRequestException(BadRequestException exception){
		FinderError finderError = new FinderError();
		finderError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		finderError.setMessage(exception.getMessage());
		finderError.setErrorCode(exception.getErrorCode());
		return finderError;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseBody
	public FinderError handleNotFoundException(BadRequestException exception){
		FinderError finderError = new FinderError();
		finderError.setStatusCode(HttpStatus.NOT_FOUND.value());
		finderError.setMessage(exception.getMessage());
		finderError.setErrorCode(exception.getErrorCode());
		return finderError;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(value = JSONParseException.class)
	@ResponseBody
	public FinderError handleJSONException(JSONParseException exception){
		FinderError finderError = new FinderError();
		finderError.setStatusCode(HttpStatus.NO_CONTENT.value());
		finderError.setMessage(exception.getMessage());
		finderError.setErrorCode(exception.getErrorCode());
		return finderError;
	}
	
	//Catch validation exception on method params (binding)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public FinderError handleMethodArgumentTypeMismatchException(HttpResponse response, MethodArgumentNotValidException exception){
		FinderError finderError = new FinderError();
		
		finderError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		String message = FinderExceptions.INVALID_PARAM_REQUEST.getMessage();
		message = String.format(message,  exception.getClass(), exception.getParameter(), exception.getStackTrace());
		finderError.setMessage(message);
		finderError.setErrorCode(FinderExceptions.INVALID_PARAM_REQUEST.getErrorCode());
		
		return finderError;
	}
	
	//Catch validation exception on missing method param (binding)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler()
//	@ResponseBody
//	public FinderError handleMethodArgumentTypeMismatchException(HttpResponse response, MissingServletRequestParameterException exception){
//		FinderError finderError = new FinderError();
//		
//		finderError.setStatusCode(HttpStatus.BAD_REQUEST.value());
//		String message = FinderExceptions.REQUIRED_PARAM_REQUEST.getMessage();
//		message = String.format(message,  exception.getClass(), exception.getMessage(), exception.getParameterName());
//		finderError.setMessage(message);
//		finderError.setErrorCode(FinderExceptions.INVALID_PARAM_REQUEST.getErrorCode());
//		
//		return finderError;
//	}
}
