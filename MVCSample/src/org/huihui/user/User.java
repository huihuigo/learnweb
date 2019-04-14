package org.huihui.user;

public class User {
	private String name;//�û���
	private String pwd;//����
	private String sex;//�Ա�
	private String email;//����
	private String introduce;//��������
	private int visitnum;//������
	private int experience;//������
	private int rank;//�ȼ�
	private String type;//�û����
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVisitnum() {
		return visitnum;
	}
	public void setVisitnum(int visitnum) {
		this.visitnum = visitnum;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public User() {
		
	}
	public User(String name,String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	public User(String name,String pwd,String sex,String email,String introduce,String type) {
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.email = email;
		this.introduce = introduce;
		this.type = type;
	}
	/*public User(String name,String pwd,String sex,String email) {
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.email = email;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
