package processor;

import models.User;
import dao.UserDao;
import dao.UserDaoMongo;

public class MyAccountManager {

	public static User getUser(String username) {
		UserDao userDao = new UserDaoMongo();
		return userDao.findUser(username);
	}
	
}
