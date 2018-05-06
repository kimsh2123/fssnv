package org.mycom.service;

import java.util.List;

import org.mycom.domain.LoginDTO;
import org.mycom.domain.MenuVO;
import org.mycom.domain.UserVO;

public interface LoginService {

	// user login 작업
	public UserVO login(LoginDTO dto) throws Exception;
	
	// 최초 메뉴 리스트 가져오기
	public List<MenuVO> menuList(String user_id) throws Exception;
	
	// password search 16.08.09
	public String searchPwd(String input) throws Exception;
}
