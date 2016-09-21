/**
 * 
 */
package com.finder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aishwaryasivaraman
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class Coordinates {

	@JsonProperty("lat")
	private String latitude;
	@JsonProperty("lng")
	private String longitude;
	
	public Coordinates(){}
	
	public Coordinates(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//Assert that double values are valid for string conversion
	public Coordinates(Double latitude, Double longitude){
		this.latitude = String.valueOf(latitude);
		this.longitude = String.valueOf(longitude);
	}
	
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
