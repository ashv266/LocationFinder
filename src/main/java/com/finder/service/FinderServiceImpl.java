/**
 * @author Aishwarya Sivaraman
*/

package com.finder.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.finder.dto.Borders;

import math.geom2d.Point2D;
import math.geom2d.polygon.SimplePolygon2D;


@Component
@Configuration
public class FinderServiceImpl implements FinderService {
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);

	@Value("classpath:/states.json")
	private Resource refFile;
	
	private static Map<String, SimplePolygon2D> locMap = new HashMap<String, SimplePolygon2D>();
	
	/*
	 * @PostConstruct executes this method on startup. It sets the locMap above for use by other methods.
	 * In this method, the states.json file is read form src/main/resources and parsed line by line, into a map.
	 * The map is keyed by states, with values of Polygonal points (vertices).
	 */
	@PostConstruct
	private void getFileContentsAsMap() throws IOException, JSONException {
		logger.info("Getting locations from file...({})");
		Borders borders = new Borders();
		
        try{
        	String[] locs = null;
        	try(InputStream is = refFile.getInputStream()) {
                	locs = IOUtils.toString(is).split("\n");
            }
        	
        	for(String line : locs){
        		if (line == null)
        			break;
        		borders = getBordersFromLineInFile(line);
        		locMap.put(borders.getState(), getStateShape(borders.getBorders()));
        	}
        }catch(Exception e){
        	logger.error("FinderServiceImpl.getJSONFile(String fileLocation): Error getting file data.",e);
        }
	}
	
	/*
	 * Returns the state for query parameters from URL. If the latitudes and longitudes passed in, lie within the StateShape defined
	 * in getStateShape(), the state from locMap is returned.
	 * @see com.finder.service.FinderService#getStateForPoint(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getStateForPoint(String longitude, String latitude) throws IOException, JSONException{
		List<String> statesFound = new ArrayList<String>();
		
		Point2D queryPoints = new Point2D();
		try{
			queryPoints = returnPointFromStringInput(longitude, latitude);
			statesFound = getStateFromMap(queryPoints);
			if(statesFound.isEmpty() || statesFound == null)
				statesFound.add("Coordinates not found. Enter new coordinates.");
		}catch(Exception e){
			logger.error("Invalid coordinates received: longitude={}, latitude={}", longitude, latitude);
			statesFound.add("Check the coordinates entered. Are they valid?");
		}
		
		return statesFound; 
	}
	
	/*
	 * Checks whether the points received lie within the area of any state in the mapped file and returns the state if found.
	 */
	public List<String> getStateFromMap(Point2D queryPoints){
		List<String> statesFound = new ArrayList<String>();
		
		for(String state : locMap.keySet()){
			try{
				if(locMap.get(state).contains(queryPoints)){
					statesFound.add(state);
//					Uncomment this break statement if it is certain that state borders do not overlap. Will improve performance
//					as iterations will cease after state is found.
//					break;
				}
			}catch(Exception e){
				logger.error("Error checking for state in FinderServiceImpl.getPointState(latitude={}, longitude={})", queryPoints.x(), queryPoints.y(), e);
				statesFound.add("ERROR");
			}
		}
		
		return statesFound;
	}

	/*
	 * Converts the input coordinates into Double values
	 */
	public Point2D returnPointFromStringInput(String latitude, String longitude){
		Point2D inPoint = new Point2D();
		inPoint = new Point2D(Double.parseDouble(latitude), Double.parseDouble(longitude));
		return inPoint;
	}
	
	/*
	 * Returns the borders of each state from line read of json file 
	 */
	private Borders getBordersFromLineInFile(String line) throws JSONException{
		JSONObject stateStats = new JSONObject(line);
		String key = stateStats.getString("state");
		return new Borders(stateStats.get("border").toString(), key);
	}
	
	/*
	 * Sets the shape of the state by taking into account the vertices associated with each state in file
	 */
	private SimplePolygon2D getStateShape(List<Point2D> indBorder){
		SimplePolygon2D stateShape = new SimplePolygon2D();
		ListIterator<Point2D> listIterator = indBorder.listIterator(indBorder.size());
		while(listIterator.hasPrevious()){
			stateShape.addVertex(listIterator.previous());
		}
		return stateShape;
	}
	
	
	public Resource getRefFile() {
		return refFile;
	}

	public void setRefFile(Resource refFile) {
		this.refFile = refFile;
	}

}