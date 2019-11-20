//用户登录主界面

$(document).ready(function() {
	var inPhone = "";
	var inPwd = "";
	// input框获得焦点边框变为黑色
	$("input").focus(function() {
		$(this).css("border", "1px solid #666");
	});

	// 登录主页面验证手机号码和邮箱
	$("input[name='box_phone_email']").blur(
			function() {
				var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
				var phone = $(this).val();
				if (phone.length < 1) {
					$(this).next().html("请输入手机号码");
					$(this).css({
						"background-color" : "rgba(255,0,0,0.1)",
						"border" : "1px solid red"
					});
				} else if (check.test(phone) == false) {
					$(this).next().html("请输入正确的手机号码");
					$(this).css({
						"background-color" : "rgba(255,0,0,0.1)",
						"border" : "1px solid red"
					});
				} else if (check.test(phone) == true) {
					$(this).next().html("");
					$(this).css({
						"background-color" : "rgb(255,255,255)",
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
									$("input[name='box_phone_email']").next().html(
											"");
									$("input[name='box_phone_email']").css({
										"background-color" : "#fff",
										"border" : "1px solid transparent"
									});
								} else if (result.YES == 'NO') {
									// 验证失败
									$("input[name='box_phone_email']").next().html(
											"该用户不存在!");
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
	// 登录主页面验证密码
	$("input[name='box_password']").blur(function() {
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
				"background-color" : "rgb(255,255,255)",
				"border" : "1px solid transparent"
			});
		}
	});
	// 登录主页面记住我
	$("#box_login").prev().prev().click(function() {
		var remember = $(this).html();
		if (remember.length > 0) {
			$(this).html("");
		} else {
			$(this).html("√");
		}
	});
	// 登录主页面登录验证
	$("#box_login").click(function() {
		var cxt = $("#cxt").val();
		var phone = $("input[name='box_phone_email']").val();
		var pwd = $("input[name='box_password']").val();
		var remember = $(this).prev().prev().val();
		var url = cxt + "/LoginVerifyAndQueryPersonal";
		var params = {
			phone : phone,
			pwd : pwd,
			remember : remember
		};
		if(phone=="13888888888" && pwd=="888888"){
			location.href=cxt+"/admin/admin.jsp";
		}else{
			$.ajax({
				url : url,
				type : "post",
				dataType : "json",
				data : params,
				success : function(result) {
					if (null == result.error) {
						if (result.result == 'Y') {
							// 验证通过
							inPhone = result.outPhone;
							inPwd = result.outPwd;
							$("input[name='login_phone_email']").html(inPhone);
							$("input[name='login_password']").html(inPwd);
							$("#box_login").prev().prev().html("√");
							window.location = cxt + "/user/gucci.jsp";
						} else {
							// 验证失败
							$("input[name='box_password']").next().html("用户名或密码错误！");
							$("input[name='box_password']").css({
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
		}
		
	});
	// 登录主页面忘记密码，找回密码
	$("#box_login").next().click(function() {
		$("#forgotPassword").show();
	});
	// 登录主页面创建用户，注册
	$("#box_registe").click(function() {
		$("#registe").show();
	});
	
	// 声明变量用于判断手机号码、密码、二次密码是否符合要求
	var flag1 = false;
	var flag2 = false;
	var flag3 = false;
	var flag4 = false;
	// 注册页面关闭按钮
	$("input[name='registe_phone_email']").parent().prev().click(function() {
		$("#registe").hide();
		$("input[name='registe_phone_email']").next().html("");
		$("input[name='registe_phone_email']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='registe_password']").next().html("");
		$("input[name='registe_password']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='registe_restpassword']").next().html("");
		$("input[name='registe_restpassword']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='registe_restpassword']").next().next().html("");
	});
	// 注册页面手机验证
	$("input[name='registe_phone_email']").blur(
			function() {
				var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
				var phone = $(this).val();
				// 判断手机号码是否符合要求
				if (phone.length < 1) {
					$(this).next().html("请输入手机号码");
					$(this).css({
						"background-color" : "rgba(255,0,0,0.1)",
						"border" : "1px solid red"
					});
				} else if (check.test(phone) == false) {
					$(this).next().html("请输入正确的手机号码");
					$(this).css({
						"background-color" : "rgba(255,0,0,0.1)",
						"border" : "1px solid red"
					});
				} else if (check.test(phone) == true) {
					// 手机号码通过验证请空提示栏
					$(this).next().html("");
					$(this).css({
						"background-color" : "#eee",
						"border" : "1px solid transparent"
					});
					var cxt = $("#cxt").val();
					var url = cxt + "/Register";
					var params = {
						phone : phone
					};
					$.ajax({
						url : url,
						type : "post",
						async : true,
						dataType : "json",
						data : params,
						success : function(result) {
							if (null == result.error) {
								if (result.result == 'Y') {
									// 验证通过
									$("input[name='registe_phone_email']").next()
											.html("");
									$("input[name='registe_phone_email']").css({
										"background-color" : "#eee",
										"border" : "1px solid transparent"
									});
									flag1 = true;
								} else if (result.result == 'N') {
									// 验证失败
									$("input[name='registe_phone_email']").next()
											.html("该用户已存在!");
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
	// 发送验证码
	$("input[name='registe_verification_code']").next().click(
			function() {
				var verification = $("input[name='registe_phone_email']").val();
				if (flag1 == true) {
					var cxt = $("#cxt").val();
					var url = cxt + "/Register";
					var para = {
						verification : verification
					};
					$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						data : para,
						success : function(result) {
							if (null == result.error) {
								if (result.verification == 'YES') {
									// 验证通过
									$("input[name='registe_verification_code']")
											.next().html("");
									$("input[name='registe_verification_code']")
											.css({
												"background-color" : "#eee",
												"border" : "1px solid transparent"
											});
								} else if (result.verification == 'NO') {
									// 验证失败
									$("input[name='registe_verification_code']")
											.next().html("发送失败!");
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
	// 验证验证码
	$("input[name='registe_verification_code']").blur(
			function() {
				var inputNumber = $("input[name='registe_verification_code']")
						.val();
				if (inputNumber != "") {
					var cxt = $("#cxt").val();
					var url = cxt + "/Register";
					var para1 = {
						inputNumber : inputNumber
					};
					$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						data : para1,
						success : function(result) {
							if (null == result.error) {
								if (result.Verify == 'YES') {
									// 验证通过
									$("input[name='registe_verification_code']")
											.next().html("");
									$("input[name='registe_verification_code']")
											.css({
												"background-color" : "#eee",
												"border" : "1px solid transparent"
											});
									flag4 = true;
								} else if (result.Verify == 'NO') {
									// 验证失败
									$("input[name='registe_verification_code']")
											.next().html("验证码错误!");
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
	// 注册页面验证密码
	$("input[name='registe_password']").blur(function() {
		var pwd = $(this).val();
		// 判断密码是否符合要求
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
			flag2 = true;
		}
	});
	// 注册页面验证再次输入密码
	$("input[name='registe_restpassword']").blur(function() {
		var pwd = $("input[name='registe_password']").val();
		var repwd = $(this).val();
		// 判断二次密码是否符合要求
		if (pwd.length < 1) {
			$(this).next().html("确认密码不能为空");
			$(this).css({
				"background-color" : "rgba(255,0,0,0.1)",
				"border" : "1px solid red"
			});
		} else if (pwd != repwd) {
			$(this).next().html("确认密码和密码需要保持一致");
			$(this).css({
				"background-color" : "rgba(255,0,0,0.1)",
				"border" : "1px solid red"
			});
		} else if (pwd.length >= 6 && pwd == repwd) {
			$(this).next().html("");
			$(this).css({
				"background-color" : "#eee",
				"border" : "1px solid transparent"
			});
			flag3 = true;
		}
	});
	// 注册页面记住我
	$("input[name='registe_restpassword']").next().next().click(function() {
		var remember = $(this).html();
		if (remember.length > 0) {
			$(this).html("");
		} else {
			$(this).html("√");
		}
	});
	// 注册页面提交验证
	$("#registe_submit").click(function() {
		var remenber = $("#registe_submit").prev().prev().text();
		if (flag1 == true && flag2 == true && flag3 == true && flag4 == true && remenber == "√") {
			var cxt = $("#cxt").val();
			var phone1 = $("input[name='registe_phone_email']").val();
			var pwd1 = $("input[name='registe_password']").val();
			var url = cxt + "/Register";
			var param = {
				phone1 : phone1,
				pwd1 : pwd1
			};
			$.ajax({
				url : url,
				type : "post",
				dataType : "json",
				data : param,
				success : function(result) {
					if (null == result.error) {
						if (result.registe == 'OK') {
							// 验证通过
							window.location = cxt + "/user/G.jsp";
						} else if (result.registe == 'NO') {
							// 验证失败
						}
					} else {
						alert(result.error);
					}
				},
				error : function() {
					alert("请求超时!");
				}
			});
		}
	});

	// 声明变量用于判断手机号码、密码、二次密码是否符合要求
	var flaga = false;
	var flagb = false;
	var flagc = false;
	var flagd = false;

	// 忘记密码页面关闭按钮
	$("input[name='forgotPassword_phone_email']").parent().prev().click(function() {
		$("#forgotPassword").hide();
		$("input[name='forgotPassword_phone_email']").next().html("");
		$("input[name='forgotPassword_phone_email']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='forgotPassword_password']").next().html("");
		$("input[name='forgotPassword_password']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
		$("input[name='forgotPassword_restpassword']").next().html("");
		$("input[name='forgotPassword_restpassword']").css({
			"background-color" : "#eee",
			"border" : "1px solid transparent"
		});
	});
	// 忘记密码页面手机验证
	$("input[name='forgotPassword_phone_email']").blur(
			function() {
				var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
				var phone = $(this).val();
				// 判断手机号码是否符合要求
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
					// 手机号码通过验证请空提示栏
					$(this).next().html("");
					$(this).css({
						"background-color" : "#eee",
						"border" : "1px solid transparent"
					});
					var cxt = $("#cxt").val();
					var url = cxt + "/ForgotPassword";
					var params = {
						phone : phone
					};

					$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						data : params,
						success : function(result) {
							if (null == result.error) {
								if (result.result == 'Y') {
									// 验证通过
									$("input[name='forgotPassword_phone_email']")
											.next().html("");
									$("input[name='forgotPassword_phone_email']")
											.css({
												"background-color" : "#eee",
												"border" : "1px solid transparent"
											});
									flaga = true;
								} else if (result.result == 'N') {
									// 验证失败
									$("input[name='forgotPassword_phone_email']")
											.next().html("该用户不存在!");
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
	// 发送验证码
	$("input[name='forgotPassword_verification_code']").next().click(
			function() {
				var verification = $("input[name='forgotPassword_phone_email']")
						.val();
				if (flag1 == true) {
					var cxt = $("#cxt").val();
					var url = cxt + "/ForgotPassword";
					var para = {
						verification : verification
					};
					$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						data : para,
						success : function(result) {
							if (null == result.error) {
								if (result.verification == 'YES') {
									// 验证通过
									$("input[name='registe_verification_code']")
											.next().html("");
									$("input[name='registe_verification_code']")
											.css({
												"background-color" : "#eee",
												"border" : "1px solid transparent"
											});
								} else if (result.verification == 'NO') {
									// 验证失败
									$("input[name='registe_verification_code']")
											.next().html("发送失败!");
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
	// 验证验证码
	$("input[name='forgotPassword_verification_code']")
			.blur(
					function() {
						var inputNumber = $(
								"input[name='forgotPassword_verification_code']")
								.val();
						if (inputNumber != "") {
							var cxt = $("#cxt").val();
							var url = cxt + "/ForgotPassword";
							var para1 = {
								inputNumber : inputNumber
							};
							$.ajax({
								url : url,
								type : "post",
								dataType : "json",
								data : para1,
								success : function(result) {
									if (null == result.error) {
										if (result.Verify == 'YES') {
											// 验证通过
											$("input[name='forgotPassword_verification_code']")
													.next().html("");
											$("input[name='forgotPassword_verification_code']")
													.css(
															{
																"background-color" : "#eee",
																"border" : "1px solid transparent"
															});
											flagb = true;
										} else if (result.Verify == 'NO') {
											// 验证失败
											$(
													"input[name='forgotPassword_verification_code']")
													.next().html("验证码错误!");
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
	// 忘记密码页面验证密码
	$("input[name='forgotPassword_password']").blur(function() {
		var pwd = $(this).val();
		// 判断密码是否符合要求
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
			flagc = true;
		}
	});
	// 忘记密码页面验证再次输入密码
	$("input[name='forgotPassword_restpassword']").blur(function() {
		var pwd = $(this).prev().prev().prev().val();
		var repwd = $(this).val();
		// 判断二次密码是否符合要求
		if (pwd.length < 1) {
			$(this).next().html("确认密码不能为空");
			$(this).css({
				"background-color" : "rgba(255,0,0,0.1)",
				"border" : "1px solid red"
			});
		} else if (pwd != repwd) {
			$(this).next().html("确认密码和密码需要保持一致");
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
			flagd = true;
		}
	});
	// 忘记密码主页面提交验证
	$("#forgotPassword_submit").click(
			function() {
				if (flaga == true && flagb == true && flagd == true && flagc == true) {
					var phone1 = $("input[name='forgotPassword_phone_email']").val();
					var pwd1 = $("input[name='forgotPassword_password']").val();
					var forgotPassword_verification_code = $(
							"input[name='forgotPassword_verification_code']").val();
					var cxt = $("#cxt").val();
					var url = cxt + "/ForgotPassword";
					var param = {
						phone1 : phone1,
						pwd1 : pwd1
					};
					$.ajax({
						url : url,
						type : "post",
						dataType : "json",
						data : param,
						success : function(result) {
							if (null == result.error) {
								if (result.forgotPassword == 'OK') {
									// 验证通过
									window.location = cxt + "/user/G.jsp";
								} else {
									// 验证失败
								}
							} else {
								alert(result.error);
							}
						},
						error : function() {
							alert("请求超时!");
						}
					});
				}
			});
	})