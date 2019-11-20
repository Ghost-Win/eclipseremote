<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String cxt = request.getContextPath();
	request.setAttribute("cxt", cxt);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cart</title>
<link href="${cxt }/user/css/cart.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript" src="${cxt }/common/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${cxt }/user/js/cart.js"></script>
<script type="text/javascript" src="${cxt }/common/js/header.js"></script>
<body>
	<input type="hidden" id="cxt" value="${cxt }">
	<c:import url="../common/header.jsp"></c:import>
	<div id="cart">
		<div id="empty">
			<div>
				<p>购物车空空如也！</p>
				<p class="goBuy">继续购物</p>
			</div>
		</div>
		<div id="noEmpty" class="clear">
			<div id="left_cart">
				<div>
					<input type="checkbox" /><span>全选</span>
				</div>
				<div>
					<ul id="cart_list">
						<c:forEach items="${sessionScope.cartList }" var="g">
							<li class="cart_list_veryOne">
								<ol>
									<li><input <c:if test="${g.statu==1 }"> checked </c:if> type="checkbox"/></li>
									<li><img src="${cxt }${g.goods.picPath }" width="200"
										height="200"></li>
									<li>
										<p>${g.goods.goodsName }</p>
										<p>${g.goods.goodsDesc }</p>
										<p>
											<a href="${cxt }/DeleteCartInfoServlet.do?goodsId=${g.goods.goodsId }">删&nbsp;&nbsp;除</a>
										</p>
									</li>
								</ol>
								<ol>
									<li>
										<div class="do_count">
											<span class="reduce">-</span> <span>${g.buyCount }</span>
											<span class="add">+</span> 
											<input type="hidden" value="${g.goods.goodsCount }"> 
											<input type="hidden" value="${g.goods.price }"> 
											<input type="hidden" value="${g.goods.goodsId }">
										</div>
									</li>
									<li>
										<div>
											￥&nbsp;&nbsp;<span title="总价">${g.prices }</span>
										</div>
									</li>
								</ol>
							</li>
						</c:forEach>
					</ul>
					<div id="delete_empty">清空购物车</div>
				</div>
			</div>
			<div id="right_cart">
				<ul>
					<li><span>订单小计</span> <span>已选&nbsp;&nbsp;</span> <span
						id="checked_count">0</span> <span>&nbsp;&nbsp;件商品</span></li>
					<li>
						<p>
							<span>商品总计</span> <span>￥<b id="price">0</b></span>
						</p>
						<p>
							<span>运费</span> <span>￥<b id="yun">0</b></span>
						</p>
						<p>
							<span>总计</span> <span>￥<b id="total_price">0</b></span>
						</p>
					</li>
					<li>
						<p>说明</p>
						<p>
							<i>订单提交之后1小时内未付款，订单将被系统自动取消，请您尽快完成支付以确保商品能及时送达，有货商品和门店配货商品是分开寄出。</i>
						</p>
					</li>
					<li>
						<p id="buy" style="background: rgb(229, 223, 217);">立即结算</p>
						<p class="goBuy">继续购物</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<c:import url="../common/map.jsp"></c:import>
	<div id="isBuy">
		<div>
			<p>结算</p>
			<div>
				<p>
					<span id="address">选择收货地址</span><span id="addAddress">新增收货地址</span>
				</p>
				<ul id="address1"></ul>
				<div id="addAddress1">
					<span>请填写新的收货地址</span><input type="text" />
				</div>
			</div>
			<div>
				你一共需要支付<br />
				<b id="thisPrice"></b><br />元
			</div>
			<div>
				<span id="yesBuy">确定支付</span><span id="noBuy">我再想想</span>
			</div>
		</div>
	</div>
</body>
</html>