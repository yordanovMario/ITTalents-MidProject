package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Offer;
import model.User;
import model.dao.OfferDAO;

/**
 * Servlet implementation class PostOfferServlet
 */
@WebServlet("/postoffer")
public class PostOfferServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
			if(request.getParameter("content") != null){
				long id = Long.parseLong(request.getParameter("id"));
				System.out.println(id);
				String content = (String) request.getParameter("content");
				int price = Integer.parseInt(request.getParameter("price"));
				Offer offer = new Offer(u.getId(), id, content, price);
				try {
					OfferDAO.getInstance().postOffer(offer);
				} catch (SQLException e) {
					System.out.println("Offer sending error - " + e.getMessage());
				}
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else{
				long id = Long.parseLong(request.getParameter("id"));
				System.out.println(id);
				request.setAttribute("id", id);
				getServletContext().getRequestDispatcher("/postoffer.jsp").forward(request, response);
			}
		}
	}
}
