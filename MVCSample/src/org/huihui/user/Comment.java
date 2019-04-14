package org.huihui.user;

public class Comment {
	private int bid;//对应微博的id，唯一标识
	private String name;//评论者的用户名
	private String bcomment;//评论的内容
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
