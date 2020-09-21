package com.jin.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jin.doamin.usersVO;



@Controller
@RequestMapping(value = "social")
public class SocialController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth;
	
	@RequestMapping(value = "googleSignInCallback", method = { RequestMethod.GET, RequestMethod.POST})
	public String doSessionAssignActionPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Model model, @RequestParam String code, @RequestParam String scope){
		System.out.println("googleSignInCallback get...........");
//		String code = request.getParameter("code");

		logger.info("googleSignInCallback request : " + request);
		logger.info("googleSignInCallback response : " + response);
		logger.info("googleSignInCallback code : " + code);
		logger.info("googleSignInCallback scope : " + scope);
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code , googleOAuth.getRedirectUri(),
				null);
		
		logger.info("googleSignInCallback accessGrant : " + accessGrant);
		logger.info("googleSignInCallback accessGrant.getRefreshToken() : " + accessGrant.getRefreshToken());
		
		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		
		logger.info("googleSignInCallback accessToken : " + accessToken);
		logger.info("googleSignInCallback exprireTime : " + expireTime);
		logger.info("googleSignInCallback System : " + System.currentTimeMillis());
		
		
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
			logger.info("accessToken is expired. refresh token = {}", accessToken);
		}
		logger.info("googleSignInCallback accessToken : " + accessToken);
	
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		logger.info("googleSignInCallback connection : " + connection);
		
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		logger.info("googleSignInCallback google : " + google);
		
		PlusOperations plusOperations = google.plusOperations();
		logger.info("googleSignInCallback plusOperations : " + plusOperations);
		
		Person person = plusOperations.getGoogleProfile();
		logger.info("person" + person);
		
		
		usersVO member = new usersVO();
		member.setUsername(person.getDisplayName());

		session.setAttribute("login", member);
		
		System.out.println(person.getDisplayName());
		
		return "redirect:/";
		/*System.out.println(person.getAccountEmail());
		System.out.println(person.getAboutMe());
		System.out.println(person.getDisplayName());
		System.out.println(person.getEtag());
		System.out.println(person.getFamilyName());
		System.out.println(person.getGender());
		*/
		
	}
	
}
