package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Job;
import model.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class postJob
 */
@WebServlet("/postJob")
public class postJob extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");  
		HttpSession session = req.getSession(false);  
         
		boolean valid = true;
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		String budget  = req.getParameter("budget");
		String category = req.getParameter("category");
		String reqExp = req.getParameter("reqExp");
		String sponsored = req.getParameter("sponsored");
		
		if(title.isEmpty() || desc.isEmpty() || budget.isEmpty() || category.isEmpty() || reqExp.isEmpty()){
			valid = false;
		}
		String page = "PostJob.html";
		if(valid){
			page = "index.html";
			User user = UserDAO.getUser((String)session.getAttribute("username"));
			Job job = new Job(user, title, desc, Integer.parseInt(budget), Integer.parseInt(category), Integer.parseInt(reqExp), Boolean.parseBoolean(sponsored));
			try {
				UserDAO.getInstance().postJob(job);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Job posting error - " + e.getMessage());
				page = "PostJob.html";
			}
		}
		
		RequestDispatcher rq = req.getRequestDispatcher(page);
		rq.forward(req, resp);
	}
    

}
