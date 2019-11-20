<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String cxt = request.getContextPath();
	request.setAttribute("cxt", cxt);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin</title>
<link href="${cxt }/admin/css/admin.css" type="text/css" rel="stylesheet">
</head>
<body>
		<input type="hidden" id="pageIndex" value="1">
		<input type="hidden" id="pageSize" value="8">
		<input type="hidden" id="pageCount" value="1">
		<input type="hidden" id="type" value="">
		<input type="hidden" id="cxt" value="${cxt }">
	<div id="head">
		<div>
			<div>
				<img src="${cxt }/user/img/gucci-logo@2x.png" width="148"
					height="24" />
			</div>
			<div>
				<ul id="GoodsClassList" style="color: #fff;position: absolute;right:200px;top:145px;
				cursor: pointer;">退出</ul>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="wel-login">
			<span class="wel-login-words1">欢迎登陆</span>
			<strong>GUCCI</strong>
			<span class="wel-login-words1">商户管理界面</span>
			<span class="wel-login-words2">您好：GUCCI</span>
		</div>
		<div class="show-box">
			<div class="goods-box">
				<div class="show-title">
					<strong class="goods-manage">商品管理</strong>
					<strong class="order-manage">订单管理</strong>
					<strong class="sale-count">销量统计</strong>
				</div>
				<div class="bgr-img1"></div>
				<div class="goods-pic">
					<div id="goods-ctr-words">
						<p id="p1">	
							<span class="ctr-byType">按分类：</span>
							<select name="select1" id="select1"></select>
							<button name="goodSelectType" id="goodSelectType">查询</button>
							<button name="addGoods" id="addGoods" onclick="addGoods()">添加商品</button>
						</p>
						<!-- <p id="p3">
							<button type="button" name="selectSale" id="selectSale">按销量查询</button>
						</p>  -->
					</div>
					<div id="show_goods"></div>
					<p class="changePage">
						<span>第</span><span id="cpageIndex"></span><span>页</span>
						<button type="button" name="upPage" id="upPage">上一页</button>
						<button type="button" name="downPage" id="downPage">下一页</button>
						<span>共</span><span id="ctotalPage"></span><span>页</span>
					</p>
				</div>
				<div id="add_goods">
					<form action="${cxt }/addGoodsServlet" method="post" enctype="multipart/form-data">
						<p>请添加商品详细信息</p>
						<ul>
							<li><span>商品名称：</span><input type="text" name="goodsName" value="" /></li>
							<li>
								<span id="f_typeWords">一级分类：</span>
								<select name="type" id="f-type"></select>
							</li>
							<li>
								<span id="s_typeWords">二级分类：</span>
								<select name="goodsClassId" id="s-type" ></select>
							</li>
							<li><span>商品数量：</span><input type="text" name="goodsCount" value="" /></li>
							<li><span>商品单价：</span><input type="text" name="price" value="" /></li>
							<li><span>商品销量：</span><input type="text" name="monthSale" value="" /></li>
							<li><span>商品描述：</span><input type="text" name="goodsDesc" value="" /></li>
							<li><span>商品图片：</span><input type="file" name="picPath"/></li>
						</ul>
						<input type="submit" value="提交"/>
						<input type="reset" value="返回"/>
					</form>
				</div>
				<input type="hidden" id="status" value="">
				<div id="manage_order">
					<p id="p2">
							<span onclick="showDoneOrder();">已完成</span>&nbsp;&nbsp;
							<span onclick="showUndoneOrder()">未完成</span>
					</p>
					<p id="table_head">
						<span>商品</span>
						<span>订单金额</span>
						<span>用户姓名</span>
						<span>收货地址</span>
						<span>订单状态</span>
					</p>
					<p id="changePage1">
						<span>第</span><span id="opageIndex"></span><span>页</span>
						<button type="button" name="oupPage" id="oupPage">上一页</button>
						<button type="button" name="odownPage" id="odownPage">下一页</button>
						<span>共</span><span id="ototalPage"></span><span>页</span>
					</p>
				</div>
				<p id="p3">
					<span id="top10">
						<span>GUCCI</span>
						<span>TOP10</span>
					</span>
				</p>
				<div id="sale_count">
					<c:import url="${pagecontext.request.contextPath }/admin/myCharts.jsp"></c:import>
				</div>
				<div class="bgr-img2"></div>
			</div>
		</div>
	</div>
	<div class="foot"></div>
	<script type="text/javascript" src="${cxt }/admin/js/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="${cxt }/admin/js/admin.js"></script>
</body>
</html>