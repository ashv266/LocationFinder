/**
 * 
 */
package com.finder.common;

import com.finder.dto.Coordinates;

/**
 * @author aishwaryasivaraman
 *
 */
public class Utilities {


	/**
	 * Routine to convert a string into coordinates
	 * 
	 * @param coords
	 * @return 
	 */
	public static Coordinates getCoordinatesFromString(String coords) {
		//assertValidlatlngString(coords); (Not empty, non null, and valid length/numbers
		String[] coordPair = coords.split(",");
		Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(coordPair[0]);
		coordinates.setLatitude(coordPair[1]);
		
		return coordinates;
	}
	
	
	/**
	 * Routine to calculate the midpoint of two places
	 * 
	 * @param geocodedOrigin
	 * @param geocodedDest
	 * @return
	 */
	public static Coordinates calculateMidpoint(Coordinates geocodedOrigin, Coordinates geocodedDest) {
		//Midpoint= (x1 + x2)/2 , (y1 +y2)/2
		Double lat1 = Double.valueOf(geocodedOrigin.getLatitude());
		//assertDoubleIsSameAsCoordinateString
		
		Double lat2 = Double.valueOf(geocodedDest.getLatitude());
		Double midLat = (lat1+lat2)/2;
		//assertMidLatIsValidDouble
		
		Double lng1 = Double.valueOf(geocodedOrigin.getLongitude());
		Double lng2 = Double.valueOf(geocodedDest.getLongitude());
		Double midLng = (lng1+lng2)/2;
		
		Coordinates midpoint = new Coordinates(midLat, midLng);
		
		return midpoint;
	}
}
