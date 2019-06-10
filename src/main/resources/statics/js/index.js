//首页
homepageUrl="";
$(function(){
	window.setTimeout(function(){
		$(".main-sidebar").mCustomScrollbar({
			/*mouseWheelPixels:250,*/
			alwaysShowScrollbar:1,
			autoDraggerLength:true,
			advanced:{ 
				updateOnBrowserResize:true,
				updateOnContentResize:true
				} 
		});
	},500);//使用字符串执行方法
});
//生成菜单
var menuItem = Vue
		.extend({
			name : 'menu-item',
			props : {
				item : {}
			},
			template : [
					'<li>',
					'<a v-if="item.type === 0" href="javascript:;">',
					'<i v-if="item.icon != null" :class="item.icon"></i>',
					'<span>{{item.name}}</span>',
					'<i class="fa fa-angle-left pull-right"></i>',
					'</a>',
					'<ul v-if="item.type === 0" class="treeview-menu">',
					'<menu-item :item="item" v-for="item in item.list"></menu-item>',
					'</ul>',
					'<a v-if="item.type === 1" :href="\'#\'+item.url"><i v-if="item.icon" :class="item.icon"></i><i v-else class="fa fa-angle-double-right"></i>{{item.name}}</a>',
					'</li>' ].join('')
		});

// iframe自适应
$(window).on('resize', function() {
	var $content = $('.content');
	$content.height($(this).height() - 120);
	$content.find('iframe').each(function() {
		$(this).height($content.height());
	});
}).resize();

// 注册菜单组件
Vue.component('menuItem', menuItem);

function gethomepageUrl(){
	$.get("sys/syshomepagemanager/getHomepageByUserRole", function(r){
		vm.main = JSON.parse(r).homepageUrl;
  });
}

var vm = new Vue({
	el : '#rrapp',
	data : {
		user : {},
		menuList : {},
		main : 'sys/main.html',
		password : '',
		newPassword : '',
		navTitle : "首页",
		upNavTitle: "主页",
		menushowFlag: true,
		winMainHeight: ($(window).height() - 100) + 'px'
	},
	methods : {
		getMenuList : function(event) {
			$.getJSON("sys/menu/user?_" + $.now(), function(r) {
				vm.menuList = r.menuList;
			});
		},
		getUser : function() {
			$.getJSON("sys/user/info?_" + $.now(), function(r) {
				vm.user = r.user;
			});
		},
		updatePassword : function() {
			layer.open({
				type : 1,
				skin : 'layui-layer-molv',
				title : "修改密码",
				area : [ '550px', '270px' ],
				shadeClose : false,
				content : jQuery("#passwordLayer"),
				btn : [ '修改', '取消' ],
				btn1 : function(index) {
					var data = "password=" + vm.password + "&newPassword=" + vm.newPassword;
					$.ajax({
						type : "POST",
						url : "sys/user/password",
						data : data,
						dataType : "json",
						success : function(result) {
							if (result.code == 0) {
								layer.close(index);
								layer.alert('修改成功', function(index) {
									location.reload();
								});
							} else {
								layer.alert(result.msg);
							}
						}
					});
				}
			});
		}
	},
	created : function() {
		this.getMenuList();
		this.getUser();
	},
	updated : function() {
		// 路由
		var router = new Router();
		routerList(router, vm.menuList);
		router.start();
	},
	mounted: function () {
	  this.$nextTick(function () {
		  //gethomepageUrl();
	  })
	}
});


function firstPage(){
	window.location.href="index" ;
}

function routerList(router, menuList) {
	for ( var key in menuList) {
		var menu = menuList[key];
		if (menu.type == 0) {
			routerList(router, menu.list);
			if (menu.name == '系统管理') {
				// 导航菜单展开
				//$(".header").next().addClass("active");
			}
		} else if (menu.type == 1) {
			router.add('#' + menu.url, function() {
				var url = window.location.hash;
				// 替换iframe的url
				vm.main = url.replace('#', '');
				// 导航菜单展开
				$(".treeview-menu li").removeClass("active");
				$("a[href='" + url + "']").parents("li").addClass("active");
				vm.upNavTitle = $("a[href='" + url + "']").parents("li").find("span").text();
				vm.navTitle = $("a[href='" + url + "']").text();
				$('#parentClosed',window.parent.document).hide();
			});
		}
	}
}

function parentClosed(){
	document.getElementById("mainIframe").contentWindow.childClose();
}

function menushowbtn(){
	if(vm.menushowFlag){
		vm.menushowFlag = false;
	}else{
		vm.menushowFlag = true;
	}
}