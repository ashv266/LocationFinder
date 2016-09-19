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
public class SearchResults {

	@JsonProperty("html_attributions")
	private List<String> htmlAttributions;
	@JsonProperty("next_page_token")
	private String nextPageToken;
	@JsonProperty("results")
	private List<Place> results;
	/**
	 * @return the htmlAttributions
	 */
	public List<String> getHtmlAttributions() {
		return htmlAttributions;
	}
	/**
	 * @param htmlAttributions the htmlAttributions to set
	 */
	public void setHtmlAttributions(List<String> htmlAttributions) {
		this.htmlAttributions = htmlAttributions;
	}
	/**
	 * @return the nextPageToken
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}
	/**
	 * @param nextPageToken the nextPageToken to set
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
	/**
	 * @return the results
	 */
	public List<Place> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List<Place> results) {
		this.results = results;
	}
}
