<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>在此处插入标题</title>
</head>
<body style = "font-size: 20px">
	<table width = "600" border = "1" cellpadding = "0">
		<tr>
			<th>用户名</th>
			<th>性别</th>
			<th>个人介绍</th>
			<th>查看ta的微博</th>
		</tr>
		<!-- allUser为查询到的所有用户，friends为本用户的所有好友 -->
		<c:forEach var = "U" items = "${allUser}">
			<c:set var = "judge" value = "true" /><!-- 设置一个变量，用来判断查询的用户是否已经是自己的好友 -->
			<c:forEach var = "F" items = "${sessionScope.friends}">
				<!-- 如果用户包含在好友列表中则说明已是好友，令judge为false -->
				<c:if test = "${F.name eq U.name}">
					<c:set var = "judge" value = "false" />
				</c:if>
			</c:forEach>	
			<tr>
				<td><input type = "text"  value = "${U.name}" disabled = "disabled"/></td>
				<td><input type = "text"  value = "${U.sex}" disabled = "disabled"/></td>
				<td><input type = "text"  value = "${U.introduce}" disabled = "disabled"/></td>
				<td><a href = "showWeiboServlet?uname=${U.name}&pagenum=1&status=1">查看</a></td>
				<c:if test = "${judge}"><td><a href = "applyServlet?responseuname=${U.name}">加为好友</a></td></c:if>
				<c:if test = "${!judge}"><td>对方已是您的好友</td></c:if><!-- 如果judge是false，则显示一下 -->
			</tr>
		</c:forEach>
	</table>
</body>
</html>