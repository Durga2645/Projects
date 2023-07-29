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
		margin-left:20px;
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
		margin-left:30%;
	}
	

</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${empty history}">
		<div>No history is available</div>
	</c:if>
	
	<c:if test="${! empty history}">
		<div>Displaying not returned Books</div><br>
		<table>
			<tr>
				<th>STUDENT ID</th>
				<th>STUDENT NAME</th>
				<th>BOOK ID</th>
				<th>BOOK NAME</th>
				<th>BOOK ISSUE DATE</th>
				<th>BOOK RETURNED DATE</th>
			</tr>
			<c:forEach var="allRecords" items="${history}">
				<tr>
				
				<c:forEach var="record" items="${allRecords}">
					<td>${record}</td>
				</c:forEach>
				</tr>
			</c:forEach>
		
		</table>
		
	</c:if>
	
	
</body>
</html>