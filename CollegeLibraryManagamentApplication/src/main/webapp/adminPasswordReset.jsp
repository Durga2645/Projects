<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./../login.css"></link>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<p class="p1">Password Reset</p>
		<form method="post" action="./../controller/adminPasswordReset">
			<input type="password" placeholder="Enter your new  password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" name="password1" size=30 required>
			<p style="font-size:15px">! Password should contains atleast 8 characters with one number and one lowercase and one uppercase</p><br>
			
			<input type="password" placeholder="Enter the above password again"  name="password2" size=30	required><br><br>
			<input type="submit" value="submit" style="width:100px"><br><br>
		</form>
	</div>
</body>
</html>