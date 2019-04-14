<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>在此处插入标题</title>
<script src = "jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#input3").change(function(){//当再次输入密码改变时，获取输入的密码和再次输入的密码，如果不相同，则在span区域提示，并且将注册键设置为不可用
			var x = $("#input2").val();
			var y = $("#input3").val();
			if(x!=y){
				$("#span3").html("两次密码不一致，请重新输入");
				$("#submit").attr("disabled",true);
			}
			else{
				$("#span3").html("");
				$("#submit").attr("disabled",false);
			}
		});
		$("#registerform").submit(function(){//将表单数据通过ajax提交到服务端，然后显示出结果
				$.ajax({
					type:"post",
					url:"registerServlet",
					data:$("#registerform").serialize(),
					success:function(response){
						alert(response);
					}
				});
		});
	});
</script>
</head>
<body>
	<h1>欢迎注册辉博!</h1>
	<form id="registerform" >
	请输入您的用户名:<input type = "text"  name = "uname" required = "required" /><br/>
	请输入您的密码:<input id = "input2" type = "password" name = "upwd" required = "required"/><br/>
	请再次输入您的密码:<input id = "input3" type = "password" name = "reupwd" required = "required"/><span id="span3" style = "color:red"></span><br/>
	请输入您的邮箱:<input type = "email" name = "uemail" required = "required"/><br/>
	选择您的性别:<input type = "radio" name = "usex" value = "男" checked />男
	<input type = "radio" name = "usex" value = "女" />女<br/>
	自我介绍:<br/>
	<textarea name = "uintroduce" rows = "5" cols = "30"></textarea><br/>
	选择用户类型:
	<input type = "radio" name = "utype" value = "ordinary" checked />普通用户
	<input type = "radio" name = "utype" value = "manager" />管理员
	<input id = "submit" type = "submit" value = "注册" />
	<input type = "reset" value = "重置" />
	<a href = "login.jsp">返回</a>
	</form>
</body>
</html>