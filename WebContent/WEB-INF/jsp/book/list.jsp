<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Ex1 - Spring MVC w/ JPA</title>
</head>
<body>
	<table border="1">
		<tr> <th>ISBN</th> <th>Title</th> <th>Publish Date</th></tr>
		<c:forEach var="book" items="${bookList}">
			<tr>
				<td><a href="<c:url value="/books/${book.isbn}"/>">${book.isbn}</a></td>
				<td>${book.title}</td>
				<td><fmt:formatDate type="date" value="${book.publishDate}"/></td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="books/create"/>">Create new</a>
</body>
</html>