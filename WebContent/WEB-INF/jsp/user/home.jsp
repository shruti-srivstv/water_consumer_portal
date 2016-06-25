<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Water Consumer Portal</title>
</head>
<body>	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>	
	<script type="text/javascript" src="<c:url value="/resources/js/exporting.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/exporting2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/custom-chart.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/highstock.js"/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/js/chart.js" />"></script>	
	
	Welcome to Water Consumer Portal
	<br>
	<span> DATA : </span>
	<a href="<c:url value="/map"/>">View Map</a>
	<a href="<c:url value="/logout"/>">Logout</a>
	<br>
	<br>

	<select id ="gran" name="changeGranularitys" onmousedown="this.value='';" onchange="getChart(this.value);">
	  <option value='day'>Day</option>
	  <option value='week'>Week</option>
	  <option value='month'>Month</option>
	</select>
	<br>
	<br>
	Show your daily average : <input type="checkbox" id="useravg" name="Daily Average" onclick="dailyAvg();"/>
	<br>
	Show neighbourhood daily average : <input type="checkbox" id="localityavg" name="Local Average" onclick="localityAvg();"/>
	<div id="container"></div>
	<input type="button" value="Back" onclick="getDefaultChart()" id="back_btn" />
	
	<script>window.onload = getDefaultChart();</script>
	<input type="button" value="Show value" onclick="getDates()" id="show_btn" />
</body>
</html>