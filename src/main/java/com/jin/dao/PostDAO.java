package com.jin.dao;

import java.util.List;

import com.jin.doamin.postVO;

public interface PostDAO {
	
	// �Խù� ����Ʈ
	public List<postVO> postList() throws Exception;
	
	// �Խù� �ۼ�
	public void wirtePost(postVO vo) throws Exception;
	
	// �Խù� �б�
	public postVO read(postVO vo) throws Exception;
	
	// ��ȸ�� ����
	public void postVirecnt(postVO vo) throws Exception;
	
	// �Խù� ����
	public void updatePost(postVO vo) throws Exception;
	
	// �Խù� ����
	public void deletePost(postVO vo) throws Exception;

}
