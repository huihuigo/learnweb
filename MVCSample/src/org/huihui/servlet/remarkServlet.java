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
		int id = Integer.parseInt(request.getParameter("id"));//��ȡ���۵�΢����Ӧ��id��
		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("uname");//��ȡ�����ߵ��û���
		String cm = request.getParameter("comment");//��ȡ��������
		Comment comment = new Comment();
		comment.setBid(id);
		comment.setName(name);
		comment.setBcomment(cm);//��װ��һ�����ۼ�¼
		User user = new User();
		user.setName(name);
		UserService us = new UserService();
		if(us.addComment(comment)) {//�������۳ɹ���ʹ�����ߵľ���+1
			us.addExperience(user);
			session.setAttribute("rank",us.getRankByName(user));//����session�����û��ĵȼ��������Ѿ�����
			request.getRequestDispatcher("showWeiboServlet").forward(request, response);//�������ۺ󷵻�΢��ҳ
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
