package org.mycom.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import org.mycom.domain.UserVO;
import org.mycom.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	// 4.2.4
	@Inject
	private UserService service;
	
	// 2.4.2 최초 사용자가 원하는 URI 저장
	private void saveDest(HttpServletRequest req) {

		String uri = req.getRequestURI();
		String query = req.getQueryString();

		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}

		// GET 방식일 경우 URI 뒤에 붙임
		if (req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			req.getSession().setAttribute("dest", uri + query);
		}
	}
	
	// 2.4 로그인 상태 체크
	/*
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null){
			logger.info("current user is not logined");
			
			saveDest(request); //2.4.2 추가
			
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	*/
	
	// 4.2.4 자동 로그인 기능 추가
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("login") == null) {
			logger.info("current user is not logined");
			saveDest(request);

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			//적당한 값이 없는 경우 loginCookie를 이용 사용자 정보를 찾음
			if (loginCookie != null) {

				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				logger.info("USERVO: " + userVO);

				if (userVO != null) {
					session.setAttribute("login", userVO);
					return true;
				}
			}
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	  
}
