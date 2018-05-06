package org.mycom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.mycom.controller.CommonExceptionAdvice;

/*
 * 예외 처리 @ControllerAdvice 이용 방법
 * 1. 클래스에 @ControllerAdvice 어노테이션 선언
 * 2. 각 메소드에 @ExceptionHandler를 이용해서 적절한 타입의 Exception 처리
 */

@ControllerAdvice
public class CommonExceptionAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	// @ExceptionHandler(Exception.class)
	public String common(Exception e) {

		logger.info(e.toString());

		return "error_common";
	}

	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception ex) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error_common");
		modelAndView.addObject("exception", ex);

		// Error 페이지로 값 전달해준다 WEB-INF/views/error_common.jsp
		return modelAndView;
	}

}
