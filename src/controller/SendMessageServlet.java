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
import model.Message;
import model.User;
import model.dao.JobDAO;
import model.dao.MessageDAO;
import model.dao.UserDAO;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/sendmessage")
public class SendMessageServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		RequestDispatcher rd;
		
		boolean valid = true;
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String date = req.getParameter("date");
		
		String page = "index.jsp";
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			if(req.getParameter("title") != null && req.getParameter("content") != null){
				long id = Long.parseLong(req.getParameter("id"));
				User receiver = UserDAO.getUserID(id);
				User sender = (User) session.getAttribute("user");
				if(sender == null || receiver == null || title.isEmpty() || content.isEmpty()){
					valid = false;
				}
				if(valid){
					System.out.println(sender+" in sendMessageServlet");
					Message message = new Message(sender, receiver, title, content, date);
					System.out.println(message);
					MessageDAO.getInstance();
					MessageDAO.sendMessage(message);
					req.setAttribute("notification", "Message successfuly sent!");
					getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
				}
			}
			else{
				long id = Long.parseLong(req.getParameter("id"));
				System.out.println(id);
				req.setAttribute("id", id);
				getServletContext().getRequestDispatcher("/sendmessage.jsp").forward(req, resp);
			}
		}
		
		
	}

}
