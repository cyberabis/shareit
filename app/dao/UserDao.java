package dao;

import models.User;

public interface UserDao {
	
	public User findUser(String username);
	
	public boolean saveUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
		
}
