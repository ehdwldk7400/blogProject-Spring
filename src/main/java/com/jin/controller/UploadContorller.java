package com.jin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	// 년/월/일 폴더 생성 메서드
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		
		logger.info("오늘 날짜 경로"+ str);
		
		return str.replace("-", File.separator);
	}
	
	
	// Image Upload 후 이미지 저장하는 메서드
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject uploadImageFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{
		
		logger.info("uploadImageFile...........");
		logger.info("uploadImageFile..........." +multipartFile);
		
		// 저장될 외부 파일 경로
		String fileRoot = "E:\\upload\\";
		
		File uploadFolder = new File(fileRoot, getFolder());
		logger.info("uploadFolder : " +uploadFolder);
		
		// 파일 저장할 폴더 생성 
		// 해당 경로가 없다면 경로에 해당하는 폴더들을 생성한다.
		// ex) 202011\\09
		if(uploadFolder.exists() == false) {
			logger.info("uploadFolder : " +uploadFolder.exists());
			uploadFolder.mkdirs();
		}
		
		JSONObject jsonObject = new JSONObject();
		
		
		// 오리지날 파일 이름
		String originaFileName = multipartFile.getOriginalFilename();
		
		// 파일 확장자
		String extension = originaFileName.substring(originaFileName.lastIndexOf("."));
		
		// UUID 생성과 동시에 파일 명과 통합하여 초기화
		String savaFileName = UUID.randomUUID() + "_"+originaFileName; // 저장될 파일 명
		
		// 파일 경로를 합쳐 파일 저장 위치 지정
		File targetFile = new File(uploadFolder + "\\" + savaFileName);
		
		// 에디터 상에 뿌려줄 url주소 지정
		String filePath = getFolder() + "\\" +savaFileName;
		
		logger.info("originaFileName :" +originaFileName);
		logger.info("extension :" +extension);
		logger.info("savaFileName : " +savaFileName);
		logger.info("targetFile : " +targetFile);
		logger.info("targetFile : " + filePath);
		
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			
			// 파일 저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile); 
			
			// 리턴 할 URL 주소 셋팅 및 responseCode 세팅
			// Json 타입으로 리턴함
			jsonObject.put("url", "/blog/summernoteImage/"+filePath);
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
