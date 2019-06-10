$(function () {
    $("#jqGrid").jqGrid({
        url: '../userGroup/h62campaigninfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'campId', index: 'camp_id', width: 50, key: true },
			{ label: '群组名称', name: 'campNm', index: 'camp_nm', width: 100 }, 			
			{ label: '创建者', name: 'campIndsCd', index: 'camp_inds_cd', width: 80 }, 			
			{ label: '创建时间', name: 'createdTs', index: 'created_ts', width: 80 }, 			
			{ label: '更新时间', name: 'updatedTs', index: 'updated_ts', width: 80 },
			{ label: '操作', index: 'operate', align: 'center', width: 150, 
				formatter: function (cellvalue, options, rowObject) {
					return '<input type="button" class="btn-primary" value="查看明细" onclick="onClickQuery('+ rowObject.campId +')"/>	';
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

//查看
function onClickQuery(campId){
	var params = {"campId":campId};
	toNewViewByPost(params, "../toNewView/userGroup/microsPersonsView");
}
//修改
function downloadPeople(campId){
	vm.onclickDownload(campId);
}


var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			campNm: null
		},
		showList: true,
		title: null,
		showLoading: false,
		showMsg: null,
		id: null
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'campNm': vm.q.campNm},
                page:page
            }).trigger("reloadGrid");
		},
		onclickDownload: function(campId){
			vm.showList = false;
			vm.title = "下载信息提示";
			vm.id = campId;
			vm.showMsg = "正在努力加载中...";
			vm.showLoading = true;
			
			var data = "campId="+campId;
			ajaxFunctionPost(ctxPath + "/userGroup/h62campaigninfo/queryCampaign", data, 
				function(res){
					var countSum = res.h62CampaignInfo.sumPersionCount;
					var addMsg = countSum > 1000? "下载需要较长的时间，" : "";
					vm.showMsg = "群组【<span style='color:#3300CC'>"+res.h62CampaignInfo.campNm+"</span>】" +
							"含有<span style='color:red'>"+countSum+"</span>个人员的明细数据，" + addMsg + 
									"</br>确认需要导出吗？";
					vm.showLoading = false;
				}, 
				function(e){
					alert("调用系统异常，请联系管理人员定位");
				}
			);
			
		},
		downLoadSend: function(){
			console.log(vm.id);
			
			vm.showMsg = "人员明细正在努力下载中，需要较长时间，请耐心等候...";
			vm.showLoading = true;
			
			var form = $("<form></form>")
	        form.attr('action', ctxPath + "/userGroup/microscopic/downLoadExcel")
	        form.attr('method','post')
	        input1 = $("<input type='hidden' name='campId'/>")
	        input1.attr('value', vm.id)
	        form.append(input1);
			form.appendTo("body");
	        form.css('display','none');
	        form.submit();
		}
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
	  })
	}
});
