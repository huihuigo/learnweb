package org.huihui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;
import org.huihui.user.User;
public class searchallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		List<User> users = us.getAllUsers();//��ȡ�������û�����
		request.setAttribute("allUser",users);//����request����
		request.getRequestDispatcher("search.jsp").forward(request, response);//����ת���������ʾ��
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
