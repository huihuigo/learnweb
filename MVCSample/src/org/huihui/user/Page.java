package org.huihui.user;

import java.util.List;

public class Page {//一张页面
	private int pageNum;//当前页的页码
	private int pageSize;//页面显示的数据条数
	private int totalRecord;//用户的总微博条数
	private int totalPage;//总页数，通过计算获得，得到了总微博条数和页面显示的数据条数即可计算得到
	private List<Data> datas;//页面显示的数据集合
	private String name;//用户名
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
