package com.jin.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//  로그인 세션 명 저장하는 변수
	private static final String LOGIN = "login";
	
	// 컨트롤러 들어간 후
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception{
		
	
		System.out.println("post handel....");
		
		// 세션 생성
		HttpSession session = request.getSession();
		
		// 컨트롤러에서 보내온 usersVO 값 가져오기
		Object userVO = modelAndView.getModel().get("userVO");
		System.out.println("userVO : "+userVO);
		
		// 컨트롤러에서 받아온 vo값이 null이 아니라면
		if(userVO != null) {
			System.out.println("new login siccess");
			
			session.setMaxInactiveInterval(30*60);
			session.setAttribute(LOGIN, userVO);
			
			response.sendRedirect("/blog");
		}
	}
	
	// 컨트롤러 들어가기 전
	@Override
	public boolean preHandle(HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler) throws Exception{
			
			
			HttpSession httpSession = request.getSession();
			
			// 기존 로그인 정보 제거
			if(httpSession.getAttribute(LOGIN) != null) {
				logger.info("clear login data before");
	            httpSession.removeAttribute(LOGIN);
			}
			
			return true;
		}
		
	
}
