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
    <title>密码修改</title>
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
    <script language="javascript" type="text/javascript" src="js/jQuery.md5.js"></script>

    <script type="text/javascript">
        $(function() {

            checkLogin();


            //用户名文本框自动获得焦点
            $("#old_password").focus();

            //给登录按钮绑定鼠标单击事件
            $("#updateBtn").click(updatePassword);

            //给整个window对象
            //dom编程
            //window.onkeydown = function(event){}
            //jquery编程
            $(window).keydown(function(event){ //event是一个变量名，随意写，该引用指向了当前发生的事件对象。
                //所有keydown事件对象都有一个keyCode属性，用来获取键值。
                if(event.keyCode == 13){ // 回车键13，ESC键27
                    updatePassword();
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

        // 修改密码
        function updatePassword() {

            if(checkOldPassword() && checkLoginPassword() && checkCaptcha()) {

                var old_password = $.trim($("#old_password").val());
                var new_password = $.trim($("#new_password").val());
                var preNew_password = $.trim($("#preNew_password").val());

                $("#old_password").val($.md5(old_password));
                $("#new_password").val($.md5(new_password));
                var md5Old_password = $.md5(old_password);
                var md5New_password = $.md5(new_password);

                $.ajax({
                    type : "post",
                    url : "public/updatePassword",
                    data:"new_password="+md5New_password+"&old_password="+md5Old_password,
                    success : function(jsonObject) {
                        if(jsonObject.errorMessage == "OK") {
                            //登录成功跳转页面
                            alert("密码修改成功");
                            window.open("/index.jsp");
                        } else {
                            $("#showId").html(jsonObject.errorMessage);
                            // 刷新验证码
                            $("#imgCode").click();
                        }
                    }
                });
            }
        }

        // 验证原密码
        function checkOldPassword() {
            var old_password = $.trim($("#old_password").val());

            if("" == old_password) {
                $("#showId").html("请输入原密码");
                return false;
            } else {
                $("#showId").html("");
            }
            return true;
        }

        // 验证登录密码
        function checkLoginPassword() {
            var old_password = $.trim($("#old_password").val());
            var new_password = $.trim($("#new_password").val());
            var preNew_password = $.trim($("#preNew_password").val());

            // a) 密码不能为空
            if("" == new_password) {
                $("#showId").html("请输入新密码");
                return false;
            } else if(old_password == new_password) {
                $("#showId").html("新密码不能与原密码一致，请重新输入!");
                return false;
            } else if(!/^[0-9a-zA-Z]+$/.test(new_password)) {
                $("#showId").html("密码字符只可使用数字和大小写英文字母");
                return false;
            } else if(!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(new_password)) {
                $("#showId").html("密码应同时包含英文或数字");
                return false;
            } else if(new_password.length < 6 || new_password.length > 20) {
                $("#showId").html("密码长度应为6-20位");
                return false;
            } else if("" == preNew_password) {
                $("#showId").html("请再次确认密码");
                return false;
            } else if(new_password != preNew_password) {
                $("#showId").html("两次密码输入不一致");
                return false;
            } else {
                $("#showId").html("");
            }
            return true;
        }

        // 验证验证码
        function checkCaptcha() {
            var captcha = $.trim($("#captcha").val());
            var flag = true;

            // a) 图形验证码不能为空
            if("" == captcha) {
                $("#showId").html("请输入图形验证码");
                return false;
            } else {
                $.ajax({
                    type:"post",
                    url:"mch/checkCaptcha",
                    data:"captcha="+captcha,

                    async:false,

                    success:function (jsonObject) {
                        if(jsonObject.errorMessage == "OK") {
                            $("#showId").html("");
                            flag = true;
                        } else {
                            $("#showId").html(jsonObject.errorMessage);
                            flag = false;
                        }
                    },
                    error:function () {
                        $("#showId").html("系统繁忙，请稍后再试...");
                        flag = false;
                    }
                });
            }

            if(!flag) {
                return false;
            }

            return true;
        }

    </script>
    <style>
        .nameSpan{
            width: 26%;float: left;margin-top: 6px;font-weight: bold;color: white;
            font-family: 宋体;font-size: 20px;float: left;margin-left: 70px;
        }
        .layui-input{
            width: 47%;float: left;
        }
        hr{
            border-top:1px outset white;width: 100%;margin: 0px;
        }
    </style>

</head>

<body>
<%--
    页面缩放不失真总结：
        nameSpan与layui-input的宽度要设为百分比；
        最外层div的宽度要设为具体数值；
--%>
<div style="margin-top: 100px; border:1px solid #135ca1;width: 540px;margin-left: 33%;border-radius: 10px;background-color: #135ca1;">

    <h3 style="margin-top:20px;text-align: center;font-family: 微软雅黑;color:white;">密&nbsp;码&nbsp;修&nbsp;改</h3>
    <hr/>

    <div class="layui-form-item" style="margin-top: 30px;">
        <span class="nameSpan">原&nbsp;密&nbsp;码:</span>
        <input id="old_password" type="password" name="xmbh" placeholder="请输入原密码" class="layui-input">
    </div>

    <div class="layui-form-item" style="margin-top: 15px;">
        <span class="nameSpan">新&nbsp;密&nbsp;码:</span>
        <input id="new_password" type="password" name="xmbh" placeholder="请输入新密码" class="layui-input">
    </div>

    <div class="layui-form-item" style="margin-top: 15px;">
        <span class="nameSpan">确认新密码:</span>
        <input id="preNew_password" type="password" name="xmbh" placeholder="请确认新密码" class="layui-input">
    </div>

    <div class="layui-form-item" style="margin-top: 15px;">
        <span class="nameSpan">验&nbsp;证&nbsp;码:</span>
        <input id="captcha" onblur="checkCaptcha()" type="text" name="captcha" lay-verify="captcha" autocomplete="off" placeholder="点击图片刷新" class="layui-input yzm" value="" style="width: 144px;float: left;">
        &nbsp;&nbsp;
        <img src="mch/captcha" id="imgCode" onclick="this.src='mch/captcha?d='+new Date().getTime()" style="border-radius:2px;cursor:pointer;border:0; display:inline;vertical-align:middle;float: left;width: 107px;"/>
        <br/><br/>
    </div>

    <div style="height: 15px;">
        <ul><li id="showId" style="color:red;font-size:15px;margin-top:10px;margin-left:100px;margin-bottom:10px;line-height:18px;"></li></ul>
    </div><br/>

    <div class="beg-pull-right">
        <button id="updateBtn" style="width: 390px;margin-left: 70px;margin-bottom: 30px;" class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
            <span style="font-size: 20px;font-family: 微软雅黑;">确 认 修 改</span>
        </button>
    </div>

</div>

</body>

</html>