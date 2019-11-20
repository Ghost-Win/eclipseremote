/**
 * 
 */
$(document).ready(function() {
	var cxt = $("#cxt").val();
	var goodsId = $("#goodsId").val();
	var url = cxt + "/GoodsViewServlet.do";
	$.ajax({
		url : url,
		data : {
			goodsId : goodsId
		},
		type : "post",
		dataType : "json",
		success : function(data) {
			var g = data.goods;
			var gc = data.gc;
			$("#show_name").text(g.goodsName);
			$("#show_price").text(g.price);
			$("#show_count").text(g.goodsCount);
			$("#show_month_sale").text(g.monthSale);
			$("#show_desc").text(g.goodsDesc);
			$("#show_img").attr("src", "" + cxt + g.picPath + "");
			$("#show_class_name").text(gc.className);
			$("#show_small_img").attr("src", "" + cxt + g.picPath + "");
		}
	})
	$("#back>span").click(function() {
		var cxt = $("#cxt").val();
		location.href = cxt + "/user/gucci.jsp";
	})
	//点击返回
	$("#incart").click(function() {
		var cxt = $("#cxt").val();
		var userId=$("#userId").val();
		if(userId==null || userId==""){
			location.href=cxt+"/user/G.jsp";
			return;
		}
		// 判断是否还有存货，若果有，加入购物车，如果没有则提示
		var count = parseInt($("#show_count").text());
		if (count > 0) {
			

			$(this).next().show().animate({
				'top' : '-580px',
			}, 800).hide(500);
			$(this).next().animate({
				'top' : '0',
			});

			// 添加到购物车
			var goodsId = $("#goodsId").val();
			var url = cxt + "/InsertIntoCartServlet.do";
			var userId=$("#userId").val();
			$.ajax({
				url : url,
				data : {
					goodsId : goodsId,
					userId:userId
				},
				type : "post",
				success : function(data) {
					if (data = "true") {
						// 成功后执行的函数(商品数量减一//月销量加一//购物车的数量加一)
						ifInsertOk();
					}
				}
			})
		} else {
			alert("我们会尽快补货，你可以浏览其他商品。");
		}
	});
})
// 购买成功后执行的函数（减商品的数量）
function ifInsertOk() {
	var cxt = $("#cxt").val();
	var goodsId = $("#goodsId").val();
	var url = cxt + "/ReduceGoodsCountServlet.do";
	var count = parseInt($("#show_count").text());
	var yucount = parseInt($("#show_month_sale").text());
	yucount = yucount + 1;
	count = count - 1;
	$("#show_count").text(count);
	$("#show_month_sale").text(yucount);
	$.ajax({
		url : url,
		data : {
			goodsId : goodsId
		},
		type : "post"
	})
}