$(document).ready(function() {
	init();
	var cxt = $("#cxt").val();
	// 加载显示所有的商品信息，并实现分页显示
	var pageIndex = $("#pageIndex").val();
	var pageCount = $("#pageCount").val();
	var goodsClassId = $("#goodsClassId").val();
	
	// 登录子界面关闭按钮
	$("input[name='login_phone_email']").parent().prev().click(function() {
		$("#login").hide();
		$("input[name='login_phone_email']").next().html("");
		$("input[name='login_phone_email']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='login_password']").next().html("");
		$("input[name='login_password']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("#login_login").prev().prev().html("");
	});
	$("#pleaseLogin").click(function(){
		$("#Login").show();
	})
})
// 点击跳转商品详情页面
function viewGoods(goodsId) {
	var cxt = $("#cxt").val();
	location.href = cxt + "/user/showShopping.jsp?goodsId=" + goodsId;
	//将商品存入缓存,ajax请求
	var url=cxt+"/ShoucangServlet.do";
	var params={goodsId:goodsId};
	$.ajax({
		url:url,
		data:params,
		type:"post"
	})
	
}
// 登录子界面

// 获取上下文路径
var cxt = $("#cxt").val();

var inPhone = "";
var inPwd = "";

function init() {
	var cxt = $("#cxt").val();
	var url = cxt + "/Inti";
	var inti = 0;
	var param = {
		inti : inti
	};
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : param,
		success : function(result) {
			if (null == result.error) {
				if (result.YES == 'Y') {
					// 验证通过
					$("input[name='login_phone_email']").val(result.outPhone);
					$("input[name='login_password']").val(result.outPwd);
					if ($("input[name='login_password']").val() != "") {
						$("#login_login").prev().prev().html("√");
					}
				} else if (result.YES == 'N') {
					// 验证失败
				}
			} else {
				alert(result.error);
			}
		},
		error : function() {
			alert("error:请求超时!");
		}
	});
}

// input框获得焦点边框变成黑色
$("input").focus(function() {
	$(this).css("border", "1px solid #666");
});



// 登录子界面手机/邮箱验证
$("input[name='login_phone_email']").blur(
		function() {
			var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
			var phone = $(this).val();
			if (phone.length < 1) {
				$(this).next().html("请输入手机号码/电子邮箱");
				$(this).css({
					"background-color" : "rgba(255,0,0,0.1)",
					"border" : "1px solid red"
				});
			} else if (check.test(phone) == false) {
				$(this).next().html("请输入正确的手机号码/电子邮箱");
				$(this).css({
					"background-color" : "rgba(255,0,0,0.1)",
					"border" : "1px solid red"
				});
			} else if (check.test(phone) == true) {
				$(this).next().html("");
				$(this).css({
					"background-color" : "#eee",
					"border" : "1px solid transparent"
				});
				var cxt = $("#cxt").val();
				var url = cxt + "/LoginVerifyAndQueryPersonal";
				var param = {
					phone1 : phone
				};
				$.ajax({
					url : url,
					type : "post",
					dataType : "json",
					data : param,
					success : function(result) {
						if (null == result.error) {
							if (result.YES == 'YES') {
								// 验证通过
								$("input[name='login_phone_email']").next()
										.html("");
								$("input[name='login_phone_email']").css({
									"background-color" : "#eee",
									"border" : "1px solid transparent"
								});
							} else if (result.YES == 'NO') {
								// 验证失败
								$("input[name='login_phone_email']").next()
										.html("该用户不已存在!");
							}
						} else {
							alert(result.error);
						}
					},
					error : function() {
						alert("error:请求超时!");
					}
				});
			}
		});

// 登录子界面验证密码
$("input[name='login_password']").blur(function() {
	var pwd = $(this).val();
	if (pwd.length < 1) {
		$(this).next().html("密码不能为空!");
		$(this).css({
			"background-color" : "rgba(255,0,0,0.1)",
			"border" : "1px solid red"
		});
	} else if (pwd.length > 1 && pwd.length < 6) {
		$(this).next().html("至少6个字符含数字大小写字母");
		$(this).css({
			"background-color" : "rgba(255,0,0,0.1)",
			"border" : "1px solid red"
		});
	} else if (pwd.length >= 6) {
		$(this).next().html("");
		$(this).css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
	}
});

// 登录子界面记住我勾选框
$("#login_login").prev().prev().click(function() {
	var remember = $(this).html();
	if (remember.length > 0) {
		$(this).html("");
	} else {
		$(this).html("√");
	}
});

// 登录子界面登录验证
$("#login_login").click(function() {

	// 获取记住我勾选框是否勾选，如果勾选将生成相应的cookie
	var remember = $(this).prev().prev().val();

	// 获取手机号码和密码
	var phone = $("input[name='login_phone_email']").val();
	var pwd = $("input[name='login_password']").val();
	var cxt = $("#cxt").val();
	// 异步请求路径
	var url = cxt + "/LoginVerifyAndQueryPersonal";
	var param = {
		phone : phone,
		pwd : pwd,
		remember : remember
	};

	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : param,
		success : function(result) {
			if (null == result.error) {// 回调函数中错误为空证明没有异常发生
				if (result.result == 'Y') {
					// 验证通过
					window.location = cxt + "/a.jsp";
				} else {
					// 验证失败
					$("input[name='login_password']").next().html("用户名或密码错误！");
					$("input[name='login_password']").css({
						"background-color" : "rgba(255,0,0,0.1)",
						"border" : "1px solid red"
					});
				}
			} else {
				alert(result.error);
			}
		},
		error : function() {
			alert("error:请求超时!");
		}
	});
});

// 登录子页面忘记密码，找回密码
$("#login_login").next().click(function() {
	window.location = cxt + "/ForgotPassword.jsp";
});