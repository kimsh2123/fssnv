package org.mycom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mycom.domain.MenuVO;
import org.mycom.service.BaseFileService;
import org.mycom.service.base.DailyBaseDownService;
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
public class DailyBaseDownController {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBaseDownController.class);
	
	@Inject
	private BaseFileService service;
	
	@Inject
	private DailyBaseDownService dbdservice;
	
	
	// Daily Base file download 화면 Call 
	@RequestMapping(value = "/dailyBaseDown", method = RequestMethod.GET)
	public void dailyBaseDownGET(Model model
			, @RequestParam(value="make_date", defaultValue="") String make_date) throws Exception{
		
		if(make_date.equals("")){
			make_date = Utils.today(1);
		}
		
		logger.info("dailyBaseDownGET ...>"+make_date);
		
		// 일보고 Download 대상 가져오기
		// ('D' daily or 'M' monthly), (1=down, 2=upload)
		List<MenuVO> list = service.selectSubject("D", 1);
		
		// DB에 존재하는 건수 가져오기
		HashMap<String, Integer> hash_cnt = service.getExistsData(list, make_date);
		
		// Zip File Search 16.05.03
		String zip_file = dbdservice.searchZipFile(make_date);
		
		model.addAttribute("make_date", make_date);
		model.addAttribute("zip_file", zip_file);
		model.addAttribute("menulist", list);
		model.addAttribute("hash_cnt", hash_cnt);
	}
	
	// Daily Base file download Process
	@RequestMapping(value = "/dailyBaseDown", method = RequestMethod.POST)
	public String dailyBaseDownPOST(RedirectAttributes rttr
			, @RequestParam(value="make_date", defaultValue="") String make_date
			, @RequestParam(value="user_id", defaultValue="") String up_id
			, @RequestParam(value="menu_id", required=true) List<String> menu_id) throws Exception{

		logger.info("dailyBaseDownPOST ...>"+make_date+","+up_id);
		
		// create file & zip
		dbdservice.fileDown(make_date, up_id, menu_id);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		// GO TO dailyBaseDownGET
		return "redirect:/fileupdw/dailyBaseDown?make_date="+make_date;
	}
	
	// Zip File Download 16.05.03
	@RequestMapping(value = "/dailybasefiledown", method = RequestMethod.GET)
	public void downloadzip(HttpServletRequest request
			, HttpServletResponse response
			, @RequestParam(value="file_name", defaultValue="") String file_name) throws Exception {
		
		logger.info("downloadzip ...>"+file_name);
		
		// the complete absolute path of the file
		String zip_path = FssENV.BaseFileDir + "/" + file_name;
		File downloadFile = new File(zip_path);
		FileInputStream inputstream = new FileInputStream(downloadFile);
		
		// get MIME type of file
		String mimetype = request.getServletContext().getMimeType(zip_path);
		if(mimetype == null){
			// set to binary type if MIME mapping not found
			mimetype = "application/octet-stream";
		}
		
		// set contect attributes for the response
		response.setContentType(mimetype);
		response.setContentLength((int)downloadFile.length());
		// set headers for the response
		response.setHeader("Content-Disposition", "attachment;filename="+file_name);
		
		// get output stream of the response
		ServletOutputStream outStream = response.getOutputStream();
		
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		// write bytes read from the input stream into the output stream
		while((bytesRead = inputstream.read(buffer)) != -1){
			outStream.write(buffer, 0, bytesRead);
		}
		
		inputstream.close();
		outStream.close();
	}

}
