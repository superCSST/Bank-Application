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
 * Servlet implementation class CheckBalance
 */
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet{
	private static final long serialVersionUID =1L;
	


	
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
	   int accno=(int) session.getAttribute("accno");
	  
	    
	   try {
		Model m=new Model();
		m.setAccno(accno);
		boolean b=m.checkBalance();
		
		
		
		
		if(b==true)
			{session.setAttribute("bal", m.getBal());
			 session.setAttribute("sub", "You had viewed your balance");
			 session.setAttribute("con", "Your balance is"+m.getBal());
			
			 ServletContext context = getServletContext();
		     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
			 rd.include(request,response);
		     System.out.println(request.getAttribute("Message"));
		     
		    
			  response.sendRedirect("/BankApplication/BalanceView.jsp");
			  
			}
		else
		{
			response.sendRedirect("/BankApplication/BalanceFail.jsp");
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	}
}
