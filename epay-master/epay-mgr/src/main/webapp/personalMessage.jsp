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
<title>个人信息</title>
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

		// 查询商户信息
		queryMchInfo();

	});

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
					html = "";
					$.each(jsonObject.mchInfoList, function (i, n) {
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">商&nbsp;&nbsp;户&nbsp;&nbsp;号：</span></br><span style="margin-left: 120px;">'+n.mch_id+'</span></td>';
						html+='<td><span class="titleName">商户名称：</span></br><span style="margin-left: 120px;">'+n.mch_name+'</span></td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">商户类型：</span></br><span style="margin-left: 120px;">'+(n.mch_type == '1' ? '个体商户' : n.mch_type == '2' ? '企业' : '小微商户')+'</span></td>';
						html+='<td><span class="titleName">经营范围：</span></br><span style="margin-left: 120px;">'+n.mch_range+'</span></td>';
						html+='</tr>';
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">所在城市：</span></br><span style="margin-left: 120px;">'+n.mch_city+'</span></td>';
						html+='<td><span class="titleName">商户地址：</span></br><span style="margin-left: 120px;">'+n.mch_address+'</span></td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">商户状态：</span></br><span style="margin-left: 120px;">'+(n.mch_status == '0' ? '未激活' : n.mch_status == '1' ? '使用中' : '停止使用')+'</span></td>';
						html+='<td><span class="titleName">联&nbsp;&nbsp;系&nbsp;&nbsp;人：</span></br><span style="margin-left: 120px;">'+n.contact_person+'</span></td>';
						html+='</tr>';
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">联系电话：</span></br><span style="margin-left: 120px;">'+n.contact_phone+'</span></td>';
						html+='<td><span class="titleName">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span></br><span style="margin-left: 120px;">'+n.contact_email+'</span></td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">证件类型：</span></br><span style="margin-left: 120px;">'+n.certificate_type+'</span></td>';
						html+='<td><span class="titleName">证件号码：</span></br><span style="margin-left: 120px;">'+n.certificate_number+'</span></td>';
						html+='</tr>';
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">营业执照：</span></br><span style="margin-left: 120px;">'+n.business_license+'</span></td>';
						html+='<td><span class="titleName">拓展网点号：</span></br><span style="margin-left: 120px;">'+n.branch_id+'</span></td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">拓展网点名：</span></br><span style="margin-left: 120px;">'+n.branch_name+'</span></td>';
						html+='<td><span class="titleName">拓展员工号：</span></br><span style="margin-left: 120px;">'+n.staff_id+'</span></td>';
						html+='</tr>';
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">拓展员工名：</span></br><span style="margin-left: 120px;">'+n.stall_name+'</span></td>';
						html+='<td><span class="titleName">创建时间：</span></br><span style="margin-left: 120px;">'+n.create_time+'</span></td>';
						html+='</tr>';
						html+='<tr>';
						html+='<td><span class="titleName">更新时间：</span></br><span style="margin-left: 120px;">'+n.update_time+'</span></td>';
						html+='<td><span class="titleName">审核时间：</span></br><span style="margin-left: 120px;">'+n.audit_time+'</span></td>';
						html+='</tr>';
						html+='<tr style="background-color: #135ca1;color:white">';
						html+='<td><span class="titleName">审核状态：</span></br><span style="margin-left: 120px;">'+(n.audit_status == '0' ? '未审核' : n.audit_status == '1' ? '已审核' : '拒绝')+'</span></td>';
						html+='<td><span class="titleName">审核原因：</span></br><span style="margin-left: 120px;">'+n.audit_reason+'</span></td>';
						html+='</tr>';
					});
					$("#content").html(html);
				}
			}
		});
	}

</script>
	<style>
		.titleName{
			width:300px;margin-left:50px;
		}
		.valueSpan{
			width: 300px;margin-left: 120px;
		}
	</style>

</head>

<body>
	<%--margin:外边框；padding:内边框--%>
	<%--margin:0 auto;实现div居中--%>
	<div class="layui-field-box layui-form" style="margin:0 auto;margin-top:10px;width:50%;border:solid 0px #C0C0C0;padding: 5px;">
	<table class="layui-table admin-table">
		<thead>
			<tr>
				<c:choose>
					<c:when test="${empty user}">
					</c:when>
					<c:otherwise>
						<th colspan="2" style="font-size: 25px; text-align: center;">个&nbsp; 人&nbsp; 信&nbsp; 息</th>
					</c:otherwise>
				</c:choose>
			</tr>
		</thead>
		<tbody id="content"><%--
			<c:choose>
				<c:when test="${not empty mchInfo}">
					<tr>
						<div>
							<td><span class="titleName">商&nbsp;&nbsp;户&nbsp;&nbsp;号：</span><br/><span class="valueSpan">${mchInfo.mch_id}</span></td>
							<td><span class="titleName">商户名称：</span><br/><span class="valueSpan">${mchInfo.mch_name}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">商户类型：</span><br/><span class="valueSpan">${mchInfo.mch_type}</span></td>
							<td><span class="titleName">经营范围：</span><br/><span class="valueSpan">${mchInfo.mch_range}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">所在城市：</span><br/><span class="valueSpan">${mchInfo.mch_city}</span></td>
							<td><span class="titleName">商户地址：</span><br/><span class="valueSpan">${mchInfo.mch_address}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">商户状态：</span><br/><span class="valueSpan">${mchInfo.mch_status == 0 ? '未激活' : mchInfo.mch_status == 1 ? '使用中' : '停止使用'}</span></td>
							<td><span class="titleName">联&nbsp;&nbsp;系&nbsp;&nbsp;人：</span><br/><span class="valueSpan">${mchInfo.contact_person}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">联系电话：</span><br/><span class="valueSpan">${mchInfo.contact_phone}</span></td>
							<td><span class="titleName">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span><br/><span class="valueSpan">${mchInfo.contact_email}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">证件类型：</span><br/><span class="valueSpan">${mchInfo.certificate_type}</span></td>
							<td><span class="titleName">证件号码：</span><br/><span class="valueSpan">${mchInfo.certificate_number}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">营业执照：</span><br/><span class="valueSpan">${mchInfo.business_license}</span></td>
							<td><span class="titleName">拓展网点号：</span><br/><span class="valueSpan">${mchInfo.branch_id}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">拓展网点名称：</span><br/><span class="valueSpan">${mchInfo.branch_name}</span></td>
							<td><span class="titleName">拓展员工号：</span><br/><span class="valueSpan">${mchInfo.staff_id}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">拓展员工名称：</span><br/><span class="valueSpan">${mchInfo.stall_name}</span></td>
							<td><span class="titleName">创建时间：</span><br/><span class="valueSpan">${mchInfo.create_time}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">更新时间：</span><br/><span class="valueSpan">${mchInfo.update_time}</span></td>
							<td><span class="titleName">审核时间：</span><br/><span class="valueSpan">${mchInfo.audit_time}</span></td>
						</div>
					</tr>
					<tr>
						<div>
							<td><span class="titleName">审核状态：</span><br/><span class="valueSpan">${mchInfo.audit_status== 0 ? '未审核' : mchInfo.audit_status == 1 ? '已审核' : '拒绝'}</span></td>
							<td><span class="titleName">审核原因：</span><br/><span class="valueSpan">${mchInfo.audit_reason}</span></td>
						</div>
					</tr>
				</c:when>
			</c:choose>
		--%></tbody>
	</table>
	</div>

</body>

</html>