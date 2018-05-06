package org.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mycom.domain.MenuVO;
import org.springframework.stereotype.Repository;

@Repository
public class BaseFileDAOImpl implements BaseFileDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.mycom.mapper.BaseFileMapper";

	@Override
	public List<MenuVO> selectSubject(String menu_type, int flag) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menu_type", menu_type);
		paramMap.put("flag", flag);
		
		return session.selectList(namespace + ".selectSubject", paramMap);
	}

	@Override
	public int getExistsData(String menu_id, String make_date) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("make_date", make_date);
		paramMap.put("menu_id", menu_id);
		
		return session.selectOne(namespace + ".selectCount", paramMap);
	}

}
