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
		System.out.println("SerivceImpl Update vo : "+ vo);
		dao.update(vo);
	}

	@Override
	public ReplyPageVO listPage(int bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("SerivceImpl bno : "+ bno);
		System.out.println("SerivceImpl cri : "+ cri);
		return new ReplyPageVO(dao.listPage(bno, cri), dao.replycnt(bno));
	}

	@Override
	public void rePlyGroupcreate(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		
		dao.rePlyGroupcreate(vo);
		
	}

	@Override
	public void CommentDelete(int rno) throws Exception {
		// TODO Auto-generated method stub
		
		dao.CommentDelete(rno);
	}
	

}
