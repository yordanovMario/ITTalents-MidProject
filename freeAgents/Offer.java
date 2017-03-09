package freeAgents;

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
		if(price>job.getBudget()){		// the offer must be higher than the job's budget
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
		return sender.getUsername();
	}

	@Override
	public String toString() {
		return "Offer [sender=" + sender + ", job=" + job + ", content=" + content + ", price=" + price + ", date="
				+ date + "]";
	}
	
	
}
