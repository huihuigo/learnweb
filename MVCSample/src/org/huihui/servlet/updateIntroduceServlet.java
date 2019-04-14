package org.huihui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.User;

public class updateIntroduceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		User user = new User();
		HttpSession session = request.getSession(false);
		user.setName((String)session.getAttribute("uname"));
		user.setIntroduce(request.getParameter("uintroduce"));//���û������޸ĺ�ĸ������Ϸ�װΪһ��User,�����������
		UserService us = new UserService();
		PrintWriter out = response.getWriter();
		if(us.updateUser(user)) {//�޸ĳɹ��������Ӧ��ʾ
			session.setAttribute("uintroduce",user.getIntroduce());
			out.write("�޸ĳɹ�");
		}
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
