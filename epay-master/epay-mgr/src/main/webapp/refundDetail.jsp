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
    <title>退款明细列表</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="css/global.css" media="all">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/table.css" />
    <%--add by GaoLiang--%>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <script type="text/javascript" src="js/jquery-1.11.1-min.js"></script>

    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <link href="jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/localization/en.js"></script>

    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>

    <script type="text/javascript">
        $(function() {

            $(".time").datetimepicker({
                language: 'zh-CN',//显示中文
                format: 'yyyy-mm-dd',//显示格式
                minView : "month",
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                clearBtn : true,
                pickerPosition : "bottom-right"
            });

            checkLogin();

            // 查询所有退款列表,0表示第一页
            $("#queryBtn").click(function() {
                display(0, 10);
            });

            // 清空查询条件
            $("#clearBtn").click(function() {
                clearQuery();
            });

        });//————————————————————————————————————————————————————————————————————————————————————————

        function clearQuery() {
            $("#start_time").val("");
            $("#end_time").val("");
            $("#refund_order_id").val("");
            $("#pay_orderid").val("");
            $("#user_id").val("");
            $("#user_name").val("");
        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function display(pageNo, pageSize){
            var start_time = $.trim($("#start_time").val());
            var end_time = $.trim($("#end_time").val());
            var refund_order_id = $.trim($("#refund_order_id").val());
            var pay_orderid = $.trim($("#pay_orderid").val());
            var user_id = $.trim($("#user_id").val());
            var user_name = $.trim($("#user_name").val());
                var myselect = document.getElementById("orderStates");
                var index=myselect.selectedIndex;
            var status = myselect.options[index].value;
            $.get(
                "refund/refundPage",
                {
                    "start_time" : start_time,
                    "end_time" : end_time,
                    "refund_order_id" : refund_order_id,
                    "pay_orderid" : pay_orderid,
                    "user_id" : user_id,
                    "user_name" : user_name,
                    "status" : status,
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "_" : new Date().getTime()
                },
                function(json){
                    if(json.errorMessage == "SUCCESS") {
                        var html = "";
                        $.each(json.refundOrderJson, function(i, n){
                            html+='<tr>';
                            html+='<td>'+(i + 1)+'</td>';
                            html+='<td>'+(n.create_time == null ? "" : n.create_time)+'</td>';
                            html+='<td>'+(n.refund_order_id == null ? "" : n.refund_order_id)+'</td>';
                            html+='<td>'+(n.pay_orderid == null ? "" : n.pay_orderid)+'</td>';
                            html+='<td>'+(n.pay_amount == null ? "" : n.pay_amount)+'元</td>';
                            html+='<td>'+(n.refund_amount == null ? "" : n.refund_amount)+'元</td>';
                            html+='<td>'+(n.currency == null ? "" : n.currency)+'</td>';
                            html+='<td>'+(n.mch_id == null ? "" : n.mch_id)+'</td>';
                            html+='<td>'+(n.mch_refund_no == null ? "" : n.mch_refund_no)+'.00</td>';
                            html+='<td>'+(n.channel_id == null ? "" : n.channel_id)+'</td>';
                            //退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败
                            html += '<td>'+(n.status == 0 ? '订单生成' : n.status == 1 ? '退款中' : n.status == 2 ? '退款成功' : '退款失败')+'</td>';
                            html+='</tr>';
                        });
                        $("#billTbody").html(html);
                        //显示翻页pagination
                        var totalRows = json.total;
                        var totalPages = totalRows % pageSize == 0 ? totalRows / pageSize : parseInt(totalRows / pageSize) + 1;
                        $("#cluePagination").bs_pagination({
                            currentPage: pageNo + 1,  // 页码
                            rowsPerPage: pageSize, // 每页显示的记录条数
                            maxRowsPerPage: 100, // 每页最多显示的记录条数
                            totalPages: totalPages, // 总页数
                            totalRows: totalRows, //总记录条数
                            visiblePageLinks: 5, // 设置显示卡片的个数
                            showGoToPage: true,
                            showRowsPerPage: true,
                            showRowsInfo: true,
                            showRowsDefaultInfo: true,
                            // 翻页的时候执行以下的回调函数。
                            onChangePage : function(event, data){
                                display(data.currentPage , data.rowsPerPage);
                            }
                        });
                    } else {
                        alert("未查询到符合要求的订单,请确认查询条件是否正确!");
                        // window.location.reload();页面刷新效果不好
                        $("#billTbody").html("");
                    }
                }
            );
        }
    </script>
    <style>
        <%--强制不换行--%>
        div{
            white-space:nowrap;
        }
        .input-group-addon{
            background-color: #135ca1;color: white;border: 1px solid #135ca1;
        }
    </style>

</head>

<body>

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">退款查询</h3><h5 style="float: left;margin-top: 30px;">(说明:您可以输入任意多项查询条件,如无查询条件则查询全部退款)</h5>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 5px;">
    <form class="form-inline" role="form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">开始时间</div>
                <input class="form-control time" type="text" id="start_time" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">结束时间</div>
                <input class="form-control time" type="text" id="end_time" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">退款单号</div>
                <input class="form-control" type="text" id="refund_order_id" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">支付单号</div>
                <input class="form-control" type="text" id="pay_orderid" placeholder="">
            </div>
        </div>
        <br/>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">个人标识</div>
                <input class="form-control" type="text" id="user_id" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">姓&nbsp;&nbsp;&nbsp;&nbsp;名</div>
                <input class="form-control" type="text" id="user_name" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">退款状态</div>
                <select name="status" id="orderStates" lay-search="" style="width: 200px;height: 35px">
                    <%--支付状态:0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中--%>
                    <%--退款状态:0-订单生成,1-退款中,2-退款成功,3-退款失败--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="0" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 订单生成 ==
                    </option>
                    <option value="1" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 退&nbsp;&nbsp;款&nbsp;&nbsp;中 ==
                    </option>
                    <option value="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 退款成功 ==
                    </option>
                    <option value="3" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 退款失败 ==
                    </option>
                </select>
            </div>
        </div>

        <button type="button" class="btn btn-default" id="queryBtn" style="width: 144px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-search"></span>&nbsp;查&nbsp;&nbsp;询
        </button>
        <button type="button" class="btn btn-default" id="clearBtn" style="width: 144px;background-color: #d9534f;color: white;">
            <span class="glyphicon glyphicon-trash"></span>&nbsp;清空条件
        </button>
    </form>
</div>

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">退款明细</h3>
    </div>
</div>

<div style="margin:10px;margin-left: 5px;width:100%; overflow:auto;">
    <table class="table table-hover">
        <thead align="center">
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">创建时间</th>
            <th style="text-align: center;">退款订单号</th>
            <th style="text-align: center;">支付订单号</th>
            <th style="text-align: center;">支付金额</th>
            <th style="text-align: center;">退款金额</th>
            <th style="text-align: center;">货币代码</th>
            <th style="text-align: center;">商户号</th>
            <th style="text-align: center;">账单号</th>
            <th style="text-align: center;">渠道号</th>
            <th style="text-align: center;">退款状态</th>
        </tr>
        </thead>
        <tbody id="billTbody" align="center">

        </tbody>

    </table>

    <div id="cluePagination"></div>

</div>
</body>

</html>