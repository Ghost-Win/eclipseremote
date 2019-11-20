$(document).ready(function() {
	// 页面刷新显示一级二级分类的名称
	show_one();
	show_two();
	showClassName();
	var cxt = $("#cxt").val();
	$("#GoodsClassList").click(function() {
		location.href = cxt + "/user/G.jsp";
	})
	// 商品盒子上一页
	$("#upPage").click(function() {
		var pageIndex = parseInt($("#cpageIndex").text());
		var type=$("#type").val();
		if (pageIndex > 1) {
			pageIndex -= 1;
		}
		showGoods(pageIndex,type);
	});
	// 商品盒子下一页
	$("#downPage").click(function() {
		var pageIndex = parseInt($("#cpageIndex").text());
		var totalPage = parseInt($("#ctotalPage").text());
		var type=$("#type").val();
		if (pageIndex < totalPage) {
			pageIndex += 1;
		}
		showGoods(pageIndex,type);
	});
	// 订单盒子上一页
	$("#oupPage").click(function() {
		var pageIndex = parseInt($("#opageIndex").text());
		if (pageIndex > 1) {
			pageIndex -= 1;
		}
		var status = $("#status").val();
		$(".order_box").remove();
		showOrder(pageIndex, status);
	});
	// 订单盒子下一页
	$("#odownPage").click(function() {
		var pageIndex = parseInt($("#opageIndex").text());
		var totalPage = parseInt($("#ototalPage").text());
		if (pageIndex < totalPage) {
			pageIndex += 1;
		} else {
			pageIndex = totalPage;
		}
		var status = $("#status").val();
		$(".order_box").remove();
		showOrder(pageIndex, status);
	});

	// 点击商品管理按钮，切换相应操作选项及显示区div-goods-pic内容
	$(".goods-manage").click(function() {
		$(this).css("background-color", "white");
		$(this).siblings().css("background-color", "gainsboro");
		$("#p1").show();
		$("#p2").hide();
		$("#p3").hide();
		$("#add_goods").hide();
		$("#manage_order").hide();
		$("#sale_count").hide();
		$(".goods-pic").show();
		var pageIndex = $("#pageIndex").val();
		showGoods(pageIndex);
	})
	// 点击订单管理按钮，切换相应操作选项及显示区<div id="manage_order">内容
	$(".order-manage").click(function() {
		$(".order_box").remove();
		$(this).css("background-color", "white");
		$(this).siblings().css("background-color", "gainsboro");
		$("#p1").hide();
		$("#p2").show();
		$("#p3").hide();
		$(".goods-pic").hide();
		$("#add_goods").hide();
		$("#sale_count").hide();
		$("#manage_order").show();
		// 获取隐藏域中的当前页码
		var pageIndex = $("#pageIndex").val();
		showOrder(pageIndex);
	})

	// 点击销量统计按钮，切换相应操作选项及显示区内容
	$(".sale-count").click(function() {
		$(this).css("background-color", "white");
		$(this).siblings().css("background-color", "gainsboro");
		$("#p1").hide();
		$("#p3").show();
		$("#p2").hide();
		$(".goods-pic").hide();
		$("#add_goods").hide();
		$("#manage_order").hide();
		$("#sale_count").show();
	})
	// 点击添加商品按钮显示添加商品盒子
	$("#addGoods").click(function() {
		$(".goods-pic").hide();
		$("#add_goods").show();
	});
	// 点击添加商品页面的取消按钮返回显示商品盒子
	$("[type=reset]").click(function() {
		$("#add_goods").hide();
		$(".goods-pic").show();
	});

	// 获取隐藏域中的当前页码
	var pageIndex = $("#pageIndex").val();
	// 调用显示所有商品方法
	showGoods(pageIndex);
})

/* ————————商品管理功能———————— */
// 显示所有商品方法
function showGoods(pageIndex, type) {
	var cxt = $("#cxt").val();
	var params = {
		pageIndex : pageIndex,
		type : type
	};
	$.ajax({
		url : cxt + "/AdminServletDo",
		data : params,
		type : "post",
		dataType : "JSON",
		success : callBack,
		error : function() {
			alert(cxt);
		}
	});
	function callBack(data) {
		if (data.list == null) {
			$(".goods-pic").html("当前没有任何商品");
		} else {
			var list = data.list;
			// 显示页码和总页数
			$("#cpageIndex").text(data.pageIndex);
			$("#ctotalPage").text(data.totalPage);
			$("#pageIndex").val(data.pageIndex);
			$("#type").val(type);
			$("#show_goods").html("");
			$(list).each(
			function(index, item) {
				var temp = ""
				temp = "<div class='goods-pic1'>" + "<p>"
						+ "<span id='"
						+ item.goodsId
						+ "_desc'></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "<input type='hidden' value='"
						+ item.status
						+ "' id='"
						+ item.goodsId
						+ "_status'/>"
						+ "<button type='button' onclick='deleteGoods("
						+ item.goodsId
						+ ","
						+ item.status
						+ ")'>上架/下架</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "<input type='hidden' value='"
						+ item.goodsId
						+ "'/>"
						+ "</p>"
						+ "<img src='"
						+ cxt
						+ item.picPath
						+ "'/>"
						+ "<ul>"
						+ "<li>"
						+ "<span>价格:￥</span>"
						+ "<input id='"
						+ item.goodsId
						+ "_price' class='item_price'  type='text'value='"
						+ item.price
						+ "'/>"
						+ "<span>名称:</span>"
						+ "<input id='"
						+ item.goodsId
						+ "_goodsName' type='text'value='"
						+ item.goodsName
						+ "'/>"
						+ "</li>"
						+ "<li>"
						+ "<span>库存:</span>"
						+ "<input id='"
						+ item.goodsId
						+ "_goodsCount' type='text' value='"
						+ item.goodsCount
						+ "'/>"
						+ "<span>销量:</span>"
						+ "<input id='"
						+ item.goodsId
						+ "_monthSale' type='text' value='"
						+ item.monthSale
						+ "'/>"
						+ "</li>"
						+ "</ul>"
						+ "<p>"
						+ "<button type='button' onclick='updateGoods(\""
						+ item.goodsId
						+ "\")'>修改信息</button>"
						+ "</p>" + "</div>";
				$("#show_goods").append(temp);
				// 遍历完元素后添加input失去焦点事件
				// 判断价格
				if ($("#" + item.goodsId + "_status").val() == '0') {
					$("#" + item.goodsId + "_desc").text("【  下架中  】");
				} else {
					$("#" + item.goodsId + "_desc").text("【  上架中  】");
				}
				var price = $("#" + item.goodsId + "_price")
						.val();
				$("#" + item.goodsId + "_price")
						.blur(
								function() {
									var temp = $(
											"#" + item.goodsId
													+ "_price")
											.val();
									if (isNaN(temp)) {
										alert("商品价格必须是数字类型！");
										$(
												"#"
														+ item.goodsId
														+ "_price")
												.val(price);
									} else if (temp <= 0) {
										alert("商品价格必须大于0");
										$(
												"#"
														+ item.goodsId
														+ "_price")
												.val(price);
									} else {
									}
								});

				// 判断库存
				var goodsCount = $("#" + item.goodsId + "_goodsCount")
						.val();
				$("#" + item.goodsId + "_goodsCount")
						.blur(
								function() {
									var temp = $(
											"#"
													+ item.goodsId
													+ "_goodsCount")
											.val();
									if (isNaN(temp)) {
										alert("商品库存必须数字类型！");
										$(
												"#"
														+ item.goodsId
														+ "_goodsCount")
												.val(goodsCount);
									} else if (temp < 0) {
										alert("商品库存不能为负数");
										$(
												"#"
														+ item.goodsId
														+ "_goodsCount")
												.val(goodsCount);
									} else {
									}
								});
				// 判断销量
				var monthSale = $(
						"#" + item.goodsId + "_monthSale")
						.val();
				$("#" + item.goodsId + "_monthSale").blur(
						function() {
							var temp = $(
									"#" + item.goodsId
											+ "_monthSale")
									.val();
							if (isNaN(temp)) {
								alert("商品销量必须是数字类型！");
								$(
										"#" + item.goodsId
												+ "_monthSale")
										.val(monthSale);
							} else if (temp < 0) {
								alert("商品销量不能为负数");
								$(
										"#" + item.goodsId
												+ "_monthSale")
										.val(monthSale);
							} else {
							}
						});
			});
		}
	}
}
// 下架商品绑定点击按钮
function deleteGoods(goodsId, status) {
	if (status == 1) {
		var cxt = $("#cxt").val();
		var pageIndex = parseInt($("#cpageIndex").text());
		// 在遍历商品显示商品方法中获得当前删除按钮对应商品的id，
		// 并保存在隐藏域中
		$.ajax({
			url : cxt + "/deleteGoodsByIdServlet",
			data : {
				goodsId : goodsId,
				status : status
			},
			type : "post",
			dataType : "JSON",
			success : function(data) {
				if (data.error == null) {
					if (data.result == 'ok') {
						alert("商品下架成功！");
						// 再次查询显示所有商品
						showGoods(pageIndex);
					} else {
						alert("下架失败！");
					}
				} else {
					alert("订单未完成，不能下架该商品!");
				}
			},
			error : function() {
				alert("订单未完成，不能下架该商品!");
			}
		})
	} else {
		var cxt = $("#cxt").val();
		var pageIndex = parseInt($("#cpageIndex").text());
		// 在遍历商品显示商品方法中获得当前删除按钮对应商品的id，
		// 并保存在隐藏域中
		$.ajax({
			url : cxt + "/deleteGoodsByIdServlet",
			data : {
				goodsId : goodsId,
				status : status
			},
			type : "post",
			dataType : "JSON",
			success : function(data) {
				if (data.error == null) {
					if (data.result == 'ok') {
						alert("商品上架成功！");
						// 再次查询显示所有商品
						showGoods(pageIndex);
					} else {
						alert("上架失败！");
					}
				} else {
					alert("订单未完成，不能上架该商品!");
				}
			},
			error : function() {
				alert("订单未完成，不能上架该商品!");
			}
		})
	}

}
// --------修改商品前添加鼠标失去焦点判断用户输入是否正确的方法

// 修改商品信息绑定点击按钮
function updateGoods(goodsId) {
	var cxt = $("#cxt").val();

	var price = $("#" + goodsId + "_price").val();
	var goodsName = $("#" + goodsId + "_goodsName").val();
	var goodsCount = $("#" + goodsId + "_goodsCount").val();
	var monthSale = $("#" + goodsId + "_monthSale").val();

	$.ajax({
		url : cxt + "/updateGoodsServlet",
		data : {
			price : price,
			goodsName : goodsName,
			goodsCount : goodsCount,
			monthSale : monthSale,
			goodsId : goodsId
		},
		type : "post",
		dataType : "JSON",
		success : function(data) {
			if (data.result == 'ok') {
				alert("商品修改成功！");
				// 再次查询显示所有商品
				showGoods();
			} else {
				alert("修改失败！");
			}
		}
	})
}
/* ————————订单管理功能———————— */

// 显示所有订单的方法
function showOrder(pageIndex, status) {
	var cxt = $("#cxt").val();
	var params = {
		pageIndex : pageIndex,
		status : status
	};
	$.ajax({
		url : cxt + "/showOrderServlet",
		data : params,
		type : "post",
		dataType : "JSON",
		success : function(data) {
			// 取出数据库中所有订单，并用js遍历出每个订单对象显示到页面
			var list = data.list;
			// 获得后台返回的页码和总页数并显示
			$("#opageIndex").text(data.pageIndex);
			$("#ototalPage").text(data.totalPage);
			$("#status").val(data.status);
			// 同时将当前页码存储到隐藏域中
			$("#pageIndex").val(data.pageIndex);
			$(".order_box").remove();
			$.each(list, function(index, item) {
				var temp = ""
				temp = "<div id='order_box" + item.orderId
						+ "' class='order_box'>" + "<div>"
						+ "<p>订单编号:&nbsp;&nbsp;" + "<span>" + item.orderId
						+ "</span>" + "</p>" + "<p>购买数量:&nbsp;&nbsp;"
						+ "<span>" + item.buyCount + "</span>" + "</p>"
						+ "<p><span></span><input type='hidden' value='"
						+ item.status + "'/></p>" + "</div>" + "<div>"
						+ "<div>" + "<div>" + "<img  src='" + cxt
						+ item.goods.picPath + "'>" + "</div>" + "<div>"
						+ "<span>" + item.goods.goodsName + "</span>"
						+ "</div>" + "</div>" + "<div>" + "<span>￥</span>"
						+ "<span>" + item.totalPrices + "</span>" + "</div>"
						+ "<div>" + "<span>" + item.name + "</span>" + "</div>"
						+ "<div>" + "<span>" + item.address + "</span>"
						+ "</div>" + "<div>" + "<button onclick='deleteOrder("
						+ item.orderId + "," + item.status + ")'>删除订单</button>"
						+ "</div>" + "</div>" + "</div>";
				$("#manage_order").append(temp);
				var changeStatus = $(".order_box input");
				for (var i = 0; i < changeStatus.length; i++) {
					if ($(changeStatus.get(i)).val() == 0) {
						$(changeStatus.get(i)).prev().text("未完成");
					} else {
						$(changeStatus.get(i)).prev().text("已完成");
					}
				}

			})
		}
	})
}
// 删除订单绑定点击按钮
function deleteOrder(orderId, status) {
	var cxt = $("#cxt").val();
	// var pageIndex=parseInt($("#opageIndex").text());
	// 在遍历商品显示商品方法中获得当前删除按钮对应商品的id，
	// 并保存在隐藏域中
	$.ajax({
		url : cxt + "/deleteOrderByIdServlet",
		data : {
			orderId : orderId,
			status : status
		},
		type : "post",
		dataType : "JSON",
		success : function(data) {
			if (data.error == null) {
				if (data.result == 'ok') {
					alert("订单删除成功！");
					// 再次查询显示所有订单
					$(".order_box").remove();
					showOrder(1);
				} else if (data.result == "fail") {
					alert("订单未签收，不能删除该订单!");
				}
			} else {
				alert("订单未完成，不能删除该订单!");
			}
		},
		error : function() {
			alert("订单未完成，不能删除该订单!");
		}
	})
}
// 读取一级分类并在页面显示
function show_one() {
	var cxt = $("#cxt").val();
	var url = cxt + "/ShowOneClassName";
	$.ajax({
		url : url,
		data : {},
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			$("#f-type").html("");
			$.each(list, function(index, item) {
				var $opsion = "<option value='" + item.goodsClassId + "'>"
						+ item.className + "</option>";
				$("#f-type").append($opsion);
			})
		}
	})
}
// 读取二级分类并在页面显示
function show_two() {
	var cxt = $("#cxt").val();
	var url = cxt + "/ShowTwoClassName";
	$.ajax({
		url : url,
		data : {},
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			$("#s-type").html("");
			$.each(list, function(index, item) {
				var $opsion = "<option value='" + item.goodsClassId + "'>"
						+ item.className + "</option>";
				$("#s-type").append($opsion);
			})
		}
	})
}
// 点击已完成订单查询
function showDoneOrder() {
	var pageIndex = $("#pageIndex").val();
	var status = 1;
	showOrder(1, status);
}
// 点击已完成订单查询
function showUndoneOrder() {
	var pageIndex = $("#pageIndex").val();
	var status = 0;
	showOrder(1, status);
}

function showClassName() {
	var cxt = $("#cxt").val();
	var url = cxt + "/GoodsClassListServlet.do";
	$.ajax({
		url : url,
		data : {},
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var option = "";
			$.each(list, function(index, item) {
				option = "<option value='" + item.goodsClassId + "'>" + item.className
						+ "</option>";
				$("#select1").append(option);
			})

			$("#goodSelectType").click(function() {
				var pageIndex = $("#pageIndex").val();
				var type = $("#select1").val();
				showGoods(pageIndex, type);
			})
		}
	})
}
