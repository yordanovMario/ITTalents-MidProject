package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Job;
import model.dao.UserDAO;

/**
 * Servlet implementation class BrowseJobs
 */
@WebServlet("/BrowseJobs")
public class BrowseJobs extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Job> jobs = UserDAO.getInstance().getAllJobs();
		response.getWriter().append(jobs.toString());
	}

}
