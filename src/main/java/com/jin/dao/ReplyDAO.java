package com.jin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.ReplyVO;

public interface ReplyDAO {

	//��� ��� (����¡ �� )
	public List<ReplyVO> list(int bno) throws Exception;
	
	//��ü ��� ��
	public int replycnt(int bno) throws Exception;
	
	// ��� ����
	public void rePlycreate(ReplyVO vo) throws Exception;
	
	// ���� ����
	public void rePlyGroupcreate(ReplyVO vo) throws Exception;
	
	//��� ����
	public void delete(int rno) throws Exception;
	
	// ��� ����
	public void update(ReplyVO vo) throws Exception;

	
	// ��� ��� (����¡ ��)
	public List<ReplyVO> listPage(@Param ("bno") int bno, @Param("cri") Criteria cri) throws Exception;
}
