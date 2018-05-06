package org.mycom.dao;

import java.util.List;

import org.mycom.domain.BoardVO;
import org.mycom.domain.Criteria;
import org.mycom.domain.SearchCriteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void delete(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listPage(int page) throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;

	// use for dynamic sql
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;

	public int listSearchCount(SearchCriteria cri) throws Exception;

	public void updateReplyCnt(Integer bno, int amount) throws Exception;

	public void updateViewCnt(Integer bno) throws Exception;

	// 게시물 파일 첨부 등록
	public void addAttach(String fullName) throws Exception;

	// 게시물 첨부 파일 목록 가져오기
	public List<String> getAttach(Integer bno) throws Exception;

	public void deleteAttach(Integer bno) throws Exception;

	public void replaceAttach(String fullName, Integer bno) throws Exception;

}
