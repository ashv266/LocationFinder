/**
 * 
 */
package com.finder.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aishwaryasivaraman
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception implements Serializable {

	private static final long serialVersionUID = -5951183893173265629L;
	private int errorCode;
	
	public BadRequestException(){
		super();
	}
	
	public BadRequestException(int errorCode, String msg){
		super(msg);
		this.errorCode = errorCode;
	}
	
	public BadRequestException(int errorCode, String msg, Exception e){
		super(msg, e);
		this.errorCode = errorCode;
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
	
}
