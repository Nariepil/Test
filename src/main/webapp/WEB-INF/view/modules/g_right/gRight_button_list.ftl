<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>按钮管理</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/datatable/datatable.css" rel="stylesheet" type="text/css"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<#include "/common/top.ftl">
	<div class="container">
		<#include "/common/left.ftl">
	    <div id="main">
	    	<div class="position"><a>基础数据管理</a> > 权限列表列表</div>
	    	<div class="search_container"  style="display:none">
        		<form class="search_c" id="searchForm">
        			<input type="hidden" id="userButton" value="${userButton}"/>
        				<div class="sc">
	            			<input type="hidden" class="word" name="search_EQ_parentid" value="${parentId!}">
	            		</div>
            	</form>
        	</div>
	    	<div class="header header_no_martop">
	        	<div class="create"><a href="javascript:;" onclick="toAdd()">新增</a></div>
	        	<div class="del"><a href="javascript:;" id="delBtn" targetUrl="${request.getContextPath()}/g_right/gRight/batchDeleteGRight">删除</a></div>
	     	</div>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data_Table" id="gRight_list">
            	<thead>
            		<tr class="header_title">
		            	<td width="3%"><input type="checkbox" name="checkAllT" id="checkALLT" /></td>
		                	<td width="10%">编号</td>
		                	<td width="10%">编码</td>
		                	<td width="10%">名称</td>
		                	<td width="10%">按钮id</td>
		                	<td width="10%">菜单链接</td>
		                	<td width="10%">排序号</td>
						<td width="8%">操作</td>
	                </tr>
	            </thead>
	        </table>
	    </div>
	</div>
	<#include "/common/bottom.ftl">
	<script type="text/javascript" src="${request.getContextPath()}/js/datatable/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/rbcList.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript">
		function toAdd() {
			jBox("iframe:${request.getContextPath()}/g_right/gRight/toAddGRight?pId=${parentId!}&type=1", {
				title: "添加权限列表",
			    width: 600,
				id:"gRightAddForm",
			    height: 300,
				buttons: {}
			});
		}
		
		function toEdit(id) {
			jBox("iframe:${request.getContextPath()}/g_right/gRight/editGRight/" + id, {
				title: "修改权限列表",
			    width: 600,
				id:"gRightEditForm",
			    height: 280,
				buttons: {}
			});
		}
		var userButton;

		$(function() {
			userButton = $("#userButton").val();
		
			var columns = [
		    	{"mDataProp" : null, "bSortable" : false,
					"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
						$(nTd).html("<input type=\"checkbox\" class=\"chk_list\" value=\"" + oData.id + "\">");
					}
				},
						{"mDataProp" : "id", "bSortable" : false},					
						{"mDataProp" : "code", "bSortable" : false},					
						{"mDataProp" : "name", "bSortable" : false},					
						{"mDataProp" : "buttonId", "bSortable" : false},					
						{"mDataProp" : "menuurl", "bSortable" : false},					
						{"mDataProp" : "sequence", "bSortable" : false},					
				{"mDataProp" : null, "bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
							$(nTd).html("<a class=\"editicon\"  href='javascript:;'  onclick=\"toEdit('"+oData.id+"')\"  title=\"修改\">修改</a>");
					}
				}
			];
			$.rbc({
				"targetTable" : '#gRight_list',
				"tableDataUrl" : '${request.getContextPath()}/g_right/gRight/customPageAjax',
				"tableColumns" : columns,
				"checkAll" : '#checkALLT',
				"tableHeaderOpers" : '#delBtn',
				"msg" : '${message}'
			});
			
			$('.chosen').chosen({
				"no_results_text":'未找到匹配数据!',
				"width":"160px",
				"allow_single_deselect":true
			});
		});
	</script>
</body>
</html>