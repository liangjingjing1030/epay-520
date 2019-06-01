$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epaypayorder/list',
        datatype: "json",
        colModel: [			
			{ label: '订单号', name: 'payOrderId', index: 'pay_order_id', width: 50, key: true },
			{ label: '商户ID', name: 'mchId', index: 'mch_id', width: 80 }, 			
			{ label: '账单号', name: 'mchOrderNo', index: 'mch_order_no', width: 80 }, 			
			{ label: '渠道ID', name: 'channelId', index: 'channel_id', width: 80 }, 			
			{ label: '支付金额', name: 'amount', index: 'amount', width: 80 }, 			
			{ label: '支付状态', name: 'status', index: 'status', width: 80, formatter:statusFormatter}, 				
			{ label: '商品标题', name: 'subject', index: 'subject', width: 80 }, 			
			{ label: '商品信息', name: 'body', index: 'body', width: 80 }, 			
			{ label: '渠道商户ID', name: 'channelMchId', index: 'channel_mch_id', width: 80 }, 			
			{ label: '渠道订单号', name: 'channelOrderNo', index: 'channel_order_no', width: 80 }, 				
			{ label: '失效时间', name: 'expireTime', index: 'expire_time', width: 80 }, 			
			{ label: '支付成功时间', name: 'paySuccTime', index: 'pay_succ_time', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }	
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
function statusFormatter(value, options, row){
	if(value === 0){
		return '订单生成'
	}else if(value === 1){
		return '支付中'
	}else if(value === 2){
		return '支付成功'
	}else if(value === 3){
		return '业务处理完成'
	}
}
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
        	pay_order_id: null
        },
		showList: true,
		title: null,
		epayPayOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayPayOrder = {};
		},
		update: function (event) {
			var payOrderId = getSelectedRow();
			if(payOrderId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(payOrderId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayPayOrder.payOrderId == null ? "manager/epaypayorder/save" : "manager/epaypayorder/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayPayOrder),
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
			var payOrderIds = getSelectedRows();
			if(payOrderIds == null){
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
                        url: baseURL + "manager/epaypayorder/delete",
                        contentType: "application/json",
                        data: JSON.stringify(payOrderIds),
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
		getInfo: function(payOrderId){
			$.get(baseURL + "manager/epaypayorder/info/"+payOrderId, function(r){
                vm.epayPayOrder = r.epayPayOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'pay_order_id': vm.q.pay_order_id},
                page:page
            }).trigger("reloadGrid");
		}
	}
});