//全局处理shiro登录session失效时弹出提示
$.ajaxSetup({
	beforeSend: function(params1, params2){
		showMask();
		hidenParentClosed();
	},
    complete: function(XMLHttpRequest,textStatus){
    	hideMask();
        if(textStatus=="parsererror"){
        	confirm("登录失效，请确认是否重新登录！", function(){
        		window.location.href = 'login.html';
  			});
        } else if(textStatus=="error"){
        	//alert("请求超时！请稍后再试！");
        }else{
        	wholeError(XMLHttpRequest.responseJSON);
        }
    }  
});

function showMask(){
	$("#publicmask").show();
}

function hideMask(){
	$("#publicmask").hide();
}

//全局处理异常信息
function wholeError(res){
	if(res.code !== 0 && null != res.msg){
		console.log(res.msg);
		alert(res.msg);
	}
}

function changeOperationName(v){
	$('#operationName',window.parent.document).text(v);
}

function ajaxFunctionPost(url, data, successFunctionCall, errorFunctionCall){
	$.ajax({
		type: "POST",
	    url: url,
	    data: data,
	    dataType: "json",
	    success: function(resPublic){
	    	wholeError(resPublic);
			if (successFunctionCall != null) {
				successFunctionCall(resPublic);
			}
		},
		error: function(resPublic){
			if(resPublic.code !== 0 && null != resPublic.msg){
	    		alert(resPublic.msg);
			}else{
				alert("系统请求异常，请联系管理人员定位");
			}
			if (errorFunctionCall != null) {
				errorFunctionCall(resPublic);
			}
		}
	});
}

function toNewViewByPost(toViewParam, url) {
	var formObj = $("form");
	if (toViewParam != null) {
		var hiddenObj = $("<input type='hidden' name='viewParams' id='viewParams' value='" + JSON.stringify(toViewParam) + "'>");
		formObj.append(hiddenObj);
	}

	formObj.attr("method", "post");
	formObj.attr("action", url);
	formObj.submit();
}

function setEcharts(reData, personCnt, echartId, yData, personCnt){
	console.log(yData);
	var dom = document.getElementById(echartId);
    var tagProportionChart = echarts.init(dom);
    var app = {};
    option = null;
    var tagData = genData(reData);
    
    
    var data=[];
	var insperdata=[];
	var showValue=true;
	var endValue = tagData.seriesData.length;
	if(endValue <= 10 ){
		endValue = 100;
	}else if(endValue > 10 && endValue <= 20){
		endValue = 50;
	}else if(endValue > 20 && endValue <= 30){
		endValue = 30;
	}else if(endValue > 30 && endValue <= 40){
		endValue = 25;
	}else if(endValue > 40 && endValue <= 50){
		endValue = 20;
	}else if(endValue > 50 && endValue <= 60){
		endValue = 18;
	}else if(endValue > 60 && endValue <= 70){
		endValue = 14;
	}else if(endValue > 70 && endValue <= 80){
		endValue = 12;
	}else if(endValue > 80){
		endValue = 10;
	}
	option={
			baseOption: {
		        title: {},
		        timeline: {
		        	show:false,
		        	axisType: 'category',
		            autoPlay: false,
		            playInterval: 1000,
		            width: '90%',
		            bottom: 12,
		            left: 30,
		            symbolSize: 12,
		            label: {
		                normal: {
		                	show: false,
		                    textStyle: {
		                        color: '#FFFFFF',
		                        fontSize: 15
		                    }
		                },
		                emphasis: {
		                    textStyle: {
		                        color: '#FFFFFF',
		                        fontSize: 20
		                    }
		                }
		            },
		            controlStyle: {
		            	showPlayBtn: false
		            },
		            data: yData,
		        },
		        tooltip: {
		            trigger: 'item',
		            axisPointer: {
		                type: 'none'//'shadow'
		            },
		            formatter:function (params){
		            	var rName=yData;
		            	var b=false;
		            	for (var i = 0; i < rName.length; i++) {
		            		if (params.name==rName[i]) {
		            			b=true;
							}
						}
		            	if (b) {
		            		return params.name;
						}
						return params.name+': '+params.value;
		            } //"{b} : {c}"
		        },
		        grid: {
		            left: '3%',
		            right: '15%',
		            top: '6%',
		            containLabel: true
		        },
		        xAxis: {
		            type: 'value',
		            axisLine:{
		              lineStyle:{
		                  color:'#87CEFF'
		              }
		            },
		            axisLabel : {
		        	  show:true,
		              formatter: '{value}',
		              textStyle: {
		                  color: 'red',
		                  fontSize: 10
		              }
		            }
		        },
		        yAxis: {},
		        dataZoom: [{
			        	show:showValue,
			            type:'slider',
			            backgroundColor:'#383838',//背景色
			            borderColor:'#87CEFF',//边框色
			            fillerColor:'#00cc66',//选中区颜色
			            dataBackground:{
			            	lineStyle:{
			            		color:'#383838'
			            	}
			            },
			            yAxisIndex: 0,
			            width:18,
			            right:'7%',
			            start: 0,
			            end: endValue,
			            handleSize: '50%',
			            handleStyle:{
			                color:'#87CEFF',
			                borderWidth:15
			            },
		               textStyle:{
		            	   color:"red",
		               },
		               borderColor:"#eee"
		        	}
		        ],
		        calculable : true,
		        series: [],
		        animationEasing: 'elasticOut',
		        animationDelayUpdate: function(idx) {
		            return idx * 5;
		        }
		    },
		        series: [],
		        animationEasing: 'elasticOut',
		        animationDelayUpdate: function(idx) {
		            return idx * 5;
		        },
		    options: []
	};
	for (var n = 0; n < yData.length; n++) {
  		var dataList=tagData.seriesData;
  		var yValue=[];
  		var sData=[];
  		//重新分别取出排序后的nane和value
  		for (var b = 0; b < dataList.length; b++) {
  			yValue.push(dataList[b].name + "   ");
  			sData.push(dataList[b].value);
		}
	    option.options.push({
	        title : {
	        	text:yData[n],
	            x:'center'
	        },
	        yAxis: {
	            type: 'category',
	            data: yValue,
	            axisLabel: {
            		show:true,	
            		textStyle: {
            			color: '#330099',
            			fontSize: 10
            		}
            	},
            	axisLine:{
   		    	    lineStyle:{
                        color:'#87CEFF'
   		    	    }
   		        }
	        },
	        series: [{
	            type: 'bar',
	            barWidth:'60%',
	            label: {
	                normal: {
	                    show: true
	                }
	            },
		        itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'right',
							formatter : function(params) {
								return null == personCnt? params.value : ((params.value / personCnt) * 100).toFixed(2) + "%";
							}
						},
						color : '#00cc66'
					}
				},
	            data: sData,
	        }]
	    });
	}

    if (option && typeof option === "object") {
    	tagProportionChart.setOption(option, true);
    }
}

function showParentClosed(){
	$('#parentClosed',window.parent.document).show();
}

function hidenParentClosed(){
	$('#parentClosed',window.parent.document).hide();
}

function getWinHeight(){
	var wHei = $(window).height() - 80;
	return wHei;
}

function getWinWidth(){
	var wWid = $(window).width() - 300;
	return wWid;
}