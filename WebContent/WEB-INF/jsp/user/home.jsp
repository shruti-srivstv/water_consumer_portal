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
	<span>Building ID: ${user.neutralUser.household.building.oid}</span><br>
	<span>Zip Code: ${user.neutralUser.household.building.district.zipcode}</span><br>
	<span>Smart Meter ID: ${user.neutralUser.household.smartMeter.oid}</span><br>
	
	<br>
	<br>
	<br>
	Meter Reading
	<br>
	${user.neutralUser.household.smartMeter.meterReadings}
	
</body>
</html>