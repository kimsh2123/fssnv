package org.mycom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.mycom.domain.Criteria;
import org.mycom.domain.PageMaker;
import org.mycom.domain.ReplyVO;
import org.mycom.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;

	// 댓글 등록 처리
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {

		ResponseEntity<String> entity = null;
		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 발생 시 400 에러 전송
			entity = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 전체 댓글 리스트 처리 bno 활용
	@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {

		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			// 성공하면 OK 헤더 전송
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	@RequestMapping(value = "/{rno}", method = { RequestMethod.PUT,	RequestMethod.PATCH })
	public ResponseEntity<String> update(@PathVariable("rno") Integer rno,
			@RequestBody ReplyVO vo) {

		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			service.modifyReply(vo);

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),	HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(
			@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) {

		ResponseEntity<Map<String, Object>> entity = null;

		try {
			Criteria cri = new Criteria();
			cri.setPage(page);

			// PageMaker 페이징에서 공통으로 사용 가능
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			// Ajax로 호출되므로 Model을 사용하지 않고 Map 타입 사용
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReplyVO> list = service.listReplyPage(bno, cri);

			// 댓글 목록 담기
			map.put("list", list);

			int replyCount = service.count(bno);
			pageMaker.setTotalCount(replyCount);

			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
