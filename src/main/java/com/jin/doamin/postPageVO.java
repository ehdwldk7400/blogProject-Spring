 package com.jin.doamin;

import java.util.List;

public class postPageVO {
	
	private List<postVO> list;
	
	private int postcnt;
	
	public postPageVO(List<postVO> list, int postcnt) {
		this.list = list;
		this.postcnt = postcnt;
	}

	public List<postVO> getList() {
		return list;
	}

	public void setList(List<postVO> list) {
		this.list = list;
	}

	public int getPostcnt() {
		return postcnt;
	}

	public void setPostcnt(int postcnt) {
		this.postcnt = postcnt;
	}

	@Override
	public String toString() {
		return "postPageVO [list=" + list + ", postcnt=" + postcnt + "]";
	}
	
	

}
