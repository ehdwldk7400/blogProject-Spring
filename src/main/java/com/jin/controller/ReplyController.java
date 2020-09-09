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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jin.doamin.Criteria;
import com.jin.doamin.ReplyPageVO;
import com.jin.doamin.ReplyVO;
import com.jin.service.ReplyService;
import com.sun.org.glassfish.gmbal.ParameterNames;

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
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<ReplyPageVO> list(@PathVariable("bno") int bno, @PathVariable("page") int page) throws Exception{
		
		logger.info("list bno, page : " + bno +", " + page );
		Criteria cri = new Criteria(page, 10);
		logger.info("list cri : " + cri );
		logger.info("list listpage : " + Reservice.listPage(bno, cri) );
		
		
		return new ResponseEntity<ReplyPageVO>(Reservice.listPage(bno, cri), HttpStatus.OK);
	}
	
	@RequestMapping(value = "ReplyCnt/{bno}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getReplyCnt(@PathVariable("bno") int bno) throws Exception{
		
		return new ResponseEntity<Integer>(Reservice.replycnt(bno), HttpStatus.OK);
	}
	
	@RequestMapping(value = "ReplyCreate", method = RequestMethod.POST)
	public ModelAndView getCreate(ReplyVO vo, ModelAndView mav)throws Exception {
		
		mav = new ModelAndView();
		
		logger.info("ReplyCreate POST.....");
		logger.info("ReplyCreate vo : " + vo);
		
		Reservice.rePlycreate(vo);
		
		mav.setViewName("redirect:/post/read?bno="+vo.getBno());
		logger.info("ReplyCreate mav : " + mav);
		
		return mav;
	}
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno) throws Exception {
		ResponseEntity<String> result = null;
		
		logger.info("DELETE.....");
		logger.info("ReplyDelete rno : " + rno);
		
		try {
			Reservice.delete(rno);
			result = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return result;
	}
	@RequestMapping(value="/{rno}", method=RequestMethod.PUT)
	public ResponseEntity<String> replyupdate(@PathVariable("rno") int rno, @RequestBody ReplyVO vo) throws Exception{
		
		ResponseEntity<String> result = null;
		
		try {
			vo.setRno(rno);
			logger.info("Reply Update : "+ vo);
			Reservice.update(vo);
			result = new ResponseEntity<String>("success",HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			result = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return result;
	}
}
