$(function () {
    $("#jqGrid").jqGrid({
        url: '../recommend/h62policypushtemp/list',
        datatype: "json",
        colModel: [			
			{ label: '推送模板Id', name: 'tempId', index: 'temp_id', width: 50, key: true },
			{ label: '模板名称', name: 'tempNm', index: 'temp_nm', width: 80 }, 			
			{ label: '推送内容', name: 'tempContent', index: 'temp_content', width: 80 }, 			
			{ label: '创建日期', name: 'createDt', index: 'create_dt', width: 80 ,
				formatter:"date", formatoptions: {newformat:'Y-m-d'}
			}, 			
			{ label: '创建者', name: 'userName', index: 'userName', width: 80 }, 			
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.tempId +')"/>';
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
		h62PolicyPushTemp: {},
		q:{
			tempNm: null
		},
		readonlyFlag: false
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
			vm.h62PolicyPushTemp = {};
            vm.title = "查看";
            vm.getInfo(id);
            vm.showList = false;
            vm.readonlyFlag = true;
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.h62PolicyPushTemp = {};
			vm.readonlyFlag = false;
		},
		update: function (event) {
			var tempId = getSelectedRow();
			if(tempId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(tempId);
            vm.readonlyFlag = false;
		},
		saveOrUpdate: function (event) {
			if(vm.validateCheck()){
				var url = vm.h62PolicyPushTemp.tempId == null ? "../recommend/h62policypushtemp/save" : "../recommend/h62policypushtemp/update";
				$.ajax({
					type: "POST",
				    url: url,
				    contentType: "application/json",
				    data: JSON.stringify(vm.h62PolicyPushTemp),
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
			}
		},
		del: function (event) {
			var tempIds = getSelectedRows();
			if(tempIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../recommend/h62policypushtemp/delete",
				    contentType: "application/json",
				    data: JSON.stringify(tempIds),
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
		getInfo: function(tempId){
			$.get("../recommend/h62policypushtemp/info/"+tempId, function(r){
                vm.h62PolicyPushTemp = r.h62PolicyPushTemp;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'tempNm': vm.q.tempNm},
                page:page
            }).trigger("reloadGrid");
		},
		validateCheck:function (){
			if("" == vm.h62PolicyPushTemp.tempNm || null == vm.h62PolicyPushTemp.tempNm){
				alert("【模板名称】不能为空！");
				return false;
			}
			if("" == vm.h62PolicyPushTemp.tempContent || null == vm.h62PolicyPushTemp.tempContent){
				alert("【推送内容】不能为空！");
				return false;
			}
			return true;
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