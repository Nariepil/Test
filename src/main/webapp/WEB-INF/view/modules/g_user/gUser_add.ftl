<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加用户表</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#999;}
		.form{overflow:auto;background:#F7F7F7;height:500px;}
	</style>
</head>
<body>
	<div class="form" style="width: 600px;height: 287px;">
		<form class="addForm" target="_parent" id="gUserAddForm" action="${request.getContextPath()}/g_user/gUser/saveGUser" method="post">
			<input type="hidden" id="pageContext" value="${request.getContextPath()}" />
			<input type="hidden" name="orgCode" value="${orgCode!}" />
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;" class="boxTable boxmar">
					<tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>用户名:</td>
		      			<td style="width:150px">
		      				<input name="name" id="name" class="cword"   datatype="s2-16;loginCodeUniqueValid" nullmsg="请输入用户名！" errormsg="用户名至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <#--<tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>登录用户编码（简称）:</td>
		      			<td style="width:150px">
		      				<input name="loginid" id="loginid" class="cword" datatype="s2-16" nullmsg="请输入用户名！" errormsg="用户名至少2个字符,最多16个字符！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>-->
		            <tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>密码:</td>
		      			<td style="width:150px">
		      				<input name="password" type="password"  id="password" class="cword"  datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>确认密码:</td>
		      			<td style="width:150px">
		      				<input name="checkPassword" type="password" id="checkPassword" class="cword"  datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！"/>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>用户级别：</td>
		      			<td style="width:150px">
		      				<select name="grade" id="grade" class="word" style="width:220px;">
								<option value="1">超级用户</option>
								<option value="0" selected="selected">管理员</option>
							</select>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="right" style="width:160px"><span class="check">*</span>用户状态:</td>
		      			<td style="width:150px">
		      				<select name="isused" id="isused" class="word len90">
								<option value="1" selected="selected">启用</option>
								<option value="0">停用</option>
							</select>
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
	            <tr align="center">
    				<td colspan="3">
    					<input class="btn" type="button" id="saveBtn" value="保存" style="margin-left: 1px;">
    					<input type="button" class="btn dis cancel" id="cancelBtn" value="取消">
    				</td>
  				</tr>
	  		</table>
		</form>
	</div>
	<script type="text/javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/jQuery.md5.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/allPage.js" contextPath="${request.getContextPath()}"  id="allPageJs"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/chosen/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#gUserAddForm').Validform({
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
								loginCode: gets
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
				} ,
				beforeSubmit:function(curform){
					//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
					//这里明确return false的话表单将不会提交;	
					$('#password').val($.md5($('#password').val()));
					$('#checkPassword').val($.md5($('#checkPassword').val()));
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