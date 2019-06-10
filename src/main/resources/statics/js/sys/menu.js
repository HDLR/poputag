//$(function () {
//    $("#jqGrid").jqGrid({
//        url: '../menu/list',
//        datatype: "json",
//        colModel: [			
//			{ label: '菜单ID', name: 'menuId', index: "menu_id", width: 40, key: true },
//			{ label: '菜单名称', name: 'name', width: 60 },
//			{ label: '上级菜单', name: 'parentName', sortable: false, width: 60 },
//			{ label: '菜单图标', name: 'icon', sortable: false, width: 50, formatter: function(value, options, row){
//				return value == null ? '' : '<i class="'+value+' fa-lg"></i>';
//			}},
//			{ label: '菜单URL', name: 'url', width: 100 },
//			{ label: '授权标识', name: 'perms', width: 100 },
//			{ label: '类型', name: 'type', width: 50, formatter: function(value, options, row){
//				if(value === 0){
//					return '<span class="label label-primary">目录</span>';
//				}
//				if(value === 1){
//					return '<span class="label label-success">菜单</span>';
//				}
//				if(value === 2){
//					return '<span class="label label-warning">按钮</span>';
//				}
//			}},
//			{ label: '排序号', name: 'orderNum', index: "order_num", width: 50}
//        ],
//		viewrecords: true,
//        height: 385,
//        rowNum: 300,
//		rowList : [50,300],
//        rownumbers: true, 
//        rownumWidth: 25, 
//        autowidth:true,
//        multiselect: true,
//        pager: "#jqGridPager",
//        jsonReader : {
//            root: "page.list",
//            page: "page.currPage",
//            total: "page.totalPage",
//            records: "page.totalCount"
//        },
//        prmNames : {
//            page:"page", 
//            rows:"limit", 
//            order: "order"
//        },
//        gridComplete:function(){
//        	//隐藏grid底部滚动条
//        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
//        }
//    });
//});

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
	}
};
var ztree;

var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		menu : {
			parentName : null,
			parentId : 0,
			type : 1,
			orderNum : 0,
			icon:"",
			list:null,
			menuId:null,
			name:null,
			open:null,
			perms:null,
			url:null
			
		}
	},
	methods : {
		getMenu : function(menuId) {
			// 加载菜单树
			$.ajaxSettings.async = false;  
			$.get("../menu/select", function(r) {
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.menu = {
				parentName : null,
				parentId : 0,
				type : 1,
				orderNum : 0
			};
			vm.getMenu();
		},
		update : function(event) {
			var menuId = getMenuId();
			if (menuId == null) {
				return;
			}

			$.get("../menu/info/" + menuId, function(r) {
				vm.showList = false;
				vm.title = "修改";
				//vm.menu = r.menu;
				vm.menu = {
						parentName : null,
						parentId :r.menu.parentId,
						type : r.menu.type,
						orderNum :r.menu.orderNum,
						icon:r.menu.icon,
						list:r.menu.list,
						menuId:r.menu.menuId,
						name:r.menu.name,
						open:r.menu.open,
						perms:r.menu.perms,
						url:r.menu.url
					};
				vm.getMenu();
				//vm.menu = r.menu;
			});
		},
		closed:function(){
			vm.showList = true;
			//vm.menu.parentName = "123123";
		},
		del : function(event) {
			var menuIds = getMenuId();
			if (menuIds == null) {
				return;
			}

			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../menu/delete",
					contentType : "application/json",
					data : menuIds,
					success : function(r) {
						if (r.code === 0) {
							doSuccess('操作成功', function(index) {
								vm.reload();
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate : function(event) {
			if (vm.validator()) {
				return;
			}
			var url = vm.menu.menuId == null ? "../menu/save" : "../menu/update";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify(vm.menu),
				success : function(r) {
					if (r.code === 0) {
						doSuccess('操作成功', function(index) {
							vm.reload();
						});
					} else {
						alert(r.msg);
					}
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
					// 选择上级菜单
					vm.menu.parentId = node[0].menuId;
					vm.menu.parentName = node[0].name;
					//vm.menu.push({ "message": 'Baz' }) ;
					layer.close(index);
				}
			});
		},
		reload : function(event) {
			vm.showList = true;
			Menu.table.refresh();
			// vm.showList = true;
			// var page = $("#jqGrid").jqGrid('getGridParam','page');
			// $("#jqGrid").jqGrid('setGridParam',{
			// page:page
			// }).trigger("reloadGrid");
		},
		validator : function() {
			if (isBlank(vm.menu.name)) {
				alert("菜单名称不能为空");
				return true;
			}

			// 菜单
			if (vm.menu.type === 1 && isBlank(vm.menu.url)) {
				alert("菜单URL不能为空");
				return true;
			}
		}
	}
});

var Menu = {
	id : "menuTable",
	table : null,
	layerIndex : -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function() {
	var columns = [ {
		field : 'selectItem',
		radio : true
	}, {
		title : '菜单ID',
		field : 'menuId',
		visible : false,
		align : 'center',
		valign : 'middle',
		width : '80px'
	}, {
		title : '菜单名称',
		field : 'name',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '180px'
	}, {
		title : '上级菜单',
		field : 'parentName',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '100px'
	}, {
		title : '图标',
		field : 'icon',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '80px',
		formatter : function(item, index) {
			return item.icon == null ? '' : '<i class="' + item.icon + ' fa-lg"></i>';
		}
	}, {
		title : '类型',
		field : 'type',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '100px',
		formatter : function(item, index) {
			if (item.type === 0) {
				return '<span class="label label-primary">目录</span>';
			}
			if (item.type === 1) {
				return '<span class="label label-success">菜单</span>';
			}
			if (item.type === 2) {
				return '<span class="label label-warning">按钮</span>';
			}
		}
	}, {
		title : '排序号',
		field : 'orderNum',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '100px'
	}, {
		title : '菜单URL',
		field : 'url',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '160px'
	}, {
		title : '授权标识',
		field : 'perms',
		align : 'center',
		valign : 'middle',
		sortable : true
	} ]
	return columns;
};

function getMenuId() {
	var selected = $('#menuTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
		alert("请选择一条记录");
		return false;
	} else {
		return selected[0].id;
	}
}

$(function() {
	var colunms = Menu.initColumn();
	var table = new TreeTable(Menu.id, "../menu/list", colunms);
	table.setExpandColumn(2);
	table.setIdField("menuId");
	table.setCodeField("menuId");
	table.setParentCodeField("parentId");
	//table.setExpandAll(true);
	table.init();
	Menu.table = table;
	$("#menuTable").on("click","tbody tr",function(){
		$(this).find("input[name=select_item]").prop('checked',true);
	});
	var height=$("#menuTable").find(".treegrid-tbody").css("height");
	var height=parseInt(height)-10;
	$("#menuTable").find(".treegrid-tbody").css("height",height);
});
