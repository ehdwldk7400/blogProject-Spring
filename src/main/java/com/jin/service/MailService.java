package com.jin.service;

public interface MailService {
	
	public boolean send(String subject, String text, String from, String to, String filePath) throws Exception;

}



