package org.huihui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;
import org.huihui.user.User;

/**
 * Servlet implementation class searchServlet
 */
public class searchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uname");//获取查询的用户名
		name = "%"+name+"%";//实现模糊查询，按关键字来查询相关用户
		UserService us = new UserService();
		User user = new User();
		user.setName(name);
		List<User> list = us.getSearchUser(user);//获取所有可能的你想查找的用户集合
		request.setAttribute("allUser",list);//放入request域中
		request.getRequestDispatcher("search.jsp").forward(request, response);//请求转发至查询结果页面
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
