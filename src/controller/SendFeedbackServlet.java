package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Feedback;
import model.User;
import model.dao.FeedbackDAO;
import model.dao.UserDAO;

@WebServlet("/sendfeedback")
public class SendFeedbackServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		boolean valid = true;
		
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			if(req.getParameter("content") != null && req.getParameter("rating") != null){
				String content = req.getParameter("content");
				int rating = Integer.parseInt(req.getParameter("rating"));
				String date = req.getParameter("date");
				long id = Long.parseLong(req.getParameter("id"));
				User receiver = UserDAO.getUserID(id);
				User sender = (User) session.getAttribute("user");
				if(sender == null || receiver == null || content.isEmpty() || rating < 1 || rating >5){
					valid = false;
				}
				if(valid){
					Feedback feedback = new Feedback(sender, receiver, content, rating, date);
					FeedbackDAO.getInstance();
					FeedbackDAO.sendFeedback(feedback);
					req.setAttribute("notification", "Feedback successfuly sent!");
					getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
				}
			}
			else{
				long id = Long.parseLong(req.getParameter("id"));
				System.out.println(id);
				req.setAttribute("id", id);
				getServletContext().getRequestDispatcher("/sendfeedback.jsp").forward(req, resp);
			}
		}
		
	}

}