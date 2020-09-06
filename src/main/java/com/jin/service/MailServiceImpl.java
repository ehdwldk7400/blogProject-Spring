package com.jin.service;


import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import com.jin.controller.HomeController;


@Service
public class MailServiceImpl implements MailService{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void estJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Override
	public boolean send(String subject, String text, String from, String to, String filePath) throws Exception {
		// TODO Auto-generated method stub
		
		MimeMessage message = javaMailSender.createMimeMessage();
		logger.info("subject : " + subject);
		logger.info("subject : " + text);
		logger.info("subject : " + from);
		logger.info("subject : " + to);
		logger.info("subject : " + filePath);
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject);
			helper.setText(text, true);
			helper.setFrom(from);
			helper.setTo(to);
			
			
			// 梅何 颇老 贸府
			if(filePath != null) {
				File file = new File(filePath);
				
				if(file.exists()) {
					helper.addAttachment(file.getName(), new File(filePath));
				}
			}
			
			javaMailSender.send(message);
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}

}
