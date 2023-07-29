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
		margin-left:30px;
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
	
	<c:if test="${empty allStudents}">
		<div>No Students are Available</div>
	</c:if>
	
	<c:if test="${! empty allStudents}">
		<div>Displaying all Students</div><br>
		<table>
			<tr>
				<th>STUDENT ID</th>
				<th>STUDENT NAME</th>
				<th>STUDENT EMAIL</th>
				<th>STUDENT DEPARTMENT</th>
				<th>STUDENT MOBILENUMBER</th>
			</tr>
			<c:forEach var="student" items="${allStudents}">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.studentName}</td>
					<td>${student.studentEmail}</td>
					<td>${student.studentDepartment}</td>
					<td>${student.studentMobilenumber}</td>
				</tr>
			</c:forEach>
		
		</table>
		
	</c:if>
	
	
</body>
</html>