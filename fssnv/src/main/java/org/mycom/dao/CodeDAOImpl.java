package org.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mycom.domain.CodeVO;
import org.springframework.stereotype.Repository;

@Repository
public class CodeDAOImpl implements CodeDAO {
	
	@Inject
	private SqlSession session;

	private static String namespace = "org.mycom.mapper.CodeMapper";

	@Override
	public List<CodeVO> codelist(String subject, int types) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("subject", subject);
		paramMap.put("types", types);
		
		return session.selectList(namespace + ".codelist", paramMap);
	}

}
