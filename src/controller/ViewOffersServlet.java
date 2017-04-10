package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Offer;
import model.dao.OfferDAO;

@WebServlet("/viewoffers")
public class ViewOffersServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			long id = Long.parseLong(request.getParameter("id"));
			System.out.println(request.getParameter("id"));
			ArrayList<Offer> offers = OfferDAO.getInstance().getJobOffers(id);
			if(offers == null){
				request.setAttribute("offers", "No offers for your job");
				System.out.println(offers.isEmpty());
			}
			else{
				request.setAttribute("offers", offers);
			}
			getServletContext().getRequestDispatcher("/viewoffers.jsp").forward(request, response);
		}
	}
}
