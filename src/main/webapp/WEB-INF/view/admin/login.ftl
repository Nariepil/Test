<!doctype html>
<html>
	<head>
		<meta charset="UTF-8" />
		<title>后台管理中心</title>
		<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta name="robots" content="noindex,nofollow">
		<link href="${request.getContextPath()}/css/admin_login.css" rel="stylesheet" />
		<style>
			#login_btn_wraper{
				text-align: center;
			}
			#login_btn_wraper .tips_success{
				color:#fff;
			}
			#login_btn_wraper .tips_error{
				color:#DFC05D;
			}
			#login_btn_wraper button:focus{outline:none;}
		</style>
		<script>
			if (window.parent !== window.self) {
					document.write = '';
					window.parent.location.href = window.self.location.href;
					setTimeout(function () {
							document.body.innerHTML = '';
					}, 0);
			}
		</script>
		
	</head>
<body>
	<div class="wrap">
		<h1><!--<a href="">ThinkCMF 后台管理中心</a>--><span style="color:#fff;font-size:25px;font-weight: 800;">&nbsp;管&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;后&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;台</span></h1>
		<form method="post" name="login" action="" autoComplete="off" class="js-ajax-form">
			<div class="login">
				<ul>
					<li>
						<input class="input" id="js-admin-name" required name="username" type="text" placeholder="用户名或邮箱" title="用户名或邮箱" value=""/>
					</li>
					<li>
						<input class="input" id="admin_pwd" type="password" required name="password" placeholder="密码" title="密码" />
					</li>
					<li class="verifycode-wrapper">
						<img class="verify_img" src="" onclick="getImages()" style="cursor: pointer;width:248px;height:42px" title="点击获取"/>					</li>
					<li>
						<input class="input" type="text" name="verify" placeholder="请输入验证码" />
					</li>
				</ul>
				<div id="login_btn_wraper">
					<button type="submit" name="submit" class="btn js-ajax-submit" data-loadingmsg="正在加载...">登录</button>
				</div>
			</div>
		</form>
	</div>

<script>
var GV = {
	DIMAUB: "",
	JS_ROOT: "/public/js/",//js版本号
	TOKEN : ''	//token ajax全局
};
</script>
<!--<script src="${request.getContextPath()}/js/wind.js"></script>-->
<script src="${request.getContextPath()}/js/jquery.js"></script>
<script src="${request.getContextPath()}/js/jquery.min.js"></script>
<!--<script type="text/javascript" src="${request.getContextPath()}js/common.js"></script>-->
<script>
(function(){
	document.getElementById('js-admin-name').focus();
})();
$(function() {
 		getImages();
 	});
var rndzz = Math.random();//UUID
function getImages(){
		$(".verify_img").attr("src", "${request.getContextPath()}/login/verifyCode/captchaId="+rndzz+"&timestamp=" + (new Date()).valueOf());
	}
</script>
</body>
</html>