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
	
	@SuppressWarnings("unchecked")
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
        		locMap.put(borders.getState(), getStatePolygon(borders.getBorders()));
        	}
        }catch(Exception e){
        	logger.error("FinderServiceImpl.getJSONFile(String fileLocation): Error getting file data.",e);
        }
	}
	
	private Borders getBordersFromLineInFile(String line) throws JSONException{
		JSONObject stateStats = new JSONObject(line);
		String key = stateStats.getString("state");
		return new Borders(stateStats.get("border").toString(), key);
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
	public List<String> getPointState(String lattitude, String longitude) throws IOException, JSONException{
		Point2D queryPoints = new Point2D(Double.parseDouble(lattitude), Double.parseDouble(longitude));
		String foundState = new String();
		List<String> statesFound = new ArrayList<String>();
		
		for(String state : locMap.keySet()){
			try{
				if(locMap.get(state).contains(queryPoints)){
					foundState = state;
					statesFound.add(foundState);
				}
			}catch(Exception e){
				logger.error("Error checking for state in FinderServiceImpl.getPointState(lattitude={}, longitude={})", lattitude, longitude, e);
			}
		}
		
		return statesFound; 
	}

	public Resource getRefFile() {
		return refFile;
	}

	public void setRefFile(Resource refFile) {
		this.refFile = refFile;
	}

}