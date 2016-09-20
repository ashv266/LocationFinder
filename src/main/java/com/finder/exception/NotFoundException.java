/**
 * 
 */
package com.finder.exception;

import java.io.Serializable;

/**
 * @author aishwaryasivaraman
 *
 */
public class NotFoundException extends Exception implements Serializable {


	private static final long serialVersionUID = -3762427961582514408L;
	private int errorCode;
	
	public NotFoundException(){
		super();
	}
	public NotFoundException(int errorCode, String msg){
		super(msg);
		this.errorCode = errorCode;
	}
	public NotFoundException(int errorCode, String msg, Exception e){
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
