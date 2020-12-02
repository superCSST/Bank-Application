package com.abc.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class GetStatement
 */
@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();
			int accno=(int) session.getAttribute("accno");
			Model m=new Model();
			m.setAccno(accno);
			
			ArrayList al=m.getStatement();
			ArrayList sal=m.sal;
			ArrayList ral=m.ral;
			if(al.isEmpty())
			{
				response.sendRedirect("/BankApplication/StatementFail.html");
			}
			else
			{   session.setAttribute("al", al);
			session.setAttribute("sal", m.sal);
			session.setAttribute("ral", m.ral);
			String text=
			         "<table width='100%' border='1' align='center'>"
			                + "<tr align='center'>"
			                + "<td><b>Senders Account <b></td>"
			                + "<td><b>Recievers Account<b></td>"
			                + "<td><b>Amount<b></td>"
			                + "</tr>";

			    for (int i=0;i<sal.size();i++) {
			                    
			                    text=text+"<tr align='center'>"+"<td>" + sal.get(i) + "</td>"
			                                + "<td>" + ral.get(i) + "</td>"+"<td>" + al.get(i) + "</td>"+"</tr>";

			                }
			    
			    session.setAttribute("sub", "Transfer statement");
				 session.setAttribute("con", text);
				
				 ServletContext context = getServletContext();
			     RequestDispatcher rd = context.getRequestDispatcher("/EmailSendingServlet");
				 rd.include(request,response);
				response.sendRedirect("/BankApplication/StatementSuccess.jsp");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
