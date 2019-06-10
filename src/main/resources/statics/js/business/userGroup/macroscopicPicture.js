var nameMap = {};
//查看
function onClickQuery(campId){
	vm.clickQuery(campId);
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			campNm: null
		},
		title: null,
		campTagDetails: {},
		topTagDetails: {},
		personCntSum: 0,
		campList:{},
		selectId:null,
		isShow: false,
		mapFlag: false,
		height: (getWinHeight() + 33) + 'px',
		height2: (getWinHeight() + 62) + 'px',
		echartHeight: ((getWinHeight() + 15)/2) + 'px',
		echartWidth:((getWinWidth()+100)*4/5) + 'px',
		tagHeight: (getWinHeight() - 126) + 'px'
	},
	methods: {
		closed:function(){
			changeOperationName("浏览");
		},
		query: function () {
			vm.reload();
		},
		clickQuery: function(campId){
            vm.campTagDetails = {};
            vm.queryCampAllTagDetail(campId);
		},
		queryCampAllTagDetail: function(campId){
			var data = "campId="+campId;
			ajaxFunctionPost(ctxPath + "/userGroup/macroscopicPicture/queryCampAllTagDetail", data, vm.successPro, vm.errorPro);
		},
		successPro: function(res){
			vm.campTagDetails = res.campAllTagDetails;
			
			this.$nextTick(function () {
				//初始化含有标签属性
		    	$('div[id*="category_"]').each(function(){
				    $(this).removeClass("bsclass");
				});
		    	$("#category_0").addClass("bsclass");
		    	
		    	$('div[id*="categoryChild_"]').each(function(){
				    $(this).hide();
				});
		    	$("#categoryChild_0").show();
		    	
		    	//初始化含有标签属性大类
		    	$('div[id*="categoryPie_"]').each(function(){
		    		 $(this).removeClass("bsclass");
				});
		    	$("#categoryPie_0").addClass("bsclass");
		    	
		    	//初始化含有标签属性小类
		    	$('div[id*="categoryPieChild_"]').each(function(){
				    $(this).hide();
				});
		    	$("#categoryPieChild_0").show();
		    	
		    	for(pKey in vm.campTagDetails){
					var cChild = vm.campTagDetails[pKey];
					for(cKey in cChild){
						setEcharts2(res.campId, cKey, cChild[cKey], "pie_" + pKey + "_" + cKey, vm.personCntSum);
					}
				}
			});
		},
		errorPro: function(res){
			alert("系统请求异常，请联系管理人员定位！");
		},
		categoryPieClick: function(index){
			//初始化含有标签属性大类
	    	$('div[id*="categoryPie_"]').each(function(){
	    		 $(this).removeClass("bsclass");
			});
	    	$("#categoryPie_" + index).addClass("bsclass");
	    	
			$('div[id*="categoryPieChild_"]').each(function(){
			    $(this).hide();
			});
	    	$("#categoryPieChild_" + index).show();
		},
		personCntSumPro: function(){
			ajaxFunctionPost(ctxPath + "/userGroup/macroscopicPicture/personCntSum", null, 
				function(res){
					vm.personCntSum = res.personCntSum;
				}, 
				function(e){
					alert("调用系统异常，请联系管理人员定位");
				}
			);
		},
		queryPersonDistribute: function(campId){
	    	
	    	//人口居住地
			var dom3 = document.getElementById("main_area");
			var myChart3 = echarts.init(dom3);
			myChart3.showLoading();
			
			//人口居住地是否已知
			var dom4 = document.getElementById("main_pie");
		    var myChart4 = echarts.init(dom4);
		    myChart4.showLoading();
			
			var data = "campId="+campId;
			ajaxFunctionPost(ctxPath + "/userGroup/macroscopicPicture/queryPersonDistribute", data, 
				function(res){
					setEcharts3(campId, res, myChart3);//人口居住地
					setEcharts4(res, myChart4);//居住地数据排序
				}, function(res){
					
				}
			);
		},
		personDistribute: function(){
			vm.queryPersonDistribute(vm.selectId);
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'campNm': vm.q.campNm},
                page:page
            }).trigger("reloadGrid");
		},
		getTagsTree: function() {
			//加载菜单树
			$.get("../statis/queryCampaignTress2", function(r){
				vm.campList = r.campTress;
			});
	    },
	    liNameOnClick: function(id, name){
	    	//给群组名称添加样式
	    	$('div[id*="camp_"]').each(function(){
			    $(this).removeClass("onclickCampClass");
			});
	    	$("#camp_" + id).addClass("onclickCampClass");
	    	
	    	nameMap[id] = "【用户群："+name+"】";
	    	vm.campTagDetails = {};
            vm.selectId = id;
            vm.queryCampAllTagDetail(id);
            
            vm.isShow = true;
            vm.mapFlag = false;
	    },
	    categoryClick: function(index){
	    	
	    	if(!vm.selectId){
	    		alert("请点击左侧其中一个用户群。");
	    		return;
	    	}
	    	
	    	$('div[id*="category_"]').each(function(){
			    $(this).removeClass("bsclass");
			});
	    	$("#category_" + index).addClass("bsclass");
	    	
	    	$('div[id*="categoryChild_"]').each(function(){
			    $(this).hide();
			});
	    	$("#categoryChild_" + index).show();
	    	
	    	if(1 == index){
	    		if(!vm.mapFlag){
	    			vm.queryPersonDistribute(vm.selectId);
	    			vm.mapFlag = true;
	    		}
	    	}
	    }
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
		  vm.getTagsTree();
		  vm.personCntSumPro();
	  })
	}
});

function setEcharts2(campId, titName, reData, echartId, personCnt){
	var dom = document.getElementById(echartId);
    var tagProportionChart = echarts.init(dom);
    
	var xValue=[];
	var sData=[];
	var mdata=[];
	//重新分别取出排序后的nane和value
	var dataLength = reData.length;
	for (var b = 0; b < dataLength; b++) {
		xValue.push(reData[b].name);
		sData.push(reData[b].count);
		mdata.push(personCnt);
	}
	var barWidth = 10;//设置柱图的宽度，默认10
	if(dataLength <= 5){
		barWidth = 100;
	}else if(dataLength <= 10){
		barWidth = 50;
	}else if(dataLength <= 20){
		barWidth = 20;
	}
	var labelShow = true;//是否在柱体上方显示数据
	if(dataLength > 15){
		labelShow = false;
	}
	var rotate = 0;
	if(dataLength > 10){
		rotate = 40;
	}
	var zrks = '人口总数(' + personCnt + ')';
	option = {
	    title: {
	        text: titName,
	        x:'left',
	        textStyle:{
	            fontSize:12
	        }
	    },
	    legend: {
	        right: '2%',
	        itemWidth: 12,
	        itemHeight: 12,
	        data :  [zrks, '符合标签属性人数'],
	        textStyle:{    //图例文字的样式
	            fontSize:10
	        }
	    },
	    tooltip: {
	        show: "true",
	        trigger: 'axis',
	        backgroundColor: 'rgba(0,0,0,0.7)', // 背景
	        padding: [8, 10], //内边距
	        extraCssText: 'box-shadow: 0 0 3px rgba(255, 255, 255, 0.4);', //添加阴影
	        formatter: function(params) {
//	            if (params.seriesName == zrks) {
//	                return '人口总数： ' + params.value;
//	            }else{
//	                return params.name + 
//	                '<br/>符合人数： ' + params.value + 
//	                '<br/>占人口总数比例：' + (null == personCnt? params.value : ((params.value / personCnt) * 100).toFixed(2) + '%');
//	            }
	        	return params[0].name + 
                '<br/>符合人口数： ' + params[0].value + 
                '<br/>占人口总数比例：' + (null == personCnt? params[0].value : ((params[0].value / personCnt) * 100).toFixed(2) + '%');
	        },

	    },
	    grid: {
	        borderWidth: 0,
	        left: 40,
	        right: 40,
	        top:15,
	        textStyle: {
	            color: "#fff"
	        }
	    },
	    xAxis: [{
	        type: 'category',
	        axisLabel:  {
                rotate: rotate,
                show: true
            },
	        data: xValue
	    }, {
	        type: 'category',
	        axisLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLabel: {
	            show: false
	        },
	        splitArea: {
	            show: false
	        },
	        splitLine: {
	            show: false
	        },
	        data: xValue,
	    }],
	    yAxis: {
		   show:false,
		   type : 'value'
		},
	    series: [{
	    		name:'符合标签属性人数',
	            type: 'bar',
	            label: {
	                show: labelShow,
	                position: 'top'
	            },
	            itemStyle: {
	                normal: {
	                    show: true,
	                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
	                        offset: 0,
	                        color: '#00c0e9'
	                    }, {
	                        offset: 1,
	                        color: '#3b73cf'
	                    }]),
	                    borderWidth: 0,
	                },
	                emphasis: {
	                    shadowBlur: 15,
	                    shadowColor: 'rgba(105,123, 214, 0.7)'
	                }
	            },
	            zlevel: 2,
	            barWidth: barWidth,
	            data: sData,
	        },
	        {
	            name: zrks,
	            type: 'bar',
	            xAxisIndex: 1,
	            zlevel: 1,
	            itemStyle: {
	                normal: {
	                    color: '#FFCCFF',
	                    borderWidth: 0,
	                    shadowBlur: {
	                        shadowColor: 'rgba(255,255,255,0.31)',
	                        shadowBlur: 10,
	                        shadowOffsetX: 0,
	                        shadowOffsetY: 2,
	                    },
	                }
	            },
	            barWidth: barWidth,
	            data: mdata
	        }
	    ]
	};
	
    if (option && typeof option === "object") {
    	tagProportionChart.setOption(option, true);
    }
}

function setEcharts3(campId, reData, myChart){

    var uploadedDataURL = ctxPath + "/js/public/map/hai_nan_geo.json";
    
    $.get(uploadedDataURL, function(geoJson) {
    	var name = 'hn';
    	echarts.registerMap(name, geoJson);
    	var persons = reData.persons;
    	
    	var yData = [];
    	var sData = [];
    	var dataLength = persons.length;
    	for (var b = 0; b < dataLength; b++) {
    		yData.push(persons[b].name);
    		sData.push(persons[b].value);
    	}
    	
    	var maxCount = reData.maxCount;
    	option = {
            title: {
                text: "人口居住地分布",
                left: 'left',
                textStyle:{
                	fontSize:12
                }
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b}: {c}"
            },
            visualMap: {
                min: 0,
                max: maxCount,
                left: 'left',
                top: 'bottom',
                calculable: true,
                inRange: {
                	color: ['#bafff9', '#014bf5']
                }
            },
            grid: {
                right: 5,
                top: 30,
                bottom: 30,
                width: '18%'
            },
	        series: [{
	                type: 'map',
	                mapType: name,
	                label: {
	                    normal: {
	                        show: true
	                    },
	                    emphasis: {
	                        textStyle: {
	                            color: '#fff'
	                        }
	                    }
	                },
	                itemStyle: {
	                    normal: {
	                        borderColor: '#389BB7',
	                        areaColor: '#fff',
	                    },
	                    emphasis: {
	                        areaColor: '#389BB7',
	                        borderWidth: 0
	                    }
	                },
	                roam: true, //开启缩放或者平移
	                zoom: 1.2,  //缩放比例
	                animation: false,
	                data: persons,
	                nameMap:{
		            	 '白沙黎族自治县': '白沙县',
		            	 '昌江黎族自治县': '昌江县',
		            	 '乐东黎族自治县': '乐东县',
		            	 '陵水黎族自治县': '陵水县',
		            	 '保亭黎族苗族自治县': '保亭县',
		            	 '琼中黎族苗族自治县': '琼中县'
		            }
	            }
            ],
        }
        myChart.setOption(option, true);
    	myChart.hideLoading();
    });
}

function setEcharts4(reData, myChart4){
    
	var persons = reData.persons;
	persons.sort(function(a,b){
        return a.value-b.value}
	);
	
	var xData = [];
	var sData = [];
	var dataLength = persons.length;
	for (var b = 0; b < dataLength; b++) {
		xData.push(persons[b].name);
		sData.push(persons[b].value);
	}
	option = {
	    color: ['#3398DB'],
	    grid: {
	        left: '4%',
	        right: '4%',
	        bottom: 30,
	        top:30,
	        containLabel: true
	    },
	    xAxis: {
	        show:false,
	        type: 'value',
	        boundaryGap: [0, 0.8],
	        interval: 20
	    },
	    yAxis: {
	    	type: 'category',
	        nameGap: 16,
	        axisLine: {
	            show: true,
	            lineStyle: {
	                color: '#ddd'
	            }
	        },
	        axisTick: {
	            show: false,
	            lineStyle: {
	                color: '#ddd'
	            }
	        },
	        axisLabel: {
	            interval: 0,
	            textStyle: {
	                color: '#999'
	            }
	        },
	        data: xData
	    },
	    series: [{
	        type: 'bar',
	        label: {
	            normal: {
	                show: true,
	                position:'right'
	            }
	        },
	        data: sData
	    }]
	};
    
    myChart4.setOption(option, true);
    myChart4.hideLoading();
}
