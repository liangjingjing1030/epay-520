$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/paycompanyorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'seqNo', name: 'seqNo', index: 'SEQ_NO', width: 50, key: true, hidden: true },
			{ label: '合作支付机构编号', name: 'payCompanyNo', index: 'PAY_COMPANY_NO', width: 80 }, 			
			{ label: '支付公司清算日期', name: 'payCompanyClearDate', index: 'PAY_COMPANY_CLEAR_DATE', width: 80 }, 			
			{ label: '机构交易时间', name: 'payCompanyTxDate', index: 'PAY_COMPANY_TX_DATE', width: 80 }, 			
			{ label: '交易金额', name: 'txAmt', index: 'TX_AMT', width: 80 }, 			
			{ label: '交易手续费', name: 'txFee', index: 'TX_FEE', width: 80 }, 			
			{ label: '交易类型', name: 'txType', index: 'TX_TYPE', width: 80 }, 			
			{ label: '商户名称', name: 'mername', index: 'MERNAME', width: 80 }, 			
			{ label: '商户号', name: 'merno', index: 'MERNO', width: 80 }, 			
			{ label: '交易流水号', name: 'txSerialNo', index: 'TX_SERIAL_NO', width: 80 }, 			
			{ label: '交易返回码', name: 'trandeCode', index: 'TRANDE_CODE', width: 80 }, 			
			{ label: '对账日期', name: 'targetDate', index: 'TARGET_DATE', width: 80 }			
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		paycompanyOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		downfile: function(){
			
            var url = "manager/paycompanyorder/downfile";
            $.ajax({
                type: "GET",
                url: baseURL + url,
                contentType: "application/json",
                data: {
                	channel:$("#channel").val(),
                	starttime:$("#starttime").val(),
                	platform:$("#platform").val(),
                },
                success: function(r){
                    if(r.code === 0){
                         layer.msg("下载成功");
                         vm.reload();
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
		},
		compare: function (event) {
            var url = "manager/paycompanyorder/compare";
            $.ajax({
                type: "GET",
                url: baseURL + url,
                contentType: "application/json",
                data: {
                	channel:$("#channel").val(),
                	starttime:$("#starttime").val(),
                	platform:$("#platform").val(),
                },
                success: function(r){
                    if(r.code === 0){
                         layer.msg("对账成功");
                         vm.reload();
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.paycompanyOrder.seqNo == null ? "manager/paycompanyorder/save" : "manager/paycompanyorder/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.paycompanyOrder),
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
			var seqNos = getSelectedRows();
			if(seqNos == null){
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
                        url: baseURL + "manager/paycompanyorder/delete",
                        contentType: "application/json",
                        data: JSON.stringify(seqNos),
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
		getInfo: function(seqNo){
			$.get(baseURL + "manager/paycompanyorder/info/"+seqNo, function(r){
                vm.paycompanyOrder = r.paycompanyOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});