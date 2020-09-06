package com.jin.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jin.doamin.usersVO;
import com.jin.service.MailService;


@Controller
@RequestMapping(value="mail")
public class MailContorller {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MailService mailService;

	@ResponseBody
	@RequestMapping(value="creareEmailCheck", method=RequestMethod.GET)
	public boolean createEmailCheck( usersVO user, int random, HttpServletRequest req) throws Exception{

		logger.info("createEmailCheck get............");
		logger.info("userEmail : " + user.getUserid());
		logger.info("random : " + random);
		
		
		//인증 번호 이메일 발송
		int ran = new Random().nextInt(900000) + 100000;
		HttpSession session = req.getSession();
		
		String authCode = String.valueOf(ran);
		
		session.setAttribute("userid", user);
		session.setAttribute("authCode", authCode);
		session.setAttribute("random", random);
		
		String subject = "회원가입 인증 코드 발급 안내 입니다.";
		StringBuilder sb = new StringBuilder();
		sb.append("귀하의 인증 코드는 " + authCode + " 입니다.");
		
		String sendEmailId = "yjw5951@gmail.com";
		
		return mailService.send(subject, sb.toString(), sendEmailId, user.getUserid(), null);

	}
	
	@ResponseBody
	@RequestMapping(value="emailAuth", method=RequestMethod.GET)
	public ResponseEntity<String> emailAuth(String authCode, String random, HttpSession session){
		
		logger.info("emailAuth get............");

		String JoiunCode = (String) session.getAttribute("authCode");
		String JoinRandom = Integer.toString((int)session.getAttribute("random"));
		
		

		logger.info("authCode : " + authCode);
		logger.info("random : " + random);
		logger.info("JoiunCode : " + JoiunCode);
		logger.info("JoinRandom : " + JoinRandom);
		
		
		if(JoiunCode.equals(authCode) && JoinRandom.equals(random)) {
			return new ResponseEntity<String>("complete", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("false", HttpStatus.OK);
		}
	}
}
