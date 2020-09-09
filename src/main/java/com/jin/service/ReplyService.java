package com.jin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.ReplyVO;

public interface ReplyService {
	
	//´ñ±Û ¸ñ·Ï
	public List<ReplyVO> list(int bno) throws Exception;
	
	//ÀüÃ¼ ´ñ±Û ¼ö
	public int replycnt(int bno) throws Exception;
	
	// ´ñ±Û ¾²±â
	public void rePlycreate(ReplyVO vo) throws Exception;
	
	// ´ñ±Û »èÁ¦
	public void delete(int rno) throws Exception;
	
	// ´ñ±Û ¼öÁ¤
	public void update(ReplyVO vo) throws Exception;
	
	// ´ñ±Û ¸ñ·Ï (ÆäÀÌÂ¡ ÈÄ)
	public ReplyPageVO listPage(@Param ("bno") int bno, @Param("cri") Criteria cri) throws Exception;
}
