package freeAgents;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

public class Demo {
	
	static ArrayList<Job> catalogueJobs = new ArrayList<>();

	public static void main(String[] args) {
		
		User mario = new User("mario", "M123456o", "yordanov.mario@yahoo.com", "mario", "ddd");
		
		User pavel = new User("pavel", "ngadgad234A", "paaavkata@gmai.com", "pavel", "damyanov");
		
		pavel.postJob("job1", "content", 100, "programing", 5);
		mario.postJob("title", "content", 200, "category", 6);
		pavel.sendOffer(catalogueJobs.get(0), "alabalanica", 350);
		pavel.sendOffer(catalogueJobs.get(0), "dgadga", 3511);
		pavel.sendOffer(catalogueJobs.get(0), "aldgadgadadgadgaabalanica", 310);
		pavel.sendOffer(catalogueJobs.get(0), "alagadgadbalanica", 330);
		
		mario.sendOffer(catalogueJobs.get(1), "adgadghad", 150);
		mario.sendOffer(catalogueJobs.get(1), "dgadgad", 333);
		mario.sendOffer(catalogueJobs.get(1), "gadga", 33);
		mario.sendOffer(catalogueJobs.get(1), "24fgwvfs", 50);
		
		System.out.println(mario);
		System.out.println(pavel);
		System.out.println(catalogueJobs);
		catalogueJobs.get(1).printOffers();
		catalogueJobs.get(0).printOffers();
	}

}
