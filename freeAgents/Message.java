package freeAgents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message extends Feedback {

	private String title;

	public Message(String content, String title, User sender, User receiver) {
		super(content, sender, receiver, 1);
		if(title!=null && !title.isEmpty()){
			this.title = title;
		}
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.setDate(dateTime.format(formatter)); 
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		//return "Message [date=" + getDate() + "]" ;
		System.out.println("Message from " + getSender().getFirstName() + " " + getSender().getLastName());
		System.out.println("Date: " + getDate());
		System.out.println(getTitle());
		System.out.println(getContent());
		return "";
	}
	
	
	
}