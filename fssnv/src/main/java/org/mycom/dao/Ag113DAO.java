package org.mycom.dao;

import java.util.List;

import org.mycom.domain.Ag113VO;
import org.mycom.domain.PagingVO;

public interface Ag113DAO {
	
	// 전체 리스트 가져오기
	public List<Ag113VO> listAll(String make_date) throws Exception;
	
	// Register 처리
	public int register(Ag113VO vo) throws Exception;
	
	// 상세 리스트 Select
	public Ag113VO listOne(String make_date, int num) throws Exception;
	
	// Modify
	public int update(Ag113VO vo) throws Exception;
	
	// delete one
	public int delOne(Ag113VO vo) throws Exception;
	
	// listAll -> paging add method, 건별로 데이터 가져오기
	public List<Ag113VO> listPaging(String make_date, PagingVO vo) throws Exception;
	
	// select list total count, for paging
	public int totalCount(String make_date) throws Exception;
	
	// Delete All
	public int delAll(String make_date) throws Exception;
	
	// All column select 16.4.29
	public List<Ag113VO> allColSelect(String make_date) throws Exception;

}
