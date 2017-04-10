package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Message{
	
	private long id;
	private String content;
	private String title;
	private User sender;
	private User receiver;
	private String date;
	

	public Message(User sender, User receiver, String title, String content, String date) {
		if(title!=null && !title.isEmpty()){
			this.title = title;
		}
		if(this.date == null){
			LocalDateTime dateTime = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			this.date = dateTime.format(formatter);
		}
		if(sender!=null){
			this.sender = sender;
		}
		if(sender!=null){
			this.receiver = receiver;
		}
		if(content!=null){
			this.content = content;
		}
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

	public String getContent() {
		return content;
	}

	public User getSender() {
		return sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public String getDate() {
		return date;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
