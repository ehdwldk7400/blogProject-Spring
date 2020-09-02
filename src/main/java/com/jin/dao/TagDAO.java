package com.jin.dao;

import java.util.List;

import com.jin.doamin.tagVO;

public interface TagDAO {
	
	// Tag ADD
	public void createTag(tagVO vo) throws Exception;
	
	// Tag List
	public List<tagVO> tagList() throws Exception;
}
