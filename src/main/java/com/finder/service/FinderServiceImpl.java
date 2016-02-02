package com.finder.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.finder.dto.Border;
import com.finder.dto.Borders;


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
					break;
				}
			}
		}
		
		return state;
	}

	public Resource getRefFile() {
		return refFile;
	}

	public void setRefFile(Resource refFile) {
		this.refFile = refFile;
	}
}