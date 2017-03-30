package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Job;
import model.User;
import model.dao.UserDAO;
import sorters.CompByBudgetAsc;
import sorters.CompByBudgetDesc;
import sorters.CompByLately;
import sorters.CompBySponsored;

/**
 * Servlet implementation class BrowseJobs
 */
@WebServlet("/BrowseJobs")
public class BrowseJobs extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sorter = Integer.parseInt(request.getParameter("sorter"));
		Comparator comp;
		switch (sorter) {
		case 1:
			comp = new CompByLately();
			break;
		case 2:
			comp = new CompByBudgetAsc();
			break;
		case 3:
			comp = new CompByBudgetDesc();
			break;
			
		case 4:
			comp = new CompBySponsored();
		default:
			comp = new CompByLately();
		}
		TreeSet<Job> jobs = UserDAO.getInstance().getAllJobs(comp);
		HttpSession session = request.getSession(false);
		boolean logged = (Boolean) request.getSession().getAttribute("logged");
		if (session.getAttribute("logged") != null && logged){
				User user = UserDAO.getProfile((User) session.getAttribute("username"));
				HashMap<Integer, String> levels = UserDAO.getLevels();
				HashMap<Integer, String> countries = UserDAO.getCountries();
				System.out.println(countries);
				request.setAttribute("user", user);
				request.setAttribute("countries", countries);
				request.setAttribute("levels", levels);
				session.setAttribute("username", user);
				getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("LogIn.html");
		}
		
	}

}
