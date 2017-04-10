package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/viewprofile")
public class ViewProfile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			User temp = UserDAO.getUserID(id);
			User user = UserDAO.getProfile(temp);
			request.setAttribute("userprofile", user);
			String country = UserDAO.getCountry(user.getCountry());
			request.setAttribute("country", country);
			getServletContext().getRequestDispatcher("/viewprofile.jsp").forward(request, response);
		}
		
	}


}
