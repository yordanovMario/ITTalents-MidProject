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
		this.employer = employer;
		this.title = title;
		this.content = content;
		this.budget = budget;
		this.category = category;
		this.requiredExp = requiredExp;
		this.status = 1;
		this.offers = new ArrayList<Offer>();
	}
	
	public void addOffer(Offer offer){
		offers.add(offer);
	}
	
	public void printOffers(){
		for (int i = 0; i < offers.size(); i++) {
			System.out.println(offers.get(i).getSenderName());
			System.out.println(offers.get(i).getContent());
			System.out.println(offers.get(i).getPrice());
			System.out.println("==============");
		}
	}
	
	public int getOffersSize(){
		
	}
	
	public Offer getOffer(){
		Random rand = new Random()
	}
	
}
