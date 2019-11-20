$(document)
		.ready(
				function() {
					init();
					showGoodsClass();
					var pageIndex = $("#pageIndex").val();
					var type = $("#type").val();
					var pageCount = $("#pageCount").val();
					var totalCount = $("#totalCount").val();
					var params = {
						pageIndex : pageIndex,
						type : type,
						pageCount : pageCount,
						totalCount : totalCount,
					};

					// 点击页头跳转主页面
					$(".logo").click(function() {
						var cxt = $("#cxt").val();
						location.href = cxt + "/user/gucci.jsp";
					})
					showGoods(params);
					// 声明变量用于判断手机号码、密码、二次密码是否符合要求
					$("#pleaseLogin").click(function() {
						$("#login").show();
					})
					var flaga = false;
					var flagb = false;
					var flagc = false;
					var flagd = false;

					// 忘记密码页面关闭按钮
					$("input[name='forgotPassword_phone_email']")
							.parent()
							.prev()
							.click(
									function() {
										$("#forgotPassword").hide();
										$(
												"input[name='forgotPassword_phone_email']")
												.next().html("");
										$(
												"input[name='forgotPassword_phone_email']")
												.css(
														{
															"background-color" : "#eee",
															"border" : "1px solid transparent"
														});
										$(
												"input[name='forgotPassword_password']")
												.next().html("");
										$(
												"input[name='forgotPassword_password']")
												.css(
														{
															"background-color" : "#eee",
															"border" : "1px solid transparent"
														});
										$(
												"input[name='forgotPassword_restpassword']")
												.next().html("");
										$(
												"input[name='forgotPassword_restpassword']")
												.css(
														{
															"background-color" : "#eee",
															"border" : "1px solid transparent"
														});
									});
					// 忘记密码页面手机验证
					$("input[name='forgotPassword_phone_email']")
							.blur(
									function() {
										var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
										var phone = $(this).val();
										// 判断手机号码是否符合要求
										if (phone.length < 1) {
											$(this).next().html("请输入手机号码");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (check.test(phone) == false) {
											$(this).next().html("请输入正确的手机号码");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (check.test(phone) == true) {
											// 手机号码通过验证请空提示栏
											$(this).next().html("");
											$(this)
													.css(
															{
																"background-color" : "#eee",
																"border" : "1px solid transparent"
															});
											var cxt = $("#cxt").val();
											var url = cxt + "/ForgotPassword";
											var params = {
												phone : phone
											};

											$
													.ajax({
														url : url,
														type : "post",
														dataType : "json",
														data : params,
														success : function(
																result) {
															if (null == result.error) {
																if (result.result == 'Y') {
																	// 验证通过
																	$(
																			"input[name='forgotPassword_phone_email']")
																			.next()
																			.html(
																					"");
																	$(
																			"input[name='forgotPassword_phone_email']")
																			.css(
																					{
																						"background-color" : "#eee",
																						"border" : "1px solid transparent"
																					});
																	flaga = true;
																} else if (result.result == 'N') {
																	// 验证失败
																	$(
																			"input[name='forgotPassword_phone_email']")
																			.next()
																			.html(
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
					// 发送验证码
					$("input[name='forgotPassword_verification_code']")
							.next()
							.click(
									function() {
										var verification = $(
												"input[name='forgotPassword_phone_email']")
												.val();
										if (flag1 == true) {
											var cxt = $("#cxt").val();
											var url = cxt + "/ForgotPassword";
											var para = {
												verification : verification
											};
											$
													.ajax({
														url : url,
														type : "post",
														dataType : "json",
														data : para,
														success : function(
																result) {
															if (null == result.error) {
																if (result.verification == 'YES') {
																	// 验证通过
																	$(
																			"input[name='registe_verification_code']")
																			.next()
																			.html(
																					"");
																	$(
																			"input[name='registe_verification_code']")
																			.css(
																					{
																						"background-color" : "#eee",
																						"border" : "1px solid transparent"
																					});
																} else if (result.verification == 'NO') {
																	// 验证失败
																	$(
																			"input[name='registe_verification_code']")
																			.next()
																			.html(
																					"发送失败!");
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
											$
													.ajax({
														url : url,
														type : "post",
														dataType : "json",
														data : para1,
														success : function(
																result) {
															if (null == result.error) {
																if (result.Verify == 'YES') {
																	// 验证通过
																	$(
																			"input[name='forgotPassword_verification_code']")
																			.next()
																			.html(
																					"");
																	$(
																			"input[name='forgotPassword_verification_code']")
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
																			.next()
																			.html(
																					"验证码错误!");
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
					$("input[name='forgotPassword_restpassword']")
							.blur(
									function() {
										var pwd = $(this).prev().prev().prev()
												.val();
										var repwd = $(this).val();
										// 判断二次密码是否符合要求
										if (pwd.length < 1) {
											$(this).next().html("确认密码不能为空");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (pwd != repwd) {
											$(this).next()
													.html("确认密码和密码需要保持一致");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (pwd.length >= 6) {
											$(this).next().html("");
											$(this)
													.css(
															{
																"background-color" : "#eee",
																"border" : "1px solid transparent"
															});
											flagd = true;
										}
									});
					// 忘记密码主页面提交验证
					$("#forgotPassword_submit")
							.click(
									function() {
										if (flaga == true && flagb == true
												&& flagd == true
												&& flagc == true) {
											var phone1 = $(
													"input[name='forgotPassword_phone_email']")
													.val();
											var pwd1 = $(
													"input[name='forgotPassword_password']")
													.val();
											var forgotPassword_verification_code = $(
													"input[name='forgotPassword_verification_code']")
													.val();
											var cxt = $("#cxt").val();
											var url = cxt + "/ForgotPassword";
											var param = {
												phone1 : phone1,
												pwd1 : pwd1
											};
											$
													.ajax({
														url : url,
														type : "post",
														dataType : "json",
														data : param,
														success : function(
																result) {
															if (null == result.error) {
																if (result.forgotPassword == 'OK') {
																	// 验证通过
																	window.location = cxt
																			+ "/user/G.jsp";
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
									})
					// 登录子界面关闭按钮
					$("input[name='login_phone_email']").parent().prev().click(
							function() {
								$("#login").hide();
								$("input[name='login_phone_email']").next()
										.html("");
								$("input[name='login_phone_email']").css({
									"background-color" : "#eee",
									"border" : "1px solid transparent"
								});
								$("input[name='login_password']").next().html(
										"");
								$("input[name='login_password']").css({
									"background-color" : "#eee",
									"border" : "1px solid transparent"
								});
								$("#login_login").prev().prev().html("");
							});
					// 点击上一页实现局部刷新
					$("#up").click(function() {
						var pageIndex = parseInt($("#pageIndex").val());
						if (pageIndex > 1) {
							pageIndex -= 1;
						}
						var type = $("#type").val();
						var pageCount = $("#pageCount").val();
						var totalCount = $("#totalCount").val();
						var goodsClassId = $("#goodsClassId").val();
						var params = {
							pageIndex : pageIndex,
							type : type,
							pageCount : pageCount,
							totalCount : totalCount,
							goodsClassId : goodsClassId
						};
						showGoods(params);
					})
					// 点击下一页实现局部刷新
					$("#down").click(function() {
						var pageIndex = parseInt($("#pageIndex").val());
						var pageCount = parseInt($("#pageCount").val());
						var type = $("#type").val();
						var totalCount = $("#totalCount").val();
						if (pageIndex < pageCount) {
							pageIndex += 1;
						}

						var goodsClassId = $("#goodsClassId").val();
						var params = {
							pageIndex : pageIndex,
							type : type,
							pageCount : pageCount,
							totalCount : totalCount,
							goodsClassId : goodsClassId
						};
						showGoods(params);
					})
					/* 轮播图，点击尖角符号切换图片 */
					var img = new Array("2.jpg", "4.jpg", "5.jpg");
					var flag = 1;
					$(".btn-right").click(function() {
						if (flag == img.length) {
							flag = 0;
						}

						$(".top-img-box").css({
							"background" : "url(img/" + img[flag] + ")",
							"background-size" : "100%",
							"background-repeat" : "no-repeat"
						})
						flag++;
					});

					$(".btn-left").click(function() {
						if (flag == -1) {
							flag = img.length - 1;
						}
						$(".top-img-box").css({
							"background" : "url(img/" + img[flag] + ")",
							"background-size" : "100%",
							"background-repeat" : "no-repeat"
						})
						flag--;
					});

					$(window).scroll(
							function() {
								var scrollT = $(document).scrollTop();
								var top = $(".center-classify").offset().top;
								if (scrollT > top) {
									$(".top-title-box").css("background-color",
											"rgb(27,27,27)");
								} else {
									$(".top-title-box").css("background",
											"none");
									$(".top-title-box").hover(
											function() {
												$(this).css("background",
														"rgb(27,27,27)");
											},
											function() {
												$(this).css("background",
														"none");
											})
								}
							})

					// 登录子界面
					var inPhone = "";
					var inPwd = "";

					// input框获得焦点边框变成黑色
					$("input").focus(function() {
						$(this).css("border", "1px solid #666");
					});

					// 登录子界面手机/邮箱验证
					$("input[name='login_phone_email']")
							.blur(
									function() {
										var check = /^1[3-9][0-3|5-9][0-9]{8}$/;
										var phone = $(this).val();
										if (phone.length < 1) {
											$(this).next().html("请输入手机号码/电子邮箱");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (check.test(phone) == false) {
											$(this).next().html(
													"请输入正确的手机号码/电子邮箱");
											$(this)
													.css(
															{
																"background-color" : "rgba(255,0,0,0.1)",
																"border" : "1px solid red"
															});
										} else if (check.test(phone) == true) {
											$(this).next().html("");
											$(this)
													.css(
															{
																"background-color" : "#eee",
																"border" : "1px solid transparent"
															});
											var cxt = $("#cxt").val();
											var url = cxt
													+ "/LoginVerifyAndQueryPersonal";
											var param = {
												phone1 : phone
											};
											$
													.ajax({
														url : url,
														type : "post",
														dataType : "json",
														data : param,
														success : function(
																result) {
															if (null == result.error) {
																if (result.YES == 'YES') {
																	// 验证通过
																	$(
																			"input[name='login_phone_email']")
																			.next()
																			.html(
																					"");
																	$(
																			"input[name='login_phone_email']")
																			.css(
																					{
																						"background-color" : "#eee",
																						"border" : "1px solid transparent"
																					});
																} else if (result.YES == 'NO') {
																	// 验证失败
																	$(
																			"input[name='login_phone_email']")
																			.next()
																			.html(
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
					$("#login_login")
							.click(
									function() {

										// 获取记住我勾选框是否勾选，如果勾选将生成相应的cookie
										var remember = $(this).prev().prev()
												.val();

										// 获取手机号码和密码
										var phone = $(
												"input[name='login_phone_email']")
												.val();
										var pwd = $(
												"input[name='login_password']")
												.val();

										// 异步请求路径
										var cxt = $("#cxt").val();
										var url = cxt
												+ "/LoginVerifyAndQueryPersonal";
										var param = {
											phone : phone,
											pwd : pwd,
											remember : remember
										};

										$
												.ajax({
													url : url,
													type : "post",
													dataType : "json",
													data : param,
													success : function(result) {
														if (null == result.error) {// 回调函数中错误为空证明没有异常发生
															if (result.result == 'Y') {
																// 验证通过
																window.location = cxt
																		+ "/user/gucci.jsp";
															} else {
																// 验证失败
																$(
																		"input[name='login_password']")
																		.next()
																		.html(
																				"用户名或密码错误！");
																$(
																		"input[name='login_password']")
																		.css(
																				{
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
						$("#forgotPassword").show();
					});
				})
// 获得商品的一级分类
function showGoodsClass() {
	var cxt = $("#cxt").val();
	var url = cxt + "/GoodsClassListServlet.do";
	$.ajax({
		url : url,
		data : {},
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var $li = "";
			$.each(list, function(index, item) {
				$li += "<li class='item1' id='" + item.goodsClassId
						+ "_itemId'>";
				$li += "</li>";
				getClassName(item.className, item.goodsClassId);
				$("#show_goods_class").html($li);
			})
			$(".item1").mouseover(function() {
				$(this).children("ul").show();
			}).mouseout(function() {
				$(this).children("ul").hide();
			})
		}
	})
}
// 获得二级分类
function getClassName(className, goodsClassId) {
	var cxt = $("#cxt").val();
	var params = {
		goodsClassId : goodsClassId
	};
	var url = cxt + "/GoodsClassListTwoServlet.do";
	var $ul_li = "";
	$.ajax({
		url : url,
		data : params,
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			$ul_li += "<span>" + className + "</span>";
			$ul_li += "<ul class='center-hide'><input type='hidden' value='"
					+ goodsClassId + "'/>";
			$.each(list, function(index, item) {
				$ul_li += "<li onclick='selects(" + item.goodsClassId + ")'>"
						+ item.className + "</li>";
				$ul_li += "<input type='hidden' value='" + item.goodsClassId
						+ "'/>";
			})
			$ul_li += "</ul>";
			$("#" + goodsClassId + "_itemId").html($ul_li);
			$(".item1>span").click(
					function() {
						var cxt = $("#cxt").val();
						// 点击获得该分类的ID
						var type = $(this).parent().children("ul").children(
								"input").val();
						$("#type").val(type);
						var pageIndex = $("#pageIndex").val();
						var pageCount = $("#pageCount").val();
						var totalCount = $("#totalCount").val();
						var params = {
							type : type,
							pageIndex : pageIndex,
							pageCount : pageCount,
							totalCount : totalCount
						};
						showGoods(params);
					})
		}
	})
}
// 显示二级
function selects(id) {
	var type = $("#type").val();
	var pageCount = $("#pageCount").val();
	var totalCount = $("#totalCount").val();
	var pageIndex = $("#pageIndex").val();
	var params = {
		pageIndex : pageIndex,
		type : type,
		pageCount : pageCount,
		totalCount : totalCount,
		goodsClassId : id
	};
	showGoods(params);
}
// 显示商品
function showGoods(params) {
	var cxt = $("#cxt").val();
	var url = cxt + "/ShowAllgoodsServlet.do";
	$.ajax({
		url : url,
		data : params,
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.error == null) {
				var list = data.list;
				$("#pageIndex").val(data.pageIndex);
				$("#totalCount").val(data.totalCount);
				$("#pageCount").val(data.pageCount);
				$("#ctotalCount").text(data.totalCount);
				$("#cpageIndex").text(data.pageIndex);
				$("#cpageCount").text(data.pageCount);
				$("#type").val(data.type);
				$("#goodsClassId").val(data.goodsClassId);
				var $list = "";
				$.each(list, function(index, item) {
					$list += "<div class='show_one' onclick='viewGoods("
							+ item.goodsId + ")'>";
					$list += "<div>"
					$list += "<img src='" + cxt + item.picPath
							+ "' width='300'"
					$list += "height='300' />"
					$list += "</div>"
					$list += "<div>"
					$list += "<p>" + item.goodsName + "</p>"
					$list += "<p>"
					$list += "<span>￥<b>" + item.price
							+ "</b></span><span>月销量<b>" + item.monthSale
							+ "</b>件"
					$list += "</span>"
					$list += "</p>"
					$list += "<p>立即购买&nbsp;&nbsp;&nbsp;></p>"
					$list += "</div>"
					$list += "</div>";
				})
				$("#sh").html($list);

				$(".show_one").hover(function() {
					$(this).children("div:nth-child(2)").show();
					$(this).css("background", "rgb(240,240,240)");
				}, function() {
					$(this).children("div:nth-child(2)").hide();
					$(this).css("background", "none");
				})
			}else{
				window.location.reload();
			}

		},
		error : function() {
			window.location.reload();
		}
	})
}

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