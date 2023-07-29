<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	input
	{
		width:220px;
		border-radius:5px;
		padding:5px;
		background-color:lightblue;
		font-size:20px;
		font-family:inherit;
		border:0;
		margin-left:15px;
	}
	input:hover,focus
	{
		background-image: linear-gradient(to right, #141e30, #243b55);
		color:white
	}

</style>
<title>Insert title here</title>
</head>
<body>
	<div style="margin-top:20px;">
		<form action="./searchBooks.jsp" target="frame4">
		<input type="submit" value="Search Library Books"><br><br>
		</form>
		
		<form  action="./controller/studentIssuedBooks" target="frame4" >
			<input type="submit" value="Issued Books"><br><br>
		</form>
		
	</div>

</body>
</html>