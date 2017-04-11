package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.DBManager;
import model.Feedback;
import model.Message;
import model.User;

public class FeedbackDAO {

	private static FeedbackDAO instance;
	//Hashmap with all received feedbacks with key - user id
	private static HashMap<Long, ArrayList<Feedback>> receivedUser = new HashMap<Long, ArrayList<Feedback>>();
	//Hashmap with all feedbacks with key - feedback id
	private static HashMap<Long, Feedback> feedbacks = new HashMap<Long, Feedback>();
		
	private FeedbackDAO(){
		
	}
	
	public static synchronized FeedbackDAO getInstance(){
		if(instance == null){
			instance = new FeedbackDAO();
		}
		return instance;
	}
	
	public static synchronized void sendFeedback(Feedback feedback){
		feedbacks.put(feedback.getId(), feedback);
		if(!receivedUser.containsKey(feedback.getReceiver().getId())){
			receivedUser.put(feedback.getReceiver().getId(), new ArrayList<Feedback>());
		}
		receivedUser.get(feedback.getReceiver().getId()).add(feedback);
		
		String query = "INSERT INTO feedbacks (content, rating, date, sender_id, receiver_id, is_read) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(query);
			st.setString(1, feedback.getContent());
			st.setInt(2, feedback.getRating());
			st.setString(3, feedback.getDate());
			st.setLong(4, feedback.getSender().getId());
			st.setLong(5, feedback.getReceiver().getId());
			st.setInt(6, 0);
			st.execute();
			ResultSet res = st.getGeneratedKeys();
			res.next();
			long id = res.getLong(1);
			feedback.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized void getFeedbacks(User user){
		String query = "select f.date, f.rating, f.content, u.first_name from feedbacks f join users u on f.receiver_id = u.user_id";
		PreparedStatement ps;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				String rating = rs.getString(2);
				String content = rs.getString(3);
				String first_name = rs.getString(4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ArrayList<Feedback> getReceived(long id){
		return receivedUser.get(id);
	}
	
}
