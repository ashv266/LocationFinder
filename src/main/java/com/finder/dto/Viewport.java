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
public class Viewport {

	@JsonProperty(value="northeast")
	private Coordinates northeast;
	@JsonProperty(value="southwest")
	private Coordinates southwest;
	/**
	 * @return the northeast
	 */
	public Coordinates getNortheast() {
		return northeast;
	}
	/**
	 * @param northeast the northeast to set
	 */
	public void setNortheast(Coordinates northeast) {
		this.northeast = northeast;
	}
	/**
	 * @return the southwest
	 */
	public Coordinates getSouthwest() {
		return southwest;
	}
	/**
	 * @param southwest the southwest to set
	 */
	public void setSouthwest(Coordinates southwest) {
		this.southwest = southwest;
	}
}
