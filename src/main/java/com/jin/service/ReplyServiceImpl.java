package com.jin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.dao.ReplyDAO;
import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDAO dao;
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}

	@Override
	public int replycnt(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.replycnt(bno);
	}

	@Override
	public void rePlycreate(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		dao.rePlycreate(vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		// TODO Auto-generated method stub
		
		dao.delete(rno);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public ReplyPageVO listPage(int bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return new ReplyPageVO(dao.list(bno), dao.replycnt(bno));
	}
	

}
