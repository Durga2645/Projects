<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
	th,td
	{
		padding:5px;
		font-size:20px;
		text-align:center;
	}
	tr:nth-child(even)
	{
		background-color:lightblue;
	}
	table
	{
		margin-left:100px;
	}
	div
	{	
		font-size:25px;
		text-align:center;
		padding:5px;
		background-image: linear-gradient(to right, #141e30, #243b55);
		color:white;
		width:30%;
		border-radius:5px;
		margin-left:35%;
	}
	

</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${empty bookList}">
		<div>No Books are Available</div>
	</c:if>
	
	<c:if test="${! empty bookList}">
		<div>Displaying all Books</div><br>
		<table>
			<tr>
				<th>BOOK ID</th>
				<th>BOOK NAME</th>
				<th>BOOK TITLE</th>
				<th>BOOK AUTHOR</th>
				<th>BOOK CATEGORY</th>
				<th>BOOK QUANTITY</th>
			</tr>
			<c:forEach var="book" items="${bookList}">
				<tr>
					<td>${book.bookId}</td>
					<td>${book.bookName}</td>
					<td>${book.bookTitle}</td>
					<td>${book.bookAuthor}</td>
					<td>${book.bookCategory}</td>
					<td>${book.bookQuantity}</td>
				</tr>
			</c:forEach>
		
		</table>
		
	</c:if>
	
	
</body>
</html>