/**
 * 
 */
package com.finder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author aishwaryasivaraman
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class Geometry {

	private Coordinates location;
	private Coordinates viewport;
	
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
	public Coordinates getViewport() {
		return viewport;
	}
	/**
	 * @param viewport the viewport to set
	 */
	public void setViewport(Coordinates viewport) {
		this.viewport = viewport;
	}
}
