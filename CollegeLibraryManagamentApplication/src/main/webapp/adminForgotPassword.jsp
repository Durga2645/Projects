<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	input 
	{
		border-radius:7px;
		border:2px solid black;
		padding:10px;
		font-size:15px;
	}
	.p1
	{
		font-size:25px;
		border:2px solid black;
		border-radius:5px;
		padding:5px;
		display:inline-block;
		margin-left:40px;
		color:white;
		background-image: linear-gradient(to right, #870000, #190a05);	
	}
	div
{
	padding:10px;
	width:350px;
	margin-left:auto;
	margin-right:auto;
	margin-top:180px;
	background-image: linear-gradient(to right, #870000, #190a05); 
}
	
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<p class="p1">Admin Forgot Password</p>
		<form method="post" action="./controller/adminForgotPassword">
			<input type="email" placeholder="Enter your email" name="adminEmail" size=30 required><br><br>
			<input type="submit" value="submit">
		</form>
	</div>
</body>
</html>