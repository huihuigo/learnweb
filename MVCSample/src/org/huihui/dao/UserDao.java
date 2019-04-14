package org.huihui.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.huihui.user.Comment;
import org.huihui.user.Huibo;
import org.huihui.user.User;
import org.huihui.util.DBUtil;
public class UserDao {
	public boolean isExist(User user){//查询用户是否存在
		boolean result = false;
		String sql = "select count(*) from user_info where user_name = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next()) {
				if(rs.getInt(1)>0)
					result = true;
			}
			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		finally {
			DBUtil.close();
		}
	}
	public boolean isRight(User user) {//查询用户名与密码是否一致
		String sql = "select user_pwd from user_info where user_name = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		boolean result = false;
		try {
			if(rs.next()) {
				if(rs.getString(1).equals(user.getPwd()))
					result = true;
			}
			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		finally {
			DBUtil.close();
		}
	}
	public boolean inSert(User user) {//添加用户,用于注册
		String sql = "insert into user_info (user_name,user_pwd,user_sex,user_email,user_introduce,user_type) values (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql,user.getName(),user.getPwd(),user.getSex(),user.getEmail(),user.getIntroduce(),user.getType());
	}
	public String searchNameByEmail(String email) {//通过邮箱来查找用户名,用于实现邮箱登陆
		String sql = "select user_name from user_info where user_email = ?";
		String result = null;
		ResultSet rs = DBUtil.executeQuery(sql,email);
		try {
			if(rs.next()) {
				result = rs.getString(1);
			}
			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		finally {
			DBUtil.close();
		}
	}
	public String searchIntroduceByName(User user) {//通过用户名查用户个人简介
		String sql = "select user_introduce from user_info where user_name = ?";
		String result = null;
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next()) {
				result = rs.getString(1);
			}
			return result;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return result;
		}
		finally {
			DBUtil.close();
		}
	}
	public String searchTypeByName(User user) {//通过用户名查询用户类别
		String sql = "select user_type from user_info where user_name = ?";
		String result = null;
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next()) {
				result = rs.getString(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return result;
	}
	public int searchVisitNumByName(User user) {//通过用户名查找访问量
		String sql = "select visit_num from user_info where user_name = ?";
		int result = 0;
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next())
				result = rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return result;
	}
	public int getExperienceByName(User user) {//通过用户名查找经验
		int result = 0;
		String sql = "select user_experience from user_info where user_name = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next())
				result = rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return result;
	}
	public boolean updateVisitNumByName(User user) {//增加用户访问量
		String sql = "update user_info set visit_num = visit_num + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean addExperienceByName(User user) {//增加用户经验
		String sql = "update user_info set user_experience = user_experience + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean resetExperienceByName(User user) {//重置用户经验条
		String sql = "update user_info set user_experience = 0 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean updateRankByName(User user) {//增加用户等级
		String sql = "update user_info set user_rank = user_rank + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public int getRankByName(User user) {//得到用户等级
		String sql = "select user_rank from user_info where user_name = ?";
		int result = 0;
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return result;
	}
	public boolean update(User user) {//修改用户个人简介
		String sql = "update user_info set user_introduce = ? where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getIntroduce(),user.getName());
	}
	public boolean shareNew(Huibo huibo) {//发布新的微博，即增加微博表一条记录
		String sql = "insert into user_huibo (user_name,huibo_text,huibo_type) values (?,?,?)";
		return DBUtil.executeUpdate(sql,huibo.getName(),huibo.getBtext(),huibo.getBtype());
	}
	public String searchSexByName(User user) {//通过用户名查找性别
		String sql = "select user_sex from user_info where user_name = ?";
		String sex = null;
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		try {
			if(rs.next()) {
				sex = rs.getString(1);
			}
			return sex;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return sex;
		}
		finally {
			DBUtil.close();
		}
	}
	public List<Huibo> getUserHuiboByPage(User user,int pageNum,int pageSize){//查找用户发布的微博通过分页显示
		List<Huibo> list = new ArrayList<Huibo>();
		String sql = "select * from user_huibo where user_name = ? limit ? , ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName(),(pageNum-1)*pageSize,pageSize);
		try {
			while(rs.next()) {
				Huibo huibo = new Huibo();
				huibo.setId(rs.getInt(1));
				huibo.setName(rs.getString(2));
				huibo.setBtext(rs.getString(3));
				huibo.setBtype(rs.getString(4));
				huibo.setGood(rs.getInt(5));
				list.add(huibo);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtil.close();
		}
	}
	public int getHuiboNumByName(User user) {//查找用户发布的所有微博数量
		String sql = "select count(*) from user_huibo where user_name = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		int result = -1;
		try {
			if(rs.next())
				result = rs.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return result;
	}
	public boolean deleteHuibo(Huibo huibo) {//删除指定的微博
		String sql = "delete from user_huibo where id = ?";
		return DBUtil.executeUpdate(sql,huibo.getId());
	}
	public List<User> searchUserByName(User user) {//通过用户名查找所有用户的所有信息(支持模糊查询)
		String sql = "select user_name,user_sex,user_introduce from user_info where user_name like ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName());
		List<User> list = new ArrayList<>();
		try {
			while(rs.next()) {
				User allUser = new User();
				allUser.setName(rs.getString("user_name"));
				allUser.setSex(rs.getString("user_sex"));
				allUser.setIntroduce(rs.getString("user_introduce"));
				list.add(allUser);
			}
			return list;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtil.close();
		}
	}
	public boolean addGood(Huibo huibo) {//增加微博点赞数
		String sql = "update user_huibo set user_good = user_good + 1 where id= ? ";
		return DBUtil.executeUpdate(sql,huibo.getId());
	}
	public List<Comment> showHuiboComment(Huibo huibo){//查找指定微博的所有评论
		String sql = "select user_name,comment_text from user_comment where huibo_id = ?";
		ResultSet rs = DBUtil.executeQuery(sql,huibo.getId());
		List<Comment> list = new ArrayList<>();
		try {
			while(rs.next()) {
				Comment temp = new Comment();
				temp.setName(rs.getString("user_name"));
				temp.setBcomment(rs.getString("comment_text"));
				list.add(temp);
			}
			return list;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			DBUtil.close();
		}
	}
	public boolean addComment(Comment comment) {//增加对指定微博的评论
		String sql = "insert into user_comment(huibo_id,user_name,comment_text) values (?,?,?)";
		return DBUtil.executeUpdate(sql,comment.getBid(),comment.getName(),comment.getBcomment());
	}
	public boolean requestBeFriend(User requestuser,User responseuser) {//请求添加好友
		String sql = "insert into user_relation (request_user_name,response_user_name,status) values (?,?,\"请求\")";
		return DBUtil.executeUpdate(sql,requestuser.getName(),responseuser.getName());
	}
	public List<User> getRequestUser(User responseuser) {//通过被请求的用户名得到所有请求的用户名
		String sql = "select request_user_name from user_relation where response_user_name = ? and status = ?";
		ResultSet rs = DBUtil.executeQuery(sql,responseuser.getName(),"请求");
		List<User> users = new ArrayList<>();
		try {
			while(rs.next()) {
				User temp = new User();
				temp.setName(rs.getString("request_user_name"));
				users.add(temp);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return users;
	}
	public boolean agreeRequest(User requestuser,User responseuser) {//同意好友申请
		String sql = "update user_relation set status = ? where request_user_name = ? and response_user_name = ?";
		return DBUtil.executeUpdate(sql,"同意",requestuser.getName(),responseuser.getName());
	}
	public boolean refuseRequest(User requestuser,User responseuser) {//拒绝好友申请
		String sql = "update user_relation set status = ? where request_user_name = ? and response_user_name = ?";
		return DBUtil.executeUpdate(sql,"拒绝",requestuser.getName(),responseuser.getName());
	}
	public List<User> searchFriends(User user){//查找指定用户的所有好友
		String sql = "select request_user_name from user_relation where response_user_name = ? and status = ? union select response_user_name from user_relation where request_user_name = ? and status = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName(),"同意",user.getName(),"同意");
		List<User> users = new ArrayList<>();
		try {
			while(rs.next()) {
				User temp = new User();
				temp.setName(rs.getString(1));
				temp.setSex(searchSexByName(temp));
				temp.setIntroduce(searchIntroduceByName(temp));
				users.add(temp);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return users;
	}
	public boolean deleteFriend(User user1,User user2) {//删除用户的指定好友，因为当初成为好友时，用户可能是请求方也可能是响应方，所以查询两种情况状态时同意的
		String sql = "delete from user_relation where request_user_name = ? and response_user_name = ? and status = ? or request_user_name = ? and response_user_name = ? and status = ?";
		return DBUtil.executeUpdate(sql,user1.getName(),user2.getName(),"同意",user2.getName(),user1.getName(),"同意");
	}
	public List<User> getAllUser(){//获取所有用户信息
		String sql = "select user_name,user_sex,user_introduce from user_info";
		ResultSet rs = DBUtil.executeQuery(sql);
		List<User> users = new ArrayList<>();
		try {
			while(rs.next()) {
				User temp = new User();
				temp.setName(rs.getString(1));
				temp.setSex(rs.getString(2));
				temp.setIntroduce(rs.getString(3));
				users.add(temp);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
		return users;
	}
	public boolean deleteComment(Comment comment) {//删除针对某条微博的所有评论
		String sql = "delete from user_comment where huibo_id = ?";
		return DBUtil.executeUpdate(sql,comment.getBid());
	}
}
