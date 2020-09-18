package com.jin.doamin;

import java.sql.Date;

public class postVO {
	
	private String tagname;
	private int bno;
	private String title;
	private String content;
	private Date regdate;
	private int viewcnt;
	
	
	private Criteria cri;
	
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	@Override
	public String toString() {
		return "postVO [tagname=" + tagname + ", bno=" + bno + ", title=" + title + ", content=" + content
				+ ", regdate=" + regdate + ", viewcnt=" + viewcnt + ", cri=" + cri + "]";
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewent(int viewent) {
		this.viewcnt = viewent;
	}

}
