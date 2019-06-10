$(function () {
    $("#jqGrid").jqGrid({
        url: '../labelreq/apply/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'applyId', index: 'apply_id', width: 50, key: true },
			{ label: '标签名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '标签类别', name: 'type', index: 'type', width: 80 }, 			
			{ label: '标签生产申请人', name: 'applyUserid', index: 'apply_userid', width: 80 }, 			
			{ label: '申请部门', name: 'dep', index: 'dep', width: 80 }, 			
			{ label: '审核结果', name: 'check', index: 'check', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue=='1'){
						return '<span class="label label-success">审核通过，等待生产结束</span>';
					}else if(cellvalue=='2'){
						return '<span class="label label-danger">审核未通过</span>';
					}else if(cellvalue=='3'){
						return '<span class="label label-success">生产结束</span>';
					}else{
						return '<span class="label label-success">未审核</span>';
					}
				} 
			},
			{ label: '需求提出时间', name: 'applyTime', index: 'apply_time', width: 80 }		
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
		q:{
			name: null
		},
		showList: true,
		showTimeAxis: true,
		title: null,
		labelReqApply: {},
		//申请apply
		//审核check
		//生产production
		//结束finish
		labelReqApplyProcess: {"apply":{}, "check":{}, "production":[], "finish":{}},
		now: null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.labelReqApply = {};
		},
		update: function (event) {
			var applyId = getSelectedRow();
			if(applyId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(applyId)
		},
		saveOrUpdate: function (event) {
			var url = vm.labelReqApply.applyId == null ? "../labelreq/apply/save" : "../labelreq/apply/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.labelReqApply),
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
			var applyIds = getSelectedRows();
			if(applyIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../labelreq/apply/delete",
				    contentType: "application/json",
				    data: JSON.stringify(applyIds),
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
		getInfo: function(applyId){
			$.get("../labelreq/apply/info/"+applyId, function(r){
                vm.labelReqApply = r.labelReqApply;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		showProcess: function(){
			var applyId = getSelectedRow();
			if(applyId == null){
				return ;
			}
			vm.labelReqApplyProcess = {"apply":{}, "check":{}, "production":[], "finish":{}};
			vm.showTimeAxis = false;
			$.get("../labelreq/apply/showProcess/"+applyId, function(r){
                vm.labelReqApplyProcess = r.labelReqApplyProcess;
                console.log(vm.labelReqApplyProcess);
                vm.now = r.now;
            });
		}
	}
});