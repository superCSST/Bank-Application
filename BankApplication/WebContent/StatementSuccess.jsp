<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@page import="java.util.ArrayList" %>
<head>
<meta charset="ISO-8859-1">
<style>
h3{
font-family:courier;
font-size: 400%;
}
table{
font-family:Lucida Console;

}

body{

background-image:url('Images/bg2.jpg');
background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  text-align:center;
  color:black;
}
a:link,a:visited{
 background-color:#ff2b2b;
 color:white;
 font-family:verdana;
 font-size:140%;
 text-decoration:none;
 text-align:center;
 padding:10px 15px;
 display:inline-block;
 border-radius:12px;
 
}
a:hover,a:active{
background-color:#ebf5a2;
color:black;
}
</style>


<title>Statement Success</title>

</head>
<body >
<h3>Bank Statement</h3><br>
 <%
        session=request.getSession();
        ArrayList sal=(ArrayList)session.getAttribute("sal");
        ArrayList ral=(ArrayList)session.getAttribute("ral");
        ArrayList al=(ArrayList)session.getAttribute("al");
        
        
       %>
<table border=3 align=center >
      <thead>
          <tr>
             <th>Senders Account</th>
             <th>Receivers Account</th>
             <th>Transferred Amount</th>
             
          </tr>
      </thead>
 <tbody>
       
      <%  for(int i=0;i<sal.size();i++)
        {
            %> 
            <tr>
                <td><%=sal.get(i) %></td>
                <td><%=ral.get(i) %></td>
                <td><%="Rs."+al.get(i)+"/-" %></td>
                
            </tr>
            <%}
      
            %>
           </tbody>
        </table><br><br><br>
   
<a href="Home.html">Click here to Go Home</a>
</body>
</html>