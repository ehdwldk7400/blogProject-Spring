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
		
		String str ="";
		
		str += "  <table align='center' width='100%' ccellpadding='0' border='0' valign='top'" + 
				"        style='background-color: #f7f7f9;; margin: 0 auto;'>" + 
				"        <tr>" + 
				"            <td>" + 
				"                <table align='center' cellpadding='0' cellspacing='0' border='0' valign='top'" + 
				"                    style='margin: 0 auto; width: 600px;'>" + 
				"                    <tr>" + 
				"                        <td bgcolor='#1f3263' style='padding: 14px 30px;'></td>" + 
				"                    </tr>" + 
				"                    <tr>" + 
				"                        <td>" + 
				"                            <table align='center' width='100%' cellpadding='0' cellspacing='0' border='0' valig='top'>" + 
				"                                <tr>" + 
				"                                    <td style='padding: 40px 30px;' bgcolor='#ffffff'>" + 
				"                                        <table width='100%' cellpadding='0' cellspacing='0' border='0' valig='top'>" + 
				"                                            <td style='color: #535768; font-size: 28px; font-weight: bold;'" + 
				"                                                ont-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun" + 
				"                                                Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;" + 
				"                                                letter-spacing:-0.05em;'>" + 
				"                                                <span style='color: #4998f1;'>회원가입 인증</span>" + 
				"                                                안내입니다." + 
				"                                            </td>" + 
				"                                            <tr>" + 
				"                                                <td style='padding-top: 20px;'>" + 
				"                                                    <table width='100%' cellpadding='0' cellspacing='0' border='0'" + 
				"                                                        valign='top'>" + 
				"                                                        <tr>" + 
				"                                                            <td" + 
				"                                                                style='color:#535768; font-size:14px; font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif; letter-spacing:-0.05em; line-height: 1.71;'>" + 
				"                                                                안녕하세요. Yang Dongjin Post 입니다.<br><br>" + 
				"                                                                회원가입 화며네 인증번호를 입력하시면 회원가입이 완료됩니다.<br>" + 
				"                                                                인증번호는 발송된 시점부터 10분간만 유효하니 확인 후 바로 입력해 주시기 바랍니다." + 
				"                                                            </td>" + 
				"                                                        </tr>" + 
				"                                                        <tr>" + 
				"                                                            <td style='padding: 25px 0;'>" + 
				"                                                                <table width='100%' cellpadding='0' cellspacing='0'" + 
				"                                                                    border='0' valign='top'" + 
				"                                                                    style='text-align: center; border-top: 1px solid #c9cbd4; padding: 25px 10px; font-size:14px; font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif; color: #535768; line-height: 1.71; background-color: #efeff4;'>" + 
				"                                                                    <tr>" + 
				"                                                                        <td valign='top' width='100%'" + 
				"                                                                            style='color: #535768; font-size: 21px; font-weight: bold;'>" + 
				"                                                                            인증번호" + 
				"                                                                            <span style='color: #4998f1;'>"+ authCode +"</span>" + 
				"                                                                        </td>" + 
				"                                                                    </tr>" + 
				"" + 
				"                                                                        " + 
				"                                                                </table>" + 
				"                                                            </td>" + 
				"                                                        </tr>" + 
				"                                                    </table>" + 
				"                                                </td>" + 
				"                                            </tr>" + 
				"                                        </table>" + 
				"                                    </td>" + 
				"                                </tr>" + 
				"" + 
				"                            </table>" + 
				"                        </td>" + 
				"                    </tr>" + 
				"" + 
				"                </table>" + 
				"            </td>" + 
				"        </tr>" + 
				"" + 
				"    </table>";
		
	
		
		session.setAttribute("userid", user);
		session.setAttribute("authCode", authCode);
		session.setAttribute("random", random);
		
		String subject = "회원가입을 위해 인증을 진행해 주세요.";
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		
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
