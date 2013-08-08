package processor;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import models.Trip;

public class TripManager {
	
	private static final List<String> places = Arrays.asList("Chennai", "Bangalore", "Hyderabad", "Mumbai", "Delhi");
	
	public static boolean saveTrip(Trip trip) {
		//TODO
		return false;
	}
	
	public static List<String> getPlaces() {
		return places;
	}
	
	public static String getPlacesAsJson() {
		String jsonPlaces = null;
		jsonPlaces = new Gson().toJson(getPlaces());
		return jsonPlaces;
	}
	
	
}
