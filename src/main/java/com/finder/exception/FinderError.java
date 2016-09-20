/**
 * 
 */
package com.finder.exception;

/**
 * @author aishwaryasivaraman
 *
 */
public class FinderError {

	private int errorCode;
	private String message;
	private StackTraceElement[] stackTrace;
	private int statusCode;
	
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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
	/**
	 * @return the stackTrace
	 */
	public StackTraceElement[] getStackTrace() {
		return stackTrace;
	}
	/**
	 * @param stackTrace the stackTrace to set
	 */
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = stackTrace;
	}

	
}
