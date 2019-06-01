$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epaymchinfo/getauditlist',
        datatype: "json",
        colModel: [			
			{ label: '商户号', name: 'mchId', index: 'mch_id', width: 50, key: true },
			{ label: '商户名称', name: 'mchName', index: 'mch_name', width: 80 }, 			
			{ label: '商户类型', name: 'mchType', index: 'mch_type', width: 80 }, 			
			{ label: '商户状态', name: 'mchStatus', index: 'mch_status', width: 80, formatter:mchStatusFormatter }, 					
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '更新时间', name: 'updateTime', index: 'update_time', width: 80 }, 			
			{ label: '审核时间', name: 'auditTime', index: 'audit_time', width: 80 }, 			
			{ label: '审核原因', name: 'auditReason', index: 'audit_reason', width: 80 },		
			{ label: '操作', name: 'auditStatus', index: 'auditStatus', width: 40,edittype: 'button', formatter: buttonFormatter}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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
function buttonFormatter(value, options, row){
	if(value === 0){
		return '<button class="btn btn-primary" onclick="handle('+row.mchId+')">审核</button>';
	}else if(value === 2){
		return '<button class="btn btn-primary" onclick="handle('+row.mchId+')">重新审核</button>';
	}
	
}
function handle(id) {
	vm.handle(id);
}
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
        	mchId: null
        },
		showList: true,
		title: null,
		epayMchInfo: {},
		showHandle: true,
		radioList: [{value:'审核通过'}, {value:'审核拒绝'}],
		auditStatus: 0,
		oldStatus: 1,
		showPause: true
	},
	methods: {
		handle: function(id) {
			vm.getInfo(id);
			vm.showHandle = false;
			vm.showList = false;
		},
		handleCheck: function(index) {
			console.log(index);
			if(index == 0){
				vm.showPause = true;
				vm.epayMchInfo.auditStatus = 1;
				vm.epayMchInfo.auditReason = '';
			}else if(index == 1) {
				vm.showPause = false;
				vm.epayMchInfo.auditStatus = 2;
			}
		},
		query: function () {
			vm.reload();
		},
		update: function (event) {
			if(vm.epayMchInfo.auditStatus == 0){
				//说明选择了默认的处理方式，直接处理，但是没有点击时不会赋值
				vm.epayMchInfo.auditStatus = 1;
			}
			var url = "manager/epaymchinfo/updateHandle";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.epayMchInfo),
			    success: function(r){
			    	console.log(r);
			    	if(r.code === 0){
			    		alert(r.code);
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayMchInfo = {};
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
		reloadHandle: function(event){
			vm.showList = true;
			vm.showHandle = true;
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
			vm.checkRadio = 0;
			$.get(baseURL + "manager/epaymchinfo/info/"+mchId, function(r){
                vm.epayMchInfo = r.epayMchInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			vm.showHandle = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});