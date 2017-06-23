<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>菜单管理</title>
<#include "/common/global_css.ftl"></head>
<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
<link href="${request.getContextPath()}/css/ztree/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<input type="hidden"  id="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="userButton" value="${userButton}"/>
<#include "/common/top.ftl">
<div class="container">
	<#include "/common/left.ftl">
    <div id="main">
    	<div class="position"><a href="">系统设置</a> > 权限管理</div>
        <div class="header">
            <div class="create" id="createTJ">增加</div>
            <div class="create" id="create">增加下级菜单</div>
            <div class="del">删除</div>
            <div class="edit" id="edit">修改</div>
        </div>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="treeTable">
			<tr>
	    		<!--左侧目录树-->
		    	<td class="treeTd" width="160" valign="top">
			    	<div class="tree_ctrl">
			        	菜单管理
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
var currentNode;
var userButton;
$(function() {
	userButton =  $("#userButton").val();
	pageContext = $("#pageContext").val();
	var firstAsyncSuccessFlag = 0;
	function zTreeOnAsyncSuccess(event, treeId, msg) {  
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
	function configTree() {
		setting = {
			async : {
				enable : true,
				url :  pageContext + "/g_right/gRight/findChildren",
				autoParam : [ "id" ]
			},
			view : {
				expandSpeed : ""
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					currentNode=treeNode;
					if(currentNode){
						$("#viewDiv").load(pageContext + "/g_right/gRight/viewDepartment?id="+currentNode.id);
					}
				},
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		};
		return setting;
	}
	$.fn.zTree.init($("#departmentTree"), configTree());
	
	//修改
	$("#edit").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		var nodeId = 0;
		if(currNode == null || currNode =='' || currNode.length ==0){
			$.jBox.info('请选择结点后再修改！', '提示');
		}else{
			jBox("iframe:${request.getContextPath()}/g_right/gRight/editGRight/"+currNode[0].id , {
			    title: "修改菜单信息",
			    width: 585,
				id:"editDiv",
			    height: 246,
				buttons: {}
			});
		}
	});
	function toAddView(nodeId){
		var url = encodeURI(pageContext + "/g_right/gRight/toAddGRight?pId="+nodeId+"&type=0")
		jBox("iframe:"+url , {
		    title: "新增菜单",
		    width: 589,
			id:"editDiv",
		    height: 235,
			buttons: {}
		});
	}
	//增加同级地区或者根地区
	$("#createTJ").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		var nodeId = 0;
		if(currNode == null || currNode =='' || currNode.length ==0){
			$.jBox.confirm("目前未选中节点会新增根节点，确定要添加吗？", "提示", function(v, h, f){
			    if (v == 'cancel')
			        return true;
		       else if (v=='ok'){
		       		toAddView(nodeId);
		       }
			});
		}else{
			if(currNode[0].level > 0){
				nodeId = currNode[0].getParentNode().id;
			}
			toAddView(nodeId);
		}
		
	});
	//增加下级地区
	$("#create").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		var nodeId = 0;
		if(currNode == null || currNode =='' || currNode.length ==0){
			$.jBox.info('请选择地区在增加！', '提示');
			return false;
		}else{
			nodeId = currNode[0].id;
			toAddView(nodeId);
		}
	});
	$(".del").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		
		if(currNode == null || currNode.length == 0){
			$.jBox.info('请先选择要删除的结点', '提示');
		}else{
			currentNode = currNode[0];
			var message='您确定要删除"'+currentNode.name+'"';
			if(currentNode.isParent){
				message+='及其下面的菜单和按钮吗';
			}
			jBox.confirm(message, "提示", function(v, h, f){
				if (v == 'ok') {
			    	$.ajax({
			    		type : "post",
			    		url : encodeURI(pageContext + "/g_right/gRight/deleteGRight"),
			    		dataType : "json",
			    		data : {id:currentNode.id},
			    		success : function(data) {
			    			if(data.flag=='true'){
			    				var zTree = $.fn.zTree.getZTreeObj("departmentTree"); 
			    				zTree.removeNode(currentNode);
			    				currentNode=null;
			    				$("#viewDiv").html("");
			    				jBox.tip("删除成功","success");
			    			}else{
			    				jBox.info(data.message,"提示");
			    			}
			    		},
			    		error : function(){
			    			jBox.info("删除失败","提示");
			    		}
			    	});
			    }
				return true;
			});
		}
	});
});
</script>
</body>
</html>
