package org.mycom.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.mycom.domain.MessageVO;
import org.mycom.dao.MessageDAO;
import org.mycom.dao.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO messageDAO;

	@Inject
	private PointDAO pointDAO;

	// @Transactional for 트랜잭션 테스트
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {

		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {

		// 메시지의 상태를 변경
		messageDAO.updateState(mid);

		// 메시지를 본 사람의 포인트 5점 증가
		pointDAO.updatePoint(uid, 5);

		// 모든 작업 후 메시지를 조회해서 반환
		return messageDAO.readMessage(mid);
	}
}
