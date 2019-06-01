$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epayrefundorder/list',
        datatype: "json",
        colModel: [			
			{ label: '退款订单号', name: 'refundOrderId', index: 'refund_order_id', width: 50, key: true },
			{ label: '支付订单号', name: 'payOrderid', index: 'pay_orderid', width: 80 }, 			
			{ label: '渠道支付单号', name: 'channelPayOrderNo', index: 'channel_pay_order_no', width: 80 }, 			
			{ label: '商户ID', name: 'mchId', index: 'mch_id', width: 80 }, 			
			{ label: '商户退款单号', name: 'mchRefundNo', index: 'mch_refund_no', width: 80 }, 			
			{ label: '渠道ID', name: 'channelId', index: 'channel_id', width: 80 }, 			
			{ label: '支付金额', name: 'payAmount', index: 'pay_amount', width: 80 }, 			
			{ label: '退款金额', name: 'refundAmount', index: 'refund_amount', width: 80 }, 			
			{ label: '退款状态', name: 'ctatus', index: 'ctatus', width: 80, formatter:ctatusFormatter }, 			
			{ label: '退款结果', name: 'result', index: 'result', width: 80, formatter:resultFormatter }, 			
			{ label: '渠道订单号', name: 'channelOrderNo', index: 'channel_order_no', width: 80 }, 			
			{ label: '渠道响应码', name: 'channelResCode', index: 'channel_res_code', width: 80 }, 			
			{ label: '失效时间', name: 'expireTime', index: 'expire_time', width: 80 }, 			
			{ label: '退款成功时间', name: 'refundSuccTime', index: 'refund_succ_time', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
/**
 * 格式化列显示形式
 */
function ctatusFormatter(value, options, row){
	if(value === 0){
		return '订单生成'
	}else if(value === 1){
		return '退款中'
	}else if(value === 2){
		return '退款成功'
	}else if(value === 3){
		return '退款失败'
	}else if(value === 4){
		return '业务处理完成'
	}
}
function resultFormatter(value, options, row){
	if(value === 0){
		return '不确认结果'
	}else if(value === 1){
		return '等待手动处理'
	}else if(value === 2){
		return '确认成功'
	}else if(value === 3){
		return '确认失败'
	}
}

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
        	refund_order_id: null
        },
		showList: true,
		title: null,
		epayRefundOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayRefundOrder = {};
		},
		update: function (event) {
			var refundOrderId = getSelectedRow();
			if(refundOrderId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(refundOrderId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayRefundOrder.refundOrderId == null ? "manager/epayrefundorder/save" : "manager/epayrefundorder/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayRefundOrder),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var refundOrderIds = getSelectedRows();
			if(refundOrderIds == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "manager/epayrefundorder/delete",
                        contentType: "application/json",
                        data: JSON.stringify(refundOrderIds),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(refundOrderId){
			$.get(baseURL + "manager/epayrefundorder/info/"+refundOrderId, function(r){
                vm.epayRefundOrder = r.epayRefundOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'refund_order_id': vm.q.refund_order_id},
                page:page
            }).trigger("reloadGrid");
		}
	}
});