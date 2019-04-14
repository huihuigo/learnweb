package org.huihui.util;
import java.sql.*;
public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/db_huibo?serverTimezone=GMT";
	private static final String UNAME = "root";
	private static final String UPWD = "123456";
	public static Connection connection = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	static void getConnection() throws ClassNotFoundException, SQLException {//���������ݿ������
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(URL,UNAME,UPWD);
	}
	static void prepareStatement(String sql,Object...params) throws SQLException {//���ظ�ִ��sql���
		pstmt = connection.prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1,params[i]);
			}
		}
	}
	public static void close() {//�رոùصĶ���
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean executeUpdate(String sql,Object...params) {//��ɾ��
		try {
			getConnection();
			prepareStatement(sql,params);
			int result = pstmt.executeUpdate();
			if(result>0)
				return true;
			else
				return false;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			close();
		}
	}
	public static ResultSet executeQuery(String sql,Object...params) {//��
		try {
			getConnection();
			prepareStatement(sql,params);
			rs = pstmt.executeQuery();
			return rs;
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			return rs;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return rs;
		}
		catch(Exception e) {
			e.printStackTrace();
			return rs;
		}
	}
}
