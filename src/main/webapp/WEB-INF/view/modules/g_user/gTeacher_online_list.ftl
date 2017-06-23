<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>在线用户</title>
	<#include "/common/global_css.ftl">
	<script type="text/javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/datatable/datatable.css" rel="stylesheet" type="text/css"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<!--<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>-->
	<#include "/common/top.ftl">
	<div class="container">
		<#include "/common/left.ftl">
	    <div id="main">
	    	<div class="position"><a>系统设置</a> > 在线用户列表</div>
	     	<div class="sc">
	            	角色：
	           <select name="search_EQ_grade" id="search_EQ_grade" class="chosen" data-placeholder="角色" onchange="skip_grade(this.value)">
                  <option value="2">在线教师</option>	
                  <option value="1">在线学生</option>	
                  <option value="0">在线管理</option>
		       </select>
	        </div>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data_Table" id="gUser_list">
            	<thead>
            		<tr class="header_title">
		                	<td width="10%">id</td>
		                	<td width="10%">编号</td>
		                	<td width="10%">姓名</td>
		                	<td width="10%">性别 </td>
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
		function reload(){
			window.location.href=window.location.href;
		}
		var userButton;

		$(function() {
			userButton = $("#userButton").val();
		
			var columns = [
		    	//{"mDataProp" : null, "bSortable" : false,
				//	"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
				//		$(nTd).html("<input type=\"checkbox\" name=\"chk_list\" class=\"chk_list\" value=\"" + oData.id + "\">");
				//	}
				//},
						{"mDataProp" : "id", "bSortable" : false},					
						{"mDataProp" : "teacherCode", "bSortable" : false},					
						{"mDataProp" : "teacherName", "bSortable" : false},					
						{"mDataProp" : "gender", "bSortable" : false,
							"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
								if(oData.gender==0){
									$(nTd).html("男");
								}else if(oData.gender==1){
									$(nTd).html("女");
								}
							}	
						},					
			];
			$.rbc({
				"targetTable" : '#gUser_list',
				"tableDataUrl" : '${request.getContextPath()}/getAllOnlineTeacher/findTeacherAjax',
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
		function skip_grade(value) {
			if(value == '0'){
				 var url="${request.getContextPath()}/getAllOnlineUser";
       			 window.location.href=url;
			}
			if(value == '1'){
				var url="${request.getContextPath()}/getAllOnlineStudent";
       			window.location.href=url;
			}
			if(value == '2'){
				var url="${request.getContextPath()}/getAllOnlineTeacher";
       			window.location.href=url;
			}
			
		}
	</script>
</body>
</html>