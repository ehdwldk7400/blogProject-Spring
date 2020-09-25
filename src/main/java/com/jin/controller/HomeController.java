package com.jin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jin.auth.SNSLogin;
import com.jin.auth.SnsValue;
import com.jin.doamin.tagVO;
import com.jin.doamin.usersVO;
import com.jin.service.PostService;
import com.jin.service.TagService;
import com.jin.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	UserService service;
	
	@Autowired
	TagService Tagservice;
	
	@Autowired
	PostService postservice;

	private Object retrun;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Tag List : " + Tagservice.tagList() );
		model.addAttribute("tag",Tagservice.tagList());
		
	
		
//		logger.info("post get... : " + postservice.postList());
//		model.addAttribute("post", postservice.postList());
		
		
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
//	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Autowired
	private SnsValue naverSns;
	
	@Autowired
	private SnsValue googleSns;
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void getsignup(HttpSession session, Model model) {
		logger.info("signup get....");
		
//		// 구글 Code 발행
//		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
//		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth);	
//
//		model.addAttribute("google", url);
		
		SNSLogin snsLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", snsLogin.getNaverAuthURL());
		
		
//		/* 구글code 발행을 위한 URL 생성 */
//		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
//		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
//
//		model.addAttribute("google_url", url);

	}
	
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView getjoin() {
		logger.info("회원가입 페이지 이동");
		ModelAndView mv = new ModelAndView();
		int ran = new Random().nextInt(900000) + 100000;
		mv.setViewName("/join");
		mv.addObject("random", ran);
		
		
		logger.info("joning get.... " + mv);
		logger.info("ran.... " + ran);
		return mv;

	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String postjoin(usersVO vo) throws Exception {
		
		String hashedPw = BCrypt.hashpw(vo.getUserpw(), BCrypt.gensalt());
		vo.setUserpw(hashedPw);
	
		logger.info("PostJoin VO : " + vo);
		service.createUser(vo);
		return "redirect:/";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getlogin() {
		logger.info("로그인 페이지 이동");
	}
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void postlogin(usersVO vo, Model model) throws Exception {
		
		logger.info("PostLogin VO : " + vo);
	
		usersVO userVO = service.login(vo);
		logger.info("userVO 의 값 : " + userVO);
		
		if(userVO == null || !BCrypt.checkpw(vo.getUserpw(), userVO.getUserpw())) {
			return;
		}
//		
		model.addAttribute("userVO", userVO);
	}

	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public String posthome(tagVO vo) throws Exception {
		logger.info("tagVO : " + vo);
		Tagservice.createTag(vo);
		return "redirect:/";
	}
	 
	// 유저 아이디 체크
	@RequestMapping(value = "/idchk", method = RequestMethod.POST)
	public ResponseEntity<Integer> idchk(usersVO vo) throws Exception{
		
		logger.info("idchk : " + vo);
		
		return new ResponseEntity<Integer>(service.idchk(vo), HttpStatus.OK);
	}
	
	@RequestMapping(value="logout", method= RequestMethod.GET)
	public String logout(HttpSession session, HttpServletResponse response) throws Exception{
		
		session.invalidate();
		
		logger.info("logout response", response);
		
		return "redirect:/";
	}
	
}
