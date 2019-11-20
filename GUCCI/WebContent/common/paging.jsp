<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String cxt = request.getContextPath();
	request.setAttribute("cxt", cxt);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>paging</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style: none;
}

#paging {
	width: 600px;
	height: 80px;
	background: rgb(240, 240, 240);
	border-radius: 20px;
	margin-left: 700px;
	margin-top: 150px;
}

#paging>li {
	display: inline-block;
	width: 140px;
	font-size: 13px;
	cursor: pointer;
	margin-top: 30px;
}

#paging>li:nth-child(1) {
	margin-left: 60px;
}

#paging>li:nth-child(2) {
	background: rgb(40, 40, 40);
	color: white;
	padding: 5px 10px;
	width: 60px;
	text-align: center;
	border-radius: 5px;
}

#paging>li:nth-child(3) {
	background: rgb(40, 40, 40);
	color: white;
	padding: 5px 10px;
	width: 60px;
	text-align: center;
	border-radius: 5px;
}

#paging>li:nth-child(4) {
	text-align: right;
}

#paging>li:nth-child(2):hover {
	background: rgb(90, 90, 90);
}

#paging>li:nth-child(3):hover {
	background: rgb(90, 90, 90);
}
</style>
</head>
<script type="text/javascript" src="${cxt }/common/jquery-3.3.1.js"></script>
<script type="text/javascript"></script>
<div id="paging">
	<li>共计&nbsp;&nbsp;<b id="ctotalCount"></b>&nbsp;&nbsp;条记录
	</li>
	<li id="up">上一页</li>
	<li id="down">下一页</li>
	<li>当前第&nbsp;&nbsp;<b id="cpageIndex"></b>&nbsp;&nbsp;/&nbsp;&nbsp;<b id="cpageCount"></b>&nbsp;&nbsp;页
	</li>
	<input type="hidden" id="type" value="0">
</div>
</html>