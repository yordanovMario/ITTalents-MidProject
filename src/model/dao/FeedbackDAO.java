package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBManager;
import model.Feedback;
import model.User;

public class FeedbackDAO {

	private static FeedbackDAO instance;
	
	private FeedbackDAO(){
		
	}
	
	public static synchronized FeedbackDAO getInstance(){
		if(instance == null){
			instance = new FeedbackDAO();
		}
		return instance;
	}
	
	public static synchronized void sendFeedback(Feedback feedback){
		
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
	
}
