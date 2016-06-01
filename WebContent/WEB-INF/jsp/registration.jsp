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
	<div style="margin-top: 60px">
		<form class="registration" id="formRegister" action="register" method="POST">
			<table>
			<tr>
				<td style="width: 50%">
					<fieldset>
						<label for="name">Household Id</label> 
						<input type="number" id="householdId" name="householdId"> 
						
						<label for="name">Building Id</label> 
						<input type="number" id="buildingdId" name="buildingdId">
		
						<label for="name">Smart Meter Id</label> 
						<input type="number" id="smartMeterdId" name="smartMeterdId"> 
						
						<label for="name">Zip Code</label> 
						<input type="text" id="zipcode" name="zipcode">
					</fieldset>
				</td>
				<td style="width: 50%; vertical-align: text-top;" >
					<fieldset>
						<label for="name">Username</label> 
						<input type="text" id="username" name="username"> 
							
						<label for="name">Password</label> 
						<input type="text" id="password" name="password">
						
						<span style="color: red">${message}</span>
						
						<button style="margin-top: 25px; height: 55px" type="submit">Register</button>
					</fieldset>
				</td>
			</tr>
			</table>
		</form>
	</div>
</body>

</html>
