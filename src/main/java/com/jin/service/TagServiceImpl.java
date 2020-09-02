package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.dao.TagDAO;
import com.jin.doamin.tagVO;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagDAO dao;
	
	@Override
	public void createTag(tagVO vo) throws Exception {
		// TODO Auto-generated method stub	
		dao.createTag(vo);
	}

}
