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
    <title>对账查询</title>
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
            // 查询所有结算列表,0表示第一页
            $("#queryBtn").click(function() {
                display(1, 6);
            });

            // 清空查询条件
            $("#clearBtn").click(function() {
                clearQuery();
            });

            queryChannelInfo();


        });//————————————————————————————————————————————————————————————————————————————————————————

        // 进入页面查询渠道信息
        function queryChannelInfo() {
            $.ajax({
                type: "get",
                url: "mch/queryMchChannel",
                success: function (jsonObject) {
                    if(jsonObject.errorMessage == "OK") {
                        var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=全部渠道=</option>";
                        $.each(jsonObject.mchChannelList, function(i,n){
                            html += "<option value="+n.channel_id+" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+n.channel_name+"</option>"
                        });
                        $("#pay_channel").html(html);
                    } else {
                        alert(jsonObject.errorMessage);
                    }
                }
            });
        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function clearQuery() {
            $("#start_time").val("");
            $("#end_time").val("");
            queryChannelInfo();
        }

        // 对账显示
        function display(pageNo, pageSize){
                var myselect = document.getElementById("pay_channel");
                var index=myselect.selectedIndex;
            var pay_channel = myselect.options[index].value;
                var myselect = document.getElementById("compare_status");
                var index=myselect.selectedIndex;
            var compare_status = myselect.options[index].value;
            var start_time = $.trim($("#start_time").val());
            var end_time = $.trim($("#end_time").val());
            $.get(
                "count/reconciliationPage",
                {
                    "pay_channel" : pay_channel,
                    "compare_status" : compare_status,
                    "start_time" : start_time,
                    "end_time" : end_time,
                    "pageNo" : pageNo,
                    "pageSize" : pageSize
                },
                function(json){
                    if(json.errorMessage == "OK") {
                        /*<th style="text-align: center;">序号</th>
                        <th style="text-align: center;">支付渠道</th>
                        <th style="text-align: center;">交易日期</th>
                        <th style="text-align: center;">对账日期</th>
                        <th style="text-align: center;">对账状态</th>
                        <th style="text-align: center;">处理状态</th>
                        <th style="text-align: center;">处理时间</th>
                        <th style="text-align: center;">交易类型</th>*/
                        var html = "";
                        $.each(json.compareHistorySrcList, function(i, n){
                            html+='<tr>';
                            html+='<td>'+(i + 1)+'</td>';
                            html+='<td>'+(n.channel_id == null ? "" : n.channel_id)+'</td>';
                            html+='<td>'+(n.txDate == null ? "" : n.txDate)+'</td>';
                            html+='<td>'+(n.compareDate == null ? "" : n.compareDate)+'</td>';
                            //对账状态(T=時差,M=匹配,Q=存疑)
                            html += '<td>'+(n.compareStatus == 'T' ? '时差' : n.compareStatus == 'M' ? '匹配' : '存疑')+'</td>';
                            html+='<td>'+(n.processStatus == null ? "" : n.processStatus)+'</td>';
                            html+='<td>'+(n.processDatetime == null ? "" : n.processDatetime)+'</td>';
                            // 00-支付 02-退款
                            html+='<td>'+(n.txType == '00' ? "支付" : "退款")+'</td>';
                            html+='</tr>';
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
                        alert("未查询到符合要求的结算信息,请确认查询条件是否正确!");
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

<!-- 汇总数据模态窗口 -->
<div class="modal fade" id="summaryModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">汇总统计</h4>
            </div>

            <div class="modal-body" style="height: 150px;margin-top: -10px;">

                <div style="margin-top: 0px;">
                    <table class="table table-hover">
                        <thead align="center">
                        <tr style="color: #B3B3B3;">
                            <th style="text-align: center;">当日数据统计</th>
                        </tr>
                        </thead>
                        <tbody id="bookTbody3" align="center">

                        </tbody>
                    </table>
                    <div id="bookPagination3"></div>
                </div>

            </div>

            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

<!-- 结算明细的模态窗口 -->
<div class="modal fade" id="checkOutDetailModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 80%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">结算明细</h4>
            </div>

            <div class="modal-body" style="height: 500px;margin-top: -10px;">
                <div style="top: 100px;width: 79%; float: left;">
                    <table class="table table-hover">
                        <thead align="center">
                        <tr style="color: #B3B3B3;">
                            <th style="text-align: center;">序号</th>
                            <th style="text-align: center;">项目编号</th>
                            <th style="text-align: center;">个人标识</th>
                            <th style="text-align: center;">姓名</th>
                            <th style="text-align: center;">货币代码</th>
                            <th style="text-align: center;">应缴金额</th>
                            <th style="text-align: center;">支付时间</th>
                            <th style="text-align: center;">支付状态</th>
                        </tr>
                        </thead>
                        <tbody id="bookTbody" align="center">

                        </tbody>
                    </table>
                    <div id="bookPagination"></div>
                </div>
                <div style="margin-top: 20px;width: 19%; float: right;">
                    <table class="table table-hover">
                        <thead align="center">
                            <tr style="color: #B3B3B3;">
                                <th style="text-align: center;">数据统计</th>
                            </tr>
                        </thead>
                        <tbody id="bookTbody2" align="center">

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

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">对账查询</h3>
    </div>
</div>

<div class="btn-toolbar" role="toolbar" style="margin:10px; margin-left: 5px;margin-top: 5px;">
    <form class="form-inline" role="form">
        <%--<div class="form-group" style="margin-right: 10px;">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">支付渠道</div>
                <input class="form-control" type="text" id="pay_channel" placeholder="">
            </div>
        </div>--%>

        <div class="form-group" style="margin-right: 10px;">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">支付渠道</div>
                <select id="pay_channel" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=全部渠道=</option>--%>
                </select>
            </div>
        </div>

        <div class="form-group" style="margin-right: 10px;">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">对账状态</div>
                <select id="compare_status" lay-search="" style="width: 200px;height: 35px;border-radius: 3px;">
                    <%--对账状态(T=時差,M=匹配,Q=存疑)--%>
                    <option value="-99" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 全部状态 ==
                    </option>
                    <option value="T" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 時差 ==
                    </option>
                    <option value="M" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 匹配 ==
                    </option>
                    <option value="Q" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;== 存疑 ==
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">对账日期</div>
                <input class="form-control time" type="text" id="start_time" placeholder="">
            </div>
        </div>

        <span style="margin-left: 10px;margin-right: 10px;">——</span>

        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">对账日期</div>
                <input class="form-control time" type="text" id="end_time" placeholder="">
            </div>
        </div>

        <%--<br/>--%>

        <%--<div class="form-group" style="margin-right: 10px;margin-top: 5px;">
            <div class="input-group">
                <div class="input-group-addon" style="width: 90px;">结算状态</div>
                <select name="status" id="moneyStates" lay-search="" style="width: 200px;height: 35px">
                    &lt;%&ndash;结算状态,0-未结算,1-结算成功,2-结算失败&ndash;%&gt;
                    <option value="-99" >————全部状态————</option>
                    <option value="0" >————未&nbsp;&nbsp;结&nbsp;&nbsp;算————</option>
                    <option value="1" >————结算成功————</option>
                    <option value="2" >————结算失败————</option>
                </select>
            </div>
        </div>--%>
        <br/>
        <button type="button" class="btn btn-default" id="queryBtn" style="width: 90px;background-color: #135ca1;color: white;margin-top: 0px;">
            <span class="glyphicon glyphicon-search"></span>&nbsp;查&nbsp;&nbsp;询
        </button>
        <button type="button" class="btn btn-default" id="clearBtn" style="width: 110px;background-color: #d9534f;color: white;margin-top: 0px;">
            <span class="glyphicon glyphicon-trash"></span>&nbsp;清空条件
        </button>
    </form>
</div>

<div style="margin: 10px;margin-top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
        <h3 style="float: left;">对账明细</h3>
    </div>
</div>

<div style="margin:10px;margin-left: 5px;width:100%; overflow:auto;">
    <table class="table table-hover">
        <thead align="center">
        <tr>
            <th style="text-align: center;">序号</th>
            <th style="text-align: center;">支付渠道</th>
            <th style="text-align: center;">交易日期</th>
            <th style="text-align: center;">对账日期</th>
            <th style="text-align: center;">对账状态</th>
            <th style="text-align: center;">处理状态</th>
            <th style="text-align: center;">处理时间</th>
            <th style="text-align: center;">交易类型</th>
        </tr>
        </thead>
        <tbody id="billTbody" align="center">

        </tbody>

    </table>

    <div id="cluePagination"></div>

</div>
</body>

</html>