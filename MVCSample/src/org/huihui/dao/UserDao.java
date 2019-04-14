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
	public boolean isExist(User user){//��ѯ�û��Ƿ����
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
	public boolean isRight(User user) {//��ѯ�û����������Ƿ�һ��
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
	public boolean inSert(User user) {//����û�,����ע��
		String sql = "insert into user_info (user_name,user_pwd,user_sex,user_email,user_introduce,user_type) values (?,?,?,?,?,?)";
		return DBUtil.executeUpdate(sql,user.getName(),user.getPwd(),user.getSex(),user.getEmail(),user.getIntroduce(),user.getType());
	}
	public String searchNameByEmail(String email) {//ͨ�������������û���,����ʵ�������½
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
	public String searchIntroduceByName(User user) {//ͨ���û������û����˼��
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
	public String searchTypeByName(User user) {//ͨ���û�����ѯ�û����
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
	public int searchVisitNumByName(User user) {//ͨ���û������ҷ�����
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
	public int getExperienceByName(User user) {//ͨ���û������Ҿ���
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
	public boolean updateVisitNumByName(User user) {//�����û�������
		String sql = "update user_info set visit_num = visit_num + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean addExperienceByName(User user) {//�����û�����
		String sql = "update user_info set user_experience = user_experience + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean resetExperienceByName(User user) {//�����û�������
		String sql = "update user_info set user_experience = 0 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public boolean updateRankByName(User user) {//�����û��ȼ�
		String sql = "update user_info set user_rank = user_rank + 1 where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getName());
	}
	public int getRankByName(User user) {//�õ��û��ȼ�
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
	public boolean update(User user) {//�޸��û����˼��
		String sql = "update user_info set user_introduce = ? where user_name = ?";
		return DBUtil.executeUpdate(sql,user.getIntroduce(),user.getName());
	}
	public boolean shareNew(Huibo huibo) {//�����µ�΢����������΢����һ����¼
		String sql = "insert into user_huibo (user_name,huibo_text,huibo_type) values (?,?,?)";
		return DBUtil.executeUpdate(sql,huibo.getName(),huibo.getBtext(),huibo.getBtype());
	}
	public String searchSexByName(User user) {//ͨ���û��������Ա�
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
	public List<Huibo> getUserHuiboByPage(User user,int pageNum,int pageSize){//�����û�������΢��ͨ����ҳ��ʾ
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
	public int getHuiboNumByName(User user) {//�����û�����������΢������
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
	public boolean deleteHuibo(Huibo huibo) {//ɾ��ָ����΢��
		String sql = "delete from user_huibo where id = ?";
		return DBUtil.executeUpdate(sql,huibo.getId());
	}
	public List<User> searchUserByName(User user) {//ͨ���û������������û���������Ϣ(֧��ģ����ѯ)
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
	public boolean addGood(Huibo huibo) {//����΢��������
		String sql = "update user_huibo set user_good = user_good + 1 where id= ? ";
		return DBUtil.executeUpdate(sql,huibo.getId());
	}
	public List<Comment> showHuiboComment(Huibo huibo){//����ָ��΢������������
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
	public boolean addComment(Comment comment) {//���Ӷ�ָ��΢��������
		String sql = "insert into user_comment(huibo_id,user_name,comment_text) values (?,?,?)";
		return DBUtil.executeUpdate(sql,comment.getBid(),comment.getName(),comment.getBcomment());
	}
	public boolean requestBeFriend(User requestuser,User responseuser) {//������Ӻ���
		String sql = "insert into user_relation (request_user_name,response_user_name,status) values (?,?,\"����\")";
		return DBUtil.executeUpdate(sql,requestuser.getName(),responseuser.getName());
	}
	public List<User> getRequestUser(User responseuser) {//ͨ����������û����õ�����������û���
		String sql = "select request_user_name from user_relation where response_user_name = ? and status = ?";
		ResultSet rs = DBUtil.executeQuery(sql,responseuser.getName(),"����");
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
	public boolean agreeRequest(User requestuser,User responseuser) {//ͬ���������
		String sql = "update user_relation set status = ? where request_user_name = ? and response_user_name = ?";
		return DBUtil.executeUpdate(sql,"ͬ��",requestuser.getName(),responseuser.getName());
	}
	public boolean refuseRequest(User requestuser,User responseuser) {//�ܾ���������
		String sql = "update user_relation set status = ? where request_user_name = ? and response_user_name = ?";
		return DBUtil.executeUpdate(sql,"�ܾ�",requestuser.getName(),responseuser.getName());
	}
	public List<User> searchFriends(User user){//����ָ���û������к���
		String sql = "select request_user_name from user_relation where response_user_name = ? and status = ? union select response_user_name from user_relation where request_user_name = ? and status = ?";
		ResultSet rs = DBUtil.executeQuery(sql,user.getName(),"ͬ��",user.getName(),"ͬ��");
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
	public boolean deleteFriend(User user1,User user2) {//ɾ���û���ָ�����ѣ���Ϊ������Ϊ����ʱ���û�����������Ҳ��������Ӧ�������Բ�ѯ�������״̬ʱͬ���
		String sql = "delete from user_relation where request_user_name = ? and response_user_name = ? and status = ? or request_user_name = ? and response_user_name = ? and status = ?";
		return DBUtil.executeUpdate(sql,user1.getName(),user2.getName(),"ͬ��",user2.getName(),user1.getName(),"ͬ��");
	}
	public List<User> getAllUser(){//��ȡ�����û���Ϣ
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
	public boolean deleteComment(Comment comment) {//ɾ�����ĳ��΢������������
		String sql = "delete from user_comment where huibo_id = ?";
		return DBUtil.executeUpdate(sql,comment.getBid());
	}
}
