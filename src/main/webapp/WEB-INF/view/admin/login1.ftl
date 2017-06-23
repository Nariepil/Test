<html xmlns="http://www.w3.org/1999/xhtml"><head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>后台管理中心</title>
	<link href="${request.getContextPath()}/css/jbox/jbox_blue.css" rel="stylesheet" type="text/css" id="jbox"/>
	<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css">
	<link href="${request.getContextPath()}/css/login.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${request.getContextPath()}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/uuid/Math.uuid.js"></script>
	<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2.js"></script>
	<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
	<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
	<style type="text/css">
		.Validform_checktip{margin-left:0;}
		.info{
			border:1px solid #ccc; 
			padding:2px 20px 2px 5px; 
			color:#666; 
			position:absolute;
			display:none;
			line-height:20px;
			background-color:#fff;
			margin:-10px 0 0 0;
		}
		.dec {
		    bottom: -8px;
		    display: block;
		    height: 8px;
		    left: 10px;
		    overflow: hidden;
		    position: absolute;
		    width: 17px;
		    margin-top:20px;
		}
		.dec s {
		    font-family: simsun;
		    font-size: 16px;
		    height: 19px;
		    left: 0;
		    line-height: 21px;
		    position: absolute;
		    text-decoration: none;
		    top: -9px;
		    width: 17px;
		}
		.dec .dec1 {
		    color: #CCCCCC;
		}
		.dec .dec2 {
		    color: #FFFFFF;
		    top: -10px;
		    left:0;
		}
	</style>
</head>

<body>
	<div class="boxlogin">
 		<h1 style="font-size:46px"><b>管理系统</b></h1>
 		<div class="txt">
 			
	 		<form id="fm1" class="fm-v clearfix" action="${request.getContextPath()}/login/login" method="post">
	 			<div class="loginError"><span id="msg" class="errors" style="display: none;"></span></div>
	 			<ul>
					<li>
				    	<label>用户名:</label>
				    	<input type="text" class="wh1" name="username" id="username" datatype="*" nullmsg="用户名不能为空！">
				    	<div class="info">
			    			<span class="Validform_checktip">用户名不能为空！</span>
		    				<span class="dec">
		    					<s class="dec1">◆</s>
	    						<s class="dec2">◆</s>
							</span>
						</div>
				    </li>
				
					<li>
				    	<label>密　码:</label>
				    	<input type="password" class="wh1" name="password" id="password" datatype="*" nullmsg="密码不能为空！">
				    	<div class="info">
			    			<span class="Validform_checktip">密码不能为空！</span>
		    				<span class="dec">
		    					<s class="dec1">◆</s>
	    						<s class="dec2">◆</s>
							</span>
						</div>
				    </li>
				    
				    <li class="yz">
			        	<label>验证码:</label>
			        	<span><input type="text" class="wh2" name="verifyCode" id="verifyCode" datatype="*" nullmsg="验证码不能为空" maxlength="4" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
			        		<div class="info">
				    			<span class="Validform_checktip"></span>
			    				<span class="dec">
			    					<s class="dec1">◆</s>
		    						<s class="dec2">◆</s>
								</span>
							</div>
			        		<font> <img id="captchaImage" title="点击更换验证码" src="" title="看不清，换一张" alt="看不清，换一张" onclick="getImages()"/> </font>
		        			<a href="#" onclick="getImages();">看不清</a>
			        	</span></li>
			        	
			        <li><input class="btn" id="submit" name="submit" accesskey="l" value="登录" tabindex="4" type="submit"></li>
			        <!--<li><label class="autoLogin"><input type="checkbox" name="rememberMe" id="rememberMe" value="true"><span>下次自动登录</span></label></li>-->
				</ul>
	 		</form>
	 	</div>
 	</div>

<script>

	var rndzz = Math.uuid();//UUID
	
	//获取验证码
	function getImages(){
		$("#captchaImage").attr("src", "${request.getContextPath()}/login/verifyCode/captchaId="+rndzz+"&timestamp=" + (new Date()).valueOf());
	}
	
	
 	$(function() {
 		getImages();
 		
 		$("#fm1").Validform({
 			btnSubmit:"#submit",
 			ajaxPost:true,
			tiptype:function(msg,o,cssctl){
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip=o.obj.parents("li").find(".Validform_checktip");
					cssctl(objtip,o.type);
					objtip.text(msg);
					
					var infoObj=o.obj.parents("li").find(".info");
					if(o.type==2){
						infoObj.fadeOut(200);
					}else{
						if(infoObj.is(":visible")){return;}
						var left=o.obj.offset().left,
							top=o.obj.offset().top;
		
						infoObj.css({
							left:left+170,
							top:top-45
						}).show().animate({
							top:top-35	
						},200);
					}
					
				}	
			},
			callback:function(data){
				if(data.flag == 'false'){
					$('#msg').html(data.message);
					$('#msg').show();
				}else{
					window.location.href="${request.getContextPath()}/login/index";
				}
			}
		});
	})
</script>
</body>
</html>