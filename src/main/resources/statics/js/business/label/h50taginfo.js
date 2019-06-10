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
		q:{
			tempCode:"bqTemp",
			tagNameUpload:"",
			tagStatusUpload:"",
			queryOrSelect:"0",
			doType:"",
			tagIdUpload:"-1",
		},
		res:{
			all:"",
			success:"",
			fail:"",
			tips:{}
		},
		showList : true,
		showInputRes:true,
		reportShow:true,
		showTagList : true,
		title : null,
		h50TagCategoryInfo:{},
		tagType:"",
		readonlyFlag:false,
		queryFlag:false,
		selectDate:null,
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
			$.get("../label/h50tagcategoryinfo/select/"+vm.tagType, function(r) {
				//添加标签类别
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
		},
		addTagCtgy : function() {//添加标签类别
			vm.readonlyFlag=false;
			vm.queryFlag=false;
			vm.showList = false;
			vm.title = "新增";
			vm.h50TagCategoryInfo = {};
			//设置默认值
			vm.h50TagCategoryInfo.tagCtgyStatus="0";
			vm.h50TagCategoryInfo.upTagCtgyId="0";
			vm.h50TagCategoryInfo.TagCtgyLevel="1";
			vm.h50TagCategoryInfo.tagTypeCd="A";
			vm.h50TagCategoryInfo.enabledDt=getDate(0);
			vm.h50TagCategoryInfo.disabledDt='2099-01-01';
			vm.tagType=0;
			//input赋值
			vm.menu = {
				parentName : null,
				parentId : 0,
				type : 3
			};
			//获得菜单树
			vm.getMenu();
		},
		update : function(event) {//修改
			var tagId = getTagId();
			if (tagId == null) {
				return;
			}
			//通过ID查询标签的内容进行修改
			$.get("../label/h50tagcategoryinfo/info/" + tagId, function(r) {
				vm.showList = false;
				vm.readonlyFlag=true;
				vm.queryFlag=false;
				vm.title = "修改";
				vm.h50TagCategoryInfo = {
						tagCtgyId:r.h50TagCategoryInfo.tagCtgyId,
						tagCtgyNm:r.h50TagCategoryInfo.tagCtgyNm,
						tagDesc:r.h50TagCategoryInfo.tagDesc,
						haveTagInd:r.h50TagCategoryInfo.haveTagInd,
						tagTypeCd:r.h50TagCategoryInfo.tagTypeCd,
						enabledDt:r.h50TagCategoryInfo.enabledDt.substring(0,10),
						disabledDt:r.h50TagCategoryInfo.disabledDt.substring(0,10),
						tagCtgyStatus:r.h50TagCategoryInfo.tagCtgyStatus,
						//修改赋值
						tagCtgyLevel:r.h50TagCategoryInfo.tagCtgyLevel//标签层级
					};
				vm.menu = {
						parentName : null,
						parentId :r.h50TagCategoryInfo.upTagCtgyId,
						menuId:r.h50TagCategoryInfo.tagCtgyId,
						name:r.h50TagCategoryInfo.tagCtgyNm
					};
				vm.tagType=0;
				vm.getMenu();
			});
		},
		query : function(event) {//查看
			var tagId = getTagId();
			if (tagId == null) {
				return;
			}
			//通过ID查询标签的内容进行修改
			$.get("../label/h50tagcategoryinfo/info/" + tagId, function(r) {
				vm.showList = false;
				vm.readonlyFlag=true;
				vm.queryFlag=true;
				vm.title = "查看";
				vm.h50TagCategoryInfo = {
						tagCtgyId:r.h50TagCategoryInfo.tagCtgyId,
						tagCtgyNm:r.h50TagCategoryInfo.tagCtgyNm,
						tagDesc:r.h50TagCategoryInfo.tagDesc,
						haveTagInd:r.h50TagCategoryInfo.haveTagInd,
						tagTypeCd:r.h50TagCategoryInfo.tagTypeCd,
						enabledDt:r.h50TagCategoryInfo.enabledDt.substring(0,10),
						disabledDt:r.h50TagCategoryInfo.disabledDt.substring(0,10),
						tagCtgyStatus:r.h50TagCategoryInfo.tagCtgyStatus,
						//修改赋值
						tagCtgyLevel:r.h50TagCategoryInfo.tagCtgyLevel//标签层级
					};
				vm.menu = {
						parentName : null,
						parentId :r.h50TagCategoryInfo.upTagCtgyId,
						menuId:r.h50TagCategoryInfo.tagCtgyId,
						name:r.h50TagCategoryInfo.tagCtgyNm
					};
				vm.tagType=0;
				vm.getMenu();
			});
		},
		upLine : function(event) {//上线
			var tagId = getTagId();
			if (tagId == null) {
				return;
			}
			$.ajax({
				type : "POST",
				url : "../label/h50tagcategoryinfo/info/"+tagId,
				contentType : "application/json",
				success : function(r) {
					if (r.code === 0 && r.h50TagCategoryInfo.tagCtgyStatus==0) {
						//通过ID查询标签的内容进行修改
						confirm('确定要上线选中的标签', function() {
							$.get("../label/h50tagcategoryinfo/upline/" + tagId, function(r) {
								if (r.code === 0) {
									alert("标签上线成功");
									Menu.table.refresh();
								} else {
									alert("标签上线失败，"+r.msg);
								}
							});
						});
					} else {
						alert("请选择其他标签进行上线");
					}
				}
			});
		},
		downLine : function(event) {//下线
			var tagId =getTagId();
			if (tagId == null) {
				return;
			}
			confirm('确定要下线选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../label/h50tagcategoryinfo/downTag/"+tagId,
					contentType : "application/json",
					success : function(r) {
						if (r.code === 0) {
							alert(r.msg);
							Menu.table.refresh();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		output : function(event) {//导出
			vm.q.doType="all";//查询所有 or 条件查询
			location.href = "../label/manager/downloadExcel/"+JSON.stringify(vm.q)
			
		},
		downloadTemplate : function(event) {//模板下载
			var temoCode="bqTemp";
			location.href = "../label/manager/downloadTemp/"+temoCode
		},
		closed:function(){
			vm.showList = true;
			vm.showInputRes = true;
		},
		del : function(event) {
			var tagId =getTagId();
			if (tagId == null) {
				return;
			}
			confirm('确定要删除选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : "../label/h50tagcategoryinfo/deleteTag/"+tagId,
					contentType : "application/json",
					success : function(r) {
						if (r.code === 0) {
							alert(r.msg);
							Menu.table.refresh();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate : function(event) {//标签类的添加修改
			if (vm.validatorTagCtgy()) {
				return;
			}
			var url = vm.readonlyFlag == false ? "../label/h50tagcategoryinfo/save" : "../label/h50tagcategoryinfo/update";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify(vm.h50TagCategoryInfo),
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
					// 弹出选择上级标签，获取上级标签的Id
					vm.menu.parentId = node[0].menuId;
					vm.menu.parentName = node[0].name;
					//上级Id
					vm.h50TagCategoryInfo.upTagCtgyId=node[0].menuId;
					//标签层级
					vm.h50TagCategoryInfo.TagCtgyLevel=node[0].level+1;
					//vm.menu.push({ "message": 'Baz' }) ;
					layer.close(index);
				}
			});
		},
		select:function(){
			console.log($("#beginEndDate").val().trim());
			loadTag($("#beginEndDate").val().trim());
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
		validatorTagCtgy : function() {
			if (isBlank(vm.h50TagCategoryInfo.tagCtgyNm)) {
				alert("标签类别名称不能为空");
				return true;
			}
			if (isBlank(vm.h50TagCategoryInfo.tagCtgyId)) {
				alert("标签类别ID不能为空");
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
		title : '标签名称',
		field : 'name',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '180px'
	}
	, {
		title : '标签ID',
		field : 'menuId',
		visible : false,
		align : 'center',
		valign : 'middle',
		width : '80px'
	}, {
		title : '状态',
		field : 'tagCtgyStatus',
		visible : false,
		align : 'center',
		valign : 'middle',
		width : '80px',
		formatter:function(row){
			return row.tagCtgyStatus==2?'启用':'禁用';
		}
	}]
	return columns;
};

function getTagId() {
	var selected = $('#menuTable').bootstrapTreeTable('getSelections');
	if (selected.length == 0) {
		alert("请选择一条记录");
		return ;
	} else {
		return selected[0].id;
	}
}

function loadTag(time){
	var url="../label/h50tagcategoryinfo/getTagCategoryTree";
	if(time!=null){
		url=url+"?time="+time;
	}
	var colunms = Menu.initColumn();
	var table = new TreeTable(Menu.id,url, colunms);
	table.setExpandColumn(1);
	table.setIdField("menuId");
	table.setCodeField("menuId");
	table.setParentCodeField("parentId");
	table.setExpandAll(false);
	//table.setExpandAll(true);
	table.init();
	Menu.table = table;
	$("#menuTable").on("click","tbody tr",function(){
		$(this).find("input[name=select_item]").prop('checked',true);
	});
	var height=$("#menuTable").find(".treegrid-tbody").css("height");
	var height=parseInt(height)-10;
	$("#menuTable").find(".treegrid-tbody").css("height",height);
}
$(function() {
	
	new AjaxUpload('#upload', {
		action : ctxPath +"/label/manager/upload", // url自己写
		name : 'file',
		autoSubmit : true,
		responseType : "json",
		onSubmit : function(file, extension) {
			if (!(extension && /^(xlsx)$/.test(extension.toLowerCase()))) {
				alert('只支持xlsx格式的文件！');
				return false;
			}
		},
		onComplete : function(file, r) {
			vm.showInputRes=false;
			vm.title="导入报告";
			vm.res.all=r.msg.data[0];
			vm.res.success=r.msg.data[1];
			vm.res.fail=r.msg.data[2];
			vm.res.tips=r.msg.tips;
		}
	});
	var time=null;
	loadTag(time);
});
