$(function() {

	// Set the default dates
	var startDate = Date.create().addDays(-6), // 7 days ago
	endDate = Date.create(); // today

	var range = $('#range');

	// Show the dates in the range input
	range.val(startDate.format('{MM}/{dd}/{yyyy}') + ' - '
			+ endDate.format('{MM}/{dd}/{yyyy}'));

	// Load chart
	ajaxLoadChart(startDate, endDate);

	range.daterangepicker({
		startDate : startDate,
		endDate : endDate,
		ranges : {
			'Today' : [ 'today', 'today' ],
			'Yesterday' : [ 'yesterday', 'yesterday' ],
			'Last 7 Days' : [ Date.create().addDays(-6), 'today' ],
			'Last 30 Days' : [ Date.create().addDays(-29), 'today' ]
		}
	}, function(start, end) {
		ajaxLoadChart(start, end);
	});

	// Function for loading data via AJAX and showing it on the chart
	function ajaxLoadChart(startDate, endDate) {
		// If no data is passed (the chart was cleared)
		if (!startDate || !endDate) {
			return;
		}
		// Otherwise, issue an AJAX request
		var xhttp = new XMLHttpRequest();
		var data;
		function dataC() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					data = JSON.parse(xhttp.responseText);
					document.getElementById("nameS").innerHTML = data.name;
					getChart();
				}
			};
			xhttp.open("GET", 'getConsumption?from='
					+ document.getElementById("startDate").value + '&to='
					+ document.getElementById("endDate").value, true);
			xhttp.setRequestHeader('Content-Type', 'application/json');
			xhttp.send();
		}
	
		function getChart(data) {
			if ((data.indexOf("No record found") > -1)
					|| (data.indexOf("Date must be selected.") > -1)) {
				$('#msg').html('<span style="color:red;">' + data + '</span>');
			} else {
				$('#msg').empty();
				$('#chart').highcharts({
					chart : {
						type : 'arearange',
						zoomType : 'x'
					},

					title : {
						text : 'Temperature variation by day'
					},

					xAxis : {
						type : 'datetime'
					},

					yAxis : {
						title : {
							text : null
						}
					},

					tooltip : {
						crosshairs : true,
						shared : true,
						valueSuffix : '�C'
					},

					legend : {
						enabled : false
					},

					series : [ {
						name : 'Temperatures',
						data : data
					} ]
				});
			}
		};
	}
});
