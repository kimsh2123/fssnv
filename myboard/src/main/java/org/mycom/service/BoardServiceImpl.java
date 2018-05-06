package org.mycom.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.mycom.domain.BoardVO;
import org.mycom.domain.Criteria;
import org.mycom.domain.SearchCriteria;
import org.mycom.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	// for ex03
	/* 
	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}
	*/

	// for 6.2.4 첨부파일 처리 추가
	@Transactional
	@Override
	public void regist(BoardVO board) throws Exception {

		dao.create(board);

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.addAttach(fileName);
		}
	}

	// 게시물 조회 수 증가 추가
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}
	  
	/*
	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}
	*/

	//for ex03
	/*
	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}
	*/

	// 8.1.1 수정 작업에 트랜잭션 추가
	@Transactional
	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);

		Integer bno = board.getBno();

		dao.deleteAttach(bno);

		String[] files = board.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			dao.replaceAttach(fileName, bno);
		}
	}

	//for ex03
	/*
	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}
	*/
	
	// 8.1.3.2 첨부파일 정보도 삭제
	@Transactional
	@Override
	public void remove(Integer bno) throws Exception {
		dao.deleteAttach(bno);
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {

		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {

		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri)	throws Exception {

		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {

		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {

		return dao.getAttach(bno);
	}   
	  
}
