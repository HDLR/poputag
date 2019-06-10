$(function () {
    $("#jqGrid").jqGrid({
        url: '../recommend/h62policypush/list',
        datatype: "json",
        colModel: [			
			{ label: '推送Id', name: 'pushId', index: 'push_id', width: 50, key: true },
			{ label: '政策推送名称', name: 'pushNm', index: 'push_nm', width: 80 }, 			
			{ label: '政策名称', name: 'policyNm', index: 'policy_nm', width: 80 }, 			
			{ label: '筛选人群名称', name: 'campNm', index: 'camp_nm', width: 80 }, 			
			{ label: '推荐内容', name: 'tempNm', index: 'temp_nm', width: 80 }	,
			{ label: '创建时间', name: 'createDt', index: 'create_dt', width: 80 ,
				formatter:"date", formatoptions: {newformat:'Y-m-d'}
			}, 			
			{ label: '创建人', name: 'username', index: 'username', width: 80 }, 			
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.pushId +')"/>';
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
		h62PolicyPush: {},
		q:{
			pushNm: null
		},
		readonlyFlag: false,
		sending: false,
		sengMsg: null,
		confirming: false
//		policys:null,
//		camps:null,
//		temps:null
	},
	methods: {
		queryAllParams: function(){
			ajaxFunctionPost(ctxPath + "/recommend/h62policypush/queryAllParams", null, function(e){
//				vm.policys = e.params.policys;
//				vm.camps = e.params.camps;
//				vm.temps = e.params.temps;
				vm.h62PolicyPush = {policys:e.params.policys, camps:e.params.camps, temps:e.params.temps}
			}, function(e){
				alert("获取参数异常");
			});
		},
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
			vm.h62PolicyPush = {};
            vm.title = "查看";
            vm.getInfo(id);
//            vm.queryAllParams();
            vm.showList = false;
            vm.readonlyFlag = true;
		},
		add: function(){
			vm.readonlyFlag = false;
			vm.queryAllParams();
			vm.showList = false;
			vm.title = "新增";
//			vm.h62PolicyPush = {};
		},
		update: function (event) {
			vm.readonlyFlag = false;
			var pushId = getSelectedRow();
			if(pushId == null){
				return ;
			}
//			vm.queryAllParams();
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(pushId);
		},
		saveOrUpdate: function (event) {
			if(vm.validateCheck()){
				var url = vm.h62PolicyPush.pushId == null ? "../recommend/h62policypush/save" : "../recommend/h62policypush/update";
				$.ajax({
					type: "POST",
				    url: url,
				    contentType: "application/json",
				    data: JSON.stringify(vm.h62PolicyPush),
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
			var pushIds = getSelectedRows();
			if(pushIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../recommend/h62policypush/delete",
				    contentType: "application/json",
				    data: JSON.stringify(pushIds),
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
		pushPolicy: function(){
			var pushId = getSelectedRow();
			if(pushId == null){
				return ;
			}
			vm.confirming = true;
			vm.title = "发送";
			vm.sengMsg = "确定要推送选中的政策？";
			vm.sending = true;
		},
		pushPolicySend: function(){
			vm.confirming = false;
			var pushId = getSelectedRow();
			if(pushId == null){
				return ;
			}
			vm.sengMsg = "正在发送中，请耐心等候。。。";
			console.log(pushId);
			var data = {"pushId": pushId};
			ajaxFunctionPost(ctxPath + "/recommend/h62policypush/sendPush", data, function(r){
				if(r.code == 0){
					vm.sengMsg = "已向用户手机中发送模板信息，请注意查收。";
				}
			}, function(e){
				alert(e.msg);
			});
		},
		getInfo: function(pushId){
			$.get("../recommend/h62policypush/info2/"+pushId, function(r){
                vm.h62PolicyPush = r.h62PolicyPush;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'pushNm': vm.q.pushNm},
                page:page
            }).trigger("reloadGrid");
		},
		validateCheck:function (){
			if("" == vm.h62PolicyPush.pushNm || null == vm.h62PolicyPush.pushNm){
				alert("【政策推送名称】不能为空！");
				return false;
			}
			if("" == vm.h62PolicyPush.policyId || null == vm.h62PolicyPush.policyId){
				alert("【政策名称】不能为空！");
				return false;
			}
			if("" == vm.h62PolicyPush.campId || null == vm.h62PolicyPush.campId){
				alert("【筛选人群名称】不能为空！");
				return false;
			}
			if("" == vm.h62PolicyPush.tempId || null == vm.h62PolicyPush.tempId){
				alert("【推荐模板内容】不能为空！");
				return false;
			}
			if("" == vm.h62PolicyPush.pushDesc || null == vm.h62PolicyPush.pushDesc){
				alert("【政策推荐描述备注】不能为空！");
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