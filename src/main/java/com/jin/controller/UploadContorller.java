package com.jin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



@Controller
public class UploadContorller {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject uploadImageFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{
		
		logger.info("uploadImageFile...........");
		logger.info("uploadImageFile..........." +multipartFile);
		
		Gson gson = new Gson();
		
		JSONObject jsonObject = new JSONObject();
		
		// 저장될 외부 파일 경로
		String fileRoot = "E:\\upload\\";
		
		// 오리지날 파일 이름
		String originaFileName = multipartFile.getOriginalFilename();
		
		// 파일 확장자
		String extension = originaFileName.substring(originaFileName.lastIndexOf("."));
		
		String savaFileName = UUID.randomUUID() + "_"+originaFileName; // 저장될 파일 명
		
		File targetFile = new File(fileRoot + savaFileName);
		
		logger.info("originaFileName :" +originaFileName);
		logger.info("extension :" +extension);
		logger.info("savaFileName : " +savaFileName);
		logger.info("targetFile : " +targetFile);
		
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			
			// 파일 저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile); 
			
			jsonObject.put("url", "/blog/summernoteImage/"+savaFileName);
			jsonObject.put("responseCode", "success");
			
		} catch (Exception e) {
			// TODO: handle exception
			FileUtils.deleteQuietly(targetFile);
			jsonObject.put("responseCode", "error");
			e.printStackTrace();
		}
		
		logger.info("jsonObject : " +jsonObject);
		
		return jsonObject;
		
	}
}
