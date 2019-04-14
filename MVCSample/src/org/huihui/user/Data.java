package org.huihui.user;

import java.util.List;

public class Data {//查看微博时页面显示的数据
	private Huibo huibo;//包括微博的内容，类型，点赞数
	private List<Comment> comments;//以及微博的所有评论
	public Data(Huibo huibo,List<Comment> comments) {
		this.huibo = huibo;
		this.comments = comments;
	}
	public Huibo getHuibo() {
		return huibo;
	}
	public void setHuibo(Huibo huibo) {
		this.huibo = huibo;
	}
	public List<Comment> getComments(){
		return this.comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
