package com.finder.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.dto.Border;
import com.finder.dto.Borders;

@Component
//@PropertySource(value="classpath:src/main/resources/states.json")
public class FinderServiceImpl implements FinderService {

	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);

	String js="{\"state\": \"Washington\", \"border\": [[-122.402015, 48.225216], [-117.032049, 48.999931], [-116.919132, 45.995175], [-124.079107, 46.267259], [-124.717175, 48.377557], [-122.92315, 47.047963], [-122.402015, 48.225216]]}"
			+ "\n{\"state\": \"Montana\", \"border\": [[-111.475425, 44.702162], [-114.560924, 45.54874], [-116.063531, 48.99995], [-104.062991, 49.000026], [-104.043072, 44.997805], [-111.475425, 44.702162]]}"
			+"\n{\"state\": \"Maine\", \"border\": [[-69.777276, 44.074148], [-70.818668, 43.121871], [-71.087509, 45.301469], [-68.230807, 47.352148], [-66.969271, 44.828655], [-69.777276, 44.074148]]}"; 
	
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Borders> getJSONFile(String fileLoc) throws IOException, JSONException {
		logger.info("Getting locations from file...({})", fileLoc);
		Borders borders = new Borders();
		Map<String, Borders> locMap = new HashMap<String, Borders>();
		
        try{
        	String[] locs;
        	locs = js.split("\n");
        	List<String> jsToList= Arrays.asList(locs);
        	for(String line : jsToList){
        		if (line == null)
        			break;
        		JSONObject stateStats = new JSONObject(line);
        		String key = stateStats.getString("state");
        		borders = new Borders(stateStats.get("border").toString());
        		locMap.put(key, borders);
        	}
        }catch(Exception e){
        	logger.error("FinderServiceImpl.getJSONFile(String fileLocation): Error getting file data.",e);
        }

		return locMap;
	}

	@Override
	public String getLocationFromFile(String lattitude, String longitude) throws IOException, JSONException{
		Map<String, Borders> locMap = new HashMap<String, Borders>();
		locMap = getJSONFile("states.json");
		Border border = new Border();
		border.setLattitude(lattitude);
		border.setLongitude(longitude);
		String state = null;
		
		
		for(String loc : locMap.keySet()){
			List<Border> borders = locMap.get(loc).getBorders();
			for(Border borderQuery : borders){
				if(borderQuery.getLattitude().equals(border.getLattitude())
						&& borderQuery.getLongitude().equals(border.getLongitude())){
					state = loc;
					break;
				}
			}
		}
		
		return state;
	}
}