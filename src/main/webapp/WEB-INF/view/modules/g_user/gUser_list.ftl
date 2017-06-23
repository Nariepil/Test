<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户表管理</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/datatable/datatable.css" rel="stylesheet" type="text/css"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
	<style>
        .sc{ margin-top: -9px;}
    </style>
</head>
<body>
	<!--<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>-->
	<#include "/common/top.ftl">
	<div class="container">
		<#include "/common/left.ftl">
	    <div id="main">
	    	<div class="position"><a>系统管理</a> > 用户列表</div>
	    	<div class="search_container">
        		<form class="search_c" id="searchForm">
        			<input type="hidden" id="userButton" value="${userButton}"/>
        				<div class="sc">
	            			编号：<input type="text" class="word" name="search_EQ_loginid">
	            		</div>
        				<div class="sc">
	            			姓名：<input type="text" class="word" name="search_EQ_name">
	            		</div>
        				<div class="sc">
	            			角色：
	            			<select name="search_EQ_grade" class="chosen" data-placeholder="角色">
	            				<option value="" selected></option>
                        		<option value="0">管理员</option>
                        		<option value="1">超级用户</option>	
		                    </select>
	            		</div>
	            		<div class="sc">
	            			是否启用：
	            			<select name="search_EQ_isused" class="chosen" data-placeholder="是否启用">
	            				<option value="" selected></option>
                        		<option value="1">是</option>	
                        		<option value="0">否</option>
		                    </select>
	            		</div>
                	<i class="search_btn" id="searchBtn"></i>
                	<i class="reset_btn" id="searchResetBtn"></i>
            	</form>
        	</div>
	    	<div class="header header_no_martop">
	    		<form id="submitForm" method="post"></form>
	     		<div class="lock"><a href="javascript:;" id="resetPasswordBtn" onclick="resetPassword()">重置密码</a></div>
	        	<div class="start"><a id="revoke" onclick="enableUser()">启用</a></div>
	     		<div class="stop"><a id="release" onclick="disableUser()">禁用</a></div>
	     	</div>
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="data_Table" id="gUser_list">
            	<thead>
            		<tr class="header_title">
		            	<td width="3%"><input type="checkbox" name="checkAllT" id="checkALLT" /></td>
		                	<td width="10%">id</td>
		                	<td width="10%">编号</td>
		                	<td width="10%">姓名</td>
		                	<td width="10%">用户等级 </td>
		                	<td width="10%">状态</td>
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
			jBox("iframe:${request.getContextPath()}/g_user/gUser/toAddGUser", {
				title: "添加用户表",
			    width: 600,
				id:"gUserAddForm",
			    height: 350,
				buttons: {}
			});
		}
		
		function toEdit(id) {
			jBox("iframe:${request.getContextPath()}/g_user/gUser/editGUser/" + id, {
				title: "修改用户表",
			    width: 600,
				id:"gUserEditForm",
			    height: 600,
				buttons: {}
			});
		}
		//密码重置
		function resetPassword() {
            var arrChk=$("input[name='chk_list'][checked]");
			if(arrChk.size()>1||arrChk.size()==0){
				$.jBox.info("请选择单行数据进行操作！","提示");
			}else{ 
				var id = $(arrChk).val();
	            $.ajax({
	                type:"post",
	                url:"${request.getContextPath()}/g_user/gUser/resetPassword",
	                dataType:"json",
	                data:"id="+id,
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
		//启用用户
		function enableUser() {
			if ($(".chk_list:checked").length < 1) {
				$.jBox.info("请选择启用用户!", '提示');
				return;
			}
			$(".chk_list:checked").each(function(){
				$("#submitForm").append("<input type='hidden' name='ids' value='"+$(this).val()+"' />");
			});
			//var form =  new FormData(document.getElementById("submitForm"));
			var idss = [];
			$($("input[name=ids]")).each(function(){
				idss.push(parseInt($(this).val()));
			});
			$.ajax({
			    type: 'POST',
			    url: '${request.getContextPath()}/g_user/gUser/enableUser',
			    data: {ids:idss},
			    dataType: 'JSON',
			    success: function(data){
			    	 if(data.flag=='true'){
			    			jBox.tip(data.message,"success");
                    		setTimeout(reload,1000)
 						}else{
	    					jBox.tip(data.message,"success");
                    		setTimeout(reload,1000)
	    				}
				}
			});
			$("#submitForm").html("");
		}
		//禁用用户
		function disableUser() {
			if ($(".chk_list:checked").length < 1) {
				$.jBox.info("请选择禁用用户!", '提示');
				return;
			}
			$(".chk_list:checked").each(function(){
				$("#submitForm").append("<input type='hidden' name='ids' value='"+$(this).val()+"' />");
			});
			//var form =  new FormData(document.getElementById("submitForm"));
			var idss = [];
			$($("input[name=ids]")).each(function(){
				idss.push(parseInt($(this).val()));
			});
			$.ajax({
			    type: 'POST',
			    url: '${request.getContextPath()}/g_user/gUser/disableUser',
			    data: {ids:idss},
			    dataType: 'JSON',
			    success: function(data){
			    	 if(data.flag=='true'){
			    			jBox.tip(data.message,"success");
                    		setTimeout(reload,1000)
 						}else{
	    					jBox.tip(data.message,"success");
                    		setTimeout(reload,1000)
	    				}
				}
			});
			$("#submitForm").html("");
		}
		
		function reload(){
			window.location.href=window.location.href;
		}
		var userButton;

		$(function() {
			userButton = $("#userButton").val();
		
			var columns = [
		    	{"mDataProp" : null, "bSortable" : false,
					"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
						$(nTd).html("<input type=\"checkbox\" name=\"chk_list\" class=\"chk_list\" value=\"" + oData.id + "\">");
					}
				},
						{"mDataProp" : "id", "bSortable" : false},					
						{"mDataProp" : "loginid", "bSortable" : false},					
						{"mDataProp" : "name", "bSortable" : false},					
						{"mDataProp" : "grade", "bSortable" : false,
							"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
								if(oData.grade==0){
									$(nTd).html("管理员");
								}else if(oData.grade==1){
									$(nTd).html("超级用户");
								}
							}	
						},					
						{"mDataProp" : "isused", "bSortable" : false,
							"fnCreatedCell" : function(nTd, sData,oData, iRow, iCol){
								if(oData.isused==0){
									$(nTd).html("停用");
								}else if(oData.isused==1){
									$(nTd).html("启用");
								}else{
									$(nTd).html("");
								}
							}
						},					
			];
			$.rbc({
				"targetTable" : '#gUser_list',
				"tableDataUrl" : '${request.getContextPath()}/g_user/gUser/customPageAjax',
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