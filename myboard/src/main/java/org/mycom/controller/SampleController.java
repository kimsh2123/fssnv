package org.mycom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.mycom.domain.SampleVO;

/*
 * @RestController 선언은 이것이 뷰 처리가 JSP 가 아니라는 것
 */

@RestController
@RequestMapping("/sample")
public class SampleController {

	// 일반 문자열 처리
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World ";
	}

	// JSON 처리
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {

		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);

		return vo;
	}

	// List 형식 처리
	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {

		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		return list;
	}

	// Map 형식 처리
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {

		Map<Integer, SampleVO> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}

	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {

		// 400 메시지 출력
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot() {

		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}

		// 404 메시지 출력 & list 데이터
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
}
