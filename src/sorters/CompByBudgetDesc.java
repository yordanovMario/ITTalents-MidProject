package sorters;

import java.util.Comparator;

import model.Job;

public class CompByBudgetDesc implements Comparator<Job>{

	public int compare(Job j1, Job j2) {
		return j2.getBudget() - j1.getBudget();
		
	}
	
}