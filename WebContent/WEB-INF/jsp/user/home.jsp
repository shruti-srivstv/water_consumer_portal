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
<script type="text/javascript">
	var data;
	function amin() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				data = JSON.parse(xhttp.responseText);
				document.getElementById("nameS").innerHTML = data.name;
				chart();
			}
		};
		xhttp.open("GET", 'getConsumption?from='+document.getElementById("startDate").value+'&to='+document.getElementById("endDate").value, true);
		xhttp.setRequestHeader('Content-Type','application/json');
		xhttp.send();
	}
	function chart() {
		var meters = data.meters;
		var html = "";
		for (var i=0; i<meters.length; i++) {
			html += "<span>" + meters[i].totalConsumption + "</span><br>";
		}
		document.getElementById("nameS").innerHTML = html;
	}
</script>
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
	<button type="button" onclick="amin()">Amin</button>
	<br>
	Meter Reading
	<br>
	<form id="formMeter" action="meterReading" method="GET">
			<fieldset>
				<label for="startDate">Start Date</label> 
				<input type="date" id="startDate" name="startDate"> 
				<label for="endDate">End Date</label>
				<input type="date" id="endDate" name="endDate">
			</fieldset>
			<button type="submit">Send</button>
		</form>
	<br>
	<br>
	<div id="results">
		My Name is: <span id="nameS"></span>
	</div>
	
	
	
</body>
</html>