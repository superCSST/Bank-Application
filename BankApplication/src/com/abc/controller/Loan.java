package com.abc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.abc.model.Model;

/**
 * Servlet implementation class Loan
 */
@WebServlet("/Loan")
public class Loan extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	HttpSession session = request.getSession();	
		int accno=(int) session.getAttribute("accno");
		try {
			Model m=new Model();
			m.setAccno(accno);
			boolean b=m.loan();
			
			if(b)
			{   session.setAttribute("name", m.getName());
			    session.setAttribute("email", m.getEmail());
			    session.setAttribute("sub", "Loan Application");
				session.setAttribute("con", "Mr."+m.getName()+", Thankyou for your interest in loan from ABC Bank.<br>Our executive will contact you on your email"+m.getEmail());
				
				 ServletContext context = getServletContext();
			     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
				 rd.include(request,response);
			     System.out.println(request.getAttribute("Message"));
			     response.sendRedirect("/BankApplication/LoanSuccess.jsp");
			    
				
			}
			else
			{
				response.sendRedirect("/BankApplication/LoanFail.html");
			}
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
