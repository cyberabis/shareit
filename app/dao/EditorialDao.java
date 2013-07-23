package dao;

import java.util.List;

import models.Editorial;

public interface EditorialDao {
	
	public Editorial findEditorial(int articleId);
	
	public List<Editorial> findAllEditorials();

}
