<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	td,th{
		font-size:20px;
		padding:5px;
	}
	th
	{
		background-color:lightblue;
	}
	table
	{
		margin-left:100px;
	}
	.div1
	{	
		font-size:25px;
		text-align:center;
		padding:5px;
		background-image: linear-gradient(to right, #141e30, #243b55);
		color:white;
		width:40%;
		border-radius:5px;
		margin-left:32%;
		margin-top:5%;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<c:if test="${empty bookDetails}">
		<div class="div1">Entered Book details are not available</div>
	</c:if>
	
	<c:if test="${! empty bookDetails}">
		<table>
			<tr>
				<th>BOOK ID</th>
				<th>BOOK NAME</th>
				<th>BOOK TITLE</th>
				<th>BOOK AUTHOR</th>
				<th>BOOK CATEGORY</th>
				<th>BOOK AUTHOR</th>			
			</tr>
			<tr>
				<td>${bookDetails.bookId}</td>
				<td>${bookDetails.bookName}</td>
				<td>${bookDetails.bookTitle}</td>
				<td>${bookDetails.bookAuthor}</td>
				<td>${bookDetails.bookCategory}</td>
				<td>${bookDetails.bookQuantity}</td>
			</tr>
		</table><br>
		<div style='text-align:center;background-image: linear-gradient(to right, #141e30, #243b55);width:35%;margin-left:400px;margin-top:10px;border-radius:10px'> 
		<div style="font-size:25px;color:white">Update Book</div><br>
		<form method="post" action="./../controller/updateBook?bookId=${bookDetails.bookId}">
			<input type="text" placeholder="Enter the book name" name="bookName" value="${bookDetails.bookName}" ><br><br>
			<input type="text" placeholder="Enter the book title" name="bookTitle" value="${bookDetails.bookTitle}"><br><br>
			<input type="text" placeholder="Enter the book author" name="bookAuthor" value="${bookDetails.bookAuthor}" ><br><br>
			<input type="text" placeholder="Enter the book category" name="bookCategory" value="${bookDetails.bookCategory}" ><br><br>
			<input type="number" placeholder="Enter the book quantity" name="bookQuantity" min=1 value="${bookDetails.bookQuantity}"><br><br>
			<input type="submit" value="update Book" style="width:150px;background-image: linear-gradient(to right, #FFD194, #D1913C);"><br><br>
		</form>
	</div>
	
		
	</c:if>
	
</body>
</html>