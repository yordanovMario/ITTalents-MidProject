package model;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import model.dao.UserDAO;

public class DBManager {

	private static DBManager instance;
	
	//private static final String DB_IP = "46.238.28.76";
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "freeagentsdb";
	private static final String DB_USER = "freeagents";
	private static final String DB_PASS = "freeagents.eu71928";
	private Connection con = null;
	private DBManager(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
		}
		try{
			con = (Connection) DriverManager.getConnection("jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASS);
		}
		catch(SQLException e){
			System.out.println("Error connecting to Database - " + e.getMessage());
		}

	}
	
	public static synchronized DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return con;
	}
	
}
