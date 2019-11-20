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
<title>order</title>
<link href="${cxt }/user/css/order.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript" src="${cxt }/common/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${cxt }/user/js/order.js"></script>
<script type="text/javascript" src="${cxt }/common/js/header.js"></script>
<body>
	<c:import url="../common/header.jsp" />
	<div id="order">
		<input type="hidden" id="cxt" value=${cxt }>
		<ul id='order_show'></ul>
		<ol id="pei_song">
			<li>免费配送</li>
			<li>
				<p>
					<span>需求帮助</span><b>+</b>
				</p>
				<p>
					<span>在线顾问</span> <span>400.8210.582</span> <span>contact@cn.customercare.gucci</span>
				</p>
			</li>
			<li>
				<p>
					<span>支付方式</span><b>+</b>
				</p>
				<p>
					<span>支付宝</span> <span>微信支付</span> <span>货到付款</span> <span>花呗分期</span>
				</p>
			</li>
			<li>
				<p>
					<span>礼品包装</span><b>+</b>
				</p>
				<p>
					<span>免费礼品包装</span><img src="" />
				</p>
			</li>
			<li>
				<p>
					<span>配送方式</span><b>+</b>
				</p>
				<p>支持顺丰快递，部分偏远地区支持EMS，敬请谅解</p>
			</li>
			<li>
				<p>
					<span>退换货政策</span><b>+</b>
				</p>
				<p>
					GUCCI中国官方网站为您提供14天轻松<span>退换货服务</span>
				</p>
			</li>
		</ol>
		<p id="back">
			<span>回到主页</span>
		</p>
	</div>
	<div id="shoucang" class="clear">
		<p>你看过的</p>
		<div id="show_choucang"></div>
	</div>
	<c:import url="../common/map.jsp" />
</body>
</html>