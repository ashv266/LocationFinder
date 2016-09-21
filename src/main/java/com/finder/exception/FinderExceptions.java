/**
 * 
 */
package com.finder.exception;

/**
 * @author aishwaryasivaraman
 *
 */
public enum FinderExceptions {

	INVALID_PARAM_REQUEST(8000, "An invalid request has been detected for argument: '%s' value: '%s'"),
	REQUIRED_PARAM_REQUEST(8001, "Required parameter '%s' is not present, root cause: %s"),
	REQUIRED_PARAM_NOT_PRESENT(8002, "Required parameter '%s' not provided"),
	INVALID_NO_CONTENT(8003, "invalid request. no valid properties to update resource '%s'"),
	INVALID_PROPERTY(8004, "Invalid property '%s' provided"),
	INVALID_PROPERTY_IS_NULL(8005, "Invalid property '%s' is null"),
	INVALID_DATE_PROPERTY(8006, "Invalid date property. Expecting date to be be '%s' format"), 
	INVALID_REQUEST(8007, "Invalid API request '%s' with HttpStatus '%s'"),
	INVALID_MAPPING(8008, "Invalid mapping from API to container class '%s'"),
	;
	
	private int errorCode;
	private String message;
	
	FinderExceptions(int errorCode, String message){
		this.errorCode = errorCode;
		this.setMessage(message);
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
