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
		int id = Integer.parseInt(request.getParameter("id"));//获取点赞的微博的惟一标识号
		Huibo huibo = new Huibo();
		huibo.setId(id);
		UserService us = new UserService();//调用Service层增加此微博的点赞数
		if(us.addGood(huibo)) {//成功后返回至微博显示页
				request.getRequestDispatcher("showWeiboServlet").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
