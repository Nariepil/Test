<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>绑定ip地址</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#999;}
		.form{overflow:auto;background:#F7F7F7;height:273px;}
	</style>
</head>
<body>
	<div class="form">
		<div class="position"><a href="">系统设置</a> > IP地址设置</div>
		<form class="addForm" target="_parent" id="mPwdViewForm" action="${request.getContextPath()}/g_user/gUser/bindIp" method="post">
			<input type="hidden" id="pageContext" value="${request.getContextPath()}" />
			<input type="hidden" name="id" value="${gUser.id!}" />
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;" class="boxTable boxmar">
					<tr>
		            	<td align="center" style="width:160px"><span class="check">*</span>用户名:</td>
		      			<td style="width:150px">
		      				${gUser.name!}
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		           <tr>
		            	<td align="center" style="width:160px"><span class="check">*</span>当前ip地址:</td>
		      			<td style="width:150px">
		      				${currentIp}
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		           <tr>
		            	<td align="center" style="width:160px"><span class="check">*</span>原绑定ip地址:</td>
		      			<td style="width:150px">
		      				${gUser.lockIp!"无"}
		            	</td>
		            	<td>
	    					<div class="Validform_checktip"></div>
	    				</td>
		            </tr>
		            <tr>
		            	<td align="center" style="width:160px"><span class="check">*</span>新绑定ip地址:</td>
		      			<td style="width:150px">
		      				<input name="newIpAddr" type="" style="width:100px" id="newIpAddr" class="cword" />
		            	</td>
		            </tr>
	            <tr align="center">
    				<td colspan="3">
    					<input class="btn" type="button" id="saveBtn" value="保存">
    					<input type="button" class="btn dis cancel" id="cancelBtn" value="取消">
    					<input type="button" class="btn dis cancel" id="removeBtn" value="解除绑定">
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
	<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
	<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			var newIpAddr ;
			$('#mPwdViewForm').Validform({
				btnSubmit:"#saveBtn", 
				tiptype:2,
				showAllError:true,
				postonce:true,
				ajaxPost:true,
				beforeSubmit:function(curform){
					//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
					//这里明确return false的话表单将不会提交;	
					newIpAddr = $('#newIpAddr').val();
				},
				callback:function(data){
					if(data.flag == 'true'){
						$.jBox.alert('绑定成功,请在该ip地址登录!','确认',{submit:function(){
								window.parent.logout();
							}}
						);
					}else{
						$.jBox.info(data.message, 'error');
						$('#newIpAddr').val('');
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