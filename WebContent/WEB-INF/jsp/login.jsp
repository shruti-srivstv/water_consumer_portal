<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/main.css" />
<title>Water Consumer Portal</title>
</head>
<body background="<%=request.getContextPath()%>/resources/img/bg.png">
	<header class="semi-transparent-header">
		<div class="main-header">
			<img src="<%=request.getContextPath()%>/resources/img/logo.png"
				style="width: 20%" />
		</div>
	</header>
	<div style="margin-top: 100px">
		<form id="formLogin" action="login" method="POST">
			<fieldset>
				<label for="name">Username</label> <input type="text" id="username"
					name="username"> <label for="password">Password</label> <input
					type="password" id="password" name="password">
			</fieldset>
			<label for="name" style="color: red">${message}</label>
			<button type="submit">Sign In</button>
			<label for="password">First time here? <a
				href="<c:url value="/registration"/>">Activate your ID</a></label>
		</form>
	</div>
</body>

</html>
