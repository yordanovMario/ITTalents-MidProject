package freeAgents;

import java.util.Date;

public class Message extends Feedback {

	private String title;

	public Message(String content, String title, User sender, User receiver, Date date) {
		super(content, sender, receiver, date, 1);
		this.title = title;
	}
	
}
