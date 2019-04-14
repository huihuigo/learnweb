package org.huihui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.User;

public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("uname");
			String pwd = request.getParameter("upwd");
			UserService us = new UserService();
			User user = new User(name,pwd);
			boolean result = us.login(user);
			if(result){//将用户的所有信息保存在session域中
				HttpSession session = request.getSession();
				session.setAttribute("uname",user.getName());//用户名
				session.setAttribute("uintroduce",us.getIntroduce(user));//用户个人资料
				session.setAttribute("usex",us.getSex(user));//用户性别
				session.setAttribute("visitnum",us.getVisitNum(user));//用户访问量
				session.setAttribute("friends",us.searchFriends(user));//用户好友
				session.setAttribute("rank",us.getRankByName(user));//用户等级
				session.setAttribute("type",us.getType(user));//用户类别
				request.getRequestDispatcher("getMessageServlet").forward(request, response);//再请求转发去看看有没有新的消息，即好友申请	
			}
			else
				response.sendRedirect("login.jsp");//如果登陆出现问题就跳转回登陆页面重新登陆
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
