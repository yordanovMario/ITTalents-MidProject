package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Job;
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			comp = new CompBySponsored();;
		}
		TreeSet<Job> jobs = UserDAO.getInstance().getAllJobs(comp);
		response.getWriter().append(jobs.toString());
		
	}

}
