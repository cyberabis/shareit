package dao;

import models.Query;

public interface QueryDao {
	
	public Query findQuery(int queryId);
	
	public Query[] findAllQueries();
	
	public boolean saveQuery(Query query);
	
}
