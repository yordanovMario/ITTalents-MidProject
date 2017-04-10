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
import model.dao.JobDAO;
import model.dao.UserDAO;
import sorters.CompByBudgetAsc;
import sorters.CompByBudgetDesc;
import sorters.CompByLately;
import sorters.CompBySponsored;

/**
 * Servlet implementation class BrowseJobs
 */
@WebServlet("/browsejobs")
public class BrowseJobsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sorter;
		int category;
		if(request.getParameter("sort") == null || request.getParameter("sort") == ""){
			sorter=2;
		}
		else{
			sorter = Integer.parseInt(request.getParameter("sort"));
		}
		if(request.getParameter("category") == null || request.getParameter("category") == ""){
			category=0;
		}
		else{
			category = Integer.parseInt(request.getParameter("category"));
		} 
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
			comp = new CompByBudgetDesc();
		}
		TreeSet<Job> jobs = JobDAO.getInstance().getAllJobs(comp, category);
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
				User u = (User) session.getAttribute("user");
				request.setAttribute("user", u);
				request.setAttribute("jobs", jobs);
				HashMap<Integer, String> categories = UserDAO.getCategories();
				request.setAttribute("categories", categories);
				getServletContext().getRequestDispatcher("/browsejobs.jsp").forward(request, response);
		}
		else{	
			response.sendRedirect("LogIn.html");
		}
		
	}

}
