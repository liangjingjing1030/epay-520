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
    <title>订单管理</title>
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
                format: 'yyyy-mm-dd hh:ii:ss',//显示格式
                minView : "day",
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                clearBtn : true,
                // pickerPosition : "top-right"
            });

            checkLogin();

            // 清空查询条件
            $("#clearBtn").click(function() {
                clearQuery();
            });

            // 加载页面前首先查询出当前商户下的所有上传的项目编号
            //queryItemsId();

            // 查询按钮
            $("#queryBtn").click(function() {
                // $("#hide_itemsName").val($.trim($("#xmbh").val()));
                display(1, 10);
            });

            // 全部退款模态窗口下拉列表
            $("#refundAllBtn").click(function () {
                // 查询当前商户下所有的accountFile的items_id
                $.ajax({
                    type: "get",
                    url: "bill/queryAllItemsId",
                    data: {
                        "_" : new Date().getTime()// 去掉缓存
                    },
                    //请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器
                    async:false,
                    success: function (jsonObject) {
                        if(jsonObject.OK) {
                            var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;-请选择要全部退款的项目-</option>";
                            $.each(jsonObject.itemsIdList, function(i,n){
                                html += "<option >&nbsp;&nbsp;"+n+"</option>"
                            });
                            $("#a_items_id").html(html);
                        } else {
                            $("#showId").html(jsonObject.errorMessage);
                        }
                    }
                });
                // 显示全部退款模态窗口
                $("#refundAllModal").modal("show");
            });
            
            // 全部退款（modal）
            $("#refundAllInModalBtn").click(function () {
                var myselect = document.getElementById("a_items_id");
                var index=myselect.selectedIndex;
                var a_items_id = myselect.options[index].value;
                items_id = $.trim(a_items_id);
                // alert(a_items_id);
                $.ajax({
                    type: "get",
                    url: "refund/refundAll",
                    data: {
                        "items_id": items_id
                    },
                    success: function (json) {
                        if(json.OK == "SUCCESS"){
                            alert("退款成功");
                            $("#refundAllModal").modal("hide");
                        }else{
                            alert(json.errorMessage);
                            $("#refundAllModal").modal("hide");
                        }
                    }
                });
            });

            // 退款按钮
            /*$("#billTbody").on("click", $(":button[name='id']"), function(){
                // var sendData = document.getElementById("deleteOrder").value;
                var a = document.getElementsByName("id")[1];
                alert(a.value);
                alert(sendData);
                sendData.substring()
                if(window.confirm("您确定要退款吗?")){
                    $.post(
                        "refund/orderRefund",
                        sendData,
                        function(json){
                            if(json.OK){//请求成功
                                alert(json.errorMessage);
                                display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                            }else{//请求失败
                                alert(json.errorMessage);
                            }
                        }
                    );
                }
            });*/
        });//————————————————————————————————————————————————————————————————————————————————————————

        function clearQuery() {
            $("#pay_order_id").val("");
            $("#mch_order_no").val("");
            $("#channel_id").val("");
            $("#user_id").val("");
            $("#user_name").val("");
            $("#user_channel_account").val("");
        }

        // 退款
        function refund(pay_order_id,mch_id,channel_id,amount,user_id,user_name,user_channel_account) {
            // alert(pay_order_id + "," + mch_id + "," + channel_id + "," + amount + "," + user_id + "," + user_name + "," + user_channel_account);
            // 拼接主键选择器
            var seletor="#"+pay_order_id;
            var v = $(seletor);
            // console.log(v);
            $(seletor).attr("disabled", true);
            if(window.confirm("您确定要退款吗?")){
                // if(checkRefundOrder() ) {
                    $.post(
                        "refund/orderRefund",
                        {
                           "pay_order_id": pay_order_id,
                           "mch_id": mch_id,
                           "channel_id": channel_id,
                           "amount": amount,
                           "user_id": user_id,
                           "user_name": user_name,
                           "user_channel_account": user_channel_account
                        },
                        function(json){
                            if(json.OK){//请求成功
                                // 1.退款成功，退款按钮设为不可用 TODO
                                // $("#refund"+pay_order_id).attr("disabled",true);
                                // 2.退款成功，查询明细按钮设为可用
                                /*window.onload=function(){
                                    document.getElementById("detail"+pay_order_id).disabled=false;
                                }*/
                                // $("#detail"+pay_order_id).attr("disabled",false);
                                // $('#detail'+pay_order_id).removeAttr("disabled");
                                // var a = $('#detail'+pay_order_id).val();
                                // alert(a);

                                alert(json.errorMessage);
                                display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                            }else{//请求失败
                                alert(json.errorMessage);
                            }
                        }
                    );

                // } else {
                //     alert("该账单正在退款中,请稍后……")
                // }
            }
        }
        // 判断退款状态
        function checkRefundOrder() {

        }

        // 退款明细 TODO 确认参数数量
        function detail(pay_order_id,mch_id,channel_id,amount,user_id,user_name,user_channel_account) {
            // alert(pay_order_id + "," + mch_id + "," + channel_id + "," + amount + "," + user_id + "," + user_name + "," + user_channel_account);
            alert("该功能暂未开发");
            /*$.post(
                "refund/refundDetailSingle",
                {
                    "pay_order_id": pay_order_id,
                    "mch_id": mch_id,
                },
                function(json){
                    if(json.OK){//请求成功
                        // 显示模态窗口
                        $("#refundDetailModal").modal("show");
                    }else{//请求失败
                        alert(json.errorMessage);
                    }
                }
            );*/

        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function display(pageNo, pageSize){
            var pay_order_id = $.trim($("#pay_order_id").val());//支付订单号
            var mch_order_no = $.trim($("#mch_order_no").val());// 账单号
            var channel_id = $.trim($("#channel_id").val());// 渠道号
            var user_id = $.trim($("#user_id").val());
            var user_name = $.trim($("#user_name").val());
            var user_channel_account = $.trim($("#user_channel_account").val());// 渠道账号
                var myselect = document.getElementById("orderStates");
                var index=myselect.selectedIndex;
            var status = myselect.options[index].value;
            $.get(
                "refund/orderPage",
                {
                    "pay_order_id" : pay_order_id,
                    "mch_order_no" : mch_order_no,
                    "channel_id" : channel_id,
                    "user_id" : user_id,
                    "user_name" : user_name,
                    "user_channel_account" : user_channel_account,
                    "status" : status,
                    "pageNo" : pageNo,
                    "pageSize" : pageSize
                },
                function(json){
                    //{"total" : total, "pageList" : [{"accountBookId":"","mchId":"","itemsId":"","userId":"","currency":"","itemsMoney":"","discountsMoney":"","actualMoney":"","paidMoney":"","surplusMoney":"","violateMoney":"","payNumber":"","payTime":"","payStatus":""},{},{}]}
                    if(json.errorMessage == "SUCCESS") {
                        var html = "";
                        $.each(json.payOrderList, function(i, n){
                            html += '<tr>';
                            html += '<td>'+(i + 1)+'</td>';
                            html += '<td>'+(n.pay_order_id == null ? "" : n.pay_order_id)+'</td>';
                            html += '<td>'+(n.mch_order_no == null ? "" : n.mch_order_no)+'</td>';
                            html += '<td>'+(n.channel_id == null ? "" : n.channel_id)+'</td>';
                            html += '<td>'+(n.user_id == null ? "" : n.user_id)+'</td>';
                            html += '<td>'+(n.user_name == null ? "" : n.user_name)+'</td>';
                            html += '<td>'+(n.user_channel_account == null ? "" : n.user_channel_account)+'</td>';
                            html += '<td>'+(n.amount == null ? "" : n.amount)+'元</td>';
                            html += '<td>'+(n.currency == null ? "" : n.currency)+'</td>';
                            //html += "<option value='"+n.id+"' "+(currentUserId == n.id ? "selected" : "")+">"+n.name+"</option>"
                            //0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中
                            html += '<td>'+(n.status == 0 ? '订单生成' : n.status == 1 ? '支付成功' : n.status == 2 ? '支付失败' : n.status == 3 ? '已退款' : '支付中')+'</td>';
                            // html += '<td><button '+(n.status == 1 ? "" : "disabled")+' type="button" class="btn btn-danger" name="id"; id="'+n.pay_order_id+'" value="pay_order_id='+n.pay_order_id+"&mch_id="+n.mch_id+"&channel_id="+n.channel_id+"&amount="+n.amount+"&user_id="+n.user_id+"&user_name="+n.user_name+"&user_channel_account="+n.user_channel_account+'" style="height: 25px;border-width:0px;background-color: '+(n.status == 1 ? "#d9534f" : "darkgray")+'"><span class="glyphicon glyphicon-usd" style="height: 25px;"></span> 退 款</button></td>';
                            // 只有订单状态为1-支付成功的才能发起退款，只有订单状态为3-已退款/1-支付成功但是点击发起退款的订单可以查询明细
                            html += '<td><input id='+n.pay_order_id+' '+(n.status == 1 ? "" : "disabled")+' type="button" onclick=refund("'+n.pay_order_id+'","'+ n.mch_id +'","'+ n.channel_id +'","'+ n.amount +'","'+ n.user_id +'","'+ n.user_name +'","'+ n.user_channel_account +'") name="id" class="btn btn-danger" value="退 款" style="height: 25px;border-width:0px;background-color: '+(n.status == 1 ? "#d9534f" : "darkgray")+'"></td>';
                            // &nbsp;' + '<input id="detail"'+n.pay_order_id+' '+(n.status == 1 || n.status == 3 ? "" : "disabled")+' type="button" onclick=detail("'+n.pay_order_id+'","'+ n.mch_id +'","'+ n.channel_id +'","'+ n.amount +'","'+ n.user_id +'","'+ n.user_name +'","'+ n.user_channel_account +'") name="id" class="btn btn-danger" value="明 细" style="height: 25px;border-width:0px;background-color: '+(n.status == 1 || n.status == 3 ? "#d9534f" : "darkgray")+'">
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
        .layui-form-item{
            margin:0px; border:0; padding:0;
        }
        .keySpan{
            float: left; font-size: 15px;font-family: 宋体;width: 100px;
            text-align: right;margin-top: 0px;margin-left: 22px;font-weight: bold;
        }
        .valueSpan{
            float: left; font-size: 15px;font-family: 宋体;
            text-align: right;margin-top: 0px;
        }
        .myHr{
            border-top:1px outset black; margin-left: 30px;margin-top: 0px;bottom: 0px;width: 600px;
        }
        .input-group-addon{
            background-color: #135ca1;color: white;border: 1px solid #135ca1;
        }
    </style>
    <style>
        .layui-input{
            height: 35px; width: 190px; float: right;
        }
        .xingSpan{
            color: red; font-size: 10px;
        }
        .layui-form-item{
            margin:7px; border:0; padding:0;
        }
        .nameSpan{
            width: 100px; float: left; font-size: 15px;font-family: 宋体;
            text-align: right;margin-top: 7px;margin-left: 22px;
        }
    </style>

</head>

<body>

<!-- 全部退款的模态窗口 -->
<div class="modal fade" id="refundAllModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" style="text-align: center;">全部账单退款</h4>
            </div>

            <div class="modal-body" style="height: 150px; float: left;">

                <div  style="top: 90px;width: 350px">

                    <div class="layui-form-item" style="margin-left: -15px;">
                        <span class="nameSpan">项目编号:</span>
                        <select id="a_items_id" name="mchType" style="float: left;width: 200px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">

                        </select>
                    </div>

                </div>
                <h5 style="color: red;margin-left: 43px;">说明:当前项目编号下已缴费的账单将全部退款!</h5>
                <button id="refundAllInModalBtn" style="width:199px;margin-top: 3px;margin-left:107px;bottom: 20px;background-color: #135ca1" class="btn btn-primary">
                    退&nbsp;&nbsp;&nbsp;&nbsp;款
                </button>
                <%--<span id="errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 50px;left: 70px"></span>--%>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

<!-- 退款明细的模态窗口 -->
<div class="modal fade" id="refundDetailModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 55%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">退款明细</h4>
            </div>

            <div class="modal-body" style="height: 420px; float: left;">
                <div  style="top: 100px;left: 50px;">

                    <div class="layui-form-item">
                        <span class="keySpan">退款订单号:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">支付订单号:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">商户号:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">渠道号:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">账单号:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">个人标识:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">用户名称:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">渠道账户:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">支付金额:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">退款金额:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">币种:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">退款状态:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">客户端IP:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">设备:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">备注:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">通知地址:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">订单失效时间:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">退款成功时间:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">创建时间:</span>
                        <span class="valueSpan">xxxxxxx</span>
                        <span class="keySpan" style="margin-left: 200px;">更新时间:</span>
                        <span class="valueSpan">xxxxxxx</span>
                    </div><hr class="myHr">

                </div>
            </div>

            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>
            </div>
        </div>
    </div>
</div>

<div style="margin: 10px;margin-top: 0px;width: 100%;margin-left: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="margin-left:10px;float: left;">订单查询</h3><h5 style="float: left;margin-top: 30px;">&nbsp;&nbsp;(说明:您可以输入任意多项查询条件,如无查询条件则查询全部订单)</h5>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 0px;">
    <form class="form-inline" role="form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">订&nbsp;单&nbsp;号</div>
                <input class="form-control" type="text" id="pay_order_id" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">账&nbsp;单&nbsp;号</div>
                <input class="form-control" type="text" id="mch_order_no" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">渠&nbsp;道&nbsp;号</div>
                <input class="form-control" type="text" id="channel_id" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">个人标识</div>
                <input class="form-control" type="text" id="user_id" placeholder="">
            </div>
        </div>
        <br/>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">客户姓名</div>
                <input class="form-control" type="text" id="user_name" placeholder="">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">渠道账户</div>
                <input class="form-control" type="text" id="user_channel_account" placeholder="">
            </div>
        </div>

        <div class="form-group" style="margin-top: 1px;">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">订单状态</div>
                <select name="status" id="orderStates" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--支付状态,0-订单生成,1-支付成功,2-支付失败,3-已退款,4-支付中--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="0" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 订单生成 ==
                    </option>
                    <option value="1" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 支付成功 ==
                    </option>
                    <option value="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 支付失败 ==
                    </option>
                    <option value="3" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 已&nbsp;&nbsp;退&nbsp;&nbsp;款 ==
                    </option>
                    <option value="4" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 支&nbsp;&nbsp;付&nbsp;&nbsp;中 ==
                    </option>
                </select>
            </div>
        </div>

        <button type="button" class="btn btn-default" id="queryBtn" style="width: 95px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-search"></span>&nbsp;查&nbsp;&nbsp;询
        </button>
        <button type="button" class="btn btn-default" id="clearBtn" style="width: 95px;background-color: #d9534f;color: white;">
            <span class="glyphicon glyphicon-trash"></span>&nbsp;清空条件
        </button>
        <button type="button" class="btn btn-default" id="refundAllBtn" style="width: 95px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-retweet"></span>&nbsp;全部退款
        </button>
    </form>
</div>

<div style="margin: 10px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">订单详情</h3>
    </div>
</div>

<div style="margin: 10px;margin-left: 5px;">
    <div>
        <table class="table table-hover">
            <thead align="center">
            <tr>
                <th style="text-align: center;">序号</th>
                <th style="text-align: center;">订单号</th>
                <th style="text-align: center;">账单号</th>
                <th style="text-align: center;">渠道号</th>
                <th style="text-align: center;">个人标识</th>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">渠道账户</th>
                <th style="text-align: center;">支付金额</th>
                <th style="text-align: center;">货币代码</th>
                <th style="text-align: center;">支付状态</th>
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