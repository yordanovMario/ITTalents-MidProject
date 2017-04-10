package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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

/**
 * Servlet implementation class postJob
 */
@WebServlet("/postjob")
public class PostJobServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		RequestDispatcher rd;
		
		boolean valid = true;
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		String budget  = req.getParameter("budget");
		String category = req.getParameter("category");
		String reqExp = req.getParameter("reqExp");
		String expire = req.getParameter("expire");
		boolean isSponsored = false;
		String page = "index.jsp";
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if(title.isEmpty() || desc.isEmpty() || budget.isEmpty() || category.isEmpty() || reqExp.isEmpty() || expire.isEmpty()){
				valid = false;
			}
			if(valid){
				System.out.println(user+" in postjobservlet");
				Job job = new Job(user, title, desc, Integer.parseInt(budget), Integer.parseInt(category), Integer.parseInt(reqExp), isSponsored, Integer.parseInt(expire), null);
				try {
					JobDAO.getInstance().postJob(job);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Job posting error - " + e.getMessage());
					page = "postjob.jsp";
				}
			}
		}
		rd = req.getRequestDispatcher(page);
		rd.forward(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		boolean logged = (Boolean) request.getSession().getAttribute("logged");
		if (session.getAttribute("logged") != null && logged){
			HashMap<Integer, String> categories = UserDAO.getCategories();
			request.setAttribute("categories", categories);
			getServletContext().getRequestDispatcher("/postjob.jsp").forward(request, response);
		}
		else{
			response.sendRedirect("LogIn.html");
		}
	}

}
