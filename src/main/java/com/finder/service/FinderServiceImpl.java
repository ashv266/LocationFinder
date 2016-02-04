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
	public String getLocationFromFile(String lattitude, String longitude) throws IOException, JSONException{
		Map<String, Borders> locMap = new HashMap<String, Borders>();
		locMap = getJSONFile();
		Border border = new Border();
		border.setLattitude(Double.parseDouble(lattitude));
		border.setLongitude(Double.parseDouble(longitude));
		String state = null;
		
		
		for(String loc : locMap.keySet()){
			List<Border> borders = locMap.get(loc).getBorders();
			for(Border borderQuery : borders){
				if(borderQuery.getLattitude() == border.getLattitude()
						&& borderQuery.getLongitude() == (border.getLongitude())){
					state = loc;
					break;
				}
			}
		}
		
		return state+"\n";
	}
	
	
	@Override
	public Map<String, SimplePolygon2D> setPolygon() throws IOException, JSONException{
		Map<String, Borders> borders = getJSONFile();
		Map<String, SimplePolygon2D> statePolygonMap = new HashMap<String, SimplePolygon2D>();
		
		for(String state : borders.keySet()){
			try{
				List<Border> indBorder = borders.get(state).getBorders();
				statePolygonMap.put(state, getStatePolygon(indBorder));
			}catch(Exception e){
				logger.error("Error creating a polygon for state:{}",state, e);
			}
		}
		return statePolygonMap;
	}
	
	private SimplePolygon2D getStatePolygon(List<Border> indBorder){
		SimplePolygon2D statePolygon = new SimplePolygon2D();
		ListIterator<Border> listIterator = indBorder.listIterator(indBorder.size());
		while(listIterator.hasPrevious()){
				statePolygon.addVertex(getPointFromBorder(listIterator.previous()));
			}
		return statePolygon;
	}
	
	private Point2D getPointFromBorder(Border borderSet){
		//TODO: Change Border attributes to return Double
		Double lat = borderSet.getLattitude();
		Double lon = borderSet.getLongitude();
		Point2D point = new Point2D(lat, lon);
		return point;
	}
	
	@Override
	public String getPointState(String lattitude, String longitude) throws IOException, JSONException{
		Map<String, SimplePolygon2D> statePolygonMap = setPolygon();
		Point2D queryPoints = new Point2D(Double.parseDouble(lattitude), Double.parseDouble(longitude));
		
		String foundState = new String();
		for(String state : statePolygonMap.keySet()){
			try{
				SimplePolygon2D polygon = statePolygonMap.get(state);
				if(polygon.contains(queryPoints)){
					foundState= state;
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