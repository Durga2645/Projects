<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	input
	{
		width:250px;
		padding:10px;
		font-size:20px;
		border-radius:10px;
		border:2px solid black
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<div style='text-align:center;background-image: linear-gradient(to right, #141e30, #243b55);width:35%;margin-left:400px;margin-top:30px;border-radius:10px'> 
		<div style="font-size:25px;color:white">Delete Book</div><br>
		<form method="post" action="./controller/deleteBook">
			<input type="number" placeholder="Enter the book id" name="bookId" required><br><br>
			<input type="text" placeholder="Enter the book name" name="bookName" required><br><br>
			<input type="submit" value="Delete" style="width:150px;background-image: linear-gradient(to right, #FFD194, #D1913C);"><br><br>
		</form>
	</div>
</body>
</html>