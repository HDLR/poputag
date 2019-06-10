$(function () {
    $("#jqGrid").jqGrid({
        url: '../recommend/h62recompolicy/list',
        datatype: "json",
        colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '政策ID', name: 'policyId', index: 'policy_id', width: 80 }, 			
			{ label: '政策名称', name: 'policyNm', index: 'policy_nm', width: 80 }, 			
			/*{ label: '政策概要信息', name: 'policyCont', index: 'policy_cont', width: 80 }, 			
			{ label: '政策外连接', name: 'policyHref', index: 'policy_href', width: 80 }, 	*/		
			{ label: '创建人', name: 'userName', index: 'userName', width: 80 }	,		
			{ label: '创建时间', name: 'createDt', index: 'create_dt', width: 80 ,
				formatter:"date", formatoptions: {newformat:'Y-m-d'}
			},
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.id +')"/>';
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
function onClickQuery(id){
	vm.clickQuery(id);
}
//修改
function onClickModi(id){
	vm.clickModi(id);
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

var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		showTrees: false,
		readonlyFlag: false,
		title: null,
		h62RecomPolicy: {},
		q:{
			policyNm: null
		}
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
		clickModi: function (id) {
			vm.h62RecomPolicy = {};
            vm.title = "修改";
            
            vm.getInfo(id);
            vm.showList = false;
            vm.getTagsTree(id);
            vm.showTrees=true;
            vm.readonlyFlag = false;
		},
		clickQuery: function(id){
			vm.h62RecomPolicy = {};
			vm.showTrees=false;
            vm.title = "查看";
            vm.getInfo(id);
            vm.showList = false;
            vm.readonlyFlag = true;
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.h62RecomPolicy = {policyId:null, tags:null, tagMap:null};
			vm.getTagsTree("");
            vm.showTrees=true;
            vm.readonlyFlag = false;
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.showTrees = true;
            vm.title = "修改";
            vm.getTagsTree(id);
            vm.getInfo(id);
            vm.readonlyFlag = false;
		},
		saveOrUpdate: function (event) {
			if(vm.validateCheck()){
				var url = vm.h62RecomPolicy.id == null ? "../recommend/h62recompolicy/save" : "../recommend/h62recompolicy/update";
				$.ajax({
					type: "POST",
				    url: url,
				    contentType: "application/json",
				    data: JSON.stringify(vm.h62RecomPolicy),
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
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../recommend/h62recompolicy/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get("../recommend/h62recompolicy/info/"+id, function(r){
                vm.h62RecomPolicy = r.h62RecomPolicy;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'policyNm': vm.q.policyNm},
                page:page
            }).trigger("reloadGrid");
		},
		getTagsTree: function(id) {
			//加载菜单树
			$.get("../recommend/h62recompolicy/queryAllPolicyTags?id=" + id, function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.tags);
				//展开所有节点
				ztree.expandAll(false);
				
				if("" != id){
	    			var tags = r.checkTags;
	    			for(var i=0; i<tags.length; i++) {
	    				var node = ztree.getNodeByParam("tag_id", tags[i].tag_id);
	    				ztree.checkNode(node, true, false);
	    			}
				}
			});
	    },
	    handleInput: function(e){
	    	vm.h62RecomPolicy.policyId = e.target.value.replace(/[^\d]/g,'');
	    },
	    validateCheck:function (){
			if("" == vm.h62RecomPolicy.policyId || null == vm.h62RecomPolicy.policyId){
				alert("【政策编号】不能为空！");
				return false;
			}
			if("" == vm.h62RecomPolicy.policyNm || null == vm.h62RecomPolicy.policyNm){
				alert("【政策名称】不能为空！");
				return false;
			}
			if("" == vm.h62RecomPolicy.policyCont || null == vm.h62RecomPolicy.policyCont){
				alert("【政策概要信息】不能为空！");
				return false;
			}
			if("" == vm.h62RecomPolicy.policyHref || null == vm.h62RecomPolicy.policyHref){
				alert("【政策外连接】不能为空！");
				return false;
			}
			if(null == vm.h62RecomPolicy.tags || vm.h62RecomPolicy.tags.length<1){
				alert("【标签】必须勾选！");
				return false;
			}
			return true;
		}
	},
	watch:{
		title: function(){
			changeOperationName(vm.title);
			if("查看" == vm.title || "修改" == vm.title || "新增" == vm.title){
				showParentClosed();
			}
		}
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
	  })
	}
});

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var checkTags = new Array();
	for(var i=0; i<nodes.length; i++) {
		checkTags.push(nodes[i].tag_id);
	}
	
	ajaxFunctionPost("../recommend/h62recompolicy/checkTagsName", {"checkTags":checkTags.join()}, 
		function(r){
			vm.h62RecomPolicy.tags = r.tags;
			vm.h62RecomPolicy.tagMap = r.tagMap;
		}, function(r){
			
		}
	);
}

function childClose(){
	vm.closed();
}