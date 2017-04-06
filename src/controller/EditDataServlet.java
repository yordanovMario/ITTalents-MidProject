package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class editDataServlet
 */
@WebServlet("/editdata")
public class EditDataServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			User user = (User) request.getSession().getAttribute("user");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String jobtitle = request.getParameter("jobtitle");
			String phone = request.getParameter("phone");
			int perhourrate = Integer.parseInt(request.getParameter("perhourrate"));
			String aboutme = request.getParameter("aboutme");
			String portfolio = request.getParameter("portfolio");
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setJobTitle(jobtitle);
			user.setPhone(phone);
			user.setPerHourRate(perhourrate);
			user.setAboutMe(aboutme);
			user.setPortfolio(portfolio);
			try {
				UserDAO.updateProfile(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		}
		
	}

}
