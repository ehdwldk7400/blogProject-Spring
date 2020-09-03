package com.jin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.dao.PostDAO;
import com.jin.doamin.postVO;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostDAO dao;

	@Override
	public List<postVO> postList() throws Exception {
		// TODO Auto-generated method stub
		return dao.postList();
	}

}
