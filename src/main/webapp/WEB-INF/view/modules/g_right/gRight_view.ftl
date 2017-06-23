<div style="display: table;width: 100%;border-bottom: 1px solid #34B8DF;">
	<div class="orgTitle" style="border-bottom:none;float:left;background:#E3D8D8;">
		菜单信息
	</div>
	<#if gright.isleaf==1>
		<div class="edit" id="buttonManage">按钮管理</div>
	</#if>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="200" align="right">名称：</td>
    <td>${gright.name!}</td>
  </tr>
  <tr>
    <td align="right">编码：</td>
    <td>${gright.code!}</td>
   </tr>
  <tr>
    <td align="right">菜单链接：</td>
    <td>${gright.menuurl!}</td>
  </tr>
  <tr>
    <td align="right">排序：</td>
    <td>${gright.sequence!}</td>
  </tr>
  </table>
  <script type="text/javascript">
$(function() {
	//按钮管理
	$("#buttonManage").on("click",function(){
		var treeObj = $.fn.zTree.getZTreeObj("departmentTree");
		var currNode = treeObj.getSelectedNodes();
		var nodeId = 0;
		if(currNode == null || currNode =='' || currNode.length ==0){
			$.jBox.info('请选择结点后再操作！', '提示');
		}else{
			jBox("iframe:${request.getContextPath()}/g_right/gRight/buttonList/"+currNode[0].id , {
			    title: "按钮列表",
			    width: 800,
				id:"editDiv",
			    height: 520,
				buttons: {}
			});
		}
	});
});
</script>       