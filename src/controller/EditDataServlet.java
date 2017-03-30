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
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String jobtitle = request.getParameter("jobtitle");
		String phone = request.getParameter("phone");
		int perhourrate = Integer.parseInt(request.getParameter("perhourrate"));
		String aboutme = request.getParameter("aboutme");
		String portfolio = request.getParameter("portfolio");
		User user = UserDAO.getUser(username);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
