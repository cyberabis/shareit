package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import models.Query;

public class QueryDaoMongo implements QueryDao {

	@Override
	public Query findQuery(int queryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query[] findAllQueries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveQuery(Query query) {
		DB db = DaoMongo.connect();
		
		int nextQueryId = MongoDaoUtil.getnextUniqueId("queryId");
		query.setQueryId(nextQueryId);
		
		DBCollection coll = db.getCollection("query");
		BasicDBObject doc = new BasicDBObject("queryId", query.getQueryId()).
                append("username", query.getUsername()).
                append("guestname", query.getGuestname()).
                append("guestEmail", query.getGuestEmail()).
                append("question", query.getQuestion()).
                append("makePublic", query.getMakePublic()).
                append("questionDate", query.getQuestionDate()).
                append("answer", query.getAnswer()).
                append("answerDate", query.getAnswerDate()).
                append("answerAuthor", query.getAnswerAuthor());
		coll.insert(doc);
		return true;
	}

}
