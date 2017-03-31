package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.dao.UserDAO;

public class Offer {
	
	private long id;
	private long sender;
	private long job;
	private String content;
	private int price;
	private String date;
	
	public Offer(long sender, long job_id, String content, int price) {
		this.sender = sender;
		this.job = job_id;
		if(content!=null && !content.isEmpty()){
			this.content = content;
		}
		this.price = price;
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		this.date = dateTime.format(formatter);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSenderName(){
		return UserDAO.getInstance().getUserName(id);
	}
	public long getJob() {
		return job;
	}

	public String getDate() {
		return date;
	}

	public String getContent(){
		return content;
	}
	
	public int getPrice(){
		return price;
	}

	public long getSender() {
		return sender;
	}
	
	public String toString() {
		String job = "<div class=\"search-results\">"
				+ "<div class=\"result-description\">"
				+ "<p>Description</p>"
				+ "<p>"+getContent()+"</p>"
				+ "</div>"
				+ "<div class=\"result-budjet\">"
				+ "<p>Budjet</p>"
				+ "<p>"+getPrice()+"</p>"
				+ "</div>"
				+ "<div class=\"result-title\">"
				+ "<p>From</p>"
				+ "<p>"+getSenderName()+"</p>"
				+ "</div>"
				//+ "<form method=\"GET\" action=\"acceptoffer\">"
				//+ "<input type=\"hidden\" value=\""+getId()+"\" name=\"id\">"
				//+ "<input type=\"submit\" id=\"post-job-btn\" value=\"Accept offer\" />"
				//+ "</form>"
				+ "</div>";
		return job;
	}
	
	
}
