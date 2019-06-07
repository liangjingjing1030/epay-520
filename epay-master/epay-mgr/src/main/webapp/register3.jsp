<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>EPAY - 注册（绑定商户信息）</title>
	<link rel="icon" type="image/x-icon" href="images/favicon.ico">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/login.css" />
	<!-- add by GaoLiang -->
	<script language="javascript" type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jQuery.md5.js"></script>
	<script type="text/javascript">
		$(function() {

			//结算账户文本框自动获得焦点
			$("#accountNo").focus();

			//给注册按钮绑定鼠标单击事件
			$("#registerBtn").click(register);

			//给整个window对象
			//dom编程
			//window.onkeydown = function(event){}
			//jquery编程
			$(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
				//所有keydown事件对象都有一个keyCode属性，用来获取键值。
				if(event.keyCode == 13){ // 回车键13，ESC键27
					register();
				}
			});
			
			// 查询机构号按钮
			$("#queryBranchIdBtn").click(function () {
				var branchName = $.trim($("#branchName").val());
				if("" == branchName) {
					alert("请输入网点名称查询");
				} else {
					$.ajax({
						type : "post",
						url : "mch/queryBranchIdByBranchName",
						data: {
							"branchName": branchName
						},
						success : function(jsonObject) {
							if(jsonObject.errorMessage == "OK") {
								$("#branchId").val(jsonObject.branchId);
							} else {
								$("#branchId").val("");
								alert(jsonObject.errorMessage);
							}
						}
					});
				}
			});

		});//---------------------------------------------------------------------------------------------

		// 注册
		function register() {
			if(checkAccountNo() && checkAccountName() && checkOpenBank() && checkAccountType() && checkDellDay() && checkBranchId() && checkRegisterCaptcha()) {
				var loginAct;
				var loginPwd;
				var mchName;
				var mchType;
				var range;
				var mchAddress;
				var contactName;
				var contactPhone;
				var contactEmail;
				var cerType;
				var cerNo;
				var businessLic;
				var city;
				var account;
				//那么我们在新页面接收参数， 并且将参数转为可用的json格式时， 可以用下面的方法：
				var url = location.search; //获取url中"?"符后的字串 ('?modFlag=business&role=1')
				var theRequest = new Object();
				if ( url.indexOf( "?" ) != -1 ) {
					var str = url.substr(1); //substr()方法返回从参数值开始到结束的字符串；loginAct=loginAct&loginPwd=loginPwd
					var strs = str.split("&");//{loginAct=loginAct,loginPwd=loginPwd}
					for (var i = 0; i < strs.length; i++) {
						// theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
						if(i == 0) {
							loginAct = (strs[i].split("=")[1]);
						}
						if(i == 1) {
							loginPwd = (strs[i].split("=")[1]);
						}
						if(i == 2) {
							mchName = (strs[i].split("=")[1]);
						}
						if(i == 3) {
							mchType = (strs[i].split("=")[1]);
						}
						if(i == 4) {
							range = (strs[i].split("=")[1]);
						}
						if(i == 5) {
							mchAddress = (strs[i].split("=")[1]);
						}
						if(i == 6) {
							contactName = (strs[i].split("=")[1]);
						}
						if(i == 7) {
							contactPhone = (strs[i].split("=")[1]);
						}
						if(i == 8) {
							contactEmail = (strs[i].split("=")[1]);
						}
						if(i == 9) {
							cerType = (strs[i].split("=")[1]);
						}
						if(i == 10) {
							cerNo = (strs[i].split("=")[1]);
						}
						if(i == 11) {
							businessLic = (strs[i].split("=")[1]);
						}
						if(i == 12) {
							city = (strs[i].split("=")[1]);
						}
						if(i == 13) {
							account = (strs[i].split("=")[1]);
						}
					}
					// console.log(theRequest); //此时的theRequest就是我们需要的参数；{loginAct: "111", loginPwd: "111qqq"}
				}
				var accountNo = $.trim($("#accountNo").val());
				var accountName = $.trim($("#accountName").val());
				var openBank = $.trim($("#openBank").val());
				var myselect = document.getElementById("accountType");
				var index=myselect.selectedIndex;
				var accountType = myselect.options[index].value;// 1:对私；2：对公
				var dellDay = $.trim($("#dellDay").val());
				var branchId = $.trim($("#branchId").val());
				var branchName = $.trim($("#branchName").val());
				var staffNo = $.trim($("#staffNo").val());
				var staffName = $.trim($("#staffName").val());
				$.ajax({
					type : "post",
					url : "mch/register",
					data: {
						"loginAct": loginAct,
						"loginPwd": loginPwd,
						"mchName": mchName,
						"mchType": mchType,
						"range": range,
						"mchAddress": mchAddress,
						"contactName": contactName,
						"contactPhone": contactPhone,
						"contactEmail": contactEmail,
						"cerType": cerType,
						"cerNo": cerNo,
						"businessLic": businessLic,
						"city": city,
						"accountNo": accountNo,
						"accountName": accountName,
						"openBank": openBank,
						"accountType": accountType,
						"dellDay": dellDay,
						"branchId": branchId,
						"branchName": branchName,
						"staffNo": staffNo,
						"staffName": staffName,
						"account": account
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
		//验证结算账户
		function checkAccountNo() {
			var accountNo = $.trim($("#accountNo").val());
			if("" == accountNo) {
				$("#showId").html("请输入结算账户");
				return false;
			} else if(!/^[0-9]*$/.test(accountNo)) {
				$("#showId").html("请输入正确的结算账号");
				return false;
			}
			return true;
		}
		// 验证结算账户名
		function checkAccountName() {
			var accountName = $.trim($("#accountName").val());
			//
			if("" == accountName) {
				$("#showId").html("请输入结算账户名");
				return false;
			} /*else if(!/^[\u4E00-\u9FA5A-Za-z]+$/.test(accountName)) {
			$("#showId").html("账户名不能包含数字和特殊字符");
			return false;
		}*/
			return true;
		}// 验证开户行
		function checkOpenBank() {
			var openBank = $.trim($("#openBank").val());

			if("" == openBank) {
				$("#showId").html("请输入开户行");
				return false;
			} /*else if(!/^[\u4E00-\u9FA5]+$/.test(openBank)) {
			$("#showId").html("请输入正确的开户行,如中国工商银行");
			return false;
		}*/
			return true;
		}
		//验证账户类型
		function checkAccountType() {
			var myselect = document.getElementById("accountType");
			var index=myselect.selectedIndex;
			var accountType = myselect.options[index].value;

			if("" == accountType) {
				$("#showId").html("请选择账户类型");
				return false;
			} else {
				$("#showId").html("");
			}
			return true;
		}
		//验证交易结算日
		function checkDellDay() {
			var dellDay = $.trim($("#dellDay").val());

			if("" == dellDay) {
				$("#showId").html("请输入交易结算日");
				return false;
			} else if(!/^[0-9]*$/.test(dellDay)) {
				$("#showId").html("交易结算日输入错误");
				return false;
			}
			return true;
		}
		//验证拓展网点号
		function checkBranchId() {
			var branchId = $.trim($("#branchId").val());

			if("" == branchId) {
				$("#showId").html("请输入拓展网点号");
				return false;
			} else if(!/^[0-9]*$/.test(branchId)) {
				$("#showId").html("拓展网点号由纯数字组成");
				return false;
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
		.xingSpan{
			color: red; font-size: 10px;
		}
	</style>
</head>
<body class="beg-login-bg">
<div class="beg-login-box" style="height: 520px; width: 500px; margin-top: 80px;">
	<header style="height: 30px;">
		<h1 style="text-align: center; font-size: 20px;">
			商户注册(第3步)
		</h1>
		<%--<a href="login.jsp.jsp" style="float: right;margin-top: 4%; color: white;">立即登录->&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
	</header>
	<div class="beg-login-main">

		<div class="layui-form-item" style="margin-top: -15px;">
			<span class="nameSpan"><span class="xingSpan">✲</span>结算账号:</span>
			<input id="accountNo" type="text" name="accountNo" lay-verify="accountNo" autocomplete="off" placeholder="*请输入结算银行账号" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan"><span class="xingSpan">✲</span>结算账户名:</span>
			<input id="accountName" type="text" name="accountName" lay-verify="accountName" autocomplete="off" placeholder="*请输入结算账户名" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan"><span class="xingSpan">✲</span>开户行:</span>
			<input id="openBank" type="text" name="openBank" lay-verify="openBank" autocomplete="off" placeholder="*请输入开户行" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan"><span class="xingSpan">✲</span>账户类型:</span>
			<select id="accountType" name="accountType" style="float: right;width: 190px; height: 30px; border-radius: 3px;/* text-align: center; text-align-last: center;*/">
				<option value="" disabled selected hidden>
					<span style="color: #d7d9dd;height: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--请选择账户类型--</span>
				</option>
				<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对私</option>
				<option value="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对公</option>
			</select>
		</div>

		<div class="layui-form-item">
			<span class="nameSpan"><span class="xingSpan">✲</span>交易结算日:</span>
			<input id="dellDay" type="text" name="dellDay" lay-verify="dellDay" autocomplete="off" placeholder="*1为次日结算,2为……" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan">拓展网点名:</span>
			<input id="branchName" type="text" name="branchName" lay-verify="branchName" autocomplete="off" placeholder="-请输入拓展网点名称" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan"><span class="xingSpan">✲</span>拓展网点号:</span>
			<input id="branchId" type="text" name="branchId" lay-verify="branchId" autocomplete="off" placeholder="自动填写" class="layui-input" style="width: 120px;float: left;margin-left: 16px;">
			<%--<input type="button" value="查询" style="float: right;width: 68px;height: 30px;border-radius: 3px;"/>--%>
			<button id="queryBranchIdBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 68px;height: 30px;float: right;">
				查&nbsp;询
			</button>
		</div>

		<div class="layui-form-item">
			<span class="nameSpan">拓展员工号:</span>
			<input id="staffNo" type="text" name="staffNo" lay-verify="staffNo" autocomplete="off" placeholder="-请输入拓展员工号" class="layui-input">
		</div>

		<div class="layui-form-item">
			<span class="nameSpan">拓展员工名:</span>
			<input id="staffName" type="text" name="staffName" lay-verify="staffName" autocomplete="off" placeholder="-请输入拓展员工名称" class="layui-input">
		</div>

		<div class="layui-form-item">
			<input id="captcha" onblur="checkRegisterCaptcha()" type="text" name="captcha" lay-verify="captcha" autocomplete="off" placeholder="点击刷新" class="layui-input yzm" value="" style="margin-left: 116px;width: 33%;float: left;">
			<img src="mch/captcha" id="imgCode" onclick="this.src='mch/captcha?d='+new Date().getTime()" style="cursor:pointer;border:0; display:inline;vertical-align:middle;float: right;width: 28%;"/>
		</div>

		<div style="height: 15px;">
			<ul><li id="showId" style="margin-left:40px;color:white;font-size:15px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
		</div>

		<div class="layui-form-item" style="">

			<div class="beg-pull-right">
				<button id="registerBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 280px;height: 35px;">
					注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册
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

		/* form.on('submit(login)',function(data){

            location.href='index.jsp';
            return false;
        }); */
	});
</script>
</body>

</html>