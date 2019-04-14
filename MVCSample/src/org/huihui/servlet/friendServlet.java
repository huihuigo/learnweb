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
 * Servlet implementation class friendServlet
 */
public class friendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		String responseuname = (String)session.getAttribute("uname");//操作的用户为回应者
		String requestuname = request.getParameter("requestuser");//获取请求者的用户名
		User requestuser = new User();
		User responseuser = new User();
		requestuser.setName(requestuname);
		responseuser.setName(responseuname);
		UserService us = new UserService();
		if(request.getParameter("status").equals("1")) {//如果status==1,则代表同意
			if(us.agreeRequest(requestuser, responseuser)) {
				session.setAttribute("friends",us.searchFriends(responseuser));//重新获取一遍好友列表，将新的好友放进session域中的好友
				response.sendRedirect("getMessageServlet");//请求转发至获取消息的servlet，刷新好友请求的消息
			}
		}
		else {
			if(us.refuseRequest(requestuser, responseuser)) {
				response.sendRedirect("getMessageServlet");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
