package com.finder.test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.finder.dto.Borders;
import com.finder.service.FinderServiceImpl;

@Test(enabled=true)
@ComponentScan(basePackages = { "com.finder" })
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class FinderServiceTest extends AbstractTestNGSpringContextTests{
  
	@Autowired
	FinderServiceImpl finderService;
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceTest.class);
	
	@Test(enabled=true)
	public void testGetJSONFile() throws IOException, JSONException {
		//Map<String, Borders> locations= finderService.getFileContentsAsMap();
		//logger.info("This is the locations array: {}, size: {}", locations.get("Washington"), locations.get("Washington").getBorders().size());
	}
	
	@Test(enabled=true)
	public void testGetLocationFromFile() throws IOException, JSONException{
		String state = finderService.getPointState("-68.230807", "47.352148");
		logger.info("STATE FOUND: {}", state);
	}
	
	@Test(enabled=true)
	public void testGetPointState() throws IOException, JSONException{
		String state1 = finderService.getPointState( "-79.9764","40.4397");
		String state2 = finderService.getPointState( "-105.998886","31.39394");
		logger.info("STATE FOUND FROM POLYGON: {}", state1);
		logger.info("STATE FOUND FROM POLYGON: {}", state2);
		
		assert(state1.equals("Pennsylvania"));
		assert(state2.equals("Texas"));
	}
}
