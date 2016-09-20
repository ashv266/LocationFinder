/**
 * 
 */
package com.finder.exception;

import java.io.Serializable;

import org.codehaus.jettison.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author aishwaryasivaraman
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JSONParseException extends JSONException implements Serializable {


	private static final long serialVersionUID = 3277415548375460418L;
	private int errorCode;
	
	public JSONParseException(String msg){
		super(msg);
	}
	
	public JSONParseException( String msg,int errorCode){
		super(msg);
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
