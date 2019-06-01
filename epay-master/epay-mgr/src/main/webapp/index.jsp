<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--fmt为jstl的格式化标签--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--fn为jstl中的功能函数--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta charset="utf-8">
		<title>商户管理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="icon" type="image/x-icon" href="images/favicon.ico">
		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<!-- add by GaoLiang -->
	<script language="javascript" type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/jQuery.md5.js"></script>
	<script type="text/javascript">
		$(function() {

			//给登录按钮绑定鼠标单击事件
			// $("#zhuxiao").click(zhuxiao);

		});

		// 点击注销跳转到登录页面，同时去掉页面缓存，不允许点击返回
		function zhuxiao() {
			window.location.href = "login.jsp";
		}
	</script>
	</head>

	<body>
		<div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #135ca1;">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;margin-top:8px;" href="">
							<span style="font-size: 25px;color: #135ca1;font-weight: bold;">E掌柜管理平台</span>
						</a>

						<div class="admin-side-toggle" style="width: 85px; float: left;margin-top: 12px;margin-left: -13px;background-color: white;color: #135ca1;font-weight: bold;">
							<%--<i class="fa fa-bars" aria-hidden="true"></i>--%>
							<span style="font-size: 15px"><i class="fa fa-eye-slash" aria-hidden="true" style="font-size: 15px;"></i> 导航隐藏</span>
						</div>

						<div class="admin-side-full" style="width: 75px; margin-left: 30px;margin-top: 12px;background-color: white;color: #135ca1;font-weight: bold;">
							<%--<i class="fa fa-life-bouy" aria-hidden="true"></i>--%>
							<span style="font-size: 15px"><i class="fa fa-arrows" aria-hidden="true" style="font-size: 15px;"></i> 全屏</span>
						</div>

					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">

							<c:choose>
								<c:when test="${empty user}">
									<!-- 判断用户是否登录：未登录，显示登录连接 -->
									<span>
										<a href="/login.jsp" style="font-size:18px;color:#135ca1;vertical-align:bottom;">
											<i class="fa fa-sign-in" aria-hidden="true"></i> 请登录
										</a>
									</span>
								</c:when>
								<c:otherwise>
									<!-- 判断用户是否登录：已登录，显示欢迎 -->
									<span style="font-size:18px;color:#135ca1;vertical-align:bottom;">${user.loginName}，欢迎您</span>
									<dl class="layui-nav-child">
										<dd>
											<a href="personalMessage.jsp"><i class="fa fa-id-card" aria-hidden="true"></i> 个人信息</a>
										</dd>
										<dd>
											<a href="login.jsp" id="zhuxiao"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
											<%--<i class="fa fa-sign-out" aria-hidden="true"></i>--%>
											<%--<input id="zhuxiao" style="border: none;" type="button" value="注销"/>--%>
										</dd>
										<%--<dd>
											<a href="keyManage.jsp"><i class="fa fa-search" aria-hidden="true"></i> 查看密钥</a>
										</dd>--%>
										<dd>
											<a href="passwordManage.jsp"><i class="fa fa-repeat" aria-hidden="true"></i> 密码重置</a>
										</dd>
									</dl>
								</c:otherwise>
							</c:choose>

						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #135ca1;" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite>首页</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="main.html"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
			<!--锁屏模板 start-->
			<script type="text/template" id="lock-temp">
				<div class="admin-header-lock" id="lock-box">
					<div class="admin-header-lock-img">
						<img src="images/0.jpg"/>
					</div>
					<div class="admin-header-lock-name" id="lockUserName">beginner</div>
					<input type="text" class="admin-header-lock-input" value="输入密码解锁.." name="lockPwd" id="lockPwd" />
					<button class="layui-btn layui-btn-small" id="unlock">解锁</button>
				</div>
			</script>
			<!--锁屏模板 end -->
			
			<script type="text/javascript" src="plugins/layui/layui.js"></script>
			<script type="text/javascript" src="datas/nav.js"></script>
			<script src="js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;

					$('#pay').on('click', function() {
						layer.open({
							title: '捐赠我&支付流程体验',
							maxmin: true,
							type: 2,
							content: 'http://shop.epay.org/goods/openQrPay.html',
							area: ['800px', '600px']
						});
                    });
				});
			</script>
		</div>
	</body>

</html>