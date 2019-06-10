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
		labelTaskMana: {"applyId":null , "check":null, "processs":[]},
		showMana: true,
		czName: "请选择审核结果"
	},
	methods: {
		query: function () {
			vm.reload();
		},
		queryInfo: function (event) {
			var applyId = getSelectedRow();
			if(applyId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(applyId)
		},
		getInfo: function(applyId){
			$.get("../labelreq/apply/info/"+applyId, function(r){
                vm.labelReqApply = r.labelReqApply;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.showMana = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		showManaFuc: function(){
			var applyId = getSelectedRow();
			if(applyId == null){
				return ;
			}
			vm.showMana = false;
			
			vm.labelTaskMana = {"applyId":null , "check":null, "processs":[]};
			
			$.get("../label/taskmana/info/"+applyId, function(r){
                vm.labelTaskMana = r.labelTaskMana;
                vm.czName = "请选择审核结果";
                var v = vm.labelTaskMana.check;
                if('1' == v){
            		vm.czName = "审核通过";
            	}else if('2' == v){
            		vm.czName = "审核不通过";
            	}else if('3' == v){
            		vm.czName = "生产结束";
            	}
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.labelTaskMana.id == null ? "../label/taskmana/save" : "../label/taskmana/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.labelTaskMana),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
//							vm.showMana = true;
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		add: function(){
			vm.labelTaskMana.processs.push({"time": null, "process": null});
		},
		remove: function(){
			$("[id^='process_id_']").each(function(i, e){
				if(this.checked){
					vm.labelTaskMana.processs.splice(i, 1);
				}
			});
		}
	}
});

function selectCheck(v){
	if('1' == v){
		vm.czName = "审核通过";
	}else if('2' == v){
		vm.czName = "审核不通过";
	}else if('3' == v){
		vm.czName = "生产结束";
	}
	
	vm.labelTaskMana.check = v;
}
