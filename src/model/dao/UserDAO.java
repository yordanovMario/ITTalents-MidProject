package model.dao;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.HashSet;


import org.apache.commons.codec.binary.Hex;

import model.User;
import model.DBManager;


public class UserDAO {

	private static UserDAO instance;
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static HashSet<String> emails = new HashSet<String>();
	
	private static HashMap<Integer, String> categories = new HashMap<Integer, String>();
	private static HashMap<Integer, String> countries = new HashMap<Integer, String>();
	private static HashMap<Integer, String> levels = new HashMap<Integer, String>();
	
	
	private UserDAO(){
		try {
			reloadCache();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
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
	
	public static HashMap<String, User> getUsers() {
		return users;
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
		user.setId(id);
		user.setPassword(md5(user.getPassword()));
		users.put(user.getUsername(), user);
	}
	
	public String getUserName(long id){
		String query = "SELECT first_name, last_name FROM users WHERE user_id ="+id;
		PreparedStatement ps;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			String name = rs.getString(1) + " " + rs.getString(2);
			return name;
		}
		catch(SQLException e){
			System.out.println("Error getting data: " + e.getMessage());
		}
		return "Nobody";
	}
	
	public synchronized boolean validLogin(String username, String password) {
		if(users.containsKey(username)){
			String result = md5(password);
			if(users.get(username).getPassword().equals(result)){
				System.out.println("Pass and username match with DB. User " + username + " logged in.");
				return true;
			}
		}
		return false;
	}
	
	private String md5(String pass){
		try{
			MessageDigest messageDigest;
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(pass.getBytes(Charset.forName("UTF8")));
			final byte[] resultByte = messageDigest.digest();
			final String result = Hex.encodeHexString(resultByte);
			return result;
		}
		catch(Exception e){
			
		}
		return pass;
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
		String query = "SELECT username, password, email, first_name, last_name, job_title, phone, about_me, country_id, level_id, portfolio, perHourRate FROM users WHERE username = ?";
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
			e.printStackTrace();
		}
		return user;
	}
	
	public static synchronized void updateProfile(User user) throws SQLException{
		String query = "UPDATE users SET first_name=?, last_name=?, job_title=?, phone=?, perHourRate=?, about_me=?, portfolio=? WHERE user_id=?;";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getJobTitle());
		st.setString(4, user.getPhone());
		st.setInt(5, user.getPerHourRate());
		st.setString(6, user.getAboutMe());
		st.setString(7, user.getPortfolio());
		st.setLong(8, user.getId());
		st.execute();
		UserDAO.getUsers().remove(user.getUsername());
		UserDAO.getUsers().put(user.getUsername(), user);
	}
	
}

