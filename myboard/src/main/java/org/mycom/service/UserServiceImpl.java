package org.mycom.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.mycom.domain.UserVO;
import org.mycom.dto.LoginDTO;
import org.mycom.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;

	// login 정보 가져오기 2.1.3
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}
	
	// 4.2.2
	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		dao.keepLogin(uid, sessionId, next);
	}

	// 4.2.2
	@Override
	public UserVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}

}
