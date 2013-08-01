package processor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

import models.User;
import dao.UserDao;
import dao.UserDaoMongo;

public class MyAccountManager {

	public static User getUser(String username) {
		UserDao userDao = new UserDaoMongo();
		return userDao.findUser(username);
	}
	
	public static boolean saveProfilePic(String username, File file) {
		boolean result = false;
		Cloudinary cloudinary = new Cloudinary("cloudinary://851169366924174:FMRfm8KdbatTRSBkxA-tTFCMSVw@db4meqdaj");
		try {
			Map response = cloudinary.uploader().upload(file, Cloudinary.emptyMap());			
			User user = getUser(username);
			System.out.println("Cloudinary URL: " + (String)response.get("url"));
			user.setProfilePictureUrl((String)response.get("url"));
			UserDao userDao = new UserDaoMongo();
			result = userDao.updateUser(user);					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
