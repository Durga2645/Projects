<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="registration.css"></link>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="div1" style="box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;"><br>
		<a href="index.jsp" class="a2">Login</a><br>
		<p style="font-size:25px">Student Registration Form</p>
			<form method="post" action="./controller/studentRegistration">
				<input type="text" placeholder="Enter your name" size="30" name="studentName" required><br><br>
				<input type="email" placeholder="Enter your email" size="30" name="studentEmail" required><br><br>
				<input type="password" placeholder="Enter a password" size="30" name="studentPassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
				<p style="font-size:15px;">Password should contain atleast 8 characters and one number and lowercase and uppercase</p>
				<select name="studentDepartment" required>
				<option disabled selected hidden value="Department">Department</option>
				<option value="CSE">CSE</option>
				<option value="ECE">ECE</option>
				<option value="EEE">EEE</option>
				<option value="MECH">MECH</option>
				<option value="CIV">CIV</option>
				</select><br><br>
				<input type="tel" placeholder="Enter your mobile number" size="30" name="studentMobile" required><br><br>
				<input type="submit" value="Register" style="width:150px;background-color:white;color:black">		
			</form>			
		</div>
</body>
</html>