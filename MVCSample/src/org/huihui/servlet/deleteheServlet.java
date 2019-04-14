package org.huihui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.User;

/**
 * Servlet implementation class deleteheServlet
 */
public class deleteheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name1 = (String)session.getAttribute("uname");
		String name2 = request.getParameter("uname");//获取朋友双方的用户名
		User user1 = new User();
		User user2 = new User();
		user1.setName(name1);
		user2.setName(name2);
		UserService us = new UserService();
		if(us.deleteFriend(user1, user2)) {//删除好友成功后，刷新一遍好友列表重新放入session域
			session.setAttribute("friends",us.searchFriends(user1));
			response.sendRedirect("welcome.jsp");//跳转至主页
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
