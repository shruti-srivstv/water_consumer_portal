<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/niceforms.js"></script>
 --%>	
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/highcharts.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/exporting.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/custom-chart.js" />"></script>
	<title>Your Homepage</title>
</head>
<body>
	<h3> Your water consumption goes here </h3>
	<br/>
	<script type="text/javascript" src="/js/themes/gray.js"></script>
	<%-- var contextPath = '<c:out value="${pageContext.request.contextPath}"/>'; --%>
	<div id="container" style="width:100%; height:400px;"></div>
	<script>
		$(function () { 
		    $('#container').highcharts({
		        chart: {
		            type: 'bar'
		        },
		        title: {
		            text: 'Fruit Consumption'
		        },
		        xAxis: {
		            categories: ['Apples', 'Bananas', 'Oranges']
		        },
		        yAxis: {
		            title: {
		                text: 'Fruit eaten'
		            }
		        },
		        series: [{
		            name: 'Jane',
		            data: [1, 0, 4]
		        }, {
		            name: 'John',
		            data: [5, 7, 3]
		        }]
		    });
		});
	</script>
	<br/>
	<br/>
	<form:form modelAttribute="user">
			 <form:hidden path="username"/>
	 Username:   <form:input path="username"/>  		 <form:errors path="username"/>  		   <br/>
	 <%-- Usage:  <form:input path="title"/>  		 <form:errors path="title"/>  		   <br/>
	 Date:   <form:input path="publishDate"/>    <form:errors path="publishDate"/>    <br/> --%>
	 
	 <input type="submit" name="cancel" value="Back"/>
	 
	 <c:if test="${!empty user.oid}">
	 	<input type="submit" name="remove" value="Remove"/>
	 </c:if>
	 
	 <input type="submit" name="save" value="Save"/>
	 
	</form:form>
</body>
</html>