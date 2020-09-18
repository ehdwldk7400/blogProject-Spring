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
	
	// ��/��/�� ���� ���� �޼���
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM-dd");
		Date date = new Date();
		
		String str = sdf.format(date);
		
		logger.info("���� ��¥ ���"+ str);
		
		return str.replace("-", File.separator);
	}
	
	
	// Image Upload �� �̹��� �����ϴ� �޼���
	@RequestMapping(value = "/upload", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject uploadImageFile(@RequestParam("file") MultipartFile multipartFile) throws Exception{
		
		logger.info("uploadImageFile...........");
		logger.info("uploadImageFile..........." +multipartFile);
		
		// ����� �ܺ� ���� ���
		String fileRoot = "E:\\upload\\";
		
		File uploadFolder = new File(fileRoot, getFolder());
		logger.info("uploadFolder : " +uploadFolder);
		
		// ���� ������ ���� ���� 
		// �ش� ��ΰ� ���ٸ� ��ο� �ش��ϴ� �������� �����Ѵ�.
		// ex) 202011\\09
		if(uploadFolder.exists() == false) {
			logger.info("uploadFolder : " +uploadFolder.exists());
			uploadFolder.mkdirs();
		}
		
		JSONObject jsonObject = new JSONObject();
		
		
		// �������� ���� �̸�
		String originaFileName = multipartFile.getOriginalFilename();
		
		// ���� Ȯ����
		String extension = originaFileName.substring(originaFileName.lastIndexOf("."));
		
		// UUID ������ ���ÿ� ���� ��� �����Ͽ� �ʱ�ȭ
		String savaFileName = UUID.randomUUID() + "_"+originaFileName; // ����� ���� ��
		
		// ���� ��θ� ���� ���� ���� ��ġ ����
		File targetFile = new File(uploadFolder + "\\" + savaFileName);
		
		// ������ �� �ѷ��� url�ּ� ����
		String filePath = getFolder() + "\\" +savaFileName;
		
		logger.info("originaFileName :" +originaFileName);
		logger.info("extension :" +extension);
		logger.info("savaFileName : " +savaFileName);
		logger.info("targetFile : " +targetFile);
		logger.info("targetFile : " + filePath);
		
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			
			// ���� ����
			FileUtils.copyInputStreamToFile(fileStream, targetFile); 
			
			// ���� �� URL �ּ� ���� �� responseCode ����
			// Json Ÿ������ ������
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
