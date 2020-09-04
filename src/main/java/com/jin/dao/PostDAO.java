package com.jin.dao;

import java.util.List;

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

}
