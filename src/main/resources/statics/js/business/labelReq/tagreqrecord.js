$(function () {
    $("#jqGrid").jqGrid({
        url: '../tagreq/record/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '标签需求名称', name: 'reqName', index: 'req_name', width: 80 }, 			
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }, 			
			{ label: '更新时间', name: 'updateDate', index: 'update_date', width: 80 }, 			
			{ label: '创建人', name: 'username', index: 'username', width: 80 }	,
			{ label: '审批状态', name: 'approvalStatus', index: 'approvalStatus', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue=='0'){
						return '<span class="label label-success">未审批</span>';
					}else if(cellvalue=='1'){
						return '<span class="label label-success">审批通过</span>';
					}else if(cellvalue=='-1'){
						return '<span class="label label-success">审批未通过</span>';
					}else{
						return '<span class="label label-danger">状态出错</span>';
					}
				} 
			},
    		{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.id +')"/>';
                }
    		},
			{ label: '审批操作', index: 'operate2', align: 'center', width: 120, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="通过审批" onclick="pass('+ rowObject.id +')"/>&nbsp;&nbsp;<input type="button" class="btn-primary" value="驳回审批" onclick="reject ('+ rowObject.id +')"/>';
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
//通过审批
function pass(id){
	vm.tagReqRecord={};
	vm.tagReqRecord.approvalStatus='1'
	vm.tagReqRecord.id=id
	vm.changeApprovalStatus();
}
//驳回审批
function reject(id){
	vm.tagReqRecord={};
	vm.tagReqRecord.approvalStatus='-1'
	vm.tagReqRecord.id=id
	vm.changeApprovalStatus();
}
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tagReqRecord: {},
		q:{
			reqName:null
		},
		readonlyFlag: false,
		apiList: null,
		methods:[
			{method: "get"},
			{method: "post"},
			{method: "put"},
		]
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
		clickQuery: function(id){
			vm.tagReqRecord = {};
            vm.title = "查看";
            vm.getInfo(id);
            vm.showList = false;
            vm.readonlyFlag = true;
            vm.queryAllApi();
		},
		add: function(){
			vm.readonlyFlag = false;
			vm.showList = false;
			vm.title = "新增";
			vm.tagReqRecord = {webserviceConfEntity:null};
			vm.queryAllApi();
		},
		update: function (event) {
			vm.readonlyFlag = false;
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.queryAllApi();
            vm.getInfo(id);
		},
		saveOrUpdate: function (event) {
			var url = vm.tagReqRecord.id == null ? "../tagreq/record/save" : "../tagreq/record/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.tagReqRecord),
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
		changeApprovalStatus: function () {
			$.ajax({
				type: "POST",
			    url: '../tagreq/record/changeApprovalStatus',
			    contentType: "application/json",
			    data: JSON.stringify(vm.tagReqRecord),
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
				    url: "../tagreq/record/delete",
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
			$.get("../tagreq/record/info/"+id, function(r){
                vm.tagReqRecord = r.tagReqRecord;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'reqName': vm.q.reqName},
                page:page
            }).trigger("reloadGrid");
		},
		toConf: function (event) {
			toNewViewByPost(null, ctxPath + "/labelReq/webserviceconf.html" );
		},
		queryAllApi: function(){
			ajaxFunctionPost(ctxPath + "/tagreq/webserviceconf/allGroup", null, 
				function(res){
					vm.apiList = res.list;
					console.log(vm.apiList);
				},
				function(e){
					alert("调用系统异常，请联系管理人员定位");
				}
			);
		},
		changOption: function(){
			var webserviceId = vm.tagReqRecord.webserviceId;
			if(null != webserviceId){
				$.get(ctxPath + "/tagreq/webserviceconf/info/"+webserviceId, function(r){
	                vm.tagReqRecord.webserviceConfEntity = r.webserviceConf;
	            });
			}
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

function childClose(){
	vm.closed();
}