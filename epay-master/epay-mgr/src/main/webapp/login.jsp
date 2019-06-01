<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>EPAY - 运营平台登录</title>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/login.css" />
<!-- add by GaoLiang -->
<script language="javascript" type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jQuery.md5.js"></script>
<script type="text/javascript">
	$(function() {

		//用户名文本框自动获得焦点
		$("#loginAct").focus();

		//给登录按钮绑定鼠标单击事件
		$("#loginBtn").click(login);

		//给整个window对象
		//dom编程
		//window.onkeydown = function(event){}
		//jquery编程
		$(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
			//所有keydown事件对象都有一个keyCode属性，用来获取键值。
			if(event.keyCode == 13){ // 回车键13，ESC键27
				login();
			}
		});

	});

	// 登录方法
	function login() {
		var checkFlag = "0";
		if($("#tenDaysAutoLogin").prop("checked")) {
			checkFlag = "1";
		}

		if(checkLoginAct() && checkLoginPassword() && checkCaptcha()) {
			if(checkStatus()) {

				var loginAct = $.trim($("#loginAct").val());
				var loginPwd = $.trim($("#loginPwd").val());

				$("#loginPwd").val($.md5(loginPwd));
				var md5LoginPwd = $.md5(loginPwd);

				$.ajax({
					type : "post",
					url : "public/login",
					data:"loginAct="+loginAct+"&loginPwd="+md5LoginPwd+"&checkFlag="+checkFlag,
					success : function(jsonObject) {
						if(jsonObject.errorMessage == "OK") {
							//登录成功跳转页面
							location.href='index.jsp';
						} else {
							$("#showId").html(jsonObject.errorMessage);
							// 刷新验证码
							$("#imgCode").click();
						}
					}
				});
			}
		}
	}

	// 检查账户状态
	function checkStatus() {
		var loginAct = $.trim($("#loginAct").val());
		var loginPwd = $.trim($("#loginPwd").val());
		var md5LoginPwd = $.md5(loginPwd);
		var flag = false;
		$.ajax({
			type: "get",
			url: "mch/checkStatus",
			data:"loginAct="+loginAct+"&loginPwd="+md5LoginPwd,
			//请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器
			async:false,
			success: function (jsonObject) {
				if(jsonObject.errorMessage == "OK") {
					flag = true;
				} else {
					$("#showId").html(jsonObject.errorMessage);
					// 刷新验证码
					$("#imgCode").click();
				}
			}
		});
		return flag;
	}
	// 验证账户名格式
	function checkLoginAct() {
		var loginAct = $.trim($("#loginAct").val());

		if("" == loginAct) {
			$("#showId").html("请输入登录名");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	// 验证密码格式
	function checkLoginPassword() {
		var loginPwd = $.trim($("#loginPwd").val());

		if("" == loginPwd) {
			$("#showId").html("请输入登录密码");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	// 验证验证码
	function checkCaptcha() {
		var captcha = $.trim($("#captcha").val());
		var flag = true;

		// a) 图形验证码不能为空
		if("" == captcha) {
			$("#showId").html("请输入图形验证码");
			return false;
		} else {
			$.ajax({
				type:"post",
				url:"mch/checkCaptcha",
				data:"captcha="+captcha,

				async:false,

				success:function (jsonObject) {
					if(jsonObject.errorMessage == "OK") {
						$("#showId").html("");
						flag = true;
					} else {
						$("#showId").html(jsonObject.errorMessage);
						flag = false;
					}
				},
				error:function () {
					$("#showId").html("系统繁忙，请稍后再试...");
					flag = false;
				}
			});
		}

		if(!flag) {
			return false;
		}

		return true;
	}

</script>
	<style>
		html,body{
			/*width:100%;*/
			height:100%
		}
		body{
			background: url(images/login3.jpg) no-repeat top left;
			background-size: cover;
		}
		/*设置渐变色*/
		.beg-login-box{
			height: 360px; margin-right: 200px; opacity: 0.7;
			background: -webkit-linear-gradient(
				top, #135ca1,#0e4d92,#1969ae,#247db9,#64abd7
			);
		}
	</style>
</head>
<body class="beg-login-bg">

	<div class='box'></div>

	<div class="beg-login-box" style="">
		<header>
			<h1 style="float: left; font-size: 30px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;E掌柜登陆
			</h1>
			<%--<button id="quickRegBtn" onclick="location.href='register1.jsp'" style="float: right;">没有账号？</button>--%>
			<a href="register1.jsp" style="float: right;margin-top: 4%; color: white;">立即注册->&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</header>
		<div class="beg-login-main">
			<%--<input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />--%>
			<div class="layui-form-item" style="margin-top: -5px;">
				<label class="beg-login-icon">
				   <i class="layui-icon">&#xe612;</i>
			   </label>
				<input id="loginAct" type="text" name="userName" lay-verify="userName" autocomplete="off" placeholder="请输入登录账号" class="layui-input" value="">
			</div>

			<div class="layui-form-item">
				<label class="beg-login-icon">
				   <i class="layui-icon">&#xe642;</i>
			   </label>
				<input id="loginPwd" type="password" name="password" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input" value="">
			</div>

			<div class="layui-form-item">
				<label class="beg-login-icon">
					<i class="layui-icon">&#xe60c;</i>
				</label>
				<input id="captcha" onblur="checkCaptcha()" type="text" name="captcha" lay-verify="captcha" autocomplete="off" placeholder="点击图片刷新" class="layui-input yzm" value="" style="width: 55%;float: left;">
				<img src="mch/captcha" id="imgCode" onclick="this.src='mch/captcha?d='+new Date().getTime()" style="cursor:pointer;border:0; display:inline;vertical-align:middle;float: right;width: 40%;"/>
				<br/><br/>
			</div>

			<div style="height: 15px;">
				<ul><li id="showId" style="color:red;font-size:15px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
			</div>

			<div class="layui-form-item" style="">
				<div class="beg-pull-left beg-login-remember">
					<label>记住帐号</label>
					<input id="tenDaysAutoLogin" type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
					<a href="passwordUpdate.jsp" style="color: white; float: right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						忘记密码？
					</a>
				</div>
				<br/>
				<div class="beg-pull-right">
					<button id="loginBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 270px;">
					   登&nbsp;&nbsp;&nbsp;&nbsp;录
				   </button>
				</div>
				<div class="beg-clear"></div>
			</div>
			<%--<span id="errorMsg" style="color: red;font-size: 16px;"></span>--%>
		</div>
	</div>
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
	<script>
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form();
				
			/* form.on('submit(login)',function(data){
				
				location.href='index.jsp';
				return false;
			}); */
		});
	</script>
</body>

</html>