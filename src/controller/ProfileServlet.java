package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
		boolean logged = (Boolean) request.getSession().getAttribute("logged");
		if (session.getAttribute("logged") != null && logged){
				User user = UserDAO.getProfile((User)session.getAttribute("user"));
				HashMap<Integer, String> levels = UserDAO.getLevels();
				HashMap<Integer, String> countries = UserDAO.getCountries();
				request.setAttribute("user", user);
				request.setAttribute("countries", countries);
				request.setAttribute("levels", levels);
				session.setAttribute("user", user);
				getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("LogIn.html");
		}
	}

}
