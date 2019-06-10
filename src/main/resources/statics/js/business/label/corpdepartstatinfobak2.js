$(function () {
	//加载数据
	onloadLeftUp();
	onloadLeftDown();
	onloadPercentData();
	onloadImputationData();
	onloadNotImputationData();
	onloadImputationInfoAllCount();
});


onloadLeftUp = function(){
	var DepName = [];
	var DepTableCount = [];
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getDeptTableStatus/default",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.depTableInfo;
				for(var i=0;i<list.length;i++){
					DepName.push(list[i].DepartName);
					DepTableCount.push(list[i].tableCount);
				}
				
			}else{
				alert(r.msg);
			}
		}
	});
	var dom = document.getElementById("leftUpContent");
	var tagProportionChart = echarts.init(dom);
	option = {
	    title: {
	        x: 'center',
	        text: '海南省各单位数据归集状况',
	        subtext: '数据来自海口信息中心',
	        link: ''
	    },
	    tooltip: {
	        trigger: 'item'
	    },
	    calculable: true,
	    grid: {
	        borderWidth: 0,
	        y: 80,
	        y2: 60
	    },
	    xAxis: [
	        {
	            type: 'category',
	            show: false,
	            data: DepName
//	            	[
//		           '省发改委', '省民政厅', '省财政厅', '省文化厅 ', ' 省人事厅', '省司法厅', '省文化厅 '
//		          , '省科技厅'
//		          , '省水利厅','省安全厅','省农牧厅'
//	          	]
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            show: false
	        }
	    ],
	    series: [
	        {
	            name: '海南省各单位数据归集状况',
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: function(params) {
	                        // build a color map as your need.
	                        var colorList = [
	                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
	                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
	                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
	                        ];
	                        return colorList[params.dataIndex]
	                    },
	                    label: {
	                        show: true,
	                        position: 'top',
	                        formatter: '{b}\n{c}'
	                    }
	                }
	            },
	            data: DepTableCount,
	        }
	    ]
	};
	tagProportionChart.setOption(option, true);  
}
onloadImputationInfoAllCount = function(){
	var allCount="";
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfoAllCount",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				allCount=r.allCount;
			}else{
				alert(r.msg);
			}
		}
	});
	$("#allCountDiv").html(allCount);
	
}
onloadLeftDown = function(){
	var DepName = [];
	var dataPercent = [];
	var seriesData = [];
	var radius = [40, 50];
	var labelFromatter = {
	    normal : {
	        label : {
	        	position : 'center',
//	            formatter : function (params){
//	                //var percent=Math.floor(100*params.value * 100) / 100
//	                return "{b}"+(params.value * 100 +"").substr(0,5)+"%";
//	            },
	            formatter:"{b}",
	            textStyle: {
	                baseline : 'top'
	            }
	        }
	    },
	};
	var labelTop = {
	    normal : {
	        label : {
	            show : true,
	            position : 'center',
	            formatter : '{b}',
	            textStyle: {
	                baseline : 'bottom'
	            }
	        },
	        labelLine : {
	            show : false
	        }
	    }
	};
	
	var labelBottom = {
	    normal : {
	        color: '#ccc',
	        label : {
	            show : true,
	            position : 'center',
	            
	        },
	        labelLine : {
	            show : false
	        }
	    },
	    emphasis: {
	        color: 'rgba(0,0,0,0)'
	    }
	};
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getDeptDataQualityStatus/default",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.depTableInfo;
				var locationX=100/list.length;
				for(var i=0;i<list.length;i++){
					DepName.push(list[i].DepartName);
					dataPercent.push(list[i].dataPercent);
					seriesData.push({
				          type : 'pie',
				          center : [locationX+'%', '50%'],
				          radius : radius,
				          x: (locationX-10)+'%', // for funnel
				          itemStyle : labelFromatter,
				          data : [
				             {name:(list[i].dataPercent * 100 +"").substr(0,5)+"%", value:1-list[i].dataPercent, itemStyle : labelBottom},
				             {name:list[i].DepartName, value:list[i].dataPercent,itemStyle : labelTop}
				          ]
					});
					locationX+=20;
					
				}
			}else{
				alert(r.msg);
			}
		}
	});
	var dom = document.getElementById("leftDownContent");
	var tagProportionChart = echarts.init(dom);
	
	
		
	option = {
//	    legend: {
//	        x : 'center',
//	        y : '60%',
//	        data:DepName
//	        	[
//	           '省发改委', '省民政厅', '省财政厅', '省文化厅 ', ' 省人事厅', '省司法厅', '省文化厅 '
//	          , '省科技厅'
//	          , '省水利厅','省安全厅'
//	        ]
//	    },
	    title : {
	        text: '海南省各单位数据质量',
	        subtext: '数据来自海口信息中心',
	        x: 'center'
	    },

	    series : seriesData 
	    /*[
	        {
	            type : 'pie',
	            center : ['10%', '30%'],
	            radius : radius,
	            x: '0%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:46, itemStyle : labelBottom},
	                {name:'省发改委', value:54,itemStyle : labelTop}
	            ]
	        },
	     
	        {
	            type : 'pie',
	            center : ['30%', '30%'],
	            radius : radius,
	            x:'20%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:56, itemStyle : labelBottom},
	                {name:  '省民政厅', value:44,itemStyle : labelTop}
	            ]
	        },
	     
	        {
	            type : 'pie',
	            center : ['50%', '30%'],
	            radius : radius,
	            x:'40%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:65, itemStyle : labelBottom},
	                {name:'省财政厅',value:35,itemStyle : labelTop}
	            ]
	        },
	      
	        {
	            type : 'pie',
	            center : ['70%', '30%'],
	            radius : radius,
	            x:'60%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:70, itemStyle : labelBottom},
	                {name:'省文化厅 ',value:30,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['90%', '30%'],
	            radius : radius,
	            x:'80%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:73, itemStyle : labelBottom},
	                {name:' 省人事厅', value:27,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['10%', '70%'],
	            radius : radius,
	            y: '55%',   // for funnel
	            x: '0%',    // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:78, itemStyle : labelBottom},
	                {name: '省司法厅', value:22,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['30%', '70%'],
	            radius : radius,
	            y: '55%',   // for funnel
	            x:'20%',    // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:78, itemStyle : labelBottom},
	                {name:  '省文化厅 ', value:22,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['50%', '70%'],
	            radius : radius,
	            y: '55%',   // for funnel
	            x:'40%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:78, itemStyle : labelBottom},
	                {name:  '省科技厅', value:22,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['70%', '70%'],
	            radius : radius,
	            y: '55%',   // for funnel
	            x:'60%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:83, itemStyle : labelBottom},
	                {name:'省水利厅', value:17,itemStyle : labelTop}
	            ]
	        },
	        {
	            type : 'pie',
	            center : ['90%', '70%'],
	            radius : radius,
	            y: '55%',   // for funnel
	            x:'80%', // for funnel
	            itemStyle : labelFromatter,
	            data : [
	                {name:'other', value:89, itemStyle : labelBottom},
	                {name:'省安全厅', value:11,itemStyle : labelTop}
	            ]
	        }
	    ]*/
	};
	tagProportionChart.setOption(option, true);
}

onloadPercentData = function(){
	var dom = document.getElementById("percentDiv");
	var tagProportionChart = echarts.init(dom);

	var percent =0;
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfoFullRate",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.imputationInfo;//获得归集的信息的完整率
				//percent = list[0].fullRate;
				percent=list[0].fullRate;
			}else{
				alert(r.msg);
			}
		}
	});
	
	function getData() {
	    return [{
	        value: percent,
	        itemStyle: {
	            normal: {
	                color: '#f2c967',
	                shadowBlur: 3,
	                shadowColor: '#f2c967'
	            }
	        }
	    }, {
	        value: 1-percent,
	        itemStyle: {
	            normal: {
	                color: 'transparent'
	            }
	        }
	    }];
	}
	var option = {
	    title: {
	        text: (percent * 100 +"").substr(0,5) + '%',
	        subtext: '完整率',
	        x: 'center',
	        y: '30%',
	        textStyle: {
	            color: '#f2c967',
	            fontSize: 15
	        },
	        subtextStyle: {
	            color: '#3da1ee',
	            fontSize: 12
	        }
	    },
	    animation: false,
	    series: [{
	        type: 'pie',
	        radius: ['60%', '80%'],
	        silent: true,
	        label: {
	            normal: {
	                show: false,
	            }
	        },
	        data: [{
	            itemStyle: {
	                normal: {
	                    color: '#3da1ee',
	                    shadowBlur: 2,
	                    shadowColor: '#3da1ee'
	                }
	            }
	        }]
	    }, {
	        name: 'main',
	        type: 'pie',
	        radius: ['60%', '80%'],
	        label: {
	            normal: {
	                show: false
	            }
	        },
	        data: getData()
	    }]
	};
	tagProportionChart.setOption(option, true);                    
	
}

onloadImputationData = function(){
	var textareaContent="";
	var tableContent="<table border='1'><tr><th>名称</th>" +
			"<th>完整率</th></tr>"; 
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfo/1",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.imputationInfo;//获得归集的信息
				for(var i=0;i<list.length;i++){
					tableContent=tableContent+"<tr>";
					tableContent=tableContent+"<td>";
					tableContent=tableContent+list[i].informationItem+"xxxxxxxx";
					tableContent=tableContent+"</td>";
					tableContent=tableContent+"<td>";
					tableContent=tableContent+list[i].informationRate;
					tableContent=tableContent+"</td>";
					
//					textareaContent =textareaContent +list[i].informationItem 
//					if(list[i].informationRate!=""){
//						textareaContent=textareaContent+"----"+list[i].informationRate+"\r\n";
//					}else{
//						textareaContent=textareaContent+"\r\n";
//					}
				}
				tableContent=tableContent+"</table>"
			}else{
				alert(r.msg);
			}
		}
	});
	$("#imputation").append(tableContent);
	
}

onloadNotImputationData = function(){
	var textareaContent="";
	var tableContent="<table border='1'><tr><th>名称</th>" +
			"</tr>"; 
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfo/0",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.imputationInfo;//获得归集的信息
				for(var i=0;i<list.length;i++){
					tableContent=tableContent+"<tr>";
					tableContent=tableContent+"<td>";
					tableContent=tableContent+list[i].informationItem+"xxxxxxxx";
					tableContent=tableContent+"</td>";
					
//					textareaContent =textareaContent +list[i].informationItem 
//					if(list[i].informationRate!=""){
//						textareaContent=textareaContent+"----"+list[i].informationRate+"\r\n";
//					}else{
//						textareaContent=textareaContent+"\r\n";
//					}
				}
				tableContent=tableContent+"</table>"
			}else{
				alert(r.msg);
			}
		}
	});
	$("#NOTimputation").append(tableContent);
	
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		corpDepartStatInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.corpDepartStatInfo = {};
		},
		update: function (event) {
			var departName = getSelectedRow();
			if(departName == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(departName)
		},
		saveOrUpdate: function (event) {
			var url = vm.corpDepartStatInfo.departName == null ? "../label/corpdepartstatinfo/save" : "../corpdepartstatinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.corpDepartStatInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var departNames = getSelectedRows();
			if(departNames == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../label/corpdepartstatinfo/delete",
				    data: JSON.stringify(departNames),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(departName){
			$.get("../label/corpdepartstatinfo/info/"+departName, function(r){
                vm.corpDepartStatInfo = r.corpDepartStatInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});


	                    