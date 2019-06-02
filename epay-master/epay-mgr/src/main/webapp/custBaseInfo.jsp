<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--fmt为jstl的格式化标签--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--fn为jstl中的功能函数--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>商户配置</title>
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/global.css" media="all">
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="css/table.css" />
<%--add by GaoLiang--%>
<script type="text/javascript" src="js/jquery-1.11.1-min.js"></script>

<style>
	.titleName{
		font-weight: bold;
	}
</style>
<script type="text/javascript">

	$(function() {

		checkLogin();

		// 查询商户信息
		queryMchInfo();

		// 查询商户结算信息
		queryMchStlInfo();

		// 查询商户渠道信息
		queryMchChannel();

	});

	// 查询渠道信息
	function queryMchChannel() {
		$.ajax({
			type: "get",
			url: "mch/queryMchChannel",
			success: function (jsonObject) {
				if(jsonObject.errorMessage == "OK") {
					html2 = "";
					if(jsonObject.size > 0) {
						html2+='<tr>';
						html2+='<th colspan="5" style="font-size: 20px; width: 50%;">商户渠道信息</th>';
						html2+='</tr>';
						$("#thead3").html(html2);

						html = '<tr>'+
								'<td style="width: 8%;height: 10px;"><span class="titleName">序号</span></td>'+
								'<td style="width: 23%;height: 10px;"><span class="titleName">渠道名称</span></td>'+
								'<td style="width: 20%;height: 10px;"><span class="titleName">渠道商户号</span></td>'+
								'<td style="width: 20%;height: 10px;"><span class="titleName">渠道状态</span></td>'+
								'<td style="width: 29%;height: 10px;"><span class="titleName">创建时间</span></td>'+
								'</tr>';
						// 循环遍历
						$.each(jsonObject.mchChannelList, function (i, n) {
							html+='<tr>';
							html+='<td>'+(i + 1)+'</td>';
							html+='<td>'+n.channel_name+'</td>';
							html+='<td>'+n.channel_mchId+'</td>';
							html+='<td>'+(n.channel_status == 0 ? '停止使用' : '使用中')+'</td>';
							html+='<td>'+n.create_time+'</td>';
							html+='</tr>';
						});
						$("#content3").html(html);

					} else {
						// html2 += "<span style='color:red;'>未绑定渠道信息!</span>";
						$("#thead3").html(html2);
					}

				}
			}
		});
	}

	// 查询结算信息
	function queryMchStlInfo() {
		$.ajax({
			type: "get",
			url: "mch/queryMchStlInfo",
			success: function (jsonObject) {
				if(jsonObject.errorMessage == "OK") {
					html2 = "";
					html2+='<tr>';
					html2+='<th colspan="3" style="font-size: 20px; width: 50%;">商户结算信息</th>';
					html2+='</tr>';
					$("#thead2").html(html2);

					html = "";
					$.each(jsonObject.mchStlInfoList, function (i, n) {
						html+='<tr>';
						html+='<td style="width: 34%;"><span class="titleName">银行结算账号：</span>'+n.mch_bank_account+'</td>';
						html+='<td style="width: 33%;"><span class="titleName">结算账户名：</span>'+n.mch_bank_name+'</td>';
						html+='<td style="width: 33%;"><span class="titleName">开户行：</span>'+n.account_bank+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">账户类型：</span>'+(n.account_type == '0' ? '对私' : '对公')+'</td>';
						html+='<td><span class="titleName">交易结算日：</span>'+n.mch_stl_day+'</td>';
						html+='<td><span class="titleName">创建时间：</span>'+n.create_time+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td colspan="3"><span class="titleName">更新时间：</span>'+n.update_time+'</td>';
						html+='</tr>';
					});
					$("#content2").html(html);

				}
			}
		});
	}

	function checkLogin() {
		var loginName = "${user.loginName}";
		if(loginName == null || loginName == "") {
			alert("请先登录！")
			window.open("/login.jsp");
		}
	}

	// 查询商户信息
	function queryMchInfo() {
		$.ajax({
			type: "get",
			url: "mch/queryMchInfo",
			success: function (jsonObject) {
				if(jsonObject.errorMessage != "OK") {
					alert("请绑定商户信息");
					//跳转页面
					// location.href='register2.jsp';
					window.open("/register1.jsp")
				} else {
					// 刷新页面   造成循环调用
					// window.location.href = 'custBaseInfo.jsp';
					// window.location.reload();
					html2 = "";
					html2+='<tr>';
					html2+='<th colspan="4" style="font-size: 20px;">基本信息</th>';
					html2+='</tr>';
					$("#thead1").html(html2);

					html = "";
					$.each(jsonObject.mchInfoList, function (i, n) {
						html+='<tr style="width: 25%;">';
						html+='<td style="width: 25%;"><span class="titleName">商户号：</span>'+n.mch_id+'</td>';
						html+='<td style="width: 25%;"><span class="titleName">商户名称：</span>'+n.mch_name+'</td>';
						html+='<td style="width: 25%;"><span class="titleName">商户类型：</span>'+(n.mch_type == '1' ? '个体商户' : n.mch_type == '2' ? '企业' : '小微商户')+'</td>';
						html+='<td style="width: 25%;"><span class="titleName">经营范围：</span>'+n.mch_range+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">所在城市：</span>'+n.mch_city+'</td>';
						html+='<td><span class="titleName">商户地址：</span>'+n.mch_address+'</td>';
						html+='<td><span class="titleName">商户状态：</span>'+(n.mch_status == '0' ? '未激活' : n.mch_status == '1' ? '使用中' : '停止使用')+'</td>';
						html+='<td><span class="titleName">联系人：</span>'+n.contact_person+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">联系电话：</span>'+n.contact_phone+'</td>';
						html+='<td><span class="titleName">邮箱：</span>'+n.contact_email+'</td>';
						html+='<td><span class="titleName">证件类型：</span>'+n.certificate_type+'</td>';
						html+='<td><span class="titleName">证件号码：</span>'+n.certificate_number+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">营业执照：</span>'+n.business_license+'</td>';
						html+='<td><span class="titleName">拓展网点号：</span>'+n.branch_id+'</td>';
						html+='<td><span class="titleName">拓展网点名称：</span>'+n.branch_name+'</td>';
						html+='<td><span class="titleName">拓展员工号：</span>'+n.staff_id+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">拓展员工名称：</span>'+n.stall_name+'</td>';
						html+='<td><span class="titleName">创建时间：</span>'+n.create_time+'</td>';
						html+='<td><span class="titleName">更新时间：</span>'+n.update_time+'</td>';
						html+='<td><span class="titleName">审核时间：</span>'+n.audit_time+'</td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">审核状态：</span>'+(n.audit_status == '0' ? '未审核' : n.audit_status == '1' ? '已审核' : '拒绝')+'</td>';
						html+='<td colspan="3"><span class="titleName">审核原因：</span>'+n.audit_reason+'</td>';
						html+='</tr>';
					});
					$("#content").html(html);

				}
			}
		});
	}

</script>

</head>

<body>
<%--margin:外边框；padding:内边框--%>
	<div class="layui-field-box layui-form" style="border:solid 0px #C0C0C0;margin:5px;padding: 5px;margin-top: -10px;">
	<table class="layui-table admin-table">
		<thead id="thead1">
			<%--<tr>
				<th colspan="4" style="font-size: 20px;">基本信息</th>
			</tr>--%>
		</thead>
		<tbody id="content">

		</tbody>
	</table>
	</div>

	<div class="layui-field-box layui-form" style="border:solid 0px #C0C0C0;margin:5px;padding: 5px;margin-top: -20px;">
		<table class="layui-table admin-table">
			<thead id="thead2">
			<%--<tr>
				<th colspan="3" style="font-size: 20px; width: 50%;">商户结算信息</th>
			</tr>--%>
			</thead>
			<tbody id="content2">

			</tbody>
		</table>
	</div>

	<div class="layui-field-box layui-form" style="border:solid 0px #C0C0C0;margin:5px;padding: 5px;margin-top: -20px;">
		<table class="layui-table admin-table">
			<thead id="thead3">
			<%--<tr>
				<th colspan="5" style="font-size: 20px; width: 50%;">商户渠道信息</th>
			</tr>--%>
			</thead>
			<tbody id="content3" style="font-size: 1px;">

			</tbody>
		</table>
	</div>

</body>

</html>