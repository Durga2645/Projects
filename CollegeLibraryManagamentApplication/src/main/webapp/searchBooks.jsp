<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	input
	{
		width:300px;
		padding:10px;
		font-size:20px;
		border-radius:10px;
		border:2px solid black
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div style='text-align:center;background-image: linear-gradient(to right, #141e30, #243b55);width:35%;margin-left:400px;margin-top:20px;border-radius:10px'> 
		<div style="font-size:25px;color:white">Search Books</div><br>
		<form method="post" action="./controller/studentSearchBooks">
		<label style="font-size:20px;color:white"  for="search">Select One option : </label>
		<select id="search" name="search" required style="width:180px;padding:5px;border-radius:5px;font-size:20px;">
				<option disabled selected hidden value="search by">Serach by</option>
				<option value="Book Name">Book Name</option>
				<option value="Book Title">Book Title</option>
				<option value="Book Author">Book Author</option>
				<option value="Book Category">Book Category</option>
		</select><br><br>
		<input type="text" placeholder="Enter the text" name="type" required><br><br>
		<input type="submit" value="Add" style="width:150px;background-image: linear-gradient(to right, #FFD194, #D1913C);" ><br><br>
		</form>
	</div>
</body>
</html>