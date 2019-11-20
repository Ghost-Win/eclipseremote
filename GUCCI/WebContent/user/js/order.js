$(document).ready(function() {
	// 加载显示我看过的商品
	show_shoucang();
	$("#pei_song>li>p:nth-child(1)>b").click(function() {
		$(this).parent().next().toggle(300);
		if ($(this).text() == "+") {
			$(this).text("－");
		} else if ($(this).text() == "－") {
			$(this).text("+");
		}
	});
	showOrder();
	$("#back>span").click(function() {
		location.href = $("#cxt").val() + "/user/gucci.jsp";
	})
})
// 查看用户的订单信息
function showOrder() {
	var cxt = $("#cxt").val();
	var userId = $("#userId").val();
	var params = {
		userId : userId
	};
	var url = cxt + "/ShowOrderListServlet.do";
	$.ajax({
		url : url,
		data : params,
		type : "post",
		dataType : "json",
		success : function(data) {
			var list = data.list;
			var $li = "";
			$.each(list, function(index, item) {
				$li += "<li class='order_list'><div>";
				$li += "<img src='" + cxt + item.picPath
						+ "' width='100px' height='100px' /> <img src='" + cxt
						+ item.picPath + "'";
				$li += "width='220px' height='220px' />";
				$li += "</div>";
				$li += "<div>" + item.goodsName + "</div>";
				$li += "<div>";
				$li += "购买数量&nbsp;&nbsp;<b>" + item.buyCount + "</b>";
				$li += "</div>";
				$li += "<div>";
				$li += "总价&nbsp;&nbsp;<b>" + item.totalPrices + "</b>";
				$li += "</div>";
				$li += "<div>";
				$li += "<input type='hidden' value='" + item.orderId + "'/>";
				$li += "<input type='hidden' value='" + item.status + "'/>";
				$li += "<span></span>";
				$li += "</div></li>";
			})
			$("#order_show").html($li);
			var input = $(".order_list input");
			for (var i = 0; i < input.length; i++) {
				if ($(input.get(i)).val() == "0") {
					$(input.get(i)).next().text("请签收");
					$(input.get(i)).next().click(
							function() {
								// 点击签收，签收订单(修改订单状态)
								var orderId = $(this).prev().prev().val();
								var params = {
									orderId : orderId
								};
								var url = $("#cxt").val()
										+ "/UpdateOrderStatusServlet.do";
								$.ajax({
									url : url,
									data : params,
									type : "post",
									dataType : "text",
									success : function(data) {
										if (data == "true") {
											showOrder();
										}
									}
								})
							})
				} else {
					$(input.get(i)).next().text("已签收").css({
						"background" : "none",
						"color" : "rgb(255,0,54)"
					});
					$(input.get(i)).next().attr({
						"title" : "该商品已经签收！"
					})
				}
			}
			// 移动显示大图
			$(".order_list>div:nth-child(1)>img:nth-of-type(1)").mouseover(
					function() {
						$(this).next().show();
					})
			$(".order_list>div:nth-child(1)>img:nth-of-type(2)").mouseout(
					function() {
						$(this).hide();
					})
		}
	})
}
function show_shoucang() {
	var cxt = $("#cxt").val();
	var url = cxt + "/ShowMySelectGoods.do";
	$.ajax({
		url : url,
		data : {},
		type : "post",
		dataType : "json",
		success : function(data) {
			if (data.list==null) {
				var $a = "<a href='"+cxt+"/user/gucci.jsp'>暂时没有浏览过任何商品，快去看看吧&nbsp;&nbsp;  >></a>";
				$("#show_choucang").html($a);
			} else {
				var list = data.list;
				var $list = "";
				$.each(list, function(index, item) {
					$list += "<div class='show_one' onclick='viewGoods(" + item.goodsId + ")'>";
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
				$("#show_choucang").html($list);

				$(".show_one").hover(function() {
					$(this).children("div:nth-child(2)").show();
					$(this).css("background", "rgb(240,240,240)");
				}, function() {
					$(this).children("div:nth-child(2)").hide();
					$(this).css("background", "none");
				})
			}
		}
	})
}
//点击跳转商品详情页面
function viewGoods(goodsId) {
	var cxt = $("#cxt").val();
	location.href = cxt + "/user/showShopping.jsp?goodsId=" + goodsId;
}