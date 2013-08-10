package processor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static Map<String, String> getTripsbyUser(String username) {
		//TODO
		Map<String, String> trips = new HashMap<String,String>();
		return trips;		
	}
	
	
}
