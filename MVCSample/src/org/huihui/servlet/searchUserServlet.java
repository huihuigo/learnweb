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
		String name = request.getParameter("uname");//��ȡ��ѯ���û���
		name = "%"+name+"%";//ʵ��ģ����ѯ�����ؼ�������ѯ����û�
		UserService us = new UserService();
		User user = new User();
		user.setName(name);
		List<User> list = us.getSearchUser(user);//��ȡ���п��ܵ�������ҵ��û�����
		request.setAttribute("allUser",list);//����request����
		request.getRequestDispatcher("search.jsp").forward(request, response);//����ת������ѯ���ҳ��
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
