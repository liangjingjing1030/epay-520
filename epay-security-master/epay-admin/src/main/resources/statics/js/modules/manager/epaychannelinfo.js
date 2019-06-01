$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epaychannelinfo/list',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'id', index: 'id', width: 50, key: true },
			{ label: '商户ID', name: 'mchId', index: 'mch_id', width: 80 }, 			
			{ label: '渠道ID', name: 'channelId', index: 'channel_id', width: 80 }, 			
			{ label: '渠道名称', name: 'channelName', index: 'channel_name', width: 80 }, 			
			{ label: '渠道商户ID', name: 'channelMchid', index: 'channel_mchId', width: 80 }, 			
			{ label: '渠道状态', name: 'channelStatus', index: 'channel_status', width: 80, formatter:statusFormatter }, 			
			{ label: '配置参数', name: 'param', index: 'param', width: 80 }, 			
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
		return '停止使用'
	}else if(value === 1){
		return '使用中'
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
		epayChannelInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayChannelInfo = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayChannelInfo.id == null ? "manager/epaychannelinfo/save" : "manager/epaychannelinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayChannelInfo),
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
			var ids = getSelectedRows();
			if(ids == null){
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
                        url: baseURL + "manager/epaychannelinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
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
		getInfo: function(id){
			$.get(baseURL + "manager/epaychannelinfo/info/"+id, function(r){
                vm.epayChannelInfo = r.epayChannelInfo;
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