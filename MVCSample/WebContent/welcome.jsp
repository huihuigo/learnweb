<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>在此处插入标题</title>
<script src = "jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#change").click(function(){//点击编辑按扭后，显示出修改与取消按扭，并使个人资料可以编辑，隐藏编辑按扭
			$("#introducetext").attr("readonly",false);
			$("#submit").show();
			$("#cancel").show();
			$("#change").hide();
		});
		$("#cancel").click(function(){//点击取消按扭后，编辑按扭出现，修改与取消按扭隐藏，并使个人资料恢复并不可编辑
			$("#submit").hide();
			$("#cancel").hide();
			$("#change").show();
			$("#introducetext").val();
			$("#introducetext").attr("readonly",true);
		});
		$("#submit").click(function(){//点击修改按扭后，则请求修改个人资料的服务端，修改成功后给予提示，并使修改与取消按扭隐藏，显示编辑按扭，使个人资料刷新与不可编辑
			$.post("updateIntroduceServlet",
					{
						uintroduce:$("#introducetext").val()
					},
					function(data){
						alert(data);
						$("#submit").hide();
						$("#cancel").hide();
						$("#change").show();
						$("#introducetext").attr("readonly",true);
					})
		});
		$("#finish").click(function(){//点击发布按扭，请求发布微博的服务端，发布成功后给与提示，并清空文本框
			if($("#weibotext").val().length!=0){
				$.post("shareWeiboServlet",
						{
							btext:$("#weibotext").val(),
							btype:$("#weibotype").val()
						},
							function(data){
								alert(data);
								$("#weibotext").html("");
						});
			}
			else
				alert("内容不能为空!");
		});
		$("#weibotext").keyup(function(){//设置keyup事件，用来控制发布的微博字数只能限制200，并时刻给予剩余字数提示
			var text = $(this).val();
			var count = "";
			if(text.length>200){
				$(this).val(text.substring(0,200));
			}
			count = 200 - $(this).val().length;
			$("#tip").html(count);
		});
	});
</script>
<style>
	body{
		font-size:20px;
	}
</style>
</head>
<body>
	欢迎您!
	<!-- 如果用户类别是管理员，显示一下 -->
	<c:if test = "${sessionScope.type eq 'manager'}" >
		尊敬的管理员！
	</c:if>
	<b><%=session.getAttribute("uname") %></b><b style = "color:red"><%=session.getAttribute("usex") %></b>访客数量:<b style = "color:red"><%=session.getAttribute("visitnum") %></b>
	等级：<b style = "color:red"><%=session.getAttribute("rank") %></b>
	<a href = "invalidateServlet">退出登陆</a><br/>
	自我介绍:<br/>
	<textarea id = "introducetext" readonly = "readonly" rows="5" cols="30"><%=session.getAttribute("uintroduce") %></textarea><br/>
	<button id = "change">编辑</button>
	<button id = "cancel" style = "display:none">取消</button>
	<button id = "submit" style = "display:none">提交</button>
	<br/>
	<c:if test = "${!empty sessionScope.requestusers}"><!-- 如果session域中的请求者不为空，则罗列出来 -->
		新的好友请求:<br/>
		<c:forEach var = "U" items = "${sessionScope.requestusers}">
			<li style = "color:Red">${U.name} <a href= "friendServlet?status=1&requestuser=${U.name}">接受</a> <a href = "friendServlet?status=0&requestuser=${U.name}">拒绝</a></li>
		</c:forEach>
	</c:if>
	我的好友:<br/>
	<c:if test = "${empty sessionScope.friends}">无<br/></c:if>
	<c:if test = "${!empty sessionScope.friends}"><!-- 如果session域中的好友列表不为空，则罗列出来 -->
		<table width = "100" border = "1" cellpadding = "0">
			<c:forEach var = "U" items = "${sessionScope.friends}">
				<tr>
					<td>${U.name}</td>
					<td>${U.sex}</td>
					<td>${U.introduce}</td>
					<td><a href = "showWeiboServlet?uname=${U.name}&pagenum=1&status=1">查看ta的微博</a></td>
					<td><a href = "deleteheServlet?uname=${U.name}">删除</a></td>
				</tr>			
			</c:forEach>
		</table>
	</c:if>
	<span style = "color:red">分享点什么吧</span><br/>
	<textarea id="weibotext" rows = "10" cols = "50"></textarea>限制200字，剩余<span id = "tip" style = "color:red">200</span><br/>
	<select id = "weibotype">
		<option value = "技术类">技术类</option>
		<option value = "娱乐类">娱乐类</option>
		<option value = "生活类">生活类</option>
		<option value = "讨论类">讨论类</option>
		<option value = "新闻类">新闻类</option>
	</select>
	<button id = "finish">发布</button><br/>
	<a href = "showWeiboServlet?uname=${sessionScope.uname}&pagenum=1&status=0">查看自己所有微博内容</a><br/>
	<c:if test = "${sessionScope.type eq 'ordinary'}" ><!-- 如果用户类别是普通用户，则只能查询指定用户 -->
		<form action = "searchUserServlet" method = "post">
			输入用户名:<input type = "text" name = "uname" />
			<input type = "submit" value = "查找" />
		</form>
	</c:if>
	<c:if test = "${sessionScope.type eq 'manager'}" ><!-- 如果用户类别是管理员，则可以查看所有用户 -->
		<a href = "searchallServlet">查看所有用户</a>
	</c:if>
</body>

</html>