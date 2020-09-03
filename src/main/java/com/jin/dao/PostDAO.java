package com.jin.dao;

import java.util.List;

import com.jin.doamin.postVO;

public interface PostDAO {
	
	// 게시물 리스트
	public List<postVO> postList() throws Exception;

}
