package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Offer {

	private User sender;
	private Job job;
	private String content;
	private int price;
	private String date;
	
	public Offer(User sender, Job job, String content, int price) {
		if(sender!=null){
			this.sender = sender;
		}
		if(job!=null){
			this.job = job;
		}
		if(content!=null && !content.isEmpty()){
			this.content = content;
		}
		if(price >= job.getBudget()){		// the offer must be >= the job's budget
			this.price = price;
		}
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.date = dateTime.format(formatter);
	}
	
	public String getContent(){
		return content;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getSenderName(){
		System.out.println(sender.getFirstName() + " " + sender.getLastName());
		return "";
	}
	
	public User getSender() {
		return sender;
	}
	@Override
	public String toString() {
		System.out.println("============================================");
		System.out.println("-*- Offer -*-");
		System.out.println("From: " + this.sender.getFirstName() + " " + this.sender.getLastName());
		System.out.println("Date: " + this.date);
		System.out.println(this.content);
		System.out.println("Price: EUR " + this.price);
		System.out.println("============================================");
		return "";
	}
	
	
}
