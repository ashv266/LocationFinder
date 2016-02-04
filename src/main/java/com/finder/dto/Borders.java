package com.finder.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finder.service.FinderServiceImpl;

public class Borders {
	private Logger logger = LoggerFactory.getLogger(FinderServiceImpl.class);
	private List<Border> borders = new ArrayList<Border>();

	public Borders() {}
	
	public Borders(String coords){
		List<Border> borderList = new ArrayList<Border>();
		coords = stripList(coords);
		String[] bList = coords.split("],");
		for(String o : bList){
			Border b = new Border();
			o = o.replaceAll("[^\\d.,-]","");
			String[] oCoords = o.split(",");
			b.setLattitude(Double.parseDouble(oCoords[0]));
			b.setLongitude(Double.parseDouble(oCoords[1]));
			borderList.add(b);
		}
		borders = borderList;
	}
	
	public String stripList(String coords){
		coords = coords.substring(1, coords.length());
		coords = coords.substring(0, coords.length()-1);
		return coords;
	}
	
	public List<Border> getBorders() {
		return borders;
	}

	public void setBorders(List<Border> borders) {
		this.borders = borders;
	}
	
}
