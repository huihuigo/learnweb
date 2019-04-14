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
		String status = request.getParameter("status");//�����״̬�����ж��Ƿ���ͬһ�η����Լ��Ƿ����Լ������Լ�����ֹ��ҳ�鿴��ʱ���޶����ӷÿ��������Լ��鿴�Լ�ʱ����
		user.setName(name);
		if("1".equals(status)) {//���status��1��˵�����˲�ѯ�˴��û���������������ݣ���ʹ���û��ķÿ���+1
			us.addVisitNum(user);
		}
		int pageNum = Integer.parseInt(request.getParameter("pagenum"));//��ʾ��ʾ��ĵڼ�ҳ
		int pageSize = 3;//��ʾҳ�����ݵ�����
		int totalRecord = us.getHuiboNumByName(user);//��ȡ���û�����΢����¼��
		List<Huibo> huibos = us.showUserHuiboByPage(user,pageNum,pageSize);//���������ݴ��������ʵ�ַ�ҳ��ѯ
		List<Data> datas = us.getDataBean(huibos);//����ѯ����΢�����ݽ��в�ѯ���Ե����ۣ���װΪ�������ݣ���ҳ����ʾ
		Page page = new Page(name,pageNum,pageSize,totalRecord);
		page.setDatas(datas);//�����ݼ��Ϸ�װ��һ��ҳ��
		request.setAttribute("page",page);//����request����
		request.getRequestDispatcher("show.jsp").forward(request, response);//��ת����ʾ��
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
