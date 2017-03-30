package model.dao;

import java.security.MessageDigest;
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
	private static HashSet<String> emails = new HashSet<String>();
	private static HashSet<Job> jobs = new HashSet<Job>();	
	private static HashMap<Integer, String> categories = new HashMap<Integer, String>();
	private static HashMap<Integer, String> countries = new HashMap<Integer, String>();
	private static HashMap<Integer, String> levels = new HashMap<Integer, String>();
	private static HashMap<Integer, String> statuses = new HashMap<Integer, String>();
	private UserDAO(){
		try {
			reloadCache();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	private void reloadCache() throws SQLException{
		if(users.isEmpty()){
			String query = "SELECT user_id, username, password, email, first_name, last_name FROM users;";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			User temp;
			while(res.next()){
				temp = new User(res.getString("username"), res.getString("password"),
						res.getString("email"),	res.getString("first_name"), res.getString("last_name"));
				temp.setId(res.getLong("user_id"));
				users.put(temp.getUsername(), temp);
				emails.add(temp.getEmail());
			}
			this.users = users;
		}
		if(jobs.isEmpty()){
			String query = "SELECT j.title, j.description, j.budget, j.category_id, u.username FROM jobs j JOIN users u ON j.user_employer_id = u.user_id";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			User temp;
			Job job;
			while(res.next()){
				temp = users.get(res.getString("username"));
				job = new Job(temp, res.getString("title"), res.getString("description"), Integer.parseInt(res.getString("budget")), Integer.parseInt(res.getString("category_id")), 3, false);
				jobs.add(job);
			}
		}
		if(categories.isEmpty()){
			String query = "SELECT * FROM categories";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				categories.put((Integer) res.getInt("category_id"), res.getString("name"));
			}
		}
		if(countries.isEmpty()){
			String query = "SELECT * FROM countries";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				countries.put((Integer) res.getInt("country_id"), res.getString("name"));
			}
		}
		if(levels.isEmpty()){
			String query = "SELECT * FROM user_levels";
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				levels.put((Integer) res.getInt("level_id"), res.getString("name"));
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

	public static HashMap<Integer, String> getCategories() {
		return categories;
	}

	public static HashMap<Integer, String> getCountries() {
		return countries;
	}

	public static HashMap<Integer, String> getLevels() {
		return levels;
	}

	public static HashMap<Integer, String> getStatuses() {
		return statuses;
	}

	public synchronized void registerUser(User user) throws SQLException{
		//String query = "INSERT INTO users (first_name, last_name, username, email, password, level_id) values (?, ?, ?, ?, ?, ?)";
		String query = "INSERT INTO users (first_name, last_name, username, email, password) values (?, ?, ?, ?, md5(?))";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getUsername());
		st.setString(4, user.getEmail());
		st.setString(5, user.getPassword());
		//st.setInt(6, 1);
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
		System.out.println(jobs);
		TreeSet<Job> temp = new TreeSet<Job>(comp);
		for (Job j : jobs) {
			temp.add(j);
		}
		return temp;
	}

	public synchronized boolean validLogin(String username, String password) {
		if(users.containsKey(username)){
			MessageDigest m;
			if(users.get(username).getPassword().equals(password)){
				System.out.println("Pass and username match with DB. User " + username + " logged in.");
				return true;
			}
		}
		return false;
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
	
	public static synchronized User getUser(String username){
		User user = users.get(username);
		return user;
	}

	public synchronized boolean checkUser(String user, String email) {
		if(users.containsKey(user) || emails.contains(email)){
			return true;
		}
		return false;
	}
	public static synchronized User getProfile(User user){
		String query = "SELECT username, password, email, first_name, last_name, job_title, phone, about_me, country_id, level_id, porfolio, perHourRate FROM users WHERE username = ?";
		PreparedStatement ps;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(query);
			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String job_title = rs.getString(6);
			String phone = rs.getString(7);
			String about_me = rs.getString(8);
			String country = rs.getString(9);
			int level = rs.getInt(10);
			String portfolio = rs.getString(11);
			int perHourRate	= rs.getInt(12);
			user.setJobTitle(job_title);
			user.setPhone(phone);
			user.setAboutMe(about_me);
			user.setCountry(country);
			user.setLevel(level);
			user.setPortfolio(portfolio);
			user.setPerHourRate(perHourRate);
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
		
	}
	
	public static synchronized void updateProfile(User user) throws SQLException{
		String query = "INSERT INTO users (first_name, last_name,) values (?, ?, ?, ?, md5(?))";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getJobTitle());
		st.setString(4, user.getPhone());
		st.setInt(5, user.getPerHourRate());
		st.setString(5, user.getAboutMe());
		st.setString(5, user.getPortfolio());
		st.execute();
		users.remove(user.getUsername());
		users.put(user.getUsername(), user);
	}
	
}

