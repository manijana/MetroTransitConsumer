/************************************
# 	Copyright © India 2019 		  	*
# 	All rights reserved.       		*
#***********************************/

package com.metro.transit.consumer.helper;

import java.util.*;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
* <h1>Direction.java</h1>
* HelpProvider used to do some support of Mule application.
* <p>
* @author  Manikandan Janarthanan
* @version 1.0
* @since   2019-03-28 21:22:24 IST
*/
public class HelpProvider {
	
	final static Logger logger = Logger.getLogger(HelpProvider.class);
	
	public static Map<String, JSONObject> ROUTES = new HashMap<String, JSONObject>();
	
	// To get directory ID by given direction name
	public String getDirectionId(String direction) {
		int directionId = 0;
		try {
			// logger.info("Given direction : " + direction);
			for (Direction dir : Direction.values()) {
				if(dir.name().equals(direction))
					directionId = dir.getDirectionId();
			}
		} catch (Exception e) {
			logger.error("Exception when getDirectionId : ", e);
		}
		return directionId > 0 ? String.valueOf(directionId) : "";
	}
	
	// To validate whether the Routes already loaded or not
	public String checkRoutesLoaded() {
		return ROUTES.size() > 0 ? "YES" : "NO";
	}
	
	// To refresh routes information into Storage system. i.e, DB, In-Memory, File,...etc
	public void loadRoutes(String routeJson) {
		try {
			JSONArray jsonRoutes = new JSONArray(routeJson);
			for (int i = 0; i < jsonRoutes.length(); i++) {
				JSONObject route = jsonRoutes.getJSONObject(i);
				ROUTES.put(route.getString("Route"), route);
			}
		} catch (JSONException e) {
			logger.error("JSONException while load into memory : ", e);
		}
	}
	
	// To get Route ID by given route name
	public String getRouteId(String routeName) {
		String routeId = "";
		try {
			// First check with exact matches
			for (Map.Entry<String, JSONObject> entry : ROUTES.entrySet()) {
				if(entry.getValue().getString("Description").equals(routeName)) {
					routeId = entry.getValue().getString("Route");
					break;
				}
			}
			// Not exact match, try with contains
			for (Map.Entry<String, JSONObject> entry : ROUTES.entrySet()) {
				if(entry.getValue().getString("Description").contains(routeName)) {
					routeId = entry.getValue().getString("Route");
					break;
				}
			}
		} catch (JSONException e) {
			logger.error("JSONException while getRouteId : ", e);
		}
		return routeId;
	}
	
	// To get Bus ID by given bus stop name
	public String getBusId(String busJson, String busStopName) {
		String busId = "";
		try {
			JSONArray jsonBuses = new JSONArray(busJson);
			for (int i = 0; i < jsonBuses.length(); i++) {
				JSONObject bus = jsonBuses.getJSONObject(i);
				if(bus.getString("Text").equals(busStopName)) {
					busId = bus.getString("Value");
					break;
				}
			}
		} catch (JSONException e) {
			logger.error("JSONException while getBusId : ", e);
		}
		return busId;
	}
	
	// To get latest next bus departure time by given departure JSON text.
	public String getNextBusTime(String departureDetailsJson) {
		String busTime = "";
		try {
			// logger.info("departureDetailsJson : " + departureDetailsJson);
			JSONArray jsonBusDepartures = new JSONArray(departureDetailsJson);
			for (int i = 0; i < jsonBusDepartures.length(); i++) {
				JSONObject departure = jsonBusDepartures.getJSONObject(i);
				if(departure.getBoolean("Actual")) {
					busTime = departure.getString("DepartureText");
					break;
				}
			}
		} catch (JSONException e) {
			logger.error("JSONException while getNextBusTime : ", e);
		}
		return busTime;
	}
	
}
