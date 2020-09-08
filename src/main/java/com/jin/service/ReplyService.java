package com.jin.service;

import java.util.List;

import com.jin.doamin.ReplyVO;

public interface ReplyService {
	
	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> list(int bno) throws Exception;
	
	//ÀüÃ¼ ´ñ±Û ¼ö
	public int replycnt(int bno) throws Exception;
	
	// ´ñ±Û ¾²±â
	public void rePlycreate(ReplyVO vo) throws Exception;

}
