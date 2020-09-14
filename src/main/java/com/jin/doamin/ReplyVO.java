package com.jin.doamin;

import java.sql.Date;

public class ReplyVO {
	private int rno;
	private int bno;
	private int rnogroup;
	private int bundel_order;

	private String replytext;
	private String replyer;
	private String open;
	private Date regdate;
	private Date updatedate;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRnogroup() {
		return rnogroup;
	}
	public void setRnogroup(int rnogroup) {
		this.rnogroup = rnogroup;
	}
	
	public int getBundel_order() {
		return bundel_order;
	}
	public void setBundel_order(int bundel_order) {
		this.bundel_order = bundel_order;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}
	public String getReplyer() {
		return replyer;
	}
	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", rnogroup=" + rnogroup + ", bundel_order=" + bundel_order
				+ ", replytext=" + replytext + ", replyer=" + replyer + ", open=" + open + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + "]";
	}
	
	

}
