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
		String name2 = request.getParameter("uname");//��ȡ����˫�����û���
		User user1 = new User();
		User user2 = new User();
		user1.setName(name1);
		user2.setName(name2);
		UserService us = new UserService();
		if(us.deleteFriend(user1, user2)) {//ɾ�����ѳɹ���ˢ��һ������б����·���session��
			session.setAttribute("friends",us.searchFriends(user1));
			response.sendRedirect("welcome.jsp");//��ת����ҳ
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
