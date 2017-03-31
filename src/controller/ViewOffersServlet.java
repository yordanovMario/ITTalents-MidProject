package controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Job;
import model.Offer;
import model.User;
import model.dao.UserDAO;

/**
 * Servlet implementation class ViewOffersServlet
 */
@WebServlet("/viewoffers")
public class ViewOffersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			//Job j = (Job) request.getParameter("jobid");
			//System.out.println(j.getId());
			HashSet<Offer> offers = UserDAO.getInstance().getJobOffers(id);
			System.out.println(offers.isEmpty());
			request.setAttribute("offers", offers);
			getServletContext().getRequestDispatcher("/viewoffers.jsp").forward(request, response);
		}
		
		
	}


}
