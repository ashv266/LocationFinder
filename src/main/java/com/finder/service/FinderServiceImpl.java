package com.finder.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.finder.dto.Border;
import com.finder.dto.Borders;

import math.geom2d.Point2D;
import math.geom2d.polygon.SimplePolygon2D;


@Component
@Configuration
public class FinderServiceImpl implements FinderService {
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);

	@Value("classpath:/states.json")
	private Resource refFile;
	
	@SuppressWarnings("unchecked")
	@Override
	@Bean
	public Map<String, Borders> getJSONFile() throws IOException, JSONException {
		logger.info("Getting locations from file...({})");
		Borders borders = new Borders();
		Map<String, Borders> locMap = new HashMap<String, Borders>();
		
        try{
        	String[] locs = null;
        	try(InputStream is = refFile.getInputStream()) {
                	locs = IOUtils.toString(is).split("\n");
            }
        	
        	for(String line : locs){
        		if (line == null)
        			break;
        		JSONObject stateStats = new JSONObject(line);
        		String key = stateStats.getString("state");
        		//TODO: Move lat long coords to Polygon and avoid Borders classes
        		borders = new Borders(stateStats.get("border").toString());
        		locMap.put(key, borders);
        	}
        }catch(Exception e){
        	logger.error("FinderServiceImpl.getJSONFile(String fileLocation): Error getting file data.",e);
        }

		return locMap;
	}

	@Override
	public Map<String, SimplePolygon2D> setPolygon() throws IOException, JSONException{
		Map<String, Borders> borders = getJSONFile();
		Map<String, SimplePolygon2D> statePolygonMap = new HashMap<String, SimplePolygon2D>();
		
		for(String state : borders.keySet()){
			try{
				List<Point2D> indBorder = borders.get(state).getBorders();
				statePolygonMap.put(state, getStatePolygon(indBorder));
			}catch(Exception e){
				logger.error("Error creating a polygon for state:{}",state, e);
			}
		}
		return statePolygonMap;
	}
	
	private SimplePolygon2D getStatePolygon(List<Point2D> indBorder){
		SimplePolygon2D statePolygon = new SimplePolygon2D();
		ListIterator<Point2D> listIterator = indBorder.listIterator(indBorder.size());
		while(listIterator.hasPrevious()){
				statePolygon.addVertex(listIterator.previous());
			}
		return statePolygon;
	}
	
	@Override
	public String getPointState(String lattitude, String longitude) throws IOException, JSONException{
		Map<String, SimplePolygon2D> statePolygonMap = setPolygon();
		Point2D queryPoints = new Point2D(Double.parseDouble(lattitude), Double.parseDouble(longitude));
		
		String foundState = new String();
		for(String state : statePolygonMap.keySet()){
			try{
				if(statePolygonMap.get(state).contains(queryPoints)){
					foundState = state;
					break;
				}
			}catch(Exception e){
				logger.error("Error checking for state in FinderServiceImpl.getPointState(lattitude={}, longitude={})", lattitude, longitude, e);
			}
		}
		
		return foundState; 
	}

	public Resource getRefFile() {
		return refFile;
	}

	public void setRefFile(Resource refFile) {
		this.refFile = refFile;
	}

}