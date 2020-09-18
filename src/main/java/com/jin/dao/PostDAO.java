package com.jin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.doamin.Criteria;
import com.jin.doamin.postVO;

public interface PostDAO {
	
	// 게시물 리스트
	public List<postVO> postList() throws Exception;
	
	// 게시물 작성
	public void wirtePost(postVO vo) throws Exception;
	
	// 게시물 읽기
	public postVO read(postVO vo) throws Exception;
	
	// 조회수 증가
	public void postVirecnt(postVO vo) throws Exception;
	
	// 게시물 수정
	public void updatePost(postVO vo) throws Exception;
	
	// 게시물 삭제
	public void deletePost(postVO vo) throws Exception;
	
	// Tag 리스트
	public List<postVO> tagList(postVO vo) throws Exception;
	
	// 메인페이지 게시물 페이징 (무슨 스크롤)
	public List<postVO> postPaging(@RequestParam("cri")Criteria cri) throws Exception;
	
	// 태그별 게시물 페이징 처리
	public List<postVO> tagPaging(@Param("cri") Criteria cri, @Param("tagname") String tagname) throws Exception;
	
	// 태그별 게시물 카운트
	public int getTotalTag(String tagname) throws Exception;

}
