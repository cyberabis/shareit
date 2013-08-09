package dao;

import com.mongodb.*;

import models.User;

public class UserDaoMongo implements UserDao {

	@Override
	public User findUser(String username) {		
		User user = null;
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("user");		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", username);	 
		DBCursor cursor = coll.find(searchQuery);
	 
		if (cursor.hasNext()) {
			BasicDBObject doc = (BasicDBObject) cursor.next();
			user = new User();
			user.setUsername(doc.getString("username"));
			user.setPassword(doc.getString("password"));
			user.setFirstName(doc.getString("firstName"));
			user.setLastName(doc.getString("lastName"));
			user.setEmail(doc.getString("email"));
			user.setCity(doc.getString("city"));
			user.setCountry(doc.getString("country"));
			user.setBirthDate(doc.getDate("birthDate"));
			user.setProfilePictureUrl(doc.getString("profilePictureUrl"));
			user.setMiles((doc.get("miles") == null) ? 0 : doc.getInt("miles"));
			user.setGender(doc.getString("gender"));
			user.setUserType(doc.getString("userType"));
		}		
		
		return user;
	}

	@Override
	public boolean saveUser(User user) {		
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("user");
		BasicDBObject doc = createUserDocument(user);
		coll.insert(doc);
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		DB db = DaoMongo.connect();
		DBCollection coll = db.getCollection("user");
		BasicDBObject doc = createUserDocument(user);
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", user.getUsername());	 
		coll.update(searchQuery, doc);
		return true;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private BasicDBObject createUserDocument(User user) {
		return new BasicDBObject("username", user.getUsername()).
                append("password", user.getPassword()).
                append("firstName", user.getFirstName()).
                append("lastName", user.getLastName()).
                append("email", user.getEmail()).
                append("city", user.getCity()).
                append("country", user.getCountry()).
                append("birthDate", user.getBirthDate()).
				append("profilePictureUrl", user.getProfilePictureUrl()).
				append("miles", user.getMiles()).
				append("gender",user.getGender()).
				append("userType", user.getUserType());
	}

}
