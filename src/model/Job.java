package model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Job {
	
	private long id;
	private User worker;
	private User employer;
	private String title;
	private String description;
	private int budget;
	private int category;
	private ArrayList<File> files;
	private int requiredExp;
	private int status;
	private ArrayList<Offer> offers;
	private Offer acceptedOffer;
	private boolean sponsored;
	private LocalDateTime date;

	public Job(User employer, String title, String description, int budget, int category, int requiredExp, boolean sponsored) {
		this.date = LocalDateTime.now();
		if(employer!=null){
			this.employer = employer;
		}
		if(title!=null && !title.isEmpty()){
			this.title = title;
		}
		if(description!=null && !description.isEmpty()){
			this.description = description;
		}
		if(budget>0){
			this.budget = budget;
		}
		if(category!=0 && category <= 16){
			this.category = category;
		}
		this.sponsored = sponsored;
		this.requiredExp = requiredExp;
		this.status = 1;
		this.offers = new ArrayList<Offer>();
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isSponsored() {
		return sponsored;
	}

	public void setSponsored(boolean sponsored) {
		this.sponsored = sponsored;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public int getBudget() {
		return budget;
	}
	
	public void addOffer(Offer offer){
		this.offers.add(offer);
		this.worker = offer.getSender();
		this.status = 2;
	}
	
	public void printOffers(){
		System.out.println("Offers for Job *" + this.title + "* :");
		for (int i = 0; i < offers.size(); i++) {
			System.out.println(offers.get(i));
		}
	}
	
	public void acceptOffer(Offer offer){
		if(offers.contains(offer)){
			this.acceptedOffer = offer;
		}
		this.status = 3;
	}
	
	public void inProgress(){
		this.status = 4;
	}
	
	public void jobDone(){
		this.status = 5;
	}
	
	public User getEmployer() {
		return employer;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getCategory() {
		return category;
	}

	public void openDispute(){
		this.status = 6;
	}

	
}
