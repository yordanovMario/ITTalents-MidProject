<%@page import="model.Job"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%boolean logged = false;
  ArrayList<Job> jobs = (ArrayList<Job>) request.getAttribute("jobs");%>
<%if (session != null || session.getAttribute("user") != null) {
	logged = true;
}
if(!logged){
	response.sendRedirect("LogIn.html");
}
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Post Job :: FreeAgents.eu</title>
		<link rel="stylesheet" href="post-job.css"/>
		<link rel="stylesheet" type="text/css" href="signup.css">
		<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
	</head>
	<body>
		<header>
			<div class="flowe-header">
				<div class="flowe-logo"></div>
				<div class="flowe-header-menu"></div>
				<div class="header-menu">
					<a href="postjob.jsp">Post Job</a>
					<a href="browsejobs.jsp">Browse Job Offers</a>
					<a href="logout">Log Out</a>
					<div class="flowe-blue-button">
						<p class="flowe-signup-button">${user.getFirstName()}</p>
					</div>
				</div>
			</div>
		</header>
		<div id="post-job">
			<h2>Post Job</h2>
			<div class="post-job">
				<table>
				<%for(Job j : jobs){ %>
				<tr><td><%=j.getTitle()%></td></tr>
				<tr><td><%=j.getDescription()%></td></tr>
				<tr><td><%=j.getBudget()%></td></tr>
				<tr><td><%=j.getEmployer()%></td></tr>
				<tr><td>
					<form action="sendoffer" method="post">
						<input type="hidden" value=<%=j.getId() %> name="quantity">
						<input type="submit" value="Send Offer">
					</form>
				</td></tr>
				<%} %>
				</table>
			</div>
			<div class="post-job-account">
			</div>
		</div>
		<footer>
		<div class="flowe-footer-menu">
				<p>Terms Of Service</p>	
				<p>Privacy Policy</p>
				<p class="flowe-blog">Blog</p>
				<p>Careers</p>
				<p class="flowe-faq">FAQ</p>
 		</div>
		<div class="flowe-footer">
			<div class="flowe-logo-footer"> 
				<img src="logo.png">
			</div>
			<p>&copy; 2017 Free Agents LTD. All rights reserved.</p>
		</div>
	</footer>
	</body>
</html>