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
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		boolean valid = true;
		String page = "SignUpFailed.html";
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		String passconf	= req.getParameter("password2");
		
		if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty() || passconf.isEmpty()){
			valid = false;
		}
	
		boolean second = true;
		boolean third = true;
		if(!pass.equals(passconf)){
			page = "SignUpPasswords.html";
			second = false;
		}
		if(UserDAO.getInstance().checkUser(user, email)){
			page = "SignUpDuplicates.html";
			third = false;
		}
		if(valid && second && third){
			page = "LogInSuccess.html";
			try {
				User u = new User(user, pass, email, fname, lname);
				UserDAO.getInstance().registerUser(u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SignUp error - " + e.getMessage());
			}
		}

		RequestDispatcher rq = req.getRequestDispatcher(page);
		rq.forward(req, resp);

			
	}
	
}