package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import models.Share;

public class ShareDaoMongo implements ShareDao {

	@Override
	public Share findShare(int uniqueId) {
		Share share = null;
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("share");		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("uniqueId", uniqueId);	 
		DBCursor cursor = coll.find(searchQuery);
	 
		if (cursor.hasNext()) {
			BasicDBObject doc = (BasicDBObject) cursor.next();
			share = new Share();
			share.setAuthor(doc.getString("author"));
			share.setContent(doc.getString("content"));
			share.setUniqueId(doc.getInt("uniqueId"));
		}		
		
		return share;
	}

	@Override
	public Share saveShare(Share share) {				
		int nextUniqueId = getnextUniqueId("nextShareUID");
		if (nextUniqueId !=0) {
			share.setUniqueId(nextUniqueId);
			DB db = DaoMongo.connect();
			DBCollection coll = db.getCollection("share");
			BasicDBObject doc = new BasicDBObject("author", share.getAuthor()).
	                append("content", share.getContent()).
	                append("uniqueId", share.getUniqueId());
			coll.insert(doc);
		}			
		return share;
	}


	private int getnextUniqueId(String counterName) {
		
		int nextUniqueId = 0;
		
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("nextSequence");
		
		//if doc does not exist
		//DBObject temp = new BasicDBObject("name", "nextShareUID").append("value", 0);
		//coll.insert(temp);
		
		DBObject modifier = new BasicDBObject("value", 1);
		DBObject incQuery = new BasicDBObject("$inc", modifier);
		DBObject searchQuery = new BasicDBObject("name", counterName);
		BasicDBObject newDoc = (BasicDBObject) coll.findAndModify(searchQuery, null, null, false, incQuery, true, true);
		if (newDoc != null){
			nextUniqueId = newDoc.getInt("value");
		}
				
		return nextUniqueId;
	}

}
