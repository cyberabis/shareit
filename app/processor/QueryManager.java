package processor;

import java.util.Date;
import java.util.List;

import dao.QueryDao;
import dao.QueryDaoMongo;

import models.Query;

public class QueryManager {
	
	public static String saveQuery(Query query) {
		String result = "fail";
		
		if (((query.getGuestEmail() != null) && (!query.getGuestEmail().equals(""))) || (query.getUsername() != null)){
			query.setQuestionDate(new Date());
			QueryDao queryDao = new QueryDaoMongo();
			queryDao.saveQuery(query);
			result = "success";			
		}		
		return result;		
	}
	
	public static List<Query> findQueries() {
		//todo
		return null;
	}
	
	public static List<Query> findQuery(int queryId) {
		//todo
		return null;
	}
}
