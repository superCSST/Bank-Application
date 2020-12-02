package com.abc.controller;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;


/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 * 
 * @author www.codejava.net
 * 
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
		
		System.out.println("inside init");
		
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// reads form fields
		

		String resultMessage = "";
		HttpSession session = request.getSession();
		String email=(String) session.getAttribute("email");
		String subject = (String) session.getAttribute("sub");
		String content=(String) session.getAttribute("con");
		System.out.println("inside service");

		try {
			System.out.println("inside try");
			Model m=new Model();
			System.out.println(email);
			m.setRecipient(email);
			m.setSubject(subject);
			m.setContent(content);
			m.sendEmail(host, port, user, pass);
			System.out.println("model problem");
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {
			request.setAttribute("Message", resultMessage);
			
			//getServletContext().getRequestDispatcher("/BalanceView.jsp").forward(
				//	request, response);
		}
	}
}