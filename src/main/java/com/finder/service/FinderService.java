package com.finder.service;


import java.io.IOException;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finder.dto.SearchResults;

public interface FinderService{
	/**
	 * @param radius 
	 * @param latitude 
	 * @param longitude 
	 * @param name 
	 * @param types 
	 * @return 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws JSONException 
	 * 
	 */
	public SearchResults getLocation(String longitude, String latitude, Integer radius, List<String> types, String name) throws JsonParseException, JsonMappingException, IOException, JSONException;
}