package com.jin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.ReplyVO;

public interface ReplyDAO {

	//댓글 목록 (페이징 전 )
	public List<ReplyVO> list(int bno) throws Exception;
	
	//전체 댓글 수
	public int replycnt(int bno) throws Exception;
	
	// 댓글 쓰기
	public void rePlycreate(ReplyVO vo) throws Exception;
	
	// 대댓글 쓰기
	public void rePlyGroupcreate(ReplyVO vo) throws Exception;
	
	//댓글 삭제
	public void delete(int rno) throws Exception;
	
	// 댓글 수정
	public void update(ReplyVO vo) throws Exception;

	
	// 댓글 목록 (페이징 후)
	public List<ReplyVO> listPage(@Param ("bno") int bno, @Param("cri") Criteria cri) throws Exception;
}
