package com.finder.test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import com.finder.service.FinderServiceImpl;

@Test(enabled=true)
@ComponentScan(basePackages = { "com.finder" })
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class FinderServiceTest extends AbstractTestNGSpringContextTests{
  
	@Autowired
	FinderServiceImpl finderService;
	
	private Logger logger = LoggerFactory.getLogger(FinderServiceTest.class);
	
	@Test(enabled=true)
	public void testInputCoordsIfInvalid() throws IOException, JSONException{
		List<String> state = finderService.getStateForPoint("-68.we230807", "47.352148");
		logger.info("STATE : {}", state);
	}
	
	@Test(enabled=true)
	public void testInputCoordsIfNotFound() throws IOException, JSONException{
		List<String> state = finderService.getStateForPoint("-135.900", "47.352148");
		logger.info("STATE : {}", state);
	}
	
	@Test(enabled=true)
	public void testGetPointStateFunctionality() throws IOException, JSONException{
		List<String> state1 = finderService.getStateForPoint( "-79.9764","40.4397");
		List<String> state2 = finderService.getStateForPoint( "-105.998886","31.39394");
		logger.info("STATE FROM POINT SET 1: longitude={}, latitude={} => {}", "-79.9764","40.4397",state1);
		logger.info("STATE FROM POINT SET 2: longitude={}, latitude={} => {}", "-105.998886","31.39394",state2);
		
		List<String> state1Truth = new ArrayList<String>();
		state1Truth.add("Pennsylvania");
		List<String> state2Truth = new ArrayList<String>();
		state2Truth.add("Texas");
		
		assert(state1.equals(state1Truth));
		assert(state2.equals(state2Truth));
	}
}
