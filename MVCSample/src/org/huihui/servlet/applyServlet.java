package org.huihui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.User;
public class applyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		HttpSession session = request.getSession(false);
		String requestuname = (String)session.getAttribute("uname");//将操作的用户作为请求方
		String responseuname = request.getParameter("responseuname");//获取回应方的用户名
		User requestuser = new User();
		User responseuser = new User();
		requestuser.setName(requestuname);
		responseuser.setName(responseuname);
		UserService us = new UserService();
		if(us.requestBeFriend(requestuser,responseuser))//暂时没想到如何处理页面比较好，暂且控制台显示一下
			System.out.println("OK");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
