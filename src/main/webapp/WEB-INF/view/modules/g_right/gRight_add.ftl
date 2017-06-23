<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加权限列表</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#999;}
		.form{overflow:auto;background:#F7F7F7;height:500px;}
	</style>
</head>
<body>
	<div class="form" style="height: 208px; width: 589px;">
		<form class="addForm" target="_parent" id="gRightAddForm" action="${request.getContextPath()}/g_right/gRight/saveGRight" method="post">
			<input type="hidden" id="pageContext" value="${request.getContextPath()}" />
			<input type="hidden" name="pId" id='pId' value="${pId}" />
			<input type="hidden" name="type" id='type' value="${type}" />
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;height: 184px; width: 543px;" class="boxTable boxmar">
					<tr>
		            	<td align="right" style="width:80px"><span class="check">*</span>编码：</td>
		      			<td style="width:150px">
		      				<input name="code" id="code" class="cword"   datatype="s2-16;codeUniqueValid" nullmsg="请输入编码！" errormsg="编码至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
					<tr>
		            	<td align="right" style="width:80px"><span class="check">*</span>名称：</td>
		      			<td style="width:150px">
		      				<input name="name" id="name" class="cword"   datatype="s2-16" nullmsg="请输入名称！" errormsg="名称至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="right" style="width:80px"><span class="check">*</span>菜单链接：</td>
		      			<td style="width:150px">
		      				<input name="menuurl" id="menuurl" class="cword" datatype="*" nullmsg="请输入菜单链接！"  maxlength="100" />
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <#if type == 1>
		            	<tr>
			            	<td align="right" style="width:80px"><span class="check">*</span>按钮id：</td>
			      			<td style="width:150px">
			      				<input name="buttonId" id="buttonId" class="cword" />
			            	</td>
			            	<td>
		    					<div class="Validform_checktip"></div>
		    				</td>
			            </tr>
		            </#if>
		            <tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>菜单权限：</td>
		      			<td style="width:150px">
		      				<select name="projectId" id="projectId" class="word" style="width:220px;">
								<option value="1">教务权限菜单</option>
								<!--<option value="2" selected="selected">教师权限菜单</option>-->
							</select>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="right" style="width:160px">排序字段：</td>
		      			<td style="width:150px">
		      				<input name="sequence" id="sequence" class="cword" datatype="n;n1-4"  ignore="ignore"  errormsg="只能是1-4位数字！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
	            <tr align="center">
    				<td colspan="3">
    					<input class="btn" type="button" id="saveBtn" value="保存" style="margin-left: 53px;">
    					
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
			$('#gRightAddForm').Validform({
				btnSubmit:"#saveBtn", 
				tiptype:2,
				showAllError:true,
				datatype:{
					"codeUniqueValid":function(){
						var flag = true;
						var returnMessage = '';
						$.ajax({
							type: "get",
							cache: false,
							url: "${request.getContextPath()}/g_right/gRight/codeUniqueValid",
							data: {
								code: $("#code").val()
							},
							dataType: "json",
							async: false,
							success: function(data){
								if (data.flag == 'false') {
									flag = false;
									returnMessage = data.message;
								}
							}
						});
						if (!flag) {
							return returnMessage;
						}
					}
				}
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