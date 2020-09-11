package com.jin.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	
	// 컨트롤러 들어가기 전
	@Override
	public boolean preHandle(HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler) throws Exception{
		
		System.out.println("AuthInterceptor get....");
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute("login") == null) {
			System.out.println("current user is not logged");
			
			response.sendRedirect("/blog/login");
			return false;
		}
		
		return true;
		
	}

}
