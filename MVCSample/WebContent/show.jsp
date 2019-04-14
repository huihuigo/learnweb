<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src = "jquery-3.3.1.min.js"></script>
<title>在此处插入标题</title>
</head>
<body style = "font-size:20px">
	<c:if test = "${requestScope.page.totalPage!=0}"><!-- 如果微博总页数不为0，则分页罗列出来 -->
		<c:forEach var = "U" items = "${requestScope.page.datas}" >
				<textarea rows="10" cols="30" disabled = "disabled" style = "font-size:20px">${U.huibo.btext}</textarea>${U.huibo.btype}<br/>
				<a href = "praiseServlet?id=${U.huibo.id}&uname=${U.huibo.name}&pagenum=${requestScope.page.pageNum}">赞(${U.huibo.good})</a>
				<c:if test = "${U.huibo.name==sessionScope.uname or sessionScope.type eq 'manager'}"><!-- 如果用户是微博发布者，或者用户是管理员，则可以删除此条微博 -->
					<a href = "deleteWeiboServlet?id=${U.huibo.id}&uname=${U.huibo.name}&pagenum=${requestScope.page.pageNum}">删除</a>
				</c:if>
				<form action = "remarkServlet?id=${U.huibo.id}&uname=${U.huibo.name}&pagenum=${requestScope.page.pageNum}" method = "post">
				<input type = "text" name = "comment" placeholder = "说两句吧" />
				<input type = "submit" value = "发表评论" />
				</form>
				<c:if test = "${empty U.comments}">暂无评论<br/></c:if>
				<c:if test = "${!empty U.comments}"><!-- 如果评论不为空，则罗列出来 -->
					<c:forEach var = "C" items = "${U.comments}">
						<li style = "color:red">${C.name}:${C.bcomment}</li>
					</c:forEach>
				</c:if>
		</c:forEach>
		<a href = "showWeiboServlet?pagenum=1&uname=${requestScope.page.name}">首页</a>
		<c:if test = "${requestScope.page.pageNum>1}"><!-- 如果当前页大于1，则有上一页 -->
			<a href = "showWeiboServlet?pagenum=${requestScope.page.pageNum-1}&uname=${requestScope.page.name}">上一页</a>
		</c:if>
		<c:if test = "${requestScope.page.pageNum<requestScope.page.totalPage}"><!-- 如果当前页小于总页数，则有下一页 -->
			<a href = "showWeiboServlet?pagenum=${requestScope.page.pageNum+1}&uname=${requestScope.page.name}">下一页</a>
		</c:if>
		<a href = "showWeiboServlet?pagenum=${requestScope.page.totalPage}&uname=${requestScope.page.name}">尾页</a>
	</c:if>
	<c:if test = "${requestScope.page.totalPage==0}">该用户暂未发布微博<br/></c:if>
	<a href = "welcome.jsp">返回主页</a>
</body>
</html>