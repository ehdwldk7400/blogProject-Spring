package com.jin.service;

import java.util.List;

import com.jin.doamin.postVO;

public interface PostService {
	
	public List<postVO> postList() throws Exception;
}
