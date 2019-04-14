package org.huihui.user;

import java.util.List;

public class Page {//һ��ҳ��
	private int pageNum;//��ǰҳ��ҳ��
	private int pageSize;//ҳ����ʾ����������
	private int totalRecord;//�û�����΢������
	private int totalPage;//��ҳ����ͨ�������ã��õ�����΢��������ҳ����ʾ�������������ɼ���õ�
	private List<Data> datas;//ҳ����ʾ�����ݼ���
	private String name;//�û���
	public Page(String name,int pageNum,int pageSize,int totalRecord) {
		this.name = name;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.totalPage = this.totalRecord%this.pageSize == 0 ? this.totalRecord/this.pageSize : this.totalRecord/this.pageSize+1;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}
	public String getName() {
		return name;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public List<Data> getDatas() {
		return datas;
	}
}
