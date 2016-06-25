<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<head>
	<title>Google Map Hello World Example</title>
	
	</head>
	
	<body>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
		<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/map.js" />"></script>
		<script>window.onload = listUser();</script>
		
		<div id="map_container"></div>
		
	</body>
</html>