package com.jin.auth;

import java.util.Iterator;

import org.apache.commons.codec.binary.StringUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.jin.doamin.usersVO;

public class SNSLogin {
	
	private OAuth20Service oauthService;
	private String profileUrl;
	
	public SNSLogin(SnsValue sns) {
		this.oauthService = new ServiceBuilder(sns.getClientId())
				.apiSecret(sns.getClientSecret())
				.callback(sns.getRediretUrl())
				.scope("profile")
				.build(sns.getApi20Instance());
		this.profileUrl = sns.getProfileUrl();
	}

	public String getNaverAuthURL() {
	
		return this.oauthService.getAuthorizationUrl();
	}

	public usersVO getUserProfile(String code) throws Exception{
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		
		OAuthRequest request = new OAuthRequest(Verb.GET, this.profileUrl);
		oauthService.signRequest(accessToken, request);
		
		Response response = oauthService.execute(request);
		
		return parseJson(response.getBody());
	
	}

	private usersVO parseJson(String body) throws Exception {
		usersVO user = new usersVO();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(body);
		JsonNode responseNode = rootNode.get("response");
		
		System.out.println("body : " + body);
		System.out.println("body : " + rootNode);
		System.out.println("body : " + responseNode);
		
		user.setGender(responseNode.get("gender").asText());			
		user.setUserid(responseNode.get("email").asText());
		user.setUsername(responseNode.get("name").asText());
		
		System.out.println("userVO : " + user);
		return user;
	}
}
