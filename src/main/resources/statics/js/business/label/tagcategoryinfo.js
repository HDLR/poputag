$(function () {
	$.ajax({
		type: "POST",
	    url: '../label/h50tagcategoryinfo/findAllRecursion',
	    data: "",
	    success: function(r){
	    	if(r.code === 0){
	    		var tagList=r.tagList;//list<Entity>
	    		//创建div N个
	    		for(var i=0;i<tagList.length;i++){//第一级别标签类别
	    			var levelOneTag=tagList[i];
	    			var tagCtgyId=levelOneTag.tagCtgyId;
	    			var tagCtgyNm=levelOneTag.tagCtgyNm;
	    			$("#tagShow").append("<div class='col-md-6' >"
	    					+"<table border='0'"
	    					+" style='width:100%;height:100%'"
	    					+" id="+tagCtgyId+">"
	    					+"<caption  align='top' style='font-size:4vh;'>"
	    					+tagCtgyNm+"</caption> ");
    				//9个标签类别 。分成4*2排列 余1 
	    			//13个标签        4*3  余1
	    			//29个		4*7 余1
	    			//27个               4*6 余3
	    			//9个              4*2 余1
    				var levelTwoSize=levelOneTag.children.length;//第二层的数量
    				var selector ="#"+tagCtgyId;
    				var trContent="";
    				var dolength = Math.floor(levelTwoSize/4);//29  循环dolength:7 leftlength:2 
    				var leftlength = levelTwoSize%4;//剩下的个数
    				for(var j=0;j<dolength;j++){//数量/4（一个tr 设置4个td） 
    					var tdContent="";
	    				for(var td=0;td<4;td++){
	    					tdContent=tdContent 
	    					+"<td >"
	    					+"<button type='button' class='layui-btn layui-btn-primary layui-btn-radius'"
	    					+" style='height:4vh !important;width:99%;font-size:3vh;margin:1vh 0px 1vh 0px'"
	    					+" onClick=onClickQuery("+levelOneTag.children[j*4+td].tagCtgyId+") >"
	    					+showData(levelOneTag.children[j*4+td].tagCtgyNm)
	    					+"</button>"
	    					+"</td>";
	    				}
	    				//一次循环添加一个 <tr>
	    				trContent=trContent+"<tr style=''>"+tdContent+"<tr>";
    				}
    				//用取模剩余的进行添加
    				tdContent2="";
    				for(var k=0;k<leftlength;k++){
    					tdContent2=tdContent2
    					+"<td >"
    					+"<button type='button' class='layui-btn layui-btn-primary layui-btn-radius'"
    					+" style='height:4vh !important;width:99%;font-size:3vh;margin:1vh 0px 1vh 0px'"
    					+" onClick=onClickQuery("+levelOneTag.children[dolength*4+k].tagCtgyId+") >"
    					+showData(levelOneTag.children[dolength*4+k].tagCtgyNm)
    					+"</button>"
    					+"</td>";
    				}
    				//剩下的不足一行的<tr>
    				trContent2="<tr>"+tdContent2+"<tr>";
    				$(selector).append(trContent+trContent2+"</div>");
    				if(i%2==1){
    					$("#tagShow").append('<HR align=center  color=#468ebc0d SIZE=2>');
    				}
	    		}
			}else{
				alert(r.msg);
			}
		}
	});
});
//查看
function onClickQuery(tagCtgyId){
	var params = {"tagCtgyId":tagCtgyId};
	toNewViewByPost(params, ctxPath + "/toNewView/label/h50taginfoItem");
}
showData = function(data){
	if(data.length>7){
		return data.substring(0,7)+"...";
	}
	return data
} 
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		h50TagCategoryInfo: {},
		navTitle:'标签市场'
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.reload();
			vm.h50TagCategoryInfo = {};
			$.ajax({
				type: "POST",
			   // url: '../label/h50tagcategoryinfo/getCtgyList',
			    url: '../label/h50tagcategoryinfo/findAllRecursion',
			    data: "",
			    success: function(r){
			    	if(r.code === 0){
			    			
					}else{
						alert(r.msg);
					}
				}
			});
			
		},
		update: function (event) {
			var tagCtgyId = getSelectedRow();
			if(tagCtgyId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(tagCtgyId)
		},
		saveOrUpdate: function (event) {
			var url = vm.h50TagCategoryInfo.tagCtgyId == null ? "../h50tagcategoryinfo/save" : "../h50tagcategoryinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.h50TagCategoryInfo),
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
			var tagCtgyIds = getSelectedRows();
			if(tagCtgyIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../h50tagcategoryinfo/delete",
				    data: JSON.stringify(tagCtgyIds),
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
		getInfo: function(tagCtgyId){
			$.get("../h50tagcategoryinfo/info/"+tagCtgyId, function(r){
                vm.h50TagCategoryInfo = r.h50TagCategoryInfo;
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