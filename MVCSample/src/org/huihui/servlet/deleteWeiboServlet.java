package org.huihui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;
import org.huihui.user.Huibo;

public class deleteWeiboServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));//获取某条微博的唯一标识号
		Huibo huibo = new Huibo();
		huibo.setId(id);
		if(us.deleteHuiboById(huibo)) {//删除成功后，请求转发至showWeiboServlet刷新页面
			request.getRequestDispatcher("showWeiboServlet").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
