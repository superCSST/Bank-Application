package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid=request.getParameter("custid");
		String pwd=request.getParameter("pwd");
		
	   HttpSession session = request.getSession(true);
		try {
			Model m = new Model();
			    m.setCustid(custid);
			    m.setPwd(pwd);
			    boolean b= m.login();
			    if(b==true)
			    {   session.setAttribute("accno", m.getAccno());
			        session.setAttribute("email", m.getEmail());
			    	response.sendRedirect("/BankApplication/Home.html");
			    	
			    }
			    else
			    {
			    	response.sendRedirect("/BankApplication/Error.html");
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}

}
