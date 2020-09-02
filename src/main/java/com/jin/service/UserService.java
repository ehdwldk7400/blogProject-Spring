package com.jin.service;

import com.jin.doamin.usersVO;

public interface UserService {
	
	// 유저 생성
	public void createUser(usersVO vo)throws Exception;
	
	// Login 
	public usersVO login(usersVO vo) throws Exception;


}
