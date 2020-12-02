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
 * Servlet implementation class PasswordChange
 */
@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String npwd=request.getParameter("npwd");
		String cnpwd=request.getParameter("cnpwd");
		HttpSession session = request.getSession();
		   int accno=(int) session.getAttribute("accno");
		   
		   try {
			Model m=new Model();
			m.setAccno(accno);
			
			if(npwd.equals(cnpwd) )
			 {
				m.setPwd(npwd);
				boolean b=m.passwordChange();
				if(b==true)
				{   
					session.setAttribute("sub", "Password Changed");
					 session.setAttribute("con", "Your Password Changed successfully\nIf not authorised by you kindly report ");
					
					 ServletContext context = getServletContext();
				     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
					 rd.include(request,response);
				     System.out.println(request.getAttribute("Message"));
				     
					response.sendRedirect("/BankApplication/PwdChangeSuccess.html");
				}
				else
				{
					response.sendRedirect("/BankApplication/PwdChangeFail.html");
					
				}
			}
			else
			{
				System.out.println("not equals");
				response.sendRedirect("/BankApplication/PwdChangeFail.html");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		
		
	}

}
