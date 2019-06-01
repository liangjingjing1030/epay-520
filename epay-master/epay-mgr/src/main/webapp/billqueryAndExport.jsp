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
            $(".time2").datetimepicker({
                language: 'zh-CN',//显示中文
                format: 'yyyy-mm-dd',//显示格式
                minView : "month",
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                clearBtn : true,
                pickerPosition : "top-right"
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
            $("#queryBtn").click(function() {
                // $("#hide_itemsName").val($.trim($("#xmbh").val()));
                display(1, 10);
            });

            // 清空查询条件
            $("#clearBtn").click(function() {
                clearQuery();
            });

            // 修改单条accountBook(模态窗口按钮)
            $("#updateSingleAccountBookBtn").click(function () {
                // 从隐藏域获取当前accountBook的主键
                var account_book_id = $("#hide_account_book_id").val();
                var m_items_id = $.trim($("#m_items_id").val());
                var m_user_id = $.trim($("#m_user_id").val());
                var m_user_name = $.trim($("#m_user_name").val());
                var m_items_money = $.trim($("#m_items_money").val());
                var m_pay_time = $.trim($("#m_pay_time").val());
                var myselect = document.getElementById("m_pay_status");
                var index=myselect.selectedIndex;
                var m_pay_status = myselect.options[index].value;
                $.ajax({
                    type: "get",
                    url: "bill/updateSingleAccountBook",
                    data: {
                        "account_book_id": account_book_id,
                        "items_id": m_items_id,
                        "user_id": m_user_id,
                        "user_name": m_user_name,
                        "items_money": m_items_money,
                        "pay_time": m_pay_time,
                        "pay_status": m_pay_status
                    },
                    success: function (json) {
                        if(json.deleteOK){
                            $("#updateAccountBookModal").modal("hide");
                            display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                            alert("修改成功");
                        }else{
                            alert(json.errorMessage);
                            $("#updateAccountBookModal").modal("hide");
                        }
                    }
                });
            });

            // 添加accountBook(模态窗口按钮)
            $("#addSingleAccountBookBtn").click(function () {
                var a_items_id = $.trim($("#a_items_id").val());
                var a_user_id = $.trim($("#a_user_id").val());
                var a_user_name = $.trim($("#a_user_name").val());
                var a_items_money = $.trim($("#a_items_money").val());
                var a_pay_time = $.trim($("#a_pay_time").val());
                var myselect = document.getElementById("a_pay_status");
                var index=myselect.selectedIndex;
                var a_pay_status = myselect.options[index].value;
                $.ajax({
                    type: "get",
                    url: "bill/addSingleAccountBook",
                    data: {
                        "items_id": a_items_id,
                        "user_id": a_user_id,
                        "user_name": a_user_name,
                        "items_money": a_items_money,
                        "pay_time": a_pay_time,
                        "pay_status": a_pay_status
                    },
                    success: function (json) {
                        if(json.addOK){
                            $("#addAccountBookModal").modal("hide");
                            display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                            alert("添加成功");
                        }else{
                            alert(json.errorMessage);
                            $("#updateAccountBookModal").modal("hide");
                        }
                    }
                });
            });

            // 添加账单accountBook
            $("#addAccountBookBtn").click(function () {
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
                            var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;-请选择所属项目编号-</option>";
                            $.each(jsonObject.itemsIdList, function(i,n){
                                html += "<option >&nbsp;&nbsp;"+n+"</option>"
                            });
                            $("#a_items_id").html(html);
                        } else {
                            $("#showId").html(jsonObject.errorMessage);
                        }
                    }
                });
                // 显示添加缴费明细模态窗口
                $("#addAccountBookModal").modal("show");
            });

            // 给导出全部的按钮绑定鼠标单击事件
            $("#exportAllBtn").click(function(){
                // 判断有无可以导出的账单
                $.ajax({
                    type: "get",
                    url: "bill/checkExportAll",
                    data: {
                        "_": new Date().getTime()
                    },
                    success: function (data) {
                        if(data.ok) {
                            document.location.href = "bill/exportAll";
                        } else {
                            alert(data.errorMessage);
                        }
                    }
                });
            });

            // 给导出选中的按钮绑定鼠标单击事件
            $("#exportSelectedBtn").click(function(){
                var selected = $(":checkbox[name='id']:checked");
                if(selected.size() == 0){
                    alert("请选择要导出的账单");
                }else{
                    var sendData = "";
                    $.each(selected, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    window.location.href = "bill/exportSelected?" + sendData;
                }
            });

            // 删除多条accountBook
            $("#deleteBtn").click(function(){
                var checked = $(":checkbox[name='id']:checked");
                if(checked.size() == 0){
                    alert("请选择要删除的数据!");
                }else{
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    if(window.confirm("您确定要删除数据吗?")){
                        $.post(
                            "bill/deleteAccountBooks",
                            sendData,
                            function(json){
                                if(json.deleteOK){
                                    alert("批量删除成功");
                                    display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                                }else{
                                    alert(json.errorMessage);
                                }
                            }
                        );
                    }
                }
            });

            /*
            // 项目编号自动补全
            $("#xmbh").typeahead({
                source : function(query, process){
                    // query是输入的值，例如：“百度”、“阿里巴巴"
                    $.post("bill/itemsIdAutoCompletion", {"itemsId" : query}, function(arr){
                        process(arr);
                    });
                },
                items : 8, // 最多弹出8个提示
                delay : 500 // 延迟提示
            });
            */
        });//————————————————————————————————————————————————————————————————————————————————————————

        // 删除单条accountBook
        function deleteSingleAccountBook(account_book_id) {
            if(window.confirm("您确定要删除数据吗?")){
                $.post(
                    "bill/deleteAccountSingleAccountBook",
                    {
                        "account_book_id": account_book_id
                    },
                    function(json){
                        if(json.deleteOK){
                            display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                            alert("删除成功");
                        }else{
                            alert(json.errorMessage);
                        }
                    }
                );
            }
        }

        // 修改单条accountBook
        function updateSingleAccountBook(account_book_id,items_id,user_id,user_name,items_money,pay_time,pay_status) {
            // 将主键放入隐藏域
            $("#hide_account_book_id").val(account_book_id);

            $("#m_items_id").val(items_id);
            $("#m_user_id").val(user_id);
            $("#m_user_name").val(user_name);
            $("#m_items_money").val(items_money);
            $("#m_pay_time").val(pay_time);
            //0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中
            $("#m_pay_status option[value='"+pay_status+"']").attr("selected", "selected");

            $("#updateAccountBookModal").modal("show");
        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }
        // 获取下拉列表
        /*function queryItemsId() {
            $.get(
                "mch/queryItemsId",
                {"_" : new Date().getTime()},// 去掉缓存
                function (data) {
                    //{"itemsId1":"","itemsId2":"","itemsId2":""}
                    var html = '<option value=""></option>';
                    $.each(data, function (i, n) {
                        html += '<option value="n">'+n+'</option>';
                    });
                    $("#xmbh").html(html);
                }
            );
        }*/

        function clearQuery() {
            $("#xmbh").val("");
            $("#xmmc").val("");
            $("#wjmc").val("");
            $("#scrq").val("");
            $("#user_id").val("");
            $("#user_name").val("");
        }

        function display(pageNo, pageSize){
            // 获取被选中的下拉菜单的值
            /*var myselect = document.getElementById("xmbh");
            var index=myselect.selectedIndex;
            var xmbh = myselect.options[index].text;*/
            var xmbh = $.trim($("#xmbh").val());
            var xmmc = $.trim($("#xmmc").val());
            var wjmc = $.trim($("#wjmc").val());
            var scrq = $.trim($("#scrq").val());
            var myselect = document.getElementById("orderStates");
            var index=myselect.selectedIndex;
            var status = myselect.options[index].value;
            var user_id = $.trim($("#user_id").val());
            var user_name = $.trim($("#user_name").val());
            // alert(status)
            $.get(
                "bill/accountBookPage",
                {
                    "xmbh" : xmbh,
                    "xmmc" : xmmc,
                    "wjmc" : wjmc,
                    "scrq" : scrq,
                    "status" : status,
                    "user_id" : user_id,
                    "user_name" : user_name,
                    "pageNo" : pageNo,
                    "pageSize" : pageSize
                },
                function(json){
                    //{"total" : total, "pageList" : [{"accountBookId":"","mchId":"","itemsId":"","userId":"","currency":"","itemsMoney":"","discountsMoney":"","actualMoney":"","paidMoney":"","surplusMoney":"","violateMoney":"","payNumber":"","payTime":"","payStatus":""},{},{}]}
                    if(json.errorMessage == "SUCCESS") {
                        var html = "";
                        $.each(json.accountBookList, function(i, n){
                            html += '<tr>';
                            html += '<td><input type="checkbox" name="id" value="'+n.account_book_id+'"/></td>';
                            html += '<td>'+(i + 1)+'</td>';
                            html += '<td>'+(n.items_id == null ? "" : n.items_id)+'</td>';
                            html += '<td>'+(n.items_name == null ? "" : n.items_name)+'</td>';
                            html += '<td>'+(n.items_filename == null ? "" : n.items_filename)+'</td>';
                            html += '<td>'+(n.upload_date == null ? "" : n.upload_date)+'</td>';
                            html += '<td>'+(n.affect_date == null ? "" : n.affect_date)+'</td>';
                            html += '<td>'+(n.expiry_date == null ? "" : n.expiry_date)+'</td>';
                            html += '<td>'+(n.user_id == null ? "" : n.user_id)+'</td>';
                            html += '<td>'+(n.user_name == null ? "" : n.user_name)+'</td>';
                            html += '<td>'+(n.currency == null ? "" : n.currency)+'</td>';
                            html += '<td>'+(n.items_money == null ? "" : n.items_money)+'元</td>';
                            html += '<td>'+(n.pay_time == null ? "" : n.pay_time)+'</td>';
                            //html += "<option value='"+n.id+"' "+(currentUserId == n.id ? "selected" : "")+">"+n.name+"</option>"
                            //0-未支付,1-支付成功,2-支付失败,3-账单退款
                            html += '<td>'+(n.pay_status == 0 ? '未缴费' : n.pay_status == 1 ? '已缴费' : n.pay_status == 2 ? '缴费失败' : '账单退款')+'</td>';
                            html += '<td><input id='+n.account_book_id+' type="button" onclick=deleteSingleAccountBook("'+n.account_book_id+'") name="id" class="btn btn-danger" value="删除" style="height: 25px;border-width:0px;">&nbsp;' +
                                '<input id="detail"'+n.account_book_id+' type="button" onclick=updateSingleAccountBook("'+n.account_book_id+'","'+n.items_id+'","'+n.user_id+'","'+n.user_name+'","'+n.items_money+'","'+n.pay_time+'","'+n.pay_status+'") name="id" class="btn btn-danger" value="修改" style="height: 25px;border-width:0px;background-color: #135ca1;"></td>';
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
        .layui-input{
            height: 35px; width: 190px; float: right;
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
<input id="hide_account_book_id" type="hidden"/>

<!-- 修改accountBook的模态窗口 -->
<div class="modal fade" id="updateAccountBookModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">缴费明细修改</h4>
            </div>

            <div class="modal-body" style="height: 350px; float: left;">

                <div  style="top: 90px;left: 70px;width: 350px">

                    <div class="layui-form-item" style="margin-top: 5px;">
                        <span class="nameSpan">项目编号:</span>
                        <input id="m_items_id" type="text" class="layui-input" disabled style="color: #9d9d9d;">
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">个人标识:</span>
                        <input id="m_user_id" type="text" class="layui-input" disabled style="color: #9d9d9d;">
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
                        <input id="m_user_name" type="text" class="layui-input" disabled style="color: #9d9d9d;">
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">应缴金额/元:</span>
                        <input id="m_items_money" type="text" class="layui-input">
                        <%--<span class="nameSpan" style="float: right;">(单位：元)</span>--%>
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">支付时间:</span>
                        <input id="m_pay_time" type="text" class="layui-input time2">
                    </div>
                    <%--0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中--%>
                    <div class="layui-form-item">
                        <span class="nameSpan">支付状态:</span>
                        <select id="m_pay_status" name="mchType" style="float: right;width: 190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">
                            <option value="0">&nbsp;&nbsp;未缴费</option>
                            <option value="1">&nbsp;&nbsp;已缴费</option>
                            <option value="2">&nbsp;&nbsp;缴费失败</option>
                            <option value="3">&nbsp;&nbsp;账单退款</option>
                            <option value="4">&nbsp;&nbsp;缴费中</option>
                        </select>
                    </div>

                </div>

                <button id="updateSingleAccountBookBtn" style="width:190px;margin-top: 3px;margin-left:153px;bottom: 20px;background-color: #135ca1" class="btn btn-primary">
                    修&nbsp;&nbsp;&nbsp;&nbsp;改
                </button>
                <%--<span id="errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 50px;left: 70px"></span>--%>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 添加accountBook的模态窗口 -->
<div class="modal fade" id="addAccountBookModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">添加缴费明细</h4>
            </div>

            <div class="modal-body" style="height: 350px; float: left;">

                <div  style="top: 90px;left: 70px;width: 350px">

                    <div class="layui-form-item">
                        <span class="nameSpan">项目编号:</span>
                        <select id="a_items_id" name="mchType" style="float: right;width: 190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">

                        </select>
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">个人标识:</span>
                        <input id="a_user_id" type="text" class="layui-input">
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
                        <input id="a_user_name" type="text" class="layui-input">
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">应缴金额/元:</span>
                        <input id="a_items_money" type="text" class="layui-input">
                        <%--<span class="nameSpan" style="float: right;">(单位：元)</span>--%>
                    </div>
                    <div class="layui-form-item" style="margin-top: 15px;">
                        <span class="nameSpan">支付时间:</span>
                        <input id="a_pay_time" type="text" class="layui-input time2">
                    </div>
                    <%--0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中--%>
                    <div class="layui-form-item">
                        <span class="nameSpan">支付状态:</span>
                        <select id="a_pay_status" name="mchType" style="float: right;width: 190px; height: 30px; border-radius: 3px; /*text-align: center; text-align-last: center;*/">
                            <option value="0">&nbsp;&nbsp;未缴费</option>
                            <option value="1">&nbsp;&nbsp;已缴费</option>
                            <option value="2">&nbsp;&nbsp;缴费失败</option>
                            <option value="3">&nbsp;&nbsp;账单退款</option>
                            <option value="4">&nbsp;&nbsp;缴费中</option>
                        </select>
                    </div>

                </div>

                <button id="addSingleAccountBookBtn" style="width:190px;margin-top: 3px;margin-left:153px;bottom: 20px;background-color: #135ca1" class="btn btn-primary">
                    添&nbsp;&nbsp;&nbsp;&nbsp;加
                </button>
                <%--<span id="errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 50px;left: 70px"></span>--%>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>
            </div>
        </div>
    </div>
</div>

<div style="margin: 10px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">账单查询</h3><h5 style="float: left;margin-top: 30px;">(说明:您可以输入任意多项查询条件,如无查询条件则查询全部账单)</h5>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 5px;">
    <form class="form-inline" role="form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">项目编号</div>
                <input class="form-control" type="text" id="xmbh">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">项目名称</div>
                <input class="form-control" type="text" id="xmmc">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">文件名称</div>
                <input class="form-control" type="text" id="wjmc">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">上传日期</div>
                <input class="form-control time" type="text" id="scrq">
            </div>
        </div>

        <br/>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">个人标识</div>
                <input class="form-control" type="text" id="user_id">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">客户姓名</div>
                <input class="form-control" type="text" id="user_name">
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">账单状态</div>
                <select name="status" id="orderStates" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--支付状态,0-未支付,1-支付成功,2-支付失败,3-账单退款,4-支付中--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="0" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 未&nbsp;&nbsp;缴&nbsp;&nbsp;费 ==
                    </option>
                    <option value="1" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 已&nbsp;&nbsp;缴&nbsp;&nbsp;费 ==
                    </option>
                    <option value="2" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 缴费失败 ==
                    </option>
                    <option value="3" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 账单退款 ==
                    </option>
                    <option value="4" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 支&nbsp;&nbsp;付&nbsp;&nbsp;中 ==
                    </option>
                </select>
            </div>
        </div>

        <button type="button" class="btn btn-default" id="queryBtn" style="width: 82px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-search"></span> 查询
        </button>
        <button type="button" class="btn btn-default" id="clearBtn" style="width:96px;background-color: #d9534f;color: white;">
            <span class="glyphicon glyphicon-trash"></span> 清空条件
        </button>
        <br/>
        <button type="button" class="btn btn-default" id="exportAllBtn" style="margin-top:2px;width:96px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-export"></span> 导出全部
        </button>
        <button type="button" class="btn btn-default" id="exportSelectedBtn" style="margin-top:2px;width:96px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-export"></span> 导出选中
        </button>
        <button type="button" class="btn btn-default" id="addAccountBookBtn" style="margin-top:2px;width:96px;background-color: #135ca1;color: white;">
            <span class="glyphicon glyphicon-plus"></span> 添加账单
        </button>
        <button type="button" class="btn btn-danger" id="deleteBtn" style="margin-top:2px;width:96px;">
            <span class="glyphicon glyphicon-minus"></span> 批量删除
        </button>
    </form>
</div>

<div style="margin: 10px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">账单详情</h3>
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
                <th style="text-align: center;">项目名称</th>
                <th style="text-align: center;">文件名称</th>
                <th style="text-align: center;">上传日期</th>
                <th style="text-align: center;">生效时间</th>
                <th style="text-align: center;">失效时间</th>
                <th style="text-align: center;">个人标识</th>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">货币代码</th>
                <th style="text-align: center;">应缴金额</th>
                <th style="text-align: center;">支付时间</th>
                <th style="text-align: center;">支付状态</th>
                <th style="text-align: center;">操&nbsp;&nbsp;&nbsp;&nbsp;作</th>
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