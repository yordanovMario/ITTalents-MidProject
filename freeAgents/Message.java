package freeAgents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message extends Feedback {

	private String title;

	public Message(User sender, User receiver, String title, String content) {
		super( sender, receiver, content, 1);
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
		System.out.println("============================================");
		System.out.println("Message from " + getSender().getFirstName() + " " + getSender().getLastName());
		System.out.println("Date: " + getDate());
		System.out.println(getTitle());
		System.out.println(getContent());
		System.out.println("============================================");
		return "";
	}
	
	
	
}
