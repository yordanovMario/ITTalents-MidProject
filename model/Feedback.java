package freeAgents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Feedback {
	
	private String content;
	private User sender;
	private User receiver;
	private String date;
	private int rating;
	
	public Feedback(User sender, User receiver, String content, int rating) {
		if(content!=null && !content.isEmpty()){
			this.content = content;
		}
		if(sender!=null){
			this.sender = sender;
		}
		if(sender!=null){
			this.receiver = receiver;
		}
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.date = dateTime.format(formatter);
		this.rating = rating;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public User getSender() {
		return sender;
	}
	
	@Override
	public String toString() {
		System.out.println("============================================");
		System.out.println("Feedback from " + getSender().getFirstName() + " " + getSender().getLastName());
		System.out.println("Date: " + getDate());
		System.out.println(getContent());
		System.out.println("============================================");
		return "";
	}
	
}