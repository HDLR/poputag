$(function () {
	queryJqGrid();
});

function queryJqGrid(){

	$("#jqGrid").jqGrid({
        url: '../solr/query/byQueryValue',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'popu_nm', index: 'popu_nm', width: 80 }, 			
			{ label: '性别', name: 'gende', index: 'gende', width: 80 }, 			
			{ label: '身份证号码', name: 'id_card_num', index: 'id_card_num', width: 100 }, 
			{ label: '户籍地址', name: 'hshld_addr', index: 'hshld_addr', width: 200 }, 
			{ label: '操作', index: 'operate', align: 'center', width: 50, 
				formatter: function (cellvalue, options, rowObject) {
					nameMap[rowObject.id] = rowObject.popu_nm;
					cardId[rowObject.id] = rowObject.id_card_num;
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.id +')"/>';
                }
			}
        ],
		viewrecords: true,
        height: 340,
        rowNum: 8,
		rowList : [8,20,30],
        rownumbers: true, 
        rownumWidth: 80, 
        autowidth:true,
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
    })
}

var nameMap = {};
var cardId = {};

var vm = new Vue({
	el:'#rrapp',
	data:{
		queryValue:null,
		showList:false,
		title:null,
		childs:[],
		parents:[],
		parentName:'明细',
		popuName:null,
		popuId:null,
		clickId:null,
		toId:null
	},
	methods: {
		closed:function(){
			vm.showList = false;
			vm.title = "浏览";
			changeOperationName("浏览");
			hidenParentClosed();
		},
		categoryClick: function(index, type){
			
			vm.queryData(type);
			
	    	$('div[id*="category_"]').each(function(){
			    $(this).removeClass("onclickCampClass");
			});
	    	$("#category_" + index).addClass("onclickCampClass");
	    },
	    queryData: function(type){
	    	
	    	$('div[id*="category_"]').each(function(){
			    $(this).removeClass("onclickCampClass");
			});
	    	
			$("#jqGrid").jqGrid('setGridParam',{ 
	    		//根据tagCtgyId查询它下面的标签项
	            postData:{'queryValue': vm.queryValue, 'type': type},
	            page:1
	        }).trigger("reloadGrid");
		},
	    queryById: function(id){
	    	vm.title = "查看";
	    	vm.clickId = id;
	    	vm.showList = true;
			vm.popuName = nameMap[id];
			vm.clickTag("01", "人口基础信息");//默认打开"人口基础信息"
			
			vm.addTag();
	    },
	    queryByCode: function(code){
	    	var data = {"id": vm.clickId ,"code": code};
	    	ajaxFunctionPost(ctxPath + "/solr/query/byId", data, 
				function(res){
					vm.childs = res.popuInfo;
					showParentClosed();
				}, function(res){
					
				}
			);
	    },
	    clickTag: function(code, tagName){
	    	if("13" == code){
	    		window.open(clientUrl + "&cardId=" + vm.toId + "&name=" + vm.popuName);
	    	}else{
	    		vm.parentName = tagName;
				vm.queryByCode(code);
				$('div[id*="tag_"]').each(function(){
				    $(this).removeClass("onclickCampClass");
				});
		    	$("#tag_" + code).addClass("onclickCampClass");
	    	}
		},
		addTag: function(){
			var data = {"cardId": cardId[vm.clickId]};
			ajaxFunctionPost(ctxPath + "/solr/query/httpRequestClient", data, 
				function(res){
					var length = vm.parents.length;
					if(length == 13){
						vm.parents.splice(12, 1);
					}
					console.log(res);
					vm.toId = res.cardId;
					if(res.code == 0 && null != vm.toId && "" != vm.toId){
						vm.parents.push({"code": "13", "name": "企业法人查询"});
					}
				}, function(res){
					
				}
			);
		}
	},
	watch:{
		title: function(){
			changeOperationName(vm.title);
			if("查看" == vm.title || "修改" == vm.title || "添加" == vm.title){
				showParentClosed();
			}
		}
	},
	mounted: function () {
		this.$nextTick(function () {
			vm.parents = [
				{"code": "01", "name": "人口基础信息"},
				{"code": "02", "name": "计生流动人口表（流动轨迹表）"},
				{"code": "03", "name": "社保参保人员基本信息（原始）"},
				{"code": "04", "name": "人口出生信息"},
				{"code": "05", "name": "民政婚姻信息"},
				{"code": "06", "name": "民政低保家庭基本信息"},
				{"code": "07", "name": "生育服务证信息"},
				{"code": "08", "name": "学籍信息"},
				{"code": "09", "name": "卫计委新农合信息"},
				{"code": "10", "name": "计划免疫信息表"},
				{"code": "11", "name": "0-7岁流动儿童国家免疫规划疫苗接种状况表"},
				{"code": "12", "name": "儿童收养登记信息表"}
			];
		})
	}
});

//查看
function onClickQuery(userGuid){
	vm.queryById(userGuid);
}

function childClose(){
	vm.closed();
}