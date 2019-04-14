package org.huihui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;

import org.huihui.user.User;
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("upwd");
		String sex = request.getParameter("usex");
		String email = request.getParameter("uemail");
		String introduce = request.getParameter("uintroduce");
		String type = request.getParameter("utype");
		if(introduce==null||introduce.equals(""))
			introduce = "����˺���,ʲôҲû����";
		User user= new User(name,pwd,sex,email,introduce,type);//����ȡ�ĺ������ݷ�װ��һ���û���Ϣ���������ݿ���û���
		UserService us = new UserService();
		PrintWriter out = response.getWriter();
		if(us.register(user)) {//ע��������Ӧ��ʾ
			out.write("ע��ɹ�");
		}
		else {
			out.write("�û��Ѵ���");
		}
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
