<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="login.css"></link>
<meta charset="ISO-8859-1">
<title>Student login page</title>
</head>
<body>
	<div style="box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;">
		<a class="a1" href="index.jsp">Student login</a><br><br>
		<p class="p1">Admin Login</p>
		<form method="post" action="./controller/adminLogin">
			<input type="email" placeholder="Enter your email" name="adminEmail" size=30 required><br><br>
			<input type="password" placeholder="Enter your password" name="adminPassword" size=30 required><br><br><br>
			<input type="submit" value="login" style="width:100px"><br><br>
		</form>
		<a href="adminForgotPassword.jsp">Forgot password?</a><br><br>
		<p style="text-align:center">New User? <a href="adminRegistration.jsp">Register Here</a></p>
		
	</div>
</body>
</html>