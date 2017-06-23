<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户权限表管理</title>
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
	    	<div class="position"><a>系统设置</a> > 用户权限列表</div>
	    	<div class="search_container">
        		<form class="search_c" id="searchForm">
        			<input type="hidden" id="userButton" value="${userButton}"/>
        				<div class="sc">
	            			主键：<input type="text" class="word" name="search_EQ_id">
	            		</div>
        				<div class="sc">
	            			用户编号，对应g_user表id：<input type="text" class="word" name="search_EQ_userId">
	            		</div>
        				<div class="sc">
	            			权限编号，对应g_right表id：<input type="text" class="word" name="search_EQ_rightId">
	            		</div>
                	<i class="search_btn" id="searchBtn"></i>
                	<i class="reset_btn" id="searchResetBtn"></i>
            	</form>
        	</div>
	    	<div class="header header_no_martop">
	        	<div class="create"><a href="javascript:;" onclick="toAdd()">新增</a></div>
	        	<div class="del"><a href="javascript:;" id="delBtn" targetUrl="${request.getContextPath()}/g_userright/gUserright/batchDeleteGUserright">删除</a></div>
	     	</div>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data_Table" id="gUserright_list">
            	<thead>
            		<tr class="header_title">
		            	<td width="3%"><input type="checkbox" name="checkAllT" id="checkALLT" /></td>
		                	<td width="10%">主键</td>
		                	<td width="10%">用户编号，对应g_user表id</td>
		                	<td width="10%">权限编号，对应g_right表id</td>
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
			jBox("iframe:${request.getContextPath()}/g_userright/gUserright/toAddGUserright", {
				title: "添加用户权限表",
			    width: 600,
				id:"gUserrightAddForm",
			    height: 600,
				buttons: {}
			});
		}
		
		function toEdit(id) {
			jBox("iframe:${request.getContextPath()}/g_userright/gUserright/editGUserright/" + id, {
				title: "修改用户权限表",
			    width: 600,
				id:"gUserrightEditForm",
			    height: 600,
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
						{"mDataProp" : "userId", "bSortable" : false},					
						{"mDataProp" : "rightId", "bSortable" : false},					
				{"mDataProp" : null, "bSortable" : false,
					"fnCreatedCell" : function(nTd, sData, oData, iRow, iCol) {
							$(nTd).html("<a class=\"editicon\"  href='javascript:;'  onclick=\"toEdit('"+oData.id+"')\"  title=\"修改\"></a>");
					}
				}
			];
			$.rbc({
				"targetTable" : '#gUserright_list',
				"tableDataUrl" : '${request.getContextPath()}/g_userright/gUserright/customPageAjax',
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