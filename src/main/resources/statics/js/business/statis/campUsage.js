var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "campId",
			pIdKey: "parentId",
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
		
		height: (getWinHeight() + 66) + 'px',
		echartHeight: ((getWinHeight() + 66)/2) + 'px',
		tagHeight: (getWinHeight() - 128) + 'px'
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		getTagsTree: function() {
			//加载菜单树
			$.get("../statis/queryCampaignTress", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.campTress);
				//展开所有节点
				ztree.expandAll(true);
			});
	    }
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
		  vm.getTagsTree();
	  })
	}
});

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var campIds = new Array();
	for(var i=0; i<nodes.length; i++) {
		campIds.push(nodes[i].campId);
	}
	if(campIds.length > 0){
		vm.showList = false;
		var campIdJoin = campIds.join();
		
		ajaxFunctionPost("../userGroup/analysis/list", {"campIds":campIdJoin}, 
			function(r){
				setEcharts3("用户群热度（含有人数）对比分析", r.camps, "group_heat");
			}, function(r){
				alert("调用系统异常，请联系管理人员定位");
			}
		);
		
		ajaxFunctionPost("../statis/usage/campUsageLog", {"campIds":campIdJoin}, 
			function(r){
				setEcharts2("用户群使用对比分析", r.usageLog, "group_usage");
			}, function(r){
				alert("调用系统异常，请联系管理人员定位");
			}
		);
		
	}else{
		vm.showList = true;
	}
	
}

function setEcharts2(titName, reData, echartId){
	
	var dom = document.getElementById(echartId);
    var tagProportionChart = echarts.init(dom);
    
	var xValue=[];
	var sData=[];
	//重新分别取出排序后的nane和value
	var dataLength = reData.length;
	for (var b = 0; b < dataLength; b++) {
		xValue.push(reData[b].camp_nm);
		sData.push(reData[b].count);
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
	option = {
	    title: {
	        text: titName,
	        x:'left',
	        textStyle:{
	            fontSize:12
	        }
	    },
	    tooltip: {
	        show: "true",
	        trigger: 'item',
	        backgroundColor: 'rgba(0,0,0,0.7)', // 背景
	        padding: [8, 10], //内边距
	        extraCssText: 'box-shadow: 0 0 3px rgba(255, 255, 255, 0.4);', //添加阴影
	        formatter: function(params) {
                return params.name + 
                '<br/>使用次数： ' + params.value;
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
	    		name:'用户群使用次数',
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
	        }
	    ]
	};
	
    if (option && typeof option === "object") {
    	tagProportionChart.setOption(option, true);
    }
}

function setEcharts3(titName, reData, echartId){
	
	var dom = document.getElementById(echartId);
    var tagProportionChart = echarts.init(dom);
    
	var xValue=[];
	var sData=[];
	//重新分别取出排序后的nane和value
	var dataLength = reData.length;
	for (var b = 0; b < dataLength; b++) {
		xValue.push(reData[b].campNm);
		sData.push(reData[b].sum);
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
	option = {
	    title: {
	        text: titName,
	        x:'left',
	        textStyle:{
	            fontSize:12
	        }
	    },
	    tooltip: {
	        show: "true",
	        trigger: 'item',
	        backgroundColor: 'rgba(0,0,0,0.7)', // 背景
	        padding: [8, 10], //内边距
	        extraCssText: 'box-shadow: 0 0 3px rgba(255, 255, 255, 0.4);', //添加阴影
	        formatter: function(params) {
                return params.name + 
                '<br/>含有人口数： ' + params.value;
            }
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
	    		name:'用户群含有人口数',
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
	        }
	    ]
	};
	
    if (option && typeof option === "object") {
    	tagProportionChart.setOption(option, true);
    }
}