package com.jin.doamin;

import java.sql.Date;

public class usersVO {
	
	private String userid;
	private String userpw;
	private String username;
	private String gender;
	private int verify;
	private Date regdate;
	private Date updatedate;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getVerify() {
		return verify;
	}
	public void setVerify(int verify) {
		this.verify = verify;
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
		return "usersVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", gender=" + gender
				+ ", verify=" + verify + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	

}
