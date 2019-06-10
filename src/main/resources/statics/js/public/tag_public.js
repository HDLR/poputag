//全局处理shiro登录session失效时弹出提示
$.ajaxSetup({
	beforeSend: function(params1, params2){
		showMask();
		hidenParentClosed();
	},
    complete: function(XMLHttpRequest,textStatus){
    	hideMask();
        if(textStatus=="parsererror"){
        	confirm("登录失效，请确认是否重新登录！", function(){
        		window.location.href = 'login.html';
  			});
        } else if(textStatus=="error"){
        	//alert("请求超时！请稍后再试！");
        }else{
        	wholeError(XMLHttpRequest.responseJSON);
        }
    }  
});

function showMask(){
	$("#publicmask").show();
}

function hideMask(){
	$("#publicmask").hide();
}

//全局处理异常信息
function wholeError(res){
	try {
		if(res.code !== 0 && null != res.msg){
			console.log(res.msg);
			alert(res.msg);
		}
	} catch (e) {
		
	}
}

function changeOperationName(v){
	$('#operationName',window.parent.document).text(v);
}

function ajaxFunctionPost(url, data, successFunctionCall, errorFunctionCall){
	$.ajax({
		type: "POST",
	    url: url,
	    data: data,
	    dataType: "json",
	    success: function(resPublic){
	    	wholeError(resPublic);
			if (successFunctionCall != null) {
				successFunctionCall(resPublic);
			}
		},
		error: function(resPublic){
			if(resPublic.code !== 0 && null != resPublic.msg){
	    		alert(resPublic.msg);
			}else{
				alert("系统请求异常，请联系管理人员定位");
			}
			if (errorFunctionCall != null) {
				errorFunctionCall(resPublic);
			}
		}
	});
}

function ajaxFunctionPostAsync(url, data, successFunctionCall, errorFunctionCall){
	$.ajax({
		type: "POST",
	    url: url,
	    data: data,
	    async:false,
	    dataType: "json",
	    success: function(resPublic){
	    	wholeError(resPublic);
			if (successFunctionCall != null) {
				successFunctionCall(resPublic);
			}
		},
		error: function(resPublic){
			if(resPublic.code !== 0 && null != resPublic.msg){
	    		alert(resPublic.msg);
			}else{
				alert("系统请求异常，请联系管理人员定位");
			}
			if (errorFunctionCall != null) {
				errorFunctionCall(resPublic);
			}
		}
	});
}

function toNewViewByPost(toViewParam, url) {
	var formObj = $("form");
	if (toViewParam != null) {
		var hiddenObj = $("<input type='hidden' name='viewParams' id='viewParams' value='" + JSON.stringify(toViewParam) + "'>");
		formObj.append(hiddenObj);
	}

	formObj.attr("method", "post");
	formObj.attr("action", url);
	formObj.submit();
}

function showParentClosed(){
	$('#parentClosed',window.parent.document).show();
}

function hidenParentClosed(){
	try {
		$('#parentClosed',window.parent.document).hide();
	} catch (e) {
		
	}
}

function getWinHeight(){
	var wHei = $(window).height() - 80;
	return wHei;
}

function getWinWidth(){
	var wWid = $(window).width() - 300;
	return wWid;
}


function toEncodeURI(url){
//	return encodeURI(url);
	return url;
}

var cDate = new Date();

var gisSelectYear = new Array(cDate.getFullYear()+1, cDate.getFullYear(), cDate.getFullYear()-1, cDate.getFullYear()-2);

function judgeBlank(value){
	if("" == value || null == value){
		return true;
	}
	return false;
}
function timeFormatterYYYYMMDD(time){
	var dateee = new Date(time).toJSON();
	dateFormatter = new Date(+new Date(dateee)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,''); 
	return dateFormatter;
}

/*
 * 禁止回车、空格响应触发上一次的的事件
 */
parent.document.onkeydown = function(e){    
    var ev =parent. document.all ? window.event : e;  
    if(ev.keyCode==13||ev.keyCode==32) { 
		return false
    }  
  } 
document.onkeydown = function(e){    
    var ev =document.all ? window.event : e;  
    if(ev.keyCode==13||ev.keyCode==32) { 
		return false
    }  
  } 