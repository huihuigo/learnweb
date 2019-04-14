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
		String responseuname = (String)session.getAttribute("uname");//�������û�Ϊ��Ӧ��
		String requestuname = request.getParameter("requestuser");//��ȡ�����ߵ��û���
		User requestuser = new User();
		User responseuser = new User();
		requestuser.setName(requestuname);
		responseuser.setName(responseuname);
		UserService us = new UserService();
		if(request.getParameter("status").equals("1")) {//���status==1,�����ͬ��
			if(us.agreeRequest(requestuser, responseuser)) {
				session.setAttribute("friends",us.searchFriends(responseuser));//���»�ȡһ������б����µĺ��ѷŽ�session���еĺ���
				response.sendRedirect("getMessageServlet");//����ת������ȡ��Ϣ��servlet��ˢ�º����������Ϣ
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
