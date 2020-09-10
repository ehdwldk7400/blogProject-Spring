package com.jin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jin.dao.PostDAO;
import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.postPageVO;
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

	@Override
	public void wirtePost(postVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.wirtePost(vo);
	}

	@Transactional
	@Override
	public postVO read(postVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.postVirecnt(vo);
		return dao.read(vo);
	}

	@Override
	public void updatePost(postVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.updatePost(vo);
	}

	@Override
	public void deletePost(postVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.deletePost(vo);
	}

	@Override
	public List<postVO> tagList(postVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.tagList(vo);
	}



	@Override
	public int getTotalTag(String tagname) throws Exception {
		// TODO Auto-generated method stub
		return dao.getTotalTag(tagname);
	}

	@Override
	public List<postVO> postPaging(int pageNum, int amount) throws Exception {
		// TODO Auto-generated method stub
		return dao.postPaging(pageNum, amount);
	}

	@Override
	public postPageVO tagPaging(Criteria cri, String tagname) throws Exception {
		// TODO Auto-generated method stub
		return new postPageVO(dao.tagPaging(cri, tagname), dao.getTotalTag(tagname));
	}

}
