package models;

import java.util.Date;

public class Query {
	
	private int queryId;
	private String username;
	private String guestname;
	private String guestEmail;
	private String question;
	private String makePublic; 
	private Date questionDate;
	private String answer;
	private Date answerDate;
	private String answerAuthor;
	
	
	public int getQueryId() {
		return queryId;
	}
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public String getAnswerAuthor() {
		return answerAuthor;
	}
	public void setAnswerAuthor(String answerAuthor) {
		this.answerAuthor = answerAuthor;
	}
	public String getMakePublic() {
		return makePublic;
	}
	public void setMakePublic(String makePublic) {
		this.makePublic = makePublic;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	
}
