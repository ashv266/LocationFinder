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
public class Endpoints {

	private Coordinates origin;
	private Coordinates destination;
	/**
	 * @return the origin
	 */
	public Coordinates getOrigin() {
		return origin;
	}
	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(Coordinates origin) {
		this.origin = origin;
	}
	/**
	 * @return the destination
	 */
	public Coordinates getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Coordinates destination) {
		this.destination = destination;
	}
}
