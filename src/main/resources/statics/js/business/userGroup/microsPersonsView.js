$(function () {
	
	var paramsJosn = JSON.parse(viewParams);
	campIdP = paramsJosn.campId;
	 
    $("#jqGrid").jqGrid({
        url: ctxPath + '/userGroup/microscopicPicture/listByPage',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'userName', index: 'userName', width: 80 }, 			
			{ label: '性别', name: 'userSex', index: 'userSex', width: 50 }, 			
			{ label: '出生年代', name: 'birthYears', index: 'birthYears', width: 50 }, 			
			{ label: '操作', index: 'operate', align: 'center', width: 80, 
				formatter: function (cellvalue, options, rowObject) {
					nameMap[rowObject.userGuid] = rowObject.userName;
					return '<input type="button" class="btn-primary" value="查看明细" onclick="onClickQuery('+ rowObject.userGuid +')"/>';
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
        postData:{'campIdP': campIdP},
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
    
    vm.initData();
});

var nameMap = {};

//查看
function onClickQuery(userGuid){
	vm.clickQuery(userGuid);
}

var campIdP = null;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			campNm: null
		},
		campIdP: campIdP,
		showList: true,
		showLoading: false,
		title: null,
		userPerson: {},
		name:'',
		childs:[],
		parents:[],
		tagData:[],
		parentName:'明细',
		isShow: false
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
		clickQuery: function(userGuid){
			vm.userPerson = {};
            vm.title = "个人明细";
            vm.queryPersonDetail(userGuid);
            vm.showLoading = true;
            vm.showList = false;
            showParentClosed();
		},
		queryPersonDetail: function(userGuid){
			var data = {"userGuid": userGuid ,"name": nameMap[userGuid]};
			ajaxFunctionPost(ctxPath + "/userGroup/microscopicPicture/queryPersonDetail", data, vm.successPro, vm.errorPro);
		},
		successPro: function(res){
			vm.showLoading = false;
			vm.userPerson = res.userPerson;
			vm.name = vm.userPerson.name;
			//vm.parents = vm.userPerson.parents;
			
			vm.parents = vm.tagData;
			this.$nextTick(function () {
				vm.clickTag("基本属性");
			})
		},
		errorPro: function(res){
			
		},
		clickTag: function(tagName){
			console.log(tagName);
			vm.parentName = tagName;
			vm.childs = vm.userPerson.dataChild[tagName];
			$('div[id*="tag_"]').each(function(){
			    $(this).removeClass("onclickCampClass");
			});
	    	$("#tag_" + tagName).addClass("onclickCampClass");
	    	vm.isShow = true;
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'campIdP': vm.campIdP},
                page:page
            }).trigger("reloadGrid");
		},
		initData: function(){
			vm.tagData.push(
					{
						"name":"基本属性",
						"color":"#4da1ff",
						"img":"jbsx.png"
					},
					{
						"name":"家庭特征",
						"color":"#fc9b6f",
						"img":"jttx.png"
					},
					{
						"name":"工作特征",
						"color":"#ff7b8c",
						"img":"gztx.png"
					},
					{
						"name":"流动状况",
						"color":"#83d587",
						"img":"ldzk.png"
					},
					{
						"name":"资产状况",
						"color":"#72ddc6",
						"img":"jkzk.png"
					},
					{
						"name":"健康状况",
						"color":"#72ddc6",
						"img":"jkzk.png"
					},
					{
						"name":"专业领域",
						"color":"#7d8ef3",
						"img":"zyly.png"
					},
					{
						"name":"社会保障",
						"color":"#46a8f9",
						"img":"shbz.png"
					},
					{
						"name":"信用评价",
						"color":"#f5bc34",
						"img":"xypj.png"
					}
					
			);
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
