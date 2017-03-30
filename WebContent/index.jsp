<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FreeAgents.eu</title>
</head>
<body>
<%HttpSession sess = request.getSession(false); %>
<%if (sess == null || sess.getAttribute("user") == null) {%>

<form action="LogIn.html" method="get">
<p align="right">
	<button type="submit">LOGIN</button>
</p>
</form>
<form action="SignIn.html" method="get">
<p align="right">
	<button type="submit">register</button>
</p>
</form>
<%} else { %>
<form action="profile.jsp" method="get">
<p align="right">
	<button type="submit">PROFILE</button>
</p>
</form>
<form action="logout" method="get">
<p align="right">
	<button type="submit">LOGOUT</button>
</p>
</form>
<%} %>

</body>
</html>