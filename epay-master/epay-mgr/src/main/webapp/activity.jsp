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
    <title>活动管理</title>
    <link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="../css/global.css" media="all">
    <link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/table.css" />
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

    <script type="text/javascript">
        $(function() {

            $(".time").datetimepicker({
                language: 'zh-CN',//显示中文
                format: 'yyyy-mm-dd hh:ii:ss',//显示格式
                minView : "hour",
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                clearBtn : true,
                pickerPosition : "top-right"
            });

            $(".time2").datetimepicker({
                language: 'zh-CN',//显示中文
                format: 'yyyy-mm-dd',//显示格式
                minView : "month",
                initialDate: new Date(),//初始化当前日期
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                clearBtn : true,
                pickerPosition : "bottom-right"
            });

            //活动名称自动获得焦点
            $("#activity_name").focus();

            displayActivity(1, 6);

            checkLogin();

            /*// 点击导入账单后，关闭模态窗口，防止重复点击
            // 点击创建活动后，关闭模态窗口，防止重复点击
            $("#createActivityBtn").click(function () {
                $("#createActivityModal").modal("hide");
            });*/


            $("#firstCheckBox2").click(function(){
                $(":checkbox[name='id2']").prop("checked", this.checked);
            });
            $("#billTbody2").on("click", $(":checkbox[name='id2']"), function(){
                $("#firstCheckBox2").prop("checked", $(":checkbox[name='id2']:checked").size() == $(":checkbox[name='id2']").size() ? "checked" : "");
            });

            // 创建活动
            $("#createActivityBtn").click(function () {
                if(checkActivityName() && checkItemsId() && checkTime()) {
                    var activity_name = $.trim($("#activity_name").val());
                    var items_id = $.trim($("#items_id").val());
                    var activity_type = $.trim($("#activity_type").val());
                    var start_time = $.trim($("#start_time").val());
                    var end_time = $.trim($("#end_time").val());
                    $.post(
                        "bill/createActivity",
                        {
                            "activity_name": activity_name,
                            "items_id": items_id,
                            "activity_type": activity_type,
                            "start_time": start_time,
                            "end_time": end_time
                        },
                        function(json){
                            if(json.ok){
                                alert("创建活动成功");
                                $("#createActivityModal").modal("hide");
                                displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                            }else{
                                alert(json.errorMessage);
                                $("#createActivityModal").modal("hide");
                            }
                        }
                    );
                }
            });

            // 删除活动
            $("#deleteBtn").click(function(){
                var checked = $(":checkbox[name='id2']:checked");
                if(checked.size() == 0){
                    alert("请选择要删除的活动!");
                }else{
                    if(window.confirm("您确定要删除活动吗?")){
                        var sendData = "";
                        $.each(checked, function(i, n){
                            sendData += "&id=" + n.value;
                        });
                        sendData = sendData.substr(1);
                        $.post(
                            "bill/deleteActivity",
                            sendData,
                            function(json){
                                if(json.deleteOK){
                                    displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                                }else{
                                    alert(json.errorMessage);
                                    displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                                }
                            }
                        );
                    }
                }
            });

            // 停止活动
            $("#stopBtn").click(function(){
                var checked = $(":checkbox[name='id2']:checked");
                if(checked.size() == 0){
                    alert("请选择要停止的活动!");
                }else{
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    $.post(
                        "bill/stopActivity",
                        sendData,
                        function(json){
                            if(json.deleteOK){
                                // alert("活动已停止");
                                displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                            }else{
                                alert(json.errorMessage);
                                displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                            }
                        }
                    );
                }
            });

            // 恢复活动
            $("#restartBtn").click(function(){
                var checked = $(":checkbox[name='id2']:checked");
                if(checked.size() == 0){
                    alert("请选择要停止的活动!");
                }else{
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    $.post(
                        "bill/restartActivity",
                        sendData,
                        function(json){
                            if(json.deleteOK){
                                // alert("活动已恢复");
                                displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                            }else{
                                alert(json.errorMessage);
                                displayActivity(1, $("#cluePagination2").bs_pagination('getOption', 'rowsPerPage'));
                            }
                        }
                    );
                }
            });


        });//————————————————————————————————————————————————————————————————————————————————————————————————————————

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        // 显示活动
        function displayActivity(pageNo, pageSize){
            $.get(
                "bill/ActivityPage",
                {
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "_" : new Date().getTime()
                },
                function(json){
                    if(json.total > 0) {
                        //{"total" : total, "pageList" : [{"fileId":"","itemsId":"","itemsName":"","itemsType":"","itemsFilename":"","uploadDate":"","affectDate":"","expiryDate":""},{},{}]}
                        var html = '';
                        $.each(json.activityList,function(i, n){
                            html += '<tr>';
                            html += '<td><input type="checkbox" name="id2" value="'+n.activityId+'"/></td>';
                            html += '<td>'+n.itemsId+'</td>';
                            html += '<td>'+n.activityName+'</td>';
                            html += '<td>'+n.activityType+'</td>';
                            html += '<td>'+n.createTime+'</td>';
                            html += '<td>'+n.startTime+'</td>';
                            html += '<td>'+n.endTime+'</td>';
                            /*可用状态 0：不可用；1：可用*/
                            html += '<td>'+(n.activityStatus == 0 ? '不可用' : '可用')+'</td>';
                            html += '</tr>';
                        });
                        $("#billTbody2").html(html);

                        //显示翻页pagination
                        var totalRows = json.total;
                        var totalPages = totalRows % pageSize == 0 ? totalRows / pageSize : parseInt(totalRows / pageSize) + 1;

                        $("#cluePagination2").bs_pagination({
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
                                displayActivity(data.currentPage , data.rowsPerPage);
                            }
                        });
                    } else {
                        var html = '<tr><td colspan="8" style="color: red; font-size: 20px;">您尚未创建活动</td></tr>';
                        $("#billTbody2").html(html);
                    }
                }
            );
        }

        // 检查活动名称
        function checkActivityName() {
            var activity_name = $.trim($("#activity_name").val());

            if("" == activity_name) {
                $("#activity_errorMsg").html("请输入活动名称");
                return false;
            } else {
                $("#activity_errorMsg").html("");
            }
            return true;
        }
        // 检查活动编号
        function checkItemsId() {
            var items_id = $.trim($("#items_id").val());

            if("" == items_id) {
                $("#activity_errorMsg").html("请输入活动编号");
                return false;
            } else {
                $("#activity_errorMsg").html("");
            }
            return true;
        }
        // 检查时间
        function checkTime() {
            var start_time = $.trim($("#start_time").val());
            var end_time = $.trim($("#end_time").val());

            if("" == start_time) {
                $("#activity_errorMsg").html("请选择活动开始时间");
                return false;
            } else if("" == end_time) {
                $("#activity_errorMsg").html("请选择活动结束时间");
                return false;
            } else if(start_time > end_time) {
                $("#activity_errorMsg").html("活动结束时间必须晚于开始时间");
                return false;
            } else {
                $("#activity_errorMsg").html("");
            }
            return true;
        }

    </script>
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

<!-- 创建活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 35%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">创建活动</h4>
            </div>

            <div class="modal-body" style="height: 300px; float: left;">

                <div  style="top: 100px;left: 70px;width: 350px">

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>活动名称:</span>
                        <input id="activity_name" type="text" autocomplete="off" placeholder="*请输入活动名称" class="layui-input">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">活动类型:</span>
                        <input id="activity_type" type="text" autocomplete="off" placeholder="-请输入活动类型" class="layui-input">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>活动编号:</span>
                        <input id="items_id" type="text" autocomplete="off" placeholder="*请输入活动编号" class="layui-input">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>开始时间:</span>
                        <input id="start_time" type="text" autocomplete="off" placeholder="*请输入开始时间" class="layui-input time2">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>结束时间:</span>
                        <input id="end_time" type="text" autocomplete="off" placeholder="*请输入结束时间" class="layui-input time2">
                    </div>
                </div>

                <button id="createActivityBtn" style="margin-top: 40px;margin-left:153px;bottom: 20px;background-color: #135ca1;width: 190px;" class="btn btn-primary">创 建</button>
                <span id="activity_errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 40px;left: 70px"></span>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

<div style="position: relative; left: 10px; top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 0px;">
        <h3>活动管理</h3>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="openActivityMotileBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#createActivityModal" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-file"></span> 创建活动</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除活动</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="stopBtn" type="button" class="btn btn-default"  style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-pause"></span> 停止活动</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="restartBtn" type="button" class="btn btn-default"  style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-forward"></span> 恢复活动</button>
        </div>

    </div>
</div>

<div style="position: relative; top: 20px; left: 0px; width: 100%; margin-top: 30px;">
    <%--<div style="width: 100%; position: absolute;top: 25px; left: 10px;">--%>
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 0px;">
        <h3 style="margin-left: 10px;">已创建活动</h3>
        <div style="position: relative;top: 0px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <th style="text-align: center;"><input type="checkbox" id="firstCheckBox2"/></th>
                    <th style="text-align: center;">编号</th>
                    <th style="text-align: center;">名称</th>
                    <th style="text-align: center;">类别</th>
                    <th style="text-align: center;">创建时间</th>
                    <th style="text-align: center;">开始时间</th>
                    <th style="text-align: center;">结束时间</th>
                    <th style="text-align: center;">状态</th>
                </tr>
                </thead>

                <tbody id="billTbody2" style="text-align: center;">

                </tbody>
            </table>
            <div id="cluePagination2"></div>
        </div>
    </div>
</div>

</body>

</html>