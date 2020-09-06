package com.jin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("Tag List : " + Tagservice.tagList() );
		model.addAttribute("tag",Tagservice.tagList());
		
	
		
		logger.info("post get... : " + postservice.postList());
		model.addAttribute("post", postservice.postList());
		
		
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "index";
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
	public void postjoin(usersVO vo) throws Exception {
		
		logger.info("PostJoin VO : " + vo);
		service.createUser(vo);
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void getlogin() {
		logger.info("로그인 페이지 이동");
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postlogin(usersVO vo, HttpServletRequest request) throws Exception {
		
		logger.info("PostLogin VO : " + vo);
		usersVO result = service.login(vo);
		logger.info("result 의 값 : " + result);
		
		HttpSession session = request.getSession();
		if(result != null) {
			session.setAttribute("login", result);
			logger.info("세신 값 : " +session.getAttribute("login") );
			return "redirect:/";
		}else {
			return "redirect:/blog/login";
		}
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
	
}
