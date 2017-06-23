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
<input type="hidden" id="userButton" value="${userButton}"/>
<#include "/common/top.ftl">
<div class="container">
	<#include "/common/left.ftl">
    <div id="main">
    	<span style="font-size:-webkit-xxx-large">您没有权限访问，请联系管理员。</span>
	</div>
</div>
<#include "/common/bottom.ftl">
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>

</body>
</html>
