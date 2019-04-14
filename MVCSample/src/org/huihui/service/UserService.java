package org.huihui.service;

import java.util.ArrayList;
import java.util.List;

import org.huihui.dao.UserDao;
import org.huihui.user.Comment;
import org.huihui.user.Data;
import org.huihui.user.Huibo;
import org.huihui.user.User;

public class UserService {
	public boolean register(User user) {//注册
		UserDao ud = new UserDao();
		if(!ud.isExist(user))//先判断用户名是否存在
			return ud.inSert(user);
		return false;
	}
	public boolean login(User user) {//登陆
		UserDao ud = new UserDao();
		if(user.getName().contains("@qq.com")) {//判断传过来的用户名是否是邮箱类型
			String name = ud.searchNameByEmail(user.getName());//如果是邮箱类型则通过邮箱查找对应的用户名
			if(name!=null)
				user.setName(name);
		}
		if(ud.isExist(user))
			return ud.isRight(user);
		return false;
	}
	public String getIntroduce(User user) {//获取个人介绍
		UserDao ud = new UserDao();
		return ud.searchIntroduceByName(user);
	}
	public boolean updateUser(User user) {//修改个人介绍
		UserDao ud = new UserDao();
		return ud.update(user);
	}
	public boolean shareNew(Huibo huibo) {//发布新的微博
		UserDao ud = new UserDao();
		return ud.shareNew(huibo);
	}
	public String getSex(User user) {//获取性别
		UserDao ud = new UserDao();
		return ud.searchSexByName(user);
	}
	public String getType(User user) {//获取用户类型
		UserDao ud = new UserDao();
		return ud.searchTypeByName(user);
	}
	public int getVisitNum(User user) {//获取访问量
		UserDao ud = new UserDao();
		return ud.searchVisitNumByName(user);
	}
	public boolean addVisitNum(User user) {//增加访问量
		UserDao ud = new UserDao();
		return ud.updateVisitNumByName(user);
	}
	public void addExperience(User user) {//增加用户经验，并同时更新等级，判断经验是否到5，到5则升级并重置经验条；
		UserDao ud = new UserDao();
		if(ud.addExperienceByName(user)) {
			if(ud.getExperienceByName(user)==5) {
				ud.resetExperienceByName(user);
				ud.updateRankByName(user);
			}
		}
	}
	public List<Huibo> showUserHuiboByPage(User user,int pageNum,int pageSize){//分页查询用户发布的微博
		UserDao ud = new UserDao();
		return ud.getUserHuiboByPage(user,pageNum,pageSize);
	}
	public boolean deleteHuiboById(Huibo huibo) {//删除特定微博
		UserDao ud = new UserDao();
		//同时也不要忘记删除对应的所有评论，将微博的id传给Comment的huiboid
		if(ud.showHuiboComment(huibo)!=null) {//如果这条微博有评论，则删除
			Comment comment = new Comment();
			comment.setBid(huibo.getId());
			ud.deleteComment(comment);
		}
		return ud.deleteHuibo(huibo);
	}
	public List<User> getSearchUser(User user){//得到查找的用户
		UserDao ud = new UserDao();
		return ud.searchUserByName(user);
	}
	public boolean addGood(Huibo huibo) {//增加点赞数
		UserDao ud = new UserDao();
		return ud.addGood(huibo);
	}
	public List<Comment> showHuiboAllComments(Huibo huibo){//获取某条微博的所有评论
		UserDao ud = new UserDao();
		return ud.showHuiboComment(huibo);
	}
	public boolean addComment(Comment comment) {//增加评论
		UserDao ud = new UserDao();
		return ud.addComment(comment);
	}
	public List<Data> getDataBean(List<Huibo> huibos){//将分页查询的到的微博，根据每条微博再去查询它的所有评论，并且封装成页面显示的相应数据
		List <Data> datas = new ArrayList<>();
		for(Huibo huibo : huibos) {
			List<Comment> comments = showHuiboAllComments(huibo);
			Data temp = new Data(huibo,comments);
			datas.add(temp);
		}
		return datas;
	}
	public int getHuiboNumByName(User user) {//获取用户的所有微博数
		UserDao ud = new UserDao();
		return ud.getHuiboNumByName(user);
	}
	public boolean requestBeFriend(User requestuser,User responseuser) {//发出好友请求
		UserDao ud = new UserDao();
		return ud.requestBeFriend(requestuser, responseuser);
	}
	public List<User> getRequestUser(User responseuser){//用户得到关于自己的所有请求
		UserDao ud = new UserDao();
		return ud.getRequestUser(responseuser);
	}
	public boolean agreeRequest(User requestuser,User responseuser) {//同意请求
		UserDao ud = new UserDao();
		return ud.agreeRequest(requestuser, responseuser);
	}
	public boolean refuseRequest(User requestuser,User responseuser) {//拒绝请求
		UserDao ud = new UserDao();
		return ud.refuseRequest(requestuser, responseuser);
	}
	public List<User> searchFriends(User user){//得到用户的所有好友
		UserDao ud = new UserDao();
		return ud.searchFriends(user);
	}
	public boolean deleteFriend(User user1,User user2) {//删除好友
		UserDao ud = new UserDao();
		return ud.deleteFriend(user1, user2);
	}
	public int getRankByName(User user) {//得到用户等级
		UserDao ud = new UserDao();
		return ud.getRankByName(user);
	}
	public List<User> getAllUsers(){//得到所有用户，管理员特权
		UserDao ud = new UserDao();
		return ud.getAllUser();
	}
}
