		var data;
		function getChart(startDate, endDate) {
			alert("inside getchart");
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					data = JSON.parse(xhttp.responseText);
					document.getElementById("nameS").innerHTML = data.name;
					chart2();
				}
			};
			xhttp.open("GET", 'getConsumption?from=' + startDate + '&to=' + endDate, true);
			xhttp.setRequestHeader('Content-Type', 'application/json');
			xhttp.send();
		}
		var mySeries = [];
		var myLabelSeries = [];
		function chart2() {
			var meters = data.meters;
			var html = "";
			var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			
			for (var i = 0; i < meters.length; i++) {
				html += "<span>" + meters[i].totalConsumptionAdjusted + " - "+meters[i].readingDateTime+"</span><br>";
				mySeries[i]=meters[i].totalConsumptionAdjusted;
				var date = new Date(meters[i].readingDateTime);
				myLabelSeries[i]= date.getDate()+". "+months[date.getMonth()];
			}
			document.getElementById("nameS").innerHTML = html;
			
			$('#chart2').highcharts({
		        chart: {
		            type: 'line'
		        },
		        title: {
		            text: 'Household Consumer'
		        },
		        
		        xAxis: {
		            categories: myLabelSeries,
		            crosshair: true
		        },
		        yAxis: {
		            min: 0,
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
		            name: 'Data',
		        	data : mySeries
		        }]
			});
		}

		var data;
		function dataC(startDate, endDate) {
			alert("inside datac");
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					data = JSON.parse(xhttp.responseText);
					chart();
				}
			};
			/* , 'getConsumption?from=' + startDate + '&to=' + endDate */
			xhttp.open("GET", true);
			xhttp.setRequestHeader('Content-Type', 'application/json');
			xhttp.send();
		}
		var mySeries = [];
		var myLabelSeries = [];
		function chart() {
			var meters = data.meters;
			var html = "";
			var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
			
			for (var i = 0; i < meters.length; i++) {
				html += "<span>" + meters[i].totalConsumptionAdjusted + " - "+meters[i].readingDateTime+"</span><br>";
				mySeries[i]=meters[i].totalConsumptionAdjusted;
				var date = new Date(meters[i].readingDateTime);
				myLabelSeries[i]= date;
			}
			/* document.getElementById("nameS").innerHTML = html; */
			
			$('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: 'Household Consumer'
		        },
		        rangeSelector:{
		        	allButtonsEnabled:true
	            },  
		        xAxis: {
		            categories: myLabelSeries,
		            crosshair: true,
		            type: 'datetime',
		            dateTimeLabelFormats: { // don't display the dummy year
		                month: '%e. %b',
		                year: '%b'
		            },
		            title: {text: "Date"}
		        },
		        yAxis: {
		            min: 0,
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
		            name: 'Data',
		        	data : mySeries
		        }]
		    });
		}
