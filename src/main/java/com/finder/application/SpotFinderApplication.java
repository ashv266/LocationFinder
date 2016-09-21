package com.finder.application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class SpotFinderApplication extends ResourceConfig{

	public SpotFinderApplication(){
		packages("com.finder");
		register(RequestContextFilter.class);
	}
}
