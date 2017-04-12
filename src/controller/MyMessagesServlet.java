package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.User;
import model.dao.MessageDAO;

/**
 * Servlet implementation class MyMessagesServlet
 */
@WebServlet("/mymessages")
public class MyMessagesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");
			MessageDAO.getInstance();
			ArrayList<Message> received = MessageDAO.getReceived(u.getId());
			request.setAttribute("user", u);
			request.setAttribute("messages", received);
			getServletContext().getRequestDispatcher("/mymessages.jsp").forward(request, response);
		}
		
	}

}
