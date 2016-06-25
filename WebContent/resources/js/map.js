function listUser(){
	var avgUrl = "http://localhost:8080/water_consumer_portal/mapData";
	//alert(avgUrl);
	$.get(avgUrl, function(mapData){
		for (i = 0; i < mapData.length; i++) {
			document.write((i+1)+".) ");
			//for(j = 0; j < mapData[0].length; j++){
			//	document.write(mapData[i][j]+" ");			
			//}
			document.write("<br>");			
		}
		
		alert("S!");
		loadMap();
	});
	
function loadMap() {
		
		var latlng = new google.maps.LatLng(latitude, longitude);
		var myOptions = {
			zoom : 15,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("map_container"),
				myOptions);

		var contentString = '<div id="content">' + '<h3> Data </h3>' + '</div>';

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
}

