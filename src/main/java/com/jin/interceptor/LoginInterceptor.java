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
	
	//  �α��� ���� �� �����ϴ� ����
	private static final String LOGIN = "login";
	
	// ��Ʈ�ѷ� �� ��
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler,
							ModelAndView modelAndView) throws Exception{
		
	
		System.out.println("post handel....");
		
		// ���� ����
		HttpSession session = request.getSession();
		
		// ��Ʈ�ѷ����� ������ usersVO �� ��������
		Object userVO = modelAndView.getModel().get("userVO");
		System.out.println("userVO : "+userVO);
		
		// ��Ʈ�ѷ����� �޾ƿ� vo���� null�� �ƴ϶��
		if(userVO != null) {
			System.out.println("new login siccess");
			
			session.setMaxInactiveInterval(30*60);
			session.setAttribute(LOGIN, userVO);
			
			response.sendRedirect("/blog");
		}
	}
	
	// ��Ʈ�ѷ� ���� ��
	@Override
	public boolean preHandle(HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler) throws Exception{
			
			
			HttpSession httpSession = request.getSession();
			
			// ���� �α��� ���� ����
			if(httpSession.getAttribute(LOGIN) != null) {
				logger.info("clear login data before");
	            httpSession.removeAttribute(LOGIN);
			}
			
			return true;
		}
		
	
}
