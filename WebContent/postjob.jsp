<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%boolean logged = false;
if (session.getAttribute("logged") != null || session.getAttribute("user") != null) {
	logged = true;
}%>
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
				<form method="POST" action="postJob">
					<input type="text" name="title" placeholder="Title" required="required"/>
					<!--<input type="textarea" name="description" placeholder="Description" required="required"/> -->
					<textarea rows="4" cols="50" name="description" placeholder="Enter description here..."></textarea>
					<input type="number" name="budget" placeholder="Budget" required="required"/>
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
					<select name="reqExp" class="categories">
					  <option value="">Select Experience Level</option>
					  <option value="1">Beginner</option>
					  <option value="2">Advanced</option>
					  <option value="3">Expert</option>
					</select>
					
					<input type="submit" id="post-job-btn" value="Post Job" />
				</form>
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