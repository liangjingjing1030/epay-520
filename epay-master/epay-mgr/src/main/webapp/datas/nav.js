var navs = [{
	"title": "商户模块",
	"icon": "fa-user",
	"spread": true,
	"children": [{
		"title": "商户信息",
		"icon": "fa-circle",
		"href": "/custBaseInfo.jsp"
	}]
},{
	"title": "账单模块",
	"icon": "fa-file-text-o",
	"spread": false,
	"children": [{
		"title": "活动管理",
		"icon": "fa-circle",
		"href": "/activity.jsp"
	}, {
		"title": "账单管理",
		"icon": "fa-circle",
		"href": "/billUploadAndDelete.jsp"
	}, {
		"title": "账单查询",
		"icon": "fa-circle",
		"href": "/billqueryAndExport.jsp"
	}]
},{
	"title": "交易模块",
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
	}, {
		"title": "对账查询",
		"icon": "fa-circle",
		"href": "/reconciliation.jsp"
	}]
},{
	"title": "结算模块",
	"icon": "fa-usd",
	"spread": false,
	"children": [{
		"title": "结算查询",
		"icon": "fa-circle",
		"href": "/moneyQuery.jsp"
	}]
}];