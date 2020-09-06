package com.jin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.dao.UserDAO;
import com.jin.doamin.usersVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;
	
	@Override
	public void createUser(usersVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.createUser(vo);
	}

	@Override
	public usersVO login(usersVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	@Override
	public int idchk(usersVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.idchk(vo);
	}

}
