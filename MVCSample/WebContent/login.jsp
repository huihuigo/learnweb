<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>辉博</title>
</head>
<body style = "font-size:20px">
		<h1>辉博</h1>
		<form action = "loginServlet" method = "post">
		用户名或邮箱: <input type = "text" name = "uname" /><br/>
		密码:<input type = "password" name = "upwd" /><a href = "register.jsp">还没有账户，快去注册..</a><br/>
		<input type = "submit" value = "登陆" />
		</form>
</body>
</html>