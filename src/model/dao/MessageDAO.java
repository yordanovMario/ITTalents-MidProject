package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DBManager;
import model.Message;
import model.User;

public class MessageDAO {
	
	private static MessageDAO instance;
	
	private MessageDAO(){
		
	}
	
	public static synchronized MessageDAO getInstance(){
		if(instance == null){
			instance = new MessageDAO();
		}
		return instance;
	}
	
	public static synchronized void sendMessage(Message message){
		String query = "INSERT INTO messages (title, content, sender_id, receiver_id, date) values (?, ?, ?, ?, ?)";
		PreparedStatement st;
		try {
			st = DBManager.getInstance().getConnection().prepareStatement(query);
			st.setString(1, message.getTitle());
			st.setString(2, message.getContent());
			st.setLong(3, message.getSender().getId());
			st.setLong(4, message.getReceiver().getId());
			st.setString(5, message.getDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized void getMessages(User user) throws SQLException{
		String query = "select m.date, m.title, m.content, u.first_name from messages m join users u on m.receiver_id = u.user_id";
		PreparedStatement ps;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String date = rs.getString(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String first_name = rs.getString(4);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
