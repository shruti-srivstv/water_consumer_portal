<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Water Consumer Portal</title>
</head>
<body>
	<table border="1">
		<tr> <th>OID</th> <th>Username</th> <th>Password</th></tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td><a href="<c:url value="/users/${user.oid}"/>">${user.oid}</a></td>
				<td>${user.username}</td>
				<td>${user.password}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="<c:url value="users/create"/>">Create new</a>
</body>
</html>