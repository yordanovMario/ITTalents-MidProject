package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.User;
import model.DBManager;
import model.Job;

public class UserDAO {

	private static UserDAO instance;
	
	private UserDAO(){
		
	}
	
	public static synchronized UserDAO getInstance(){
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public synchronized void registerUser(User user) throws SQLException{
		String query = "INSERT INTO Users (fname, lname, username, email, pass) values (?, ?, ?, ?, ?)";
		java.sql.PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, user.getFirstName());
		st.setString(2, user.getLastName());
		st.setString(3, user.getUsername());
		st.setString(4, user.getEmail());
		st.setString(5, user.getPassword());
		st.execute();
	}
	
	public void signIn(User user){
		
	}
	
	public ArrayList<Job> getAllJobs(){
		ArrayList<Job> jobs = new ArrayList();
		return jobs;
	}
}
