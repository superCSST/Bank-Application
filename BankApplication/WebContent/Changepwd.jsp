<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h2{
font-family:courier;
font-size: 250%;
text-align:center;
color:white;
}
label{
font-family:Arail;
font-size: 120%;
}

body{

background-image:url(Images/bg1.png);
background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  color:white;
  text-align:center;
}
input {
padding:10px 15px;
font-family:Trebuchet MS;
font-size:100%;
border-radius:8px;
}
.a{
padding:10px 25px;
font-family:Trebuchet MS;
font-size:120%;
border-radius:12px;
}
.a:hover{
background-color:#3b91bf;
color:white;
}

</style>
<title>Change Password</title>
</head>
<body>
<h2>Enter your New Password</h2><br><br>
<form action="PasswordChange">
<label><b>New Password</b></label><br>
<input type="password" name="npwd"/><br><br>
<label><b>Confirm New Password</b></label><br>
<input type="password" name="cnpwd"/><br><br>


<input class="a" type="submit" value="Change"/>
</form>
</body>
</html>