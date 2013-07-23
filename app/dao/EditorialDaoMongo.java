package dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import models.Editorial;

public class EditorialDaoMongo implements EditorialDao {

	@Override
	public Editorial findEditorial(int articleId) {
		
		Editorial editorial = null;
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("editorial");		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("articleId", articleId);	 
		DBCursor cursor = coll.find(searchQuery);
	 
		if (cursor.hasNext()) {
			BasicDBObject doc = (BasicDBObject) cursor.next();
			editorial = new Editorial();
			editorial.setArticle(doc.getString("article"));
			editorial.setArticleId(doc.getInt("articleId"));
			editorial.setAuthor(doc.getString("author"));
			editorial.setCreateDate(doc.getDate("createDate"));
			editorial.setTitle(doc.getString("title"));
			editorial.setPictureUrl(doc.getString("pictureUrl"));
			editorial.setVideoUrl(doc.getString("videoUrl"));
		}	
		
		return editorial;
	}

	@Override
	public List<Editorial> findAllEditorials() {
		
		List<Editorial> editorials = new ArrayList<Editorial>();
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("editorial");		
		BasicDBObject searchQuery = new BasicDBObject();
		DBCursor cursor = coll.find(searchQuery);
	 
		if (cursor.hasNext()) {
			BasicDBObject doc = (BasicDBObject) cursor.next();
			Editorial editorial = new Editorial();
			editorial.setArticle(doc.getString("article"));
			editorial.setArticleId(doc.getInt("articleId"));
			editorial.setAuthor(doc.getString("author"));
			editorial.setCreateDate(doc.getDate("createDate"));
			editorial.setTitle(doc.getString("title"));
			editorial.setPictureUrl(doc.getString("pictureUrl"));
			editorial.setVideoUrl(doc.getString("videoUrl"));
			editorials.add(editorial);
		}	
		
		return editorials;
	}

}
