package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import model.DBManager;
import model.Offer;

public class OfferDAO {
	
	private static OfferDAO instance;
	
	private OfferDAO(){

	}
	
	public static synchronized OfferDAO getInstance(){
		if(instance == null){
			instance = new OfferDAO();
		}
		return instance;
	}
	
	public synchronized void postOffer(Offer offer) throws SQLException{
		System.out.println(offer.getContent());
		System.out.println(offer.getPrice());
		System.out.println(offer.getSender());
		System.out.println(offer.getJob());
		System.out.println(offer.getDate());
		String query = "INSERT INTO offers (content, price, date, job_id, sender_id) values (?, ?, ?, ?, ?)";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(query);
		st.setString(1, offer.getContent());
		st.setInt(2, offer.getPrice());
		st.setString(3, offer.getDate());
		st.setLong(4, offer.getJob());
		st.setLong(5, offer.getSender());
		st.execute();
		ResultSet res = st.getGeneratedKeys();
		res.next();
		long id = res.getLong(1);
		offer.setId(id);
	}
	
	public HashSet<Offer> getJobOffers(long id){
		HashSet<Offer> temp = new HashSet<Offer>();
		try {
			String query = "SELECT * FROM offers WHERE job_id="+id;
			java.sql.PreparedStatement st = DBManager.getInstance().getConnection().clientPrepareStatement(query);
			ResultSet res;
			res = st.executeQuery();
			while(res.next()){
				temp.add(new Offer(res.getLong("sender_id"), res.getLong("job_id"), res.getString("content"), res.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
}
