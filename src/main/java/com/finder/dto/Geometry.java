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
public class Geometry {

	@JsonProperty(value="location")
	private Coordinates location;
	
	@JsonProperty(value="location_type")
	private String locationType;
	
	@JsonProperty(value="viewport")
	private Viewport viewport;
	
	/**
	 * @return the location
	 */
	public Coordinates getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Coordinates location) {
		this.location = location;
	}
	/**
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return viewport;
	}
	/**
	 * @param viewport the viewport to set
	 */
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
}
