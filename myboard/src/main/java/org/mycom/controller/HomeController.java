package org.mycom.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		/*
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
		*/
		
		// 게시판 리스트 화면으로 바로 넘김
		//return "redirect:/sboard/list";
		return "home";
	}
	
	// main menu call
	@RequestMapping(value = "/main_menu", method = RequestMethod.GET)
	public String mainMenuCall(Locale locale, Model model) {
		return "main_frame";
	}
	
	// menu call
	@RequestMapping(value = "/menu_left", method = RequestMethod.GET)
	public String menuCall(Locale locale, Model model) {
		return "include/menu_left";
	}
	
	// right page call
	@RequestMapping(value = "/menu_right", method = RequestMethod.GET)
	public String menuRightCall(Locale locale, Model model) {
		return "include/menu_right";
	}
	
	
}
