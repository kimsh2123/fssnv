package org.mycom.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import org.mycom.domain.UserVO;
import org.mycom.dto.LoginDTO;
import org.mycom.service.UserService;

// login 처리 기본경로는 "/user"
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Inject
	private UserService service;
	
	// 2.2.1
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	// 2.2.1 '/user/login' 결과 처리는 '/user/loginPost' 로 설정
	/*
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		UserVO vo = service.login(dto);
		if(vo == null){	return;	}
		model.addAttribute("userVO", vo);
	}
	*/

	// 4.2.3 자동 로그인 기능 추가
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {

		UserVO vo = service.login(dto);
		if (vo == null) {return;}

		model.addAttribute("userVO", vo);

		if (dto.isUseCookie()) {

			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis()	+ (1000 * amount));

			//loginCookie 값이 유지되는 시간 정로를 DB에 저장하는 것이 핵심
			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		}

	}
	
	// 4.3 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {

		Object obj = session.getAttribute("login");

		if (obj != null) {
			UserVO vo = (UserVO) obj;

			session.removeAttribute("login");
			session.invalidate();

			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

			// 정보 모두 초기화 해줌
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
			}
		}
	}
	  
}
