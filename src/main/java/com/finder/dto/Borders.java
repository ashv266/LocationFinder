package com.finder.dto;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.finder.service.FinderServiceImpl;

import math.geom2d.Point2D;

public class Borders {
	private List<Point2D> borders = new ArrayList<Point2D>();
	private String state = new String();

	public Borders() {}
	
	public Borders(String coords, String state){
		List<Point2D> borderList = new ArrayList<Point2D>();
		coords = stripList(coords);
		String[] bList = coords.split("],");
		for(String o : bList){
			o = o.replaceAll("[^\\d.,-]","");
			String[] oCoords = o.split(",");
			Point2D borderPoint = new Point2D(Double.parseDouble(oCoords[0]), Double.parseDouble(oCoords[1]));
			borderList.add(borderPoint);
		}
		this.borders = borderList;
		this.state = state;
	}
	
	public String stripList(String coords){
		coords = coords.substring(1, coords.length());
		coords = coords.substring(0, coords.length()-1);
		return coords;
	}
	
	public List<Point2D> getBorders() {
		return borders;
	}

	public void setBorders(List<Point2D> borders) {
		this.borders = borders;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
