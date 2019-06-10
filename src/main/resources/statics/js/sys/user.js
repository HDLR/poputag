$(function () {
    $("#jqGrid").jqGrid({
        url: '../user/list',
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userId', index: "user_id", width: 105, key: true },
			{ label: '用户名', name: 'username', width: 75 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createTime', width: 80, formatter:function(value, options, row){return timeFormatterYYYYMMDD(row.createTime);}}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
      /*  shrinkToFit:false,
        autoScroll: true, */
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
        	$("#incomegrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "auto" });  
        	$("#incomegrid").closest(".ui-jqgrid-bdiv").css({ "overflow-y" : "auto" });  
        	//隐藏grid底部滚动条
        	/*$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); */
        }
    });
    
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			username: null
		},
		showList: true,
		title:null,
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:1,roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
		},
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
		},
		closed:function(){
			vm.showList = true;
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../user/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							doSuccess('操作成功', function(index) {
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			if(!vm.validateStrongerPass()){
				return ;
			};
			var url = vm.user.userId == null ? "../user/save" : "../user/update";
			$.ajax({
				type: "POST",
			    url: url,
                contentType: "application/json",
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
			    		doSuccess('操作成功', function(index) {
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getUser: function(userId){
			$.get("../user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../role/select", function(r){
				vm.roleList = r.list;
			});
		},
		validateStrongerPass : function(){
			var pattern = /^.*(?=.{8,16})(?=.*\d)(?=.*[A-Z]{2,})(?=.*[a-z]{2,})(?=.*[!@#$%^&*?\(\)]).*$/;
			if(pattern.test(vm.user.password)){
				$("#tips").hide();
				return true;
			}else{
				$("#tips").show();
				//alert("最短8位，最长16位 {8,16},必须包含1个数字,必须包含2个小写字母,必须包含2个大写字母,必须包含1个特殊字符");
				return false;
			}
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});