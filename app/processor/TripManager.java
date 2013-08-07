package processor;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import models.Trip;

public class TripManager {
	
	public static boolean saveTrip(Trip trip) {
		//TODO
		return false;
	}
	
	public static List<String> getCities() {
		List<String> cities = new ArrayList<String>();
		//TODO
		cities.add("Bangalore");
		cities.add("Delhi");
		cities.add("Mumbai");
		cities.add("Chennai");
		return cities;
	}
	
	public static String getCitiesAsJson() {
		String jsonCities = null;
		
		jsonCities = new Gson().toJson(getCities());
		System.out.println("JsonCities: " + jsonCities);
		return jsonCities;
	}
	
	
}
