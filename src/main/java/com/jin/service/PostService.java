package com.jin.service;

import java.util.List;

import com.jin.doamin.postVO;

public interface PostService {
	
	public List<postVO> postList() throws Exception;
	
	// 게시물 작성
	public void wirtePost(postVO vo) throws Exception;
	
	// 게시물 읽기
	public postVO read(postVO vo) throws Exception;
	
	// 게시물 수정
	public void updatePost(postVO vo) throws Exception;
	
	// 게시물 삭제
	public void deletePost(postVO vo) throws Exception;

	// Tag 리스트
	public List<postVO> tagList(postVO vo) throws Exception;
	
	// 게시물 페이징
	public List<postVO> postPaging(int pageNum, int amount) throws Exception;


}
