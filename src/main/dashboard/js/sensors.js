var graphs = {}; 

function sendRequest(url,callback){
	var xhr = new XMLHttpRequest();
	xhr.open("GET",url, true);
	xhr.onload = function (e) {
  		if (xhr.readyState === 4) {
			callback(xhr.status,xhr.responseText);
  		}			
	}
	xhr.send(null);
}
		
function sensorRequest(uuid){
	var url = "https://iot.eclubprague.com/iot/sensor_entry/"+uuid+"/40/temperature";
	sendRequest(url,function(status,response){
		if(status==200){
			var dataArray = JSON.parse(response).reverse();
			var data = dataArray[0];
			
			if(data !== undefined) {
				$("#dev1p"+data.sensor._UUID).html(data.unit+" is "+parseFloat(data.value).toFixed(2));

	            graphs[data.sensor._UUID].data.datasets[0].data=[];
	            graphs[data.sensor._UUID].data.datasets[0].label=data.unit;
	            dataArray.forEach(function (record) {
	                graphs[data.sensor._UUID].data.datasets[0].data.push(parseFloat(record.value));
	            })
	            graphs[data.sensor._UUID].chart.update();
            }
		}	
	});

	var url = "https://iot.eclubprague.com/iot/sensor_entry/"+uuid+"/40/battery";
	sendRequest(url,function(status,response){
		if(status==200){
			var dataArray = JSON.parse(response).reverse();
			var data = dataArray[0];
			//console.log(data);
			
			//$("#dev1p"+data.sensor._UUID).html(data.unit+" "+parseFloat(data.value).toFixed(2));

            graphs[data.sensor._UUID].data.datasets[1].data=[];
            graphs[data.sensor._UUID].data.datasets[1].label=data.unit;
            dataArray.forEach(function (record) {
                graphs[data.sensor._UUID].data.datasets[1].data.push(parseFloat(record.value));
            })
            graphs[data.sensor._UUID].chart.update();
		}	
	});
}
	

function startPolling(sensorList){
	setInterval(function(){
			sensorList.forEach(function(sensor){
				sensorRequest(sensor._UUID);
			});
	},1000);
}
	
function uuidRequest(){
	var url = "https://iot.eclubprague.com/iot/sensor";
	sendRequest(url,function(status,response){
		if(status==200){
			var sensorList = JSON.parse(response);
			var count = 0;
			sensorList.forEach(function(element){
				
				//if(count%2 == 0) {
				//	$("#sensorOverview").append('<div class="row">');
				//}

				var outsidediv = $("<div></div>");
				outsidediv.addClass("col-md-6");
				
				var insidediv = $("<div id=dev"+element._UUID+"></div>");
				insidediv.addClass("thumbnail");
				insidediv.append("<h2>"+element._UUID+"</h2>");
				insidediv.append("<p id=dev1p"+element._UUID+">lorem ipsum</p>");
				insidediv.append("<div height='100px'><canvas id=dev2p"+element._UUID+">lorem ipsum</canvas></div>");
				
				
				
				outsidediv.append(insidediv);
				
				$("#sensorOverview").append(outsidediv);
				//if(count%2 == 1) {
				//	$("#sensorOverview").append('</div>');
				//}


                var divCanvas = document.getElementById("dev2p"+element._UUID);
                var ctx = divCanvas.getContext("2d");
		
		var tmp = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
			
                var data = {};
                data.labels=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39];
                data.datasets=[];
                data.datasets.push({
                    label: "My First dataset",
                    data: tmp,
                    backgroundColor: "rgba(10,0,200,0.5)",
		    yAxisID: "y-axis-1",
                    fill: true
                });

		
                data.datasets.push({
                    label: "My Second dataset",
                    data: tmp,
		    backgroundColor: "rgba(200,0,20,0.5)",
		    yAxisID: "y-axis-2",
                    fill: true
                });
		

                graphs[element._UUID]={};
                graphs[element._UUID].chart=new Chart(ctx, {
                    type: 'line',
                    data: data,
                    options: {
			stacked: true,
                        responsive: true,
                        scales: {
                         yAxes: [{
                            display: true,
                            position: "left",
                            id: "y-axis-1",
			    gridLines: {
                                drawOnChartArea: false
                            }
                        }, {
                            display: true,
                            position: "right",
                            id: "y-axis-2",
			    gridLines: {
                                drawOnChartArea: false
                            }
                        }],
                    }
                    }
                    
                });
                graphs[element._UUID].data=data;
				
				
			});
			
			startPolling(sensorList);
		}
	});
}
		
uuidRequest();



var randomScalingFactor = function() {
	return Math.round(Math.random() * 100);
	//return 0;
};



window.onload = function() {
	var ctx = document.getElementById("canvas").getContext("2d");



};
