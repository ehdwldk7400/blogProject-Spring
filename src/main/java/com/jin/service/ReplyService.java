package com.jin.service;

import java.util.List;

import com.jin.doamin.ReplyVO;

public interface ReplyService {
	
	//��� ���
	public List<ReplyVO> list(int bno) throws Exception;
	
	//��ü ��� ��
	public int replycnt(int bno) throws Exception;
	
	// ��� ����
	public void rePlycreate(ReplyVO vo) throws Exception;

}
