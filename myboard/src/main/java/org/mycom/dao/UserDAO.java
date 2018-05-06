package org.mycom.dao;

import java.util.Date;

import org.mycom.domain.UserVO;
import org.mycom.dto.LoginDTO;

public interface UserDAO {
	
	// 사용자 정보 조회 2.1.2
	public UserVO login(LoginDTO dto) throws Exception;
	
	// 4.2.2.1 로그인 사용자 정보 update
	public void keepLogin(String uid, String sessionId, Date next);

	// 4.2.2.1 쿠키 정보로 사용자 조회 
	public UserVO checkUserWithSessionKey(String value);

}
