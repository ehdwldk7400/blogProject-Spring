package com.jin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jin.doamin.Criteria;
import com.jin.doamin.postPageVO;
import com.jin.doamin.postVO;

public interface PostService {
	
	public List<postVO> postList() throws Exception;
	
	// �Խù� �ۼ�
	public void wirtePost(postVO vo) throws Exception;
	
	// �Խù� �б�
	public postVO read(postVO vo) throws Exception;
	
	// �Խù� ����
	public void updatePost(postVO vo) throws Exception;
	
	// �Խù� ����
	public void deletePost(postVO vo) throws Exception;

	// Tag ����Ʈ
	public List<postVO> tagList(postVO vo) throws Exception;
	
	// �Խù� ����¡
	public List<postVO> postPaging(int pageNum, int amount) throws Exception;
	
	// �±׺� �Խù� ����¡ ó��
	public postPageVO tagPaging(@Param("cri") Criteria cri, @Param("tagname") String tagname) throws Exception;

	// �±׺� �Խù� ī��Ʈ
	public int getTotalTag(String tagname) throws Exception;
}
