$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'manager/epayaccountfile/list',
        datatype: "json",
        colModel: [			
			{ label: '文件编号', name: 'fileId', index: 'file_id', width: 50, key: true },
			{ label: '商户号', name: 'mchId', index: 'mch_id', width: 80 }, 			
			{ label: '项目编号', name: 'itemsId', index: 'items_id', width: 80 }, 			
			{ label: '项目名称', name: 'itemsName', index: 'items_name', width: 80 }, 			
			{ label: '项目类别', name: 'itemsType', index: 'items_type', width: 80 }, 			
			{ label: '项目文件名称', name: 'itemsFilename', index: 'items_filename', width: 80 }, 			
			{ label: '文件上传日期', name: 'uploadDate', index: 'upload_date', width: 80 }, 			
			{ label: '账单生效时间', name: 'affectDate', index: 'affect_date', width: 80 }, 			
			{ label: '账单失效时间', name: 'expiryDate', index: 'expiry_date', width: 80 }		
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
        q:{
        	fileId: null
        },
		showList: true,
		title: null,
		epayAccountFile: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.epayAccountFile = {};
		},
		update: function (event) {
			var fileId = getSelectedRow();
			if(fileId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(fileId)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.epayAccountFile.fileId == null ? "manager/epayaccountfile/save" : "manager/epayaccountfile/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.epayAccountFile),
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
			var fileIds = getSelectedRows();
			if(fileIds == null){
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
                        url: baseURL + "manager/epayaccountfile/delete",
                        contentType: "application/json",
                        data: JSON.stringify(fileIds),
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
		getInfo: function(fileId){
			$.get(baseURL + "manager/epayaccountfile/info/"+fileId, function(r){
                vm.epayAccountFile = r.epayAccountFile;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
				postData:{'fileId': vm.q.fileId},
                page:page
            }).trigger("reloadGrid");
		}
	}
});