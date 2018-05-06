package org.mycom.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 2016.02.06 for part6
 */
public class SampleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("post handle.........");
		
		// add 1.4.2 -----------------------------------
		Object result = modelAndView.getModel().get("result");

		if (result != null) {
			// result를 보관 후 doA를 호출
			System.out.println("result exists");
			request.getSession().setAttribute("result", result);
			response.sendRedirect("/doA");
		}
		// add 1.4.2 -----------------------------------

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		System.out.println("pre handle..........");
		
		// add 1.4.1 -----------------------------------
	    HandlerMethod method = (HandlerMethod) handler;
	    Method methodObj = method.getMethod();

	    System.out.println("Bean: " + method.getBean());
	    System.out.println("Method: " + methodObj);
		// add 1.4.1 -----------------------------------	    

		return true;
	}

}
