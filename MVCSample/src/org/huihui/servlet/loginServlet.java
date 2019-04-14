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
			if(result){//���û���������Ϣ������session����
				HttpSession session = request.getSession();
				session.setAttribute("uname",user.getName());//�û���
				session.setAttribute("uintroduce",us.getIntroduce(user));//�û���������
				session.setAttribute("usex",us.getSex(user));//�û��Ա�
				session.setAttribute("visitnum",us.getVisitNum(user));//�û�������
				session.setAttribute("friends",us.searchFriends(user));//�û�����
				session.setAttribute("rank",us.getRankByName(user));//�û��ȼ�
				session.setAttribute("type",us.getType(user));//�û����
				request.getRequestDispatcher("getMessageServlet").forward(request, response);//������ת��ȥ������û���µ���Ϣ������������	
			}
			else
				response.sendRedirect("login.jsp");//�����½�����������ת�ص�½ҳ�����µ�½
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
