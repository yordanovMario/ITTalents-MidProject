<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreeAgents.eu :: Online platform that connects freelancers and employers</title>
</head>
<body>
<%HttpSession sess = request.getSession(false); %>
<%if (session == null || session.getAttribute("username") == null) { %>
<form action="login.html" method="get">
<p align="right">
	<button type="submit">LOGIN</button>
</p>
</form>
<form action="register.html" method="get">
<p align="right">
	<button type="submit">register</button>
</p>
</form>
<%} else { %>
<a href="profile.jsp">Hello, </a><% %>
<form action="profile" method="get">
<p align="right">
	<button type="submit">Profile</button>
</p>
</form>
<form action="LogOut.html" method="get">
<p align="right">
	<button type="submit">Log Out</button>
</p>
</form>
<%} %>



</body>
</html>