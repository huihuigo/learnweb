package org.huihui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.huihui.service.UserService;
import org.huihui.user.Huibo;
import org.huihui.user.Page;
import org.huihui.user.Data;
import org.huihui.user.User;

public class showWeiboServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		User user = new User();
		String name = request.getParameter("uname");
		String status = request.getParameter("status");//用这个状态量来判断是否是同一次访问以及是否是自己访问自己，防止分页查看的时候无端增加访客数量和自己查看自己时增加
		user.setName(name);
		if("1".equals(status)) {//如果status是1，说明有人查询了此用户并访问了其的内容，则使此用户的访客量+1
			us.addVisitNum(user);
		}
		int pageNum = Integer.parseInt(request.getParameter("pagenum"));//表示显示层的第几页
		int pageSize = 3;//表示页面数据的条数
		int totalRecord = us.getHuiboNumByName(user);//获取此用户的总微博记录数
		List<Huibo> huibos = us.showUserHuiboByPage(user,pageNum,pageSize);//将以上数据传到服务端实现分页查询
		List<Data> datas = us.getDataBean(huibos);//将查询到的微博内容进行查询各自的评论，封装为完整数据，供页面显示
		Page page = new Page(name,pageNum,pageSize,totalRecord);
		page.setDatas(datas);//将数据集合封装成一个页面
		request.setAttribute("page",page);//放入request域中
		request.getRequestDispatcher("show.jsp").forward(request, response);//跳转至显示层
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
