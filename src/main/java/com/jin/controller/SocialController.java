package com.jin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.auth.SNSLogin;
import com.jin.auth.SnsValue;
import com.jin.dao.UserDAO;
import com.jin.doamin.usersVO;



@Controller
@RequestMapping(value = "social")
public class SocialController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private SnsValue googleSns;
	
	@Autowired
	private SnsValue naverSns;
	
	@RequestMapping(value = "googleCallback", method = { RequestMethod.GET, RequestMethod.POST})
	public String snsLoginCallback(Model model, @RequestParam String code) throws Exception{
		// 1. code를 이용 access_token 받기
		// 2. access_token을 이용 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(googleSns);
		usersVO profile = snsLogin.getUserProfile(code);
		
		logger.info("profile : " + profile);
		model.addAttribute("login", profile);
		
		// 3. DB 해당 유저가 존재하는지 체크
		// 4. 아이디가 존재 시 강제 로그인, 미존재 시 회원가입
		
		return "/blog";
	}
	
	@RequestMapping(value = "naverCallback", method = { RequestMethod.GET, RequestMethod.POST})
	public String NaverLoginCallback(Model model, @RequestParam String code,HttpServletRequest request) throws Exception{
		
		logger.info("NaverLoginCallback get..........");
		SNSLogin snsLogin = new SNSLogin(naverSns);
		usersVO profile =snsLogin.getUserProfile(code);
		logger.info("profile : " + profile);
		if(dao.idchk(profile) == 0) {
			dao.createUser(profile);			
		}
		// 세션 생성
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		session.setAttribute("login", profile);
		return "redirect:/";
	}
}
