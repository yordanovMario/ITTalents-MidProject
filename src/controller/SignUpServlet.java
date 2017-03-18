package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.UserDAO;
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet{

protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean valid = true;
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String user = req.getParameter("username");
		String pass = req.getParameter("pass");
		if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()){
			valid = false;
		}
		String page = "SignUp.html";
		if(valid){
			page = "index.html";
			User u = new User(user, pass, email, fname, lname);
			try {
				UserDAO.getInstance().registerUser(u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				page = "SignUp.html";
			}
		}
		RequestDispatcher rq = req.getRequestDispatcher(page);
		rq.forward(req, resp);
		
	}
	
}
