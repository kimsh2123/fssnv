package org.mycom.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mycom.domain.LoginDTO;
import org.mycom.domain.MenuVO;
import org.mycom.domain.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.mycom.mapper.UserMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public List<MenuVO> menuList(String user_id) throws Exception {
		return session.selectList(namespace + ".menulist", user_id);
	}

	@Override
	public String searchPwd(String input) throws Exception {
		return session.selectOne(namespace + ".searchPwd", input);
	}

}
