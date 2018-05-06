package org.mycom.controller;

import java.util.Locale;
import javax.inject.Inject;

import org.mycom.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private LoginService l_service;
	
	// START PAGE CALL
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		String user_id = "fssadmin"; //FOR TEST ~~~~~~~~~~~~~
		
		logger.info("home CALL>>>"+user_id);
		
		model.addAttribute("menuList", l_service.menuList(user_id));
		
		return "startPage";
	}
	
	// 왼쪽 메뉴 화면 for Test 메인 페이지 호출
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String mainPage(Locale locale, Model model) {
		logger.info("mainPage CALL");
		return "mainPage";
	}
	
}
