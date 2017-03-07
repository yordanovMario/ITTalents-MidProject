package freeAgents;

import java.util.Date;

public class Feedback {
	
	private String content;
	private User sender;
	private User receiver;
	private Date date;
	private double rating;

	public Feedback(String content, User sender, User receiver, Date date, double rating) {
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.date = date;
		this.rating = rating;
	}
}
