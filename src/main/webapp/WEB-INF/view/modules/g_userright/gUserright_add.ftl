<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加用户权限表</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#999;}
		.form{overflow:auto;background:#F7F7F7;height:500px;}
	</style>
</head>
<body>
	<div class="form">
		<form class="addForm" target="_parent" id="gUserrightAddForm" action="${request.getContextPath()}/g_userright/gUserright/saveGUserright" method="post">
			<input type="hidden" id="pageContext" value="${request.getContextPath()}" />
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;" class="boxTable boxmar">
					<tr>
		            	<td align="right" style="width:80px"><span class="check">*</span>用户编号，对应g_user表id：</td>
		      			<td style="width:150px">
		      				<input name="userId" id="userId" class="cword"   datatype="s2-16" nullmsg="请输入用户编号，对应g_user表id！" errormsg="用户编号，对应g_user表id至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
					<tr>
		            	<td align="right" style="width:80px"><span class="check">*</span>权限编号，对应g_right表id：</td>
		      			<td style="width:150px">
		      				<input name="rightId" id="rightId" class="cword"   datatype="s2-16" nullmsg="请输入权限编号，对应g_right表id！" errormsg="权限编号，对应g_right表id至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
	            <tr align="center">
    				<td colspan="3">
    					<input class="btn" type="button" id="saveBtn" value="保存">
    					<input type="button" class="btn dis cancel" id="cancelBtn" value="取消">
    				</td>
  				</tr>
	  		</table>
		</form>
	</div>
	<script type="text/javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/allPage.js" contextPath="${request.getContextPath()}"  id="allPageJs"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#gUserrightAddForm').Validform({
				btnSubmit:"#saveBtn", 
				tiptype:2,
				showAllError:true
			});
			$('.chosen').chosen({
				"no_results_text":'未找到匹配数据!',
				"width":"120px",
				"allow_single_deselect":true
			});
			$('#cancelBtn').click(function(){
				parent.jBox.close(true);
			});
		});
	</script>
</body>
</html>