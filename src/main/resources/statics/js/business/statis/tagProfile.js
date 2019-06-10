$(function () {
	
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "tag_id",
			pIdKey: "parent_id",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	},
	callback: {
		onCheck: treesOnClick,
		unCheck: treesOnClick
    }
};

var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			campNm: null
		},
		showList: true,
		title: null,
		personCntSum: 0,
		selectId:null,
		isShow: false,
		tagProfile:{},
		tagUsages:{},
		clickUsageFlag: false,
		tagEchart:null,
		keyTitle:null,
		
		height:(getWinHeight() + 29) + 'px',
		height2:(getWinHeight() + 34) + 'px',
		height3:(getWinHeight() + 25) + 'px',
		tagHeight: (getWinHeight() - 130) + 'px',
		heightP: (getWinHeight() - 15) + 'px',
		heightPie:((getWinHeight() + 10)/2) + 'px',
		widthPie:((document.body.clientWidth-200)*4/5) + 'px',
		areaHeight: getWinHeight() + 'px',
		areaWidth1: ((document.body.clientWidth-230)*3/5) + 'px',
		areaWidth2: ((document.body.clientWidth-230)*0.8/5) + 'px'
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		jinzhiNode: function(ztree, nodes){
			for(var i=0; i<nodes.length; i++){
				var cNodes = nodes[i].children;
				if(null == cNodes || cNodes.length <= 0){
					nodes[i].chkDisabled = true;//禁止勾选
					nodes[i].nocheck = true;//取消复选框
					ztree.updateNode(nodes[i]);
				}else{
					vm.jinzhiNode(ztree, cNodes);
				}
			}
		},
		getTagsTree: function() {
			//加载菜单树
			$.get("../label/h50taginfo/queryAllTagsAndGroupTags", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.tags);
				//展开所有节点
				ztree.expandAll(false);
				//var nodes = ztree.getNodes();
				//vm.jinzhiNode(ztree, nodes);
			});
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
		categoryClick: function(index){
	    	$('div[id*="category_"]').each(function(){
			    $(this).removeClass("bsclass");
			});
	    	$("#category_" + index).addClass("bsclass");
	    	
	    	$('div[id*="categoryChild_"]').each(function(){
			    $(this).hide();
			});
	    	$("#categoryChild_" + index).show();
	    	
	    	if(1 == index){
	    		
	    	}
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
		usagesPieClick: function(index){
			//初始化含有标签属性大类
	    	$('div[id*="usagePie_"]').each(function(){
	    		 $(this).removeClass("bsclass");
			});
	    	$("#usagePie_" + index).addClass("bsclass");
	    	
			$('div[id*="usagePieChild_"]').each(function(){
			    $(this).hide();
			});
	    	$("#usagePieChild_" + index).show();
	    	
	    	$('div[id*="tag_"]').each(function(){
			    $(this).hide();
			});
	    	$("#tag_" + index).show();
		},
	    queryProfiles: function(checkTags){
	    	vm.selectId = checkTags;
	    	
	    	$('div[id*="usagePieChild_"]').each(function(){
			    $(this).hide();
			});
	    	
	    	ajaxFunctionPost("../statis/tags/profile", {"checkTags":checkTags.join()}, 
    			vm.successPro, function(r){
    				alert("调用系统异常，请联系管理人员定位");
    			}
    		);
	    },
	    successPro: function(r){
			if(null == r.tags || r.tags.length < 1){
				vm.isShow = false;
			}else{
				vm.isShow = true;
			}
			vm.tagProfile = r.tags;
			
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
		    	
		    	for(pKey in vm.tagProfile){
					var cChild = vm.tagProfile[pKey];
					for(cKey in cChild){
						setEcharts2(cKey, cChild[cKey], "pie_" + pKey + "_" + cKey, vm.personCntSum);
					}
				}
			});
	    },
	    queryTagUsage: function(checkTags){
	    	//标签使用分析
	    	ajaxFunctionPost("../userGroup/macroscopicPicture/queryTagsDistribute", {"checkTags":checkTags.join()}, 
    			vm.queryTagUsageSuc, 
    			function(r){
    				//alert("调用系统异常，请联系管理人员定位");
    			}
    		);
	    },
	    queryTagUsageSuc: function(r){
	    	vm.tagUsages = r.areaMap;
	    	this.$nextTick(function () {
	    		setEcharts3("test", vm.tagUsages, "usage_area");
				setEcharts4(vm.tagUsages, "usage_pie");
	    	});
	    },
	    liNameOnClick: function(id, key, value){
	    	$('div[id*="tagName_"]').each(function(){
			    $(this).removeClass("onclickCampClass");
			});
	    	$("#tagName_" + key).addClass("onclickCampClass");
	    	
	    	vm.keyTitle = key;
	    	vm.queryTagArea(value[key]);
	    },
	    queryTagArea: function(vaAr){
	    	vm.tagEchart = null;
	    	var tagSelect = new Array();
	    	for(var i=0; i<vaAr.length; i++) {
	    		for(var key in vaAr[i]){
	    			tagSelect.push(vaAr[i][key]);
	    		}
	    	}
	    	
	    	ajaxFunctionPost("../userGroup/macroscopicPicture/queryTagArea", {"checkTags":tagSelect.join()}, 
    			function(r){
	    			vm.tagEchart = r.echartData;
	    			vm.$nextTick(function () {
	    				for(var key in vm.tagEchart){
		    				setEcharts3(key, vm.tagEchart[key], "usage_area_" + key);
							setEcharts4(vm.tagEchart[key], "usage_pie_" + key);
		    			}
	    			});
	    		}, function(r){
    				alert("调用系统异常，请联系管理人员定位");
    			}
    		);
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

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var checkTags = new Array();
	var nodeLength = nodes.length;
	if(nodeLength < 1){
		vm.isShow = false;
		return;
	}
	if(nodeLength > 200){
		alert("勾选的标签属性数量过多，不能超过200。");
		return ;
	}
	for(var i=0; i<nodeLength; i++) {
		checkTags.push(nodes[i].tag_id);
	}
	
	vm.queryProfiles(checkTags);
	vm.queryTagUsage(checkTags);
}

function setEcharts2(titName, reData, echartId, personCnt){
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
	        data :  [zrks, '符合标签属性人口数'],
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
//	                return '人口总数1： ' + params.value;
//	            }else{
//	                return params.name + 
//	                '<br/>符合人口数： ' + params.value + 
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
	        top:40,
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
	    		name:'符合标签属性人口数',
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

function setEcharts3(title, persons, chartId){
	
	var dom3 = document.getElementById(chartId);
    var myChart3 = echarts.init(dom3);
    
    var uploadedDataURL = ctxPath + "/js/public/map/hai_nan_geo.json";
    
    $.get(uploadedDataURL, function(geoJson) {
    	var name = 'hn';
    	echarts.registerMap(name, geoJson);
    	var maxCount = 0;
    	var yData = [];
    	var sData = [];
    	var dataLength = persons.length;
    	for (var b = 0; b < dataLength; b++) {
    		var pValue = persons[b].value;
    		yData.push(persons[b].name);
    		sData.push(pValue);
    		if(maxCount < pValue){
    			maxCount = pValue;
    		}
    	}
    	
    	option = {
            title: {
                text: "标签人口居住地分布",
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
                top: 10,
                bottom: 20,
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
	                roam: false, //开启缩放或者平移
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
    	myChart3.setOption(option, true);
    });
}

function setEcharts4(persons, chartId){
	
	var dom4 = document.getElementById(chartId);
    var myChart4 = echarts.init(dom4);
    
	persons.sort(function(a,b){
        	return a.value-b.value
    });
	
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
	        bottom: 10,
	        top:10,
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
}
