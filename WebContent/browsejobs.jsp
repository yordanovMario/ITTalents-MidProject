<%@page import="java.util.TreeSet"%>
<%@page import="model.Job"%>
<% TreeSet<Job> jobs = (TreeSet<Job>)request.getAttribute("jobs"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Browse all jobs :: FreeAgents.eu</title>
		<link rel="stylesheet" href="css.css" /> 
		<link rel="stylesheet" href="signup.css"/>
		<link rel="stylesheet" href="post-job.css"/>
		<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
</head>
<body>
		<header>
			<div class="flowe-header">
				<a href="index.jsp">
					<div class="flowe-logo"></div>
				</a>
				<div class="flowe-header-menu"></div>
				<div class="header-menu">
					<a href="postjob.jsp">Post Job</a>	
					<a href="#">Browse Job Offers</a>
					<a href="logout">Log Out</a>
					<div class="flowe-blue-button">
						<p class="flowe-signup-button"><a href="profile.jsp"><%=session.getAttribute("name") %></a></p>
					</div>
				</div>
			</div>
		</header>
		<div id="post-job">
			<h2 id="search-offers">Search offers</h2>
			<div class="post-job search-job">
				<form method="GET" action="browsejobs">
					<!-- select categories from database -->
					<select name="category" class="categories">
					  <option value="">Select Category</option>
					  <option value="1">Sales and Marketing</option>
					  <option value="2">Web development</option>
					  <option value="3">Moblie soft development</option>
					  <option value="4">Social Media</option>
					  <option value="5">Business Support</option>
					  <option value="6">Copy writting</option>
					  <option value="7">Translation</option>
					  <option value="8">Video making/editing</option>
					  <option value="9">Photo retouch</option>
					  <option value="10">Audio redacting</option>
					  <option value="11">Web design</option>
					  <option value="12">Logo design</option>
					  <option value="13">Banner design</option>
					  <option value="14">SEO</option>
					  <option value="15">Tutorials</option>
					  <option value="16">Administration</option>
					</select>
					<select name="sort" class="categories">
					  <option value="">Select Budjet order</option>
					  <option value="2">Ascending</option>
					  <option value="3">Descending</option>
					</select>
					
					<input type="submit" id="post-job-btn" value="Show Results" />
				</form>
			</div>

				<%for(Job j : jobs){ %>
					<%=j.toString()%>
				<%}%>
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
					<a href="index.jsp">					
						<img src="logo.png">
					</a>
				</div>
				<p>&copy; 2017 Free Agents LTD. All rights reserved.</p>
			</div>
		</footer>
</body>
</html>