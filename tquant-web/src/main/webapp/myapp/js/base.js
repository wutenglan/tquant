/////////全局初始化，所有页面都能加载到///////////////////
$(document).ready(function() {
	ComponentsBootstrapSelect.init();
	initActiveMenu();

});

/**
 * validate插件
 * 
 */
VALIDATE_default = {
	errorElement : 'span', // default input error message container
	errorClass : 'help-block help-block-error', // default input error message
	// class
	focusInvalid : false, // do not focus the last invalid input
	ignore : "", // validate all fields including form hidden input

	highlight : function(element) { // hightlight error inputs
		$(element).closest('.form-group').addClass('has-error'); // set error
		// class to
		// the
		// control
		// group
	},

	unhighlight : function(element) { // revert the change done by hightlight
		$(element).closest('.form-group').removeClass('has-error'); // set error
		// class to
		// the
		// control
		// group
	},
	success : function(label) {
		label.closest('.form-group').removeClass('has-error'); // set success
		// class to the
		// control group
	}
};

/**
 * datatable插件
 * 
 */
DT_defaultSetting = {
	"language" : {
		"aria" : {
			"sortAscending" : ": activate to sort column ascending",
			"sortDescending" : ": activate to sort column descending"
		},
		"emptyTable" : "没有数据",
		"info" : "显示从 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
		"infoEmpty" : "没有找到匹配的记录",
		"infoFiltered" : "( 从 _MAX_ 条记录中过滤)",
		"lengthMenu" : "显示 _MENU_",
		"search" : "搜索:",
		"zeroRecords" : "没有找到匹配的记录",
		"paginate" : {
			"previous" : "前一页",
			"next" : "后一页",
			"last" : "尾页",
			"first" : "首页"
		}
	},
	"bStateSave" : false, // save datatable state(pagination, sort, etc) in
							// cookie.这样就会保存状态了，我们不需要
	"lengthMenu" : [ [ 10, 20, 100, -1 ], [ 10, 20, 100, "All" ] // change
																	// per
	],
	"pageLength" : 10, // warn 每页的行数，一定要在lengthMenu中有，否则的话，会显示为空
	"pagingType" : "bootstrap_full_number",
};

DT_export = {
	buttons : [ {
		extend : 'colvis',
		text : "列过滤",
		className : 'btn purple btn-outline '
	}, {
		extend : 'excelHtml5',
		title : $("#exportTitle").val(),
		className : 'btn dark btn-outline',
		exportOptions : {
			columns : ':visible'
		}
	}, {
		extend : 'csvHtml5',
		title : $("#exportTitle").val(),
		className : 'btn green btn-outline',
		exportOptions : {
			columns : ':visible'
		}
	}, {
		extend : 'print',
		title : $("#exportTitle").val(),
		className : 'btn dark btn-outline',
		exportOptions : {
			columns : ':visible'
		}
	} ],
	"dom" : "<'row' <'col-md-12'B>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>", // horizobtal
																																										// scrollable
																																										// datatable

};

/*******************************************************************************
 * datatable插件支持中文排序 这个排序不准
 * var citys = ['北京-b','上海-s','广州-g','深圳-s','南京-n','苏州-s','杭州-h','济南-j','青岛-q','武汉-w','沈阳-sh','成都-ch','天津-t','重庆-ch','西安-x','郑州-zh','石家庄-sh','长沙-ch','长春-ch','合肥-h','福州-f'];
 *     function sortRule(a,b) {         return a.localeCompare(b);            
 *     }         window.onload = function(){
 *               alert(citys.sort(sortRule));          }
 */
jQuery.fn.dataTableExt.oSort['chinese-asc'] = function(s1, s2) {
	return s1.localeCompare(s2);
};
jQuery.fn.dataTableExt.oSort['chinese-desc'] = function(s1, s2) {
	return s2.localeCompare(s1);
};

jQuery.fn.dataTableExt.oSort['string-asc'] = function(s1, s2) {
	if(!isNaN(s1) && !isNaN(s2)){
		return number_asc(s1,s2);
	}
	return s1.localeCompare(s2);
};
jQuery.fn.dataTableExt.oSort['string-desc'] = function(s1, s2) {
	if(!isNaN(s1) && !isNaN(s2)){
		return number_desc(s1,s2);
	}
	return s2.localeCompare(s1);
};

jQuery.fn.dataTableExt.oSort['numeric-comma-asc']  = function(a,b) {
    return number_asc(a,b);
};
 
function number_asc(a,b)
{
	var x = (a == "-") ? 0 : a.replace(",", "." );
    var y = (b == "-") ? 0 : b.replace( ",", "." );
    x = parseFloat( x );
    y = parseFloat( y );
    return ((x < y) ? -1 : ((x > y) ?  1 : 0));
}

jQuery.fn.dataTableExt.oSort['numeric-comma-desc'] = function(a,b) {
	return number_desc(a,b);
};

function number_desc(a,b)
{
	var x = (a == "-") ? 0 : a.replace(",", "." );
    var y = (b == "-") ? 0 : b.replace( ",", "." );
    x = parseFloat( x );
    y = parseFloat( y );
    return ((x < y) ?  1 : ((x > y) ? -1 : 0));
}


jQuery.fn.dataTableExt.oSort['sortKey-asc'] = function(s1, s2) {
	console.log('xx:' + $(s1).attr('key') + "," + $(s2).attr('key') + ":"
			+ $(s1).attr('key').localeCompare($(s2).attr('key')));
	return $(s1).attr('key').localeCompare($(s2).attr('key'));
};

jQuery.fn.dataTableExt.oSort['sortKey-desc'] = function(s1, s2) {
	return $(s2).attr('key').localeCompare($(s1).attr('key'));
};

// aTypes是插件存放表格内容类型的数组
// reg赋值的正则表达式，用来判断是否是中文字符
// 返回值push到aTypes数组，排序时扫描该数组，'chinese'则调用上面两个方法。返回null默认是'string'
jQuery.fn.dataTableExt.aTypes.push(function(sData) {
	var reg = /^[\u4e00-\u9fa5]{0,}$/;
	if (reg.test(sData)) {
		return 'chinese';
	}
	return null;
});

/**
 * 公共widgets组件
 * 
 */
/** 弹出toast框，一般用于系统级别警告或者提示* */
function popGrowl(content, level, delay) {
	content = content || "网络繁忙，请稍后再试";
	level = level || 'info';
	delay = delay || 2000;
	$.bootstrapGrowl(content, {
		ele : 'body', // which element to append to
		type : level, // (null, 'info', 'danger', 'success', 'warning')
		offset : {
			from : 'top',
			amount : 100
		}, // 'top', or 'bottom'
		align : 'left', // ('left', 'right', or 'center')
		width : 250, // (integer, or 'auto')
		delay : delay, // Time while the message will be displayed. It's not
		// equivalent to the *demo* timeOut!
		allow_dismiss : true, // If true then will display a cross to close
		// the popup.
		stackup_spacing : 10
	// spacing between consecutively stacked growls.
	});
}

/*
 * 激活sidebar的选中框
 */
function initActiveMenu() {
	// $("ul.page-sidebar>li.nav-item>a>span.title").each(function(){
	// if($(this).html()==$("#firstMenu").attr("value")){
	// $(this).parents("li.nav-item").addClass("active");
	//			
	//			
	// }
	// });

	$("ul.sub-menu>li.nav-item a>span.title").each(function() {
		if ($(this).attr("m2") == $("#m2").attr("value")) {
			$(this).parents("li.nav-item").addClass("active");
		}
	});

}

// ////////////////////////////////////////////////
// //////////下拉菜单样式/////////////////////////////////////
var ComponentsBootstrapSelect = function() {

	var handleBootstrapSelect = function() {
		$('.bs-select').selectpicker({
			iconBase : 'fa',
			tickIcon : 'fa-check'
		});
	}

	return {
		// main function to initiate the module
		init : function() {
			handleBootstrapSelect();
		}
	};

}();

/*
 * Translated default messages for the jQuery validation plugin. Locale: ZH
 * (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
$.extend($.validator.messages, {
	required : "这是必填字段",
	remote : "请修正此字段",
	email : "请输入有效的电子邮件地址",
	url : "请输入有效的网址",
	date : "请输入有效的日期",
	dateISO : "请输入有效的日期 (YYYY-MM-DD),比如：1989-11-22",
	number : "请输入有效的数字",
	digits : "只能输入数字",
	creditcard : "请输入有效的信用卡号码",
	equalTo : "你的输入不相同",
	extension : "请输入有效的后缀",
	maxlength : $.validator.format("最多可以输入 {0} 个字符"),
	minlength : $.validator.format("最少要输入 {0} 个字符"),
	rangelength : $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
	range : $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
	max : $.validator.format("请输入不大于 {0} 的数值"),
	min : $.validator.format("请输入不小于 {0} 的数值")
});

function themeDark()
{

	Highcharts.theme = {
	   colors: ['#2b908f', '#90ee7e', '#f45b5b', '#7798BF', '#aaeeee', '#ff0066', '#eeaaee',
	      '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
	   chart: {
	      backgroundColor: {
	         linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
	         stops: [
	            [0, '#2a2a2b'],
	            [1, '#3e3e40']
	         ]
	      },
	      style: {
	         fontFamily: '\'Unica One\', sans-serif'
	      },
	      plotBorderColor: '#606063'
	   },
	   title: {
	      style: {
	         color: '#E0E0E3',
	         textTransform: 'uppercase',
	         fontSize: '20px'
	      }
	   },
	   subtitle: {
	      style: {
	         color: '#E0E0E3',
	         textTransform: 'uppercase'
	      }
	   },
	   xAxis: {
	      gridLineColor: '#707073',
	      labels: {
	         style: {
	            color: '#E0E0E3'
	         }
	      },
	      lineColor: '#707073',
	      minorGridLineColor: '#505053',
	      tickColor: '#707073',
	      title: {
	         style: {
	            color: '#A0A0A3'

	         }
	      }
	   },
	   yAxis: {
	      gridLineColor: '#707073',
	      labels: {
	         style: {
	            color: '#E0E0E3'
	         }
	      },
	      lineColor: '#707073',
	      minorGridLineColor: '#505053',
	      tickColor: '#707073',
	      tickWidth: 1,
	      title: {
	         style: {
	            color: '#A0A0A3'
	         }
	      }
	   },
	   tooltip: {
	      backgroundColor: 'rgba(0, 0, 0, 0.85)',
	      style: {
	         color: '#F0F0F0'
	      }
	   },
	   plotOptions: {
	      series: {
	         dataLabels: {
	            color: '#B0B0B3'
	         },
	         marker: {
	            lineColor: '#333'
	         }
	      },
	      boxplot: {
	         fillColor: '#505053'
	      },
	      candlestick: {
	         lineColor: 'white'
	      },
	      errorbar: {
	         color: 'white'
	      }
	   },
	   legend: {
	      itemStyle: {
	         color: '#E0E0E3'
	      },
	      itemHoverStyle: {
	         color: '#FFF'
	      },
	      itemHiddenStyle: {
	         color: '#606063'
	      }
	   },
	   credits: {
	      style: {
	         color: '#666'
	      }
	   },
	   labels: {
	      style: {
	         color: '#707073'
	      }
	   },

	   drilldown: {
	      activeAxisLabelStyle: {
	         color: '#F0F0F3'
	      },
	      activeDataLabelStyle: {
	         color: '#F0F0F3'
	      }
	   },

	   navigation: {
	      buttonOptions: {
	         symbolStroke: '#DDDDDD',
	         theme: {
	            fill: '#505053'
	         }
	      }
	   },

	   // scroll charts
	   rangeSelector: {
	      buttonTheme: {
	         fill: '#505053',
	         stroke: '#000000',
	         style: {
	            color: '#CCC'
	         },
	         states: {
	            hover: {
	               fill: '#707073',
	               stroke: '#000000',
	               style: {
	                  color: 'white'
	               }
	            },
	            select: {
	               fill: '#000003',
	               stroke: '#000000',
	               style: {
	                  color: 'white'
	               }
	            }
	         }
	      },
	      inputBoxBorderColor: '#505053',
	      inputStyle: {
	         backgroundColor: '#333',
	         color: 'silver'
	      },
	      labelStyle: {
	         color: 'silver'
	      }
	   },

	   navigator: {
	      handles: {
	         backgroundColor: '#666',
	         borderColor: '#AAA'
	      },
	      outlineColor: '#CCC',
	      maskFill: 'rgba(255,255,255,0.1)',
	      series: {
	         color: '#7798BF',
	         lineColor: '#A6C7ED'
	      },
	      xAxis: {
	         gridLineColor: '#505053'
	      }
	   },

	   scrollbar: {
	      barBackgroundColor: '#808083',
	      barBorderColor: '#808083',
	      buttonArrowColor: '#CCC',
	      buttonBackgroundColor: '#606063',
	      buttonBorderColor: '#606063',
	      rifleColor: '#FFF',
	      trackBackgroundColor: '#404043',
	      trackBorderColor: '#404043'
	   },

	   // special colors for some of the
	   legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
	   background2: '#505053',
	   dataLabelsColor: '#B0B0B3',
	   textColor: '#C0C0C0',
	   contrastTextColor: '#F0F0F3',
	   maskColor: 'rgba(255,255,255,0.3)'
	};
	// Apply the theme
	Highcharts.setOptions(Highcharts.theme);
}
