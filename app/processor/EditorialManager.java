package processor;

import java.util.List;

import dao.EditorialDao;
import dao.EditorialDaoMongo;
import models.Editorial;

public class EditorialManager {
	
	public static Editorial getEditorial(int articleId) {
		EditorialDao editorialDao = new EditorialDaoMongo();
		return editorialDao.findEditorial(articleId);
	}
	
	public static List<Editorial> getAllEditorials() {
		EditorialDao editorialDao = new EditorialDaoMongo();
		return editorialDao.findAllEditorials();
	}

}
