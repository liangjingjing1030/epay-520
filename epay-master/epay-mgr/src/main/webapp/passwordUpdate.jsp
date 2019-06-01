<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>找回密码</title>
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

		// 获取经营范围
		queryBusinessRange();

		//给找回密码按钮绑定鼠标单击事件
		$("#passwordUpdateBtn").click(passwordUpdateBtn);

		//给整个window对象
		//dom编程
		//window.onkeydown = function(event){}
		//jquery编程
		$(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
			//所有keydown事件对象都有一个keyCode属性，用来获取键值。
			if(event.keyCode == 13){ // 回车键13，ESC键27
				passwordUpdateBtn();
			}
		});

		// 查询省份
		queryProvince();

		//省份改变的时候城市联动
		$("select#province").change(function(){
			//alert($(this).val());
			var id = $(this).val();
			queryCity(id);
		});
	});

	// 查询经营范围
	function queryBusinessRange() {
		$.get(
				"mch/register/queryBusinessRange",
				function(json){
					// typeList : [{"id":"","type" : ""},{},{}]
					var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-请选择经营范围-</option>";
					$.each(json, function(i,n){
						html += "<option value='"+n.id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+n.businessRange+"</option>"
					});
					$("#range").html(html);
				}
		);
	}

	function queryCity(id) {
		// alert(id);
		$.ajax({
			type: "get",
			url: "mch/register/queryCityByParentId",
			// dataType: "json",
			data:"id="+id,
			success: function (json) {
				// cityList : [{"id":"","name" : ""},{},{}]
				var html="<option value='' disabled selected hidden style='height: 30px;'>-请选择-</option>";
				$.each(json, function(i,n){
					html += "<option value='"+n.id+"'>"+n.name+"</option>"
				});
				$("#city").html(html);
			}
		});
	}

	function queryProvince() {
		$.get(
			"mch/register/queryProvince",
			function(json){
				// provinceList : [{"id":"","name" : ""},{},{}]
				var html="<option value='' disabled selected hidden style='height: 30px;'>-请选择-</option>";
				$.each(json, function(i,n){
					html += "<option value='"+n.id+"'>"+n.name+"</option>"
				});
				$("#province").html(html);
			}
		);
	}

	function passwordUpdateBtn() {

		if(checkMchNo() && checkMchName() && checkRange() && checkMchType() && checkCity() && checkMchAddress() && checkContactName() && checkPhone() && checkLoginPassword() && checkRegisterCaptcha()) {

            var loginAct = $.trim($("#loginAct").val());
            var mchName = $.trim($("#mchName").val());
            // var range = $.trim($("#range").val());
					var myselect = document.getElementById("range");
					var index=myselect.selectedIndex;
				var range = $.trim(myselect.options[index].text);

					var myselect = document.getElementById("mchType");
					var index=myselect.selectedIndex;
				var mchType = $.trim(myselect.options[index].text);

					var myselect = document.getElementById("city");
					var index=myselect.selectedIndex;
				var city = myselect.options[index].text;

            var mchAddress = $.trim($("#mchAddress").val());
			var contactName = $.trim($("#contactName").val());
			var contactPhone = $.trim($("#contactPhone").val());
			var loginPwd = $.trim($("#loginPwd").val());
			var reLoginPwd = $.trim($("#reLoginPwd").val());

			$("#loginPwd").val($.md5(loginPwd));
			$("#reLoginPwd").val($.md5(reLoginPwd));
			var md5LoginPwd = $.md5(loginPwd);

			$.ajax({
				type : "post",
				url : "mch/passwordUpdate",
				data: {
					"loginAct": loginAct,
					"mchName": mchName,
					"range": range,
					"mchType": mchType,
					"city": city,
					"mchAddress": mchAddress,
					"contactName": contactName,
					"contactPhone": contactPhone,
					"loginPwd": md5LoginPwd
				},
				success : function(jsonObject) {
					if(jsonObject.OK == "SUCCESS") {
						alert(jsonObject.errorMessage);
						location.href='login.jsp';
					} else {
						alert(jsonObject.errorMessage);
						// 刷新验证码
						$("#imgCode").click();
					}
				}
			});

		}
	}

	//商户号
	function checkMchNo() {
		var loginAct = $.trim($("#loginAct").val());

		if("" == loginAct) {
			$("#showId").html("请输入商户号");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	//商户名称
	function checkMchName() {
		var mchName = $.trim($("#mchName").val());

		if("" == mchName) {
			$("#showId").html("请输入商户名称");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	// 商户类型
	function checkMchType() {
		var myselect = document.getElementById("mchType");
		var index=myselect.selectedIndex;
		var mchType = myselect.options[index].value;
		// alert(mchType);

		if("" == mchType) {
			$("#showId").html("请选择商户类型");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	// 经营范围
	function checkRange() {
		var myselect = document.getElementById("range");
		var index=myselect.selectedIndex;
		var range = myselect.options[index].value;
		// alert(mchType);

		if("" == range) {
			$("#showId").html("请选择经营范围");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	//商户地址
	function checkMchAddress() {
		var mchAddress = $.trim($("#mchAddress").val());

		if("" == mchAddress) {
			$("#showId").html("请输入商户地址");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	//联系人姓名
	function checkContactName() {
		var contactName = $.trim($("#contactName").val());

		if("" == contactName) {
			$("#showId").html("请输入联系人姓名");
			return false;
		} else {
			$("#showId").html("");
		}
		return true;
	}
	// 验证手机号
	function checkPhone() {
		var phone = $.trim($("#contactPhone").val());
		// a) 手机号不能为空
		if("" == phone) {
			$("#showId").html("请输入手机号码");
			return false; // 终止并返回结果
		} else if(!/^1[1-9]\d{9}$/.test(phone)) {
			// b) 手机号格式
			$("#showId").html("请输入正确的手机号码");
			return false;
		}
		return true;
	}
	// 验证城市
	function checkCity() {
		var city = $.trim($("#city").val());

		if("" == city) {
			$("#showId").html("请输入所在城市");
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
			$("#showId").html("请输入新密码");
			return false;
		} else if(!/^[0-9a-zA-Z]+$/.test(loginPwd)) {
			$("#showId").html("密码字符只可使用数字和大小写英文字母");
			return false;
		} else if(!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]/.test(loginPwd)) {
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
	// 验证验证码
	function checkRegisterCaptcha() {
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
	.layui-input{
		height: 30px; width: 190px; float: right;
	}
	.layui-form-item{
		margin:7px; border:0; padding:0;
	}
	.nameSpan{
		width: 100px; float: left; font-size: 15px;font-family: 宋体;text-align: right;margin-top: 7px;
	}
</style>
</head>
<body class="beg-login-bg">
	<div class="beg-login-box" style="height: 560px; width: 500px; margin-top: 70px;">
		<header style="height: 30px;">
			<h1 style="text-align: center; font-size: 20px;">
				找 回 密 码
			</h1>
		</header>
		<div class="beg-login-main">

			<div class="layui-form-item" style="margin-top: -15px;">
				<span class="nameSpan">商户号:</span>
				<input id="loginAct" type="text" placeholder="请输入商户号" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">商户名称:</span>
				<input id="mchName" type="text" placeholder="请输入商户名称" class="layui-input">
			</div>

			<%--<div class="layui-form-item">
				<span class="nameSpan">经营范围:</span>
				<input id="range" type="text" placeholder="请输入经营范围" class="layui-input">
			</div>--%>
			<div class="layui-form-item">
				<span class="nameSpan">经营范围:</span>
				<select id="range" name="range" style="float: right; width:190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">

				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">商户类型:</span>
				<select id="mchType" name="mchType" style="float: right;width: 190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">
					<option value="" disabled selected hidden>
						<span style="color: #d7d9dd;height: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-请选择商户类型-</span>
					</option>
					<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人商户</option>
					<option value="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业商户</option>
					<option value="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小微商户</option>
				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">所属市区:</span>
				<select id="province" name="province" style="margin-left:16px;float: left;width: 95px; height: 30px; border-radius: 3px; text-align: center; text-align-last: center;">
					<%--省份--%>
				</select>
				<select id="city" name="city" style="float: left;width: 95px; height: 30px; border-radius: 3px; text-align: center; text-align-last: center;">
					<%--城市--%>
				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">商户地址:</span>
				<input id="mchAddress" type="text" name="mchAddress" lay-verify="mchAddress" autocomplete="off" placeholder="请输入商户地址" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">联系人姓名:</span>
				<input id="contactName" type="text" name="contactName" lay-verify="contactName" autocomplete="off" placeholder="请输入联系人姓名" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">联系人电话:</span>
				<input id="contactPhone" type="text" name="contactPhone" lay-verify="contactPhone" autocomplete="off" placeholder="请输入联系人电话" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">新密码:</span>
				<input id="loginPwd" type="password" name="password" lay-verify="password" autocomplete="off" placeholder="请输入新密码" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">确认密码:</span>
				<input id="reLoginPwd" type="password" name="reLoginPwd" lay-verify="reLoginPwd" autocomplete="off" placeholder="请再次输入新密码" class="layui-input">
			</div>

			<div class="layui-form-item">
				<input id="captcha" onblur="checkRegisterCaptcha()" type="text" name="captcha" lay-verify="captcha" autocomplete="off" placeholder="点击刷新" class="layui-input yzm" value="" style="margin-left: 116px;width: 33%;float: left;">
				<img src="mch/captcha" id="imgCode" onclick="this.src='mch/captcha?d='+new Date().getTime()" style="cursor:pointer;border:0; display:inline;vertical-align:middle;float: right;width: 28%;border-radius: 2px;"/>
			</div>

			<div style="height: 15px;">
				<ul><li id="showId" style="margin-left:40px;color:red;font-size:15px;width:222px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
			</div>

			<div class="layui-form-item" style="">

				<div class="beg-pull-right">
					<button id="passwordUpdateBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 280px;height: 35px;">
						找 回 密 码
					</button>
				</div>
				<div class="beg-clear"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
	<script>
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form();
				
			/ form.on('submit(login)',function(data){
				
				location.href='index.jsp';
				return false;
			}); /
		});
	</script>
</body>

</html>