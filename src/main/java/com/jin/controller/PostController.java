package com.jin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.postPageVO;
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
		
		return "redirect:read?bno="+vo.getBno();
	}
	
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void getRead(postVO vo, Model model) throws Exception {
		logger.info("read get... " + vo);
		
		model.addAttribute("read", Postservice.read(vo));
	}
	
	@RequestMapping(value = "postUpdate", method = RequestMethod.GET)
	public void getUpdatePost(postVO vo, Model model ) throws Exception {
		model.addAttribute("read", Postservice.read(vo));
		model.addAttribute("tag", Tagservice.tagList());
		logger.info("update get..." + vo);
		
	}
	@RequestMapping(value = "postUpdate", method = RequestMethod.POST)
	public String postUpdatePost(postVO vo) throws Exception {
		
		logger.info("update post..." + vo);
		Postservice.updatePost(vo);
		
		return "redirect:read?bno="+vo.getBno();
	}
	
	@RequestMapping(value = "postDelete", method = RequestMethod.POST)
	public String postDeletePost(postVO vo) throws Exception{
		
		logger.info("postDelete VO : " + vo);
		Postservice.deletePost(vo);
		
		return "redirect:/";
	}
	@RequestMapping(value = "AllList", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<postVO>> getAllList() throws Exception{
		
		logger.info("ALlList get......");
		
		logger.info("postlist : " + Postservice.postList());
		
		return new ResponseEntity<List<postVO>>(Postservice.postList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "tagList", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<postVO>> getTagList(postVO vo) throws Exception{
		
		
		logger.info("getTagList.........");
		logger.info("getTagList vo : "+ vo);
		
		return new ResponseEntity<List<postVO>>(Postservice.tagList(vo), HttpStatus.OK);
	}
	
	
	// 메인화면 페이징
	@RequestMapping(value = "PagingList", method = RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<postVO>> getPagingList(int pageNum, int amount) throws Exception{
		
		logger.info("getPaging......");
		logger.info("getPaging pageNum : " + pageNum);
		logger.info("getPaging amount : " + amount);
		
		
		return new ResponseEntity<List<postVO>>(Postservice.postPaging(pageNum, amount), HttpStatus.OK);
	}
	
	// 테그 별 페이징
	@RequestMapping(value = "/{tagname}/{page}", method = RequestMethod.GET)
	public ResponseEntity<postPageVO> tagPaginglist(@PathVariable("tagname") String tagname, @PathVariable("page") int page) throws Exception{
	
		logger.info("tagPaginglist...... " );
		
		Criteria cri = new Criteria(page, 10);
		logger.info("list bno, page : " + tagname +", " + page );
		logger.info("list cri : " + cri );
		logger.info("list listpage : " + Postservice.tagPaging(cri, tagname));
		return new ResponseEntity<postPageVO>(Postservice.tagPaging(cri, tagname), HttpStatus.OK);
	}
}
