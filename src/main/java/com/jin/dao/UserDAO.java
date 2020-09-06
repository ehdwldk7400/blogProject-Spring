package com.jin.dao;

import com.jin.doamin.usersVO;

public interface UserDAO {
	
	// 유저 생성
	public void createUser(usersVO vo)throws Exception;
	
	// Login 
	public usersVO login(usersVO vo) throws Exception;
	
	// ID Cheack
	public int idchk(usersVO vo) throws Exception;
	
}
