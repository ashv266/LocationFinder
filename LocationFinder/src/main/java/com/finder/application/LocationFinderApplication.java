package com.finder.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class LocationFinderApplication extends ResourceConfig{

	public LocationFinderApplication(){
		packages("com.finder.application");
		register(RequestContextFilter.class);
	}
}
