package com.finder.service;


import java.io.IOException;
import java.util.List;
import org.codehaus.jettison.json.JSONException;

public interface FinderService{
	public List<String> getPointState(String lattitude, String longitude) throws IOException, JSONException;
}