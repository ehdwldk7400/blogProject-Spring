package com.jin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.doamin.postVO;
import com.jin.service.PostService;
import com.jin.service.TagService;

@Controller
@RequestMapping(value = "post")
public class PostController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TagService Tagservice;
	
	@Autowired
	PostService Postservice;
	
	@RequestMapping(value = "postWrite", method = RequestMethod.GET)
	public void getpostWrite(Model model) throws Exception {
		logger.info("글쓰기페이지 페이지 이동");
		logger.info("Tag List : " + Tagservice.tagList() );
		model.addAttribute("tag",Tagservice.tagList());
	}
	@RequestMapping(value = "postWrite", method = RequestMethod.POST)
	public String postPostWrite(postVO vo) throws Exception{
		
		logger.info("POST VO : " + vo);
		
		Postservice.wirtePost(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void getRead(postVO vo, Model model) throws Exception {
		logger.info("read get... " + vo);
		
		model.addAttribute("read", Postservice.read(vo));
	}
	
}
