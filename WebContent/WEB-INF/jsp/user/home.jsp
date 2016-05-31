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
	Welcome to Water Consumer Portal	
	<br>
	<span>Name : ${user.username}</span><br>
	<span>User ID: ${user.neutralUser.userOid}</span><br>
	<span>Household ID: ${user.neutralUser.household.oid}</span><br>
	<span>Household ID: ${user.neutralUser.household.oid}</span><br>
</body>
</html>