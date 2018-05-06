package org.mycom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mycom.domain.Ag113VO;
import org.mycom.domain.PagingVO;
import org.springframework.stereotype.Repository;

@Repository
public class Ag113DAOImpl implements Ag113DAO {
	
	@Inject
	private SqlSession session;

	private static String namespace = "org.mycom.mapper.Ag113Mapper";

	@Override
	public List<Ag113VO> listAll(String make_date) throws Exception {
		return session.selectList(namespace + ".listAll", make_date);
	}

	@Override
	public int register(Ag113VO vo) throws Exception {
		return session.insert(namespace + ".register", vo);
	}

	@Override
	public Ag113VO listOne(String make_date, int num) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("make_date", make_date);
		paramMap.put("num", num);
		
		return session.selectOne(namespace + ".listOne", paramMap);
	}

	@Override
	public int update(Ag113VO vo) throws Exception {
		return session.update(namespace + ".update", vo);
	}

	@Override
	public int delOne(Ag113VO vo) throws Exception {
		return session.delete(namespace + ".delOne", vo);
	}

	@Override
	public List<Ag113VO> listPaging(String make_date, PagingVO vo) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("make_date", make_date);
		paramMap.put("pageStrat", vo.getPageStart());
		paramMap.put("perPageNum", vo.getPerPageNum());
		
		return session.selectList(namespace + ".listPaging", paramMap);
	}

	@Override
	public int totalCount(String make_date) throws Exception {
		return session.selectOne(namespace + ".totalCount", make_date);
	}

	@Override
	public int delAll(String make_date) throws Exception {
		return session.delete(namespace + ".delAll", make_date);
	}

	@Override
	public List<Ag113VO> allColSelect(String make_date) throws Exception {
		return session.selectList(namespace + ".allColSelect", make_date);
	}

}
