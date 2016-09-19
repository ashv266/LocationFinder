/**
 * 
 */
package com.finder.dto;

import java.util.List;

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
public class Photo {

	@JsonProperty("height")
	private Integer height;
	@JsonProperty("width")
	private Integer width;
	@JsonProperty("html_attributions")
	private List<String> htmlAttributions;
	@JsonProperty("photo_reference")
	private String photoReference;
	
	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * @return the html_attributions
	 */
	public List<String> getHtml_attributions() {
		return htmlAttributions;
	}
	/**
	 * @param html_attributions the html_attributions to set
	 */
	public void setHtml_attributions(List<String> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}
	/**
	 * @return the photoReference
	 */
	public String getPhotoReference() {
		return photoReference;
	}
	/**
	 * @param photoReference the photoReference to set
	 */
	public void setPhotoReference(String photoReference) {
		this.photoReference = photoReference;
	}
	
}
