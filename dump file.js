dateTimeLabelFormats: { // don't display the dummy year
	            	second: '%H:%M',
	            	minute: '%H:%M',
	            	hour: '%H:%M',
	            	day: '%e %b'
	            	
	            }










@Controller
@RequestMapping("/reportController")
public class ReportController {
	
	@Autowired
	ReportImpl reportImpl;
	
	public void setReportImpl(ReportImpl reportImpl) {
		this.reportImpl = reportImpl;
	}

	public ReportController() {
		super();
		System.out.println("inside Reportconstructor"); 
		}

	@RequestMapping(value = "/getreport", method = RequestMethod.GET)
	@ResponseBody
	 public Object getReportData()
    {
		System.out.println("inside report controller : get Report");
		List<ReportModel> reportModel =  reportImpl.getReportModels();
		Object json = null;
		ObjectMapper objectMapper = new ObjectMapper();
		  try {
			  
			  //display to console
			  json = objectMapper.readValue(
					     objectMapper.writeValueAsString(reportModel), Object.class);
		  
		  }catch(JsonGenerationException e)
		  {
			  e.printStackTrace();
		  } catch (JsonMappingException e)
		  {
			   e.printStackTrace();
		  } catch (IOException e) 
		  {
			   e.printStackTrace();
		  } 
		  
		  return json;
		 
	
    }
}


























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
	<script type="text/javascript"		src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/highcharts.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/exporting.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/exporting2.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/custom-chart.js" />"></script>
	<script type="text/javascript"      src="<c:url value="/resources/js/highstock.js"/>"></script>
	<%-- <script type="text/javascript"		src="<c:url value="/resources/js/chart.js" />"></script> --%>
	<script type="text/javascript"		src="<c:url value="/resources/js/chart2.js" />"></script>
	
	<script type="text/javascript">
	function dataC(startDate, endDate) {
		alert("inside datac");
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var data = JSON.parse(xhttp.responseText);
				chart(data);
			}
		};
		/*  */
		xhttp.open("GET" , 'getConsumption?from=' + startDate + '&to=' + endDate, true);
		xhttp.setRequestHeader('Content-Type', 'application/json');
		xhttp.send();
	}
	var mySeries = [];
	var myLabelSeries = [];
	function chart(data) {
		var html = "";
		/* for (var i = 0; i < meters.length; i++) {
			var a = meters[i].readingDateTime;
			var b = meters[i].totalConsumptionAdjusted;
			mySeries[i] = [a,b];
			html += "<span>" + mySeries[i] +"</span><br>";
		} */
		/* html += "<span>" + mySeries +"</span><br>"; */
		document.getElementById("nameS").innerHTML = data;
		
		$('#container').highcharts("StockChart", {
	        chart: {
	            alignTicks: false
	        },
	        title: {
	            text: 'Household Consumer'
	        },
	        rangeSelector:{
	                selected: 1
	        },
	        xAxis: {
	            type: 'datetime',
	            labels: {
	            	 formatter: function() {
	                 return Highcharts.dateFormat('%a %d %b', this.value);
	            	 }
	            }
	        },
	        yAxis: {
	        	min:0,
	            title: {
	                text: 'Water Consumption (m3)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	        	type: 'column',
	            name: 'Data',
	        	data : data,
	        	dataGrouping: {
                    units: [[
                        'week', // unit name
                        [1] // allowed multiples
                    ], [
                        'month',
                        [1, 2, 3, 4, 6]
                    ]]
                }
	        }]
	    });
	}
	</script>
	
	<script type="text/javascript">
		var minDate = new Date($(minDate));
		var maxDate = $(maxDate);
		alert("minDate = " + minDate);
		window.onload = getChart(minDate,maxDate);
	</script>

	Welcome to Water Consumer Portal
	<br>
	<span>Name : ${user.username}</span>
	<br>
	<span> MinDate : ${minDate }</span>
	<br>
	<span>User ID: ${user.neutralUser.userOid}</span>
	<br>
	<span>Household ID: ${user.neutralUser.household.oid}</span>
	<br>
	<span>Building ID: ${user.neutralUser.household.building.oid}</span>
	<br>
	<span>Zip Code:
		${user.neutralUser.household.building.district.zipcode}</span>
	<br>
	<span>Smart Meter ID:
		${user.neutralUser.household.smartMeter.oid}</span>
	<br>
	<span> DATA : </span>
	<a href="<c:url value="/map"/>">View Map</a>
	<br>
	<br>

	<br> Meter Reading
	<br>
	<form id="formMeter" action="meterReading" method="GET">
		<fieldset>
			<label for="startDate">Start Date</label> <input type="date"
				id="startDateInput" name="startDate"> <label for="endDate">End
				Date</label> <input type="date" id="endDateInput" name="endDate">
		</fieldset>
		<button type="button" onclick="dataC(startDateInput.value, endDateInput.value)">Send</button>
		
	</form>
	<br>
	<br>
	<div id="results">
		My Name is: <span id="nameS"></span>
	</div>
	<div id="container"></div>
	<div id="msg"></div>
    <div id="chart2">
    </div>


</body>
</html>











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
	<script type="text/javascript"		src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/highcharts.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/exporting.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/exporting2.js" />"></script>
	<script type="text/javascript"		src="<c:url value="/resources/js/custom-chart.js" />"></script>
	<script type="text/javascript"      src="<c:url value="/resources/js/highstock.js"/>"></script>
	<script type="text/javascript"      src="<c:url value="/resources/js/usdeur.js"/>"></script>
	<%-- <script type="text/javascript"		src="<c:url value="/resources/js/chart.js" />"></script> --%>
	<script type="text/javascript"		src="<c:url value="/resources/js/chart2.js" />"></script>
	
	<script type="text/javascript">
	function dataC(startDate, endDate) {
		alert("inside datac");
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var data = JSON.parse(xhttp.responseText);
				chart(data);
			}
		};
		/*  */
		xhttp.open("GET" , 'http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json', true);
		/* xhttp.setRequestHeader('Content-Type', 'application/json'); */
		xhttp.send();
	}
	var mySeries = [];
	var myLabelSeries = [];
	function chart(data) {
		var html = "";
		/* for (var i = 0; i < meters.length; i++) {
			var a = meters[i].readingDateTime;
			var b = meters[i].totalConsumptionAdjusted;
			mySeries[i] = [a,b];
			html += "<span>" + mySeries[i] +"</span><br>";
		} */
		/* html += "<span>" + mySeries +"</span><br>"; */
		document.getElementById("nameS").innerHTML = data;
		
		$('#container').highcharts( {
	        chart: {
	            alignTicks: false
	        },
	        title: {
	            text: 'Household Consumer'
	        },
	        rangeSelector:{
	                selected: 1
	        },
	       /*  xAxis: {
	            type: 'datetime',
	            labels: {
	            	 formatter: function() {
	                 return Highcharts.dateFormat('%a %d %b', this.value);
	            	 }
	            }
	        },
	        yAxis: {
	        	min:0,
	            title: {
	                text: 'Water Consumption (m3)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        }, */
	        series: [{
	        	type: 'column',
	            name: 'Data',
	        	data : data,
	        	dataGrouping: {
                    units: [[
                        'week', // unit name
                        [1] // allowed multiples
                    ], [
                        'month',
                        [1, 2, 3, 4, 6]
                    ]]
                }
	        }]
	    });
	}
	</script>
	
	<!-- <script type="text/javascript">
		var minDate = new Date($(minDate));
		var maxDate = $(maxDate);
		alert("minDate = " + minDate);
		window.onload = getChart(minDate,maxDate);
	</script> -->

	Welcome to Water Consumer Portal
	<br>
	<span>Name : ${user.username}</span>
	<br>
	<span> MinDate : ${minDate }</span>
	<br>
	<span>User ID: ${user.neutralUser.userOid}</span>
	<br>
	<span>Household ID: ${user.neutralUser.household.oid}</span>
	<br>
	<span>Building ID: ${user.neutralUser.household.building.oid}</span>
	<br>
	<span>Zip Code:
		${user.neutralUser.household.building.district.zipcode}</span>
	<br>
	<span>Smart Meter ID:
		${user.neutralUser.household.smartMeter.oid}</span>
	<br>
	<span> DATA : </span>
	<a href="<c:url value="/map"/>">View Map</a>
	<br>
	<br>

	<br> Meter Reading
	<br>
	<form id="formMeter" action="meterReading" method="GET">
		<fieldset>
			<label for="startDate">Start Date</label> <input type="date"
				id="startDateInput" name="startDate"> <label for="endDate">End
				Date</label> <input type="date" id="endDateInput" name="endDate">
		</fieldset>
		<button type="button" onclick="dataC(startDateInput.value, endDateInput.value)">Send</button>
		
	</form>
	<script> 
	window.onload = dataC("","");
	</script>
	<br>
	<br>
	<div id="results">
		My Name is: <span id="nameS"></span>
	</div>
	<div id="container"></div>
	<div id="msg"></div>
    <div id="chart2">
    </div>


</body>
</html>




,
            drilldown: {
                series: [{
                    name: 'Microsoft Internet Explorer',
                    id: 'Microsoft Internet Explorer',
                    data: perdaydata
                    	]
               }






               var chart = this, date = e.point.x;

               if (gran == "day") {
                        	alert("date = " + e.point.x + ", formatted = " + Highcharts.dateFormat('%Y-%m-%d', e.point.x));
                            getperdaydata(e.point.x);
                        }
                        chart.addSeriesAsDrilldown(e.point, {
                            name: "Per day data",
                            data: perdaydata,
                            
                        });





#drill down option
events: {
                    drilldown: function (e) {
                    	
                    	alert("inside drilldown");
                        if (gran == "day") {
                        	alert("date = " + e.point.x + ", formatted = " + Highcharts.dateFormat('%Y-%m-%d', e.point.x));
                            getperdaydata(e.point.x);
                        }
                        chart.addSeriesAsDrilldown(e.point, {
                            name: "Per day data",
                            data: perdaydata,
                            
                        });
                    },
                }




                events: {
                    drilldown: function (e) {
                    	
                    	alert("inside drilldown");
                        if (gran == "day") {
                        	alert("date = " + e.point.x + ", formatted = " + Highcharts.dateFormat('%Y-%m-%d', e.point.x));
                            getperdaydata(e.point.x);
                        }
                        chart.addSeriesAsDrilldown(e.point, {
                            name: "Per day data",
                            data: perdaydata,
                            
                        });
                    },
                }


                drilldown: {
                series: []
            }




            for (i = 0; i < data.length; i = i + 5) {
			var contentString = ""
			var infowindow = new google.maps.InfoWindow({
				content : contentString
			});
			var marker = new google.maps.Marker({
				position : latlng,
				map : map,
				title : "User"
					
			});
			marker.addListener('click', function() {
				infowindow.open(map, marker);
			});
		}






		 function loadMap() {
    var latlng = new google.maps.LatLng(latitude, longitude);
    var myOptions = {
      zoom: 15,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
    
    var contentString = 
    	'<div id="content">'+	    
	    '<h3>'+commonU+'</h3>'+
	    '<table>'+
	    '<tr>'+
	    '<td>Total Consumption'+
	    '</td>'+
	    '<td>'+totalCon+
	    '</td>'+
	    '</tr>'+
	    '<tr>'+
	    '<td>Average Daily Consumption'+ 
	    '</td>'+
	    '<td align="right">'+dailyAve+
	    '</td>'+
	    '</tr>'+
	    '<tr>'+
	    '<td>Average Weekly Consumption'+ 
	    '</td>'+
	    '<td align="right">'+weeklyAve+
	    '</td>'+
	    '</tr>'+
	    '<tr>'+
	    '<td>Average Monthly Consumption'+ 
	    '</td>'+
	    '<td align="right">'+monthlyAve+
	    '</td>'+
	    '</tr>'+
	    '</table>'+
	    '</div>';

	var infowindow = new google.maps.InfoWindow({
  		content: contentString
	});
	
    var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title:commonU
    }); 
    
    marker.addListener('click', function() {
        infowindow.open(map, marker);
      });  
  }
</script>
</head>


//document.write(totalCon);

	/* var latitude;
	var longitude;
	var getLocation =  function(address) {
	  var geocoder = new google.maps.Geocoder();
	  geocoder.geocode( { 'address': address}, function(results, status) {

	  if (status == google.maps.GeocoderStatus.OK) {
	      latitude = results[0].geometry.location.lat();
	      longitude = results[0].geometry.location.lng();
	      console.log(latitude, longitude);
	      } 
	  }); 
	} */

	//Call the function with address as parameter
	/* getLocation(address); */


	//				alert("got lat lng");
//				alert(latitude + " " + longitude);
//				}


