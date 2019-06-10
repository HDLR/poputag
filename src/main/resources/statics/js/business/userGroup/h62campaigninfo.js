$(function () {
    $("#jqGrid").jqGrid({
        url: '../userGroup/h62campaigninfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'campId', index: 'camp_id', width: 50, key: true },
			{ label: '群组名称', name: 'campNm', index: 'camp_nm', width: 100 }, 			
			{ label: '创建者', name: 'campIndsCd', index: 'camp_inds_cd', width: 80 }, 			
			{ label: '创建时间', name: 'createdTs', index: 'created_ts', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTs', index: 'updated_ts', width: 80 },
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
//                    return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.campId +')"/>' +
//                    '	<input type="button" class="btn-primary" value="修改" onclick="onClickModi('+ rowObject.campId +')"/>';
					
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.campId +')"/>';
                }
			}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
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
    });
});

//查看
function onClickQuery(campId){
	vm.clickQuery(campId);
}
//修改
function onClickModi(campId){
	vm.clickModi(campId);
}

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

var setting2 = {
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
		showTrees: false,
		title: null,
		h62CampaignInfo: {},
		readonlyFlag: true
	},
	methods: {
		closed:function(){
			vm.showList = true;
			vm.title = "浏览";
			changeOperationName("浏览");
			hidenParentClosed();
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.h62CampaignInfo = {sumPersionCount:0, tags:null, tagMap:null};
            vm.title = "添加";
            
            vm.$emit('changTitle', vm.title);
            
            vm.showList = false;
            vm.getTagsTree("");
            vm.showTrees=true;
            vm.readonlyFlag = false;
		},
		update: function (event) {
			var campId = getSelectedRow();
			if(campId == null){
				return ;
			}
			
			vm.h62CampaignInfo = {};
            vm.title = "修改";
            
            vm.queryCampaign(campId);
            vm.showList = false;
            vm.getTagsTree(campId);
            vm.showTrees=true;
            vm.readonlyFlag = true;
		},
		clickModi: function (campId) {
			vm.h62CampaignInfo = {};
            vm.title = "修改";
            
            vm.queryCampaign(campId);
            vm.showList = false;
            vm.getTagsTree(campId);
            vm.showTrees=true;
            vm.readonlyFlag = true;
		},
		clickQuery: function(campId){
			vm.h62CampaignInfo = {};
			vm.showTrees=false;
            vm.title = "查看";
            vm.getTagsTree2(campId);
            vm.queryCampaign2(campId);
            vm.showList = false;
            vm.readonlyFlag = true;
		},
		saveOrUpdate: function (event) {
			
			if(vm.validateCheck()){
				var url = vm.h62CampaignInfo.campId == null ? "../userGroup/h62campaigninfo/save" : "../userGroup/h62campaigninfo/update";
				$.ajax({
					type: "POST",
				    url: url,
				    contentType: "application/json",
				    data: JSON.stringify(vm.h62CampaignInfo),
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
			}
		},
		validateCheck: function (event){
			if("" == vm.h62CampaignInfo.campNm || null == vm.h62CampaignInfo.campNm){
				alert("【群组名称】不能为空！");
				return false;
			}
			if(null == vm.h62CampaignInfo.tags || vm.h62CampaignInfo.tags.length<1){
				alert("【标签】必须勾选！");
				return false;
			}
			return true;
		},
		del: function (event) {
			var campIds = getSelectedRows();
			if(campIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../userGroup/h62campaigninfo/delete",
				    contentType: "application/json",
				    data: JSON.stringify(campIds),
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
		getInfo: function(campId){
			$.get("../userGroup/h62campaigninfo/info/"+campId, function(r){
                vm.h62CampaignInfo = r.h62CampaignInfo;
            });
		},
		queryCampaign: function(campId){
			var data = "campId="+campId;
			ajaxFunctionPost("../userGroup/h62campaigninfo/queryCampaign", data, vm.successPro, vm.errorPro);
		},
		successPro: function(res){
			vm.h62CampaignInfo = res.h62CampaignInfo;
		},
		errorPro: function(res){
			
		},
		queryCampaign2: function(campId){
			var data = "campId="+campId;
			ajaxFunctionPost("../userGroup/h62campaigninfo/queryCampaign2", data, 
				function(res){
					vm.h62CampaignInfo = res.h62CampaignInfo;
					vm.$nextTick(function () {
						$('#categoryMap_0').addClass("bsclass");
						$('#categoryChild_0').show();
					});
					vm.queryPersonDistribute(campId, vm.h62CampaignInfo.sumPersionCount);
				}, 
				function(res){
					
				}
			);
		},
		categoryMapClick: function(param){
			$('div[id*="categoryMap_"]').each(function(){
			    $(this).removeClass("bsclass");
			});
			$("#categoryMap_" + param).addClass("bsclass");
			
			$('div[id*="categoryChild_"]').each(function(){
			    $(this).hide();
			});
			$("#categoryChild_" + param).show();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'campNm': vm.q.campNm},
                page:page
            }).trigger("reloadGrid");
		},
		getTagsTree: function(campId) {
			//加载菜单树
			$.get("../label/h50taginfo/queryAllTagsAndGroupTags?campId=" + campId, function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.tags);
				//展开所有节点
				ztree.expandAll(false);
				
				if("" != campId){
	    			var tags = r.checkTags;
	    			for(var i=0; i<tags.length; i++) {
	    				var node = ztree.getNodeByParam("tag_id", tags[i].tag_id);
	    				ztree.checkNode(node, true, false);
	    			}
				}
			});
	    },
	    getTagsTree2: function(campId) {
			//加载菜单树
			$.get("../label/h50taginfo/queryAllTagsAndGroupTags?campId=" + campId, function(r){
				ztree = $.fn.zTree.init($("#menuTree2"), setting2, r.tags);
				//展开所有节点
				ztree.expandAll(false);
				
				if("" != campId){
	    			var tags = r.checkTags;
	    			for(var i=0; i<tags.length; i++) {
	    				var node = ztree.getNodeByParam("tag_id", tags[i].tag_id);
	    				ztree.checkNode(node, true, false);
	    			}
				}
			});
	    },
	    queryPersonDistribute: function(campId, sumPersionCount){
	    	
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
					
					setEcharts4(campId, res, myChart4, sumPersionCount);//人口居住地是否已知
					
					showParentClosed();
				}, function(res){
					
				}
			);
		}
	},
	watch:{
		title: function(){
			changeOperationName(vm.title);
			if("查看" == vm.title || "修改" == vm.title || "添加" == vm.title){
				showParentClosed();
			}
		}
	},
	filters:{
		moneyFormat: function(value){
			var pattern = /(?=((?!\b)\d{3})+$)/g;
			var v = ("" + value).replace(pattern, ',');
			return v;
		}
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
	  })
	}
});

function setEcharts3(campId, reData, myChart){

    var uploadedDataURL = ctxPath + "/js/public/map/hai_nan_geo.json";
    
    $.get(uploadedDataURL, function(geoJson) {
    	var name = 'hn';
    	echarts.registerMap(name, geoJson);
    	var persons = reData.persons;
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
            }],
        }
        myChart.setOption(option, true);
    	myChart.hideLoading();
    });
}

function setEcharts4(campId, reData, myChart4, sumPersionCount){
    
    option = 
	    {
		 title : {
	        text: '居住地已知和未知比例(总人数：' + sumPersionCount + ')',
	        left: 'left',
            textStyle:{
            	fontSize:12
            }
	     },
	     tooltip: {
	         trigger: 'item',
	         formatter: "{a} <br/>{b}: {c} ({d}%)",
	     },
	     series: [
	         {
	             name:'是否已知居住地',
	             type:'pie',
	             radius: ['20%', '30%'],
	             label: {
	                 normal: {
	                     formatter: '{b}\n{d}%'
	                 }
	             },
	             data:[ 
	                 {value:reData.yz, name:'已知'},
	                 {value:(sumPersionCount - reData.yz), name:'未知'}
	             ]
	         }
	     ]
	 };
    
    myChart4.setOption(option, true);
    myChart4.hideLoading();
}

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var checkTags = new Array();
	for(var i=0; i<nodes.length; i++) {
		checkTags.push(nodes[i].tag_id);
	}
	
	ajaxFunctionPost("../label/h50taginfo/queryPersonCountSum", {"checkTags":checkTags.join()}, 
		function(r){
			vm.h62CampaignInfo.sumPersionCount = r.sumPersionCount;
			vm.h62CampaignInfo.tags = r.tags;
			vm.h62CampaignInfo.tagMap = r.tagMap;
		}, function(r){
			alert("获取信息异常");
		}
	);
}

function childClose(){
	vm.closed();
}