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
		// 1. code�� �̿� access_token �ޱ�
		// 2. access_token�� �̿� ����� profile ���� ��������
		SNSLogin snsLogin = new SNSLogin(googleSns);
		usersVO profile = snsLogin.getUserProfile(code);
		
		logger.info("profile : " + profile);
		model.addAttribute("login", profile);
		
		// 3. DB �ش� ������ �����ϴ��� üũ
		// 4. ���̵� ���� �� ���� �α���, ������ �� ȸ������
		
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
		// ���� ����
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(30*60);
		session.setAttribute("login", profile);
		return "redirect:/";
	}
}
