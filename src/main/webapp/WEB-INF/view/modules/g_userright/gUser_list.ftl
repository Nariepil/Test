<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>组织机构</title>
<#include "/common/global_css.ftl"></head>
<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
<link href="${request.getContextPath()}/css/ztree/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<div class="container">
	<#include "/common/left.ftl">
    <div id="main">
    	<div class="position"><a href="">系统管理</a> > 权限管理</div>
        <div class="header">
            <div class="create" id="create">增加用户</div>
            <div class="edit" id="edit">修改</div>
            <div class="lock" id="resetPassword">密码重置</div>
            <div class="start" id="enable">启用</div>
            <div class="stop" id="disable">禁用</div>
        </div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="treeTable">
			<tr>
	    		<!--左侧目录树-->
		    	<td class="treeTd" width="160" valign="top">
			    	<div class="tree_ctrl">
			        	权限管理
			        </div>
			        <ul class="tree_ul ztree" id="departmentTree"></ul>
		    	</td>
			    <td class="org_Table" valign="top" id="viewDiv">
			    	
		        </td>
			</tr>
		</table>
	</div>
</div>
<#include "/common/bottom.ftl">
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<script type="text/javascript">
var pageContext;
var currNode;
var treeObj;
//验证左侧是否选中了树
function isSelectedUser(){
	treeObj = $.fn.zTree.getZTreeObj("departmentTree");
	currNode = treeObj.getSelectedNodes();
	if(currNode == null || currNode =='' || currNode.length ==0 || currNode[0].level!=1){
		$.jBox.info('请选择用户再修改！', '提示');
		return false;
	}
	return true;
}
$(function() {
	userButton =  $("#userButton").val();
	pageContext = $("#pageContext").val();
	function configTree() {
		setting = {
			async : {
				enable : true,
				url :  pageContext + "/g_userright/gUserright/findUserByOrgCode",
				autoParam : [ "id=orgCode" ]
			},
			view : {
				fontCss: getFont,
				expandSpeed : ""
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					if(treeNode && treeNode.level==1){
						$("#viewDiv").load(pageContext + "/g_userright/gUserright/userMenuList",{userId:treeNode.id});
					}else if(treeNode && treeNode.level==0){
						$('#viewDiv').html('');
					}
				}
			},
			onAsyncSuccess: zTreeOnAsyncSuccess
		};
		return setting;
	}
	$.fn.zTree.init($("#departmentTree"), configTree());
	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
	//修改
	$("#edit").on("click",function(){
		if(isSelectedUser()){
			jBox("iframe:${request.getContextPath()}/g_user/gUser/editGUser/"+currNode[0].id , {
			    title: "修改用户信息",
			    width: 702,
				id:"editDiv",
			    height: 252,
				buttons: {}
			});
		}
	});


	//禁用
	$("#disable").on("click",function(){
		if(isSelectedUser()){
			$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/g_userright/gUserright/disableUser/"+currNode[0].id),
	    		dataType : "json",
	    		success : function(data) {
	    			if(data.flag=='true'){
	    				var font = {"color":"white","background-color":"black"};
	    				currNode[0].font=font;
	    				treeObj.updateNode(currNode[0]);
	    				jBox.tip("禁用成功！","success");
	    			}else{
	    				jBox.info(data.message,"提示");
	    			}
	    		},
	    		error : function(){
	    			jBox.info("禁用失败","提示");
	    		}
	    	});
		
		}
	});
		//启用
	$("#enable").on("click",function(){
		if(isSelectedUser()){
			$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/g_userright/gUserright/enableUser/"+currNode[0].id),
	    		dataType : "json",
	    		success : function(data) {
	    			if(data.flag=='true'){
	    				var font = {"color":"","background-color":""};
	    				currNode[0].font=font;
	    				treeObj.updateNode(currNode[0]);
	    				jBox.tip("启用成功！","success");
	    			}else{
	    				jBox.info(data.message,"提示");
	    			}
	    		},
	    		error : function(){
	    			jBox.info("启用失败","提示");
	    		}
	    	});
		}
	});
	//密码重置
	$("#resetPassword").on("click",function(){
		if(isSelectedUser()){
			$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/g_userright/gUserright/resetPassword/"+currNode[0].id),
	    		dataType : "json",
	    		success : function(data) {
	    			if(data.flag=='true'){
	    				jBox.tip("重置密码成功！","success");
	    			}else{
	    				jBox.info(data.message,"提示");
	    			}
	    		},
	    		error : function(){
	    			jBox.info("重置密码失败","提示");
	    		}
	    	});
		}
	});
	function toAddView(nodeId){
		var url = encodeURI(pageContext + "/g_user/gUser/toAddGUser?orgCode="+nodeId)
		jBox("iframe:"+url , {
		    title: "新增用户",
		    width: 602,
			id:"editDiv",
		    height: 315,
			buttons: {}
		});
	}
	//增加用户
	$("#create").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		var nodeId = 0;
		if(currNode == null || currNode =='' || currNode.length ==0 || currNode[0].level!=0){
			nodeId = "1";
		}else{
			nodeId = currNode[0].id;
		}
			toAddView(nodeId);
	});

});
//复制其他用户的权限的iframe回调的方法，用户保存
function copyUserReceive(checkedUserIds){
	if(isSelectedUser()){
		$.ajax({
    		type : "post",
    		url : encodeURI("${request.getContextPath()}/g_userright/gUserright/copyUserRightSave/"+currNode[0].id),
    		dataType : "json",
    		data:{checkedUserIds:checkedUserIds},
    		success : function(data) {
    			if(data.flag=='true'){
    				$("#viewDiv").load(pageContext + "/g_userright/gUserright/userMenuList",{userId:currNode[0].id});
    				jBox.tip("复制权限成功！","success");
    			}else{
    				jBox.info(data.message,"提示");
    			}
    		},
    		error : function(){
    			jBox.info("复制权限失败","error");
    		}
    	});
	}
}

	var firstAsyncSuccessFlag = 0;
	function zTreeOnAsyncSuccess(event, treeId, msg) {  
	alert('1111111111');
		var zTree = $.fn.zTree.getZTreeObj("departmentTree");
		if (firstAsyncSuccessFlag == 0) {  
	          try {  
	             //调用默认展开第一个结点  
	             var selectedNode = zTree.getSelectedNodes();  
	             var nodes = zTree.getNodes();  
				 zTree.expandNode(nodes[0], true);  
	          
	             var childNodes = zTree.transformToArray(nodes[0]);  
	             zTree.expandNode(childNodes[1], true);  
	             zTree.selectNode(childNodes[1]);  
	             var childNodes1 = zTree.transformToArray(childNodes[1]);  
	             zTree.checkNode(childNodes1[1], true, true);  
	             firstAsyncSuccessFlag = 1;  
	           } catch (err) {  
	              
	           }  
	              
	     }  
	}
</script>
</body>
</html>
