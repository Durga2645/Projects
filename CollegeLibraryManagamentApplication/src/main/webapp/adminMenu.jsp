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
		<form action="./addBook.jsp" target="frame4">
		<input type="submit" value="Add Book"><br><br>
		</form>
		
		<form  action="./deleteBook.jsp" target="frame4" >
			<input type="submit" value="Delete Book"><br><br>
		</form>
		
		<form action="./updateSelectBook.jsp" target="frame4">
			<input type="submit" value="Update Book"><br><br>
		</form>
		<form action="./issueBook.jsp" target="frame4">
			<input type="submit" value="Issue Book"><br><br>
		</form >
		<form action="./returnBook.jsp" target="frame4">
			<input type="submit" value="Return Book"><br><br>
		</form>
		<form target="frame4" action="./controller/viewAllBooks">
			<input type="submit" value="View all Books"><br><br>
		</form>
		<form target="frame4" action="./controller/viewAllStudents">
			<input type="submit" value="View all Students"><br><br>
		</form>
		<form target="frame4" action="./controller/viewAllPendingBooks">
			<input type="submit" value="View all Pending Books"><br><br>
		</form>
		<form target="frame4" action="./controller/viewAllHistory">
			<input type="submit" value="View all History"><br><br>
		</form>
	</div>

</body>
</html>