package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBManager;
import model.User;
import model.dao.UserDAO;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		String page = "login.html";
		
		if(UserDAO.getInstance().validLogin(user, pass)){
			System.out.println("Do tuk dobre 1");
			page = "index.html";
			HttpSession session=req.getSession();  
	        session.setAttribute("username", user);  
		}		
		RequestDispatcher rq = req.getRequestDispatcher(page);
		rq.forward(req, resp);
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
