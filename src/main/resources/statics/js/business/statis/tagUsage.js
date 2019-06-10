$(function () {
	
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "tag_id",
			pIdKey: "parent_id",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	},
	callback: {
		onCheck: treesOnClick,
		unCheck: treesOnClick
    }
};

var ztree;

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			campNm: null
		},
		showList: true,
		title: null,
		personCntSum: 0
	},
	methods: {
		closed:function(){
			vm.showList = true;
			changeOperationName("浏览");
		},
		getTagsTree: function() {
			//加载菜单树
			$.get("../label/h50taginfo/queryAllTagsAndGroupTags", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.tags);
				//展开所有节点
				ztree.expandAll(false);
			});
	    }
	},
	mounted: function () {
	  this.$nextTick(function () {
		  changeOperationName("浏览");
		  vm.getTagsTree();
	  })
	}
});

//checkTags
//countDesc
//limit
function queryUsage(checkTags, limit){
	if(null == checkTags || "" == checkTags){
		checkTags = new Array();
	}
	if(checkTags.length<1 && null == limit){
		vm.showList = true;
	}else{
		ajaxFunctionPost("../statis/tags/usage", {"checkTags":checkTags.join(), "limit":limit}, 
			function(r){
				setEcharts(r.tags, vm.personCntSum, "main_div", ['统计分析-标签应用']);
				if(null == r.tags || r.tags.length < 1){
					vm.showList = true;
				}else{
					vm.showList = false;
				}
			}, function(r){
				alert("调用系统异常，请联系管理人员定位");
			}
		);
	}
}

function treesOnClick(){
	//获取选择的菜单
	var nodes = ztree.getCheckedNodes(true);
	var checkTags = new Array();
	var nodeLength = nodes.length;
	if(nodeLength > 200){
		alert("勾选的标签属性数量过多，不能超过200。");
		return ;
	}
	for(var i=0; i<nodeLength; i++) {
		checkTags.push(nodes[i].tag_id);
	}
	
	queryUsage(checkTags);
}

function genData(reData) {
    var nameList = [];
    var countSumList = [];
    if(null != reData && reData.length > 0){
    	$.each(reData, function(i, eValue){
    		nameList.push(eValue.showparent_nm + ":" + eValue.tag_ctgy_nm + ":" + eValue.tag_nm);
    		countSumList.push(eValue.count);
    	});
    }
    
    var legendData = [];
    var seriesData = [];
    var selected = {};
    for (var i = 0; i < nameList.length; i++) {
        legendData.push(nameList[i]);
        seriesData.push({
            name: nameList[i],
            value: countSumList[i]
        });
        selected[nameList[i]] = true;
    }

    return {
        legendData: legendData,
        seriesData: seriesData,
        selected: selected
    };
};