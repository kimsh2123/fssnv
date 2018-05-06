package org.mycom.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.mycom.service.Ag113Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
 * 2016.08.05 by sung
 * Ag113 RestController for Ajax
 */

@RestController
@RequestMapping("/ag113Rest")
public class Ag113RestController {
	
	private static final Logger logger = LoggerFactory.getLogger(Ag113RestController.class);
	
	@Inject
	private Ag113Service service;
	
	//popupModal 호출
	@RequestMapping(value = "/{num}/{make_date}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(
			@PathVariable("num") Integer num,
			@PathVariable("make_date") String make_date){
		
		logger.info("popupModal call : "+num+","+make_date);
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try{
			// Ajax로 호출되므로 Model을 사용하지 않고 Map 타입 사용
			Map<String, Object> map = new HashMap<String, Object>();
			
			//data 담기
			map.put("popupData", service.listOne(make_date, num));
			
			logger.info("DATA>>>"+map);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

}
