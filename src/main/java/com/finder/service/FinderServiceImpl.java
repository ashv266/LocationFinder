package com.finder.service;


import java.io.IOException;
import java.io.InputStream;
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
        		JSONObject stateStats = new JSONObject(line);
        		String key = stateStats.getString("state");
        		borders = new Borders(stateStats.get("border").toString());
        		locMap.put(key, getStatePolygon(borders.getBorders()));
        	}
        }catch(Exception e){
        	logger.error("FinderServiceImpl.getJSONFile(String fileLocation): Error getting file data.",e);
        }
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
		Point2D queryPoints = new Point2D(Double.parseDouble(lattitude), Double.parseDouble(longitude));
		
		String foundState = new String();
		for(String state : locMap.keySet()){
			try{
				if(locMap.get(state).contains(queryPoints)){
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