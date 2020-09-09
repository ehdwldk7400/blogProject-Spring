package com.jin.doamin;

public class Criteria {
	
	// 페이지 번호
	private int pageNum;
	
	// 페이지 당 보여지는 게시물 의 수
	private int amount;
	
	
	public Criteria() {
		this(1, 10);
	}


	public Criteria(int pageNum, int amount) {
		// TODO Auto-generated constructor stub
		this.pageNum = pageNum;
		this.amount = amount;
 	}


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
	
		if(amount < 0 || amount > 100) {
			this.amount = 10;
		}
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
	}
	
	
	

}
