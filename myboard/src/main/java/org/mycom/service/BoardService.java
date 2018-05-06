package org.mycom.service;

import java.util.List;

import org.mycom.domain.BoardVO;
import org.mycom.domain.Criteria;
import org.mycom.domain.SearchCriteria;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public void remove(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;

	// search 입력 처리
	public List<BoardVO> listSearchCriteria(SearchCriteria cri)	throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public List<String> getAttach(Integer bno)throws Exception;
}
