package org.mycom.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * Login 처리
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler,	ModelAndView modelAndView) throws Exception {

		System.out.println("LoginInterceptor postHandle ... ");
		logger.info("LoginInterceptor postHandle...");

		HttpSession session = request.getSession();

		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		logger.info("LoginInterceptor Step 2..."+userVO.toString());

		if (userVO != null) {
			logger.info("new login success");
			session.setAttribute(LOGIN, userVO);
			
			response.sendRedirect("/fss"); //기본경로로 이동

			// 로그인 후 최초 요청 페이지로 이동
			//Object dest = session.getAttribute("dest");
			//response.sendRedirect(dest != null ? (String) dest : "/fss");
		}
	}

	// 기존 HttpSession에 정보가 남아있으면 삭제
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)	throws Exception {
		
		System.out.println("LoginInterceptor preHandle ... ");
		logger.info("CALL preHandle...");

		HttpSession session = request.getSession();

		if (session.getAttribute(LOGIN) != null) {
			logger.info("CLEAR login data before");
			session.removeAttribute(LOGIN);
		}

		return true;
	}

}
