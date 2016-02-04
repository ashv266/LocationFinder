package com.finder.service;


import java.io.IOException;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import com.finder.dto.Borders;
import math.geom2d.polygon.SimplePolygon2D;

public interface FinderService{
	public Map<String, Borders> getJSONFile() throws IOException, JSONException;

	public String getLocationFromFile(String lattitude, String longitude) throws IOException, JSONException;

	public String getPointState(String lattitude, String longitude) throws IOException, JSONException;

	public Map<String, SimplePolygon2D> setPolygon() throws IOException, JSONException;
}