$(function () {
	/*
    $("#jqGrid").jqGrid({
        url: '../userGroup/h62campaigninfo/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'campId', index: 'camp_id', width: 50, key: true },
			{ label: '群组名称', name: 'campNm', index: 'camp_nm', width: 100 }, 			
			{ label: '创建者', name: 'campIndsCd', index: 'camp_inds_cd', width: 80 }, 			
			{ label: '创建时间', name: 'createdTs', index: 'created_ts', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTs', index: 'updated_ts', width: 80 }
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
    */
});

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
		main_div_loading: true
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		query: function () {
			vm.reload();
		},
		getTagsTree: function() {
			//加载菜单树
			$.get("../statis/queryCampaignTress", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.campTress);
				//展开所有节点
				ztree.expandAll(true);
			});
	    },
		analysis: function(){
			var campIds = getSelectedRows();
			if(campIds == null){
				return ;
			}
			vm.showList = false;
			vm.main_div_loading = true;
			ajaxFunctionPost("../userGroup/analysis/list", {"campIds":campIds.join()}, 
				function(r){
					setEcharts(r.camps, null, "main_div", ['群热度分析']);
					vm.main_div_loading = false;
				}, function(r){
					
				}
			);
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'campNm': vm.q.campNm},
                page:page
            }).trigger("reloadGrid");
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
		vm.main_div_loading = true;
		ajaxFunctionPost("../userGroup/analysis/list", {"campIds":campIds.join()}, 
			function(r){
				setEcharts(r.camps, null, "main_div", ['群热度分析']);
				vm.main_div_loading = false;
			}, function(r){
				alert("调用系统异常，请联系管理人员定位");
			}
		);
	}else{
		vm.showList = true;
	}
	
}

function genData(reData) {
    var nameList = [];
    var countSumList = [];
    if(null != reData && reData.length > 0){
    	$.each(reData, function(i, eValue){
    		nameList.push(eValue.campNm);
    		countSumList.push(eValue.sum);
    	});
    }
    
    var legendData = [];
    var seriesData = [];
    var selected = {};
    for (var i = 0; i < nameList.length; i++) {
        legendData.push(nameList[i]);
        seriesData.push({
            name: nameList[i],
            value: countSumList[i]
        });
        selected[nameList[i]] = true;
    }

    return {
        legendData: legendData,
        seriesData: seriesData,
        selected: selected
    };
};
