var url = "http://localhost:8080/water_consumer_portal/consumption?gran=day";
var gran = "day"; //set granularity as day as default
var options;
var chart;

function drawChart () {
        // Create the chart
        options =  {
        	chart: {
        		renderTo : 'container'
            },
            rangeSelector : {
            	buttons: [{
            		type: 'week',
            		count: 1,
            		text: '1w'
            	}, {
            		type: 'month',
            		count: 1,
            		text: '1m'
            	}, {
            		type: 'month',
            		count: 3,
            		text: '3m'
            	}, {
            		type: 'month',
            		count: 6,
            		text: '6m'
            	}, {
            		type: 'ytd',
            		text: 'YTD'
            	}, {
            		type: 'year',
            		count: 1,
            		text: '1y'
            	}, {
            		type: 'all',
            		text: 'All'
            	}]
            },
            xAxis: {
	            type: 'datetime',
	            labels: {
	            	 formatter: function() {
	            		 if (gran == "hour"){
	            			 return Highcharts.dateFormat('%I:%M %p', this.value);
	            		 }
	            		 else{
	            			 return Highcharts.dateFormat('%d %b %y', this.value);
	            		 }
	            	 }
	            }
	        },
            plotOptions: {
                series: {
	                cursor: 'pointer',
                    point: {
                        events: {
                            click: function() {
                            	if(gran == 'day'){
	                            	getHourData(this.x);
		                            fun();
                            	}
	                        }
                        }
                    }
                }
            },
            title:{
                text:''
            },
            series : [{
            	type: 'column',
                data : [],
                name : "" 
            }] 
	}
    
    $.getJSON(url, function(data){
    	if (data=="" || data==null) {alert("Data is not available.")}
       	else{
	    	options.series[0].name = "Water Consumption Portal";
	       	options.series[0].data = data;
	       	if (gran == "hour"){chart = new Highcharts.Chart(options)}
	       	else {chart = new Highcharts.StockChart(options);}
	    }
	}).fail(function(jqXHR, textStatus, errorThrown) { alert('Data is not available'); 
	});
}

function getChart(granularity) {
	url = "http://localhost:8080/water_consumer_portal/consumption";
	url += "?gran=" + granularity;
	gran = granularity;
	document.getElementById("back_btn").style.display="none";
	drawChart();
    
}

function getDefaultChart(){
	getChart("day");
}

function getHourData(x){	
	var date =  new Date;
    date.setTime(x);
    dateString = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
	url = "http://localhost:8080/water_consumer_portal/consumption?gran=hour&date=" + dateString;
	gran = "hour";
	document.getElementById("back_btn").style.display="block";
	drawChart();
}

var avgUserConsumption = 0;
var avgUserConsumption = 0;

function drawUserAvg(){
	var minDate = $('input.highcharts-range-selector:eq(0)').val();
	var maxDate = $('input.highcharts-range-selector:eq(1)').val();
	var avgUrl = "http://localhost:8080/water_consumer_portal/user/average?startDate=" + minDate+"&endDate=" + maxDate;
	var avgUserConsumption = 0;
	$.get(avgUrl, function(avgData){
		chart.yAxis[0].addPlotLine({
			value: avgData,
			width:2,
			color: '#0000A0',
            id: 'user-avg'
        });
	});
}

function dailyAvg(){	
	if (document.getElementById('useravg').checked) {
		drawUserAvg();
	}
	else {
		chart.yAxis[0].removePlotLine('user-avg');
    }
}

function drawLocalityAvg(){
	var minDate = $('input.highcharts-range-selector:eq(0)').val();
	var maxDate = $('input.highcharts-range-selector:eq(1)').val();
	var avgUrl = "http://localhost:8080/water_consumer_portal/locality/average?startDate=" + minDate+"&endDate=" + maxDate;
	var avgUserConsumption = 0;
	$.get(avgUrl, function(avgData){
		chart.yAxis[0].addPlotLine({
			value: avgData,
			width:2,
            color: '#fc04b9',
            id: 'locality-avg'
        });
	});
}

function localityAvg(){	
	if (document.getElementById('localityavg').checked) {
		drawLocalityAvg();
	}
	else {
		chart.yAxis[0].removePlotLine('locality-avg');
    }
}
