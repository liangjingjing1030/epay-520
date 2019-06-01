$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/cscustfee/list',
        datatype: "json",
        colModel: [			
			{ label: 'seqNo', name: 'seqNo', index: 'SEQ_NO', width: 50, key: true,hidden: true },
			{ label: '商户客户号', name: 'merCustNo', index: 'MER_CUST_NO', width: 80 }, 			
			{ label: '客户类型', name: 'custType', index: 'CUST_TYPE', width: 80 }, 			
			{ label: '业务类型', name: 'bizType', index: 'BIZ_TYPE', width: 80 }, 			
			{ label: '支付方式', name: 'payType', index: 'PAY_TYPE', width: 80 }, 			
			{ label: '交易费率代码', name: 'feeCode', index: 'FEE_CODE', width: 80 }, 			
			{ label: '退款手续费代码', name: 'rfRecFeeCode', index: 'RF_REC_FEE_CODE', width: 80 }, 					
			{ label: '订单有效期', name: 'orderEffectiveDuration', index: 'ORDER_EFFECTIVE_DURATION', width: 80 }, 			
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
		csCustFee: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.csCustFee = {};
		},
		update: function (event) {
			var seqNo = getSelectedRow();
			if(seqNo == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(seqNo)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.csCustFee.seqNo == null ? "manager/cscustfee/save" : "manager/cscustfee/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.csCustFee),
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
                        url: baseURL + "manager/cscustfee/delete",
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
			$.get(baseURL + "manager/cscustfee/info/"+seqNo, function(r){
                vm.csCustFee = r.csCustFee;
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