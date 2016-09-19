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
public class Hours {

	@JsonProperty("open_now")
	private Boolean openNow;
	@JsonProperty("weekday_text")
	private List<String> weekdayText;

	/**
	 * @return the openNow
	 */
	public Boolean getOpenNow() {
		return openNow;
	}

	/**
	 * @param openNow the openNow to set
	 */
	public void setOpenNow(Boolean openNow) {
		this.openNow = openNow;
	}

	/**
	 * @return the weekdayText
	 */
	public List<String> getWeekdayText() {
		return weekdayText;
	}

	/**
	 * @param weekdayText the weekdayText to set
	 */
	public void setWeekdayText(List<String> weekdayText) {
		this.weekdayText = weekdayText;
	}
}
