package org.huihui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;

import org.huihui.user.User;
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		String sex = request.getParameter("usex");
		String email = request.getParameter("uemail");
		String introduce = request.getParameter("uintroduce");
		String type = request.getParameter("utype");
		if(introduce==null||introduce.equals(""))
			introduce = "这个人很懒,什么也没留下";
		User user= new User(name,pwd,sex,email,introduce,type);//将获取的合理数据封装成一个用户信息增加至数据库的用户表
		UserService us = new UserService();
		PrintWriter out = response.getWriter();
		if(us.register(user)) {//注册后给出相应提示
			out.write("注册成功");
		}
		else {
			out.write("用户已存在");
		}
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
