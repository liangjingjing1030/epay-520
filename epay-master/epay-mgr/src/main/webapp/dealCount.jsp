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

            display(1, 10);

            // 合并统计
            $("#countBtn").click(function () {
                var checked = $(":checkbox[name='id']:checked");
                if(checked.size() == 0){
                    alert("请选择要合并的收费活动!");
                } else if(checked.size() == 1) {
                    alert("请选择2个以上收费活动进行合并统计!");
                } else {
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    // alert(sendData);
                    // $("#countDetailModal").modal("show");
                    $.get(
                        "bill/accountFileCount",
                        sendData,
                        function(json){
                            if(json.errorMessage == "SUCCESS"){
                                var html = "";
                                html += '<tr>';
                                html += '<td>当前共统计收费活动:'+json.count+'条</td>';
                                html += '</tr>';
                                html += '<tr>';
                                html += '<td>总金额:'+json.totalMoney+'元</td>';
                                html += '</tr>';
                                html += '<tr>';
                                html += '<td>已缴金额:'+json.readMoney+'元</td>';
                                html += '</tr>';
                                html += '<tr>';
                                html += '<td>待缴金额:'+json.daiMoney+'元</td>';
                                html += '</tr>';
                                html += '<tr>';
                                html += '<td>退款金额:'+json.backMoney+'元</td>';
                                html += '</tr>';
                                html += '<tr>';
                                html += '<td>进度:'+json.perent+'%</td>';
                                html += '</tr>';
                                $("#bookTbody").html(html);
                                // 显示模态窗口
                                $("#countDetailModal").modal("show");
                            }else{
                                alert(json.errorMessage);
                            }
                        }
                    );
                }
            });

        });//————————————————————————————————————————————————————————————————————————————————————————

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function display(pageNo, pageSize){
            $.get(
                "bill/accountFilePageAddCount",
                {
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "_" : new Date().getTime()
                },
                function(json){
                    //{"total" : total, "pageList" : [{"accountBookId":"","mchId":"","itemsId":"","userId":"","currency":"","itemsMoney":"","discountsMoney":"","actualMoney":"","paidMoney":"","surplusMoney":"","violateMoney":"","payNumber":"","payTime":"","payStatus":""},{},{}]}
                    if(json.errorMessage == "SUCCESS") {
                        var html = "";
                        $.each(json.accountFileForCountList, function(i, n){
                            html += '<tr>';
                            html += '<td><input type="checkbox" name="id" value="'+n.totalMoney+','+n.readMoney+','+n.daiMoney+','+n.backMoney+'" /></td>';
                            html += '<td>'+(i + 1)+'</td>';
                            html += '<td>'+(n.items_id == null ? "" : n.items_id)+'</td>';
                            html += '<td>'+(n.items_name == null ? "" : n.items_name)+'</td>';
                            html += '<td>'+(n.items_filename == null ? "" : n.items_filename)+'</td>';
                            html += '<td>'+(n.upload_date == null ? "" : n.upload_date)+'</td>';
                            html += '<td>'+(n.affect_date == null ? "" : n.affect_date)+'</td>';
                            html += '<td>'+(n.expiry_date == null ? "" : n.expiry_date)+'</td>';
                            html += '<td>'+(n.totalMoney == null ? "" : n.totalMoney)+'元</td>';
                            html += '<td>'+(n.readMoney == null ? "" : n.readMoney)+'元</td>';
                            html += '<td>'+(n.daiMoney == null ? "" : n.daiMoney)+'元</td>';
                            html += '<td>'+(n.backMoney == null ? "" : n.backMoney)+'元</td>';
                            html += '<td>'+(n.perent == null ? "" : n.perent)+'%</td>';
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

<!-- 合并统计模态窗口 -->
<div class="modal fade" id="countDetailModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">合并统计</h4>
            </div>

            <div class="modal-body" style="height: 300px;margin-top: -10px;">

                <div style="margin-top: 0px;">
                    <table class="table table-hover">
                        <thead align="center">
                        <tr style="color: #B3B3B3;">
                            <th style="text-align: center;">数据统计</th>
                        </tr>
                        </thead>
                        <tbody id="bookTbody" align="center">
                            <%--<tr>
                                <td>当前共统计收费活动:3条</td>
                            </tr>
                            <tr>
                                <td>总金额:10000.11元</td>
                            </tr>
                            <tr>
                                <td>已缴金额:10000.11元</td>
                            </tr>
                            <tr>
                                <td>待缴金额:10000.11元</td>
                            </tr>
                            <tr>
                                <td>退款金额:10000.11元</td>
                            </tr>
                            <tr>
                                <td>进度:45.6%</td>
                            </tr>--%>
                        </tbody>
                    </table>
                    <div id="bookPagination2"></div>
                </div>

            </div>

            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

<div style="margin: 10px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">交易统计</h3>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 5px;">
    <button type="button" class="btn btn-default" id="countBtn" style="width:96px;background-color: #135ca1;color: white;">
        <span class="glyphicon glyphicon-th-list"></span> 合并统计
    </button>
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
                <th style="text-align: center;">总金额</th>
                <th style="text-align: center;">已缴金额</th>
                <th style="text-align: center;">待缴金额</th>
                <th style="text-align: center;">退款金额</th>
                <th style="text-align: center;">进度</th>
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