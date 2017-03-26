package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import model.User;
import model.DBManager;
import model.Job;

public class UserDAO {

	private static UserDAO instance;
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static HashMap<Long, Job> jobs = new HashMap<Long, Job>();	
	private UserDAO(){
		
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public HashMap<String, User> importUsersFromDB() throws SQLException{
		if(users.isEmpty()){
			String query = "SELECT user_id, username, password, email, first_name, last_name FROM users;";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			User temp;
			while(res.next()){
				temp = new User(res.getString("username"), res.getString("password"),
						res.getString("email"),	res.getString("firstName"), res.getString("lastName"));
				temp.setId(res.getLong("id"));
				users.put(temp.getUsername(), temp);
			}
		}
		return users;
	}
	
	public synchronized void registerUser(User user) throws SQLException{
		//String query = "INSERT INTO users (first_name, last_name, username, email, password, level_id) values (?, ?, ?, ?, ?, ?)";
		String query = "INSERT INTO users (first_name, last_name, username, email, password) values (?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getUsername());
		st.setString(4, user.getEmail());
		st.setString(5, user.getPassword());
		st.setInt(6, 1);
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		System.out.println(id);
		user.setId(id);
		
		users.put(user.getUsername(), user);
		System.out.println(user.getUsername() + " " + user.getFirstName());
	}
	
	public TreeSet<Job> getAllJobs(Comparator comp){
		TreeSet<Job> temp = new TreeSet<Job>(comp);
		for (int i = 0; i < jobs.size(); i++) {
			temp.add(jobs.get(i));
		}
		return temp;
	}

	public synchronized boolean validLogin(String username, String password) {
		if(users.containsKey(username)){
			System.out.println("Do tuk dobre 1");
			if(users.get(username).getPassword().equals(password)){
				System.out.println(users.get(username));
				return true;
			}
		}
		System.out.println(username);
		System.out.println(password);
		System.out.println("Do tuk dobre 2");
		return false;
	}
	
	public synchronized void postJob(Job job) throws SQLException{
		String query = "INSERT INTO jobs (title, description, budget, category_id, status) values (?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, job.getTitle());
		st.setString(2, job.getDescription());
		st.setInt(3, job.getBudget());
		st.setInt(4, job.getCategory());
		st.setInt(5, 1);
		
		//st.setInt(6, user.getLevel());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		System.out.println(id);
		job.setId(id);
		jobs.put(job.getId(), job);
		System.out.println("job is posted: " + jobs.get(job.getId()));
	}
	
	public static synchronized User getUser(String username){
		User user = users.get(username);
		return user;
	}
	
	
}

