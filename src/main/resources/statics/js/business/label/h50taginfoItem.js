//添加返回函数设置为不可选择父节点
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
		onClick: treesOnClick
	}
};
var setting2 = {
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
			beforeClick: isAddTag
		}
	};
function zTreeBeforeClick(treeId, treeNode, clickFlag) {
	
    return !treeNode.isParent ;//当是父节点 返回false 不让选取
};

function isAddTag(treeId, treeNode, clickFlag) {
	
    return !treeNode.isParent && treeNode.tagCtgyStatus!=0;//父节点 和状态为无节点不可选中
};

function treesOnClick(event, treeId, treeNode){
	var tagCtgyId = treeNode.menuId=="0"?"":treeNode.menuId;
	var page=0;
	//设置导出条件
	vm.q.queryOrSelect="0";
	vm.q.tagIdUpload = tagCtgyId==""?"-1":tagCtgyId;
	
	$("#jqGrid").jqGrid('setGridParam',{ 
		url: ctxPath +'/label/manager/queryListById',
		//根据tagCtgyId查询它下面的标签项
        postData:{'tagCtgyId': tagCtgyId},
        page:page
    }).trigger("reloadGrid");
}

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
			tagLevelUpload:""
		},
		res:{
			all:"",
			success:"",
			fail:"",
			tips:{}
		},
		showList : true,
		showInputRes:true,
		outputSelect:true,
		title : null,
		h50TagInfo:{},
		tagType:"",
		readonlyFlag:false,
		queryFlag:false,
		menu : {
			parentName : null,
			parentId : 0,
			menuId:null,
			name:null,
		}
	},
	methods : {
		getMenu : function(menuId) {
			// 加载菜单树
			$.ajaxSettings.async = false;
			$.get(ctxPath +"/label/h50tagcategoryinfo/getTagTree/"+vm.tagType, function(r) {
				//添加叶子标签
				ztree = $.fn.zTree.init($("#menuTree"), setting2, r.menuList);
				var node = ztree.getNodeByParam("menuId",  vm.menu.parentId);
				ztree.selectNode(node);
				//vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
		},
		select : function(){
			vm.q.queryOrSelect="1";
			vm.q.tagIdUpload = "-2";
			var startTime="";
			var endTime="";
			var time=$("#beginEndDate").val().trim();
			if(time!=""){
				var timeArr=time.split("~");
				startTime=timeArr[0];
				endTime=timeArr[1];
			}
			var page=0;
			$("#jqGrid").jqGrid('setGridParam',{ 
				url:ctxPath +'/label/manager/queryTagCtyList',
				//根据tagCtgyId查询它下面的标签项
		        postData:{"endTime":endTime,"startTime":startTime,"tagStatus":vm.q.tagStatusUpload,"tagName":vm.q.tagNameUpload,"tagLevel":vm.q.tagLevelUpload},
		        page:page
		    }).trigger("reloadGrid");
		},
		
		getTagTree : function(menuId) {//左侧标签树
			// 加载菜单树
			$.ajaxSettings.async = false;
			vm.tagType="1";//1 表示有菜单项、
			$.get(ctxPath +"/label/h50tagcategoryinfo/getTagTree/"+vm.tagType, function(r) {
				//添加标签类别
				ztree = $.fn.zTree.init($("#tagTree"), setting, r.menuList);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				//vm.menu.parentName = node.name;
			})
			$.ajaxSettings.async = true;  
		},
		addTag : function() {//添加标签
			vm.showList = false;
			vm.readonlyFlag=false;
			vm.queryFlag=false;
			vm.title = "新增";
			vm.h50TagInfo = {};
			//获得菜单树
			vm.getMenu();
			//设置默认值
			vm.h50TagInfo.activeInd="0";
			vm.h50TagInfo.unknownInd="0";
			vm.h50TagInfo.tagTypeCd="A";
			vm.h50TagInfo.enabledDt=getDate(0);
			vm.h50TagInfo.disabledDt='2099-01-01';
			vm.tagType="1";//标签1 标签类别0
			//input赋值
			vm.menu = {
				parentName : null,
				parentId : 0,
				type : 3
			};
			
			
		},
		update : function(event) {//修改
			var tagId =getSelectedRow();
			if (tagId == null) {
				return;
			}
			//通过ID查询标签的内容进行修改
			$.get(ctxPath +"/label/manager/getInfo/" + tagId, function(r) {
				vm.showList = false;
				vm.readonlyFlag=true;
				vm.queryFlag=false;
				vm.title = "修改";
				vm.getMenu();
				vm.h50TagInfo = {
						//修改赋值/
						tagId:r.TagInfoEntity.tag_id,
						tagNm:r.TagInfoEntity.tag_nm,
						tagDesc:r.TagInfoEntity.tag_desc,
						tagTypeCd:r.TagInfoEntity.tag_type_cd,
						unknownInd:r.TagInfoEntity.unknown_ind,
						activeInd:r.TagInfoEntity.tag_status,
						enabledDt:r.TagInfoEntity.enabled_dt_str.substring(0,10),
						disabledDt:r.TagInfoEntity.disabled_dt_str.substring(0,10),
						tagCtgyId:r.TagInfoEntity.up_tid,
						personNumbers:r.TagInfoEntity.personNumbers
					};
				vm.menu = {
						parentName : r.TagInfoEntity.up_name,
						parentId :r.TagInfoEntity.up_tid,
						menuId:r.TagInfoEntity.tag_id,
						name:r.TagInfoEntity.tag_nm,
					};
				vm.tagType="1";
				
			});
		},
		query : function(event) {//查看
			var tagId =getSelectedRow();
			if (tagId == null) {
				return;
			}
			//通过ID查询标签的内容进行修改
			$.get(ctxPath +"/label/manager/getInfo/" + tagId, function(r) {
				vm.showList = false;
				vm.readonlyFlag=true;
				vm.queryFlag=true;
				vm.title = "查看";
				vm.getMenu();
				vm.h50TagInfo = {
						tagId:r.TagInfoEntity.tag_id,
						tagNm:r.TagInfoEntity.tag_nm,
						tagDesc:r.TagInfoEntity.tag_desc,
						tagTypeCd:r.TagInfoEntity.tag_type_cd,
						unknownInd:r.TagInfoEntity.unknown_ind,
						activeInd:r.TagInfoEntity.tag_status==0?"0":"1",
						enabledDt:r.TagInfoEntity.enabled_dt_str.substring(0,10),
						disabledDt:r.TagInfoEntity.disabled_dt_str.substring(0,10),
						personNumbers:r.TagInfoEntity.personNumbers
						//修改赋值
					};
				vm.menu = {
						parentName : r.TagInfoEntity.up_name,
						parentId :r.TagInfoEntity.up_tid,
						menuId:r.TagInfoEntity.tag_id,
						name:r.TagInfoEntity.tag_nm
					};
				vm.tagType="1";//查看到标签1 标签类别0
				
			});
		},
		upLine : function(event) {//上线
			var tagId =getSelectedRow();
			if (tagId == null) {
				return;
			}
			$.ajax({
				type : "POST",
				url : ctxPath +"/label/h50taginfo/info/"+tagId,
				contentType : "application/json",
				success : function(r) {
					if (r.code === 0 && r.h50TagInfo.activeInd==0) {
						//通过ID查询标签的内容进行修改
						confirm('确定要上线选中的标签', function() {
							$.get("../label/h50taginfo/upline/" + tagId, function(r) {
								if (r.code === 0) {
									alert("标签上线成功");
									vm.reload();
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
		downLine	:	function(){
			var tagId =getSelectedRow();
			if (tagId == null) {
				return;
			}
			confirm('确定要下线选中的记录？', function() {
				$.ajax({
					type : "POST",
					url : ctxPath +"/label/h50taginfo/downTag/"+tagId,
					contentType : "application/json",
					success : function(r) {
						if (r.code === 0) {
							vm.reload();
							alert(r.msg);
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		outSelected:function(){
			vm.title = "选择导出";
			vm.outputSelect=false;
		},
		output	:	function(flag){
			vm.outputSelect=true;
			if(flag=="all"){
				vm.q.doType="all";//查询所有 or 条件查询
			}else{
				vm.q.doType="list"
			}
			//location.href = "../label/manager/downloadExcel/bqTemp";
			//{"tagNameUpload":tagNameUpload,"tagStatusUpload":tagStatusUpload,
			//"queryOrSelect":queryOrSelect,"doType":doType,"tagIdUpload":tagIdUpload,
			//"queryOrSelect":queryOrSelect}
			location.href = ctxPath +"/label/manager/downloadExcel/"+JSON.stringify(vm.q)
			
		},
		closed :function (){
			vm.showList = true;
			vm.showInputRes = true;
			vm.outputSelect=true;
		},
		del : function(event) {
			var tagId =getSelectedRow();
			if (tagId == null) {
				return;
			}
			confirm('确定要删除选中的标签？', function() {
				$.ajax({
					type : "POST",
					url : ctxPath +"/label/h50taginfo/deleteTag/"+tagId,
					contentType : "application/json",
					success : function(r) {
						if (r.code === 0) {
							alert(r.msg);
							vm.reload();
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate : function(event) {//标签项的添加修改
			if (vm.validatorTag()) {
				return;
			}
			var  url = vm.readonlyFlag == false ? ctxPath +"/label/h50taginfo/save" : ctxPath +"/label/h50taginfo/update";
			$.ajax({
				type : "POST",
				url : url,
				contentType : "application/json",
				data : JSON.stringify(vm.h50TagInfo),
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
					vm.h50TagInfo.tagCtgyId=node[0].menuId;
					layer.close(index);
				}
			});
		},
		reload : function(event) {
			//vm.showList = true;
			//Menu.table.refresh();
			 vm.showList = true;
			 var page = $("#jqGrid").jqGrid('getGridParam','page');
			 $("#jqGrid").jqGrid('setGridParam',{
			 page:page
			 }).trigger("reloadGrid");
		},
		
		validatorTag : function() {
			if (isBlank(vm.h50TagInfo.tagNm)) {
				alert("标签名称不能为空");
				return true;
			}
			if (isBlank(vm.h50TagInfo.tagId)) {
				alert("标签ID不能为空");
				return true;
			}
			if (isBlank(vm.h50TagInfo.tagCtgyId)) {
				alert("上级标签不能为空");
				return true;
			}
		},
		// 节点级别
		getLevel : function() {
			initDataFunction("/label/manager/listLevelAll", null, function(dt) {
				if (dt != null) {
					var select = $("#tagLevel");
					select.empty();
					for ( var key in dt) {// 遍历json对象的每个key/value对,p为key
						select.append("<option value='" + dt[key] + "'>" + key + "</option>");
					}
					var dtjson = eval(dt);
				}
			});
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
		title : '标签ID',
		field : 'menuId',
		visible : false,
		align : 'center',
		valign : 'middle',
		width : '80px'
	}, {
		title : '标签名称',
		field : 'name',
		align : 'center',
		valign : 'middle',
		sortable : true,
		width : '180px'
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
function childClose(){
	history.go(-1);
}
$(function() {
	vm.getTagTree();
	vm.getLevel();
	//取得参数
	var tagCtgyId;
	if(viewParams!='${viewParams}'){
		var paramsJosn = JSON.parse(viewParams);
		tagCtgyId = paramsJosn.tagCtgyId;
		showParentClosed();
	}
	
	$("#jqGrid").jqGrid({
		url: ctxPath + '/label/manager/queryListById',
        datatype: "json",
        colModel: [			
			{ label: '标签ID', name: 'tag_id', index: 'tag_id', width: 100, key: true },
			{ label: '标签名称', name: 'tag_nm', index: 'tag_nm', width: 80 }, 			
			{ label: '标签描述', name: 'tag_desc', index: 'tag_desc', width: 80 }, 			
			{ label: '标签类型', name: 'tag_type_cd', index: 'tag_type_cd', width: 80 }, 			
			{ label: '标签类别ID', name: 'tag_ctgy_id', index: 'tag_ctgy_id', width: 80 }, 			
			{ label: '启用时间', name: 'enabled_dt_str', index: 'enabled_dt', width: 130,formatter:"date", formatoptions: {newformat:'Y-m-d' }}, 			
			{ label: '禁用时间', name: 'disabled_dt_str', index: 'disabled_dt', width: 130 }, 			
			{ label: '状态', name: 'tag_status_str', index: 'active_ind', width: 80 }, 			
			{ label: '创建时间', name: 'created_ts_str', index: 'created_ts', width: 130 }, 			
			{ label: '修改时间', name: 'updated_ts_str', index: 'updated_ts', width: 130 } 			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
		postData:{'tagCtgyId': tagCtgyId},
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
});
