$(document).ready(
		function() {
			//页面刷新加载该用户的地址
			shiptoaddress();
			totalPrices();
			//点击立即结算（添加商品信息到订单，删除购物车中预支对应的信息）
			$("#buy").click(function(){
				//判断是否有商品算中
				var len=$(".cart_list_veryOne input[type=checkbox]:checked").length;
				if(len>0){
					//有选中的商品
					$(isBuy).show(1000);
				}else{
					alert("你未选择商品。请先选择你要购买的商品。")
				}
			});
			//点击收货地址和新增地址切换
			$("#addAddress").click(function(){
				$("#addAddress1").show();
				$("#address1").hide();
			})
			$("#address").click(function() {
				$("#address1").show();
				$("#addAddress1").hide();
			})
			//点击我再想想，退出，不结算
			$("#noBuy").click(function(){
				$("#isBuy").hide(1000);
			})
			$(".cart_list_veryOne input[type=checkbox]").change(function(){
				//记录该商品的状态（是否选中）。
				totalPrices();
				var statu;
				if($(this).prop("checked")){
					statu=1;
				}else{
					statu=0;
				}
				var goodsId=$(this).parent().parent().next().children()
				.children().children("input:nth-last-child(1)").val();
				var params={statu:statu,goodsId:goodsId};
				var cxt = $("#cxt").val();
				var url=cxt+"/UpdateCartStatuServlet.do";
				$.ajax({
					url:url,
					data:params,
					dataType:"json",
					type:"post"
				})
			})
			// 判断购物车是否为空
			liIsEmpty();
			// 点击全选，下面的所有input选中
			$("#left_cart>div:nth-child(1)>input[type=checkbox]").click(
					function() {
						if (!$(this).checked) {
							$(".cart_list_veryOne input[type=checkbox]").attr('checked', 'true');
						}
					})
			// 判断整个网页中input 选中的个数改变结算按钮的颜色
			$("#left_cart input[type=checkbox]").change(function() {
				totalPrices();
				isChangeColor();
			})
			//页面刷新判断按钮的颜色
			isChangeColor();
			
			//判断结算按钮的颜色
			function isChangeColor() {
				var chks = $("input:checked");
				var len = chks.length;
				if (len == 0) {
					$("#buy").css("background", "rgb(229,223,217)");
				} else {
					$("#buy").css("background", "rgb(45,45,45)");
					totalPrices();
				}
			}
			// 计算总价
			function totalPrices() {
				var c = $(".cart_list_veryOne input[type=checkbox]");
				var prices = 0;
				for (var i = 0; i < c.length; i++) {
					if (c.get(i).checked) {
						// 总价
						var price = parseInt($(c[i]).parent().parent().next()
								.children("li:nth-child(2)").children()
								.children("span").text());
						prices += price;
					}
				}

				// 如果价格小于20000，不付运费，大于2w，每2w付运费500
				var yunFei = parseInt(prices / 20000) * 500;
				$("#price").text(prices);
				$("#yun").text(yunFei);
				$("#total_price").text(prices + yunFei);
				$("#thisPrice").text(prices + yunFei);
			}
			// 点击继续购物
			$(".goBuy").click(function() {
				var cxt = $("#cxt").val();
				location.href = cxt + "/user/gucci.jsp";
			})
			// 点击删除全部购物车的信息
			$("#delete_empty").click(function() {
				var cxt = $("#cxt").val();
				$("#cart_list li").remove();
				$(this).hide();
				var url = cxt + "/deleteCartAllList.do";
				$.ajax({
					url : url,
					data : {},
					type : "post"
				});
				liIsEmpty();
			})
			// 判断购物车是否为空
			function liIsEmpty() {
				var len = $('.cart_list_veryOne');
				if (len.length < 1) {
					$("#delete_empty").hide();
					$("#empty").show();
					$("#noEmpty").hide();
				} else {
					$("#delete_empty").show();
					$("#empty").hide();
					$("#noEmpty").show();
				}
			}
			// 点击增加数量
			$(".add").click(
					function() {
						var count = parseInt($(this).prev().text());
						var yuCount = parseInt($(this).next().val());
						if (count < yuCount) {
							count++;
						} else {
							alert("库存不够啦..^v^");
							count = yucount;
						}
						$(this).prev().text(count);

						// 计算该单个商品的总价
						var price = parseInt($(this).next().next().val());
						$(this).parent().parent().next().children().children()
								.text(price * count);
						// 请求页面，将加入的数据放入session中,请求增加商品信息的页面
						var goodsId = $(this).next().next().next().val();
						var cxt = $("#cxt").val();
						var url = cxt + "/InsertIntoCartServlet.do";
						$.ajax({
							url : url,
							data : {
								goodsId : goodsId
							},
							type : "post",
							success : function(data) {
								if (data == "true") {
									// 添加成功后执行的函数（减商品的数量，增加月销量）
									ifInsertOk(goodsId);
								}
							}
						});
						totalPrices();
					});
			// 点击减少商品
			$(".reduce").click(
					function() {
						var count = parseInt($(this).next().text());
						if (count > 1) {
							count--;
						} else {
							alert("再减就没有啦..^v^")
							count = 1;
						}
						$(this).next().text(count);
						// 计算该单个商品的总价
						var price = parseInt($(this).next().next().next()
								.next().val());
						$(this).parent().parent().next().children().children()
								.text(price * count);
						// 请求页面，将加入的数据放入session中,请求增加商品信息的页面
						var goodsId = $(this).next().next().next().next()
								.next().val();
						var cxt = $("#cxt").val();
						var url = cxt + "/InsertIntoCartServlet.do";
						$.ajax({
							url : url,
							data : {
								goodsId : goodsId
							},
							type : "post",
							success : function(data) {
								if (data == "true") {
									// 减少成功后成功后执行的函数（增加商品的数量，减少月销量）
									ifReduceOk(goodsId);
								}
							}
						});
						totalPrices();
					})
		})
// 购买成功后执行的函数（减商品的数量）
function ifInsertOk(goodsId) {
	var cxt = $("#cxt").val();
	var url = cxt + "/ReduceGoodsCountServlet.do";
	$.ajax({
		url : url,
		data : {
			goodsId : goodsId
		},
		type : "post"
	})
}
// 减少成功后执行的函数（增加商品的数量，减少月销量）
function ifReduceOk(goodsId) {
	var cxt = $("#cxt").val();
	var url = cxt + "/ADDGoodsCountServlet.do";
	$.ajax({
		url : url,
		data : {
			goodsId : goodsId
		},
		type : "post"
	})
}
//页面刷新加载该用户的地址
function shiptoaddress() {
	var cxt = $("#cxt").val();
	var url=cxt+"/AddressManageServlet.do";
	var userId=$("#userId").val();
	var param={userId:userId};
	$.ajax({
		url:url,
		data:param,
		dataType:"json",
		type:"get",
			success:function(data){
			var list=data.list;
			$.each(list,function(index,item){
				var $li="<li>"+item.address+"<input type='checkbox' /><input type='hidden' value='"+item.addressId+"'></li>";
				$("#address1").append($li);
			})
			//点击地址后面的选中框(控制只能选择一个地址)
			//获得用户选择的地址信息 或者  填写的地址信息
			$("#address1>li>input[type=checkbox]").change(function(){
				if($(this).prop("checked")){
					$(this).parent().siblings().children("input[type=checkbox]").prop("checked",false);
					$(this).parent().css("background","#fff");
					$(this).parent().siblings().css("background","rgb(220,220,220)");
					selectAddress=$(this).next().val();
				}
			});
			$("#yesBuy").click(function() {
				if($("#address1>li>input[type=checkbox]:checked")){
					var selectAddress=$("#address1>li>input[type=checkbox]:checked").next().val();
					//记录地址ID ()
					//获得用户填写的信息()
					//用户的ID
					var userId=$("#userId").val();
					var inputAddress=$("#addAddress1>input").val();
					addAddress(selectAddress,inputAddress,userId);
				}
			})
		}
	})
}
function addAddress(selectAddress,inputAddress,userId) {
	var cxt=$("#cxt").val();
	var url=cxt+"/AddAddressServlet.do";
	var params={selectAddress:selectAddress,inputAddress:inputAddress,userId:userId};
	$.ajax({
		url:url,
		data:params,
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="null"){
				alert("请选择收货地址！")
			}
			if(data.result=="OK"){
				window.location.reload();
			}
		}
	})
}






