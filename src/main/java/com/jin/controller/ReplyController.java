package com.jin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jin.doamin.ReplyVO;
import com.jin.service.ReplyService;

@RestController
@RequestMapping(value="reply")
public class ReplyController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReplyService Reservice;
	
	
	
	// ´ñ±Û ¸®½ºÆ®( ÆäÀÌÂ¡ Àü)
	@RequestMapping(value = "ReplyList/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> getReplyList(@PathVariable("bno") int bno, ReplyVO vo) throws Exception{

		logger.info("ReplyList get.....");
		logger.info("ReplyList vo : " + vo);
		logger.info("ReplyList bno : "+ bno);
		logger.info("ReplyList list : "+Reservice.list(bno));	
		
		return new ResponseEntity<List<ReplyVO>>(Reservice.list(bno), HttpStatus.OK);
	}
	
	@RequestMapping(value = "ReplyCnt/{bno}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getReplyCnt(@PathVariable("bno") int bno) throws Exception{
		
		return new ResponseEntity<Integer>(Reservice.replycnt(bno), HttpStatus.OK);
	}
}
