package org.huihui.user;

public class Huibo {
	private int id;//΢����Ψһ��ʶ
	private String name;//����΢�����û���
	private String btext;//΢��ԭ��
	private String btype;//΢������
	private int good;//������
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
