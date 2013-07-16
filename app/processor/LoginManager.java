package processor;

import dao.UserDao;
import dao.UserDaoMongo;
import models.User;

public class LoginManager {
	
	public static String login(User user) {
		String result = null;
		
		UserDao userDao = new UserDaoMongo();
		if (userDao.findUser(user.getUsername()) != null) {
			if (userDao.findUser(user.getUsername()).getPassword().equals(user.getPassword())) 
				result = "logged in";
			else 
				result = "password invalid";
		}
		else
			result = "username invalid";
		
		return result;
	}

}
