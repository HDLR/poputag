$(function () {
    $("#jqGrid").jqGrid({
        url: '../label/corpdepartstatinfo/list',
        datatype: "json",
        colModel: [			
			{ label: '部门名称', name: 'departName', index: 'depart_name', width: 50},
			{ label: '部门标识', name: 'departFlag', index: 'depart_flag', width: 80 }, 			
			{ label: '表名', name: 'tableName', index: 'table_name', width: 80 }, 			
			{ label: '表中文名', name: 'tableNameZh', index: 'table_name_zh', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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
			deptName:""
		},
		showList: true,
		title: null,
		corpDepartStatInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		select : function(){
			$("#jqGrid").jqGrid('setGridParam',{ 
				url:'../label/corpdepartstatinfo/list',
				//根据tagCtgyId查询它下面的标签项
		        postData:{"departName":vm.q.deptName}
		    }).trigger("reloadGrid");
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.corpDepartStatInfo = {};
			
		},
		update: function (event) {
			var departName = getSelectedRow();
			if(departName == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(departName)
		},
		saveOrUpdate: function (event) {
			var url = vm.corpDepartStatInfo.departName == null ? "../corpdepartstatinfo/save" : "../corpdepartstatinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.corpDepartStatInfo),
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
			var departNames = getSelectedRows();
			if(departNames == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../corpdepartstatinfo/delete",
				    data: JSON.stringify(departNames),
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
		getInfo: function(departName){
			$.get("../corpdepartstatinfo/info/"+departName, function(r){
                vm.corpDepartStatInfo = r.corpDepartStatInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});