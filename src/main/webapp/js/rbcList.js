
(function ($) {
    $.rbc = function (options) {
    	var defaults = {
    		searchForm: "#searchForm", // 搜索表单
    		searchBtn: "#searchBtn", // 搜索按钮
    		searchResetBtn : "#searchResetBtn", // 搜索重置
    		targetTable: null, // 表格ID
    		checkAll : "#checkAll", // 表头中的checkbox
    		tableColumns : [],
    		tableDataUrl : null,
    		dataTableObj : null,
    		tableHeaderOpers : null,
    		checkMaxItem :null,
    		singletonRow : false,
    		multiple : false,
    		idCol: 0
        };
        options = $.extend({}, defaults, options);
        
        jBox.setDefaults({ defaults: { top: '30%', border: 0, opacity: 0.2} });
		
  		if (options.msg != "") {
			$.jBox.tip(options.msg, 'info');
  	  	}
        
        var getCheckArray = function() {
			var tempArray = new Array();
			$("input:checkbox:checked", options.dataTableObj.fnGetNodes()).each(function(i){
				tempArray[i] = $(this).val();
			});
			return tempArray;
		};
		var reloadTable = function() {
			options.dataTableObj.fnClearTable(0);
			options.dataTableObj.fnDraw();
		};
        options.dataTableObj = $(options.targetTable).dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sServerMethod" : 'post',
			"bPaginate" : true,
			"bSort" : true,
			"bFilter" : false,
			"bJQueryUI" : false,
			"sPaginationType" : "full_numbers",
			"sDom" : 'rt <"dongao_page"flpi>',
			"iDisplayLength" : 10,
			"aaData" : "list",
			"aLengthMenu" : [ [ 10, 50, 100, 1000 ], [ 10, 50, 100, 1000 ] ],
			"aoColumns" : options.tableColumns,
	        "sAjaxSource" : options.tableDataUrl + (options.tableDataUrl.indexOf("?") > 0 ? "&" : "?") + $(options.searchForm).serialize(),
	        "fnDrawCallback":function(){
	        	JXJY.dataTableCallback();
	        	if($(".dataTables_empty").length == 0){
	        		$.each($(options.targetTable + " > tbody > tr"), function(i, o){
		        		$(this).css({ "cursor": "pointer"});
						$(this).on("click", function(){
							if(options.multiple){
								$(this).toggleClass("trSelected");
							}else{
								$(".trSelected").removeClass('trSelected');
								$(this).addClass('trSelected');
							}
						});
					});
	        	}
	        },
			"oLanguage" : {
				"sLengthMenu" : "每页 _MENU_ 条",
				"sZeroRecords" : "",
				"sInfo" : "当前从 _START_ 到 _END_ 条,共 _TOTAL_ 条记录",
				"sInfoEmpty" : "没有找到记录",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "尾页"
				}
			}
		});
        $(options.checkAll).live('click', function() {
			$(".chk_list", options.dataTableObj.fnGetNodes()).prop("checked", $(this).prop("checked"));
		});
		
        $(options.searchBtn).click(function() {
			var oSettings = options.dataTableObj.fnSettings();
			oSettings._iDisplayStart = 0;
			oSettings.sAjaxSource = options.tableDataUrl + (options.tableDataUrl.indexOf("?") > 0 ? "&" : "?") + $(options.searchForm).serialize();
			options.dataTableObj.fnPageChange("first", true);
		});
        $(options.searchResetBtn).click(function(){
        	$(options.searchForm)[0].reset();
        	$('.chosen').val('').trigger('chosen:updated');
		});
        $(options.tableHeaderOpers).click(function(){
			var checkedArray = getCheckArray();
			if(options.singletonRow && $(".trSelected").length > 0){
				checkedArray = new Array();
				checkedArray.push($(".trSelected").find("td:eq(0)").html());
			}
			if(options.multiple && $(".trSelected").length > 0){
				checkedArray = selectRow.getDataArr(options.idCol);
			}
			if(checkedArray.length == 0) {
				$.jBox.info('请先选择要操作的数据', '提示');
				return false;
			}
			if (options.checkMaxItem) {
				if (checkedArray.length > options.checkMaxItem) {
					$.jBox.info('选择要操作的数据不能超过' + options.checkMaxItem + "条", '提示');
					return false;
				}
			}
			var tipText = $(this).html();
			var param = $(this).attr('param');
	    	
	    	var targetUrl = $(this).attr('targetUrl');
			var submit = function (v, h, f) {
			    if (v == 'ok') {
			    	var params = {};
					params.ids = checkedArray;
			    	if (param && param != '') {
			    		param = eval('('+param+')');
			    		params = $.extend({}, params, param);
			    	}
			   
			    	$.ajax({
			    		type : "post",
			    		url : targetUrl,
			    		dataType : "json",
			    		data : params,
			    		success : function(data) {
			    			if (typeof(data.success) != "undefined") {
			    				var success = data.success;
			    				if (success == "true") {
			    					var tipHtml = tipText + "成功";
				    				jBox.tip(tipHtml, "success");
				    				$(options.checkAll).removeAttr("checked");
				    			} else {
				    				var tipHtml = tipText + "失败";
				    				if (data.msg) {
				    					tipHtml += "<br/>" + data.msg;
				    				}
				    				jBox.tip(tipHtml, "error");
				    			}
			    			} else {	
				    			if (data.isError) {
				    				jBox.tip(tipText + "失败", "error");
				    			} else {
				    				var tipHtml = tipText + "成功";
				    				if (data.msg) {
				    					tipHtml += "<br/>" + data.msg;
				    				}
				    				jBox.tip(tipHtml, "success");
				    				$(options.checkAll).removeAttr("checked");
				    			}
			    			}
							reloadTable(); // 重新加载数据
			    		},
			    		error :function(XMLHttpRequest, textStatus, errorThrown) {
			    			jBox.tip(tipText + "失败","error");
			    			reloadTable(); // 重新加载数据
			    		}
			    	});
			    } else if (v == 'cancel') {
					return true;
			    }
			    return true; 
			};
			if(options.singletonRow){
				jBox.confirm("确定要" + tipText + "吗？", "提示", submit);
			}else{
				jBox.confirm("确定要" + tipText + "选择的" + checkedArray.length + "行数据吗？", "提示", submit);
			}
		});
    };
})(jQuery);

var selectRow = {
	getFlag : function(){
		return $(".trSelected").length > 0;
	},
	getData : function(i){
		if(this.getFlag()){
			return $(".trSelected").find("td:eq("+i+")").html();
		}else{
			return null;
		}
	},
	getDataArr : function(col){
		if(this.getFlag()){
			var arr = new Array();
			$.each($(".trSelected"),function(i,o){
				arr.push($(o).find("td:eq("+col+")").html())
			});
			return arr;
		}else{
			return null;
		}
	}
};

function genStrYesNo(data){
	if(data == 0){
		return "否";
	}else if(data == 1){
		return "是";
	}
}
