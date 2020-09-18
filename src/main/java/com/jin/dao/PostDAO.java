package com.jin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.doamin.Criteria;
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
	
	// Tag ����Ʈ
	public List<postVO> tagList(postVO vo) throws Exception;
	
	// ���������� �Խù� ����¡ (���� ��ũ��)
	public List<postVO> postPaging(@RequestParam("cri")Criteria cri) throws Exception;
	
	// �±׺� �Խù� ����¡ ó��
	public List<postVO> tagPaging(@Param("cri") Criteria cri, @Param("tagname") String tagname) throws Exception;
	
	// �±׺� �Խù� ī��Ʈ
	public int getTotalTag(String tagname) throws Exception;

}
