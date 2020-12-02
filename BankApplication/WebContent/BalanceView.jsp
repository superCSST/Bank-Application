<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
h1{
font-family:courier;
font-size: 300%;
color:#144a7a;
}
h2{
font-family:verdana;
font-size: 230%;
color:#144a7a;
padding:13px;
}
h3{
font-family:verdana;
font-size: 130%;
color:#474234;
padding:10px;
}

body{

background-image:url(Images/bank-balance.jpg);
background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  color:black;
  
}


</style>
<title>BALANCE</title>
</head>
<body>
<h1 align="center">Balance Information</h1>
<%
session=request.getSession();
out.println("<h2>Your Balance is Rs."+session.getAttribute("bal")+"/-</h2");
out.println("<br><br>");
 
out.println("<h3>"+session.getAttribute("sub")+"</h3");
out.println("<br>");

out.println("<h3>"+session.getAttribute("con")+"</h3>");


 %>
</body>
</html>