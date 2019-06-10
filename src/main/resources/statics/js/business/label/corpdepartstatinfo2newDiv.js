$(function () {
	//加载数据
	//onloadLeftUp();
	//onloadLeftDown();
	//onloadPercentData();
	//onloadImputationData();
	//onloadNotImputationData();
	//onloadImputationInfoAllCount();
});

onload1 = function(){	
	var dom = document.getElementById("data1");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		    },
		    
		    title: {
		        text: '2018年共享趋势',
	        	textStyle:{
	                fontSize:10,
	                
	            }
		    },
		    yAxis: {
		        type: 'value'
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '1%',
		        containLabel: true,
		        y:30
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line'
		    }]
		};
	tagProportionChart.setOption(option, true);
}

onload2 = function(){	
	var dom = document.getElementById("data2");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '1%',
		        containLabel: true,
		        y:20
		    },
		    series: [{
		        data: [820, 932, 901, 934, 1290, 1330, 1320],
		        type: 'line'
		    }]
		};
	tagProportionChart.setOption(option, true);
}

onload3_1 = function(){	
	var dom = document.getElementById("data3");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['国税局', '教育厅', '工商局', '卫计委', '质监局', '社保局', '人社厅',
		        '民政局','地税局','审计局']
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '1%',
		        containLabel: true,
		        y:20
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [3, 4, 6, 9, 7, 6, 6,5,5,3],
		        type: 'bar'
		    }]
		};

	tagProportionChart.setOption(option, true);
}

onload3_2 = function(){	
	var dom = document.getElementById("data3_2");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    title: {
		        text: ''
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['社保局','民政局','工商局','质监局','卫计委']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['一月','二月','三月','四月','五月','六月','七月',
		        '八月','九月','十月','十一月','十二月']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name:'社保局',
		            type:'line',
		            stack: '总量',
		            data:[120, 132, 101, 134, 90, 230, 210,232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'民政局',
		            type:'line',
		            stack: '总量',
		            data:[220, 182, 191, 234, 290, 330, 310,332, 301, 334, 390, 330]
		        },
		        {
		            name:'工商局',
		            type:'line',
		            stack: '总量',
		            data:[150, 232, 201, 154, 190, 330, 410,234, 290, 330, 310,332]
		        },
		        {
		            name:'质监局',
		            type:'line',
		            stack: '总量',
		            data:[320, 332, 301, 334, 390, 330, 320,310,332, 301, 334, 390]
		        },
		        {
		            name:'卫计委',
		            type:'line',
		            stack: '总量',
		            data:[820, 932, 901, 934, 1290, 1330, 1320,332, 301, 334, 390, 330]
		        }
		    ]
		};

	tagProportionChart.setOption(option, true);
}

onload3_3 = function(){	
	var dom = document.getElementById("data3_3");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    title: {
		        text: ''
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['社保局','民政局','工商局','质监局','卫计委']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['一月','二月','三月','四月','五月','六月','七月',
		        '八月','九月','十月','十一月','十二月']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name:'社保局',
		            type:'line',
		            stack: '总量',
		            data:[20, 132, 101, 134, 90, 230, 210,232, 201, 154, 190, 330, 410]
		        },
		        {
		            name:'民政局',
		            type:'line',
		            stack: '总量',
		            data:[220, 182, 191, 234, 90, 330, 310,332, 301, 334, 390, 330]
		        },
		        {
		            name:'工商局',
		            type:'line',
		            stack: '总量',
		            data:[150, 232, 201, 154, 190, 30, 410,234, 90, 330, 310,332]
		        },
		        {
		            name:'质监局',
		            type:'line',
		            stack: '总量',
		            data:[320, 332, 301, 34, 390, 330, 320,310,332, 301, 334, 390]
		        },
		        {
		            name:'卫计委',
		            type:'line',
		            stack: '总量',
		            data:[820, 932, 901, 934, 190, 130, 1320,332, 301, 334, 390, 330]
		        }
		    ]
		};

	tagProportionChart.setOption(option, true);
}

onload4 = function(){	
	var dom = document.getElementById("data4");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['教育厅', '工商局', '卫计委', '质监局', '社保局', '人社厅',
				        '民政局','地税局','交通厅','省编办']
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '1%',
		        containLabel: true,
		        y:20
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: [14, 11, 8, 7, 7, 6.8, 6.5,5.60,5.00,3.00],
		        type: 'bar'
		    }]
		};
	tagProportionChart.setOption(option, true);
}
onload4_2 = function(){	
	var dom = document.getElementById("data4_2");
	var tagProportionChart = echarts.init(dom);	
	option = {
		    xAxis: {
		        type: 'category',
		        data: ['教育厅', '社保局', '人社厅','民政局','地税局', '工商局', '卫计委', '质监局','交通厅','省编办']
		    },
		    grid: {
		        left: '5%',
		        right: '5%',
		        bottom: '1%',
		        y:20,
		        containLabel: true
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		    	 data: [12, 11, 10, 9, 7, 6.8, 6.5,5.60,5.00,3.00],
		        type: 'bar'
		    }]
		};
	tagProportionChart.setOption(option, true);
}






	                    