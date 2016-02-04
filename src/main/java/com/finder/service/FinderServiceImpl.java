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
		border.setLattitude(lattitude);
		border.setLongitude(longitude);
		String state = null;
		
		
		for(String loc : locMap.keySet()){
			List<Border> borders = locMap.get(loc).getBorders();
			for(Border borderQuery : borders){
				if(borderQuery.getLattitude().equals(border.getLattitude())
						&& borderQuery.getLongitude().equals(border.getLongitude())){
					state = loc;
					logger.debug("ARE THERE MORE STATES?!: {}", state);
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
				SimplePolygon2D statePolygon = new SimplePolygon2D();
				List<Border> indBorder = borders.get(state).getBorders();
				ListIterator<Border> listIterator = indBorder.listIterator(indBorder.size());
//					for(Border coords:indBorder){
				while(listIterator.hasPrevious()){
						//TODO: Change getLattitude to return Double
						Border index = listIterator.previous();
						Double lat = Double.parseDouble(index.getLattitude());
						Double lon = Double.parseDouble(index.getLongitude());
						Point2D point = new Point2D(lat, lon);
						statePolygon.addVertex(point);
					}
					//TODO: break down into functions here
					statePolygonMap.put(state, statePolygon);
					logger.debug("These are the coordinates for each state:{}, vertices:{}", state, statePolygon.vertices());
					//TODO: Add logging and error catching
			}catch(Exception e){
				logger.error("Error creating a polygon for state:{}",state, e);
			}
		}
		return statePolygonMap;
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