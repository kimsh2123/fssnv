package org.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.mycom.dao.LoginDAO;
import org.mycom.domain.LoginDTO;
import org.mycom.domain.MenuVO;
import org.mycom.domain.UserVO;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Inject
	private LoginDAO dao;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public List<MenuVO> menuList(String user_id) throws Exception {
		return dao.menuList(user_id);
	}

	@Override
	public String searchPwd(String input) throws Exception {
		return dao.searchPwd(input);
	}

}
