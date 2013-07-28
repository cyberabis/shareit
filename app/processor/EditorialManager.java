package processor;

import java.util.Date;
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
	
	public static String saveEditorial(Editorial editorial) {
		String result = "no saved";
		EditorialDao editorialDao = new EditorialDaoMongo();
		if ((editorial.getArticle() != null) && (editorial.getTitle() != null)){
			editorial.setCreateDate(new Date());
			boolean saveResult = editorialDao.saveEditorial(editorial);
			if (saveResult)
				result = "saved";
		}		
		return result;
	}
	
	public static int getLastEditorialId() {
		int lastEditorialId = 0;
		//TODO
		return lastEditorialId;
	}

}
