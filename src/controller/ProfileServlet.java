package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.User;
import model.dao.UserDAO;



@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username;
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		
		if (session != null){
			if (session.getAttribute("username") != null){
				username = (String) session.getAttribute("username");
				User user = UserDAO.getUser(username);
				out.println("<h3>PROFILE</h3>");
				out.println("User: " + user.getUsername());
				out.println("<br>Email: " + user.getEmail());
				out.println("<br>Name: " + user.getFirstName() + " " + user.getLastName());
				rd = request.getRequestDispatcher("/profile.jsp");
				rd.include(request, response);
			}
			else{
				response.sendRedirect("LogIn.html");
			}
		}
		else {
			response.sendRedirect("LogIn.html");
		}
		
	}


}
