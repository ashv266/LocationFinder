package com.finder.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.finder.dto.Borders;

public interface FinderService{
	public Map<String, Borders> getJSONFile(String fileLoc) throws IOException, JSONException;

	public String getLocationFromFile(String lattitude, String longitude) throws IOException, JSONException;
}