package org.huihui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.huihui.service.UserService;
import org.huihui.user.Comment;
import org.huihui.user.User;

public class remarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(request.getParameter("id"));//获取评论的微博对应的id号
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("uname");//获取评论者的用户名
		String cm = request.getParameter("comment");//获取评论内容
		Comment comment = new Comment();
		comment.setBid(id);
		comment.setName(name);
		comment.setBcomment(cm);//封装成一条评论记录
		User user = new User();
		user.setName(name);
		UserService us = new UserService();
		if(us.addComment(comment)) {//增加评论成功后使评论者的经验+1
			us.addExperience(user);
			session.setAttribute("rank",us.getRankByName(user));//更新session域中用户的等级，可能已经升级
			request.getRequestDispatcher("showWeiboServlet").forward(request, response);//发布评论后返回微博页
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
