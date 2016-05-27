<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Update book details</title>
</head>
<body>
	<form:form modelAttribute="book">
			 <form:hidden path="id"/>
	 ISBN:   <form:input path="isbn"/>  		 <form:errors path="isbn"/>  		   <br/>
	 Title:  <form:input path="title"/>  		 <form:errors path="title"/>  		   <br/>
	 Date:   <form:input path="publishDate"/>    <form:errors path="publishDate"/>    <br/>
	 
	 <input type="submit" name="cancel" value="Back"/>
	 
	 <c:if test="${!empty book.id}">
	 	<input type="submit" name="remove" value="Remove"/>
	 </c:if>
	 
	 <input type="submit" name="save" value="Save"/>
	 
	</form:form>
</body>
</html>