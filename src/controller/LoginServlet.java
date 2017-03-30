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
		String page = "LogInFailed.html";
		if(UserDAO.getInstance().validLogin(user, pass)){
			page = "index.jsp";
			User u = UserDAO.getUser(user);
			HttpSession session=req.getSession();  
	        session.setAttribute("user", u);
	        session.setAttribute("name", u.getFirstName());
	        session.setAttribute("logged", true);
		}		
		RequestDispatcher rd= req.getRequestDispatcher(page);
		rd.forward(req, resp);
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
