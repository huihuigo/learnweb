package org.huihui.user;

import java.util.List;

public class Data {//�鿴΢��ʱҳ����ʾ������
	private Huibo huibo;//����΢�������ݣ����ͣ�������
	private List<Comment> comments;//�Լ�΢������������
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
