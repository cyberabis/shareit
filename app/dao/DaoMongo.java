package dao;

import java.net.UnknownHostException;

import  com.mongodb.*;

public class DaoMongo {
	
	public static DB connect() {
		
		DB db = null;
		try {
			Mongo mongo = new Mongo( "localhost" , 27017 );
			//Mongo mongo = new Mongo("ds037478.mongolab.com",37478);
			db = mongo.getDB("shareit");
			//db = mongo.getDB("trc");
			boolean auth = db.authenticate("trcuser", "trcpass".toCharArray());
		} catch (UnknownHostException e) {
			e.printStackTrace();
	    } catch (MongoException e) {
		e.printStackTrace();
	    }
		
		return db;
	}
	
}
