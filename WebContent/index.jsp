<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreeAgents.eu :: Online platform that connects freelancers and employers</title>
</head>
<body>
<%HttpSession sess = request.getSession(false);
if (sess == null || sess.getAttribute("username") == null) { %>
<form action="LogIn.html" method="get">
<p align="right">
	<button type="submit">LOGIN</button>
</p>
</form>
<form action="SignUp.html" method="get">
<p align="right">
	<button type="submit">register</button>
</p>
</form>
<%} else { 
//String name = users.get(sess.getAttribute("username")).getName();%>
<%= session.getId() %>

<a href="profile.jsp">Hello, </a><% //response.getWriter().append(name); %>
<form action="profile" method="get">
<p align="right">
	<button type="submit">Profile</button>
</p>
</form>
<form action="logout" method="get">
<p align="right">
	<button type="submit">Log Out</button>
</p>
</form>
<%} %>



</body>
</html>