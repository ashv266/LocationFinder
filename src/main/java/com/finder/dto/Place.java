/**
 * 
 */
package com.finder.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author aishwaryasivaraman
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)
public class Place {

	@JsonProperty("geometry")
	private Geometry geometry;
	@JsonProperty("photos")
	private List<Photo> photos;
	@JsonProperty("types")
	private List<String> types;
	@JsonProperty("icon")
	private String icon;
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("place_id")
	private String placeId;
	@JsonProperty("rating")
	private Double rating;
	@JsonProperty("reference")
	private String reference;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("vicinity")
	private String vicinity;
	@JsonProperty("opening_hours")
	private Hours openingHours;
	@JsonProperty("status")
	private String status;
	
	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	/**
	 * @return the photos
	 */
	public List<Photo> getPhotos() {
		return photos;
	}
	/**
	 * @param photos the photos to set
	 */
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	/**
	 * @return the types
	 */
	public List<String> getTypes() {
		return types;
	}
	/**
	 * @param types the types to set
	 */
	public void setTypes(List<String> types) {
		this.types = types;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the placeId
	 */
	public String getPlaceId() {
		return placeId;
	}
	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}
	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the vicinity
	 */
	public String getVicinity() {
		return vicinity;
	}
	/**
	 * @param vicinity the vicinity to set
	 */
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	/**
	 * @return the openingHours
	 */
	public Hours getOpeningHours() {
		return openingHours;
	}
	/**
	 * @param openingHours the openingHours to set
	 */
	public void setOpeningHours(Hours openingHours) {
		this.openingHours = openingHours;
	}
}
