package org.huihui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;
import org.huihui.user.Huibo;

/**
 * Servlet implementation class praiseServlet
 */
public class praiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));//��ȡ���޵�΢����Ωһ��ʶ��
		Huibo huibo = new Huibo();
		huibo.setId(id);
		UserService us = new UserService();//����Service�����Ӵ�΢���ĵ�����
		if(us.addGood(huibo)) {//�ɹ��󷵻���΢����ʾҳ
				request.getRequestDispatcher("showWeiboServlet").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
