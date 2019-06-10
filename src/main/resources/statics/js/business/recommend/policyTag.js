
var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		menu : {
			parentName : null,
			parent_id : 0,
			type : 1,
			tag_id:null,
			name:null,
			tagTypes:null
		}
	},
	methods : {
		getMenu : function() {
			// 加载菜单树
			$.ajaxSettings.async = false;  
			$.get("../recommend/h62recompolicy/queryAllPolicyTagType", function(r) {
				vm.tagTypes = r.tagTypes;
			})
			$.ajaxSettings.async = true;  
		},
		add : function() {
			vm.showList = false;
			vm.title = "新增";
			vm.menu = {
				parentName : null,
				parent_id : 0,
				type : 1
			};
			vm.getMenu();
		},
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		saveOrUpdate : function(event) {
			if (vm.validator()) {
				return;
			}
			var url = "../recommend/h62recompolicy/savePolicyTag";
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
		reload : function(event) {
			vm.showList = true;
			Menu.table.refresh();
		},
		validator : function() {
			if (isBlank(vm.menu.name)) {
				alert("政策标签名称不能为空");
				return true;
			}

			// 菜单
			if (vm.menu.type === 2 && isBlank(vm.menu.parent_id)) {
				alert("政策标签类别不能为空");
				return true;
			}
		}
	},
	watch:{
		title: function(){
			changeOperationName(vm.title);
		}
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
	  })
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
		title : '选择',
		field : 'selectItem',
		radio : true
	}, {
		title : '政策标签名称',
		field : 'name',
		align : 'center',
		valign : 'middle',
		width : '180px'
	}]
	return columns;
};

function gettag_id() {
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
	var table = new TreeTable(Menu.id, "../recommend/h62recompolicy/queryAllPolicyTags2", colunms);
	table.setExpandColumn(1);
	table.setIdField("tag_id");
	table.setCodeField("tag_id");
	table.setParentCodeField("parent_id");
	table.setExpandAll(false);
	table.init();
	Menu.table = table;
	$("#menuTable").on("click","tbody tr",function(){
		$(this).find("input[name=select_item]").prop('checked',true);
	});
	var height=$("#menuTable").find(".treegrid-tbody").css("height");
	var height=parseInt(height)-10;
	$("#menuTable").find(".treegrid-tbody").css("height",height);
});
