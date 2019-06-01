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

            display(1, 5);

            checkLogin();

            // 点击导入账单后，关闭模态窗口，防止重复点击
            $("#importFileBtn").click(function () {
                $("#importClueModal").modal("hide");
            });

            // 下载账单模板
            $("#downLoadBtn").click(function() {
                downLoadAccountFileModel();
                // $("#openMotileBtn").disabled = true;
            });

            $("#firstCheckBox").click(function(){
                $(":checkbox[name='id']").prop("checked", this.checked);
            });

            $("#billTbody").on("click", $(":checkbox[name='id']"), function(){
                $("#firstCheckBox").prop("checked", $(":checkbox[name='id']:checked").size() == $(":checkbox[name='id']").size() ? "checked" : "");
            });

            $("#deleteBtn").click(function(){
                var checked = $(":checkbox[name='id']:checked");
                if(checked.size() == 0){
                    alert("请选择要删除的数据!");
                }else{
                    // 判断账单是否可以删除
                    var sendData = "";
                    $.each(checked, function(i, n){
                        sendData += "&id=" + n.value;
                    });
                    sendData = sendData.substr(1);
                    $.get(
                        "bill/checkIfCanDeleteAccountFile",
                        sendData,
                        function (json) {
                            if(json.ok) {
                                if(window.confirm("您确定要删除数据吗?")){
                                    $.post(
                                        "bill/deleteAccountFile",
                                        sendData,
                                        function(json){
                                            if(json.deleteOK){
                                                display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                                            }else{
                                                alert(json.errorMessage);
                                                display(1, $("#cluePagination").bs_pagination('getOption', 'rowsPerPage'));
                                            }
                                        }
                                    );
                                }
                            } else {
                                alert(json.errorMessage);
                            }
                        }
                    );
                }
            });

        });//————————————————————————————————————————————————————————————————————————————————————————————————————————

        // 下载账单模板
        function downLoadAccountFileModel() {
            document.location.href = "bill/downLoadAccountFileModel";
        }

        function checkLogin() {
            var loginName = "${user.loginName}"
            if(loginName == null || loginName == "") {
                alert("请先登录！")
                window.open("/login.jsp");
            }
        }

        //检查账单参数
        function check() {
            var xmbh = $("#xmbh").val();
            var xmmc = $("#xmmc").val();
            var shengxsj = $("#shengxsj").val();
            var shixsj = $("#shixsj").val();
            /*var file = $("#file")[0].files[0].name;

            if(file == null) {
                $("#errorMsg").text("请选择要上传的账单！");
                return false;
            }*/

            if(xmbh ==  null || xmbh == ''){
                $("#errorMsg").text("项目编号不能为空！");
                return false;
            }
            if(xmmc ==  null || xmmc == ''){
                $("#errorMsg").text("项目名称不能为空！");
                return false;
            }
            if(shengxsj ==  null || shengxsj == ''){
                $("#errorMsg").text("请设置账单生效时间！");
                return false;
            }
            if(shixsj ==  null || shixsj == ''){
                $("#errorMsg").text("请设置账单失效时间！");
                return false;
            }
            if(shengxsj >= shixsj) {
                $("#errorMsg").text("失效时间必须晚于生效时间！");
                return false;
            }
            return true;
            // 判断是否重复上传
            /*var a = 1;
            $.ajax({
                type : "get",
                url : "bill/isDuplicate",
                data : {
                    "xmbh" : xmbh
                },
                async: false,
                success : function(json){
                    if(json.ok) {
                        $("#errorMsg").text("");
                    } else {
                        $("#errorMsg").text(json.errorMessage);
                        a = 2;
                    }
                },
                fail : function(data){
                    $("#errorMsg").text(json.errorMessage);
                    a = 2;
                }
            });
            if(a == 1) {
                return true;
            } else {
                return false;
            }*/
        }

        function display(pageNo, pageSize){
            $.get(
                "bill/accountFilePage",
                {
                    "pageNo" : pageNo,
                    "pageSize" : pageSize,
                    "_" : new Date().getTime()
                },
                function(json){
                    if(json.total > 0) {
                        //{"total" : total, "pageList" : [{"fileId":"","itemsId":"","itemsName":"","itemsType":"","itemsFilename":"","uploadDate":"","affectDate":"","expiryDate":""},{},{}]}
                        var html = '';

                        $.each(json.accountFileList,function(i, n){
                            html += '<tr>';
                            html += '<td><input type="checkbox" name="id" value="'+n.file_id+'"/></td>';
                            html += '<td>'+n.items_id+'</td>';
                            html += '<td>'+n.items_name+'</td>';
                            html += '<td>'+n.items_type+'</td>';
                            html += '<td>'+n.items_filename+'</td>';
                            html += '<td>'+n.upload_date+'</td>';
                            html += '<td>'+n.affect_date+'</td>';
                            html += '<td>'+n.expiry_date+'</td>';
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
                        var html = '<tr><td colspan="8" style="color: red; font-size: 20px;">您未上传账单,请上传!</td></tr>';
                        $("#billTbody").html(html);
                    }
                }
            );
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
        /* .fileinput-button {
             position: relative;
             display: inline-block;
             overflow: hidden;
             margin-left: 50px;
             background-color: #135ca1;
         }
         .fileinput-button input{
             position: absolute;
             left: 0px;
             top: 0px;
             opacity: 0;
             -ms-filter: 'alpha(opacity=0)';
             margin-left: 50px;
         }*/
    </style>

</head>

<body>

<!-- 导入账单的模态窗口 -->
<div class="modal fade" id="importClueModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">导入账单</h4>
            </div>

            <div class="modal-body" style="height: 350px; float: left;">

                <%--<span class="btn btn-success fileinput-button">
                    <span>上传文件</span>
                    <input type="file" name="file" style="margin-left: 50px;">
                </span>
                <span style="position: relative;top: 7px;margin-left: 50px;">
                    <small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
                </span>--%>

                <form action="/bill/importAccountFile" onsubmit="return check()" enctype="multipart/form-data" method="post">

                    <input type="file" name="file" style="margin-left: 50px;">
                    <span style="position: relative;top: 7px;margin-left: 50px;">
                        <small style="color: red;">[仅支持.xls或.xlsx格式] [项目编号相同时,个人标识也相同的账单不会重复插入]</small>
                    </span>

                    <div  style="top: 100px;left: 70px;width: 350px">

                        <div class="layui-form-item" style="margin-top: 15px;">
                            <span class="nameSpan"><span class="xingSpan">✲</span>项目编号:</span>
                            <input id="xmbh" type="text" name="xmbh" placeholder="*请输入项目编号" class="layui-input">
                        </div>

                        <div class="layui-form-item">
                            <span class="nameSpan"><span class="xingSpan">✲</span>项目名称:</span>
                            <input id="xmmc" type="text" name="xmmc" autocomplete="off" placeholder="*请输入项目名称" class="layui-input">
                        </div>

                        <div class="layui-form-item">
                            <span class="nameSpan">项目类别:</span>
                            <input id="xmlb" type="text" name="xmlb" autocomplete="off" placeholder="-请输入项目类别" class="layui-input">
                        </div>

                        <div class="layui-form-item">
                            <span class="nameSpan"><span class="xingSpan">✲</span>生效时间:</span>
                            <input id="shengxsj" type="text" name="shengxsj" autocomplete="off" placeholder="*请输入生效时间" class="layui-input time">
                        </div>

                        <div class="layui-form-item">
                            <span class="nameSpan"><span class="xingSpan">✲</span>失效时间:</span>
                            <input id="shixsj" type="text" name="shixsj" autocomplete="off" placeholder="*请输入失效时间" class="layui-input time">
                        </div>
                    </div>

                    <button id="importFileBtn" style="margin-top: 40px;margin-left:50px;bottom: 20px;left: 70px;background-color: #135ca1" class="btn btn-primary">导入文件</button>
                    <span id="errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 50px;left: 70px"></span>
                </form>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>
            </div>
        </div>
    </div>
</div>

<div style="position: relative; left: 10px; top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 0px;">
        <h3>点击导入/删除账单</h3>
        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="openMotileBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#importClueModal" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-import"></span> 导入</button>
        </div>
        <div class="btn-group" style="position: relative; top: 18%;">
            <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
        </div>
        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="downLoadBtn" type="button" class="btn btn-default" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-download-alt"></span> 下载账单模板</button>
        </div>
    </div>
</div>

<div style="position: relative; top: 20px; left: 0px; width: 100%; height: 100%;margin-top: 50px;">
    <%--<div style="width: 100%; position: absolute;top: 25px; left: 10px;">--%>
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 0px;">
        <h3 style="margin-left: 10px;">已导入账单</h3>
        <div style="position: relative;top: 0px;">
            <table class="table table-hover">
                <thead>
                <tr style="color: #B3B3B3;">
                    <th style="text-align: center;"><input type="checkbox" id="firstCheckBox"/></th>
                    <th style="text-align: center;">项目编号</th>
                    <th style="text-align: center;">项目名称</th>
                    <th style="text-align: center;">项目类别</th>
                    <th style="text-align: center;">文件名称</th>
                    <th style="text-align: center;">上传日期</th>
                    <th style="text-align: center;">生效时间</th>
                    <th style="text-align: center;">失效时间</th>
                </tr>
                </thead>

                <tbody id="billTbody" style="text-align: center;">

                </tbody>

            </table>

            <div id="cluePagination"></div>

        </div>

    </div>
</div>

</body>

</html>