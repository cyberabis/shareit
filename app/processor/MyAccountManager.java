package processor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;

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
			User user = getUser(username);
			String oldProfilePicUrl = user.getProfilePictureUrl();
			if ((oldProfilePicUrl!=null)&&(!oldProfilePicUrl.equals(""))) {
				String[] parts = oldProfilePicUrl.split("/");
				String publicId = parts[parts.length - 1].split("\\.")[0];
				cloudinary.uploader().destroy(publicId, Cloudinary.emptyMap());
			}			
			//Upload after resizing
			Transformation transformation = new Transformation();
			transformation.gravity("faces:center").crop("thumb").height(180).width(100).radius(5);
			Map response = cloudinary.uploader().upload(file, Cloudinary.asMap("transformation", transformation));			
			user.setProfilePictureUrl((String)response.get("url"));
			UserDao userDao = new UserDaoMongo();
			result = userDao.updateUser(user);					
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
