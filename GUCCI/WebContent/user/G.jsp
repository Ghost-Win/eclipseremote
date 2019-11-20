<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gucci官方网站</title>
<link type="text/css" href="css/G.css" rel="stylesheet" />
</head>
<body>
	<input type="hidden" id="cxt" value="${pageContext.request.contextPath }">
	<a href="#" id="nav">GUCCI</a>
	<!--登录主页面-->
	<div id="box">
		<div>登录</div>
		<div>
			<div>
				<p>手机号码</p>
				<input type="text" name="box_phone_email" placeholder="请输入手机号" maxlength="20" />
				<div></div>
				<p>密码</p>
				<input type="password" name="box_password" maxlength="40"/>
				<div></div>
				<div></div><span>记住我</span>
				<div id="box_login">登录</div>
				<div>忘记密码?</div>
			</div>
			<div>
				<p>没有账号?</p>
				<p>拥有古驰账号您将获得一下权利:</p>
				<ul>
					<li>保存您的心愿清单</li>
					<li>个性化您的推荐内容</li>
					<li>订单寄送追踪及退换货管理</li>
				</ul>
				<div id="box_registe">创建账户</div>
			</div>
		</div>
	</div>
	
	<!--注册页面-->
	<div id="registe">
		<div>
			<div>创建您的账户</div>
			<div>×</div>
			<div>
				<p>手机号码</p>
				<input type="text" name="registe_phone_email" placeholder="请输入手机号" maxlength="20"/>
				<div></div>
				<p>手机验证码</p>
				<input type="text" name="registe_verification_code" placeholder="请输入短信验证码"/><span>发送验证码</span>
				<div></div>
				<p>设置密码</p>
				<input type="password" name="registe_password" placeholder="至少6个字符包含数字字母大小写" maxlength="40"/>
				<div></div>
				<p>确认密码</p>
				<input type="password" name="registe_restpassword" placeholder="确认密码" maxlength="40"/>
				<div></div>
				<div></div><span>创建帐号代表您已接受Gucci的隐私政策和销售条款</span>
				<div id="registe_submit">创建账户</div>
			</div>
		</div>
	</div>
	
	<!--找回密码页面-->
	<div id="forgotPassword">
		<div>
			<div>找回您的密码</div>
			<div>×</div>
			<div>
				<p>手机号码</p>
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
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/common/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="js/G.js"></script>
</body>
</html>