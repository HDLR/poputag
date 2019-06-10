$(function () {
	queryJqGrid();
});

function queryJqGrid(){

	$("#jqGrid").jqGrid({
        url: '../solr/query/byQueryValue',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'popu_nm', index: 'popu_nm', width: 50 }, 			
			{ label: '性别', name: 'gender', index: 'gender', width: 50 }, 			
			{ label: '身份证号码', name: 'zjhm', index: 'zjhm', width: 100 }, 
			{ label: '年龄', name: 'age', index: 'age', width: 50 }, 
			{ label: '民族', name: 'nation', index: 'nation', width: 50 }, 
			{ label: '婚姻状况', name: 'marrg_stat', index: 'marrg_stat', width: 50 }, 
			{ label: '户口性质', name: 'hshld_prop', index: 'hshld_prop', width: 50 }, 
			{ label: '籍贯', name: 'native_plc_desc', index: 'native_plc_desc', width: 50 }, 
			{ label: '户籍地址', name: 'regi_add_cd_desc', index: 'regi_add_cd_desc', width: 200 },
			{ label: '操作', index: 'operate', align: 'center', width: 50, 
				formatter: function (cellvalue, options, rowObject) {
					nameMap[rowObject.id] = rowObject.popu_nm;
					cardId[rowObject.id] = rowObject.zjhm;
					return '<input type="button" class="btn-primary" value="查看" onclick="onClickQuery('+ rowObject.id +')"/>';
                }
			}
        ],
		viewrecords: true,
		height: ($(window).height()-170) + 'px',
        rowNum: 14,
		rowList : [15,30,50],
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
		toId:null,
		dataInfo:{defaultHead:'m'},
		ucss:null
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
	    	vm.clickId = id;
	    	vm.showList = true;
			vm.popuName = nameMap[id];
			
			vm.dataInfo = {defaultHead:'m'};
			var data = {"value": vm.clickId ,"name": "popu_13_unif_popu_id"};
	    	ajaxFunctionPost(ctxPath + "/solr/query/byId", data, 
				function(res){
					vm.dataInfo = res.dataInfo;
					showParentClosed();
					vm.clickTag("01", "人口基础信息");//默认打开"人口基础信息"
					vm.addTag();
					vm.title = "查看";
					
					vm.photoByCertNum(id);//查询相片
				}, function(res){
					
				}
			);
	    },
	    photoByCertNum: function(id){
	    	var data = {"certNum": cardId[id]};
	    	ajaxFunctionPost(ctxPath + "/solr/query/photoByCertNum", data, 
				function(res){
	    			vm.dataInfo.head = res.photo;
				}, function(res){
					
				}
			);
	    },
	    queryByCode: function(code){
	    	this.$nextTick(function () {
	    		vm.childs = vm.dataInfo.classLabelMap[code];
			})
	    },
	    clickTag: function(code, tagName){
	    	if("11" == code){
	    		var urlr = clientUrl + "&cardId=" + vm.toId + "&name=" + vm.popuName + "&ucss=" + vm.ucss;
	    		window.open(urlr);
	    		vm.childs = {"需跳转到法人库查询":""};
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
			console.log(data);
			ajaxFunctionPost(ctxPath + "/solr/query/httpRequestClient", data, 
				function(res){
				console.log(res);
					var length = vm.parents.length;
					if(length == 11){
						vm.parents.splice(10, 1);
					}
					if(res.code == 0 && null != res.cardId && "" != res.cardId){
						var ar = res.cardId.split("value");
						vm.toId = ar[0];
						vm.ucss = ar[1];
						vm.parents.push({"code": "11", "name": "企业法人查询", "color": "#A5CC82"});
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
				{"code": "01", "name": "基础信息", "color": "#fc9b6f"},
				{"code": "02", "name": "家庭特征", "color": "#ff7b8c"},
				{"code": "03", "name": "工作特征", "color": "#83d587"},
				{"code": "04", "name": "流动状况", "color": "#9587f1"},
				{"code": "05", "name": "资产状况", "color": "#72ddc6"},
				{"code": "06", "name": "健康状况", "color": "#7d8ef3"},
				{"code": "07", "name": "专业领域", "color": "#46a8f9"},
				{"code": "08", "name": "社会保障", "color": "#f5bc34"},
				{"code": "09", "name": "信用评价", "color": "#4da1ff"},
				{"code": "10", "name": "其它信息", "color": "#00467F"},
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