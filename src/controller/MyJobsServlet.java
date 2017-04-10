package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Job;
import model.User;
import model.dao.JobDAO;

/**
 * Servlet implementation class MyJobsServlet
 */
@WebServlet("/myjobs")
public class MyJobsServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");
			ArrayList<Job> jobs = JobDAO.getInstance().getMyJobs(u.getId());
			request.setAttribute("user", u);
			request.setAttribute("jobs", jobs);
			getServletContext().getRequestDispatcher("/myjobs.jsp").forward(request, response);
		}
	}


}
