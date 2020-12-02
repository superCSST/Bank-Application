<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Success</title>
</head>
<body>
<h2>
<% session=request.getSession();
   out.println("Mr."+session.getAttribute("name")+", Thankyou for your interest in loan from ABC Bank.");
      out.println("<br>");
   out.println("Our Executive will contact you on your email-"+session.getAttribute("email"));
   


%>
</h2>

</body>
</html>