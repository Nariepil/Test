<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改用户表</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#999;}
		.form{overflow:auto;background:#F7F7F7;height:600px;}
	</style>
</head>
<body>
	<div class="form" style="width: 700px;height: 225px;">
		<form class="editForm" target="_parent" id="gUserEditForm" action="${request.getContextPath()}/g_user/gUser/updateGUser" method="post">
			<input type="hidden" name="id" value="${gUser.id}"/>
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;" class="boxTable boxmar">
				<input type="hidden" name="id" value="${gUser.id}"/>
	            
	            <tr>
	            	<td align="right" style="width:160px"><span class="check">*</span>用户名：</td>
	      			<td style="width:150px">
	      				<input name="name" id="name" class="cword"  value="${(gUser.name)!''}"  datatype="s2-16;loginCodeUniqueValid" nullmsg="请输入用户名！" errormsg="用户名至少2个字符,最多16个字符！"/>
	            	</td>
	            	<td>
    					<div class="Validform_checktip"></div>
    				</td>
	            </tr>
	           <#--<tr>
	            	<td align="right" style="width:160px"><span class="check">*</span>登录用户编码（简称）：</td>
	      			<td style="width:150px">
	      				<input name="loginid" id="loginid" class="cword" value="${(gUser.loginid)!''}" datatype="s2-16;loginCodeUniqueValid;loginUniqueValid" nullmsg="请输入用户名！" errormsg="用户名至少2个字符,最多16个字符！"/>
	            	</td>
	            	<td>
    					<div class="Validform_checktip"></div>
    				</td>
	            </tr>-->
	            <tr>
	            	<td align="right" style="width:160px"><span class="check">*</span>用户级别（类型）：</td>
	      			<td style="width:150px">
	      				<select name="grade" id="grade" class="word" style="width:220px;">
							<option value="1" <#if 1==(gUser.grade)!>  selected="selected"</#if> >超级用户</option>
							<option value="0" <#if 0==(gUser.grade)!>  selected="selected"</#if> >管理员</option>
						</select>
	            	</td>
	            	<td>
    					<div class="Validform_checktip"></div>
    				</td>
	            </tr>
	            <tr>
	            	<td align="right" style="width:160px"><span class="check">*</span>用户停启用：</td>
	      			<td style="width:150px">
	      				<select name="isused" id="isused" class="word len90">
							<option value="1" <#if 1==(gUser.isused)!>  selected="selected"</#if> >启用</option>
							<option value="0" <#if 0==(gUser.isused)!>  selected="selected"</#if> >停用</option>
						</select>
	            	</td>
	            	<td>
    					<div class="Validform_checktip"></div>
    				</td>
	            </tr>
	            <tr align="center">
    				<td colspan="3">
    					<input class="btn" type="button" id="saveBtn" value="保存" style="margin-left: -100px;">
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
			$('#gUserEditForm').Validform({
				btnSubmit:"#saveBtn", 
				tiptype:2,
				showAllError:true,
				datatype:{
					"loginCodeUniqueValid":function(gets,obj,curform,regxp){
					//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
						var flag = true;
						var returnMessage = '';
						$.ajax({
							type: "get",
							cache: false,
							url: "${request.getContextPath()}/g_user/gUser/loginCodeUniqueValid",
							data: {
								loginCode: gets,
								id:$('input[name="id"]').val()
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
					},
					"loginUniqueValid":function(gets,obj,curform,regxp){
						//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
						var nameVal = $('#name').val();
						if(nameVal==gets){
							return '登录用户编码（简称）不能和用户名 相同！';
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