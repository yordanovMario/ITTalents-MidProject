package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBManager;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		
	}
	
	public void destroy(){
		try{
			DBManager.getInstance().getConnection().close();
		}
		catch(SQLException e){
			System.out.println("Error in closing the connection - " + e.getMessage());
		}
	}
}
