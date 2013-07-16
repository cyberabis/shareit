package dao;

import models.Share;

public interface ShareDao {
	
	public Share findShare(int uniqueId);
	public Share saveShare(Share share);
	
}
