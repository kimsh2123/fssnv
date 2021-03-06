package org.mycom.service;

import java.util.List;

import org.mycom.domain.Ag113VO;
import org.mycom.domain.PagingVO;

public interface Ag113Service {
	
	// 전체 리스트 가져오기
	public List<Ag113VO> listAll(String make_date) throws Exception;
	
	// Register 처리
	public void register(Ag113VO vo) throws Exception;
	
	// 상세 리스트 Select
	public Ag113VO listOne(String make_date, int num) throws Exception;
	
	// modify
	public void modify(Ag113VO vo) throws Exception;
	
	// delete one
	public void delOne(Ag113VO vo) throws Exception;
	
	// listAll -> paging add method
	public List<Ag113VO> listPaging(String make_date, PagingVO vo) throws Exception; 
	
	// select list total count, for paging
	public int totalCount(String make_date) throws Exception;
	
	// Delete All
	public int delAll(String make_date) throws Exception;
	
}
