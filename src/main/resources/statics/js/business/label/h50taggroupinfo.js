//添加返回函数设置为不可选择父节点
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey: "tag_id",
			pIdKey: "parent_id",
			rootPId : -1
		},
		key : {
			url : "nourl"
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

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var checkTags = new Array();
	for(var i=0; i<nodes.length; i++) {
		checkTags.push(nodes[i].tag_id);
	}
	ajaxFunctionPost("../label/h50taginfo/queryPersonCountSum", {"checkTags":checkTags.join()}, 
		function(r){
			vm.h50TaggroupInfo.tags = r.tags;
			console.log(vm.h50TaggroupInfo.tags);
		}, function(r){
			
		}
	);
}
/**
 * 分解组合的内容，用标签id查询出名称进行展示
 * @param group_content
 */
var tempTag=new Array();//存放标签名称
var ztree;
var groupContentMap = {};//存放组合标签的内容
MapToString = function(){
	var group_content="";
	$.each(groupContentMap,function(key,value){
		group_content =group_content + value +" ";
	});
	return group_content;
}

function split(group_content){
	var myarray = group_content.split(" ");
	var reg = /^[0-9]*$/;
	var checkTags = new Array();
	$(myarray).each(function(i,value){
		if (reg.test(value)) {
			checkTags.push(value);
		}    
	});
	$.ajax({
		type: "POST",
	    url: "../label/h50taginfo/queryPersonCountSum",
	    async : false,
	    data : {"checkTags":checkTags.join()},
	    dataType: "json",
	    success: function(r){
	    	tempTag = r.tags;
	    },
		error: function(r){
		}
	});
}

addContentToPanel = function(group_content){
	$("#group_content").html("");//设置为空
	groupContentMap={};//设置为空
	split(group_content);//调用查询标签名称
	var myarray = group_content.split(" ");
	var reg = /^[0-9]*$/;
	$(myarray).each(function(i,op){
		var id=Math.random().toString(36).substr(2);
		groupContentMap[id] = op;//把组标签的内容记录到map中
		if("("==op){
			$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;' ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>(</a>");
		}else if(")"==op){
			$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;' ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>)</a>");
		}else if("+"==op){
			$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;' ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>AND</a>");
		}else if("!"==op){
			$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;' ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>NOT</a>");
		}else if("|"==op){
			$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;' ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>OR</a>");
		}
		if (reg.test(op)) {
			$(tempTag).each(function(i,tags){
				if(tags.tag_id==op){
					$("#group_content").append("<a style='margin:3px 5px 0px 0px;' class='btn btn-primary' id="+id+" ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>"+tags.showparent_nm+":【"+tags.tag_ctgy_nm+":"+tags.tag_nm+"】");
				}
			});
		}
	});
}


removeDiv = function(id){
	//条件的移除，查看的时候无法移除标签
	if(!vm.queryFlag){
		var selectorStr="#"+id;
		delete groupContentMap[id];
		$(selectorStr).remove();
	}
}
var updateId=null;
updateDiv = function(id){
	//双击之后 ，点击修改id后
	vm.replaceId=id;
}

$(function () {
    $("#jqGrid").jqGrid({
        url: '../label/h50taggroupinfo/list',
        datatype: "json",
        colModel: [			
			{ label: '标签ID', name: 'tagId', index: 'tag_id', width: 50, key: true },
			{ label: '标签名称', name: 'tagNm', index: 'tag_nm', width: 80 }, 			
			{ label: '标签描述', name: 'tagDesc', index: 'tag_desc', width: 80 }, 			
			{ label: '标签内容', name: 'tagGroupContent', index: 'tag_group_content', width: 80 }, 			
			{ label: '标签类型', name: 'tagTypeCd', index: 'tag_type_cd', width: 80 },			
			{ label: '启用时间', name: 'enabledDt', index: 'enabled_dt', width: 80 ,formatter:"date", formatoptions: {newformat:'Y-m-d' }}, 			
			{ label: '禁用时间', name: 'disabledDt', index: 'disabled_dt', width: 80 ,formatter:"date", formatoptions: {newformat:'Y-m-d' }}, 			
			{ label: '状态', name: 'activeInd', index: 'active_ind', width: 80 ,formatter: function (cellvalue, options, rowObject) {
				status=rowObject.activeInd=="0"?"禁用":"启用";
				return status;
            }}, 			
			{ label: '创建时间', name: 'createdTs', index: 'created_ts', width: 80 }, 			
			{ label: '修改时间', name: 'updatedTs', index: 'updated_ts', width: 80 }		
			
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
		replaceId:"",
		replace:0,
		opType:"",
		title: null,
		h50TaggroupInfo: {sumPersionCount:0, tags:null,groupsTags:null},
		menu : {
			parentName : null,
			parentId : 0,
			menuId:null
		}
	},
	methods: {
		getMenu : function(menuId) {
			// 加载菜单树
			$.ajaxSettings.async = false;
			$.get("../label/h50taginfo/queryAllTags", function(r) {
				//添加叶子标签
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.tags);
				var node = ztree.getNodeByParam("menuId", vm.menu.parentId);
				ztree.selectNode(node);
				//vm.menu.parentName = node.name;
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
					//获取选择的菜单 进行填充到div展示
					//var nodes = ztree.getCheckedNodes(true);
					//var checkTags = new Array();
					//for(var i=0; i<nodes.length; i++) {
					//$("#tag").append(nodes[i].menuId);
					//checkTags.push(nodes[i].menuId);
					//}
					layer.close(index);
				}
			});
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.readonlyFlag=false;
			vm.queryFlag=false;
			vm.replace=false;
			vm.opType="add";
			vm.title = "新增";
			vm.h50TaggroupInfo = {sumPersionCount:0, tags:null,groupsTags:null};
			//设置默认值
			vm.replace=0;
			$("#group_content").html("");//设置为空
			groupContentMap={};//设置为空
			vm.h50TaggroupInfo.activeInd="0";
			vm.h50TaggroupInfo.tagTypeCd="A";
			vm.h50TaggroupInfo.enabledDt=getDate(0);
			vm.h50TaggroupInfo.disabledDt='2099-01-01';
			vm.getMenu();
		},
		addTag: function(tag){
			//标签的添加
			var id=Math.random().toString(36).substr(2);
			
			if(vm.replace==1){//进行替换 。
				$("#"+vm.replaceId).html(tag.showparent_nm+":【"+tag.tag_ctgy_nm+":"+tag.tag_nm+"】");
				groupContentMap[vm.replaceId] = tag.tag_id;
			}else{
				$("#group_content").append("<a style='margin:3px 5px 0px 0px;' class='btn btn-primary' id="+id+" ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>"+tag.showparent_nm+":【"+tag.tag_ctgy_nm+":"+tag.tag_nm+"】");
				groupContentMap[id] = tag.tag_id;//添加标签到map
			}
		},
		operator: function(op){
			var id=Math.random().toString(36).substr(2);
			
			if("("==op){
				if(vm.replace==1){//进行替换 。
					$("#"+vm.replaceId).html("(");
					groupContentMap[vm.replaceId] = op;//修改map中key 对于的value
				}else{
					$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;'  ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>(</a>");
					groupContentMap[id] = op;//添加操作到map
				}
			}else if(")"==op){
				if(vm.replace==1){//进行替换 。
					$("#"+vm.replaceId).html(")");
					groupContentMap[vm.replaceId] = op;//修改map中key 对于的value
				}else{
					$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;'  ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>)</a>");
					groupContentMap[id] = op;//添加操作到map
				}
			}else if("+"==op){
				if(vm.replace==1){//进行替换 。
					$("#"+vm.replaceId).html("AND");
					groupContentMap[vm.replaceId] = op;//修改map中key 对于的value
				}else{
					$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;'  ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>AND</a>");
					groupContentMap[id] = op;//添加操作到map
				}
			}else if("!"==op){
				if(vm.replace==1){//进行替换 。
					$("#"+vm.replaceId).html("NOT");
					groupContentMap[vm.replaceId] = op;//修改map中key 对于的value
				}else{
					$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;'  ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>NOT</a>");
					groupContentMap[id] = op;//添加操作到map
				}
			}else if("|"==op){
				if(vm.replace==1){//进行替换 。
					$("#"+vm.replaceId).html("OR");
					groupContentMap[vm.replaceId] = op;//修改map中key 对于的value
				}else{
					$("#group_content").append("<a class='btn btn-primary' id="+id+" style='margin:3px 5px 0px 0px;'  ondblclick=removeDiv('"+id+"') onClick=updateDiv('"+id+"')>OR</a>");
					groupContentMap[id] = op;//添加操作到map
				}
			}
		},
		update: function (event) {
			var tagId = getSelectedRow();
			if(tagId == null){
				return ;
			}
			vm.showList = false;
			vm.readonlyFlag=true;
			vm.queryFlag=false;
			vm.opType="update";
            vm.title = "修改";
            vm.replace=0;
            vm.h50TaggroupInfo = {sumPersionCount:0, tags:null,groupsTags:null};
            vm.getInfo(tagId);
            vm.getMenu();
            
		},
		select: function (event) {
			var tagId = getSelectedRow();
			if(tagId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "查看";
        	vm.readonlyFlag=true;
			vm.queryFlag=true;
			vm.opType="select";
			vm.replace=0;
            vm.getInfo(tagId)
			vm.getMenu();
		},
		saveOrUpdate: function (event) {
			vm.h50TaggroupInfo.tagGroupContent=MapToString();
			if (vm.validator()) {
				return;
			}
			var  url = vm.readonlyFlag == false ? "../label/h50taggroupinfo/save" : "../label/h50taggroupinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType : "application/json",
			    data: JSON.stringify(vm.h50TaggroupInfo),
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
			var tagIds = getSelectedRows();
			if(tagIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../label/h50taggroupinfo/delete",
				    data: JSON.stringify(tagIds),
					contentType : "application/json",
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
		getInfo: function(tagId){
			$.get("../label/h50taggroupinfo/info/"+tagId, function(r){
				if(r.code == 0){
					 	vm.h50TaggroupInfo = r.h50TaggroupInfo;
		                vm.h50TaggroupInfo.activeInd=r.h50TaggroupInfo.activeInd+"";
		    			vm.h50TaggroupInfo.tagTypeCd=r.h50TaggroupInfo.tagTypeCd+"";
		    			vm.h50TaggroupInfo.enabledDt=r.h50TaggroupInfo.enabledDt.substring(0,10);
		    			vm.h50TaggroupInfo.disabledDt=r.h50TaggroupInfo.disabledDt.substring(0,10);
		    			addContentToPanel(r.h50TaggroupInfo.tagGroupContent);
				}else{
					alert(r.msg);
					vm.h50TaggroupInfo = r.h50TaggroupInfo;
	                vm.h50TaggroupInfo.activeInd=r.h50TaggroupInfo.activeInd+"";
	    			vm.h50TaggroupInfo.tagTypeCd=r.h50TaggroupInfo.tagTypeCd+"";
	    			vm.h50TaggroupInfo.enabledDt=r.h50TaggroupInfo.enabledDt.substring(0,10);
	    			vm.h50TaggroupInfo.disabledDt=r.h50TaggroupInfo.disabledDt.substring(0,10);
	    			addContentToPanel(r.h50TaggroupInfo.tagGroupContent);
				}
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		},
		closed : function (){
			vm.reload();
		},
		validator : function() {
			if (isBlank(vm.h50TaggroupInfo.tagNm)) {
				alert("标签名称不能为空");
				return true;
			}
			if (isBlank(vm.h50TaggroupInfo.tagId)) {
				alert("标签ID不能为空");
				return true;
			}
			if (!isBlank(vm.h50TaggroupInfo.tagGroupContent)) {
				//判断是不是合法的
				var  isAnalyze;//是否可以解析
				$.ajax({
					type: "POST",
				    url: "../label/h50taggroupinfo/isLegal/"+vm.h50TaggroupInfo.tagGroupContent,
				    async : false,
				    dataType: "json",
				    success: function(resPublic){
				    	if(resPublic.code===0){
				    		isAnalyze=true;
				    	}else{
				    		isAnalyze=false;
				    	}
				    },
					error: function(resPublic){
					}
				});
				if(!isAnalyze){
					alert("组合标签无法解析，请重新编辑");
					return true;
				}
			}
		}
		
		
	}
});