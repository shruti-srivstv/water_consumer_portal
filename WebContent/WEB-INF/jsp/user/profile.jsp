<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Water Consumer Portal</title>

<link rel="stylesheet" href="<c:url value="/resources/js/bootstrap.min.css" />">
<link href="<c:url value="/resources/js/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/js/dashboard.css"/>" rel="stylesheet">
</head>
<body>	

	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>	
	<script type="text/javascript" src="<c:url value="/resources/js/exporting.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/exporting2.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/custom-chart.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/highstock.js"/>"></script>	
	<script type="text/javascript" src="<c:url value="/resources/js/chart.js" />"></script>	
	


    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="http://getbootstrap.com/examples/dashboard/#">Water Consumer Portal</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="/map"/>">Map</a></li>
            <li><a href="http://getbootstrap.com/examples/dashboard/">User Profile</a></li>
            <li><a  href="<c:url value="/logout"/>">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="http://getbootstrap.com/examples/dashboard/#">Overview <span class="sr-only">(current)</span></a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="<c:url value="/map"/>">Map</a></li>
            <li><a href="http://getbootstrap.com/examples/dashboard/">User Profile</a></li>
            <li><a  href="<c:url value="/logout"/>">Logout</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="page-header">User Details</h2>

          
              
		<div class="canvas-wrapper">              
              <div style="width: 100px; float: left;"><span>Name</span></div><div style="float: left;">: ${user.username}</div>
              <br>
              <br>
              <div style="width: 100px; float: left;"><span>Household ID</span></div><div style="float: left;">: ${user.neutralUser.household.oid}</div>
              <br>
              <br>
              <div style="width: 100px; float: left;"><span>Address</span></div><div style="float: left;">: ${user.neutralUser.household.building.address}</div>
              <br>
              <br>
              <div style="width: 100px; float: left;"><span>Zip Code</span></div><div style="float: left;">: ${user.neutralUser.household.building.district.zipcode}</div>              
              <br>              
            </div>
       

		<script src="./Dashboard Template for Bootstrap_files/jquery.min.js"></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    	<script src="./Dashboard Template for Bootstrap_files/bootstrap.min.js"></script>
    	<script src="./Dashboard Template for Bootstrap_files/holder.min.js"></script>
</body>
</html>