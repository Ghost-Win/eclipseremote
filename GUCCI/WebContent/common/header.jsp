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
<title>古驰GUCCI中国官方网站-GUCCI中国官网</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/common/css/header.css">
</head>
<!-- <script type="text/javascript" src="jquery-3.3.1.js"></script> -->
<!-- <script type="text/javascript" src="js/header.js"></script> -->
<body>
	<!--头部开始-->
	<div id="login">
		<div>
			<div>登录</div>
			<div>×</div>
			<div>
				<p>手机号码</p>
				<input type="text" name="login_phone_email" placeholder="请输入手机号码" autofocus maxlength="20"/>
				<div></div>
				<p>密码</p>
				<input type="password" name="login_password" placeholder="请输入密码" maxlength="40"/>
				<div></div>
				<div></div><span>记住我</span>
				<div id="login_login">登录</div>
				<div>忘记密码?</div>
			</div>
		</div>
	</div>
	<!--找回密码页面-->
	<input type="hidden" id="userId" value="${sessionScope.gucciDTO.userId }">
	<input type="hidden" id="type" value=""> 
	<input type="hidden" id="goodsClassId" value=""> 
	<div id="forgotPassword">
		<div>
			<div>找回您的密码</div>
			<div>×</div>
			<div>
				<p>手机号码/电子邮箱</p>
				<input type="text" name="forgotPassword_phone_email" placeholder="请输入11位手机号码" maxlength="20"/>
				<div></div>
				<p>手机验证码</p>
				<input type="text" name="forgotPassword_verification_code" placeholder="请输入短信验证码"/><span>发送验证码</span>
				<div></div>
				<p>设置密码</p>
				<input type="password" name="forgotPassword_password" placeholder="至少6个字符包含数字字母大小写" maxlength="40"/>
				<div></div>
				<p>确认密码</p>
				<input type="password" name="forgotPassword_restpassword" placeholder="确认密码" maxlength="40"/>
				<div></div>
				<div id="forgotPassword_submit">找回密码</div>
			</div>
		</div>
	</div>
	<div id="wrapper">
		<div class="top-title-box">
			<div class="left-nav-box">
				<ul class="left-nav-ul">
					<li class="item"><a href="#">中国</a></li>
					<li class="item"><a href="#">简体中文</a></li>
					<li class="item"><a href="${pageContext.request.contextPath }/user/G.jsp">注册</a></li>
				</ul>
			</div>
			<div class="center-nav-box">
				<div class="logo"></div>
				<ul class="center-nav-ul" id="show_goods_class"></ul>
			</div>
			<div class="right-nav-box">
				<ul class="right-nav-ul">
					<li class="item">
						<c:if test='${gucciDTO!=null }'><a href="${pageContext.request.contextPath }/user/update.jsp"><i class="love"></i>我的账户</a></c:if>
						<c:if test="${gucciDTO==null }"><a id="pleaseLogin"><i class="love"></i>请登录</a></c:if>
					</li>
					<li class="item" id="c"><a href="${cxt }/user/cart.jsp"><i
							class="shopping-bag"></i>购物袋&nbsp;&nbsp;&nbsp;</a></li>
					<li class="item"><a href="${cxt }/user/order.jsp">我的订单</a></li>
				</ul>
			</div>
		</div>
		<div class="center-classify">
			<h6>2019春夏</h6>
			<ul class="classify-ul">
				<li class="item" id="a"><a href="#">强势回归</a></li>
				<li class="item"><a href="#">打造精品</a></li>
			</ul>
		</div>
		<div class="top-img-box"></div>
	</div>
	<div class="btn-left"></div>
	<div class="btn-right"></div>
	<div class="logo2-box">
		<div class="logo2"></div>
	</div>
</body>
</html>