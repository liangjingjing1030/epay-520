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
    <title>账单管理</title>
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
                // pickerPosition : "right"
            });

            $("#firstCheckBox").click(function(){
                $(":checkbox[name='id']").prop("checked", this.checked);
            });
            $("#billTbody").on("click", $(":checkbox[name='id']"), function(){
                $("#firstCheckBox").prop("checked", $(":checkbox[name='id']:checked").size() == $(":checkbox[name='id']").size() ? "checked" : "");
            });

            checkLogin();

            // 加载页面前首先查询出当前商户下的所有上传的项目编号
            //queryItemsId();

            // 查询按钮
            $("#queryBtn2").click(function() {
                display(1, 10);
            });

            // 清空查询条件
            $("#clearBtn").click(function() {
                clearQuery();
            });

            // 批量回单
            $("#printBtn").click(function(){
                var checked = $(":checkbox[name='id']:checked");
                if(checked.size() == 0){
                    alert("请选择要生成回单的数据!");
                }else{
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    // alert(sendData);
                    window.location.href = "bill/generateReceipt?" + sendData;
                }
            });

        });//————————————————————————————————————————————————————————————————————————————————————————

        // 生成单个回单
        function genereteDeceipt(account_book_id) {
            var sendData = "id=" + account_book_id;
            window.location.href = "bill/generateReceipt?" + sendData;
        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function clearQuery() {
            $("#items_id").val("");
            $("#user_id").val("");
            $("#user_name").val("");
            $("#pay_time").val("");
            // $("#pay_status").val("");
            // $("#settle_status").val("");
            $("#pay_channel").val("");
            $("#mch_order_no").val("");
            $("#user_channel_account").val("");
            $("#channel_mch_id").val("");
            $("#channel_order_no").val("");
            $("#res_code").val("");
            $("#expire_time").val("");
            // 清空状态
            $("#pay_status").val("-99");//
            $("#settle_status").val("-99");//
        }

        function display(pageNo, pageSize){
            var items_id = $.trim($("#items_id").val());
            var user_id = $.trim($("#user_id").val());
            var user_name = $.trim($("#user_name").val());
            var pay_time = $.trim($("#pay_time").val());
                var myselect = document.getElementById("pay_status");
                var index=myselect.selectedIndex;
            var pay_status = myselect.options[index].value;
                var myselect = document.getElementById("settle_status");
                var index=myselect.selectedIndex;
            var settle_status = myselect.options[index].value;
            var pay_channel = $.trim($("#pay_channel").val());
            var mch_order_no = $.trim($("#mch_order_no").val());
            var user_channel_account = $.trim($("#user_channel_account").val());
            var channel_mch_id = $.trim($("#channel_mch_id").val());
            var channel_order_no = $.trim($("#channel_order_no").val());
            var res_code = $.trim($("#res_code").val());
            var expire_time = $.trim($("#expire_time").val());
            $.get(
                "bill/accountBookAndPayOrderPage",
                {
                    "items_id" : items_id,
                    "user_id" : user_id,
                    "user_name" : user_name,
                    "pay_time" : pay_time,
                    "pay_status" : pay_status,
                    "settle_status" : settle_status,
                    "pay_channel" : pay_channel,
                    "mch_order_no" : mch_order_no,
                    "user_channel_account" : user_channel_account,
                    "channel_mch_id" : channel_mch_id,
                    "channel_order_no" : channel_order_no,
                    "res_code" : res_code,
                    "expire_time" : expire_time,
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "_" : new Date().getTime()
                },
                function(json){
                    //{"total" : total, "pageList" : [{"accountBookId":"","mchId":"","itemsId":"","userId":"","currency":"","itemsMoney":"","discountsMoney":"","actualMoney":"","paidMoney":"","surplusMoney":"","violateMoney":"","payNumber":"","payTime":"","payStatus":""},{},{}]}
                    if(json.errorMessage == "SUCCESS") {
                        var html = "";
                        $.each(json.accountBookAndPayOrderList, function(i, n){
                            html += '<tr>';
                            html += '<td><input type="'+(n.pay_status == 1 ? "checkbox" : "hidden")+'" name="id" value="'+n.account_book_id+'"/></td>';
                            html += '<td>'+(i + 1)+'</td>';
                            html += '<td>'+(n.items_id == null ? "" : n.items_id)+'</td>';
                            html += '<td>'+(n.user_id == null ? "" : n.user_id)+'</td>';
                            html += '<td>'+(n.user_name == null ? "" : n.user_name)+'</td>';
                            html += '<td>'+(n.pay_time == null ? "" : n.pay_time)+'</td>';
                            html += '<td>'+(n.pay_status == 0 ? '未缴费' : n.pay_status == 1 ? '已缴费' : n.pay_status == 2 ? '缴费失败' : n.pay_status == 3 ? '账单退款' : '缴费中')+'</td>';
                            // 结算状态,0-未结算,1-结算成功,2-结算失败
                            html += '<td>'+(n.settle_status == 0 ? '未结算' : n.settle_status == 1 ? '结算成功' : '结算失败')+'</td>';
                            html += '<td>'+(n.pay_channel == null ? "" : n.pay_channel)+'</td>';
                            html += '<td>'+(n.mch_order_no == null ? "" : n.mch_order_no)+'</td>';
                            html += '<td>'+(n.user_channel_account == null ? "" : n.user_channel_account)+'</td>';
                            html += '<td>'+(n.channel_mch_id == null ? "" : n.channel_mch_id)+'</td>';
                            html += '<td>'+(n.channel_order_no == null ? "" : n.channel_order_no)+'</td>';
                            html += '<td>'+(n.res_code == null ? "" : n.res_code)+'</td>';
                            html += '<td>'+(n.expire_time == null ? "" : n.expire_time)+'</td>';
                            html += '<td><input id='+n.account_book_id+' '+(n.pay_status == 1 ? "" : "disabled")+' type="button" onclick=genereteDeceipt("'+n.account_book_id+'") name="id" class="btn btn-danger" value="回单" style="height: 25px;border-width:0px;background-color: '+(n.pay_status == 1 ? "#d9534f" : "darkgray")+'"></td>';
                            html += '</tr>';
                        });
                        $("#billTbody").html(html);
                        //显示翻页pagination
                        var totalRows = json.total;
                        var totalPages = totalRows % pageSize == 0 ? totalRows / pageSize : parseInt(totalRows / pageSize) + 1;
                        $("#cluePagination").bs_pagination({
                            currentPage: pageNo,  // 页码
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
        .input-group-addon{
            /*width: 200px;*/
        }
    </style>

</head>

<body>

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">交易查询</h3><h5 style="float: left;margin-top: 30px;">(说明:账单+订单查询,您可以输入任意多项查询条件)</h5>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 5px;">
    <form class="form-inline" role="form">
        <%--accountBook字段--%>
        <div class="form-group"><%--1--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">项目编号</div>
                <input class="form-control" type="text" id="items_id">
            </div>
        </div>

        <div class="form-group"><%--2--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">个人标识</div>
                <input class="form-control" type="text" id="user_id">
            </div>
        </div>

        <div class="form-group"><%--3--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">客户姓名</div>
                <input class="form-control" type="text" id="user_name">
            </div>
        </div>

        <div class="form-group"><%--4--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">支付时间</div>
                <input class="form-control time" type="text" id="pay_time">
            </div>
        </div>
        <br/>
        <div class="form-group"><%--5--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">支付状态</div>
                <select name="status" id="pay_status" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="1" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 未&nbsp;&nbsp;缴&nbsp;&nbsp;费 ==
                    </option>
                    <option value="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 已&nbsp;&nbsp;缴&nbsp;&nbsp;费 ==
                    </option>
                    <option value="3" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 缴费失败 ==
                    </option>
                    <option value="4" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 账单退款 ==
                    </option>
                    <option value="5" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 缴&nbsp;&nbsp;费&nbsp;&nbsp;中 ==
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group" style="margin-left:1px;"><%--6--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">结算状态</div>
                <select name="status" id="settle_status" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--结算状态,0-未结算,1-结算成功,2-结算失败--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="1" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 未&nbsp;&nbsp;结&nbsp;&nbsp;算 ==
                    </option>
                    <option value="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 结算成功 ==
                    </option>
                    <option value="3" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 结算失败 ==
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group"><%--7--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">支付渠道</div>
                <input class="form-control" type="text" id="pay_channel">
            </div>
        </div>
        <%--PayOrder字段--%>
        <div class="form-group"><%--8--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">账单号</div>
                <input class="form-control" type="text" id="mch_order_no">
            </div>
        </div>
        <br/>

        <div class="form-group"><%--9--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">客户渠道账号</div>
                <input class="form-control" type="text" id="user_channel_account">
            </div>
        </div>

        <div class="form-group"><%--10--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">渠道商户号</div>
                <input class="form-control" type="text" id="channel_mch_id">
            </div>
        </div>

        <div class="form-group"><%--11--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">渠道订单号</div>
                <input class="form-control" type="text" id="channel_order_no">
            </div>
        </div>

        <div class="form-group"><%--12--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">渠道支付码</div>
                <input class="form-control" type="text" id="res_code">
            </div>
        </div>
        <br/>
        <div class="form-group"><%--13--%>
            <div class="input-group">
                <div class="input-group-addon" style="width: 120px;">订单失效时间</div>
                <input class="form-control time" type="text" id="expire_time">
            </div>
        </div>

        <button type="button" class="btn btn-default" id="queryBtn2" style="width: 120px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;查&nbsp;&nbsp;&nbsp;&nbsp;询
        </button>
        <button type="button" class="btn btn-default" id="printBtn" style="width: 120px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-print"></span>&nbsp;&nbsp;生成回单
        </button>
        <button type="button" class="btn btn-default" id="clearBtn" style="width:120px;background-color: #d9534f;color: white;">
            <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;清空条件
        </button>
    </form>
</div>

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">交易详情</h3>
    </div>
</div>

<div style="margin: 10px;margin-left: 5px;">
    <div>
        <table class="table table-hover">
            <thead align="center">
            <tr style="color: #B3B3B3;">
                <td><input type="checkbox" id="firstCheckBox"/></td>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">项目编号</th>
                <th style="text-align: center;">个人标识</th>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">支付时间</th>
                <th style="text-align: center;">支付状态</th>
                <th style="text-align: center;">结算状态</th>
                <th style="text-align: center;">渠道</th>
                <th style="text-align: center;">账单号</th>
                <th style="text-align: center;">渠道账号</th>
                <th style="text-align: center;">渠道商户号</th>
                <th style="text-align: center;">渠道订单号</th>
                <th style="text-align: center;">渠道支付码</th>
                <th style="text-align: center;">失效时间</th>
                <th style="text-align: center;">操作</th>
            </tr>
            </thead>
            <tbody id="billTbody" align="center">

            </tbody>

        </table>

        <div id="cluePagination"></div>

    </div>
</div>
</body>

</html>