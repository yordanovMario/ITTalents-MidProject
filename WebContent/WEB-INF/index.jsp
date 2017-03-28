<%@page import="javax.security.auth.login.LoginContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<% 
	if(session.getAttribute("username") == null){
		response.sendRedirect("login.html");
	}
	%>
	Welcome ${username}

	<h1>FreeAgents.eu</h1>
	<div id="index">
		<a href="signup.html">Register</a>
		<br>
		<a href="login.html">Log in</a>
		<br>
		<a href="PostJob.html">Post Job</a>
		<br>
		<a href="SendMessage.html">Send Message</a>
		<br>
		<a href="PostFeedback.html">Post Feedback</a>
		<br>
		<a href="PostOffer.html">Post Offer To A Job</a>
		<br>
		<a href="BrowseJobs.html">Browse Jobs</a>
	</div>
</body>
</html>