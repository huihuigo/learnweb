package org.huihui.user;

public class Comment {
	private int bid;//��Ӧ΢����id��Ψһ��ʶ
	private String name;//�����ߵ��û���
	private String bcomment;//���۵�����
	public Comment(){
		
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBcomment() {
		return bcomment;
	}
	public void setBcomment(String bcomment) {
		this.bcomment = bcomment;
	}
}
