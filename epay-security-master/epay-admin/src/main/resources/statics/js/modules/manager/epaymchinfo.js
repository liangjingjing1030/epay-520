$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epaymchinfo/list',
        datatype: "json",
        colModel: [			
			{ label: '商户号', name: 'mchId', index: 'mch_id', width: 50, key: true },
			{ label: '商户名称', name: 'mchName', index: 'mch_name', width: 80 }, 			
			{ label: '商户类型', name: 'mchType', index: 'mch_type', width: 80 }, 			
			{ label: '商户地址 ', name: 'mchAddress', index: 'mch_address', width: 80 }, 			
			{ label: '商户状态', name: 'mchStatus', index: 'mch_status', width: 80, formatter:mchStatusFormatter }, 		
			{ label: '联系人姓名', name: 'contactPerson', index: 'contact_person', width: 80 }, 			
			{ label: '联系电话', name: 'contactPhone', index: 'contact_phone', width: 80 }, 			
			{ label: '商户邮箱', name: 'contactEmail', index: 'contact_email', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '审核时间', name: 'auditTime', index: 'audit_time', width: 80 }, 			
			{ label: '审核状态', name: 'auditStatus', index: 'audit_status', width: 80, formatter:auditStatusFormatter }, 			
			{ label: '审核原因', name: 'auditReason', index: 'audit_reason', width: 80 }		
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
function mchStatusFormatter(value, options, row){
	if(value === 0){
		return '未激活'
	}else if(value === 1){
		return '使用中'
	}else if(value === 2){
		return '停止使用'
	}
}
function auditStatusFormatter(value, options, row){
	if(value === 0){
		return '未审核'
	}else if(value === 1){
		return '已审核'
	}else if(value === 2){
		return '拒绝'
	}
}
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
        	mchId: null
        },
		showList: true,
		title: null,
		epayMchInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayMchInfo = {};
		},
		update: function (event) {
			var mchId = getSelectedRow();
			if(mchId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(mchId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayMchInfo.mchId == null ? "manager/epaymchinfo/save" : "manager/epaymchinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayMchInfo),
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
			var mchIds = getSelectedRows();
			if(mchIds == null){
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
                        url: baseURL + "manager/epaymchinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(mchIds),
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
		getInfo: function(mchId){
			$.get(baseURL + "manager/epaymchinfo/info/"+mchId, function(r){
                vm.epayMchInfo = r.epayMchInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{'mchId': vm.q.mchId},
                page:page
            }).trigger("reloadGrid");
		}
	}
});