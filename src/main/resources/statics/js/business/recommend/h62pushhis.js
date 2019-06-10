$(function () {
    $("#jqGrid").jqGrid({
        url: '../recommend/h62pushhis/list',
        datatype: "json",
        colModel: [			
        	{ label: '推送模板名称', name: 'temp_nm', index: 'temp_nm', width: 80 }, 	
        	{ label: '总条数', name: 'his_counts', index: 'his_counts', width: 80 },
			{ label: '成功条数', name: 'his_success', index: 'his_success', width: 80 }, 			
			{ label: '失败条数', name: 'his_fail', index: 'his_fail', width: 80 },
			{ label: '政策推送主题', name: 'push_nm', index: 'push_nm', width: 80 },
			{ label: '推送状态', name: 'his_statu', index: 'his_statu', width: 80 ,
				formatter: function (cellvalue, options, rowObject) {
					return rowObject.his_statu == 0? "进行中" : "已结束";
                }
			}, 			
			{ label: '创建者', name: 'username', index: 'username', width: 80 }, 
			{ label: '创建日期', name: 'create_dt', index: 'create_dt', width: 80 ,
				formatter:"date", formatoptions: {newformat:'Y-m-d'}
			}
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
		showList: true,
		title: null,
		h62PushHis: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.h62PushHis = {};
		},
		update: function (event) {
			var hisId = getSelectedRow();
			if(hisId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(hisId)
		},
		saveOrUpdate: function (event) {
			var url = vm.h62PushHis.hisId == null ? "../recommend/h62pushhis/save" : "../recommend/h62pushhis/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.h62PushHis),
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
			var hisIds = getSelectedRows();
			if(hisIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../recommend/h62pushhis/delete",
				    data: JSON.stringify(hisIds),
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
		getInfo: function(hisId){
			$.get("../recommend/h62pushhis/info/"+hisId, function(r){
                vm.h62PushHis = r.h62PushHis;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
	  })
	}
});