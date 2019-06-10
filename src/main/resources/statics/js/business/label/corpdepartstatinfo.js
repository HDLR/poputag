//jqGrid的wh设置
var widthSize=document.body.clientWidth/1.7;
var heightSize=document.body.clientHeight/4;

onloadLeftUp = function(){
	//获取数据
	var departName = [];
	var baseItem = [];
	var recordCount = [];
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getDeptTableStatus/default",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.depTableInfo;
				for(var i=0;i<list.length;i++){
					departName.push(list[i].departName);
					baseItem.push(list[i].baseItem);
					recordCount.push(list[i].recordCount);
				}
				
			}else{
				alert(r.msg);
			}
		}
	});
//	for(i=0;i<3;i++){
//		departName.push("测试一个");
//		baseItem.push(12);
//		recordCount.push(21)
//	}
	var dom = document.getElementById("leftUpContent");
	//动态创建DIV
	var divcontent=new Array();
	
	var count=0;//单位的个数。10个放入一个大div中
    var k=0;//用来作为前进下标数的标记
    //超链接点击切换div
    var lunboDiv=new Array();
	//做12个/div的展示
    console.log(departName)
    for(var c=0;c<departName.length/12;c++){
        var styleFlag=c==0?"block":"none"; 
        var id='Deptcontent'+c;
        divcontent.push("<div class='col-xs-12' id="+id+" style='height:90%;display:"+styleFlag+"'>");
        lunboDiv.push('<li style="list-style-type:none;display:inline;margin-right:5px">');
        lunboDiv.push('<a href="javascript:showDiv('+id+')" >Ο</>');
        lunboDiv.push('</li>');
		for(var i=0+k;i<departName.length;i++){
			var pngsrc=i%8;
			//最外层的DIV 包含图+字
			divcontent.push("<div class='col-xs-3' style='height:30%;margin:2px 0px 0px 0px !important '>");
			//图片DIV
			divcontent.push("<div class='table-responsive'><table background='../images/"+pngsrc+".png' style='overflow: hidden;background-size:100% 100%;background-repeat:no-repeat;text-align:center;height:98%;width:98%'>");
			divcontent.push("<tr>");
			//divcontent.push("<td style='text-align:center;font-weight: bold;font-size:3wh'><a href=javascript:show('"+departName[i]+"')>");
			divcontent.push("<td style='text-align:center;font-weight: bold;font-size:3wh'>");
			divcontent.push(departName[i]);
			divcontent.push("</td>");
			divcontent.push("</tr>");
			divcontent.push("<tr>");
			divcontent.push("<td style='text-align:center;font-weight: bold;color: #fff'>");
			divcontent.push("共享信息:"+baseItem[i]+"项");
			divcontent.push("</td>");
			divcontent.push("</tr>");
			divcontent.push("<tr>");
			divcontent.push("<td style='text-align:center;font-weight: bold;color: #fff'>");
			divcontent.push("数据:"+recordCount[i]+"条");
			divcontent.push("</td>");
			divcontent.push("</tr>");
			divcontent.push("</table></div>");
			divcontent.push("</div>");
			count++;
            if(count==12){//12个跳出循环
                count=0;
                break;
            }
		}
		divcontent.push("</div>");
        k=k+12;
    }
    //有两个DIV才做显示
    if(departName.length/12>1){
    	divcontent.push("<div class='col-md-12' id='lunboDiv' style='height:10%;text-align:center '><ul>"+lunboDiv.join("")+"</ul><div>");
    }
	//$("#lunboDiv").append(lunboDiv.join(""));
	$("#leftUpContent").append(divcontent.join(""));
}
showDiv = function(id){
    $("div[id^='Deptcontent']").hide();//全部隐藏、显示点击对应的div
    $(id).show();
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
	var fontSize=document.body.clientWidth/150;
	var labelFromatter = {
	    normal : {
	    	color: function(params) {
                // build a color map as your need.
                var colorList = [
                  '#9789ea','#ffa76b','#78e2cf','#ff8ed8','#27727B',
                   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                ];
                return colorList[params.seriesIndex]
            },
	        label : {
	        	position : 'center',
	            formatter:"{b}",
	            textStyle: {
	                baseline : 'top',
	                fontSize:fontSize
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
	var radius =[];
	var locationX=0 //定义开始绘制位置的距离
	var locationY=0 
	var locationList =[0,60,50,40,30,20];//定义开始绘制位置的距离的数组
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getDeptDataQualityStatus/default",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.depTableInfo;
			
//				list.push(
//						{DepartName:		'sss',
//							dataPercent:10
//						}
//				);
				
				if(list.length>5){
					locationX=locationList[5];
					locationY=30;
					radius = ['25%', '35%'];//定义圆圈内外圆的半径大小
					labelFromatter.normal.label.textStyle.fontSize=document.body.clientWidth/155;
				}else{
					locationX=locationList[list.length];
					locationY=50;
					radius = ['50%', '60%'];//定义圆圈内外圆的半径大小
					labelFromatter.normal.label.textStyle.fontSize=document.body.clientWidth/80;
				}
				count=0;
				for(var i=0;i<list.length&&i<10;i++){
					seriesData.push({
				          type : 'pie',
				          center : [(locationX-10)+'%', locationY+'%'],
				          radius : radius,
				          itemStyle : labelFromatter,
				          data : [
				             {name:(list[i].dataPercent *100 +"").substr(0,5)+"%", value:1-list[i].dataPercent, itemStyle : labelBottom},
				             {name:list[i].DepartName, value:list[i].dataPercent,itemStyle : labelTop}
				          ]
					});
					locationX+=20;
					count++;
					if(count==5){
						locationY=locationY+45;
						count=0;
						locationX=20;
					}
				}
			}else{
				alert(r.msg);
			}
		}
	});
	var dom = document.getElementById("leftDownContent");
	var tagProportionChart = echarts.init(dom);
	option = {
	    title : {
	        x: 'center'
	    },
	    series : seriesData 
	};
	tagProportionChart.setOption(option, true);
	//注册点击事件
	
	tagProportionChart.on("click", function (param){               
        //alert(param.name);
		deptName=param.name;
		//判断第一位是不是数字
		var data=param.name.substr(0,1)
		var re = /\d/;      //  \D代表数字
		if( re.test(data) ){   // 返回true,代表在字符串中找到了非数字。
		    return ;
		}else{
	        vm.showListTwo = false;
	    	vm.title = deptName+"数据质量详细列表";
	    	$("#jqGridTwo").jqGrid('setGridParam',{ 
	    		//根据tagCtgyId查询它下面的标签项
	            postData:{"departName":deptName}
	        }).trigger("reloadGrid");
	    	$("#jqGridTwo").jqGrid({
	            url: '../label/corpdepartstatinfo/depDataPercentDetailList',
	            postData:{"departName":deptName},
	            datatype: "json",
	            colModel: [			
	    			{ label: '部门名称', name: 'departName', index: 'depart_name', width: 50},
	    			{ label: '表名', name: 'tableName', index: 'table_name', width: 80 }, 			
	    			{ label: '数据标准率', name: 'percent', index: 'percent', width: 80,
	    				formatter: function (cellvalue, options, rowObject) {
	    					var per=rowObject.percent.toString();
	    					var percent=per.substring(0,5)+"%";
	    					return percent;
	    	            }
	    			}			
	            ],
	    		viewrecords: true,
	            height: heightSize,
	            width: widthSize,
	            rowNum: 10,
	    		rowList : [10,30,50],
	            //rownumbers: true, 
	            //rownumWidth: 25, 
	            //autowidth:true,
	            multiselect: false,
	            pager: "#jqGridPagerTwo",
	            jsonReader : {
	                root: "page.rows",
	                page: "page.nowpage",
	                total: "page.totalPage",
	                records: "page.total"
	            },
	            prmNames : {
	                page:"page", 
	                rows:"limit", 
	                order: "order"
	            },
	            gridComplete:function(){
	            	//隐藏grid底部滚动条
	            	$("#jqGridTwo").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
	            }
	        }).trigger("reloadGrid");
		}
    });
	//重新绘制、刷新频繁会卡
//	window.addEventListener("resize", function () { 
//		tagProportionChart.resize(); 
//	});
}

function show(deptName){
	vm.showList = false;
	vm.title = deptName+"详细列表";
	$("#jqGrid").jqGrid('setGridParam',{ 
		      postData:{"departName":deptName}
	}).trigger("reloadGrid");
    $("#jqGrid").jqGrid({
        url: '../label/corpdepartstatinfo/getDataImputationList',
        postData:{"departName":deptName},
        datatype: "json",
        colModel: [			
			{ label: '部门名称', name: 'departName', index: 'depart_name', width: 50 },
			{ label: '表名', name: 'tableName', index: 'table_name', width: 80 }, 			
			{ label: '记录数', name: 'rowCount', index: 'rowCount', width: 80 }			
        ],
		viewrecords: true,
        height: heightSize,
        width: widthSize,
        rowNum: 10,
		rowList : [10,30,50],
        //rownumbers: true, 
        //rownumWidth: 25, 
        //autowidth:true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.rows",
            page: "page.nowpage",
            total: "page.totalPage",
            records: "page.total"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    }).trigger("reloadGrid");
    
}

onloadPercentData = function(){
	var fontSize=document.body.clientWidth/80;
	var radius=document.body.clientWidth/17;
	var dom = document.getElementById("percentDiv");
	var tagProportionChart = echarts.init(dom);
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfoFullRate",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.imputationInfo;//获得归集的信息的完整率
				percent=list[0].fullRate;
			}else{
				alert(r.msg);
			}
		}
	});
	
	// ECharts 水球图插件，需要额外插件脚本，参见上方“脚本”
	// 完整配置参数参见：https://github.com/ecomfe/echarts-liquidfill
	var arr = ['middleLost', percent, [], '']
	option = {
	    title: {
	        top: '50%',
	        left: 'center',
	        text: (arr[1] * 10000 / 100).toFixed(2) + '%',//显示百分比
	        textStyle: {
	            color: '#fff',
	            fontStyle: 'normal',
	            fontWeight: 'normal',
	            fontSize: fontSize
	        },
	        subtext:  arr[3],
	        subtextStyle: {
	            color: '#000',
	            fontSize: fontSize
	        }
	    },
	    series: [{
	            type: 'liquidFill',
	            radius:radius,
	            data: [{
	                value: (arr[1] * 100 / 100).toFixed(2),//显示水位,
	                itemStyle: {
	                    normal: {
	                        opacity: 0.6
	                    }
	                }
	            }],
	            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                     // 0% 处的颜色   
                    offset: 0, color: '#f45387'  },
                   {
                    // 100% 处的颜色
                   offset: 1, color: '#fe985c' 
                  }], false),
	            backgroundStyle: {
	                color: '#efe'
	            },
	            label: {
	                normal: {
	                    formatter: '',
	                }
	            },
	            outline: {
	                itemStyle: {
	                    borderColor: '#86c5ff',
	                    borderWidth: 0
	                },
	                borderDistance:0
	            }
	        }
	    ]
	};
	tagProportionChart.setOption(option, true);   
}

onloadImputationData = function(){
	var textareaContent="";
	var count="";//取得总数
	var tableContent="<div class='table-responsive'><table class='table' border='0' style='font-size:2vh'><tr style='background:#ecf0f5' ><th  width='55%'>&nbsp;&nbsp;&nbsp;&nbsp;名称</th>" +
			"<th width='25%'>责任单位</th><th width='20%'>完整率</th></tr>"; 
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfo/1",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				var list=r.imputationInfo;//获得归集的信息
				count=r.ItemAllCount;
				for(var i=0;i<list.length;i++){
					tableContent=tableContent+"<tr>";
					tableContent=tableContent+"<td>&nbsp;&nbsp;&nbsp;&nbsp;";
					tableContent=tableContent+list[i].informationItem;
					tableContent=tableContent+"</td>";
					tableContent=tableContent+"<td>";
					tableContent=tableContent+list[i].depart;
					tableContent=tableContent+"</td>";
					tableContent=tableContent+"<td>";
					var appendPercent=list[i].informationRate.length>0?list[i].informationRate+"%":list[i].informationRate;
					tableContent=tableContent+appendPercent;
					tableContent=tableContent+"</td>";
				}
				tableContent=tableContent+"</table></div>"
			}else{
				alert(r.msg);
			}
		}
	});
	$("#imputation").append(tableContent);
	$("#baseCount").html(count+'（项）');
	var color = "#ecf0f5";
    $("#imputation tr:even td").css("background-color", color); //改变偶数行背景色
    $("#imputation tr:odd").attr("bg", "#fff");
    $("#imputation tr:even").attr("bg",color );
}

onloadNotImputationData = function(){
	var textareaContent="";
	var count="";
	var tableContent="<div class='table-responsive'><table class='table' border='0' style='font-size:2vh'>" +
		"<tr style='background:#ecf0f5'><th  width='70%' ;>&nbsp;&nbsp;&nbsp;&nbsp;名称</th>" +
		"<th width='30%'>责任单位</th></tr>"
			""; 
	$.ajax({
		type: "POST",
	    url: "../label/corpdepartstatinfo/getImputationInfo/0",
	    async : false,
	    success: function(r){
			if(r.code == 0){
				console.log(r)
				count=r.ItemAllCount;
				var list=r.imputationInfo;//获得归集的信息
				for(var i=0;i<list.length;i++){
					tableContent=tableContent+"<tr >";
					tableContent=tableContent+"<td >&nbsp;&nbsp;&nbsp;&nbsp;";
					tableContent=tableContent+list[i].informationItem;
					tableContent=tableContent+"</td>";
					tableContent=tableContent+"<td >" ;
					tableContent=tableContent+list[i].depart ;
					tableContent=tableContent+"</td >";
				}
				tableContent=tableContent+"</table></div>"
			}else{
				alert(r.msg);
			}
		}
	});
	//count="10000";
	$("#NOTimputation").append(tableContent);
	$("#ExCount").html(count+'（项）');
	var color = "#ecf0f5";
    $("#NOTimputation tr:even td").css("background-color", color); //改变偶数行背景色
    $("#NOTimputation tr:odd").attr("bg", "#ffffff");
    $("#NOTimputation tr:even").attr("bg", color);
}

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showListTwo: true,
		title: null,
		corpDepartStatInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		closed: function(){
			vm.showList = true;
			vm.showListTwo=true;
			//$("#jqGrid").jqGrid("clearGridData");
			//$("#jqGridTwp").jqGrid("clearGridData");
		}
	}
});
$(function () {
	//加载数据
	onloadLeftUp();
	onloadLeftDown();
	onloadPercentData();
	onloadImputationData();
	onloadNotImputationData();
	onloadImputationInfoAllCount();
	
});

	                    