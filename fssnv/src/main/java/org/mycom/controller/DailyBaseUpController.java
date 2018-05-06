package org.mycom.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.mycom.domain.MenuVO;
import org.mycom.service.BaseFileService;
import org.mycom.service.base.DailyBaseUpService;
import org.mycom.util.FssENV;
import org.mycom.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * 2016.04 Daily Base File Upload Controller
 */

@Controller
@RequestMapping("/fileupdw/*")
public class DailyBaseUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBaseUpController.class);

	@Inject
	private BaseFileService service;
	
	@Inject
	private DailyBaseUpService dbuservice;
	
	
	// Daily Base file upload 화면 Call 
	@RequestMapping(value = "/dailyBaseUpload", method = RequestMethod.GET)
	public void dailyBaseUpGET(Model model
			, @RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		if(make_date.equals("")){
			make_date = Utils.today(1);
		}
		
		logger.info("DailyBaseUpController dailyBaseUpGET ...>"+make_date);
		
		// 일보고 Upload 대상 가져오기 
		// ('D' daily or 'M' monthly), (1=down, 2=upload)
		List<MenuVO> list = service.selectSubject("D", 2);
		
		// DB에 존재하는 건수 가져오기
		HashMap<String, Integer> hash_cnt = service.getExistsData(list, make_date);
		
		// Base File Count
		HashMap<String, String> basefilecnt = service.readDailyFileCount(make_date);
		
		model.addAttribute("make_date", make_date);
		model.addAttribute("menulist", list);
		model.addAttribute("hash_cnt", hash_cnt);
		model.addAttribute("basefilecnt", basefilecnt);
	}
	
	
	// Daily Base file upload Process
	@RequestMapping(value = "/dailyBaseUpload", method = RequestMethod.POST)
	public String dailyBaseUpPOST(RedirectAttributes rttr
			, @RequestParam(value="make_date", defaultValue="") String make_date
			, @RequestParam(value="user_id", defaultValue="") String up_id
			, @RequestParam(value="filename", defaultValue="") String filename) throws Exception{
		
		logger.info("DailyBaseUpController dailyBaseUpPOST ...>"+make_date+","+up_id+","+filename);
		
		if(filename.length() > 0){
			filename = FssENV.BaseFileDir+"/"+filename;
			
			// file upload 처리
			int rtn = dbuservice.readFile(filename, up_id);
			
			logger.info("DailyBaseUpController dailyBaseUpPOST RESULT>"+rtn);
		}
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		// GO TO dailyBaseUpGET
		return "redirect:/fileupdw/dailyBaseUpload?make_date="+make_date;
	}
	
}
