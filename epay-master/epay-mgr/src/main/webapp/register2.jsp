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

		//用户名文本框自动获得焦点
		$("#mchName").focus();

		//给注册按钮绑定鼠标单击事件
		$("#mchInfoBindBtn").click(mchInfoBindFun);

		//给整个window对象
		//dom编程
		//window.onkeydown = function(event){}
		//jquery编程
		$(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
			//所有keydown事件对象都有一个keyCode属性，用来获取键值。
			if(event.keyCode == 13){ // 回车键13，ESC键27
				mchInfoBindFun();
			}
		});

		// 获取register1的数据
		addMchId();

		// 获取经营范围
		queryBusinessRange();

		// 查询省份
		queryProvince();

		//查询证件类型
		queryCertificateType();

		//省份改变的时候城市联动
		$("select#province").change(function(){
			//alert($(this).val());
			var id = $(this).val();
			queryCity(id);
		});
	});

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

	// 查询证件类型
	function queryCertificateType() {
		$.get(
			"mch/register/queryCertificateType",
			function(json){
				// typeList : [{"id":"","type" : ""},{},{}]
				var html="<option value='-99' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-请选择证件类型-</option>";
				$.each(json, function(i,n){
					html += "<option value='"+n.id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+n.type+"</option>"
				});
				$("#cerType").html(html);
			}
		);
	}

	// 查询经营范围
	function queryBusinessRange() {
		$.get(
			"mch/register/queryBusinessRange",
			function(json){
				// typeList : [{"id":"","type" : ""},{},{}]
				var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-请选择经营范围-</option>";
				$.each(json, function(i,n){
					html += "<option value='"+n.id+"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+n.businessRange+"</option>"
				});
				$("#range").html(html);
			}
		);
	}

	// 第一个输入框添加数据（商户号）
	function addMchId() {
		var loginAct;
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
			}
		}
		$("#loginAct").val(loginAct);
	}

	// 注册第二步
	function mchInfoBindFun() {
		if(checkMchName() && checkMchType() && checkRange() && checkMchAddress() && checkContactName() && checkPhone() && checkCity() && checkEmail() && checkCerNo()) {
			// $("#loginPwd").val($.md5(loginPwd));
			// $("#reLoginPwd").val($.md5(reLoginPwd));

			var loginAct;
			var loginPwd;
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
						account = (strs[i].split("=")[1]);
					}
				}
				// console.log(theRequest); //此时的theRequest就是我们需要的参数；{loginAct: "111", loginPwd: "111qqq"}
			}
            var mchName = $.trim($("#mchName").val());
					var myselect = document.getElementById("mchType");
					var index=myselect.selectedIndex;
				var mchType = myselect.options[index].value;
					var myselect = document.getElementById("range");
					var index=myselect.selectedIndex;
				var range = $.trim(myselect.options[index].value);
            // var range = $.trim($("#range").val());
            var mchAddress = $.trim($("#mchAddress").val());
			var contactName = $.trim($("#contactName").val());
			var contactPhone = $.trim($("#contactPhone").val());
			var contactEmail = $.trim($("#contactEmail").val());
				var myselect = document.getElementById("cerType");
				var index=myselect.selectedIndex;
				var cerType = myselect.options[index].value;
			var cerNo = $.trim($("#cerNo").val());
			var businessLic = $.trim($("#businessLic").val());
				var myselect = document.getElementById("city");
				var index=myselect.selectedIndex;
			var city = myselect.options[index].text;
			window.location.href = "register3.jsp?loginAct="+loginAct+
													'&loginPwd='+loginPwd+
													'&mchName='+mchName+
													'&mchType='+mchType+
													'&range='+range+
													'&mchAddress='+mchAddress+
													'&contactName='+contactName+
													'&contactPhone='+contactPhone+
													'&contactEmail='+contactEmail+
													'&cerType='+cerType+
													'&cerNo='+cerNo+
													'&businessLic='+businessLic+
													'&city='+city+
													'&account='+account;//14个参数

		}
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
		} else if(!/^[0-9]*$/.test(phone)) {
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
	// 验证邮箱
	function checkEmail() {
		var contactEmail = $.trim($("#contactEmail").val());
		if("" == contactEmail) {
			return true; // 终止并返回结果
		} else if(/^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(\.[0-9A-Za-z]+)+$/.test(contactEmail)) {
			return true;
		}
		$("#showId").html("请输入正确的邮箱地址");
		return false;
	}
	// 验证证件号码(只验证是身份证的时候)
	function checkCerNo() {
		var cerNo = $.trim($("#cerNo").val());
		var myselect = document.getElementById("cerType");
		var index=myselect.selectedIndex;
		var cerType = myselect.options[index].text;
		if("身份证" == cerType) {
			//
			if(!/^(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)$/.test(cerNo)) {
				$("#showId").html("请输入正确的身份证号码");
				return false;
			}
		}
		return true;
	}

</script>
<style>
	.layui-input{
		height: 30px; width: 190px; float: right;
	}
	.xingSpan{
		color: red; font-size: 10px;
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
	<%--<c:forEach items="${businessRangeMap}" var="m">
		<ul title="${m.key}">
			<c:forEach items="${m.value}" var="mm">
				<li>${mm}</li>
			</c:forEach>
		</ul>
	</c:forEach>--%>
	<div class="beg-login-box" style="height: 590px; width: 500px; margin-top: 70px;">
		<header style="height: 30px;">
			<h1 style="text-align: center; font-size: 20px;">
				商户注册(第2步)
			</h1>
			<%--<a href="login.jsp.jsp" style="float: right;margin-top: 4%; color: white;">立即登录->&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
		</header>
		<div class="beg-login-main">

			<div class="layui-form-item" style="margin-top: -15px;">
				<span class="nameSpan"><span class="xingSpan">✲</span>商户号:</span>
				<input id="loginAct" type="text" name="loginAct" lay-verify="userName" autocomplete="off" disabled class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>商户名称:</span>
				<input id="mchName" type="text" name="mchName" lay-verify="mchName" autocomplete="off" placeholder="*请输入商户名称" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>商户类型:</span>
				<select id="mchType" name="mchType" style="float: right;width: 190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">
					<option value="" disabled selected hidden>
						<span style="color: #d7d9dd;height: 30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--请选择商户类型--</span>
					</option>
					<option value="1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人商户</option>
					<option value="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;企业商户</option>
					<option value="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;小微商户</option>
				</select>
			</div>

			<%--<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>经营范围:</span>
				<input id="range" type="text" name="range" lay-verify="range" autocomplete="off" placeholder="*请输入经营范围" class="layui-input">
			</div>--%>
			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>经营范围:</span>
				<select id="range" name="range" style="float: right; width:190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">

				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>商户地址:</span>
				<input id="mchAddress" type="text" name="mchAddress" lay-verify="mchAddress" autocomplete="off" placeholder="*请输入商户地址" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>联系人姓名:</span>
				<input id="contactName" type="text" name="contactName" lay-verify="contactName" autocomplete="off" placeholder="*请输入联系人姓名" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>联系人电话:</span>
				<input id="contactPhone" type="text" name="contactPhone" lay-verify="contactPhone" autocomplete="off" placeholder="*请输入联系人电话" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan"><span class="xingSpan">✲</span>所属市区:</span>
				<select id="province" name="province" style="margin-left:16px;float: left;width: 95px; height: 30px; border-radius: 3px; text-align: center; text-align-last: center;">
					<%--省份--%>
				</select>
				<select id="city" name="city" style="float: left;width: 95px; height: 30px; border-radius: 3px; text-align: center; text-align-last: center;">
					<%--城市--%>
				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">联系人邮箱:</span>
				<input id="contactEmail" type="text" name="contactEmail" lay-verify="contactEmail" autocomplete="off" placeholder="-请输入联系人邮箱" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">证件类型:</span>
				<select id="cerType" name="cerType" style="float: right; width:190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">

				</select>
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">证件号码:</span>
				<input id="cerNo" type="text" name="cerNo" lay-verify="cerNo" autocomplete="off" placeholder="-请输入联系人证件号" class="layui-input">
			</div>

			<div class="layui-form-item">
				<span class="nameSpan">营业执照:</span>
				<input id="businessLic" type="text" name="businessLic" lay-verify="businessLic" autocomplete="off" placeholder="-请输入营业执照" class="layui-input">
			</div>

			<div style="height: 15px;">
				<ul><li id="showId" style="margin-left:40px;color:white;font-size:15px;width:222px;margin-top:10px;margin-bottom:10px;line-height:18px;"></li></ul>
			</div>

			<div class="layui-form-item" style="">

				<div class="beg-pull-right">
					<button id="mchInfoBindBtn" class="layui-btn layui-btn-primary" lay-submit lay-filter="login" style="width: 280px;height: 35px;">
						下&nbsp;一&nbsp;步
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