package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;

/**
 * Servlet implementation class ProductsServlet
 */
@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product = request.getParameter("product");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		System.out.println("Product" + product +" bought with quantity " + quantity);
		//add to session
		HttpSession session = request.getSession();
		if(session.getAttribute("products") == null){
			session.setAttribute("products", new ArrayList<Product>());
		}
		ArrayList<Product> ordered = (ArrayList<Product>) session.getAttribute("products");
		ordered.add(new Product(product, quantity));
		//redirect to same jsp
		response.sendRedirect("products.jsp");
	}

}
