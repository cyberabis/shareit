package processor;

import dao.UserDao;
import dao.UserDaoMongo;
import models.User;

public class SignupManager {
	
	public static String signup(User user) {
		String result = null;
		
		UserDao userDao = new UserDaoMongo();
		if (userDao.findUser(user.getUsername()) != null) {
			result = "user exists";
		}
		else {
			userDao.saveUser(user);
			result = "user saved";
		}  
		
		return result;
	}

}
