$(function () {
    $("#jqGrid").jqGrid({
        url: '../syshomepagemanager/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: '编号', width: 50, key: true },
			{ label: '创建日期', name: 'createDate', index: 'create_date', width: 80 }, 			
			{ label: '修改日期', name: 'updateDate', index: 'update_date', width: 80 }, 			
			//{ label: '菜单ID', name: 'menuId', index: 'menu_id', width: 80 }, 
			{ label: '首页菜单名称', name: 'menuName', index: 'menu_name', width: 80 }, 			
			{ label: '角色ID', name: 'roleId', index: 'role_id', width: 80 },
			{ label: '角色名称', name: 'roleName', index: 'role_name', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80 ,
				formatter: function (cellvalue, options, rowObject) {
					status=rowObject.status=="0"?
							'<span class="label label-danger">禁用</span>' : 
								'<span class="label label-success">启用</span>';
					return status;
	            }}, 
			//{ label: '用户ID', name: 'userId', index: 'user_id', width: 80 }			
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
			beforeClick: zTreeBeforeClick
		}
	};
	var ztree;
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
		
	    return !treeNode.isParent ;//当是父节点 返回false 不让选取
	};
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			menuName: null
		},
		selected:"A",
		showList: true,
		title: null,
		sysHomepageManager: {},
		queryFlag:true,
		menu : {
			parentName : null,
			parentId : 0,
			type : 1,
			menuId:null,
			name:null
		}
	},
	computed:{
	     options:function(){
	    	 var array= [];
	    	 $.get("../role/select", function(r){
					var roleInfoList=r.list;
					if (roleInfoList != null) {
						var select = $("#roleId");
						select.empty();
						$(roleInfoList).each(function(i,value){
							array.push({
								'text':value.roleName ,
								'value':value.roleId
							});
						});
					}
	    	 });
	       return array;
	     },
	},
	methods: {
		query: function () {
			vm.reload();
		},
		getMenu : function(menuId) {
			// 加载菜单树
			$.ajaxSettings.async = false;  
			$.get("../syshomepagemanager/select", function(r) {
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
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
					vm.sysHomepageManager.menuId = node[0].menuId;
					vm.sysHomepageManager.menuName = node[0].name;
					//vm.menu.push({ "message": 'Baz' }) ;
					layer.close(index);
				}
			});
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysHomepageManager = {};
			vm.sysHomepageManager.enableDate=getDate(0);
			vm.sysHomepageManager.disableDate='2099-01-01';
			vm.sysHomepageManager.status="1";
			vm.sysHomepageManager.roleId="1";
			vm.menu = {
					parentName : null,
					parentId : 0,
					type : 1,
					orderNum : 0
				};
			vm.getMenu();
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.menu = {
					parentName : null,
					parentId : 0,
					type : 1,
					orderNum : 0
				};
			vm.getMenu();
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysHomepageManager.id == null ? "../syshomepagemanager/save" : "../syshomepagemanager/update";
			if (vm.validator()) {
				return;
			}
			$.ajax({
				type: "POST",
			    url: url,
			    contentType : "application/json",
			    data: JSON.stringify(vm.sysHomepageManager),
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
				    url: "../syshomepagemanager/delete",
				    contentType : "application/json",
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
			$.get("../syshomepagemanager/info/"+id, function(r){
                vm.sysHomepageManager = r.sysHomepageManager;
    			vm.sysHomepageManager.enableDate=r.sysHomepageManager.enableDate.substring(0,10);
    			vm.sysHomepageManager.disableDate=r.sysHomepageManager.disableDate.substring(0,10);
    			vm.menu.parentId = r.sysHomepageManager.menuId;
				vm.menu.parentName = r.sysHomepageManager.menuName;
				vm.sysHomepageManager.menuId = r.sysHomepageManager.menuId;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'menuName': vm.q.menuName},
                page:page
            }).trigger("reloadGrid");
		},
		closed: function (){
			vm.showList = true;
		},
		validator : function() {
			if (isBlank(vm.sysHomepageManager.menuId)) {
				alert("首页菜单不能为空");
				return true;
			}
			if (isBlank(vm.sysHomepageManager.roleId)) {
				alert("角色不能为空");
				return true;
			}
		}
	}
});