package org.huihui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.User;

public class getMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String name = (String)session.getAttribute("uname");
		User responseuser = new User();
		responseuser.setName(name);
		UserService us = new UserService();
		List<User> users = us.getRequestUser(responseuser);//��ȡ��������Լ�Ϊ���ѵ��û�����Ϣ���Ž�session��
		session.setAttribute("requestusers",users);
		response.sendRedirect("welcome.jsp");//��ת����ҳ
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
