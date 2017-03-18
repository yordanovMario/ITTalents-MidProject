package model;

import java.util.ArrayList;

public class Demo {
	
	static ArrayList<Job> catalogueJobs = new ArrayList<>();

	public static void main(String[] args) {
		
		User mario = new User("mario", "M123456o", "yordanov.mario@yahoo.com", "Mario", "Yordanov");
		
		User pavel = new User("pavel", "ngadgad234A", "paaavkata@gmai.com", "Pavel", "Damyanov");
		
		pavel.postJob("New online store", "I want a new website with online store because I don't like the old one.", 300, "Web Development", 5);
		mario.postJob("Mobile app for my warehouse", "I want a mobile application to easily manage my warehouse.", 800, "Software Development", 6);
		
		mario.sendOffer(catalogueJobs.get(0), "I can do this!", 400);
		mario.sendOffer(catalogueJobs.get(0), "OK, here's my offer!", 333);
		mario.sendOffer(catalogueJobs.get(0), "I have a wrong price :(", 33);
		
		pavel.sendOffer(catalogueJobs.get(1), "Applying for this job! I can do this mobile app!", 1000);
		pavel.sendOffer(catalogueJobs.get(1), "I can do this job but it will cost you a lot!", 3511);
		pavel.sendOffer(catalogueJobs.get(1), "I have a wrong price :(", 310);
		
		System.out.println(catalogueJobs);
		catalogueJobs.get(1).printOffers();
		catalogueJobs.get(0).printOffers();
		
		Message message1 = new Message(mario, pavel, "Question", "Hello, Pavel! Are you going to finish the project in time?");
		System.out.println(message1);
		
		Feedback feedback1 = new Feedback(mario, pavel, "Pavel did a very good job and finished the project in time! I would use his services again in the future ", 5);
		System.out.println(feedback1);
	}

}
