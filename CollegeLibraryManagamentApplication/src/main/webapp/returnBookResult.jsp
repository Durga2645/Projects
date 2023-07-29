<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<style>
	div
	{	
		font-size:25px;
		text-align:center;
		padding:5px;
		background-image: linear-gradient(to right, #141e30, #243b55);
		color:white;
		width:42%;
		border-radius:5px;
		margin-left:32%;
		margin-top:10%;
	}
	a
	{
		text-decoration:none;
		font-size:20px;
		padding:5px;
		background-color:white;
		border-radius:5px;
		color:black
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;">
			<c:if test="${returnBookResult eq 'Book is Not issued to that student'}">
			<p>Book is not issued to that student</p>
			</c:if>
			<c:if test="${returnBookResult eq 'Book details are Not available'}">
			<p>Book details are not available</p>
			</c:if>
			<c:if test="${returnBookResult eq 'Student details are Not available'}">
			<p>Student details are not available</p>
			</c:if>
			<c:if test="${fn:contains(returnBookResult,'Your fine is ::')}">
				<p>${returnBookResult}</p>
				<a href="./../controller/finePaid?studentId=${studentDto.studentId}&studentName=${studentDto.studentName}&bookId=${bookDto.bookId}&bookName=${bookDto.bookName}">PAID</a>
			</c:if>
			<c:if test="${finePaid eq 'fine paid successfully'}">
				<p>Book Returned Successfully</p>
			</c:if>
	</div>
</body>
</html>