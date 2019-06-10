$(function () {
    $("#jqGrid").jqGrid({
        url: '../tagreq/webserviceconf/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: 'API名称', name: 'apiName', index: 'api_name', width: 80 }, 			
			{ label: '创建时间', name: 'createDate', index: 'create_date', width: 80 }, 			
			{ label: '更新时间', name: 'updateDate', index: 'update_date', width: 80 }, 			
			{ label: '创建人', name: 'username', index: 'username', width: 80 },
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.id +')"/>';
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		webserviceConf: {},
		q:{
			apiName:null
		},
		readonlyFlag: false
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		query: function () {
			vm.reload();
		},
		clickQuery: function(id){
			vm.webserviceConf = {};
            vm.title = "查看";
            vm.getInfo(id);
            vm.showList = false;
            vm.readonlyFlag = true;
		},
		add: function(){
			vm.readonlyFlag = false;
			vm.showList = false;
			vm.title = "新增";
			vm.webserviceConf = {};
		},
		update: function (event) {
			vm.readonlyFlag = false;
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.webserviceConf.id == null ? "../tagreq/webserviceconf/save" : "../tagreq/webserviceconf/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.webserviceConf),
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
				    url: "../tagreq/webserviceconf/delete",
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
			$.get("../tagreq/webserviceconf/info/"+id, function(r){
                vm.webserviceConf = r.webserviceConf;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'apiName': vm.q.apiName},
                page:page
            }).trigger("reloadGrid");
		},
		back: function (event) {
			history.go(-1);
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