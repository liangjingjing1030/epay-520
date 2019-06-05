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

            display(1, 5);

            checkLogin();

            /*// 点击导入账单后，关闭模态窗口，防止重复点击
            $("#importFileBtn").click(function () {
                $("#importClueModal").modal("hide");
            });*/

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

            // 删除账单
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

            // 导入账单模态窗口进入前查询活动
            $("#importAccountMotileBtn").click(function () {
                // 查询当前商户下所有的活动的项目编号
                $.ajax({
                    type: "get",
                    url: "bill/queryAllAccountItemsId",
                    data: {
                        "_" : new Date().getTime()// 去掉缓存
                    },
                    //请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器
                    async:false,
                    success: function (jsonObject) {
                        if(jsonObject.OK) {
                            var html="<option value='' disabled selected hidden style='height: 30px;'>&nbsp;&nbsp;-请选择项目编号-</option>";
                            $.each(jsonObject.itemsIdList, function(i,n){
                                html += "<option >&nbsp;&nbsp;"+n+"</option>"
                            });
                            $("#xmbh").html(html);
                        } else {
                            alert(jsonObject.errorMessage);
                        }
                    }
                });
                // 显示添加缴费明细模态窗口
                $("#addAccountBookModal").modal("show");
            });
            
            // 模板编辑
            $("#modelEditBtn").click(function () {
                $("#modelEditModal").modal("show");
            });

            // 模板编辑确认按钮
            $("#editModelBtn").click(function () {
                if(checkUserId() && checkItemsMoney() && checkSelect1() && checkSelect2() && checkSelect3() && checkSelect4() && checkSelect5()
                && checkSelect6() && checkSelect7() && checkSelect8() && checkSelect9() && checkSelect10() && checkPosition()) {
                    var user_id = $.trim($("#user_id").val());
                    var userIdPosition = $.trim($("#userIdPosition").val());
                    var items_money = $.trim($("#items_money").val());
                    var items_money_position = $.trim($("#items_money_position").val());
                    var select1 = $.trim($("#select1").val());
                    var position1 = $.trim($("#position1").val());
                    var select2 = $.trim($("#select2").val());
                    var position2 = $.trim($("#position2").val());
                    var select3 = $.trim($("#select3").val());
                    var position3 = $.trim($("#position3").val());
                    var select4 = $.trim($("#select4").val());
                    var position4 = $.trim($("#position4").val());
                    var select5 = $.trim($("#select5").val());
                    var position5 = $.trim($("#position5").val());
                    var select6 = $.trim($("#select6").val());
                    var position6 = $.trim($("#position6").val());
                    var select7 = $.trim($("#select7").val());
                    var position7 = $.trim($("#position7").val());
                    var select8 = $.trim($("#select8").val());
                    var position8 = $.trim($("#position8").val());
                    var select9 = $.trim($("#select9").val());
                    var position9 = $.trim($("#position9").val());
                    var select10 = $.trim($("#select10").val());
                    var position10 = $.trim($("#position10").val());
                    var sendData = "user_id=" + user_id +
                                   "&userIdPosition=" + userIdPosition +
                                   "&items_money=" + items_money +
                                   "&items_money_position=" + items_money_position +
                                   "&select1=" + select1 +
                                   "&select2=" + select2 +
                                   "&select3=" + select3 +
                                   "&select4=" + select4 +
                                   "&select5=" + select5 +
                                   "&select6=" + select6 +
                                   "&select7=" + select7 +
                                   "&select8=" + select8 +
                                   "&select9=" + select9 +
                                   "&select10=" + select10 +
                                   "&position1=" + position1 +
                                   "&position2=" + position2 +
                                   "&position3=" + position3 +
                                   "&position4=" + position4 +
                                   "&position5=" + position5 +
                                   "&position6=" + position6 +
                                   "&position7=" + position7 +
                                   "&position8=" + position8 +
                                   "&position9=" + position9 +
                                   "&position10=" + position10;
                    window.location.href = "bill/modelEdit?" + sendData;
                    $("#modelEditModal").modal("hide");
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

        // 显示账单文件
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

        // 检查模板编辑——唯一标识
        function checkUserId() {
            var user_id = $.trim($("#user_id").val());
            var userIdPosition = $.trim($("#userIdPosition").val());

            if("" == user_id) {
                $("#editModelErrorMsg").html("请输入能唯一标识一个客户的列名");
                return false;
            } else if("" == userIdPosition) {
                $("#editModelErrorMsg").html("请输入唯一标识列位置");
                return false;
            } else if(!/^[0-9]*$/.test(userIdPosition)) {
                $("#editModelErrorMsg").html("请输入正确的唯一标识列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——应缴金额
        function checkItemsMoney() {
            var items_money = $.trim($("#items_money").val());
            var items_money_position = $.trim($("#items_money_position").val());

            if("" == items_money) {
                $("#editModelErrorMsg").html("请输入能代表 “应缴金额” 的列名");
                return false;
            } else if("" == items_money_position) {
                $("#editModelErrorMsg").html("请输入应缴金额列位置");
                return false;
            } else if(!/^[0-9]*$/.test(items_money_position)) {
                $("#editModelErrorMsg").html("请输入正确的应缴金额列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填1
        function checkSelect1() {
            var select1 = $.trim($("#select1").val());
            var position1 = $.trim($("#position1").val());
            if("" != select1 && "" == position1) {
                $("#editModelErrorMsg").html("请输入选填1的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填2
        function checkSelect2() {
            var select2 = $.trim($("#select2").val());
            var position2 = $.trim($("#position2").val());
            if("" != select2 && "" == position2) {
                $("#editModelErrorMsg").html("请输入选填2的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填3
        function checkSelect3() {
            var select3 = $.trim($("#select3").val());
            var position3 = $.trim($("#position3").val());
            if("" != select3 && "" == position3) {
                $("#editModelErrorMsg").html("请输入选填3的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填4
        function checkSelect4() {
            var select4 = $.trim($("#select4").val());
            var position4 = $.trim($("#position4").val());
            if("" != select4 && "" == position4) {
                $("#editModelErrorMsg").html("请输入选填4的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填5
        function checkSelect5() {
            var select5 = $.trim($("#select5").val());
            var position5 = $.trim($("#position5").val());
            if("" != select5 && "" == position5) {
                $("#editModelErrorMsg").html("请输入选填5的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填6
        function checkSelect6() {
            var select6 = $.trim($("#select6").val());
            var position6 = $.trim($("#position6").val());
            if("" != select6 && "" == position6) {
                $("#editModelErrorMsg").html("请输入选填6的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填7
        function checkSelect7() {
            var select7 = $.trim($("#select7").val());
            var position7 = $.trim($("#position7").val());
            if("" != select7 && "" == position7) {
                $("#editModelErrorMsg").html("请输入选填7的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填8
        function checkSelect8() {
            var select8 = $.trim($("#select8").val());
            var position8 = $.trim($("#position8").val());
            if("" != select8 && "" == position8) {
                $("#editModelErrorMsg").html("请输入选填8的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填9
        function checkSelect9() {
            var select9 = $.trim($("#select9").val());
            var position9 = $.trim($("#position9").val());
            if("" != select9 && "" == position9) {
                $("#editModelErrorMsg").html("请输入选填9的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }
        // 检查模板编辑——选填10
        function checkSelect10() {
            var select10 = $.trim($("#select10").val());
            var position10 = $.trim($("#position10").val());
            if("" != select10 && "" == position10) {
                $("#editModelErrorMsg").html("请输入选填10的列位置");
                return false;
            } else {
                $("#editModelErrorMsg").html("");
            }
            return true;
        }

        // 检查模板编辑——所有位置
        function checkPosition() {
            var userIdPosition = $.trim($("#userIdPosition").val());
            var items_money_position = $.trim($("#items_money_position").val());
            var position1 = $.trim($("#position1").val());
            var position2 = $.trim($("#position2").val());
            var position3 = $.trim($("#position3").val());
            var position4 = $.trim($("#position4").val());
            var position5 = $.trim($("#position5").val());
            var position6 = $.trim($("#position6").val());
            var position7 = $.trim($("#position7").val());
            var position8 = $.trim($("#position8").val());
            var position9 = $.trim($("#position9").val());
            var position10 = $.trim($("#position10").val());

            if(userIdPosition == items_money_position) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、应缴金额!");
                return false;
            } else if(position1 != "" &&  userIdPosition == position1) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填1!");
                return false;
            } else if(position2 != "" &&  userIdPosition == position2) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填2!");
                return false;
            } else if(position3 != "" &&  userIdPosition == position3) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填3!");
                return false;
            } else if(position4 != "" &&  userIdPosition == position4) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填4!");
                return false;
            } else if(position5 != "" &&  userIdPosition == position5) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填5!");
                return false;
            } else if(position6 != "" &&  userIdPosition == position6) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填6!");
                return false;
            } else if(position7 != "" &&  userIdPosition == position7) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填7!");
                return false;
            } else if(position8 != "" &&  userIdPosition == position8) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填8!");
                return false;
            } else if(position9 != "" &&  userIdPosition == position9) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填9!");
                return false;
            } else if(position10 != "" &&  userIdPosition == position10) {
                $("#editModelErrorMsg").html("列位置重复:唯一标识、选填10!");
                return false;
            }

              else if(position1 != "" &&  items_money_position == position1) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填1!");
                return false;
            } else if(position2 != "" &&  items_money_position == position2) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填2!");
                return false;
            } else if(position3 != "" &&  items_money_position == position3) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填3!");
                return false;
            } else if(position4 != "" &&  items_money_position == position4) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填4!");
                return false;
            } else if(position5 != "" &&  items_money_position == position5) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填5!");
                return false;
            } else if(position6 != "" &&  items_money_position == position6) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填6!");
                return false;
            } else if(position7 != "" &&  items_money_position == position7) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填7!");
                return false;
            } else if(position8 != "" &&  items_money_position == position8) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填8!");
                return false;
            } else if(position9 != "" &&  items_money_position == position9) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填9!");
                return false;
            } else if(position10 != "" &&  items_money_position == position10) {
                $("#editModelErrorMsg").html("列位置重复:应缴金额、选填10!");
                return false;
            }

              else if(position1 != "" && position2 != "" && position1 == position2) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填2!");
                return false;
            } else if(position1 != "" && position3 != "" && position1 == position3) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填3!");
                return false;
            } else if(position1 != "" && position4 != "" && position1 == position4) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填4!");
                return false;
            } else if(position1 != "" && position5 != "" && position1 == position5) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填5!");
                return false;
            } else if(position1 != "" && position6 != "" && position1 == position6) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填6!");
                return false;
            } else if(position1 != "" && position7 != "" && position1 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填7!");
                return false;
            } else if(position1 != "" && position8 != "" && position1 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填8!");
                return false;
            } else if(position1 != "" && position9 != "" && position1 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填9!");
                return false;
            } else if(position1 != "" && position10 != "" && position1 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填1、选填10!");
                return false;
            }

              else if(position2 != "" && position3 != "" && position2 == position3) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填3!");
                return false;
            } else if(position2 != "" && position4 != "" && position2 == position4) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填4!");
                return false;
            } else if(position2 != "" && position5 != "" && position2 == position5) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填5!");
                return false;
            } else if(position2 != "" && position6 != "" && position2 == position6) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填6!");
                return false;
            } else if(position2 != "" && position7 != "" && position2 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填7!");
                return false;
            } else if(position2 != "" && position8 != "" && position2 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填8!");
                return false;
            } else if(position2 != "" && position9 != "" && position2 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填9!");
                return false;
            } else if(position2 != "" && position10 != "" && position2 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填2、选填10!");
                return false;
            }

              else if(position3 != "" && position4 != "" && position3 == position4) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填4!");
                return false;
            } else if(position3 != "" && position5 != "" && position3 == position5) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填5!");
                return false;
            } else if(position3 != "" && position6 != "" && position3 == position6) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填6!");
                return false;
            } else if(position3 != "" && position7 != "" && position3 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填7!");
                return false;
            } else if(position3 != "" && position8 != "" && position3 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填8!");
                return false;
            } else if(position3 != "" && position9 != "" && position3 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填9!");
                return false;
            } else if(position3 != "" && position10 != "" && position3 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填3、选填10!");
                return false;
            }

              else if(position4 != "" && position5 != "" && position4 == position5) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填5!");
                return false;
            } else if(position4 != "" && position6 != "" && position4 == position6) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填6!");
                return false;
            } else if(position4 != "" && position7 != "" && position4 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填7!");
                return false;
            } else if(position4 != "" && position8 != "" && position4 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填8!");
                return false;
            } else if(position4 != "" && position9 != "" && position4 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填9!");
                return false;
            } else if(position4 != "" && position10 != "" && position4 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填4、选填10!");
                return false;
            }

              else if(position5 != "" && position6 != "" && position5 == position6) {
                $("#editModelErrorMsg").html("列位置重复:选填5、选填6!");
                return false;
            } else if(position5 != "" && position7 != "" && position5 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填5、选填7!");
                return false;
            } else if(position5 != "" && position8 != "" && position5 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填5、选填8!");
                return false;
            } else if(position5 != "" && position9 != "" && position5 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填5、选填9!");
                return false;
            } else if(position5 != "" && position10 != "" && position5 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填5、选填10!");
                return false;
            }

              else if(position6 != "" && position7 != "" && position6 == position7) {
                $("#editModelErrorMsg").html("列位置重复:选填6、选填7!");
                return false;
            } else if(position6 != "" && position8 != "" && position6 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填6、选填8!");
                return false;
            } else if(position6 != "" && position9 != "" && position6 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填6、选填9!");
                return false;
            } else if(position6 != "" && position10 != "" && position6 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填6、选填10!");
                return false;
            }

              else if(position7 != "" && position8 != "" && position7 == position8) {
                $("#editModelErrorMsg").html("列位置重复:选填7、选填8!");
                return false;
            } else if(position7 != "" && position9 != "" && position7 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填7、选填9!");
                return false;
            } else if(position7 != "" && position10 != "" && position7 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填7、选填10!");
                return false;
            }

              else if(position8 != "" && position9 != "" && position8 == position9) {
                $("#editModelErrorMsg").html("列位置重复:选填8、选填9!");
                return false;
            } else if(position8 != "" && position10 != "" && position8 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填8、选填10!");
                return false;
            }

              else if(position9 != "" && position10 != "" && position9 == position10) {
                $("#editModelErrorMsg").html("列位置重复:选填9、选填10!");
                return false;
            }

            else {
                $("#editModelErrorMsg").html("");
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

<!-- 模板编辑的模态窗口 -->
<div class="modal fade" id="modelEditModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 65%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">模板编辑</h4>
            </div>

            <div class="modal-body" style="height: 600px; float: left;">

                <div  style="top: 100px;left: 70px;">

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>必填:&nbsp;</span>
                        <input id="user_id" type="text" autocomplete="off" placeholder="*请输入能唯一标识一个客户的列名,如身份证号、学号……" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="userIdPosition" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan"><span class="xingSpan">✲</span>必填:&nbsp;</span>
                        <input id="items_money" type="text" autocomplete="off" placeholder="*请输入能代表 “应缴金额” 的列名" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="items_money_position" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填1:&nbsp;</span>
                        <input id="select1" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position1" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填2:&nbsp;</span>
                        <input id="select2" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position2" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填3:&nbsp;</span>
                        <input id="select3" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position3" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填4:&nbsp;</span>
                        <input id="select4" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position4" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填5:&nbsp;</span>
                        <input id="select5" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position5" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填6:&nbsp;</span>
                        <input id="select6" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position6" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填7:&nbsp;</span>
                        <input id="select7" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position7" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填8:&nbsp;</span>
                        <input id="select8" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position8" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填9:&nbsp;</span>
                        <input id="select9" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position9" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>

                    <div class="layui-form-item">
                        <span class="nameSpan">选填10:&nbsp;</span>
                        <input id="select10" type="text" autocomplete="off" placeholder="-请输入选填列内容" class="layui-input" style="width: 380px;float: left;">
                        <span class="nameSpan" style="margin-left: -30px;">列位置:&nbsp;</span>
                        <input id="position10" type="text" autocomplete="off" placeholder="-请输入本列为第几列" class="layui-input" style="width: 170px;">
                    </div>


                </div>

                <button id="editModelBtn" style="margin-top: 40px;margin-left:580px;bottom: 20px;background-color: #135ca1;width: 170px;" class="btn btn-primary">确 定</button>
                <span id="editModelErrorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 40px;left: 100px"></span>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

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

                <form action="/bill/importAccountFile" onsubmit="return check()" enctype="multipart/form-data" method="post">

                    <input type="file" name="file" style="margin-left: 50px;">
                    <span style="position: relative;top: 7px;margin-left: 50px;">
                        <small style="color: red;">[仅支持.xls或.xlsx格式] [项目编号与个人标识相同的账单不会重复导入]</small>
                    </span>

                    <div  style="top: 100px;left: 70px;width: 350px">

                        <div class="layui-form-item" style="margin-top: 15px;">
                            <span class="nameSpan"><span class="xingSpan">✲</span>项目编号:</span>
                            <%--<input id="xmbh" type="text" name="xmbh" placeholder="*请输入项目编号" class="layui-input">--%>
                            <select id="xmbh" name="xmbh" style="float: right;width: 190px; height: 30px; border-radius: 3px;">

                            </select>
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

                    <button id="importFileBtn" style="margin-top: 40px;margin-left:155px;width:190px;bottom: 20px;left: 70px;background-color: #135ca1" class="btn btn-primary">导入文件</button>
                    <span id="errorMsg" style="color: red;font-size: 16px;position: absolute;bottom: 50px;left: 70px"></span>
                </form>
            </div>
            <div class="modal-footer" style="left: 200px; bottom: -400px">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #135ca1;color: white;">关闭</button>--%>
            </div>
        </div>
    </div>
</div>

<div style="position: relative; left: 10px; top: 0px;">
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 0px;">
        <h3>点击导入/删除账单</h3>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="importAccountMotileBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#importClueModal" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-import"></span> 导入账单</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="downLoadBtn" type="button" class="btn btn-default" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-download-alt"></span> 下载账单模板</button>
        </div>

        <div class="btn-group" style="position: relative; top: 18%;">
            <button id="modelEditBtn" type="button" class="btn btn-default" style="background-color: #135ca1;color: white;"><span class="glyphicon glyphicon-edit"></span> 模板编辑</button>
        </div>

    </div>
</div>

<div style="position: relative; top: 20px; left: 0px; width: 100%; margin-top: 50px;">
    <%--<div style="width: 100%; position: absolute;top: 25px; left: 10px;">--%>
    <div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px;">
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