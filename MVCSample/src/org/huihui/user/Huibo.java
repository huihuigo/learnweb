package org.huihui.user;

public class Huibo {
	private int id;//微博的唯一标识
	private String name;//发布微博的用户名
	private String btext;//微博原文
	private String btype;//微博类型
	private int good;//点赞数
	public Huibo() {
		
	}
	public Huibo(String name,String btext,String btype){
		this.name = name;
		this.btext = btext;
		this.btype = btype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBtext() {
		return btext;
	}
	public void setBtext(String btext) {
		this.btext = btext;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
