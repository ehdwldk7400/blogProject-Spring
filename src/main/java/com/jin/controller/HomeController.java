package com.jin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.doamin.tagVO;
import com.jin.doamin.usersVO;
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
		
		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void getjoin() {
		logger.info("회원가입 페이지 이동");
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
	
	
}
