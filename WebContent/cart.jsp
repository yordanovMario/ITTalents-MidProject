<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int p = 1; %>
<%

if(session.getAttribute("products") == null ){%>
	<h2>There are no ordered products in your cart.</h2>
<%} else {
	if(request.getParameter("paging") != null){
		int nextPage = Integer.parseInt(request.getParameter("paging"));
		p = p+nextPage;
		if(p <= 0){
			p = 1;
		}
	}
	ArrayList<Product> ordered = (ArrayList<Product>) session.getAttribute("products");

	if(10*(p-1)+10 >ordered.size()){
		p--;
	}
	if(ordered.isEmpty()){
		out.print("<h2>There are no ordered products in your cart.</h2>");
	}
	else{%>
		<table border="1">
		<%for(int i = 10*(p-1); i < 10*(p-1)+10; i++){ Product p = ordered.get(i);%>
			<tr>
				<td>
					<%= p.getName() %>
				</td>
				<td>
					<%= p.getQuantity() %>
				</td>
			</tr>
		<% } %>
		</table>
		<table>
			<tr>
				<td><form action="cart.jsp"><input type="submit" value="prev"><input type="hidden" name="paging" value="-1"></form></td>
				<td><form action="cart.jsp"><input type="submit" value="next"><input type="hidden" name="paging" value="1"></form></td>
			</tr>
		</table>
		
		
	<% } %>
<%} %>
</body>
</html>