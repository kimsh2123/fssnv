package org.mycom.service;

import java.util.Date;

import org.mycom.domain.UserVO;
import org.mycom.dto.LoginDTO;

public interface UserService {

	// login 정보 가져오기 2.1.3
	public UserVO login(LoginDTO dto) throws Exception;
	
	// 4.2.2
	public void keepLogin(String uid, String sessionId, Date next) throws Exception;

	// 4.2.2
	public UserVO checkLoginBefore(String value);

}
