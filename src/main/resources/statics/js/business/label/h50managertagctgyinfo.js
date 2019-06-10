var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "menuId",
			pIdKey : "parentId",
			rootPId : -1
		},
		key : {
			url : "nourl"
		}
	},
	callback: {
		beforeClick: zTreeBeforeClick,
	}
};

function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	
    return !treeNode.isParent ;//当是父节点 返回false 不让选取
};

var ztree;
$(function () {
    $("#jqGrid").jqGrid({
        url: '../label/h50managertagctgyinfo/list',
        datatype: "json",
        colModel: [		
			{ label: '标签编号', name: 'tagCtgyId', index: 'tag_ctgy_id', width: 80 }, 			
			{ label: '标签名称', name: 'tagCtgyName', index: 'tag_ctgy_name', width: 80 }, 			
			{ label: '标签描述', name: 'desc', index: 'desc', width: 80 }, 			
			{ label: '启用时间', name: 'enabledDt', index: 'enabled_dt', width: 80,formatter:"date", formatoptions: {newformat:'Y-m-d' } }, 			
			{ label: '禁用时间', name: 'disabledDt', index: 'disabled_dt', width: 80,formatter:"date", formatoptions: {newformat:'Y-m-d' } }, 			
			{ label: '状态', name: 'status', index: 'status', width: 80 ,
				formatter: function (cellvalue, options, rowObject) {
					status=rowObject.status=="0"?"禁用":"启用";
					return status;
	            }}, 			
			{ label: '创建时间', name: 'createdTs', index: 'created_ts', width: 80,formatter:"date", formatoptions: {newformat:'Y-m-d' } }, 			
			{ label: '修改时间', name: 'updatedTs', index: 'updated_ts', width: 80,formatter:"date", formatoptions: {newformat:'Y-m-d' } }, 			
			{ label: '角色编号', name: 'roleId', index: 'role_id', width: 80 }	,
			{ label: '是否可见', name: 'showFlag', index: 'show_flag', width: 80 ,
				formatter: function (cellvalue, options, rowObject) {
					showFlag=rowObject.showFlag=="0"?"不可见":"可见";
					return showFlag;
	            }}	,
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		readonlyFlag:false,
		queryFlag:false,
		title: null,
		h50ManagerTagctgyinfo: {},
		menu : {
			parentName : null,
			parentId : 0,
			type : 1,
		}
	},
	methods: {
		getMenu : function(menuId) {
			// 加载菜单树
			$.ajaxSettings.async = false;
			$.get(ctxPath +"/label/h50tagcategoryinfo/getTagTree/"+vm.tagType, function(r) {
				//添加叶子标签
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				//vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.queryFlag=false;
			vm.title = "新增";
			vm.h50ManagerTagctgyinfo = {};
			vm.h50ManagerTagctgyinfo.enabledDt=getDate(0);
			vm.h50ManagerTagctgyinfo.disabledDt='2099-01-01';
			//input赋值
			vm.menu = {
				parentName : null,
				parentId : 0,
				type : 3
			};
			//获得菜单树
			vm.getMenu();
			vm.getRoleId();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.queryFlag=false;
            vm.title = "修改";
            vm.getInfo(id);
            vm.getRoleId();
		},
		select: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
			vm.queryFlag=true;
            vm.title = "查看";
            vm.getInfo(id);
            vm.getRoleId();
		},
		saveOrUpdate: function (event) {
			if (vm.validator()) {
				return;
			}
			var url = vm.h50ManagerTagctgyinfo.id == null ? "../label/h50managertagctgyinfo/save" : "../label/h50managertagctgyinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
				contentType : "application/json",
			    data: JSON.stringify(vm.h50ManagerTagctgyinfo),
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
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
					contentType : "application/json",
				    url: "../label/h50managertagctgyinfo/delete",
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
			$.get("../label/h50managertagctgyinfo/info/"+id, function(r){
				vm.getMenu();
				vm.h50ManagerTagctgyinfo = r.h50ManagerTagctgyinfo;
                vm.h50ManagerTagctgyinfo.status=r.h50ManagerTagctgyinfo.status+"";
    			vm.h50ManagerTagctgyinfo.showFlag=r.h50ManagerTagctgyinfo.showFlag+"";
    			vm.h50ManagerTagctgyinfo.enabledDt=r.h50ManagerTagctgyinfo.enabledDt.substring(0,10);
    			vm.h50ManagerTagctgyinfo.disabledDt=r.h50ManagerTagctgyinfo.disabledDt.substring(0,10);
    			vm.menu.parentName =r.h50ManagerTagctgyinfo.tagCtgyName;
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		validator : function() {
			if (isBlank(vm.h50ManagerTagctgyinfo.tagCtgyId)) {
				alert("请选择标签");
				return true;
			}
		},
		closed : function (){
			vm.showList = true;
		},
		// role_id
		getRoleId : function() {
			$.get("../sys/role/select", function(r){
				var roleInfoList=r.list;
				if (roleInfoList != null) {
					var select = $("#roleId");
					select.empty();
					$(roleInfoList).each(function(i,value){
						select.append("<option value='" + value.roleId + "'>" + value.roleName + "</option>");
					});
				}
            });
		},
		menuTree : function() {
			layer.open({
				type : 1,
				offset : '50px',
				skin : 'layui-layer-molv',
				title : "选择菜单",
				area : [ '300px', '450px' ],
				shade : 0,
				shadeClose : false,
				content : jQuery("#menuLayer"),
				btn : [ '确定', '取消' ],
				btn1 : function(index) {
					var node = ztree.getSelectedNodes();
					// 弹出选择上级标签，获取上级标签的Id
					vm.menu.parentId = node[0].menuId;
					vm.menu.parentName = node[0].name;
					vm.h50ManagerTagctgyinfo.tagCtgyId=node[0].menuId;
					vm.h50ManagerTagctgyinfo.tagCtgyName=node[0].name;
					layer.close(index);
				}
			});
		}
	}
});