$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epayaccountbook/list',
        datatype: "json",
        colModel: [			
			{ label: '账单编号', name: 'accountBookId', index: 'account_book_id', width: 50, key: true },
			{ label: '商户号', name: 'mchId', index: 'mch_id', width: 80 }, 			
			{ label: '项目编号', name: 'itemsId', index: 'items_id', width: 80 }, 			
			{ label: '客户标识', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '用户名称', name: 'userName', index: 'user_name', width: 80 }, 			
			{ label: '应缴金额', name: 'itemsMoney', index: 'items_money', width: 80 }, 			
			{ label: '优惠金额', name: 'discountsMoney', index: 'discounts_money', width: 80 }, 			
			{ label: '实际金额', name: 'actualMoney', index: 'actual_money', width: 80 }, 			
			{ label: '已支付金额', name: 'paidMoney', index: 'paid_money', width: 80 }, 			
			{ label: '未支付金额', name: 'surplusMoney', index: 'surplus_money', width: 80 }, 			
			{ label: '滞纳金', name: 'violateMoney', index: 'violate_money', width: 80 }, 			
			{ label: '支付次数', name: 'payNumber', index: 'pay_number', width: 80 }, 			
			{ label: '支付时间', name: 'payTime', index: 'pay_time', width: 80 }, 			
			{ label: '支付状态', name: 'payStatus', index: 'pay_status', width: 80, formatter:payStatusFormatter } 			
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
function payStatusFormatter(value, options, row){
	if(value === 0){
		return '失败'
	}else if(value === 1){
		return '成功'
	}
}
function reverseStatusFormatter(value, options, row){
	if(value === 0){
		return '未冲正'
	}else if(value === 1){
		return '已冲正'
	}
}
var vm = new Vue({
	el:'#rrapp',
	data:{		
        q:{
        	accountBookId: null
        },
		showList: true,
		title: null,
		epayAccountBook: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayAccountBook = {};
		},
		update: function (event) {
			var accountBookId = getSelectedRow();
			if(accountBookId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(accountBookId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayAccountBook.accountBookId == null ? "manager/epayaccountbook/save" : "manager/epayaccountbook/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayAccountBook),
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
			var accountBookIds = getSelectedRows();
			if(accountBookIds == null){
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
                        url: baseURL + "manager/epayaccountbook/delete",
                        contentType: "application/json",
                        data: JSON.stringify(accountBookIds),
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
		getInfo: function(accountBookId){
			$.get(baseURL + "manager/epayaccountbook/info/"+accountBookId, function(r){
                vm.epayAccountBook = r.epayAccountBook;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'accountBookId': vm.q.accountBookId},
                page:page
            }).trigger("reloadGrid");
		}
	}
});