<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>EPAY - 运营平台注册</title>
<link rel="icon" type="image/x-icon" href="images/favicon.ico">
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/login.css" />
<!-- add by GaoLiang -->
<script type="text/javascript" src="js/jquery-1.11.1-min.js"></script>
<script language="javascript" type="text/javascript" src="js/jQuery.md5.js"></script>
<script language="javascript" type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript">
	$(function() {

		//用户名文本框自动获得焦点
		$("#loginAct").focus();

		//给下一步按钮绑定鼠标单击事件
		$("#registerBtn").click(registerFun);

		//给整个window对象
		//dom编程
		//window.onkeydown = function(event){}
		//jquery编程
		$(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
			//所有keydown事件对象都有一个keyCode属性，用来获取键值。
			if(event.keyCode == 13){ // 回车键13，ESC键27
				registerFun();
			}
		});

	});

	function registerFun() {

		if(checkLoginAct() && checkAccount() && checkLoginPassword()) {
            var loginAct = $.trim($("#loginAct").val());
            var account = $.trim($("#account").val());
            var loginPwd = $.trim($("#loginPwd").val());
            var reLoginPwd = $.trim($("#reLoginPwd").val());

			$("#loginPwd").val($.md5(loginPwd));
			$("#reLoginPwd").val($.md5(reLoginPwd));

			var md5LoginPwd = $.md5(loginPwd);

			window.location.href = "register2.jsp?loginAct="+loginAct+'&loginPwd='+md5LoginPwd+'&account='+account;
			/*$.ajax({
				type : "post",
				url : "mch/register",
				data:"loginAct="+loginAct+"&loginPwd="+loginPwd+"&orgCode="+orgCode,
				success : function(jsonObject) {
					if(jsonObject.errorMessage == "OK") {
						alert("注册成功！")
						location.href='login.jsp';
					} else {
						$("#showId").html(jsonObject.errorMessage);
					}
				}
			});*/
		}
	}

	function checkLoginAct() {
		var loginAct = $.trim($("#loginAct").val());

		if("" == loginAct) {
			$("#showId").html("请输入商户号");
			return false;
		}  else if(!/^[0-9]*$/.test(loginAct)) {
			$("#showId").html("商户号必须为纯数字");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}

	function checkAccount() {
		var account = $.trim($("#account").val());

		if("" == account) {
			$("#showId").html("请输入登录账号");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}

    // 验证登录密码
    function checkLoginPassword() {
        var loginPwd = $.trim($("#loginPwd").val());
        var reLoginPwd = $.trim($("#reLoginPwd").val());

        // a) 密码不能为空
        if("" == loginPwd) {
            $("#showId").html("请输入登录密码");
            return false;
        } else if(!/^[0-9a-zA-Z]+$/.test(loginPwd)) {
            $("#showId").html("密码字符只可使用数字和大小写英文字母");
            return false;
        } else if(!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(loginPwd)) {
            $("#showId").html("密码应同时包含英文或数字");
            return false;
        } else if(loginPwd.length < 6 || loginPwd.length > 20) {
            $("#showId").html("密码长度应为6-20位");
            return false;
        } else if(loginPwd != reLoginPwd) {
            $("#showId").html("两次密码输入不一致");
			return false;
		} else {
            $("#showId").html("");
        }
        return true;
    }

</script>
</head>
<body class="beg-login-bg">
	<div class="beg-login-box" style="height: 390px; width: 500px;">
		<header>
			<h1 style="text-align: center; font-size: 30px;">
				商户注册(第1步)
			</h1>
		</header>
		<div class="beg-login-main">
			<%--<input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />--%>
			<div class="layui-form-item" style="margin-top: -5px;">
				<span style="color: red; float: left; width: 5%; font-size: 15px;margin-top: 10px;">✲</span>
				<span style="width: 30%; float: left; font-size: 20px; font-family: 宋体;margin-top: 8px;">商户号:</span>
				<input id="loginAct" type="text" name="userName" lay-verify="userName" autocomplete="off" placeholder="请输入商户号" class="layui-input" style="width: 65%; float: left;">
			</div>

			<div class="layui-form-item">
				<span style="color: red; float: left; width: 5%; font-size: 15px;margin-top: 10px;">✲</span>
				<span style="width: 30%; float: left; font-size: 20px; font-family: 宋体;margin-top: 8px;">账&nbsp;&nbsp;号:</span>
				<input id="account" type="text" name="password" lay-verify="password" autocomplete="off" placeholder="请输入登录账号" class="layui-input"  style="width: 65%; float: left;">
			</div>

			<div class="layui-form-item">
				<span style="color: red; float: left; width: 5%; font-size: 15px;margin-top: 10px;">✲</span>
				<span style="width: 30%; float: left; font-size: 20px; font-family: 宋体;margin-top: 8px;">密&nbsp;&nbsp;码:</span>
				<input id="loginPwd" type="password" name="password" lay-verify="password" autocomplete="off" placeholder="请输入密码" class="layui-input"  style="width: 65%; float: left;">
			</div>

			<div class="layui-form-item">
				<span style="color: red; float: left; width: 5%; font-size: 15px;margin-top: 10px;">✲</span>
				<span style="width: 30%; float: left; font-size: 20px; font-family: 宋体;margin-top: 8px;">确认密码:</span>
				<input id="reLoginPwd" type="password" name="reLoginPwd" lay-verify="reLoginPwd" autocomplete="off" placeholder="请再次输入密码" class="layui-input"  style="width: 65%; float: left;">
			</div>

			<%--<div class="layui-form-item">
				<span style="width: 35%; float: left; font-size: 20px;">机构代码:</span>
				<input id="orgCode" type="text" name="orgCode" lay-verify="orgCode" autocomplete="off" placeholder="请输入机构代码" class="layui-input"  style="width: 65%; float: left;">
			</div>--%>

			<div style="height: 15px;">
				<ul><li id="showId" style="margin-left: 20px;color:red;font-size:15px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
			</div>

			<div class="layui-form-item" style="">
				<br/>
				<div class="beg-pull-right">
					<button id="registerBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 300px;">
						下&nbsp;一&nbsp;步
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