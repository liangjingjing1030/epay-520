var navs = [{
	"title": "商户管理",
	"icon": "fa-user",
	"spread": true,
	"children": [{
		"title": "商户信息",
		"icon": "fa-circle",
		"href": "/custBaseInfo.jsp"
	}]
},{
	"title": "账单管理",
	"icon": "fa-file-text-o",
	"spread": false,
	"children": [{
		"title": "上传/删除",
		"icon": "fa-circle",
		"href": "/billUploadAndDelete.jsp"
	}, {
		"title": "查询/导出",
		"icon": "fa-circle",
		"href": "/billqueryAndExport.jsp"
	}]
},{
	"title": "交易管理",
	"icon": "fa-gg",
	"spread": false,
	"children": [{
		"title": "交易查询",
		"icon": "fa-circle",
		"href": "/dealQuery.jsp"
	},{
		"title": "交易统计",
		"icon": "fa-circle",
		"href": "/dealCount.jsp"
	},{
		"title": "退款",
		"icon": "fa-circle",
		"href": "/refund.jsp"
	}, {
		"title": "退款明细查询",
		"icon": "fa-circle",
		"href": "/refundDetail.jsp"
	}]
},{
	"title": "结算管理",
	"icon": "fa-usd",
	"spread": false,
	"children": [{
		"title": "结算查询",
		"icon": "fa-circle",
		"href": "/moneyQuery.jsp"
	}]
}];