package org.mycom.dao;

import java.util.List;

import org.mycom.domain.Criteria;
import org.mycom.domain.ReplyVO;

public interface ReplyDAO {

	public List<ReplyVO> list(Integer bno) throws Exception;

	public void create(ReplyVO vo) throws Exception;

	public void update(ReplyVO vo) throws Exception;

	public void delete(Integer rno) throws Exception;

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

	public int count(Integer bno) throws Exception;
	
	// 댓글 삭제 시 해당 게시물번호 가져옴
	public int getBno(Integer rno) throws Exception;

}
