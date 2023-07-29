<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./../registration.css"></link>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div style="box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;" class="div1">
			<br><a  href="./../index.jsp" class="a2">Login</a>
			<p class="p2">Student Registration form</p>
			<form method="post" action="./../controller/studentRegistration">
				<input type="text" placeholder="Enter your name" size="30" name="studentName" required><br><br>
				<input type="email" placeholder="${emailErrorMessage}" size="30"  name="studentEmail" required><br><br>
				<input type="password" placeholder="${passwordErrorMessage}" size="30" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" name="studentPassword" required><br>
				<p style="font-size:15px;">Password should contain atleast 8 characters and one number and lowercase and uppercase</p>
				<select name="studentdDepartment" required>
				<option disabled selected hidden>Department</option>
				<option >CSE</option>
				<option >ECE</option>
				<option >EEE</option>
				<option >MECH</option>
				<option >CIV</option>
				</select><br><br>
				<input type="tel" placeholder="${mobilenumberErrorMessage}" size="30" name="studentMobile" required><br><br>
				<input type="submit" value="Register" style="width:150px">		
			</form>			
		</div>
</body>
</html>