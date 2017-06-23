<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改密码</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		body{margin:0px;font-size:12px;color:#333;}
		.form{overflow:auto;background:#F7F7F7;height:auto;}
		p{
			padding: 8px;
		}
	</style>
</head>
<body>
	<div class="main">
	<div class="position"><a href="">系统设置</a> > 重置密码</div>
	<div class="search_container">
        		<form class="search_c" id="searchForm" action="${request.getContextPath()}/resetPwd/reset/search" method="post">
        				<div class="sc">
	            			登录名：<input type="text" class="word" name="loginCode" id="sloginCode" value='${code}'>
	            		</div>
	            		<!--
        				<div class="sc">
	            			姓名：<input type="text" class="word" name="sname" id="sname">
	            		</div>
	            		-->
        				<div class="sc">
	            			用户类型：
	            			<select id="sType" name="type" class="word" data-placeholder="角色" value='${type}'>
                        		<option value="">--请选择--</option>
                        		<option value="0">教务</option>
                        		<option value="1">教师</option>	
                        		<option value="2">学生</option>	
		                    </select>
	            		</div>
                	<i class="search_btn"  id="searchBtn"></i>
                	<i class="reset_btn" id="resetBtn"></i>
            	</form>
      </div>
	<form class="addForm" target="_parent" id="mPwdViewForm" action="${request.getContextPath()}/resetPwd/reset/resetPassword" method="post">
			<input type="hidden" id="pageContext" value="${request.getContextPath()}" />
			<input type="hidden" name="id" id = "uid" value="${gResetPwdUser.id!}" />
			<table border="0" cellspacing="0" cellpadding="0" width="100%" style="margin-top:20px;" class="boxTable boxmar">
					<span>
		            	<p align="left" style="width:200px">登录名:
		      				${gResetPwdUser.loginCode!}
		      			</p>
		            </span>
					<span>
		            	<p align="left" style="width:200px">姓名:
		      				${gResetPwdUser.name!}
		      			</p>
		            </span>
					<span>
		            	<p align="left" style="width:200px">类型:
		            		<#if gResetPwdUser.type=='0'>
		            			教务
		            		<#elseif gResetPwdUser.type=='1'>
		            			教师
		            		<#elseif gResetPwdUser.type=='2'>
		            			学生
		            		</#if>
		      			</p>
		            </span>
		            <span>
		            	<p align="right" style="width:400px">
    						<input class="btn" type="button" id="saveBtn" value="重置密码" onclick="resetPassword();">
    					</p>
    				</span>	
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
		$(document).ready(function(){
			var type = '${type}';
			$("#sType option[value='"+type+"']").attr("selected","selected");
		})
		$("#resetBtn").click(function(){
 			$('#sloginCode').val('');
 			//$('#sname').val('');
        	$('select').val('');
		});
		$("#searchBtn").click(function(){
			var sloginCode = $('#sloginCode').val();
        	var select = $('select').val();
        	if(sloginCode == ""){
        		jBox.info("请输入登录名","提示");
        		return;
        	}
        	if(select == ""){
        		jBox.info("请选择用户类型","提示");
        		return;
        	}
        	 document.getElementById("searchForm").submit();
		})
		//密码重置
		function resetPassword() {
            var sloginCode = $('#sloginCode').val();
			if(sloginCode== null){
				$.jBox.info("没有要操作的数据！","提示");
			}else{ 
				var id = $('#uid').val();
				var type = ${gResetPwdUser.type}
	            $.ajax({
	                type:"post",
	                url:"${request.getContextPath()}/resetPwd/reset/resetPassword",
	                dataType:"json",
	                data:{"id" :id, "type":type},
	                success:function (data) {
	               		 if(data.flag=='true'){
	 						jBox.tip("重置密码成功！","success");
	 						setTimeout(reload,1000)
 						}else{
	    					jBox.tip("重置密码失败！", "success");
	    					setTimeout(reload,1000)
	    				}
	                }
	            });
	         }   
		}
	</script>
</body>
</html>