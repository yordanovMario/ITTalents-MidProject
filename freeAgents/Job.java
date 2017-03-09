package freeAgents;

import java.io.File;
import java.util.ArrayList;

public class Job {
	
	private User worker;
	private User employer;
	private String title;
	private String content;
	private int budget;
	private String category;
	private ArrayList<File> files;
	private int requiredExp;
	private int status;
	private ArrayList<Offer> offers;
	
	public Job(User employer, String title, String content, int budget, String category, int requiredExp) {
		if(employer!=null){
			this.employer = employer;
		}
		if(title!=null && !title.isEmpty()){
			this.title = title;
		}
		if(content!=null && !content.isEmpty()){
			this.content = content;
		}
		if(budget>0){
			this.budget = budget;
		}
		if(category!=null && !category.isEmpty()){
			this.category = category;
		}
		this.requiredExp = requiredExp;
		this.status = 1;
		this.offers = new ArrayList<Offer>();
	}
	
	public int getBudget() {
		return budget;
	}
	
	public void addOffer(Offer offer){
		this.offers.add(offer);
	}
	
	public void printOffers(){
		System.out.println("Offers for Job *" + this.title + "* :");
		for (int i = 0; i < offers.size(); i++) {
			System.out.println(offers.get(i));
		}
	}
	
//	public int getOffersSize(){
//		
//	}
//	
//	public Offer getOffer(){
//		Random rand = new Random()
//	}
	
}
