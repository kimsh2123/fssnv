package org.mycom.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.mycom.domain.UserVO;
import org.mycom.dto.LoginDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "org.mycom.mapper.UserMapper";

	// 사용자 정보 조회 2.1.2
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}
	
	// 4.2.2.1 로그인 사용자 정보 update
	@Override
	public void keepLogin(String uid, String sessionId, Date next) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);

		session.update(namespace + ".keepLogin", paramMap);
	}

	// 4.2.2.1 쿠키 정보로 사용자 조회 
	@Override
	public UserVO checkUserWithSessionKey(String value) {
		return session.selectOne(namespace + ".checkUserWithSessionKey", value);
	}

}
