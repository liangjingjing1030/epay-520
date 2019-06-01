<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--fmt为jstl的格式化标签--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--fn为jstl中的功能函数--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<head>
		<meta charset="UTF-8">
		<title>支付订单</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
        <%--add by GaoLiang--%>
        <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
        <script type="text/javascript" src="../js/jquery-1.11.1-min.js"></script>

        <link href="../jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="../jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

        <link href="../jquery/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="../jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
        <script type="text/javascript" src="../jquery/bs_pagination/localization/en.js"></script>

        <script type="text/javascript">

            $(function () {

                $("#search1").click(function () {
                    display(1, 5);
                });

                checkLogin();

            });

            function checkLogin() {
                var loginName = "${user.loginName}"
                if(loginName == null || loginName == "") {
                    alert("请先登录！")
                    window.open("/login.jsp");
                }
            }

            function display(pageNo, pageSize){
                var itemsId = $.trim($("#itemsId").val());
                var itemsName = $.trim($("#itemsName").val());
                var fileName = $.trim($("#fileName").val());
                var userId = $.trim($("#userId").val());
                var userName = $.trim($("#userName").val());
                var myselect = document.getElementById("orderStates");
                var index=myselect.selectedIndex;
                var status = myselect.options[index].value;
                // alert(status)
                $.get(
                    "mch/accountPage",
                    {
                        "itemsId" : itemsId,
                        "itemsName" : itemsName,
                        "fileName" : fileName,
                        "userId" : userId,
                        "userName" : userName,
                        "status" : status,
                        "pageNo" : pageNo,
                        "pageSize" : pageSize
                    },
                    function(json){
                        //{"total" : total, "pageList" : [{"mchId":"","itemsId":"","itemsName":"","itemsFilename":"","uploadDate":"","userName":"","userId":"","payNumber":"","payTime":"","status":""},{},{}]}
                        if(0 == json.total) {
                            alert("未查询到任何账单！");
                        }
                        var html = "";
                        $.each(json.pageList, function(i, n){
                            html += '<tr>';
                            html += '<td>'+(i + 1)+'</td>';
                            html += '<td>'+n.itemsId+'</td>';
                            html += '<td>'+n.accountFile.itemsName+'</td>';
                            html += '<td>'+n.accountFile.itemsFilename+'</td>';
                            html += '<td>'+n.accountFile.uploadDate+'</td>';
                            html += '<td>'+n.userName+'</td>';
                            html += '<td>'+n.userId+'</td>';
                            html += '<td>'+n.payNumber+'</td>';
                            html += '<td>'+(n.payTime == null ? "" : n.payTime)+'</td>';
                            //html += "<option value='"+n.id+"' "+(currentUserId == n.id ? "selected" : "")+">"+n.name+"</option>"
                            html += '<td>'+(n.payOrder.status == '0' ? '待支付' : n.payOrder.status == '1' ? '支付中' : n.payOrder.status == '2' ? '支付成功' : '支付成功')+'</td>';
                            html += '</tr>';
                        });
                        $("#content").html(html);

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
                    }
                );
            }

        </script>
	</head>

	<body>
		<div class="admin-main">

            <blockquote class="layui-elem-quote" style="height: 100px">
                <%--<div class="layui-btn layui-btn-small" id=""></div>--%>
                <div class="layui-form" style="float:left;">
                    <div class="layui-form-item" style="margin:0;">

                        <label class="layui-form-label" style="width: 100px">项目编号</label>
                        <div class="layui-input-inline" style="width: 150px">
                            <input type="text" name="itemsId" id="itemsId" autocomplete="off" class="layui-input">
                        </div>

                        <label class="layui-form-label" style="width: 100px">项目名称</label>
                        <div class="layui-input-inline" style="width: 150px">
                            <input type="text" name="itemsName" id="itemsName" autocomplete="off" class="layui-input">
                        </div>

                        <label class="layui-form-label" style="width: 100px">文件名称</label>
                        <div class="layui-input-inline" style="width: 150px">
                            <input type="text" name="fileName" id="fileName" autocomplete="off" class="layui-input">
                        </div>

                        <br/><br/>

						<label class="layui-form-label" style="width: 100px">个人标识</label>
                        <div class="layui-input-inline" style="width: 150px">
                            <input type="text" name="userId" id="userId" autocomplete="off" class="layui-input">
                        </div>

                        <label class="layui-form-label" style="width: 100px">客户姓名</label>
                        <div class="layui-input-inline" style="width: 150px">
                            <input type="text" name="userName" id="userName" autocomplete="off" class="layui-input">
                        </div>

                        <%--0-订单生成,1-支付成功,2-支付失败,3-已退款--%>
                        <label class="layui-form-label" style="width: 100px">订单状态</label>
                        <div class="layui-input-inline" style="width: 150px;">
                            <select name="status" id="orderStates" lay-search="">
                                <option value="-99">所有状态</option>
                                <option value="0" >待支付</option><%--订单生成--%>
                                <option value="1" >支付成功</option>
                                <option value="2" >支付失败</option>
                                <option value="3" >已退款</option>
                            </select>
                        </div>

                        <div class="layui-form-mid layui-word-aux" style="padding:0;">
                            <button id="search1" lay-filter="search" class="layui-btn" lay-submit>
                                <i class="fa fa-search" aria-hidden="true"></i> 查询
                            </button>
                        </div>
                    </div>
                </div>
            </blockquote>

			<fieldset class="layui-elem-field" style="float: left; width: 100%">
				<legend>订单列表</legend>
				<div class="layui-field-box layui-form">
					<table class="layui-table admin-table">
						<thead>
							<tr>
								<th style="width: 30px;"><input type="checkbox" lay-filter="allselector" lay-skin="primary"></th>
                                <%--accountFile--%>
                                <th>项目编号</th>
                                <th>项目名称</th>
                                <th>文件名称</th>
                                <th>上传日期</th>
                                <%--accountBook--%>
                                <th>客户姓名</th>
                                <th>个人标识</th>
                                <th>支付次数</th>
                                <th>支付时间</th>
                                <%--payOrder--%>
                                <th>支付状态</th>
							</tr>
						</thead>

						<tbody id="content">

						</tbody>

					</table>

                    <div id="cluePagination"></div>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="paged" class="page">
				</div>
			</div>
		</div>
        <!--模板-->
        <script type="text/html" id="tpl">
            {{# layui.each(d.list, function(index, item){ }}
            <tr>
                <td><input type="checkbox" lay-skin="primary"></td>
                <td>{{ item.payOrderId }}</td>
                <td>{{ item.mchId }}</td>
                <td>{{ item.channelId }}</td>
                <td>{{ item.amount }}</td>
                <td>
                    {{# if(item.status === 0){ }} <span style="color: black">订单生成</span> {{#  } }}
                    {{# if(item.status === 1){ }} <span style="color: blue">支付中</span> {{#  } }}
                    {{# if(item.status === 2){ }} <span style="color: green">支付成功</span> {{#  } }}
                    {{# if(item.status === 3){ }} <span style="color: orange">处理完成</span> {{#  } }}
                </td>
                <td>{{ item.createTime }}</td>
                <td>
                    <a href="javascript:;" data-id="{{ item.payOrderId }}" data-opt="view" class="layui-btn layui-btn-normal layui-btn-mini">订单详情</a>
                    <#--<a href="javascript:;" data-id="{{ item.payOrderId }}" data-opt="refund" class="layui-btn layui-btn-mini">发起退款</a>-->
                    <!--<a href="javascript:;" data-id="{{ item.mchId }}" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>-->
                </td>
            </tr>
            {{# }); }}
        </script>
        <script type="text/javascript" src="../plugins/layui/layui.js"></script>
        <script>
            layui.config({
                base: '../js/'
            });

            layui.use(['paging', 'form'], function() {
                var $ = layui.jquery,
                    paging = layui.paging(),
                    layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
                    layer = layui.layer, //获取当前窗口的layer对象
                    form = layui.form();

                /*paging.init({
                    openWait: true,
                    url: '/pay_order/list?v=' + new Date().getTime(), //地址
                    elem: '#content', //内容容器
                    params: { //发送到服务端的参数
                    },
                    type: 'GET',
                    tempElem: '#tpl', //模块容器
                    pageConfig: { //分页参数配置
                        elem: '#paged', //分页容器
                        pageSize: 10 //分页大小
                    },
                    success: function() { //渲染成功的回调
                        //alert('渲染成功');
                    },
                    fail: function(msg) { //获取数据失败的回调
                        //alert('获取数据失败')
                    },
                    complate: function() { //完成的回调
                        //alert('处理完成');
                        //重新渲染复选框
                        form.render('checkbox');
                        form.on('checkbox(allselector)', function(data) {
                            var elem = data.elem;

                            $('#content').children('tr').each(function() {
                                var $that = $(this);
                                //全选或反选
                                $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
                                form.render('checkbox');
                            });
                        });

                        //绑定所有预览按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=view]').on('click', function() {
                                viewForm($(this).data('id'));
                            });
                        });

                        //绑定所有编辑按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=refund]').on('click', function() {
                                layer.confirm('确定发起退款么？', {
                                    btn: ['退款','放弃'] //按钮
                                }, function(){
                                    layer.msg('功能还再开发中...', {icon: 1});
                                }, function(){
                                });
                            });
                        });

                        //绑定所有删除按钮事件
                        $('#content').children('tr').each(function() {
                            var $that = $(this);
                            $that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
                                layer.msg($(this).data('id'));
                            });
                        });

                    },
                });*/
                //获取所有选择的列
                /*$('#getSelected').on('click', function() {
                    var names = '';
                    $('#content').children('tr').each(function() {
                        var $that = $(this);
                        var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
                        if($cbx) {
                            var n = $that.children('td:last-child').children('a[data-opt=edit]').data('name');
                            names += n + ',';
                        }
                    });
                    layer.msg('你选择的名称有：' + names);
                });*/

                /*$('#search').on('click', function() {
                    var mchId = $("#mchId").val();
                    var payOrderId = $("#payOrderId").val();
                    var status = $("#status").val();
                    paging.get({
                        "mchId": mchId,
                        "payOrderId":payOrderId,
                        "status":status,
                        "v":new Date().getTime()
                    });
                });*/

                var addBoxIndex = -1;

                /*$('#import').on('click', function() {
                    var that = this;
                    var index = layer.tips('只想提示地精准些', that, { tips: [1, 'white'] });
                    $('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
                });*/

                /*function viewForm(id) {
                    //本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
                    $.get('/pay_order/view.html?payOrderId=' + id, null, function(form) {
                        addBoxIndex = layer.open({
                            type: 1,
                            title: '订单详情',
                            content: form,

                            shade: false,
                            offset: ['100px', '30%'],
                            area: ['600px', '550px'],
                            zIndex: 19950924,
                            maxmin: false,

                            full: function(elem) {
                                var win = window.top === window.self ? window : parent.window;
                                $(win).on('resize', function() {
                                    var $this = $(this);
                                    elem.width($this.width()).height($this.height()).css({
                                        top: 0,
                                        left: 0
                                    });
                                    elem.children('div.layui-layer-content').height($this.height() - 95);
                                });
                            },
                            end: function() {
                                addBoxIndex = -1;
                            }
                        });
                        layer.full(addBoxIndex);
                    });
                }*/
            });
        </script>

	</body>

</html>