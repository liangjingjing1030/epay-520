
// 用户登录
function login() {
	alert("1111")
	/*var phone = $.trim($("#phone").val());
	var loginPassword = $.trim($("#loginPassword").val());

	if(checkPhone() && checkLoginPassword() && checkCaptcha()) {
		$("#loginPassword").val($.md5(loginPassword));

		$.ajax({
			type:"post",
			url:"loan/login",
			data:"phone="+phone+"&loginPassword="+$("#loginPassword").val(),
			success:function (jsonObject) {
				if(jsonObject.errorMessage == "OK") {
					// 从哪来回哪去
					window.location.href = referrer;
				} else {
					$("#showId").html("账号或密码错误，请重新输入");
				}
            },
			error:function () {
                $("#showId").html("系统繁忙，请稍后再试...");
            }
		});
	}*/
}

// 页面加载完成后查询数据
$(function () {
	// 加载平台信息
	loadStat();
});

function loadStat() {
	$.ajax({
		type:"get",
		url:"loan/loadStat",
		success:function (jsonObject) {
			$(".historyAverageRate").html(jsonObject.historyAverageRate);
			$("#allUserCount").html(jsonObject.allUserCount);
			$("#allBidMoney").html(jsonObject.allBidMoney);
        }
	});
}























