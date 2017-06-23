<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title id="ti">后台管理系统</title>
	<#include "/common/global_css.ftl">
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/datatable/datatable.css" rel="stylesheet" type="text/css"/>
	<link href="${request.getContextPath()}/css/chosen/chosen.css" rel="stylesheet" type="text/css"/>
</head>
<style>
.sideMenu .sel{
background-color: #1C8DAE!important;
}
.welcome{
float: left;
width: 500px;
height: 37px;
margin: 15px 0px 0px 25px;
position: relative;
top: 15px;
left: 500px;
font-size: 16px;
font-family:Microsoft YaHei;
color: #34B8DF;
}
</style>
<body>
<div class="top">
	<div class="logo"></div>
	<div class="welcome" style="left: 120px;"><span>${areaName}&nbsp;&nbsp;</span><span>${gUser.name}，</span><span>欢迎您！</span><span>&nbsp;&nbsp;</span><span id="jnkc"></span></div>
	<div class="func" style="width:75px">
		<a href="${request.getContextPath()}/login/index" title="首页" class="block first"></a>
		<a href="${request.getContextPath()}/login/logout" title="退出" id="logout" class="block third"></a>
		<!--<a href="javascript:toMPwdView();" title="修改密码" class="block last" style="background-position:-128px -46px;"></a>-->
		<div class="skinCont">
			<i class="blue"></i>
		</div>
	</div>
	<ul id="menu">
    	<#if menu?exists>
    	<#list menu as menu>
    	<li><a id="fc${menu.code}" fc="${menu.code}">${menu.name}</a></li>
    	</#list>
    	</#if>
    </ul>
</div>
<div class="container">
	<div id="sideBar">
    	<div id="sideCont">
    		<#---->
    		<#if ejMenuMap?exists>
                <#list ejMenuMap?keys as key>
                  	<dl id="${key}" class="sideMenu" style="display:none;">
                  	<#assign item = ejMenuMap[key]>   
				    <#list item as obj>
			        		<dd><a href="${request.getContextPath()}/${obj.menuurl}" >${obj.name}</a></dd>
				    </#list>
                  	
                  	
                  	
		    		  <#--<#list ejMenuMap.get(key) as menu1>
		    		  	<#if menu1?exists>
			    		  	<#if  loginUser.grade != 0>
				        		<dd><a href="${request.getContextPath()}/${menu1.menuurl}" >${menu1.name}</a></dd>
			    		  	</#if>
		    		  	</#if>
		        	  </#list>	-->
		        	  
		        	  
		        	  
	        		</dl>
                </#list>
            </#if>
        </div>
        <i class="sideBtn"></i>
    </div>
    <div id="main">
    </div>
</div>
<div id="btm"><a href="http://www.baidu.com" target="_blank"><span style="font-size: 16px;font-family:Microsoft YaHei;">121212</span></a></div>

<script src="${request.getContextPath()}/js/jquery.min.js"></script>
<script src="${request.getContextPath()}/js/allPage.js" contextPath="${request.getContextPath()}" id="allPageJs"></script>
</body>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<script>
	$(function(){
			//top菜单添加点击事件
			$(".top #menu a").click(function(){
				$('.top #menu a').removeClass("sel");
				$(this).addClass("sel");
				$(".sideMenu").css("display","none");
				var menuNo= "menu"+$(this).attr("fc");
				$("#"+menuNo).css("display","");
				return false;
			});
		
		//添加收缩事件
		$("#sideCont i").click(function(){
			var dd=$(this).parent().siblings();
			JXJY.sideMenuShow($(this),dd)					
		});
		
		//左侧添加菜单点击事件
		$("#sideCont a").click(function(){
			$("#sideCont a").removeClass("sel");
			$(this).addClass("sel");
			var s = createFrame($(this).attr('href'));
			$(main).html(s);
			return false;	
		})
		
		var firstMenu = document.getElementById("menu").getElementsByTagName("a")[0].id;
		
		document.getElementById(firstMenu).click();
	});
	function toMPwdView(){
		jBox("iframe:${request.getContextPath()}/guser/gUser/toMPwdView", {
			title: "修改密码",
		    width: 600,
			id:"mPwdView",
		    height: 300,
			buttons: {}
		});
	}
	function createFrame(url)
	{
		if(url.indexOf('?')==-1){
			url = url+"?";
		}else{
			url = url+"&";
		}
		
		var indexRandom = url.indexOf('random=');
		if(indexRandom !=-1){
			url = url.substring(0,indexRandom);
		}
		url = url+"random="+Math.random();
		var s = '<iframe id="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:'+$("#sideBar").height()+'px;"></iframe>';
		return s;
	}
	function logout(){
		window.location.href="${request.getContextPath()}/login/logout";
	}
	
	setInterval("jnkc.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
</script>
</body>
</html>