<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%boolean logged = false;%>
<%if (session != null || session.getAttribute("user") != null) {
	logged = true;
}
if(!logged){
	response.sendRedirect("LogIn.html");
}
%>
<!DOCTYPE html>
	<html>
	<head>
		<title>${user.getFirstName()}'s profile in FreeAgents.eu</title>
	 	<link rel="stylesheet" href="post-job.css"/>
		<link rel="stylesheet" type="text/css" href="signup.css">
		<link href="https://fonts.googleapis.com/css?family=Oxygen:300,400,700" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<!-- header -->
		<header>
			<div class="flowe-header">
				<div class="flowe-logo"></div>
				<div class="flowe-header-menu"></div>
				<div class="header-menu">
					<a href="PostJob.html">Post Job</a>	
					<a href="browsejobs.jsp">Browse Job Offers</a>
					<a href="logout">Log Out</a>
					<div class="flowe-blue-button">
						<p class="flowe-signup-button">${user.getFirstName()}</p>
					</div>
				</div>
			</div>
		</header>
		<section>
		<div class="main-description">
		<div class="main-header">
		<p>${user.getFirstName()}&nbsp${user.getLastName()}</p>
		</div>
		<div class="tips">
		<div>
				<form method="POST" action="editdata">
					<p>First name</p><input type="text" name="firstname" value="${user.getFirstName()}"/><br>
					<p>Last name</p><input type="text" name="lastname" value="${user.getLastName()}"><br>
					<p>Job Title</p><input type="text" name="jobtitle" value="${user.getJobTitle()}"/><br>
					<p>Phone Number</p><input type="text" name="phone" value="${user.getPhone()}"/><br>
					<p>Per Hour Rate ($)</p><input type="number" name="perhourrate" placeholder="${user.getPerHourRate()}"/>
					<p>About me</p><textarea rows="4" cols="50" name="aboutme">${user.getAboutMe()}</textarea>
					<p>Portfolio</p><textarea rows="4" cols="50" name="portfolio">${user.getAboutMe()}</textarea>
					
					<input type="submit" id="post-job-btn" value="Edit my data" />
				</form>
			</div>
			<p>Find global talent when you need it. Stay flexible.</p>
			</div>
		</div>
		</section>
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
				<img src="Images/flowe-header-logo.png">
			</div>
			<p>&copy; 2017 Free Agents LTD. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>
