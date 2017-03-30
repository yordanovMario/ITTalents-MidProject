package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
public class PostJobServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String username;
		RequestDispatcher rd;
		
		boolean valid = true;
		String title = req.getParameter("title");
		String desc = req.getParameter("description");
		String budget  = req.getParameter("budget");
		String category = req.getParameter("category");
		String reqExp = req.getParameter("reqExp");
		
//		String sponsored = req.getParameter("sponsored");
		boolean isSponsored = false;
//		if(sponsored != null){
//			isSponsored = true;
//		}
//		else{
//			isSponsored = false;
//		}
		String page = "index.jsp";
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if(title.isEmpty() || desc.isEmpty() || budget.isEmpty() || category.isEmpty() || reqExp.isEmpty()){
				valid = false;
			}
			if(valid){
				System.out.println(user+" in postjobservlet");
				Job job = new Job(user, title, desc, Integer.parseInt(budget), Integer.parseInt(category), Integer.parseInt(reqExp), isSponsored);
				try {
					UserDAO.getInstance().postJob(job);
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
    

}
