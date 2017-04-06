package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import model.DBManager;
import model.Job;
import model.User;

public class JobDAO {
	
	private static JobDAO instance;
	private static HashSet<Job> jobs = new HashSet<Job>();
	private static HashMap<Integer, String> statuses = new HashMap<Integer, String>();
	
	private JobDAO(){
		
	}
	
	public static synchronized JobDAO getInstance(){
		if(instance == null){
			instance = new JobDAO();
		}
		return instance;
	}
	
	private void reloadCache() throws SQLException{
		if(jobs.isEmpty()){
			String query = "SELECT j.job_id, j.title, j.description, j.budget, j.category_id, u.username FROM jobs j JOIN users u ON j.user_employer_id = u.user_id";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			User temp;
			Job job;
			while(res.next()){
				temp = UserDAO.getUser(res.getString("username"));
				job = new Job(temp, res.getString("title"), res.getString("description"), Integer.parseInt(res.getString("budget")), Integer.parseInt(res.getString("category_id")), 3, false);
				job.setId(Long.parseLong(res.getString("job_id")));
				jobs.add(job);
			}
		}
		if(statuses.isEmpty()){
			String query = "SELECT * FROM statuses";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				statuses.put((Integer) res.getInt("status_id"), res.getString("name"));
			}
		}
	}
	
	public synchronized void postJob(Job job) throws SQLException{
		String query = "INSERT INTO jobs (title, description, budget, category_id, status, user_employer_id) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, job.getTitle());
		st.setString(2, job.getDescription());
		st.setInt(3, job.getBudget());
		st.setInt(4, job.getCategory());
		st.setInt(5, 1);
		st.setLong(6, job.getEmployer().getId());
		//st.setInt(7, 2);
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		job.setId(id);
		jobs.add(job);
	}
	
	public TreeSet<Job> getAllJobs(Comparator comp, int category){
		TreeSet<Job> temp = new TreeSet<Job>(comp);
		if(category == 0){
			for (Job j : jobs) {
				temp.add(j);
			}
		}
		else{
			for (Job j : jobs) {
				if(j.getCategory() == category){
					temp.add(j);
				}
			}
		}
		return temp;
	}
	
	public HashSet<Job> getMyJobs(long id){
		HashSet<Job> temp = new HashSet<Job>();
		for (Job j : jobs) {
			if(j.getEmployer().getId() == id){
				temp.add(j);
			}
		}
		return temp;
	}
	
	public static HashMap<Integer, String> getStatuses() {
		return statuses;
	}
	
}
