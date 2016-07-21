var data = new Array();
var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/water_consumer_portal/mapData";

xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        var myArr = JSON.parse(xmlhttp.responseText);
        myFunction(myArr);
    }
};

xmlhttp.open("GET", url, true);
xmlhttp.send();

function myFunction(arr) {
	//load map
	var map = new google.maps.Map(document.getElementById("map_container"), {
        center: new google.maps.LatLng(46.184979, 8.729659),
        zoom: 8,
        mapTypeId: 'roadmap'
    });
    
	var geocoder = new google.maps.Geocoder();
    
    //get data from Jason
    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
    	var type = arr[i].typeUser;
    	data[i] = new Array();
        data[i][0]=arr[i].address; 
        data[i][1]=arr[i].typeUser;
        data[i][2]=arr[i].totalCon;
        data[i][3]=arr[i].dailyAve;
        data[i][4]=arr[i].weeklyAve;
        data[i][5]=arr[i].monthlyAve;
        
        out += '<a href="">' +
    	data[i][0] + '</a> '+data[i][1]+'<br>';
        
        var address = arr[i].address;
        var contentString = 
        	'<div id="content">'+	    
    	    '<h3>'+arr[i].typeUser+'</h3>'+
    	    '<table>'+
    	    '<tr>'+
    	    '<td>Total Consumption'+
    	    '</td>'+
    	    '<td>'+arr[i].totalCon+
    	    '</td>'+
    	    '</tr>'+
    	    '<tr>'+
    	    '<td>Average Daily Consumption'+ 
    	    '</td>'+
    	    '<td align="right">'+arr[i].dailyAve+
    	    '</td>'+
    	    '</tr>'+
    	    '<tr>'+
    	    '<td>Average Weekly Consumption'+ 
    	    '</td>'+
    	    '<td align="right">'+arr[i].weeklyAve+
    	    '</td>'+
    	    '</tr>'+
    	    '<tr>'+
    	    '<td>Average Monthly Consumption'+ 
    	    '</td>'+
    	    '<td align="right">'+arr[i].monthlyAve+
    	    '</td>'+
    	    '</tr>'+
    	    '</table>'+
    	    '</div>';
        
        geocodeAddress(geocoder, map, address, contentString, type);
    }

    document.getElementById("id01").innerHTML = out;
}

function geocodeAddress(geocoder, resultsMap, address, contentString, type) {
	  //var address = add;
	  geocoder.geocode({'address': address}, function(results, status) {
	    if (status === google.maps.GeocoderStatus.OK) {
	    	var infowindow = new google.maps.InfoWindow({
	  			content : contentString
	  		});
	    	
	    	
	    	var image;
	    	if(type == "Common Smartmeter")
	    		image = 'resources/img/common.png';
	    	else
	    		image = 'resources/img/individual.png';
	    	
	    	resultsMap.setCenter(results[0].geometry.location);
	    	var marker = new google.maps.Marker({
	    		map: resultsMap,
	    		position: results[0].geometry.location,
	    		title: type,
	    		icon: image
	    	});
	      
	    	marker.addListener('click', function() {
				infowindow.open(resultsMap, marker);
			});
	    	
	    } else {
	    	document.getElementById("error_message").innerHTML = "Some entries could not be retrieved because of error - " + status;
	      //alert('Geocode was not successful for the following reason: ' + status);
	    }
	  });
	}