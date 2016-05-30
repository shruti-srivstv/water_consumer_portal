<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Your Homepage</title>
</head>
<body>
	<h3> Your data goes here </h3>
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