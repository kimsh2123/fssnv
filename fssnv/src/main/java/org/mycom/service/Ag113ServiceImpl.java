package org.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.mycom.dao.Ag113DAO;
import org.mycom.domain.Ag113VO;
import org.mycom.domain.PagingVO;
import org.springframework.stereotype.Service;

@Service
public class Ag113ServiceImpl implements Ag113Service{
	
	@Inject
	private Ag113DAO dao;

	@Override
	public List<Ag113VO> listAll(String trade_date) throws Exception {
		return dao.listAll(trade_date);
	}

	@Override
	public void register(Ag113VO vo) throws Exception {
		dao.register(vo);
	}

	@Override
	public Ag113VO listOne(String make_date, int num) throws Exception {
		return dao.listOne(make_date, num);
	}

	@Override
	public void modify(Ag113VO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delOne(Ag113VO vo) throws Exception {
		dao.delOne(vo);
	}

	@Override
	public List<Ag113VO> listPaging(String make_date, PagingVO vo) throws Exception {
		return dao.listPaging(make_date, vo);
	}

	@Override
	public int totalCount(String make_date) throws Exception {
		return dao.totalCount(make_date);
	}

	@Override
	public int delAll(String make_date) throws Exception {
		return dao.delAll(make_date);
	}

}
