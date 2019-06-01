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

    <script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>

    <script type="text/javascript">
        $(function() {

            checkLogin();

            // 加载页面前首先查询出当前商户下的所有上传的项目编号
            //queryItemsId();

            // 查询按钮
            $("#queryKey").click(function() {
                showKey();
            });

        });//————————————————————————————————————————————————————————————————————————————————————————

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        function showKey() {
            $.get(
                "public/queryKey",
                {"_" : new Date().getTime()},// 去掉缓存
                function (json) {
                    var html = "";
                    $.each(json, function(i, n){
                        // alert("键：" + key + ",值 ：{姓名："+ Data[key].name+", 年龄："+Data[key].age+"}");
                        html+='<div class="layui-form-item">';
                        html+='<span class="keySpan">请求私钥:</span>';
                        html+='<span class="valueSpan">'+(n.reqKey == null ? "" : n.reqKey)+'</span>';
                        html+='</div>';
                        html+='<hr class="myHr">';
                        html+='<div class="layui-form-item">';
                        html+='<span class="keySpan">响应私钥:</span>';
                        html+='<span class="valueSpan">'+(n.resKey == null ? "" : n.resKey)+'</span>';
                        html+='</div>';
                        html+='<hr class="myHr">';
                    });
                    $("#keyDiv").html(html);
                }
            );
        }

    </script>
    <style>
        .layui-form-item{
            margin:0px; border:0; padding:0;margin-left: 50px;
        }
        .keySpan{
            float: left; font-size: 25px;font-family: 宋体;width: 150px;
            text-align: right;margin-top: 20px;margin-left: 22px;font-weight: bold;
        }
        .valueSpan{
            float: left; font-size: 25px;font-family: 宋体;
            text-align: right;margin-top: 20px;
        }
        .myHr{
            border-top:1px outset black; margin-left: 100px;margin-top: 0px;bottom: 0px;width: 600px;
        }
    </style>

</head>

<body>

    <div role="document" style="width: 55%;">
        <div>
            <div>
                <button id="queryKey" type="button" class="btn btn-default" style="margin-left:100px;margin-top:50px;background-color: #135ca1;color: white;">点击查询密钥</button>
            </div>

            <div style="height: 420px; float: left;">

                <div id="keyDiv" style="top: 100px;left: 50px;">
                    <%--<div class="layui-form-item">
                        <span class="keySpan">请求私钥:</span>
                        <span class="valueSpan"></span>
                    </div><hr class="myHr">

                    <div class="layui-form-item">
                        <span class="keySpan">响应私钥:</span>
                        <span class="valueSpan"></span>
                    </div><hr class="myHr">--%>
                </div>

            </div>

            <%--<div class="modal-footer" style="left: 200px; bottom: -400px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>
            </div>--%>
        </div>
    </div>

</body>

</html>