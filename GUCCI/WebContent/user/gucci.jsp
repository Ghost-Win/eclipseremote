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
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>www.gucci.com</title>
<link type="text/css" rel="stylesheet" href="${cxt }/user/css/gucci.css">
</head>
<script type="text/javascript" src="../common/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../user/js/gucci.js"></script>
<script type="text/javascript" src="../common/js/header.js"></script>
<body>
	<div id="gucci">
		<!-- 顶部信息显示 -->
		<c:import url="../common/header.jsp">
			<c:param name="path" value="${cxt }"/>
		</c:import>
		<input type="hidden" id="pageIndex" value="1">
		<input type="hidden" id="pageCount" value="1">
		<input type="hidden" id="totalCount" value="1">
		<input type="hidden" id="cxt" value="${cxt }">
		<div id="main">
			<div id="sh" class="clear"></div>
		</div>
		<!-- 分页查询条 -->
		<c:import url="../common/paging.jsp"></c:import>
	</div>
	<c:import url="../common/map.jsp"/>
</body>
</html>