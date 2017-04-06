package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.UserDAO;
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		boolean valid = true;
		String page = "SignUpFailed.html";
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		String passconf	= req.getParameter("password2");
		
		if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty() || passconf.isEmpty()){
			valid = false;
		}
	
		boolean second = true;
		boolean third = true;
		if(!pass.equals(passconf)){
			page = "SignUpPasswords.html";
			second = false;
		}
		if(UserDAO.getInstance().checkUser(user, email)){
			page = "SignUpDuplicates.html";
			third = false;
		}
		if(valid && second && third){
			page = "LogInSuccess.html";
			try {
				User u = new User(user, pass, email, fname, lname);
				System.out.println(u);
				UserDAO.getInstance().registerUser(u);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SignUp error - " + e.getMessage());
			}
			//Email sending code:
			String username = "freeagentseu@gmail.com";
		    String password = "freeagentseupass1357";
		    String result = null;
		    
		    try {
		         
		        Properties props = System.getProperties();
		        props.setProperty("mail.transport.protocol", "smtp");
		        props.setProperty("mail.host", "smtp.gmail.com");
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.port", "465");
		        props.put("mail.debug", "true");
		        props.put("mail.smtp.socketFactory.port", "465");
		        props.put("mail.smtp.socketFactory.class",
		                "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.smtp.socketFactory.fallback", "false");
		 
		        Session emailSession = Session.getInstance(props,
		                new javax.mail.Authenticator() {
		                    protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication("freeagentseu@gmail.com","freeagentseupass1357");
		                }
		        });
		 
		        emailSession.setDebug(true);
		        Message message = new MimeMessage(emailSession);
		        message.setFrom(new InternetAddress(
		                "freeagentseu@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse("yordanov.mario@gmail.com"));
		        message.setSubject("Welcome to freeagents.eu!");
		        message.setText("Welcome to our website! You can now log in");
		 
		        Transport transport = emailSession.getTransport("smtps");
		        transport.connect("smtp.gmail.com", username, password);
		        transport.sendMessage(message, message.getAllRecipients());
		 
		        result = "Successfully sent email";
		 
		       } catch (MessagingException e) {
		        result = "Unable to send email";
		       }
		  //End of email sending code.
		}

		RequestDispatcher rq = req.getRequestDispatcher(page);
		rq.forward(req, resp);

	}
	
}