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
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        int accno = (int)session.getAttribute("accno");
        
        
        String samt=request.getParameter("amt");
        String rraccno=request.getParameter("raccno");
        
        
       int amt= Integer.parseInt(samt);
       int raccno=Integer.parseInt(rraccno);
       
       try {
		Model m=new Model();
		m.setAccno(accno);
		m.setBal(amt);
		m.setRaccno(raccno);
	    boolean b=m.transfer();	
	    if(b)
	    {   session.setAttribute("sub", "Transfer Successfull");
		 session.setAttribute("con", "Your Account no "+m.getAccno()+" has been debited with "+m.getBal()+"<br>Transfer successful to "+m.getRaccno());
			
		 ServletContext context = getServletContext();
	     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
		 rd.include(request,response);
	     System.out.println(request.getAttribute("Message"));
	    	response.sendRedirect("/BankApplication/TransferSuccess.html");
	    }
	    else
	    {
	    	System.out.println("7");
	    	session.setAttribute("sub", "Transfer Failed");
			 session.setAttribute("con", "Transfer Failed\nKindly check with Account no and Transfer amount");
				
			 ServletContext context = getServletContext();
		     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
			 rd.include(request,response);
		     System.out.println(request.getAttribute("Message"));
	    	response.sendRedirect("/BankApplication/TransferFail.html");
	    }
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
        
        
        
        
        
	}

}
