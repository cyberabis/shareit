package dao;

import java.net.UnknownHostException;

import  com.mongodb.*;

public class DaoMongo {
	
	public static DB connect() {
		
		DB db = null;
		try {
			Mongo mongo = new Mongo( "localhost" , 27017 );
			db = mongo.getDB("shareit");
		} catch (UnknownHostException e) {
			e.printStackTrace();
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
		
		return db;
	}
	
}
