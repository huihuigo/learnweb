package org.huihui.service;

import java.util.ArrayList;
import java.util.List;

import org.huihui.dao.UserDao;
import org.huihui.user.Comment;
import org.huihui.user.Data;
import org.huihui.user.Huibo;
import org.huihui.user.User;

public class UserService {
	public boolean register(User user) {//ע��
		UserDao ud = new UserDao();
		if(!ud.isExist(user))//���ж��û����Ƿ����
			return ud.inSert(user);
		return false;
	}
	public boolean login(User user) {//��½
		UserDao ud = new UserDao();
		if(user.getName().contains("@qq.com")) {//�жϴ��������û����Ƿ�����������
			String name = ud.searchNameByEmail(user.getName());//���������������ͨ��������Ҷ�Ӧ���û���
			if(name!=null)
				user.setName(name);
		}
		if(ud.isExist(user))
			return ud.isRight(user);
		return false;
	}
	public String getIntroduce(User user) {//��ȡ���˽���
		UserDao ud = new UserDao();
		return ud.searchIntroduceByName(user);
	}
	public boolean updateUser(User user) {//�޸ĸ��˽���
		UserDao ud = new UserDao();
		return ud.update(user);
	}
	public boolean shareNew(Huibo huibo) {//�����µ�΢��
		UserDao ud = new UserDao();
		return ud.shareNew(huibo);
	}
	public String getSex(User user) {//��ȡ�Ա�
		UserDao ud = new UserDao();
		return ud.searchSexByName(user);
	}
	public String getType(User user) {//��ȡ�û�����
		UserDao ud = new UserDao();
		return ud.searchTypeByName(user);
	}
	public int getVisitNum(User user) {//��ȡ������
		UserDao ud = new UserDao();
		return ud.searchVisitNumByName(user);
	}
	public boolean addVisitNum(User user) {//���ӷ�����
		UserDao ud = new UserDao();
		return ud.updateVisitNumByName(user);
	}
	public void addExperience(User user) {//�����û����飬��ͬʱ���µȼ����жϾ����Ƿ�5����5�����������þ�������
		UserDao ud = new UserDao();
		if(ud.addExperienceByName(user)) {
			if(ud.getExperienceByName(user)==5) {
				ud.resetExperienceByName(user);
				ud.updateRankByName(user);
			}
		}
	}
	public List<Huibo> showUserHuiboByPage(User user,int pageNum,int pageSize){//��ҳ��ѯ�û�������΢��
		UserDao ud = new UserDao();
		return ud.getUserHuiboByPage(user,pageNum,pageSize);
	}
	public boolean deleteHuiboById(Huibo huibo) {//ɾ���ض�΢��
		UserDao ud = new UserDao();
		//ͬʱҲ��Ҫ����ɾ����Ӧ���������ۣ���΢����id����Comment��huiboid
		if(ud.showHuiboComment(huibo)!=null) {//�������΢�������ۣ���ɾ��
			Comment comment = new Comment();
			comment.setBid(huibo.getId());
			ud.deleteComment(comment);
		}
		return ud.deleteHuibo(huibo);
	}
	public List<User> getSearchUser(User user){//�õ����ҵ��û�
		UserDao ud = new UserDao();
		return ud.searchUserByName(user);
	}
	public boolean addGood(Huibo huibo) {//���ӵ�����
		UserDao ud = new UserDao();
		return ud.addGood(huibo);
	}
	public List<Comment> showHuiboAllComments(Huibo huibo){//��ȡĳ��΢������������
		UserDao ud = new UserDao();
		return ud.showHuiboComment(huibo);
	}
	public boolean addComment(Comment comment) {//��������
		UserDao ud = new UserDao();
		return ud.addComment(comment);
	}
	public List<Data> getDataBean(List<Huibo> huibos){//����ҳ��ѯ�ĵ���΢��������ÿ��΢����ȥ��ѯ�����������ۣ����ҷ�װ��ҳ����ʾ����Ӧ����
		List <Data> datas = new ArrayList<>();
		for(Huibo huibo : huibos) {
			List<Comment> comments = showHuiboAllComments(huibo);
			Data temp = new Data(huibo,comments);
			datas.add(temp);
		}
		return datas;
	}
	public int getHuiboNumByName(User user) {//��ȡ�û�������΢����
		UserDao ud = new UserDao();
		return ud.getHuiboNumByName(user);
	}
	public boolean requestBeFriend(User requestuser,User responseuser) {//������������
		UserDao ud = new UserDao();
		return ud.requestBeFriend(requestuser, responseuser);
	}
	public List<User> getRequestUser(User responseuser){//�û��õ������Լ�����������
		UserDao ud = new UserDao();
		return ud.getRequestUser(responseuser);
	}
	public boolean agreeRequest(User requestuser,User responseuser) {//ͬ������
		UserDao ud = new UserDao();
		return ud.agreeRequest(requestuser, responseuser);
	}
	public boolean refuseRequest(User requestuser,User responseuser) {//�ܾ�����
		UserDao ud = new UserDao();
		return ud.refuseRequest(requestuser, responseuser);
	}
	public List<User> searchFriends(User user){//�õ��û������к���
		UserDao ud = new UserDao();
		return ud.searchFriends(user);
	}
	public boolean deleteFriend(User user1,User user2) {//ɾ������
		UserDao ud = new UserDao();
		return ud.deleteFriend(user1, user2);
	}
	public int getRankByName(User user) {//�õ��û��ȼ�
		UserDao ud = new UserDao();
		return ud.getRankByName(user);
	}
	public List<User> getAllUsers(){//�õ������û�������Ա��Ȩ
		UserDao ud = new UserDao();
		return ud.getAllUser();
	}
}
