package org.huihui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.Huibo;
import org.huihui.user.User;

public class shareWeiboServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("utf-8");
		HttpSession session = request.getSession(false);
		String name = (String)session.getAttribute("uname");
		User user = new User();
		user.setName(name);
		String btext = request.getParameter("btext");
		String btype = request.getParameter("btype");//获取用户名，微博内容，微博类型
		Huibo huibo = new Huibo(name,btext,btype);//封装为一个完整微博记录
		UserService us = new UserService();
		PrintWriter out = response.getWriter();
		if(us.shareNew(huibo)) {//如果发布成功，给出相应提示，并使用户经验+1
			us.addExperience(user);
			out.write("发布成功，经验+1");
			session.setAttribute("rank",us.getRankByName(user));
		}
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
