<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Your Homepage</title>
</head>
<body>
	<h3> Your water consumption goes here </h3>
	<br/>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1.0', {
        'packages' : [ 'corechart' ]
    });
 
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
 
    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {
 
        // Create the data table.    
        var data = google.visualization.arrayToDataTable([
                                                              ['User', 'Consumption(relevant metric)'],
                                                              <c:forEach items="${userconsumption}" var="entry">
                                                                  [ '${entry.key}', ${entry.value} ],
                                                              </c:forEach>
                                                        ]);
        // Set chart options
        var options = {
            'title' : 'Area-wise Top Seven Countries in the World',
            is3D : true,
            pieSliceText: 'label',
            tooltip :  {showColorCode: true},
            'width' : 900,
            'height' : 500
        };
 
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }
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