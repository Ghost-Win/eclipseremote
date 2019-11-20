<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cxt = request.getContextPath();
	request.setAttribute("cxt", cxt);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showGoods</title>
<link href="${cxt }/user/css/shopping.css" rel="stylesheet"
	type="text/css">
</head>
<script type="text/javascript" src="${cxt }/common/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${cxt }/user/js/showGoods.js"></script>
<script type="text/javascript" src="${cxt }/common/js/header.js"></script>
<body>
	<input type="hidden" value="${param.goodsId }" id="goodsId">
	<input type="hidden" value="${cxt}" id="cxt">
	<c:import url="../common/header.jsp"></c:import>
	<div id="show_goods">
		<div>
			<div id="goods_img">
				<span style="font-size: 13px;">所属分类：</span><span
					style="color: rgb(255, 0, 54); font-weight: 700; font-size: 18px;"
					id="show_class_name"></span><img id="show_img" width="450"
					height="450" />
			</div>
			<div id="goods_price">
				<ul>
					<li id="show_name"></li>
					<li>￥&nbsp;&nbsp;<span id="show_price"></span>&nbsp;&nbsp;元
					</li>
					<li><span>商品剩余数量&nbsp;&nbsp;<b id="show_count"></b>&nbsp;&nbsp;件
					</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span>月销量&nbsp;&nbsp;<b id="show_month_sale"></b>&nbsp;&nbsp;件
					</span></li>
					<li id="show_desc" style="margin-top: -15px; height: 80px;"></li>
					<li style="position: relative;"><span id="incart">加入购物袋</span><img width='50' height='50'
					 id="show_small_img"
					style="border-radius: 50%;position: absolute;top: 0;left: 320px;display: none;"></li>
					<li><a
						href="http://wpa.qq.com/msgrd?v=3&uin=&site=qq&menu=yes"> <img
							src="${cxt }/user/img/counseling_style_52.png" />
					</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span>选购咨询：17738756885</span>
					</li>
					<li>将受实际库存影响，预计15-16周左右发货</li>
				</ul>
			</div>
		</div>
		<hr>
		<div id="back">
			<span>返回主页</span>
		</div>
	</div>
	<c:import url="../common/map.jsp" />
</body>
</html>