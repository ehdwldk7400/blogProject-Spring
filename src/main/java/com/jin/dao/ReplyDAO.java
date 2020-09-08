package com.jin.dao;

import java.util.List;

import com.jin.doamin.ReplyVO;

public interface ReplyDAO {

	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> list(int bno) throws Exception;
	
	//ÀüÃ¼ ´ñ±Û ¼ö
	public int replycnt(int bno) throws Exception;
}
