package freeAgents;

import java.util.Date;

public class Offer {

	private User sender;
	private Job job;
	private String content;
	private int price;
	private Date date;
	
	public Offer(User sender, Job job, String content, int price) {
		this.sender = sender;
		this.job = job;
		this.content = content;
		this.price = price;
		this.date = date;
	}
	
	
	public String getContent(){
		return content;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getSenderName(){
		
		return sender.getUsername();
	}
}
