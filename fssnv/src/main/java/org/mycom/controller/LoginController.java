package org.mycom.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mycom.domain.LoginDTO;
import org.mycom.domain.UserVO;
import org.mycom.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//login 처리 기본경로는 "/admin"
@Controller
@RequestMapping("/admin")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private LoginService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		logger.info("loginGET ...");
	}
	
	// '/admin/login' 결과 처리는 '/admin/loginPost' 로 설정
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		
		UserVO vo = service.login(dto);
		if(vo == null){	return;	}
		
		logger.info("loginPOST ..."+dto.toString()+"..."+vo.toString());
		
		model.addAttribute("userVO", vo);
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		logger.info("Log Out ...");
		
		session.removeAttribute("login");
		session.invalidate();
		
		return "/admin/login"; //logout 시 login page로 이동 16.08.10
	}
	
	// search password 16.08.10
	@RequestMapping(value = "/searchPwd", method = RequestMethod.GET)
	public @ResponseBody String searchPwd(
			@RequestParam(value="search_input", defaultValue="") String search_input) throws Exception {
		
		logger.info("searchPwd : "+search_input);
		
		return service.searchPwd(search_input);
	}
	

}//end of class
