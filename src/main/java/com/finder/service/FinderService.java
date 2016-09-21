package com.finder.service;


import java.io.IOException;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.finder.dto.Coordinates;
import com.finder.dto.GeocodedResult;
import com.finder.dto.SearchResults;
import com.finder.exception.BadRequestException;
import com.finder.exception.JSONParseException;
import com.finder.exception.NotFoundException;

public interface FinderService{
	/**
	 * @param radius 
	 * @param coordinates
	 * @param name 
	 * @param types 
	 * @return 
	 * @throws BadRequestException 
	 * @throws NotFoundException 
	 * @throws JSONParseException 
	 * 
	 */
	public SearchResults getLocation(Coordinates coords, Integer radius, List<String> types, String name) throws BadRequestException, NotFoundException, JSONParseException;

	/**
	 * @param coords
	 * @param endCoords 
	 * @param radius
	 * @param types
	 * @param name
	 * @return
	 * @throws BadRequestException 
	 * @throws JSONParseException 
	 * @throws NotFoundException 
	 */
	public SearchResults getPlacesForMidpoint(String coords, String endCoords, Integer radius, List<String> types, String name) throws BadRequestException, JSONParseException, NotFoundException;
}